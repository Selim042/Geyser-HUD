package selim.geyser.hud.bukkit;

import org.bukkit.inventory.ItemStack;

import io.netty.buffer.ByteBuf;
import selim.geyser.core.bukkit.BukkitByteBufUtils;
import selim.geyser.hud.shared.AbstractHUDPart;

public class ItemStackHUDPartSpigot extends AbstractHUDPart {

	private ItemStack stack;

	public ItemStackHUDPartSpigot() {}

	public ItemStackHUDPartSpigot(ItemStack stack, int x, int y) {
		this(stack, x, y, 1.0f);
	}

	public ItemStackHUDPartSpigot(ItemStack stack, int x, int y, float scale) {
		super(x, y, scale);
		this.stack = stack;
	}

	@Override
	public void toBytes(ByteBuf buf) {
		super.toBytes(buf);
		BukkitByteBufUtils.writeItemStack(buf, this.stack);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		super.fromBytes(buf);
		this.stack = BukkitByteBufUtils.readItemStack(buf);
	}

}
