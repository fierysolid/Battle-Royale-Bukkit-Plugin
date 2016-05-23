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
public class AquaMan implements PlayerGift {
	private ItemStack helmet;

	public AquaMan() {
		ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET);

		//Set name
		ItemMeta itemMeta = helmet.getItemMeta();
		itemMeta.setDisplayName("Triton's Crown");
		helmet.setItemMeta(itemMeta);

		//Set enchantments
		helmet.addEnchantment(Enchantment.OXYGEN, 3);
		helmet.addUnsafeEnchantment(Enchantment.DEPTH_STRIDER, 3);
		helmet.addEnchantment(Enchantment.WATER_WORKER, 1);

		this.helmet = helmet;
	}

	@Override
	public void giveGift(Player player) {
		player.getInventory().setHelmet(this.helmet);
		player.sendMessage(ChatColor.DARK_AQUA + "King of the Seas!");
	}
}
