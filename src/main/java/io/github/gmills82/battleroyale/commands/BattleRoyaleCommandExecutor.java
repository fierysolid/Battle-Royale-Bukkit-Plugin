package io.github.gmills82.battleroyale.commands;

import io.github.gmills82.battleroyale.BattleRoyalePlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static io.github.gmills82.battleroyale.constants.Constants.COMMAND_BEGIN_BATTLE_ROYAL;

/**
 * @author Grant Mills
 * @since 5/20/16
 */
public class BattleRoyaleCommandExecutor implements CommandExecutor {

	private static BeginBattleRoyaleService beginBattleRoyaleService = null;
	private final BattleRoyalePlugin plugin;

	public BattleRoyaleCommandExecutor(BattleRoyalePlugin plugin) {
		this.plugin = plugin; // Store the plugin in situations where you need it.
	}

	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
		//Check if issued by a player not console
		if (commandSender instanceof Player) {

			//Check which command was executed
			if (command.getName().equalsIgnoreCase(COMMAND_BEGIN_BATTLE_ROYAL)) {
				if(args.length > 0) {

					List<Player> battlePlayers = processPlayerArgs(args);

					if(battlePlayers != Collections.EMPTY_LIST) {
						if (null == beginBattleRoyaleService) {
							//Playing pass the plugin
							beginBattleRoyaleService = new BeginBattleRoyaleService(this.plugin);
						}
						beginBattleRoyaleService.beginBRComand(battlePlayers, commandSender);

						return true;
					}else {
						commandSender.sendMessage("No online players found.");
					}
				}else {
					commandSender.sendMessage("You must enter the names of all players.");
				}
			}
		} else {
			commandSender.sendMessage("You must be a player to issue this command.");
		}

		return false;
	}

	private List<Player> processPlayerArgs(String[] args) {
		List<Player> battlePlayers = new ArrayList<Player>();
		Collection<? extends Player> onlinePlayers = Bukkit.getOnlinePlayers();

		for(int i = 0; i < args.length; i++) {
			for(Player player : onlinePlayers) {
				if (args[i].equalsIgnoreCase(player.getName())) {
					battlePlayers.add(player);
				}
			}
		}

		//Set players on plugin
		this.plugin.setCurrentBattlePlayers(battlePlayers);

		return battlePlayers;
	}




}
