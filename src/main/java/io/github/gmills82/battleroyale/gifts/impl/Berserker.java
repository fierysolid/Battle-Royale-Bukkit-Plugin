package io.github.gmills82.battleroyale.gifts.impl;

import io.github.gmills82.battleroyale.gifts.PlayerGift;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * @author Grant Mills
 * @since 5/23/16
 */
public class Berserker implements PlayerGift {
	private ItemStack sword;

	public Berserker() {
		ItemStack sword = new ItemStack(Material.WOOD_SPADE);

		sword.addEnchantment(Enchantment.MENDING, 1);
		sword.addEnchantment(Enchantment.DURABILITY, 3);
		sword.addEnchantment(Enchantment.KNOCKBACK, 2);

		this.sword = sword;
	}

	@Override
	public void giveGift(Player player) {
		//Increase walking speed
		player.setWalkSpeed(1L);
		player.setSprinting(true);
		player.getInventory().setItemInMainHand(this.sword);
		player.sendMessage(ChatColor.DARK_PURPLE + "Large. Fast. Completely Insane.");
	}
}
