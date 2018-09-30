package selim.geyser.hud.shared;

public class GeyserHUDInfo {

	public static final String ID = "geyserhud";
	public static final String NAME = "Geyser HUD";
	public static final String VERSION = "1.3.0";
	public static final String CHANNEL = "geyser_hud";

	public static class PacketDiscrimators {
		public static final char SEND_PART = (char) 'h';
		public static final char CLEAR_HUD = (char) 'c';
		public static final char REMOVE_PART = (char) 'r';
		public static final char MODIFY_PART = (char) 'm';
		public static final char SCREEN_SIZE = (char) 's';
	}

}
