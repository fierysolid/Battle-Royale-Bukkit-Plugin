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
public class Mole implements PlayerGift {

	private ItemStack pickAxe;
	private ItemStack food;
	private ItemStack cow;

	public Mole() {
		//PickAxe
		ItemStack pick = new ItemStack(Material.DIAMOND_PICKAXE);

		//Name
		ItemMeta pickMeta = pick.getItemMeta();
		pickMeta.setDisplayName("Tunneler");
		pick.setItemMeta(pickMeta);

		//Enchant
		pick.addEnchantment(Enchantment.DURABILITY, 3);
		pick.addEnchantment(Enchantment.MENDING, 1);
		pick.addEnchantment(Enchantment.DIG_SPEED, 5);

		this.pickAxe = pick;

		this.food = new ItemStack(Material.BOWL, 5);
		this.cow = new ItemStack(Material.MONSTER_EGG, 1, (byte) 96);
	}

	@Override
	public void giveGift(Player player) {
		player.getInventory().setItemInMainHand(this.pickAxe);
		player.getInventory().addItem(this.cow);
		player.getInventory().addItem(this.food);
		player.sendMessage(ChatColor.DARK_PURPLE + "The Mole");
	}
}
