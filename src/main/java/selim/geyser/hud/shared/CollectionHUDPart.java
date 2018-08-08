package selim.geyser.hud.shared;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import io.netty.buffer.ByteBuf;
import selim.geyser.core.shared.RegistryKey;
import selim.geyser.core.shared.SharedByteBufUtils;

public class CollectionHUDPart extends AbstractHUDPart {

	private boolean relativePosition;
	private final Map<RegistryKey, IHUDPart> parts = new HashMap<>();

	@Override
	public void toBytes(ByteBuf buf) {
		super.toBytes(buf);
		buf.writeBoolean(this.relativePosition);
		buf.writeInt(this.parts.size());
		for (Entry<RegistryKey, IHUDPart> e : parts.entrySet()) {
			RegistryKey key = e.getKey();
			SharedByteBufUtils.writeUTF8String(buf, key.getNamespace());
			SharedByteBufUtils.writeUTF8String(buf, key.getKey());
			e.getValue().toBytes(buf);
		}
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		super.fromBytes(buf);
		this.relativePosition = buf.readBoolean();
		int size = buf.readInt();
		for (int i = 0; i < size; i++) {
			RegistryKey key = new RegistryKey(SharedByteBufUtils.readUTF8String(buf),
					SharedByteBufUtils.readUTF8String(buf));
			IHUDPart part = HUDPartRegistry.getPartInstance(buf.readInt());
			part.fromBytes(buf);
			parts.put(key, part);
		}
	}

}
