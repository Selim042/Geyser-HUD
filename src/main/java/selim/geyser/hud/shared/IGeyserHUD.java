package selim.geyser.hud.shared;

import selim.geyser.core.shared.RegistryKey;

public interface IGeyserHUD {

	public IHUDPart getPart(int id);

	public <T extends IHUDPart> T addPart(T part);

	public <T extends IHUDPart> T addPart(RegistryKey key, T part);

	public IHUDPart getPart(RegistryKey key);

	public IHUDPart removePart(int id);

	public <T extends IHUDPart> T removePart(T part);

	public IHUDPart[] getParts();

	public void update();

	public int getWidth();

	public int getHeight();

}
