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
public class MacGyver implements PlayerGift {
	private List<ItemStack> inventory = new ArrayList<ItemStack>();

	public MacGyver() {
		this.inventory.add(new ItemStack(Material.WOOD, 64));
		this.inventory.add(new ItemStack(Material.WOOD, 64, (byte) 2));
		this.inventory.add(new ItemStack(Material.WOOD, 64, (byte) 3));
		this.inventory.add(new ItemStack(Material.COBBLESTONE, 64));
		this.inventory.add(new ItemStack(Material.SLIME_BALL, 16));
		this.inventory.add(new ItemStack(Material.COAL, 64));
		this.inventory.add(new ItemStack(Material.DIAMOND, 3));
		this.inventory.add(new ItemStack(Material.IRON_INGOT, 30));
		this.inventory.add(new ItemStack(Material.GOLD_INGOT, 7));
		this.inventory.add(new ItemStack(Material.GLASS, 64));
		this.inventory.add(new ItemStack(Material.APPLE, 3));
		this.inventory.add(new ItemStack(Material.STRING, 64));
		this.inventory.add(new ItemStack(Material.REDSTONE, 64));
		this.inventory.add(new ItemStack(Material.SPONGE, 64));
	}

	@Override
	public void giveGift(Player player) {
		player.giveExpLevels(30);
		for(ItemStack stack: this.inventory) {
			player.getInventory().addItem(stack);
		}
		player.sendMessage(ChatColor.DARK_PURPLE + "MacGyver");
	}
}
