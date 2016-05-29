package io.github.gmills82.battleroyale.runnables;

import io.github.gmills82.battleroyale.catastrophy.Catastrophy;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @author Grant Mills
 * @since 5/29/16
 */
public class CatastrophyRunnable extends BukkitRunnable {
	private World world;
	private Catastrophy catastrophy;

	public CatastrophyRunnable(World world, Catastrophy catastrophy) {
		this.world = world;
		this.catastrophy = catastrophy;
	}

	@Override
	public void run() {
		this.catastrophy.initiateCatastrophy(world);
	}
}
