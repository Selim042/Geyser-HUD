package selim.geyser.hud.forge;

import org.bukkit.Color;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.GlStateManager;
import selim.geyser.hud.shared.RectangleHUDPart;

public class RectangleHUDPartForge extends RectangleHUDPart {

	@Override
	public void render() {
		GlStateManager.pushMatrix();
		GlStateManager.glBegin(GL11.GL_QUADS);
		Color color = Color.fromRGB(this.getColor());
		GlStateManager.color(color.getRed() / (float) 255, color.getGreen() / (float) 255,
				color.getBlue() / (float) 255);
		GlStateManager.glVertex3f(this.getPositionX(), this.getPositionY(), 0);
		GlStateManager.glVertex3f(this.getPositionX() + this.getWidth(), this.getPositionY(), 0);
		GlStateManager.glVertex3f(this.getPositionX() + this.getWidth(),
				this.getPositionY() + this.getHeight(), 0);
		GlStateManager.glVertex3f(this.getPositionX(), this.getPositionY() + this.getHeight(), 0);
		GlStateManager.glEnd();
		GlStateManager.popMatrix();
	}

}
