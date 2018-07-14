package selim.geyser.hud.shared;

public interface IGeyserHUD {

	public IHUDPart getPart(int id);

	public IHUDPart addPart(IHUDPart part);

	public IHUDPart[] getParts();

	public boolean isDirty();

	public void markDirty();

}
