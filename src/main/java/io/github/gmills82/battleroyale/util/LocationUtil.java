package io.github.gmills82.battleroyale.util;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Grant Mills
 * @since 5/20/16
 */
public class LocationUtil {
	public static Location getRandomSurfaceLocationInsideWorldBorder(World world) {
		Location spawn = world.getSpawnLocation();

		// This get a Random with a MaxRange
		double maxDistance = world.getWorldBorder().getSize() / 2;

		//Buffered upper bound
		double xUpperBound = (spawn.getX() + maxDistance) - 2;
		double xLowerBound = (spawn.getX() - maxDistance) - 2;
		double zUpperBound = (spawn.getZ() + maxDistance) - 2;
		double zLowerBound = (spawn.getZ() - maxDistance) - 2;

		//Get random location on map
		Double currentRandoX = ThreadLocalRandom.current().nextDouble(xLowerBound, xUpperBound + 1);
		Double currentRandoZ = ThreadLocalRandom.current().nextDouble(zLowerBound, zUpperBound + 1);

		// New Location in the right World you want
		Location randomlocation = new Location(world, 0, 0, 0);
		randomlocation.setX(currentRandoX);
		randomlocation.setZ(currentRandoZ);

		// Get the Highest Block of the Location for Save Spawn.
		randomlocation.setY(world.getHighestBlockAt(randomlocation.getBlockX(), randomlocation.getBlockZ()).getY());

		return randomlocation;
	}

	public static Location findCenterTopBlockOfChunk(Chunk chunk) {
		Location chunkCenter = new Location(chunk.getWorld(), 0, 0, 0);

		chunkCenter.setX(chunk.getX() - 8);
		chunkCenter.setZ(chunk.getZ() + 9);
		chunkCenter.setY(chunkCenter.getWorld().getHighestBlockAt(chunkCenter.getBlockX(), chunkCenter.getBlockZ()).getY());

		return chunkCenter;
	}
}
