package selim.geyser.hud.bukkit.packets;

import io.netty.buffer.ByteBuf;
import selim.geyser.core.bukkit.network.GeyserPacket;
import selim.geyser.hud.shared.HUDPartRegistry;
import selim.geyser.hud.shared.IHUDPart;

public class PacketModifyPart extends GeyserPacket {

	private IHUDPart part;

	public PacketModifyPart() {}

	public PacketModifyPart(IHUDPart part) {
		this.part = part;
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(HUDPartRegistry.getPartId(this.part));
		this.part.toBytes(buf);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.part = HUDPartRegistry.getPartInstance(buf.readInt());
		this.part.fromBytes(buf);
	}

}
