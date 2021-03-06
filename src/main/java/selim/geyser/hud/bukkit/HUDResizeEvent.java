package selim.geyser.hud.bukkit;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public class HUDResizeEvent extends PlayerEvent {

	private static final HandlerList handlers = new HandlerList();

	private final int oldWidth;
	private final int oldHeight;
	private final int width;
	private final int height;

	public HUDResizeEvent(Player player, int oldWidth, int oldHeight, int width, int height) {
		super(player);
		this.oldWidth = oldWidth;
		this.oldHeight = oldHeight;
		this.width = width;
		this.height = height;
	}

	public int getOldWidth() {
		return this.oldWidth;
	}

	public int getOldHeight() {
		return this.oldHeight;
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

}
