package io.github.gmills82.battleroyale.runnables;

import io.github.gmills82.battleroyale.BattleRoyaleGameState;
import io.github.gmills82.battleroyale.BattleRoyalePlugin;
import io.github.gmills82.battleroyale.util.LocationUtil;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashSet;
import java.util.Set;

import static io.github.gmills82.battleroyale.util.TicksUtil.convertMinsToTicks;

/**
 * @author Grant Mills
 * @since 5/21/16
 */
public class DestructSequenceRunnable extends BukkitRunnable {

	private static final double PERIOD_OF_PLAYER_WARNING = 0.5;
	private final BattleRoyalePlugin plugin;
	private World worldToDestroy;
	private static Set<Chunk> destroyedChunkSet = new HashSet<Chunk>();
	private static final Integer DELAY_BEFORE_CHUNK_DESTRUCTION = 3;
	private BattleRoyaleGameState gameState;

	public DestructSequenceRunnable(BattleRoyalePlugin plugin, World world, BattleRoyaleGameState gameState) {
		this.plugin = plugin;
		this.worldToDestroy = world;
		this.gameState = gameState;
	}

	@Override
	public void run() {
		// Determine chunk to destroy
		Chunk chunkToDestroy = null;
		while(null == chunkToDestroy) {
			chunkToDestroy = determineChunkToDestroy(worldToDestroy);
		}

		// Fire off explosions down the center of the chunk
		Location chunkCenter = LocationUtil.findCenterTopBlockOfChunk(chunkToDestroy);

		// Announce to all players the x coords and z coords of the chunk
		// to be destroyed and the amount of real life time they have to GTFO
		Bukkit.getServer().broadcastMessage("The quadrant centered around x: " +
			chunkCenter.getX() + " and z: " + chunkCenter.getZ() + " will be destroyed in " +
			DELAY_BEFORE_CHUNK_DESTRUCTION + " minutes");

		// Schedule Player warnings
		// 100 ticks = 5 secs
		BukkitTask playerWarningRunnable = new WarnPlayersOfDestructionRunnable(this.gameState, chunkToDestroy).runTaskTimer(this.plugin, 0, convertMinsToTicks(PERIOD_OF_PLAYER_WARNING));

		//Save to game state to give pause /resume access
		gameState.setWarnPlayersRunnable(playerWarningRunnable);

		// Schedule actual destruction
		BukkitTask destoryChunksRunnable = new DestroyChunksRunnable(chunkToDestroy, playerWarningRunnable).runTaskLater(this.plugin, convertMinsToTicks(DELAY_BEFORE_CHUNK_DESTRUCTION));
	}

	private Chunk determineChunkToDestroy(World world) {
		// Get random location inside border
		Location randomLocation = LocationUtil.getRandomSurfaceLocationInsideWorldBorder(world);
		Chunk spawnChunk = world.getSpawnLocation().getChunk();

		Chunk chunkToDestroy = world.getChunkAt(randomLocation);

		if(!destroyedChunkSet.contains(chunkToDestroy) && chunkToDestroy != spawnChunk) {
			destroyedChunkSet.add(chunkToDestroy);
			return chunkToDestroy;
		}
		return null;
	}
}
