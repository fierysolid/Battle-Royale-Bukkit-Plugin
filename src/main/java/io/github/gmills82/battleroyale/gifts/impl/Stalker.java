package io.github.gmills82.battleroyale.gifts.impl;

import io.github.gmills82.battleroyale.gifts.PlayerGift;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Grant Mills
 * @since 5/23/16
 */
public class Stalker implements PlayerGift {

	private ItemStack bow;
	private List<ItemStack> inventory = new ArrayList<ItemStack>();
	private ItemStack chest;
	private ItemStack boots;
	private ItemStack pants;

	public Stalker() {
		//armor
		this.chest = new ItemStack(Material.LEATHER_CHESTPLATE);
		this.boots = new ItemStack(Material.LEATHER_BOOTS);
		this.pants = new ItemStack(Material.LEATHER_LEGGINGS);

		//bow
		ItemStack bow = new ItemStack(Material.BOW);

		//Set name
		ItemMeta itemMeta = bow.getItemMeta();
		itemMeta.setDisplayName("Stalker Bow");
		bow.setItemMeta(itemMeta);

		//enchants
		bow.addEnchantment(Enchantment.MENDING, 1);
		bow.addEnchantment(Enchantment.ARROW_INFINITE, 1);
		bow.addEnchantment(Enchantment.ARROW_DAMAGE, 1);

		this.bow = bow;

		this.inventory.add(new ItemStack(Material.ARROW));
		this.inventory.add(new ItemStack(Material.SPECTRAL_ARROW, 20));
		this.inventory.add(new ItemStack(Material.BONE, 25));
	}

	@Override
	public void giveGift(Player player) {
		PlayerInventory playerInventory = player.getInventory();

		playerInventory.setChestplate(this.chest);
		playerInventory.setBoots(this.boots);
		playerInventory.setLeggings(this.pants);
		playerInventory.setItemInMainHand(this.bow);

		for(ItemStack itemStack: this.inventory) {
			playerInventory.addItem(itemStack);
		}

		player.sendMessage(ChatColor.DARK_PURPLE + "Track your prey");
	}
}
