package selim.geyser.hud.bukkit;

import java.util.LinkedList;
import java.util.List;

import org.bukkit.inventory.ItemStack;

import selim.geyser.hud.shared.IGeyserHUD;
import selim.geyser.hud.shared.RectangleRender;
import selim.geyser.hud.shared.StringRender;

public class SpigotGeyserHUD implements IGeyserHUD<ItemStack> {

	protected final List<StringRender> stringRenders = new LinkedList<>();
	protected final List<RectangleRender> rectangleRenders = new LinkedList<>();
	protected final List<ItemStack> stacks = new LinkedList<>();

	@Override
	public void renderText(String text, int x, int y, int color) {
		stringRenders.add(new StringRender(text, x, y, color));
	}

	@Override
	public void renderRectangle(int x, int y, int width, int height, int color) {
		rectangleRenders.add(new RectangleRender(x, y, width, height, color));
	}

	@Override
	public void renderItemStack(ItemStack stack) {
		stacks.add(stack);
	}

	@Override
	public List<StringRender> getStringRenders() {
		return stringRenders;
	}

	@Override
	public List<RectangleRender> getRectangleRenders() {
		return rectangleRenders;
	}

	@Override
	public List<ItemStack> getItemStacks() {
		return stacks;
	}

}
