package selim.geyser.hud.shared;

import io.netty.buffer.ByteBuf;

public class TooltipWindowHUDPart extends AbstractHUDPart {

	private int width;
	private int height;
	private int backgroundColor = -1;
	private int borderColorStart = -1;

	public TooltipWindowHUDPart() {}

	public TooltipWindowHUDPart(int x, int y, int width, int height) {
		this(x, y, width, height, -1, -1);
	}

	public TooltipWindowHUDPart(int x, int y, int width, int height, int backgroundColor,
			int borderColor) {
		super(x, y);
		this.width = width;
		this.height = height;
		this.backgroundColor = backgroundColor;
		this.borderColorStart = borderColor;
	}

	@Override
	public void toBytes(ByteBuf buf) {
		super.toBytes(buf);
		buf.writeInt(this.width);
		buf.writeInt(this.height);
		buf.writeInt(this.backgroundColor);
		buf.writeInt(this.borderColorStart);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		super.fromBytes(buf);
		this.width = buf.readInt();
		this.height = buf.readInt();
		this.backgroundColor = buf.readInt();
		this.borderColorStart = buf.readInt();
	}

	public TooltipWindowHUDPart setWidth(int width) {
		int oldWidth = this.width;
		this.width = width;
		if (oldWidth != width)
			this.markDirty();
		return this;
	}

	public int getWidth() {
		return this.width;
	}

	public TooltipWindowHUDPart setHeight(int height) {
		int oldHeight = this.height;
		this.height = height;
		if (oldHeight != height)
			this.markDirty();
		return this;
	}

	public int getHeight() {
		return this.height;
	}

	public TooltipWindowHUDPart setBackgroundColor(int color) {
		int oldColor = this.backgroundColor;
		this.backgroundColor = color;
		if (oldColor != backgroundColor)
			this.markDirty();
		return this;
	}

	public int getBackgroundColor() {
		return this.backgroundColor;
	}

	public TooltipWindowHUDPart setBorderColor(int color) {
		int oldColor = this.borderColorStart;
		this.borderColorStart = color;
		if (oldColor != borderColorStart)
			this.markDirty();
		return this;
	}

	public int getBorderColor() {
		return this.borderColorStart;
	}

}
