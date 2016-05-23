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
	}

	@Override
	public void giveGift(Player player) {
		player.getInventory().setItemInMainHand(this.pickAxe);
		player.sendMessage(ChatColor.BLACK + "Ah you think darkness is your ally? You merely adopted the dark. I was born in it, molded by it. I didn't see the light until I was already a man, by then it was nothing to me but blinding!");
	}
}
