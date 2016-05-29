package io.github.gmills82.battleroyale.catastrophy.impl;

import io.github.gmills82.battleroyale.catastrophy.Catastrophy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.WorldBorder;

/**
 * @author Grant Mills
 * @since 5/29/16
 */
public class WorldBorderCollapse implements Catastrophy {
	private static final double DELAY_WB_COLLAPSE = 3;

	@Override
	public double getDelay() {
		return 0;
	}

	@Override
	public void warnPlayers() {
		Bukkit.getServer().broadcastMessage(
			ChatColor.DARK_PURPLE + "The WorldBorder will shrink to " +
				(Bukkit.getWorlds().get(0).getWorldBorder().getSize() / 3) +
				" over the next " + DELAY_WB_COLLAPSE + " mins! Anyone caught in its path will die");
	}

	@Override
	public void initiateCatastrophy(World world) {
		WorldBorder worldBorder = world.getWorldBorder();
		worldBorder.setSize(worldBorder.getSize() / 3, new Double(DELAY_WB_COLLAPSE * 60).longValue());
	}
}
