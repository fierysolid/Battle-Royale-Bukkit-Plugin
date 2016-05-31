package io.github.gmills82.battleroyale.util;

import org.bukkit.Location;
import org.bukkit.Material;

/**
 * @author Grant Mills
 * @since 5/31/16
 */
public class PortalUtil {
	public static void createNetherPortal(Location loc) {

		loc.setY(loc.getWorld().getHighestBlockAt(loc.getBlockX(), loc.getBlockZ()).getY());

		//left side
		loc.getBlock().setType(Material.OBSIDIAN);
		loc.add(0, 1, 0).getBlock().setType(Material.OBSIDIAN);
		loc.add(0, 1, 0).getBlock().setType(Material.OBSIDIAN);
		loc.add(0, 1, 0).getBlock().setType(Material.OBSIDIAN);
		//top
		loc.add(1, 0, 0).getBlock().setType(Material.OBSIDIAN);
		loc.add(1, 0, 0).getBlock().setType(Material.OBSIDIAN);
		loc.add(1, 0, 0).getBlock().setType(Material.OBSIDIAN);
		//right
		loc.subtract(0, 1, 0).getBlock().setType(Material.OBSIDIAN);
		loc.subtract(0, 1, 0).getBlock().setType(Material.OBSIDIAN);
		loc.subtract(0, 1, 0).getBlock().setType(Material.OBSIDIAN);
		loc.subtract(0, 1, 0).getBlock().setType(Material.OBSIDIAN);
		//bottom
		loc.subtract(1, 0, 0).getBlock().setType(Material.OBSIDIAN);
		loc.subtract(1, 0, 0).getBlock().setType(Material.OBSIDIAN);
		loc.subtract(1, 0, 0).getBlock().setType(Material.OBSIDIAN);

		//back at original block - set portal
		loc.add(1, 1, 0).getBlock().setType(Material.FIRE);
	}
}
