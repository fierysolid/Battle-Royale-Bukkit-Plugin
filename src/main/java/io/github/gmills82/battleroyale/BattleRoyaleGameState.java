package io.github.gmills82.battleroyale;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Grant Mills
 * @since 5/22/16
 */
public class BattleRoyaleGameState {
	private BukkitTask destructSequenceRunnable;
	private BukkitTask warnPlayersRunnable;
	private String battleName;
	private Set<Player> battlePlayers = new HashSet<Player>();
	//TODO: Catastrophy runnable


	public Set<Player> getCurrentBattlePlayersOnline() {
		Set<Player> onlineBattlePlayers = new HashSet<Player>();
		for(Player player : battlePlayers) {
			if(player.isOnline()) {
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
}
