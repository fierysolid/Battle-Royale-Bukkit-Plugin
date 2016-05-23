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
public class Übermensch implements PlayerGift {
	private ItemStack chest;
	private ItemStack axe;

	public Übermensch() {
		//Chest
		ItemStack chest = new ItemStack(Material.IRON_CHESTPLATE);

		//Name
		ItemMeta chestMeta = chest.getItemMeta();
		chestMeta.setDisplayName("Resilience of Man");
		chest.setItemMeta(chestMeta);

		//Enchants
		chest.addEnchantment(Enchantment.DURABILITY, 3);
		chest.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);

		this.chest = chest;

		//Axe
		ItemStack axe = new ItemStack(Material.IRON_AXE);

		//Name
		ItemMeta axeMeta = axe.getItemMeta();
		axeMeta.setDisplayName("Axe of Transience");
		axe.setItemMeta(axeMeta);

		axe.addEnchantment(Enchantment.DAMAGE_ALL, 3);

		this.axe = axe;

	}

	@Override
	public void giveGift(Player player) {
		player.getInventory().setChestplate(this.chest);
		player.getInventory().setItemInMainHand(axe);
		player.sendMessage(ChatColor.DARK_PURPLE + "The Übermensch");
	}
}
