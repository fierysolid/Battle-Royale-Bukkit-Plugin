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
public class Demolitionist implements PlayerGift {
	private List<ItemStack> demoInventory = new ArrayList<ItemStack>();

	public Demolitionist() {
		ItemStack dynamite = new ItemStack(Material.TNT, 15);
		ItemStack redstone = new ItemStack(Material.REDSTONE, 64);
		ItemStack string = new ItemStack(Material.STRING, 5);
		ItemStack tripWireHook = new ItemStack(Material.TRIPWIRE_HOOK, 10);

		demoInventory.add(dynamite);
		demoInventory.add(redstone);
		demoInventory.add(string);
		demoInventory.add(tripWireHook);
		demoInventory.add(new ItemStack(Material.MONSTER_EGG, 4, (byte) 50 ));
	}

	@Override
	public void giveGift(Player player) {
		for(ItemStack nextItem: demoInventory) {
			player.getInventory().addItem(nextItem);
		}
		player.sendMessage(ChatColor.GOLD + "Some men just want to watch the world burn");
	}
}
