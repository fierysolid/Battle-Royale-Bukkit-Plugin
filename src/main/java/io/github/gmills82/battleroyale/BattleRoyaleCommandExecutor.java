package io.github.gmills82.battleroyale;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * @author Grant Mills
 * @since 5/20/16
 */
public class BattleRoyaleCommandExecutor implements CommandExecutor {

	private final BattleRoyalePlugin plugin;

	public BattleRoyaleCommandExecutor(BattleRoyalePlugin plugin) {
		this.plugin = plugin; // Store the plugin in situations where you need it.
	}

	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
		return false;
	}


}
