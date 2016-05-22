package io.github.gmills82.battleroyale.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static io.github.gmills82.battleroyale.constants.Constants.COMMAND_BEGIN_BATTLE_ROYAL;

/**
 * @author Grant Mills
 * @since 5/20/16
 */
public class BeginCommandExecutor implements CommandExecutor {

	private BattleRoyaleCommandService commandService;

	public BeginCommandExecutor(BattleRoyaleCommandService commandService) {
		this.commandService = commandService; // Store the plugin in situations where you need it.
	}

	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
		//Check if issued by a player not console
		if (commandSender instanceof Player) {

			//Check which command was executed
			if (command.getName().equalsIgnoreCase(COMMAND_BEGIN_BATTLE_ROYAL)) {
				if(args.length >= 2) {

					Set<Player> battlePlayers = processPlayerArgs(args);

					if(battlePlayers != Collections.EMPTY_LIST) {
						commandService.beginBRComand(commandSender, args[0], battlePlayers);
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

	private Set<Player> processPlayerArgs(String[] args) {
		Set<Player> battlePlayers = new HashSet<Player>();
		Collection<? extends Player> onlinePlayers = Bukkit.getOnlinePlayers();

		for(int i = 1; i < args.length; i++) {
			for(Player player : onlinePlayers) {
				if (args[i].equalsIgnoreCase(player.getName())) {
					battlePlayers.add(player);
				}
			}
		}

		return battlePlayers;
	}




}
