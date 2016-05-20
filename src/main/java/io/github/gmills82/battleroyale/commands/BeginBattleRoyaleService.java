package io.github.gmills82.battleroyale.commands;

import io.github.gmills82.battleroyale.BattleRoyalePlugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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
		this.initialWorldBorderDelay = 5;
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

		this.spreadPlayers(world, battlePlayers);

		//TODO: Spawn scheduler process to remove sections of the map

	}

	private void spreadPlayers(World world, List<Player> battlePlayers) {
		Location spawn = world.getSpawnLocation();

		List<Location> placedPlayerLocations = new ArrayList<Location>();

		// This get a Random with a MaxRange
		Integer maxDistance = this.initialWorldBorderSize / 2;

		//Buffered upper bound
		double xUpperBound = (spawn.getX() + maxDistance) - 2;
		double xLowerBound = (spawn.getX() - maxDistance) - 2;
		double zUpperBound = (spawn.getZ() + maxDistance) - 2;
		double zLowerBound = (spawn.getZ() - maxDistance) - 2;

		for(Player player : battlePlayers) {

			Integer totalAttemptsToPlacePlayer = 0;
			Integer maximumAttemptsToPlacePlayer = 15;
			double minimumAcceptablePlayerSpread = 65;

			Boolean playerSet = false;

			//Try to place player
			while (!playerSet) {
				//Increment count of attempts
				totalAttemptsToPlacePlayer++;

				//Get random location on map
				Double currentRandoX = ThreadLocalRandom.current().nextDouble(xLowerBound, xUpperBound + 1);
				Double currentRandoZ = ThreadLocalRandom.current().nextDouble(zLowerBound, zUpperBound + 1);

				// New Location in the right World you want
				Location randomlocation = new Location(world, 0, 0, 0);
				randomlocation.setX(currentRandoX);
				randomlocation.setZ(currentRandoZ);

				// Get the Highest Block of the Location for Save Spawn.
				randomlocation.setY(world.getHighestBlockAt(randomlocation.getBlockX(), randomlocation.getBlockZ()).getY());

				//If list is empty location is acceptable by default
				if(placedPlayerLocations.equals(Collections.EMPTY_LIST)) {
					//If list is empty go ahead and add the player
					placedPlayerLocations.add(randomlocation);
					player.teleport(randomlocation);
					playerSet = true;

				} else {
					//Check closeness to other player locations
					//If we haven't taken too long finding a good location
					if(totalAttemptsToPlacePlayer <= maximumAttemptsToPlacePlayer) {
						Boolean locationAcceptableToOtherPlayers = true;

						for (Location location : placedPlayerLocations) {
							if (location.distance(randomlocation) < minimumAcceptablePlayerSpread) {
								locationAcceptableToOtherPlayers = false;
							}
						}

						//If good location
						if (locationAcceptableToOtherPlayers) {
							placedPlayerLocations.add(randomlocation);
							player.teleport(randomlocation);
							playerSet = true;
						}
					}else {
						//If we can't find a good one keep what we have
						this.plugin.getLogger().info("Player " + player.getName() + " was given a less than acceptable starting location.");
						placedPlayerLocations.add(randomlocation);
						player.teleport(randomlocation);
						playerSet = true;
					}
				}
			}
		}
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
