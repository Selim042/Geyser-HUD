package selim.geyser.hud.shared;

import io.netty.buffer.ByteBuf;

public abstract class AbstractHUDPart implements IHUDPart {

	private boolean dirty;
	private int hudId = -1;
	private int x;
	private int y;
	private float scale;
	private boolean hidden;

	public AbstractHUDPart() {}

	public AbstractHUDPart(int x, int y) {
		this(x, y, 1.0f);
	}

	public AbstractHUDPart(int x, int y, float scale) {
		this.x = x;
		this.y = y;
		this.scale = scale;
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(this.hudId);
		buf.writeInt(this.x);
		buf.writeInt(this.y);
		buf.writeFloat(this.scale);
		buf.writeBoolean(this.hidden);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.hudId = buf.readInt();
		this.x = buf.readInt();
		this.y = buf.readInt();
		this.scale = buf.readFloat();
		this.hidden = buf.readBoolean();
	}

	@Override
	public boolean isDirty() {
		return this.dirty;
	}

	@Override
	public void markDirty() {
		this.dirty = true;
	}

	@Override
	public void clean() {
		this.dirty = false;
	}

	@Override
	public int getHUDId() {
		return this.hudId;
	}

	@Override
	public void setHUDId(int id) {
		if (this.hudId == -1)
			this.hudId = id;
	}

	@Override
	public int getPositionX() {
		return this.x;
	}

	@Override
	public int getPositionY() {
		return this.y;
	}

	@Override
	public IHUDPart setPositionX(int x) {
		int prevX = this.x;
		this.x = x;
		if (prevX != x)
			this.markDirty();
		return this;
	}

	@Override
	public IHUDPart setPositionY(int y) {
		int prevY = this.y;
		this.y = y;
		if (prevY != y)
			this.markDirty();
		return this;
	}

	@Override
	public float getScale() {
		return this.scale;
	}

	@Override
	public IHUDPart setScale(float scale) {
		float prevScale = this.scale;
		this.scale = scale;
		if (prevScale != scale)
			this.markDirty();
		return this;
	}

	@Override
	public boolean isHidden() {
		return this.hidden;
	}

	@Override
	public void setHidden(boolean hidden) {
		boolean prevHidden = this.hidden;
		this.hidden = hidden;
		if (prevHidden != hidden)
			this.markDirty();
	}

}
