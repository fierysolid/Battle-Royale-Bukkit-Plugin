package io.github.gmills82.battleroyale.listeners;

import io.github.gmills82.battleroyale.BattleRoyalePlugin;
import io.github.gmills82.battleroyale.commands.BeginBattleRoyaleService;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Grant Mills
 * @since 5/20/16
 */
public final class BRPlayerJoinListener implements Listener {
	private final BattleRoyalePlugin plugin;

	public BRPlayerJoinListener(BattleRoyalePlugin plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onLogin(PlayerJoinEvent event) {
		Player playerLoggingOn = event.getPlayer();

		for(Player player : this.plugin.getCurrentBattlePlayers()) {
			if(player.getName().equalsIgnoreCase(playerLoggingOn.getName())) {
				List<Player> playerList = new ArrayList<Player>();
				playerList.add(playerLoggingOn);
				this.plugin.getLogger().info("Resetting " + playerLoggingOn.getName() + " scoreboard");

				//Setup re-logging player's scoreboard again
				BeginBattleRoyaleService.setupScoreboard(playerList);
			}
		}
	}
}
