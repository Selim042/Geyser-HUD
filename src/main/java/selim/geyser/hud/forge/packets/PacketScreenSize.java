package selim.geyser.hud.forge.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketScreenSize implements IMessage {

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(Minecraft.getMinecraft().displayWidth / 2);
		buf.writeInt(Minecraft.getMinecraft().displayHeight / 2);
	}

	@Override
	public void fromBytes(ByteBuf buf) {}

	public static class Handler implements IMessageHandler<PacketScreenSize, IMessage> {

		@Override
		public IMessage onMessage(PacketScreenSize message, MessageContext ctx) {
			return null;
		}

	}

}
