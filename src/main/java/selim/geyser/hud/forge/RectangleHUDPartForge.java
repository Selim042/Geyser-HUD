package selim.geyser.hud.forge;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import selim.geyser.hud.shared.RectangleHUDPart;

public class RectangleHUDPartForge extends RectangleHUDPart {

	@Override
	public void render() {
		GlStateManager.pushMatrix();
		GlStateManager.scale(this.getScale(), this.getScale(), this.getScale());
		int color = this.getColor();
		if (color < 0x01000000)
			color += 0xFF000000;
		Gui.drawRect(getPositionX(), getPositionY(), getPositionX() + getWidth(),
				getPositionY() + getHeight(), color);
		GlStateManager.popMatrix();
	}

}
