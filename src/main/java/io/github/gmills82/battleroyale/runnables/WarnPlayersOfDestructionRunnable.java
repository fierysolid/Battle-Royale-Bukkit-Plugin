package io.github.gmills82.battleroyale.runnables;

import io.github.gmills82.battleroyale.BattleRoyalePlugin;
import org.bukkit.Chunk;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @author Grant Mills
 * @since 5/21/16
 */
public class WarnPlayersOfDestructionRunnable extends BukkitRunnable {

	private final BattleRoyalePlugin plugin;
	private Chunk chunkToDestroy;

	public WarnPlayersOfDestructionRunnable(BattleRoyalePlugin plugin, Chunk chunkToDestroy) {
		this.plugin = plugin;
		this.chunkToDestroy = chunkToDestroy;
	}

	@Override
	public void run() {
		// Check for players inside the chunk
		// Tell each player individually if they need to move
		// Should happen at a repeating countdown interval until the chunk is destroyed
		for(Player battlePlayer : this.plugin.getCurrentBattlePlayersOnline()) {
			if(battlePlayer.getLocation().getChunk() == chunkToDestroy) {
				battlePlayer.sendMessage("Evacuate! This area is scheduled for destruction.");
			}
		}
	}
}
