package selim.geyser.hud.forge;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import selim.geyser.hud.shared.StringHUDPart;

public class StringHUDPartForge extends StringHUDPart {

	@Override
	public void toBytes(ByteBuf buf) {
		super.toBytes(buf);
		ByteBufUtils.writeUTF8String(buf, this.getText());
		buf.writeInt(this.getPositionX());
		buf.writeInt(this.getPositionY());
		buf.writeInt(this.getColor());
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		super.fromBytes(buf);
		this.setText(ByteBufUtils.readUTF8String(buf));
		this.setPositonX(buf.readInt());
		this.setPositonY(buf.readInt());
		this.setColor(buf.readInt());
	}

	@Override
	public void render() {
		Minecraft.getMinecraft().fontRenderer.drawString(getText(), getPositionX(), getPositionY(),
				getColor());
	}

}
