package io.github.gmills82.battleroyale;

import io.github.gmills82.battleroyale.commands.BattleRoyaleCommandExecutor;
import io.github.gmills82.battleroyale.listeners.BRPlayerDeathListener;
import io.github.gmills82.battleroyale.listeners.BRPlayerJoinListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import static io.github.gmills82.battleroyale.constants.Constants.COMMAND_BEGIN_BATTLE_ROYAL;

/**
 * @author Grant Mills
 * @since 5/20/16
 */
public class BattleRoyalePlugin extends JavaPlugin {

	private BattleRoyaleGameState gameState;

	@Override
	public void onDisable() {

	}

	@Override
	public void onEnable() {
		//Create gameState
		this.gameState = new BattleRoyaleGameState();

		//Register Event Listeners
		PluginManager pluginManager = getServer().getPluginManager();
		pluginManager.registerEvents(new BRPlayerJoinListener(gameState), this);
		pluginManager.registerEvents(new BRPlayerDeathListener(gameState), this);

		//Register Command Executors
		this.getCommand(COMMAND_BEGIN_BATTLE_ROYAL).setExecutor(new BattleRoyaleCommandExecutor(this, gameState));
	}

	public BattleRoyaleGameState getGameState() {
		return gameState;
	}

}
