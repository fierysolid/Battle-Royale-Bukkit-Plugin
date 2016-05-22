package io.github.gmills82.battleroyale.runnables;

import io.github.gmills82.battleroyale.BattleRoyalePlugin;
import io.github.gmills82.battleroyale.util.LocationUtil;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

/**
 * @author Grant Mills
 * @since 5/20/16
 */
public class DestroyChunksRunnable extends BukkitRunnable {

	private final BattleRoyalePlugin plugin;
	private Chunk chunkToDestroy;
	private BukkitTask playerWarningRunnable;

	public DestroyChunksRunnable(BattleRoyalePlugin plugin, Chunk chunkToDestroy, BukkitTask playerWarningRunnable) {
		this.plugin = plugin;
		this.chunkToDestroy = chunkToDestroy;
		this.playerWarningRunnable = playerWarningRunnable;
	}

	@Override
	public void run() {
		playerWarningRunnable.cancel();

		//Fire off explosions down the center of the chunk
		Location chunkCenter = LocationUtil.findCenterTopBlockOfChunk(chunkToDestroy);
		int i = 0;
		while(i <= 7){
			chunkToDestroy.getWorld().createExplosion(chunkCenter, 16L, true);
			chunkCenter.setY((chunkCenter.getY() * 4) / 5);
			i++;
		}

		//Actually destroy the chunk by filling it with air
		//Get each block in a chunk and change to air
		int X = chunkToDestroy.getX() * 16;
		int Z = chunkToDestroy.getZ() * 16;
		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				for (int y = 0; y < 128; y++) {
					chunkToDestroy.getBlock(X+x, y, Z+z).setType(Material.AIR);
				}
			}
		}

		//Announce destruction
		this.plugin.getServer().broadcastMessage("Chunk destroyed at x: " + chunkCenter.getBlockX() + " and z: " + chunkCenter.getBlockZ());
	}

}
