package selim.geyser.hud.shared;

public interface IGeyserHUD {

	public IHUDPart getPart(int id);

	public <T extends IHUDPart> T addPart(T part);

	public IHUDPart removePart(int id);

	public <T extends IHUDPart> T removePart(T part);

	public IHUDPart[] getParts();

	public void update();

	public int getWidth();

	public int getHeight();

}
