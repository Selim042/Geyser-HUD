package selim.geyser.hud.forge;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import selim.geyser.hud.shared.TooltipWindowHUDPart;

public class TooltipWindowHUDPartForge extends TooltipWindowHUDPart {

	@Override
	public void render() {
		GlStateManager.disableRescaleNormal();
		RenderHelper.disableStandardItemLighting();
		GlStateManager.disableLighting();
		GlStateManager.disableDepth();

		int tooltipTextWidth = this.getWidth();
		int tooltipX = this.getPositionX() + 4;
		int tooltipY = this.getPositionY() + 4;
		int tooltipHeight = this.getHeight();

		final int zLevel = 300;
		int backgroundColor = 0xF0100010;
		if (this.getBackgroundColor() != -1)
			backgroundColor = this.getBackgroundColor();
		int borderColorStart = 0x505000FF;
		if (this.getBorderColor() != -1)
			borderColorStart = this.getBorderColor();
		int defaultBorderColorEnd = (borderColorStart & 0xFEFEFE) >> 1 | borderColorStart & 0xFF000000;

		drawGradientRect(zLevel, tooltipX - 3, tooltipY - 4, tooltipX + tooltipTextWidth + 3,
				tooltipY - 3, backgroundColor, backgroundColor);
		drawGradientRect(zLevel, tooltipX - 3, tooltipY + tooltipHeight + 3,
				tooltipX + tooltipTextWidth + 3, tooltipY + tooltipHeight + 4, backgroundColor,
				backgroundColor);
		drawGradientRect(zLevel, tooltipX - 3, tooltipY - 3, tooltipX + tooltipTextWidth + 3,
				tooltipY + tooltipHeight + 3, backgroundColor, backgroundColor);
		drawGradientRect(zLevel, tooltipX - 4, tooltipY - 3, tooltipX - 3, tooltipY + tooltipHeight + 3,
				backgroundColor, backgroundColor);
		drawGradientRect(zLevel, tooltipX + tooltipTextWidth + 3, tooltipY - 3,
				tooltipX + tooltipTextWidth + 4, tooltipY + tooltipHeight + 3, backgroundColor,
				backgroundColor);
		drawGradientRect(zLevel, tooltipX - 3, tooltipY - 3 + 1, tooltipX - 3 + 1,
				tooltipY + tooltipHeight + 3 - 1, borderColorStart, defaultBorderColorEnd);
		drawGradientRect(zLevel, tooltipX + tooltipTextWidth + 2, tooltipY - 3 + 1,
				tooltipX + tooltipTextWidth + 3, tooltipY + tooltipHeight + 3 - 1, borderColorStart,
				defaultBorderColorEnd);
		drawGradientRect(zLevel, tooltipX - 3, tooltipY - 3, tooltipX + tooltipTextWidth + 3,
				tooltipY - 3 + 1, borderColorStart, borderColorStart);
		drawGradientRect(zLevel, tooltipX - 3, tooltipY + tooltipHeight + 2,
				tooltipX + tooltipTextWidth + 3, tooltipY + tooltipHeight + 3, defaultBorderColorEnd,
				defaultBorderColorEnd);

		GlStateManager.enableLighting();
		GlStateManager.enableDepth();
		RenderHelper.enableStandardItemLighting();
		GlStateManager.enableRescaleNormal();
	}

	private static void drawGradientRect(int zLevel, int left, int top, int right, int bottom,
			int startColor, int endColor) {
		float startAlpha = (float) (startColor >> 24 & 255) / 255.0F;
		float startRed = (float) (startColor >> 16 & 255) / 255.0F;
		float startGreen = (float) (startColor >> 8 & 255) / 255.0F;
		float startBlue = (float) (startColor & 255) / 255.0F;
		float endAlpha = (float) (endColor >> 24 & 255) / 255.0F;
		float endRed = (float) (endColor >> 16 & 255) / 255.0F;
		float endGreen = (float) (endColor >> 8 & 255) / 255.0F;
		float endBlue = (float) (endColor & 255) / 255.0F;

		GlStateManager.disableTexture2D();
		GlStateManager.enableBlend();
		GlStateManager.disableAlpha();
		GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA,
				GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE,
				GlStateManager.DestFactor.ZERO);
		GlStateManager.shadeModel(7425);

		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder buffer = tessellator.getBuffer();
		buffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
		buffer.pos(right, top, zLevel).color(startRed, startGreen, startBlue, startAlpha).endVertex();
		buffer.pos(left, top, zLevel).color(startRed, startGreen, startBlue, startAlpha).endVertex();
		buffer.pos(left, bottom, zLevel).color(endRed, endGreen, endBlue, endAlpha).endVertex();
		buffer.pos(right, bottom, zLevel).color(endRed, endGreen, endBlue, endAlpha).endVertex();
		tessellator.draw();

		GlStateManager.shadeModel(7424);
		GlStateManager.disableBlend();
		GlStateManager.enableAlpha();
		GlStateManager.enableTexture2D();
	}

}
