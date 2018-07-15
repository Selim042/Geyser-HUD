package selim.geyser.hud.shared;

public abstract class StringHUDPart extends AbstractHUDPart {

	private String text;
	private int color;

	public StringHUDPart() {}

	public StringHUDPart(String text, int x, int y) {
		this(text, x, y, 0xFFFFFF);
	}

	public StringHUDPart(String text, int x, int y, int color) {
		this(text, x, y, color, 1.0f);
	}

	public StringHUDPart(String text, int x, int y, int color, float scale) {
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

	public StringHUDPart setText(String text) {
		this.text = text;
		this.markDirty();
		return this;
	}

	public StringHUDPart setColor(int color) {
		this.color = color;
		this.markDirty();
		return this;
	}

}
