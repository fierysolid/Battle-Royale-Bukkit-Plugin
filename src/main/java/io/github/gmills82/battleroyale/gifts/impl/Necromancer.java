package io.github.gmills82.battleroyale.gifts.impl;

import io.github.gmills82.battleroyale.gifts.PlayerGift;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Grant Mills
 * @since 5/23/16
 */
public class Necromancer implements PlayerGift {
	private List<ItemStack> eggs = new ArrayList<ItemStack>();
	private ItemStack helm;

	public Necromancer() {
		//Skeletons
		eggs.add(new ItemStack(Material.MONSTER_EGG, 10, (byte) 51));
		//Zombie
		eggs.add(new ItemStack(Material.MONSTER_EGG, 10, (byte) 54));
		//Wither
		eggs.add(new ItemStack(Material.MONSTER_EGG, 1, (byte) 64));

		this.helm = new ItemStack(Material.SKULL_ITEM, 1, (byte) 0);
	}

	@Override
	public void giveGift(Player player) {
		for(ItemStack eggStack: eggs) {
			player.getInventory().addItem(eggStack);
		}
		player.getInventory().setHelmet(this.helm);
		player.sendMessage(ChatColor.DARK_RED + "The Necromancer");
	}
}
