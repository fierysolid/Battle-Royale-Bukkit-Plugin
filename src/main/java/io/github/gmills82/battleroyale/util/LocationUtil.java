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

		// This get a Random with a MaxRange
		double maxDistance = world.getWorldBorder().getSize() / 2;
		Location worldBorderCenter = world.getWorldBorder().getCenter();

		//Buffered upper bound
		Location maxLocation = new Location(world, worldBorderCenter.getX() + maxDistance, 0, worldBorderCenter.getZ() + maxDistance);
		Location minLocation = new Location(world, worldBorderCenter.getX() - maxDistance, 0, worldBorderCenter.getZ() - maxDistance);

		// Get random location on map
		Double currentRandoX = ThreadLocalRandom.current().nextDouble(minLocation.getX(), maxLocation.getX());
		Double currentRandoZ = ThreadLocalRandom.current().nextDouble(minLocation.getZ(), maxLocation.getZ());

		// New Location in the right World you want
		Location randomlocation = new Location(world, currentRandoX, 0, currentRandoZ);

		// Get the Highest Block of the Location for Save Spawn.
		randomlocation.setY(world.getHighestBlockAt(randomlocation.getBlockX(), randomlocation.getBlockZ()).getY());

		return randomlocation;
	}

	public static Location findCenterTopBlockOfChunk(Chunk chunk) {
		Location chunkCenter = new Location(chunk.getWorld(), chunk.getBlock(0,0,0).getX() + 8, 0, chunk.getBlock(0,0,0).getZ() + 8);

		chunkCenter.setY(chunkCenter.getWorld().getHighestBlockAt(chunkCenter.getBlockX(), chunkCenter.getBlockZ()).getY());

		return chunkCenter;
	}
}
