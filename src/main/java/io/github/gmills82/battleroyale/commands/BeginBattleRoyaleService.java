package io.github.gmills82.battleroyale.commands;

import io.github.gmills82.battleroyale.BattleRoyaleGameState;
import io.github.gmills82.battleroyale.BattleRoyalePlugin;
import io.github.gmills82.battleroyale.runnables.DestructSequenceRunnable;
import io.github.gmills82.battleroyale.util.LocationUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static io.github.gmills82.battleroyale.util.TicksUtil.convertMinsToTicks;

/**
 * @author Grant Mills
 * @since 5/20/16
 */
public class BeginBattleRoyaleService {
	//Mins
	private static final int PERIOD_OF_CHUNK_DESTRUCT_SEQUENCE = 7;
	private static final int DELAY_OF_CHUNK_DESTRUCT_SEQUENCE = 0;
	private int initialWorldBorderSize;
	private int initialWorldBorderDelay;
	private final BattleRoyalePlugin plugin;
	private BattleRoyaleGameState gameState;

	public BeginBattleRoyaleService(BattleRoyalePlugin plugin, BattleRoyaleGameState gameState) {
		this.initialWorldBorderSize = 250;
		this.initialWorldBorderDelay = 0;
		this.plugin = plugin;
		this.gameState = gameState;
	}

	public BeginBattleRoyaleService(BattleRoyalePlugin plugin, BattleRoyaleGameState gameState, int startingWorldBorderSize, int initialWorldBorderDelay) {
		this.initialWorldBorderSize = startingWorldBorderSize;
		this.initialWorldBorderDelay = initialWorldBorderDelay;
		this.plugin = plugin;
		this.gameState = gameState;
	}

	//Command method for COMMAND_BEGIN_BATTLE_ROYAL command
	public void beginBRComand(CommandSender commandSender) {

		Player player = (Player) commandSender;
		World world = player.getWorld();

		//Setup world border
		this.setupWorldBorder(world, gameState.getCurrentBattlePlayersOnline());

		//Setup scoreboard and display
		setupScoreboard(gameState.getCurrentBattlePlayersOnline());

		this.spreadPlayers(world, gameState.getCurrentBattlePlayersOnline());

		// Spawn scheduler process to remove sections of the map twice a day
		// 1 Day = 24000
		BukkitTask destructSequenceRunnable = new DestructSequenceRunnable(this.plugin, world, this.gameState).
			runTaskTimer(this.plugin, convertMinsToTicks(DELAY_OF_CHUNK_DESTRUCT_SEQUENCE), convertMinsToTicks(PERIOD_OF_CHUNK_DESTRUCT_SEQUENCE));

		//Save off runnable so pause and resume commands have access to it
		gameState.setDestructSequenceRunnable(destructSequenceRunnable);
	}

	private void spreadPlayers(World world, Set<Player> battlePlayers) {

		List<Location> placedPlayerLocations = new ArrayList<Location>();

		for(Player player : battlePlayers) {

			Integer totalAttemptsToPlacePlayer = 0;
			Integer maximumAttemptsToPlacePlayer = 15;
			double minimumAcceptablePlayerSpread = 65;

			Boolean playerSet = false;

			//Try to place player
			while (!playerSet) {
				//Increment count of attempts
				totalAttemptsToPlacePlayer++;

				Location randomLocation = LocationUtil.getRandomSurfaceLocationInsideWorldBorder(world);

				//If list is empty location is acceptable by default
				if(placedPlayerLocations.equals(Collections.EMPTY_LIST)) {
					//If list is empty go ahead and add the player
					placedPlayerLocations.add(randomLocation);
					player.teleport(randomLocation);
					playerSet = true;

				} else {
					//Check closeness to other player locations
					//If we haven't taken too long finding a good location
					if(totalAttemptsToPlacePlayer <= maximumAttemptsToPlacePlayer) {
						Boolean locationAcceptableToOtherPlayers = true;

						for (Location location : placedPlayerLocations) {
							if (location.distance(randomLocation) < minimumAcceptablePlayerSpread) {
								locationAcceptableToOtherPlayers = false;
							}
						}

						//If good location
						if (locationAcceptableToOtherPlayers) {
							placedPlayerLocations.add(randomLocation);
							player.teleport(randomLocation);
							playerSet = true;
						}
					}else {
						//If we can't find a good one keep what we have
						Bukkit.getLogger().info("Player " + player.getName() + " was given a less than acceptable starting location.");
						placedPlayerLocations.add(randomLocation);
						player.teleport(randomLocation);
						playerSet = true;
					}
				}
			}
		}
	}

	public static void setupScoreboard(Set<Player> battlePlayers) {
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

	private void setupWorldBorder(World world, Set<Player> battlePlayers) {
		if(null != world) {

			WorldBorder worldborder = world.getWorldBorder();

			worldborder.setCenter(world.getSpawnLocation());
			worldborder.setSize(initialWorldBorderSize, initialWorldBorderDelay);

			String allPlayerNames = "";
			for (Player loopPlayer : battlePlayers) {
				allPlayerNames += loopPlayer.getName() + ", ";
			}
			allPlayerNames = allPlayerNames.substring(0, allPlayerNames.length() - 2);

			Bukkit.getServer().broadcastMessage("The fighting arena is " + ChatColor.AQUA + initialWorldBorderSize + ChatColor.BLACK + " blocks square.");
			Bukkit.getServer().broadcastMessage("Fierce contestants include: " + ChatColor.GOLD + allPlayerNames);
			Bukkit.getServer().broadcastMessage("Life is a game. So fight for survival and see if you're worth it.");

			Bukkit.getLogger().info("A Battle Royal has begun between the players of world " + world.getName());
		}else {
			throw new IllegalArgumentException("World was null when setup attempted.");
		}
	}
}
