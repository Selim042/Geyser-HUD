package selim.geyser.hud.shared;

import java.util.List;

public interface IGeyserHUD<S> {

	public default void renderText(String text, int x, int y) {
		renderText(text, x, y, 0xFFFFFF);
	}

	public void renderText(String text, int x, int y, int color);

	public void renderRectangle(int x, int y, int width, int height, int color);

	public void renderItemStack(S stack);

	public List<StringRender> getStringRenders();

	public List<RectangleRender> getRectangleRenders();

	public List<S> getItemStacks();

}
