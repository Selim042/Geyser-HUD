package selim.geyser.hud.forge.packets;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import selim.geyser.hud.forge.ForgeGeyserHUD;
import selim.geyser.hud.forge.GeyserHUDForge;

public class PacketClearHUD implements IMessage {

	@Override
	public void fromBytes(ByteBuf buf) {}

	@Override
	public void toBytes(ByteBuf buf) {}

	public static class Handler implements IMessageHandler<PacketClearHUD, PacketScreenSize> {

		@Override
		public PacketScreenSize onMessage(PacketClearHUD message, MessageContext ctx) {
			GeyserHUDForge.HUD = new ForgeGeyserHUD();
			return new PacketScreenSize();
		}

	}

}
