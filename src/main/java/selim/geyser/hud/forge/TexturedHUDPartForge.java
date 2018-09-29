package selim.geyser.hud.forge;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import selim.geyser.core.shared.RegistryKey;
import selim.geyser.hud.shared.TexturedHUDPart;

public class TexturedHUDPartForge extends TexturedHUDPart {

	@Override
	public void render() {
		GlStateManager.pushMatrix();
		GlStateManager.scale(this.getScale(), this.getScale(), this.getScale());
		Minecraft.getMinecraft().getTextureManager().bindTexture(getResourceLocation(this.getTexture()));
		Gui.drawModalRectWithCustomSizedTexture(this.getPositionX(), this.getPositionY(),
				this.getTextureX(), this.getTextureY(), this.getWidth(), this.getHeight(),
				this.getTextureWidth(), this.getTextureWidth());
		GlStateManager.popMatrix();
	}

	private ResourceLocation getResourceLocation(RegistryKey key) {
		return new ResourceLocation(key.getNamespace(), "textures/" + key.getKey() + ".png");
	}

}
