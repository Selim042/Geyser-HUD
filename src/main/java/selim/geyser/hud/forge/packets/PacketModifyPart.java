package selim.geyser.hud.forge.packets;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import selim.geyser.hud.forge.GeyserHUDForge;
import selim.geyser.hud.shared.HUDPartRegistry;
import selim.geyser.hud.shared.IHUDPart;

public class PacketModifyPart implements IMessage {

	private IHUDPart part;

	public PacketModifyPart() {}

	public PacketModifyPart(IHUDPart part) {
		this.part = part;
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(HUDPartRegistry.getPartId(this.part));
		this.part.toBytes(buf);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.part = HUDPartRegistry.getPartInstance(buf.readInt());
		this.part.fromBytes(buf);
	}

	public static class Handler implements IMessageHandler<PacketModifyPart, IMessage> {

		@Override
		public IMessage onMessage(PacketModifyPart message, MessageContext ctx) {
			GeyserHUDForge.HUD.addPart(message.part);
			return null;
		}

	}

}
