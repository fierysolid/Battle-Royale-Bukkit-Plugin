package io.github.gmills82.battleroyale.gifts.impl;

import io.github.gmills82.battleroyale.gifts.PlayerGift;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * @author Grant Mills
 * @since 5/23/16
 */
public class Ender implements PlayerGift {
	private ItemStack head;
	private ItemStack eggs;

	public Ender() {
		this.head = new ItemStack(Material.PUMPKIN);
		this.eggs = new ItemStack(Material.MONSTER_EGG, 64, (byte) 58);
	}

	@Override
	public void giveGift(Player player) {
		player.getInventory().setHelmet(this.head);
		player.getInventory().addItem(this.eggs);
		player.sendMessage(ChatColor.MAGIC + "The Ender");
	}
}
