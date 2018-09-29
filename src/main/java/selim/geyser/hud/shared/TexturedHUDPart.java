package selim.geyser.hud.shared;

import io.netty.buffer.ByteBuf;
import selim.geyser.core.shared.RegistryKey;
import selim.geyser.core.shared.SharedByteBufUtils;

public class TexturedHUDPart extends RectangleHUDPart {

	private RegistryKey texture;
	private int textureX;
	private int textureY;
	private int textureWidth;
	private int textureHeight;

	public TexturedHUDPart() {}

	public TexturedHUDPart(RegistryKey texture, int x, int y, int textureX, int textureY,
			int textureWidth, int textureHeight) {
		this(texture, x, y, textureWidth, textureHeight, textureX, textureY, textureWidth,
				textureHeight);
	}

	public TexturedHUDPart(RegistryKey texture, int x, int y, int renderWidth, int renderHeight,
			int textureX, int textureY, int textureWidth, int textureHeight) {
		super(x, y, renderWidth, renderHeight, -1);
		this.texture = texture;
		this.textureX = textureX;
		this.textureY = textureY;
		this.textureWidth = textureWidth;
		this.textureHeight = textureHeight;
	}

	@Override
	public void toBytes(ByteBuf buf) {
		super.toBytes(buf);
		SharedByteBufUtils.writeUTF8String(buf, this.texture.getNamespace());
		SharedByteBufUtils.writeUTF8String(buf, this.texture.getKey());
		buf.writeInt(this.textureX);
		buf.writeInt(this.textureY);
		buf.writeInt(this.textureWidth);
		buf.writeInt(this.textureHeight);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		super.fromBytes(buf);
		String namespace = SharedByteBufUtils.readUTF8String(buf);
		String key = SharedByteBufUtils.readUTF8String(buf);
		this.texture = new RegistryKey(namespace,key);
		this.textureX = buf.readInt();
		this.textureY = buf.readInt();
		this.textureWidth = buf.readInt();
		this.textureHeight = buf.readInt();
	}

	public RegistryKey getTexture() {
		return this.texture;
	}

	public int getTextureX() {
		return this.textureX;
	}

	public int getTextureY() {
		return this.textureY;
	}

	public int getTextureWidth() {
		return this.textureWidth;
	}

	public int getTextureHeight() {
		return this.textureHeight;
	}

}
