package selim.geyser.hud.bukkit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.entity.Player;

import selim.geyser.core.shared.RegistryKey;
import selim.geyser.hud.bukkit.packets.PacketModifyPart;
import selim.geyser.hud.bukkit.packets.PacketNewPart;
import selim.geyser.hud.bukkit.packets.PacketRemovePart;
import selim.geyser.hud.shared.IGeyserHUD;
import selim.geyser.hud.shared.IHUDPart;

public class SpigotGeyserHUD implements IGeyserHUD {

	private final Player player;

	private int partId = 0;
	private List<Integer> reusableIds = new ArrayList<>();
	private Map<Integer, IHUDPart> parts = new HashMap<>();
	private Map<RegistryKey, Integer> keys = new HashMap<>();
	private int width;
	private int height;

	public SpigotGeyserHUD(Player player) {
		this.player = player;
	}

	@Override
	public IHUDPart getPart(int id) {
		return parts.get(id);
	}

	@Override
	public <T extends IHUDPart> T addPart(T part) {
		if (part.getHUDId() != -1) {
			parts.put(part.getHUDId(), part);
			return part;
		}
		part.setHUDId(getAvailableId());
		parts.put(part.getHUDId(), part);
		GeyserHUDSpigot.NETWORK.sendPacket(this.player, new PacketNewPart(part));
		return part;
	}

	@Override
	public <T extends IHUDPart> T addPart(RegistryKey key, T part) {
		addPart(part);
		if (keys.containsKey(key)) {
			int id = keys.get(key);
			keys.remove(key);
			removePart(id);
		}
		keys.put(key, part.getHUDId());
		return part;
	}

	@Override
	public IHUDPart getPart(RegistryKey key) {
		if (keys.containsKey(key))
			return parts.get(keys.get(key));
		return null;
	}

	@Override
	public IHUDPart removePart(int id) {
		IHUDPart part = parts.remove(id);
		if (part != null)
			GeyserHUDSpigot.NETWORK.sendPacket(this.player, new PacketRemovePart(id));
		return part;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends IHUDPart> T removePart(T part) {
		if (part == null)
			return null;
		int id = -1;
		for (int i = 0; i < parts.size(); i++)
			if (part.equals(parts.get(i)))
				id = i;
		if (id == -1)
			return null;
		return (T) removePart(id);
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

	@Override
	public void update() {
		for (Entry<Integer, IHUDPart> e : parts.entrySet()) {
			if (e.getValue().isDirty()) {
				IHUDPart part = e.getValue();
				part.clean();
				GeyserHUDSpigot.NETWORK.sendPacket(this.player, new PacketModifyPart(part));
			}
		}
	}

	@Override
	public int getWidth() {
		return this.width;
	}

	@Override
	public int getHeight() {
		return this.height;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
