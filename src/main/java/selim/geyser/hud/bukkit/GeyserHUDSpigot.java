package selim.geyser.hud.bukkit;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import selim.geyser.core.bukkit.BukkitByteBufUtils;
import selim.geyser.core.shared.EnumComponent;
import selim.geyser.core.shared.IGeyserCorePlugin;
import selim.geyser.hud.shared.GeyserHUDInfo;
import selim.geyser.hud.shared.IGeyserHUD;
import selim.geyser.hud.shared.RectangleRender;
import selim.geyser.hud.shared.StringRender;

public class GeyserHUDSpigot extends JavaPlugin implements Listener, IGeyserCorePlugin {

	protected static Logger LOGGER;
	protected static GeyserHUDSpigot INSTANCE;

	private static final List<Player> HUD_PLAYERS = new LinkedList<>();
	private static final Map<Player, IGeyserHUD<ItemStack>> HUD_CACHE = new ConcurrentHashMap<>();

	@SuppressWarnings("deprecation")
	@Override
	public void onEnable() {
		LOGGER = this.getLogger();
		INSTANCE = this;
		PluginManager manager = this.getServer().getPluginManager();
		Bukkit.getMessenger().registerOutgoingPluginChannel(this, GeyserHUDInfo.CHANNEL);
		manager.registerEvents(this, this);
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {

			@Override
			public void run() {
				for (Player p : HUD_PLAYERS) {
					RenderHUDEvent event = new RenderHUDEvent(p);
					Bukkit.getPluginManager().callEvent(event);
					IGeyserHUD<ItemStack> oldHUD = HUD_CACHE.get(p);
					if (oldHUD == null || !oldHUD.equals(event.getHUD())) {
						HUD_CACHE.put(p, event.getHUD());
						sendHUD(p, event.getHUD());
					}
				}
			}
		}, 0, 1);
	}

	private static void sendHUD(Player player, IGeyserHUD<ItemStack> hud) {
		ByteBuf buf = Unpooled.buffer();
		buf.writeByte(GeyserHUDInfo.PacketDiscrimators.SEND_HUD);
		buf.writeInt(hud.getStringRenders().size());
		for (StringRender sr : hud.getStringRenders()) {
			BukkitByteBufUtils.writeUTF8String(buf, sr.text);
			buf.writeInt(sr.x);
			buf.writeInt(sr.y);
			buf.writeInt(sr.color);
		}
		buf.writeInt(hud.getRectangleRenders().size());
		for (RectangleRender rr : hud.getRectangleRenders()) {
			buf.writeInt(rr.x);
			buf.writeInt(rr.y);
			buf.writeInt(rr.width);
			buf.writeInt(rr.height);
			buf.writeInt(rr.color);
		}
		buf.writeInt(hud.getItemStacks().size());
		for (ItemStack s : hud.getItemStacks())
			BukkitByteBufUtils.writeItemStack(buf, s);
		player.sendPluginMessage(INSTANCE, GeyserHUDInfo.CHANNEL, buf.array());
	}

	@Override
	public EnumComponent[] providedComponents() {
		return new EnumComponent[] { EnumComponent.HUD };
	}

	private int getPing(Player player) {
		try {
			Object entityPlayer = player.getClass().getMethod("getHandle").invoke(player);
			return (int) entityPlayer.getClass().getField("ping").get(entityPlayer);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | NoSuchFieldException e) {
			this.getLogger().log(Level.INFO, "Unable to get ping for " + player.getDisplayName()
					+ ", encountered a " + e.getClass().getName());
			e.printStackTrace();
			return -1;
		}
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		int ping = getPing(player);
		if (ping <= 0)
			ping = 40;
		else
			ping = ping / 25;
		this.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {

			@Override
			public void run() {
				if (player.getListeningPluginChannels().contains(GeyserHUDInfo.CHANNEL))
					HUD_PLAYERS.add(player);
			}
		}, ping);
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		HUD_PLAYERS.remove(event.getPlayer());
		HUD_CACHE.remove(event.getPlayer());
	}

}
