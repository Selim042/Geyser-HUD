package selim.geyser.hud.shared;

import io.netty.buffer.ByteBuf;
import selim.geyser.core.shared.SharedByteBufUtils;

public class LocalizedStringHUDPart extends AbstractHUDPart {

	private String text;
	private int color;

	public LocalizedStringHUDPart() {}

	public LocalizedStringHUDPart(String text, int x, int y) {
		this(text, x, y, 0xFFFFFF);
	}

	public LocalizedStringHUDPart(String text, int x, int y, int color) {
		this(text, x, y, color, 1.0f);
	}

	public LocalizedStringHUDPart(String text, int x, int y, int color, float scale) {
		super(x, y, scale);
		this.text = text;
		this.color = color;
	}

	public String getText() {
		return this.text;
	}

	public int getColor() {
		return this.color;
	}

	public LocalizedStringHUDPart setText(String text) {
		this.text = text;
		this.markDirty();
		return this;
	}

	public LocalizedStringHUDPart setColor(int color) {
		this.color = color;
		this.markDirty();
		return this;
	}

	@Override
	public void toBytes(ByteBuf buf) {
		super.toBytes(buf);
		SharedByteBufUtils.writeUTF8String(buf, this.getText());
		buf.writeInt(this.getColor());
		buf.writeInt(0);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		super.fromBytes(buf);
		this.setText(SharedByteBufUtils.readUTF8String(buf));
		this.setColor(buf.readInt());
	}

}
