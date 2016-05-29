package io.github.gmills82.battleroyale.runnables;

import io.github.gmills82.battleroyale.BattleRoyaleGameState;
import io.github.gmills82.battleroyale.BattleRoyalePlugin;
import io.github.gmills82.battleroyale.catastrophy.Catastrophy;
import io.github.gmills82.battleroyale.catastrophy.CatastrophyService;
import io.github.gmills82.battleroyale.util.LocationUtil;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import static io.github.gmills82.battleroyale.util.TicksUtil.convertMinsToTicks;

/**
 * @author Grant Mills
 * @since 5/20/16
 */
public class DestroyChunksRunnable extends BukkitRunnable {

	private static final long EXPLOSION_POWER = 30L;
	private Chunk chunkToDestroy;
	private BukkitTask playerWarningRunnable;
	private BattleRoyaleGameState gameState;
	private BattleRoyalePlugin plugin;

	public DestroyChunksRunnable(BattleRoyalePlugin plugin, Chunk chunkToDestroy, BukkitTask playerWarningRunnable, BattleRoyaleGameState gameState) {
		this.chunkToDestroy = chunkToDestroy;
		this.playerWarningRunnable = playerWarningRunnable;
		this.gameState = gameState;
		this.plugin = plugin;
	}

	@Override
	public void run() {
		CatastrophyService catastrophyService = CatastrophyService.getInstance();
		playerWarningRunnable.cancel();

		//Fire off explosions down the center of the chunk
		Location chunkCenter = LocationUtil.findCenterTopBlockOfChunk(this.chunkToDestroy);
		int i = 5;
		while(i > 0){
			this.chunkToDestroy.getWorld().createExplosion(chunkCenter, EXPLOSION_POWER, true);
			chunkCenter.setY((chunkCenter.getY() * i) / 6);
			i--;
		}

		//Actually destroy the chunk by filling it with air
		//Get each block in a chunk and change to air
		int X = this.chunkToDestroy.getX() * 16;
		int Z = this.chunkToDestroy.getZ() * 16;
		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				for (int y = 0; y < 128; y++) {
					this.chunkToDestroy.getBlock(X+x, y, Z+z).setType(Material.AIR);
				}
			}
		}

		//Update gameState
		this.gameState.getDestroyedChunkSet().add(chunkToDestroy);
		//Ever closer to impending doom
		this.gameState.lowerCatastrophyCounter();

		// If countdown is at zero spawn catastrophy runnable
		if(this.gameState.getCountdownToCatastrophy() == 0) {
			Catastrophy catastrophy = catastrophyService.getRandomCatastophy();

			//Warn players
			catastrophy.warnPlayers();

			new CatastrophyRunnable(Bukkit.getServer().getWorlds().get(0), catastrophy).
				runTaskLater(this.plugin, convertMinsToTicks(catastrophy.getDelay()));
		}

		//Announce destruction
		Bukkit.getServer().broadcastMessage("Quadrant destroyed at x: " + chunkCenter.getX() + " and z: " + chunkCenter.getZ());
	}

}
