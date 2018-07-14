package selim.geyser.hud.forge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import selim.geyser.hud.shared.IGeyserHUD;
import selim.geyser.hud.shared.IHUDPart;

public class ForgeGeyserHUD implements IGeyserHUD {

	private int partId = 0;
	private List<Integer> reusableIds = new ArrayList<>();
	private Map<Integer, IHUDPart> parts = new HashMap<>();
	private boolean dirty;

	@Override
	public IHUDPart getPart(int id) {
		return parts.get(id);
	}

	@Override
	public IHUDPart addPart(IHUDPart part) {
		if (part.getHUDId() != -1) {
			parts.put(part.getHUDId(), part);
			return part;
		}
		part.setHUDId(getAvailableId());
		parts.put(part.getHUDId(), part);
		return part;
	}

	@Override
	public IHUDPart removePart(int id) {
		IHUDPart part = parts.remove(id);
		return part;
	}

	@Override
	public IHUDPart removePart(IHUDPart part) {
		if (part == null)
			return null;
		int id = -1;
		for (int i = 0; i < parts.size(); i++)
			if (part.equals(parts.get(i)))
				id = i;
		if (id == -1)
			return null;
		return removePart(id);
	}

	@Override
	public IHUDPart[] getParts() {
		// TODO: optimize
		List<IHUDPart> parts = new LinkedList<>();
		for (Entry<Integer, IHUDPart> e : this.parts.entrySet())
			parts.add(e.getValue());
		return parts.toArray(new IHUDPart[0]);
	}

	private int getAvailableId() {
		if (reusableIds.isEmpty())
			return partId++;
		int id = reusableIds.get(0);
		reusableIds.remove(0);
		return id;
	}

	// @Override
	// public boolean isDirty() {
	// if (this.dirty)
	// return this.dirty;
	// for (Entry<Integer, IHUDPart> e : parts.entrySet())
	// return e.getValue().isDirty();
	// return false;
	// }

	// @Override
	// public void markDirty() {
	// this.dirty = true;
	// }

	@Override
	public void update() {}

	protected void clean() {
		this.dirty = false;
	}

}
