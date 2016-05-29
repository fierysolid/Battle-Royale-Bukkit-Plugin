package io.github.gmills82.battleroyale.catastrophy;

import org.bukkit.World;

/**
 * @author Grant Mills
 * @since 5/29/16
 */
public interface Catastrophy {
	double getDelay();

	void warnPlayers();

	void initiateCatastrophy(World world);
}
