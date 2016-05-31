package io.github.gmills82.battleroyale.catastrophy.impl;

import io.github.gmills82.battleroyale.catastrophy.Catastrophy;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import static io.github.gmills82.battleroyale.util.PortalUtil.createNetherPortal;

/**
 * @author Grant Mills
 * @since 5/29/16
 */
public class NetherInvasion implements Catastrophy {
	@Override
	public double getDelay() {
		return 0;
	}

	@Override
	public void warnPlayers() {
		Bukkit.getServer().broadcastMessage("The Nether is invading!");
	}

	@Override
	public void initiateCatastrophy(World world) {
		Location portalStart = world.getSpawnLocation();
		portalStart.setX(portalStart.getX() + 10);
		createNetherPortal(portalStart);
	}
}
