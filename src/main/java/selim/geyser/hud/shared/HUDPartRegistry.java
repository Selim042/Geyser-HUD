package selim.geyser.hud.shared;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class HUDPartRegistry {

	private static int currentId = 0;
	private static final Map<Integer, Class<? extends IHUDPart>> PARTS = new HashMap<>();

	public static int registerPart(Class<? extends IHUDPart> partClass) {
		IHUDPart part;
		try {
			part = partClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			return -1;
		}
		// if (part.getRegistryId() != -1)
		// return -1;
		int id = currentId++;
		PARTS.put(id, partClass);
		// part.setRegistryId(id);
		return id;
	}

	public static int getPartId(IHUDPart part) {
		return getPartId(part.getClass());
	}

	public static int getPartId(Class<? extends IHUDPart> partClass) {
		for (Entry<Integer, Class<? extends IHUDPart>> e : PARTS.entrySet())
			if (e.getValue().equals(partClass))
				return e.getKey();
		return -1;
	}

	public static Class<? extends IHUDPart> getPart(int id) {
		if (PARTS.containsKey(id))
			return PARTS.get(id);
		return null;
	}

	public static IHUDPart getPartInstance(int id) {
		if (PARTS.containsKey(id))
			try {
				return PARTS.get(id).newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		return null;
	}

}
