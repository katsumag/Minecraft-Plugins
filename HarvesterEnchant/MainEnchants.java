package com.CFE.custom;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

import com.CFE.custom.Enchants;

public class MainEnchants extends JavaPlugin implements Listener{
	
	public Enchants ench = new Enchants(461);
	
	public void onEnable() {
		PluginDescriptionFile pdfFile = getDescription();
		Logger logger = getLogger();
		logger.info(pdfFile.getName() + " has been enabled! ");
		getServer().getPluginManager().registerEvents(this, this);
		getServer().getPluginManager().registerEvents(ench, this);
		LoadEnchantments();
	}
	
	@SuppressWarnings("unchecked")
	public void onDisable() {
		PluginDescriptionFile pdfFile = getDescription();
		Logger logger = getLogger();
		logger.info(pdfFile.getName() + " has been disabled! ");
		try {
			Field byIdField = Enchantment.class.getDeclaredField("byId");
			Field byNameField = Enchantment.class.getDeclaredField("byName");
			byIdField.setAccessible(true);
			byNameField.setAccessible(true);
			HashMap<Integer, Enchantment> byId = (HashMap<Integer, Enchantment>) byIdField.get(null);
			HashMap<Integer, Enchantment> byName = (HashMap<Integer, Enchantment>) byNameField.get(null);
			
			if(byId.containsKey(ench.getId())) {
				byId.remove(ench.getId());
			}
			
			if(byName.containsKey(ench.getName())) {
				byName.remove(ench.getName());
			}
			
		} catch (Exception ignored) {
			
		}
	}
	
	private void LoadEnchantments() {
		try {
				Field f = Enchantment.class.getDeclaredField("acceptingNew");
				f.setAccessible(true);
				f.set(null, true);
				f.setAccessible(false);
				Enchantment.registerEnchantment(ench);
				Enchantment.stopAcceptingRegistrations();
			} catch (Exception e) {
				e.printStackTrace();
			}
		
	}
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		System.out.println(cmd.getName().toLowerCase());
		switch(cmd.getName().toLowerCase()) {
			case("harvester"):
				ItemStack book = new ItemStack(Material.ENCHANTED_BOOK);
				EnchantmentStorageMeta meta = (EnchantmentStorageMeta) book.getItemMeta();
				meta.setDisplayName(ChatColor.BLUE + "Harvester I");
				meta.setLore(Arrays.asList("Harvester I", "A Farming Enchantment"));
				meta.addStoredEnchant(ench, 1, true);
				book.setItemMeta(meta);
				Player player = (Player) sender;
				player.getInventory().addItem(book);
				return true;
	}
	return true;
}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void dragDrop(InventoryClickEvent e) {
		if (!(e.getCurrentItem() == null)) {
			if (!(e.getCursor() == null)) {
				ItemStack clicked = e.getCurrentItem();
				ItemStack item =  e.getCursor();
				if (!(item.getItemMeta() == null)) {
				if (!(item.getItemMeta().getDisplayName() == null)) {
				if (item.getItemMeta().getDisplayName().toString().endsWith("Harvester I")) {
					ItemMeta cm = clicked.getItemMeta();
					List<String> lore = clicked.getLore();
					if (!(lore == null)) {
					cm.addEnchant(ench, 1, true);
					cm.setLore(Arrays.asList(lore + "Harvester I"));
					clicked.setItemMeta(cm);
					Player p = (Player) e.getWhoClicked();
					e.setCursor(null);
		} else {
			cm.addEnchant(ench, 1, true);
			cm.setLore(Arrays.asList("Harvester I"));
			clicked.setItemMeta(cm);
			Player p = (Player) e.getWhoClicked();
			p.getInventory().remove(item);
			e.setCursor(null);
		}
				}
	}
	}
	}
	}
	}

}
