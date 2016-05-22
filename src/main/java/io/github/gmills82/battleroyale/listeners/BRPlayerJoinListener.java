package io.github.gmills82.battleroyale.listeners;

import io.github.gmills82.battleroyale.BattleRoyaleGameState;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

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

		//Default everyone to adventure
		playerLoggingOn.setGameMode(GameMode.ADVENTURE);

		//Setup player's scoreboard
		//Get scoreboard manager
		ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();

		//Setup health scoreboard
		Scoreboard healthScoreboard = scoreboardManager.getNewScoreboard();
		Objective healthObjective = healthScoreboard.registerNewObjective("Health", "health");
		healthObjective.setDisplaySlot(DisplaySlot.SIDEBAR);
		healthObjective.setDisplayName("The Living: ");

		playerLoggingOn.setScoreboard(healthScoreboard);

	}
}
