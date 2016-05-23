package io.github.gmills82.battleroyale.gifts.impl;

import io.github.gmills82.battleroyale.gifts.PlayerGift;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Grant Mills
 * @since 5/23/16
 */
public class Devil implements PlayerGift {
	private List<ItemStack> inventory = new ArrayList<ItemStack>();
	private ItemStack pants;
	private ItemStack boots;
	private ItemStack chest;
	private ItemStack helm;

	public Devil() {
		this.inventory.add(new ItemStack(Material.OBSIDIAN, 20));
		this.inventory.add(new ItemStack(Material.FLINT_AND_STEEL));
		this.inventory.add(new ItemStack(Material.ENDER_PEARL, 5));

		//armor
		ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
		ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
		ItemStack pants = new ItemStack(Material.LEATHER_LEGGINGS);
		ItemStack helm = new ItemStack(Material.SKULL_ITEM, 1, (byte) 1);

		chest.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
		boots.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
		pants.addEnchantment(Enchantment.PROTECTION_FIRE, 4);

		this.chest = chest;
		this.boots = boots;
		this.pants = pants;
		this.helm = helm;

	}

	@Override
	public void giveGift(Player player) {
		for(ItemStack item : this.inventory) {
			player.getInventory().addItem(item);
		}
		player.getInventory().setChestplate(this.chest);
		player.getInventory().setBoots(this.boots);
		player.getInventory().setLeggings(this.pants);
		player.getInventory().setHelmet(this.helm);

		player.sendMessage(ChatColor.RED + "The Devil");
	}
}
