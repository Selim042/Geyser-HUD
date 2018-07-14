package selim.geyser.hud.shared;

public abstract class StringHUDPart extends AbstractHUDPart {

	private String text;
	private int x;
	private int y;
	private int color;

	public StringHUDPart() {}

	public StringHUDPart(String text, int x, int y) {
		this(text, x, y, 0xFFFFFF);
	}

	public StringHUDPart(String text, int x, int y, int color) {
		this.text = text;
		this.x = x;
		this.y = y;
		this.color = color;
	}

	public String getText() {
		return this.text;
	}

	public int getPositionX() {
		return this.x;
	}

	public int getPositionY() {
		return this.y;
	}

	public int getColor() {
		return this.color;
	}

	public StringHUDPart setText(String text) {
		this.text = text;
		this.markDirty();
		return this;
	}

	public StringHUDPart setPositonX(int x) {
		this.x = x;
		this.markDirty();
		return this;
	}

	public StringHUDPart setPositonY(int y) {
		this.y = y;
		this.markDirty();
		return this;
	}

	public StringHUDPart setColor(int color) {
		this.color = color;
		this.markDirty();
		return this;
	}

}
