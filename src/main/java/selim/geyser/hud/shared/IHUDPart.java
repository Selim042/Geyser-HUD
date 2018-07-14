package selim.geyser.hud.shared;

import io.netty.buffer.ByteBuf;

public interface IHUDPart {

	public default void render() {};

	public void toBytes(ByteBuf buf);

	public void fromBytes(ByteBuf buf);

	public boolean isDirty();

	public void markDirty();

	public int getHUDId();

	public void setHUDId(int id);

}
