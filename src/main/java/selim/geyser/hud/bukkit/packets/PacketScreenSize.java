package selim.geyser.hud.bukkit.packets;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import io.netty.buffer.ByteBuf;
import selim.geyser.core.bukkit.network.GeyserPacket;
import selim.geyser.core.bukkit.network.GeyserPacketHandler;
import selim.geyser.hud.bukkit.GeyserHUDSpigot;
import selim.geyser.hud.bukkit.HUDResizeEvent;
import selim.geyser.hud.bukkit.SpigotGeyserHUD;
import selim.geyser.hud.shared.IGeyserHUD;

public class PacketScreenSize extends GeyserPacket {

	private int width;
	private int height;

	@Override
	public void toBytes(ByteBuf buf) {}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.width = buf.readInt();
		this.height = buf.readInt();
	}

	public static class Handler extends GeyserPacketHandler<PacketScreenSize, GeyserPacket> {

		@Override
		public GeyserPacket handle(Player player, PacketScreenSize packet) {
			IGeyserHUD hud = GeyserHUDSpigot.getHud(player);
			if (hud != null && hud instanceof SpigotGeyserHUD) {
				SpigotGeyserHUD gHud = (SpigotGeyserHUD) hud;
				int oldWidth = hud.getWidth();
				int oldHeight = hud.getHeight();
				gHud.setWidth(packet.width);
				gHud.setHeight(packet.height);
				Bukkit.getPluginManager().callEvent(
						new HUDResizeEvent(player, oldWidth, oldHeight, packet.width, packet.height));
			}
			return null;
		}

	}

}
