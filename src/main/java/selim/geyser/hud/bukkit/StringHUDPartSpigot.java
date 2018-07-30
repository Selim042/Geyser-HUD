package selim.geyser.hud.bukkit;

import io.netty.buffer.ByteBuf;
import selim.geyser.core.shared.SharedByteBufUtils;
import selim.geyser.hud.shared.StringHUDPart;

public class StringHUDPartSpigot extends StringHUDPart {

	public StringHUDPartSpigot() {}

	public StringHUDPartSpigot(String text, int x, int y) {
		this(text, x, y, 0xFFFFFF);
	}

	public StringHUDPartSpigot(String text, int x, int y, int color) {
		super(text, x, y, color);
	}

	public StringHUDPartSpigot(String text, int x, int y, int color, float scale) {
		super(text, x, y, color, scale);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		super.toBytes(buf);
		SharedByteBufUtils.writeUTF8String(buf, this.getText());
		buf.writeInt(this.getColor());
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		super.fromBytes(buf);
		this.setText(SharedByteBufUtils.readUTF8String(buf));
		this.setColor(buf.readInt());
	}

}
