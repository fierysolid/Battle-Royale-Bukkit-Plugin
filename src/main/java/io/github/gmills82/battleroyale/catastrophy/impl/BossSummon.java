package io.github.gmills82.battleroyale.catastrophy.impl;

import io.github.gmills82.battleroyale.catastrophy.Catastrophy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;

/**
 * @author Grant Mills
 * @since 5/29/16
 */
public class BossSummon implements Catastrophy {
	private final static double DELAY = 3;

	@Override
	public double getDelay() {
		return 3;
	}

	@Override
	public void warnPlayers() {
		Bukkit.getServer().broadcastMessage(ChatColor.DARK_PURPLE +
			"The Dragon will be returned to the Overworld in " + DELAY + " mins!");
	}

	@Override
	public void initiateCatastrophy(World world) {
		Location highestPoint = world.getHighestBlockAt(world.getSpawnLocation()).getLocation();
		highestPoint.setY(highestPoint.getY() + 50);

		world.spawnEntity(highestPoint, EntityType.ENDER_DRAGON);
	}
}
