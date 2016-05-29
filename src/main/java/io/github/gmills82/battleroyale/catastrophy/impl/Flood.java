package io.github.gmills82.battleroyale.catastrophy.impl;

import io.github.gmills82.battleroyale.catastrophy.Catastrophy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.github.gmills82.battleroyale.util.LocationUtil.findCenterTopBlockOfChunk;

/**
 * @author Grant Mills
 * @since 5/29/16
 */
public class Flood implements Catastrophy{
	private static final double DELAY = 3;

	@Override
	public double getDelay() {
		return DELAY;
	}

	@Override
	public void warnPlayers() {
		Bukkit.getServer().broadcastMessage(ChatColor.DARK_PURPLE + " The Lord brought the Flood waters upon the lands, and many perished in the deep.");
	}

	@Override
	public void initiateCatastrophy(World world) {
		//Find highest Y
		Location spawn = world.getSpawnLocation();
		double floodYLevel = spawn.getY() + 3;

		List<Chunk> allChunks = new ArrayList<Chunk>(Arrays.asList(world.getLoadedChunks()));
		for(Chunk c : allChunks) {
			if(findCenterTopBlockOfChunk(c).getY() > floodYLevel) {
				floodYLevel = findCenterTopBlockOfChunk(c).getY();
			}
		}

		//Leave a little high ground, accounts for hitting tree tops with check
		floodYLevel -= 15;

		//WB dimensions
		Double worldBorderSize = world.getWorldBorder().getSize();
		Double halfWbSize = (worldBorderSize / 2);

		//WB corners
		Location firstCorner = new Location(world, spawn.getX() + halfWbSize, floodYLevel, spawn.getZ() + halfWbSize);
		Location secondCorner = new Location(world, spawn.getX() - halfWbSize, floodYLevel, spawn.getZ() - halfWbSize);

		//Double loop through x and z coords to plant water
		for(double z = firstCorner.getZ(); z >= secondCorner.getZ(); z--) {
			for (double i = firstCorner.getX(); i >= secondCorner.getX(); i--) {
				Location floodSpot = new Location(world, i, floodYLevel, z);
				floodSpot.getBlock().setType(Material.WATER);
			}
		}
	}
}
