package io.github.gmills82.battleroyale.gifts.impl;

import io.github.gmills82.battleroyale.gifts.PlayerGift;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * @author Grant Mills
 * @since 5/23/16
 */
public class Angel implements PlayerGift {
	private ItemStack sword;
	private ItemStack wings;

	public Angel() {
		//Sword
		ItemStack flamingSwordOfTruth = new ItemStack(Material.GOLD_SWORD);

		//Sword name
		ItemMeta itemMeta = flamingSwordOfTruth.getItemMeta();
		itemMeta.setDisplayName("The Word of God");
		flamingSwordOfTruth.setItemMeta(itemMeta);

		//Enchants
		flamingSwordOfTruth.addEnchantment(Enchantment.FIRE_ASPECT, 2);
		flamingSwordOfTruth.addEnchantment(Enchantment.DAMAGE_UNDEAD, 3);

		this.sword = flamingSwordOfTruth;
		this.wings = new ItemStack(Material.ELYTRA, 1);
	}

	@Override
	public void giveGift(Player player) {
		player.getInventory().setItemInMainHand(this.sword);
		player.getInventory().setChestplate(this.wings);

		player.sendMessage(ChatColor.GOLD + "Angel of the Lord");
	}
}
