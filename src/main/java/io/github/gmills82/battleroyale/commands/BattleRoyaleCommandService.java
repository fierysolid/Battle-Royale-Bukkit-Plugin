package io.github.gmills82.battleroyale.commands;

import io.github.gmills82.battleroyale.BattleRoyaleGameState;
import io.github.gmills82.battleroyale.BattleRoyalePlugin;
import io.github.gmills82.battleroyale.gifts.GiftsService;
import io.github.gmills82.battleroyale.gifts.PlayerGift;
import io.github.gmills82.battleroyale.runnables.DestructSequenceRunnable;
import io.github.gmills82.battleroyale.util.LocationUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static io.github.gmills82.battleroyale.util.TicksUtil.convertMinsToTicks;

/**
 * @author Grant Mills
 * @since 5/20/16
 */
public class BattleRoyaleCommandService {
	//Mins
	private static final int PERIOD_OF_CHUNK_DESTRUCT_SEQUENCE = 7;
	private static final int DELAY_OF_CHUNK_DESTRUCT_SEQUENCE = 0;
	private int initialWorldBorderSize;
	private int initialWorldBorderDelay;
	private final BattleRoyalePlugin plugin;
	private BattleRoyaleGameState gameState;

	public BattleRoyaleCommandService(BattleRoyalePlugin plugin, BattleRoyaleGameState gameState) {
		this.initialWorldBorderSize = 250;
		this.initialWorldBorderDelay = 0;
		this.plugin = plugin;
		this.gameState = gameState;
	}

	public BattleRoyaleCommandService(BattleRoyalePlugin plugin, BattleRoyaleGameState gameState, int startingWorldBorderSize, int initialWorldBorderDelay) {
		this.initialWorldBorderSize = startingWorldBorderSize;
		this.initialWorldBorderDelay = initialWorldBorderDelay;
		this.plugin = plugin;
		this.gameState = gameState;
	}

	//Command - COMMAND_BEGIN_BATTLE_ROYAL command
	public void beginBRComand(CommandSender commandSender, String battleName, Set<Player> battlePlayers) {
		Player player = (Player) commandSender;
		World world = player.getWorld();
		GiftsService giftsService = GiftsService.getInstance();

		//Set players on gameState
		this.gameState.setBattlePlayers(battlePlayers);

		//Set battle name
		this.gameState.setBattleName(battleName);

		//Setup world border
		this.setupWorldBorder(world, this.gameState.getCurrentBattlePlayersOnline());

		this.spreadPlayers(world, this.gameState.getCurrentBattlePlayersOnline());

		//Set pvp on
		world.setPVP(true);

		//Set each player to game mode survival
		for(Player battlePlayer: this.gameState.getBattlePlayers()) {
			battlePlayer.setGameMode(GameMode.SURVIVAL);

			//Distribute gifts
			PlayerGift gift = giftsService.getRandomPlayerGift();
			gift.giveGift(battlePlayer);
		}

		// Spawn scheduler process to remove sections of the map twice a day
		// 1 Day = 24000
		BukkitTask destructSequenceRunnable = new DestructSequenceRunnable(this.plugin, world, this.gameState).
			runTaskTimer(this.plugin, convertMinsToTicks(DELAY_OF_CHUNK_DESTRUCT_SEQUENCE), convertMinsToTicks(PERIOD_OF_CHUNK_DESTRUCT_SEQUENCE));

		//Save off runnable so pause and resume commands have access to it
		gameState.setDestructSequenceRunnable(destructSequenceRunnable);
	}

	//Command - COMMAND_PAUSE_BATTLE_ROYAL
	public void pauseBRCommand(CommandSender commandSender) {
		//Cancel all runnables
		this.gameState.getWarnPlayersRunnable().cancel();
		this.gameState.getDestructSequenceRunnable().cancel();

		//Disable PVP
		Player player = (Player) commandSender;
		player.getWorld().setPVP(false);

		//Set each player to game mode adventure
		for(Player battlePlayer: this.gameState.getBattlePlayers()) {
			battlePlayer.setGameMode(GameMode.ADVENTURE);
		}

		//Message server
		Bukkit.getServer().broadcastMessage(this.gameState.getBattleName() + " paused. " + ChatColor.GREEN + " Ceasefire!");
	}

	//Command - COMMAND_RESUME_BATTLE_ROYAL
	public void resumeBRCommand(CommandSender commandSender) {
		//Enable PVP
		Player player = (Player) commandSender;
		player.getWorld().setPVP(true);

		//Set each player to game mode survival
		for(Player battlePlayer: this.gameState.getBattlePlayers()) {
			battlePlayer.setGameMode(GameMode.SURVIVAL);
		}

		// Spawn scheduler process to remove sections of the map twice a day
		// 1 Day = 24000
		BukkitTask destructSequenceRunnable = new DestructSequenceRunnable(this.plugin, player.getWorld(), this.gameState).
			runTaskTimer(this.plugin, convertMinsToTicks(DELAY_OF_CHUNK_DESTRUCT_SEQUENCE), convertMinsToTicks(PERIOD_OF_CHUNK_DESTRUCT_SEQUENCE));

		//Save off runnable so pause and resume commands have access to it
		this.gameState.setDestructSequenceRunnable(destructSequenceRunnable);

		//Message server
		Bukkit.getServer().broadcastMessage(ChatColor.RED + "The fight resumes!");
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
