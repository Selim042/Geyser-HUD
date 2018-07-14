package selim.geyser.hud.shared;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class RectangleHUDPart extends AbstractHUDPart {

	private int x;
	private int y;
	private int width;
	private int height;
	private int color;

	public RectangleHUDPart() {}

	public RectangleHUDPart(int x, int y, int width, int height, int color) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
	}

	@Override
	public void toBytes(ByteBuf buf) {
		allToBuf(buf, x, y, width, height, color);
		// buf.writeInt(this.x);
		// buf.writeInt(this.y);
		// buf.writeInt(this.width);
		// buf.writeInt(this.height);
		// buf.writeInt(this.color);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		Object[] objs = allFromBuf(buf, Integer.class, Integer.class, Integer.class, Integer.class,
				Integer.class);
		x = (int) objs[0];
		y = (int) objs[1];
		width = (int) objs[2];
		height = (int) objs[3];
	}

	public int getPositionX() {
		return this.x;
	}

	public RectangleHUDPart setPositionX(int x) {
		this.x = x;
		this.markDirty();
		return this;
	}

	public int getPositionY() {
		return this.y;
	}

	public RectangleHUDPart setPositionY(int y) {
		this.y = y;
		this.markDirty();
		return this;
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

	private static void allToBuf(ByteBuf buf, Object... objects) {
		for (Object obj : objects)
			if (obj instanceof Integer)
				buf.writeInt((Integer) obj);
	}

	private static Object[] allFromBuf(ByteBuf buf, Class<?>... classes) {
		Object[] objs = new Object[classes.length];
		for (int i = 0; i < objs.length && i < classes.length; i++)
			if (classes[i].isAssignableFrom(Integer.class))
				objs[i] = buf.readInt();
		return objs;
	}

	public static void main(String[] args) {
		RectangleHUDPart comp = new RectangleHUDPart(1, 2, 3, 4, 5);
		ByteBuf buf = Unpooled.buffer();
		comp.toBytes(buf);
		RectangleHUDPart comp2 = new RectangleHUDPart();
		comp2.fromBytes(buf);
		System.out.println("x: " + comp2.x + ", y: " + comp2.y + ", width: " + comp2.width + ", height: "
				+ comp2.height);
	}

}
