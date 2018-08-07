package selim.geyser.hud.forge;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import selim.geyser.hud.shared.StringHUDPart;

public class LocalizedStringHUDPartForge extends StringHUDPart {

	@Override
	public void render() {
		GlStateManager.pushMatrix();
		GlStateManager.scale(this.getScale(), this.getScale(), this.getScale());
		Minecraft.getMinecraft().fontRenderer.drawString(I18n.format(getText()), getPositionX(),
				getPositionY(), getColor());
		GlStateManager.popMatrix();
	}

}
