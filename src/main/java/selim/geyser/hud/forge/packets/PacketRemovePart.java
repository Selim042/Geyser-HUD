package selim.geyser.hud.forge.packets;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import selim.geyser.hud.forge.GeyserHUDForge;

public class PacketRemovePart implements IMessage {

	private int partId;

	public PacketRemovePart() {}

	public PacketRemovePart(int partId) {
		this.partId = partId;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.partId = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(this.partId);
	}

	public static class Handler implements IMessageHandler<PacketRemovePart, IMessage> {

		@Override
		public IMessage onMessage(PacketRemovePart message, MessageContext ctx) {
			GeyserHUDForge.HUD.removePart(message.partId);
			return null;
		}

	}

}
