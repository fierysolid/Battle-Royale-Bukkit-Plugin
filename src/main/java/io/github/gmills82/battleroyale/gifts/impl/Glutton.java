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
public class Glutton implements PlayerGift {
	private List<ItemStack> food = new ArrayList<ItemStack>();
	private ItemStack spoon;

	public Glutton() {
		this.food.add(new ItemStack(Material.COOKED_BEEF, 10));
		this.food.add(new ItemStack(Material.MUTTON, 10));
		this.food.add(new ItemStack(Material.BAKED_POTATO, 32));
		this.food.add(new ItemStack(Material.COOKED_FISH, 32));

		ItemStack spoon = new ItemStack(Material.WOOD_SPADE);
		spoon.addEnchantment(Enchantment.MENDING, 1);
		spoon.addEnchantment(Enchantment.DURABILITY, 3);
		spoon.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 1);
		this.spoon = spoon;
	}

	@Override
	public void giveGift(Player player) {
		for(ItemStack item : this.food) {
			player.getInventory().addItem(item);
		}
		player.getInventory().setItemInMainHand(this.spoon);
		player.sendMessage(ChatColor.DARK_PURPLE + "Everything in this room is edible. Even I'm edible. But, that would be called cannibalism. It is looked down upon in most societies.");
	}
}
