package selim.geyser.hud.bukkit.packets;

import io.netty.buffer.ByteBuf;
import selim.geyser.core.bukkit.network.GeyserPacket;

public class PacketRemovePart extends GeyserPacket {

	private int partId;

	public PacketRemovePart() {}

	public PacketRemovePart(int partId) {
		this.partId = partId;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.partId = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(this.partId);
	}

}
