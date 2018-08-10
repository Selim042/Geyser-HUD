package selim.geyser.hud.forge;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import selim.geyser.hud.shared.AbstractHUDPart;

public class ItemStackHUDPartForge extends AbstractHUDPart {

	private ItemStack stack;

	public ItemStackHUDPartForge() {}

	public ItemStackHUDPartForge(ItemStack stack, int x, int y) {
		this(stack, x, y, 1.0f);
	}

	public ItemStackHUDPartForge(ItemStack stack, int x, int y, float scale) {
		super(x, y, scale);
		this.stack = stack;
	}

	@Override
	public void toBytes(ByteBuf buf) {
		super.toBytes(buf);
		ByteBufUtils.writeItemStack(buf, this.stack);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		super.fromBytes(buf);
		this.stack = ByteBufUtils.readItemStack(buf);
	}

	@Override
	public void render() {
		GlStateManager.pushMatrix();
		GlStateManager.enableLighting();
		RenderHelper.disableStandardItemLighting();
		RenderHelper.enableGUIStandardItemLighting();
		GlStateManager.scale(this.getScale(), this.getScale(), this.getScale());
		Minecraft.getMinecraft().getRenderItem().renderItemAndEffectIntoGUI(this.stack,
				this.getPositionX(), this.getPositionY());
		GlStateManager.popMatrix();
	}

	public ItemStack getStack() {
		return this.stack;
	}

	public ItemStackHUDPartForge setStack(ItemStack stack) {
		this.stack = stack;
		this.markDirty();
		return this;
	}

}
