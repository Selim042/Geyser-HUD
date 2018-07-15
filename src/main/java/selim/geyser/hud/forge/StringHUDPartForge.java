package selim.geyser.hud.forge;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import selim.geyser.hud.shared.StringHUDPart;

public class StringHUDPartForge extends StringHUDPart {

	@Override
	public void toBytes(ByteBuf buf) {
		super.toBytes(buf);
		ByteBufUtils.writeUTF8String(buf, this.getText());
		buf.writeInt(this.getColor());
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		super.fromBytes(buf);
		this.setText(ByteBufUtils.readUTF8String(buf));
		this.setColor(buf.readInt());
	}

	@Override
	public void render() {
		GlStateManager.pushMatrix();
		GlStateManager.scale(this.getScale(), this.getScale(), this.getScale());
		Minecraft.getMinecraft().fontRenderer.drawString(getText(), getPositionX(), getPositionY(),
				getColor());
		GlStateManager.popMatrix();
	}

}
