package selim.geyser.hud.forge;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import selim.geyser.core.shared.EnumComponent;
import selim.geyser.core.shared.GeyserCoreInfo;
import selim.geyser.hud.forge.packets.PacketClearHUD;
import selim.geyser.hud.forge.packets.PacketModifyPart;
import selim.geyser.hud.forge.packets.PacketNewPart;
import selim.geyser.hud.forge.packets.PacketRemovePart;
import selim.geyser.hud.forge.packets.PacketScreenSize;
import selim.geyser.hud.shared.CollectionHUDPart;
import selim.geyser.hud.shared.GeyserHUDInfo;
import selim.geyser.hud.shared.HUDPartRegistry;
import selim.geyser.hud.shared.IGeyserHUD;
import selim.geyser.hud.shared.IHUDPart;
import selim.geyser.hud.shared.LocalizedStringHUDPart;

@Mod.EventBusSubscriber
@Mod(modid = GeyserHUDInfo.ID, name = GeyserHUDInfo.NAME, version = GeyserHUDInfo.VERSION,
		dependencies = "required-after:" + GeyserCoreInfo.ID, clientSideOnly = true)
public class GeyserHUDForge {

	@Mod.Instance(value = GeyserHUDInfo.ID)
	public static GeyserHUDForge instance;
	public static final Logger LOGGER = LogManager.getLogger(GeyserHUDInfo.ID);
	public static SimpleNetworkWrapper network;

	public static IGeyserHUD HUD = new ForgeGeyserHUD();

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		network = NetworkRegistry.INSTANCE.newSimpleChannel(GeyserHUDInfo.CHANNEL);
		network.registerMessage(PacketNewPart.Handler.class, PacketNewPart.class,
				GeyserHUDInfo.PacketDiscrimators.SEND_PART, Side.CLIENT);
		network.registerMessage(PacketClearHUD.Handler.class, PacketClearHUD.class,
				GeyserHUDInfo.PacketDiscrimators.CLEAR_HUD, Side.CLIENT);
		network.registerMessage(PacketRemovePart.Handler.class, PacketRemovePart.class,
				GeyserHUDInfo.PacketDiscrimators.REMOVE_PART, Side.CLIENT);
		network.registerMessage(PacketModifyPart.Handler.class, PacketModifyPart.class,
				GeyserHUDInfo.PacketDiscrimators.MODIFY_PART, Side.CLIENT);
		network.registerMessage(PacketScreenSize.Handler.class, PacketScreenSize.class,
				GeyserHUDInfo.PacketDiscrimators.SCREEN_SIZE, Side.CLIENT);

		HUDPartRegistry.registerPart(StringHUDPartForge.class);
		HUDPartRegistry.registerPart(RectangleHUDPartForge.class);
		HUDPartRegistry.registerPart(ItemStackHUDPartForge.class);
		HUDPartRegistry.registerPart(LocalizedStringHUDPartForge.class);
		HUDPartRegistry.registerPart(CollectionHUDPart.class);

		// LOGGER.info("sending");
		FMLInterModComms.sendMessage(GeyserCoreInfo.ID, GeyserCoreInfo.IMC_SEND_KEY,
				EnumComponent.HUD.toString());
	}

	private static int screenWidth = -1;
	private static int screenHeight = -1;

	@SubscribeEvent
	public static void onHUDRender(RenderGameOverlayEvent.Post event) {
		if (event.getType() != ElementType.HOTBAR || HUD == null)
			return;
		if (screenWidth == -1 || screenHeight == -1) {
			screenWidth = Minecraft.getMinecraft().displayWidth;
			screenHeight = Minecraft.getMinecraft().displayHeight;
		}
		if (screenWidth != Minecraft.getMinecraft().displayWidth
				|| screenHeight != Minecraft.getMinecraft().displayHeight) {
			screenWidth = Minecraft.getMinecraft().displayWidth;
			screenHeight = Minecraft.getMinecraft().displayHeight;
			network.sendToServer(new PacketScreenSize());
		}
		GlStateManager.pushMatrix();
		for (IHUDPart part : HUD.getParts())
			if (!part.isHidden())
				part.render();
		GlStateManager.popMatrix();
	}

}
