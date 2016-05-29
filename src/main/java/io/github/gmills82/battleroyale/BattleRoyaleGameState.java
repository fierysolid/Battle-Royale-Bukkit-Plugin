package io.github.gmills82.battleroyale;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Grant Mills
 * @since 5/22/16
 */
public class BattleRoyaleGameState {
	private Set<Chunk> destroyedChunkSet = new HashSet<Chunk>();
	private BukkitTask destructSequenceRunnable;
	private BukkitTask warnPlayersRunnable;
	private String battleName;
	private Set<Player> battlePlayers = new HashSet<Player>();
	private int countdownToCatastrophy = 7;
	private static final int initialWorldBorderSize = 250;
	private static final int initialWorldBorderDelay = 0;

	public BattleRoyaleGameState() {
		World world = Bukkit.getWorlds().get(0);

		//Set global pvp to false and set adventure mode
		world.setPVP(false);

		List<Player> allPlayers = Bukkit.getWorlds().get(0).getPlayers();
		for (Player player : allPlayers) {
			player.setGameMode(GameMode.ADVENTURE);
		}

		//Set the world border
		WorldBorder worldborder = world.getWorldBorder();
		worldborder.setCenter(world.getSpawnLocation());
		worldborder.setSize(initialWorldBorderSize, initialWorldBorderDelay);

	}

	public Set<Player> getCurrentBattlePlayersOnline() {
		Set<Player> onlineBattlePlayers = new HashSet<Player>();
		for (Player player : battlePlayers) {
			if (player.isOnline()) {
				onlineBattlePlayers.add(player);
			}
		}
		return onlineBattlePlayers;
	}

	public String getBattleName() {
		return battleName;
	}

	public BukkitTask getDestructSequenceRunnable() {
		return destructSequenceRunnable;
	}

	public void setDestructSequenceRunnable(BukkitTask destructSequenceRunnable) {
		this.destructSequenceRunnable = destructSequenceRunnable;
	}

	public BukkitTask getWarnPlayersRunnable() {
		return warnPlayersRunnable;
	}

	public void setWarnPlayersRunnable(BukkitTask warnPlayersRunnable) {
		this.warnPlayersRunnable = warnPlayersRunnable;
	}

	public Set<Player> getBattlePlayers() {
		return battlePlayers;
	}

	public void setBattlePlayers(Set<Player> battlePlayers) {
		this.battlePlayers = battlePlayers;
	}

	public void setBattleName(String battleName) {
		this.battleName = battleName;
	}

	public Set<Chunk> getDestroyedChunkSet() {
		return destroyedChunkSet;
	}

	public void setDestroyedChunkSet(Set<Chunk> destroyedChunkSet) {
		this.destroyedChunkSet = destroyedChunkSet;
	}

	public void lowerCatastrophyCounter() {
		this.countdownToCatastrophy--;
	}

	public int getCountdownToCatastrophy() {
		return countdownToCatastrophy;
	}

}