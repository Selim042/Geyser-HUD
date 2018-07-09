package selim.geyser.hud.shared;

public interface IGeyserPlugin {

	public EnumComponent[] requiredComponents();

	public boolean requiredOnClient(EnumComponent component);

}
