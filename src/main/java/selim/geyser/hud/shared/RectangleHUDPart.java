package selim.geyser.hud.shared;

import io.netty.buffer.ByteBuf;

public class RectangleHUDPart extends AbstractHUDPart {

	private int width;
	private int height;
	private int color;

	public RectangleHUDPart() {}

	public RectangleHUDPart(int x, int y, int width, int height, int color) {
		this(x, y, width, height, color, 1.0f);
	}

	public RectangleHUDPart(int x, int y, int width, int height, int color, float scale) {
		super(x, y, scale);
		this.width = width;
		this.height = height;
		this.color = color;
	}

	@Override
	public void toBytes(ByteBuf buf) {
		super.toBytes(buf);
		buf.writeInt(this.width);
		buf.writeInt(this.height);
		buf.writeInt(this.color);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		super.fromBytes(buf);
		this.width = buf.readInt();
		this.height = buf.readInt();
		this.color = buf.readInt();
	}

	public int getWidth() {
		return this.width;
	}

	public RectangleHUDPart setWidth(int width) {
		this.width = width;
		this.markDirty();
		return this;
	}

	public int getHeight() {
		return this.height;
	}

	public RectangleHUDPart setHeight(int height) {
		this.height = height;
		this.markDirty();
		return this;
	}

	public int getColor() {
		return this.color;
	}

	public RectangleHUDPart setColor(int color) {
		this.color = color;
		this.markDirty();
		return this;
	}

}
