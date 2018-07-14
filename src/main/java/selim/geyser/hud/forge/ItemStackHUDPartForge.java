package selim.geyser.hud.forge;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import selim.geyser.hud.shared.AbstractHUDPart;

public class ItemStackHUDPartForge extends AbstractHUDPart {

	private ItemStack stack;
	private int x;
	private int y;

	public ItemStackHUDPartForge() {}

	public ItemStackHUDPartForge(ItemStack stack, int x, int y) {
		this.stack = stack;
		this.x = x;
		this.y = y;
	}

	@Override
	public void toBytes(ByteBuf buf) {
		super.toBytes(buf);
		ByteBufUtils.writeItemStack(buf, this.stack);
		buf.writeInt(this.x);
		buf.writeInt(this.y);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		super.fromBytes(buf);
		this.stack = ByteBufUtils.readItemStack(buf);
		this.x = buf.readInt();
		this.y = buf.readInt();
	}

	@Override
	public void render() {
		Minecraft.getMinecraft().getRenderItem().renderItemAndEffectIntoGUI(this.stack, this.x, this.y);
	}

}
