package selim.geyser.hud.bukkit;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import selim.geyser.core.bukkit.network.NetworkHandler;
import selim.geyser.core.shared.EnumComponent;
import selim.geyser.core.shared.IGeyserCorePlugin;
import selim.geyser.hud.bukkit.packets.PacketClearHUD;
import selim.geyser.hud.bukkit.packets.PacketModifyPart;
import selim.geyser.hud.bukkit.packets.PacketNewPart;
import selim.geyser.hud.bukkit.packets.PacketRemovePart;
import selim.geyser.hud.bukkit.packets.PacketScreenSize;
import selim.geyser.hud.shared.GeyserHUDInfo;
import selim.geyser.hud.shared.HUDPartRegistry;
import selim.geyser.hud.shared.IGeyserHUD;
import selim.geyser.hud.shared.RectangleHUDPart;

public class GeyserHUDSpigot extends JavaPlugin implements Listener, IGeyserCorePlugin {

	protected static Logger LOGGER;
	protected static GeyserHUDSpigot INSTANCE;
	protected static NetworkHandler NETWORK;

	private static final Map<Player, IGeyserHUD> HUDS = new ConcurrentHashMap<>();

	public static IGeyserHUD getHud(Player player) {
		if (HUDS.containsKey(player))
			return HUDS.get(player);
		return null;
	}

	@Override
	public void onEnable() {
		LOGGER = this.getLogger();
		INSTANCE = this;

		NETWORK = NetworkHandler.registerChannel(this, GeyserHUDInfo.CHANNEL);
		NETWORK.registerPacket(GeyserHUDInfo.PacketDiscrimators.SEND_PART, PacketNewPart.class);
		NETWORK.registerPacket(GeyserHUDInfo.PacketDiscrimators.CLEAR_HUD, PacketClearHUD.class);
		NETWORK.registerPacket(GeyserHUDInfo.PacketDiscrimators.REMOVE_PART, PacketRemovePart.class);
		NETWORK.registerPacket(GeyserHUDInfo.PacketDiscrimators.MODIFY_PART, PacketModifyPart.class);
		NETWORK.registerPacket(GeyserHUDInfo.PacketDiscrimators.SCREEN_SIZE, PacketScreenSize.class);

		PluginManager manager = this.getServer().getPluginManager();
		manager.registerEvents(this, this);

		HUDPartRegistry.registerPart(StringHUDPartSpigot.class);
		HUDPartRegistry.registerPart(RectangleHUDPart.class);
		HUDPartRegistry.registerPart(ItemStackHUDPartSpigot.class);
	}

	@Override
	public EnumComponent[] providedComponents() {
		return new EnumComponent[] { EnumComponent.HUD };
	}

	private int getPing(Player player) {
		try {
			Object entityPlayer = player.getClass().getMethod("getHandle").invoke(player);
			return (int) entityPlayer.getClass().getField("ping").get(entityPlayer);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | NoSuchFieldException e) {
			this.getLogger().log(Level.INFO, "Unable to get ping for " + player.getDisplayName()
					+ ", encountered a " + e.getClass().getName());
			e.printStackTrace();
			return -1;
		}
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		int ping = getPing(player);
		if (ping <= 0)
			ping = 40;
		else
			ping = ping / 25;
		this.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {

			@Override
			public void run() {
				if (player.getListeningPluginChannels().contains(GeyserHUDInfo.CHANNEL)) {
					NETWORK.sendPacket(player, new PacketClearHUD());
					HUDS.put(player, new SpigotGeyserHUD(player));
				}
			}
		}, ping);
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		HUDS.remove(event.getPlayer());
	}

	public static void main(String[] args) {
		RectangleHUDPart comp = new RectangleHUDPart(1, 2, 3, 4, 5);
		ByteBuf buf = Unpooled.buffer();
		comp.toBytes(buf);
		RectangleHUDPart comp2 = new RectangleHUDPart();
		comp2.fromBytes(buf);
		System.out.println("x: " + comp2.getPositionX() + ", y: " + comp2.getPositionY() + ", width: "
				+ comp2.getWidth() + ", height: " + comp2.getHeight());
	}

}
