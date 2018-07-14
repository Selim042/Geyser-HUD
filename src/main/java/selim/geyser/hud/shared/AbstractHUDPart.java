package selim.geyser.hud.shared;

import io.netty.buffer.ByteBuf;

public abstract class AbstractHUDPart implements IHUDPart {

	private boolean dirty;
	private int hudId = -1;

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(this.hudId);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.hudId = buf.readInt();
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

}
