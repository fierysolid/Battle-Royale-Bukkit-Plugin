package io.github.gmills82.battleroyale.listeners;

import io.github.gmills82.battleroyale.BattleRoyaleGameState;
import io.github.gmills82.battleroyale.commands.BeginBattleRoyaleService;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Grant Mills
 * @since 5/20/16
 */
public final class BRPlayerJoinListener implements Listener {
	private BattleRoyaleGameState gameState;

	public BRPlayerJoinListener(BattleRoyaleGameState gameState) {
		this.gameState = gameState;
	}

	@EventHandler
	public void onLogin(PlayerJoinEvent event) {
		Player playerLoggingOn = event.getPlayer();

		for(Player player : gameState.getBattlePlayers()) {
			if(player.getName().equalsIgnoreCase(playerLoggingOn.getName())) {
				Set<Player> playerList = new HashSet<Player>();
				playerList.add(playerLoggingOn);
				Bukkit.getLogger().info("Resetting " + playerLoggingOn.getName() + " scoreboard");

				//Setup re-logging player's scoreboard again
				BeginBattleRoyaleService.setupScoreboard(playerList);
			}
		}
	}
}
