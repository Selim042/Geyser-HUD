package selim.geyser.hud.bukkit;

import org.bukkit.inventory.ItemStack;

import io.netty.buffer.ByteBuf;
import selim.geyser.core.bukkit.BukkitByteBufUtils;
import selim.geyser.hud.shared.AbstractHUDPart;

public class ItemStackHUDPartSpigot extends AbstractHUDPart {

	private ItemStack stack;
	private int x;
	private int y;

	public ItemStackHUDPartSpigot() {}

	public ItemStackHUDPartSpigot(ItemStack stack, int x, int y) {
		this.stack = stack;
		this.x = x;
		this.y = y;
	}

	@Override
	public void toBytes(ByteBuf buf) {
		super.toBytes(buf);
		BukkitByteBufUtils.writeItemStack(buf, this.stack);
		buf.writeInt(this.x);
		buf.writeInt(this.y);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		super.fromBytes(buf);
		this.stack = BukkitByteBufUtils.readItemStack(buf);
		this.x = buf.readInt();
		this.y = buf.readInt();
	}

}
