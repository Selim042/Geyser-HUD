package selim.geyser.hud.forge;

import net.minecraft.client.gui.Gui;
import selim.geyser.hud.shared.RectangleHUDPart;

public class RectangleHUDPartForge extends RectangleHUDPart {

	@Override
	public void render() {
		int color = this.getColor();
		if (color < 0x01000000)
			color += 0xFF000000;
		Gui.drawRect(getPositionX(), getPositionY(), getPositionX() + getWidth(),
				getPositionY() + getHeight(), color);
	}

}
