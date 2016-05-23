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
public class SeaWalker implements PlayerGift{
	private ItemStack fishingRod;
	private ItemStack boots;

	public SeaWalker() {
		//Rod
		ItemStack rod = new ItemStack(Material.FISHING_ROD);

		//Name
		ItemMeta itemMeta = rod.getItemMeta();
		itemMeta.setDisplayName("Bamboo Cane Pole");
		rod.setItemMeta(itemMeta);

		//Enchant
		rod.addEnchantment(Enchantment.MENDING, 1);
		rod.addUnsafeEnchantment(Enchantment.LUCK, 5);
		rod.addUnsafeEnchantment(Enchantment.LURE, 5);

		this.fishingRod = rod;

		//Boots
		ItemStack galloshes = new ItemStack(Material.IRON_BOOTS);

		//Name
		ItemMeta galloshesMeta = rod.getItemMeta();
		galloshesMeta.setDisplayName("Galoshes");
		rod.setItemMeta(galloshesMeta);

		//Enchants
		galloshes.addEnchantment(Enchantment.FROST_WALKER, 1);

		this.boots = galloshes;
	}

	@Override
	public void giveGift(Player player) {
		player.getInventory().setItemInMainHand(this.fishingRod);
		player.getInventory().setBoots(this.boots);
		player.sendMessage(ChatColor.DARK_AQUA + "The Sea Walker");
	}
}
