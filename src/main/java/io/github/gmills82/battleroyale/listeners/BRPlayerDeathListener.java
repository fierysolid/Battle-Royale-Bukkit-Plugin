package io.github.gmills82.battleroyale.listeners;

import io.github.gmills82.battleroyale.BattleRoyalePlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.List;

/**
 * @author Grant Mills
 * @since 5/20/16
 */
public class BRPlayerDeathListener implements Listener {

	private final BattleRoyalePlugin plugin;

	public BRPlayerDeathListener(BattleRoyalePlugin plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
		Player playerLoggingOn = event.getEntity();

		//Death = Removal from game
		for(Player player : this.plugin.getCurrentBattlePlayers()) {
			if(player.getName().equalsIgnoreCase(playerLoggingOn.getName())) {

				//Remove player and reset list
				List<Player> newPlayerList = this.plugin.getCurrentBattlePlayers();
				newPlayerList.remove(player);
				this.plugin.setCurrentBattlePlayers(newPlayerList);
			}
		}
	}
}
