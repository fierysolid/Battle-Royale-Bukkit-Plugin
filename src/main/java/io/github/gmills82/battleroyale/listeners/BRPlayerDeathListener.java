package io.github.gmills82.battleroyale.listeners;

import io.github.gmills82.battleroyale.BattleRoyaleGameState;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

/**
 * @author Grant Mills
 * @since 5/20/16
 */
public class BRPlayerDeathListener implements Listener {

	private BattleRoyaleGameState gameState;

	public BRPlayerDeathListener(BattleRoyaleGameState gameState) {
		this.gameState = gameState;
	}

	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
		Player playerLoggingOn = event.getEntity();

		//Death = Removal from game
		for(Player player : gameState.getBattlePlayers()) {
			if(player.getName().equalsIgnoreCase(playerLoggingOn.getName())) {

				if(null != player.getKiller()) {
					Bukkit.getServer().broadcastMessage(player.getName() + " was killed by " + player.getKiller() + "!");
				}
				Bukkit.getServer().broadcastMessage("Fight harder!");

				//Remove player and reset list
				gameState.getBattlePlayers().remove(player);
			}
		}
	}
}
