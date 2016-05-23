package io.github.gmills82.battleroyale.gifts;

import org.bukkit.entity.Player;

/**
 * @author Grant Mills
 * @since 5/23/16
 */
public interface PlayerGift {

	/**
	 * Gives gift to the player
	 * @param player - @link{Player} player to give gift to
	 */
	void giveGift(Player player);
}
