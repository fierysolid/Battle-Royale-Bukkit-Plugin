package io.github.gmills82.battleroyale;

import io.github.gmills82.battleroyale.commands.BattleRoyaleCommandExecutor;
import io.github.gmills82.battleroyale.listeners.BRPlayerDeathListener;
import io.github.gmills82.battleroyale.listeners.BRPlayerJoinListener;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

import static io.github.gmills82.battleroyale.constants.Constants.COMMAND_BEGIN_BATTLE_ROYAL;

/**
 * @author Grant Mills
 * @since 5/20/16
 */
public class BattleRoyalePlugin extends JavaPlugin {

	private List<Player> currentBattlePlayers = new ArrayList<Player>();

	@Override
	public void onDisable() {

	}

	@Override
	public void onEnable() {

		//Register Event Listeners
		PluginManager pluginManager = getServer().getPluginManager();
		pluginManager.registerEvents(new BRPlayerJoinListener(this), this);
		pluginManager.registerEvents(new BRPlayerDeathListener(this), this);

		//Register Command Executors
		this.getCommand(COMMAND_BEGIN_BATTLE_ROYAL).setExecutor(new BattleRoyaleCommandExecutor(this));
	}

	public List<Player> getCurrentBattlePlayers() {
		return currentBattlePlayers;
	}

	public List<Player> getCurrentBattlePlayersOnline() {
		List<Player> onlineBattlePlayers = new ArrayList<Player>();
		for(Player player : currentBattlePlayers) {
			if(player.isOnline()) {
				onlineBattlePlayers.add(player);
			}
		}
		return onlineBattlePlayers;
	}

	public void setCurrentBattlePlayers(List<Player> currentBattlePlayers) {
		this.currentBattlePlayers = currentBattlePlayers;
	}
}
