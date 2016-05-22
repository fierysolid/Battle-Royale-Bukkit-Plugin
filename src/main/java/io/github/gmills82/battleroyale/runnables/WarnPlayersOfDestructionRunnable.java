package io.github.gmills82.battleroyale.runnables;

import io.github.gmills82.battleroyale.BattleRoyaleGameState;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @author Grant Mills
 * @since 5/21/16
 */
public class WarnPlayersOfDestructionRunnable extends BukkitRunnable {

	private BattleRoyaleGameState gameState;
	private Chunk chunkToDestroy;

	public WarnPlayersOfDestructionRunnable(BattleRoyaleGameState gameState, Chunk chunkToDestroy) {
		this.chunkToDestroy = chunkToDestroy;
		this.gameState = gameState;
	}

	@Override
	public void run() {
		// Check for players inside the chunk
		// Tell each player individually if they need to move
		// Should happen at a repeating countdown interval until the chunk is destroyed
		for(Player battlePlayer : gameState.getCurrentBattlePlayersOnline()) {
			if(battlePlayer.getLocation().getChunk() == chunkToDestroy) {
				battlePlayer.sendMessage(ChatColor.RED + "Evacuate! This area is scheduled for destruction.");
			}
		}
	}
}
