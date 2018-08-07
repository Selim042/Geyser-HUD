package selim.geyser.hud.forge;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import selim.geyser.hud.shared.StringHUDPart;

public class StringHUDPartForge extends StringHUDPart {

	@Override
	public void render() {
		GlStateManager.pushMatrix();
		GlStateManager.scale(this.getScale(), this.getScale(), this.getScale());
		Minecraft.getMinecraft().fontRenderer.drawString(getText(), getPositionX(), getPositionY(),
				getColor());
		GlStateManager.popMatrix();
	}

}
