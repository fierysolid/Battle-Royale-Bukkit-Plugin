package io.github.gmills82.battleroyale;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Grant Mills
 * @since 5/20/16
 */
public class BattleRoyalePlugin extends JavaPlugin {

	@Override
	public void onDisable() {

	}

	@Override
	public void onEnable() {
		//Example of logger usage
		getLogger().info("onEnable has been invoked!");
	}
}
