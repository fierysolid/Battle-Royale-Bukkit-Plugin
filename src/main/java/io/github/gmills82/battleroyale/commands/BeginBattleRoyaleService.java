package io.github.gmills82.battleroyale.commands;

import io.github.gmills82.battleroyale.BattleRoyalePlugin;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.List;

/**
 * @author Grant Mills
 * @since 5/20/16
 */
public class BeginBattleRoyaleService {
	private int initialWorldBorderSize;
	private int initialWorldBorderDelay;
	private final BattleRoyalePlugin plugin;

	public BeginBattleRoyaleService(BattleRoyalePlugin plugin) {
		this.initialWorldBorderSize = 250;
		this.initialWorldBorderDelay = 20;
		this.plugin = plugin;
	}

	public BeginBattleRoyaleService(BattleRoyalePlugin plugin, int startingWorldBorderSize, int initialWorldBorderDelay) {
		this.initialWorldBorderSize = startingWorldBorderSize;
		this.initialWorldBorderDelay = initialWorldBorderDelay;
		this.plugin = plugin;
	}

	//Command method for COMMAND_BEGIN_BATTLE_ROYAL command
	public void beginBRComand(List<Player> battlePlayers, CommandSender commandSender) {

			Player player = (Player) commandSender;

			World world = player.getWorld();

			//Setup world border
			this.setupWorldBorder(world, battlePlayers);

			//Setup scoreboard and display
			setupScoreboard(battlePlayers);

			//TODO: Spawn scheduler process to remove sections of the map

	}

	public static void setupScoreboard(List<Player> battlePlayers) {
		//Get scoreboard manager
		ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();

		//Setup health scoreboard
		Scoreboard healthScoreboard = scoreboardManager.getNewScoreboard();
		Objective healthObjective = healthScoreboard.registerNewObjective("Health", "health");
		healthObjective.setDisplaySlot(DisplaySlot.SIDEBAR);
		healthObjective.setDisplayName("The Living: ");

		for(Player online: battlePlayers) {
			online.setScoreboard(healthScoreboard);
		}
	}

	private void setupWorldBorder(World world, List<Player> battlePlayers) {
		if(null != world) {

			WorldBorder worldborder = world.getWorldBorder();

			worldborder.setCenter(world.getSpawnLocation());
			worldborder.setSize(initialWorldBorderSize, initialWorldBorderDelay);

			String allPlayerNames = "";
			for (Player loopPlayer : battlePlayers) {
				allPlayerNames += loopPlayer.getName() + ", ";
			}
			allPlayerNames = allPlayerNames.substring(0, allPlayerNames.length() - 2);

			for (Player loopPlayer : battlePlayers) {
				loopPlayer.sendMessage("The fighting arena is centered on x: " + worldborder.getCenter().getX() + " z: " + worldborder.getCenter().getZ() + ".");
				loopPlayer.sendMessage("You're locked in!");
				loopPlayer.sendMessage("Fight!");
				loopPlayer.sendMessage("Fighters include: " + allPlayerNames);
			}

			plugin.getLogger().info("A Battle Royal has begun between the players of world " + world.getName());
		}else {
			throw new IllegalArgumentException("World was null when setup attempted.");
		}
	}
}
