package selim.geyser.hud.shared;

public abstract class AbstractHUDPart implements IHUDPart {

	private boolean dirty;
	private static int registryId = -1;
	private int hudId = -1;

	@Override
	public boolean isDirty() {
		return this.dirty;
	}

	@Override
	public void markDirty() {
		this.dirty = true;
	}

	public void clean() {
		this.dirty = false;
	}

	@Override
	public int getRegistryId() {
		return AbstractHUDPart.registryId;
	}

	@Override
	public void setRegistryId(int id) {
		if (AbstractHUDPart.registryId == -1)
			AbstractHUDPart.registryId = id;
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
