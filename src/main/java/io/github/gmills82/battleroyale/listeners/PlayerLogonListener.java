package io.github.gmills82.battleroyale.listeners;

import io.github.gmills82.battleroyale.BattleRoyalePlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import java.util.logging.Level;

/**
 * @author Grant Mills
 * @since 5/20/16
 */
public class PlayerLogonListener implements Listener {
	private final BattleRoyalePlugin plugin;

	public PlayerLogonListener(BattleRoyalePlugin plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onLogin(PlayerLoginEvent event) {
		//example of logging from the plugin level in a listener
		plugin.getLogger().log(Level.INFO, "Player " + event.getPlayer().getName() + " is logging in!");
	}
}
