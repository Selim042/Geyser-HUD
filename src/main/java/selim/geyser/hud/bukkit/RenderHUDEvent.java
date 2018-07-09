package selim.geyser.hud.bukkit;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.ItemStack;

import selim.geyser.hud.shared.IGeyserHUD;

public class RenderHUDEvent extends PlayerEvent {

	private static final HandlerList handlers = new HandlerList();

	private final IGeyserHUD<ItemStack> hud = new SpigotGeyserHUD();

	public RenderHUDEvent(Player who) {
		super(who);
	}

	public IGeyserHUD<ItemStack> getHUD() {
		return this.hud;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

}
