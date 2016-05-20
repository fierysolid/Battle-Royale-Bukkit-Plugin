package io.github.gmills82.battleroyale;

import io.github.gmills82.battleroyale.commands.BattleRoyaleCommandExecutor;
import io.github.gmills82.battleroyale.listeners.PlayerLogonListener;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import static io.github.gmills82.battleroyale.constants.Constants.COMMAND_BEGIN_BATTLE_ROYAL;

/**
 * @author Grant Mills
 * @since 5/20/16
 */
public class BattleRoyalePlugin extends JavaPlugin implements Listener {

	@Override
	public void onDisable() {

	}

	@Override
	public void onEnable() {
		//Example of logger usage
		getLogger().info("onEnable has been invoked!");

		getServer().getPluginManager().registerEvents(new PlayerLogonListener(this), this);

		this.getCommand(COMMAND_BEGIN_BATTLE_ROYAL).setExecutor(new BattleRoyaleCommandExecutor(this));
	}


}
