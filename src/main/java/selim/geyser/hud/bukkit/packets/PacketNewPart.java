package selim.geyser.hud.bukkit.packets;

import io.netty.buffer.ByteBuf;
import selim.geyser.core.bukkit.network.GeyserPacket;
import selim.geyser.hud.shared.HUDPartRegistry;
import selim.geyser.hud.shared.IHUDPart;

public class PacketNewPart extends GeyserPacket {

	private IHUDPart part;

	public PacketNewPart() {}

	public PacketNewPart(IHUDPart part) {
		this.part = part;
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(this.part.getRegistryId());
		this.part.toBytes(buf);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.part = HUDPartRegistry.getPartInstance(buf.readInt());
		this.part.fromBytes(buf);
	}

}
