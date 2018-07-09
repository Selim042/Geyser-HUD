package selim.geyser.hud.forge;

import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import selim.geyser.hud.shared.IGeyserHUD;
import selim.geyser.hud.shared.RectangleRender;
import selim.geyser.hud.shared.StringRender;

public class PacketSendHUD implements IMessage {

	private final IGeyserHUD<ItemStack> hud = new ForgeGeyserHUD();

	@Override
	public void fromBytes(ByteBuf buf) {
		int numStrings = buf.readInt();
		for (int i = 0; i < numStrings; i++)
			hud.getStringRenders().add(new StringRender(ByteBufUtils.readUTF8String(buf), buf.readInt(),
					buf.readInt(), buf.readInt()));
		int numRects = buf.readInt();
		for (int i = 0; i < numRects; i++)
			hud.getRectangleRenders().add(new RectangleRender(buf.readInt(), buf.readInt(),
					buf.readInt(), buf.readInt(), buf.readInt()));
		int numStacks = buf.readInt();
		for (int i = 0; i < numStacks; i++)
			hud.getItemStacks().add(ByteBufUtils.readItemStack(buf));
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(hud.getStringRenders().size());
		for (StringRender sr : hud.getStringRenders()) {
			ByteBufUtils.writeUTF8String(buf, sr.text);
			buf.writeInt(sr.x);
			buf.writeInt(sr.y);
			buf.writeInt(sr.color);
		}
		buf.writeInt(hud.getRectangleRenders().size());
		for (RectangleRender rr : hud.getRectangleRenders()) {
			buf.writeInt(rr.x);
			buf.writeInt(rr.y);
			buf.writeInt(rr.width);
			buf.writeInt(rr.height);
			buf.writeInt(rr.color);
		}
		buf.writeInt(hud.getItemStacks().size());
		for (ItemStack s : hud.getItemStacks())
			ByteBufUtils.writeItemStack(buf, s);
	}

	public static class Handler implements IMessageHandler<PacketSendHUD, IMessage> {

		@Override
		public IMessage onMessage(PacketSendHUD message, MessageContext ctx) {
			GeyserHUDForge.CURRENT_HUD = message.hud;
			return null;
		}

	}

}
