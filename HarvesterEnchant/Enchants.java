package com.CFE.custom;

import org.bukkit.Material;
import org.bukkit.craftbukkit.Main;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class Enchants extends Enchantment implements Listener {

	Main plugin;
	
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		if (e.getPlayer() instanceof Player) {
			Player player = e.getPlayer();
			if (player.getInventory().getItemInMainHand().containsEnchantment(this)| player.getInventory().getItemInMainHand().getItemMeta().hasEnchant(this)){
				
			if (e.getBlock().getType() == Material.WHEAT) {
				ItemStack drop = new ItemStack(Material.WHEAT);
				player.getInventory().addItem(drop);
				e.setDropItems(false);
			} 
			
			if (e.getBlock().getType() == Material.POTATO) {
				ItemStack drop = new ItemStack(Material.POTATO_ITEM);
				player.getInventory().addItem(drop);
				e.setDropItems(false);
			}
			
			if (e.getBlock().getType() == Material.CARROT) {
				ItemStack drop = new ItemStack(Material.CARROT_ITEM);
				player.getInventory().addItem(drop);
				e.setDropItems(false);
			}
			
			if(e.getBlock().getType() == Material.SUGAR_CANE_BLOCK) {
				ItemStack drop = new ItemStack(Material.SUGAR_CANE);
				player.getInventory().addItem(drop);
				e.setDropItems(false);
			}
			
			if(e.getBlock().getType() == Material.MELON_BLOCK) {
				ItemStack drop = new ItemStack(Material.MELON);
				player.getInventory().addItem(drop);
				e.setDropItems(false);
			}
			
			if (e.getBlock().getType() == Material.PUMPKIN) {
				ItemStack drop = new ItemStack(Material.PUMPKIN);
				player.getInventory().addItem(drop);
				e.setDropItems(false);
			}
			
			if(e.getBlock().getType() == Material.NETHER_WART_BLOCK) {
				ItemStack drop = new ItemStack(Material.NETHER_WARTS);
				player.getInventory().addItem(drop);
				e.setDropItems(false);
			}
		}
	}
	}
	
	public Enchants(int id) {
		super(id);
	}
	@Override
	public int getId() {
		return 461;
	}

	@Override
	public boolean canEnchantItem(ItemStack item) {
		return item.getType() == Material.DIAMOND_AXE;
	}

	@Override
	public boolean conflictsWith(Enchantment arg0) {
		return false;
	}

	@Override
	public EnchantmentTarget getItemTarget() {
		return EnchantmentTarget.TOOL;
	}

	@Override
	public int getMaxLevel() {
		return 1;
	}

	@Override
	public String getName() {
		return "Harvester";
	}

	@Override
	public int getStartLevel() {
		return 1;
	}

	@Override
	public boolean isCursed() {
		return false;
	}

	@Override
	public boolean isTreasure() {
		return false;
	}
	
}

