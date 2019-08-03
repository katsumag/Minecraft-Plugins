package Main;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class CustomItem {

    public static ItemStack customItem(ItemStack item, String displayName, List<String> lore){
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayName);
        if (lore != null) {
        	meta.setLore(lore);
        }
        item.setItemMeta(meta);
       
        return item;
    }
    
    public static ItemStack customItem(ItemStack item, String displayName){
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayName);
        item.setItemMeta(meta);
       
        return item;
    }
    
    public static void giveCustomItem(Player player, ItemStack item, String displayName, List<String> lore){
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayName);
        meta.setLore(lore);
        item.setItemMeta(meta);
        player.getInventory().addItem(item);
        return;
    }
    
    public static boolean isArmour(ItemStack item) {
    	
    		if (item.getType() == Material.CHAINMAIL_HELMET || item.getType() == Material.IRON_HELMET || item.getType() == Material.GOLD_HELMET || item.getType() == Material.IRON_HELMET || item.getType() == Material.LEATHER_HELMET) {
    			return true;
    		}
    		
    		if (item.getType() == Material.CHAINMAIL_CHESTPLATE || item.getType() == Material.IRON_CHESTPLATE || item.getType() == Material.GOLD_CHESTPLATE || item.getType() == Material.IRON_CHESTPLATE || item.getType() == Material.LEATHER_CHESTPLATE) {
    			return true;
    		}
    		
    		if (item.getType() == Material.CHAINMAIL_LEGGINGS || item.getType() == Material.IRON_LEGGINGS || item.getType() == Material.GOLD_LEGGINGS || item.getType() == Material.IRON_LEGGINGS || item.getType() == Material.LEATHER_LEGGINGS) {
    			return true;
    		}
    		
    		if (item.getType() == Material.CHAINMAIL_BOOTS|| item.getType() == Material.IRON_BOOTS || item.getType() == Material.GOLD_BOOTS || item.getType() == Material.IRON_BOOTS || item.getType() == Material.LEATHER_BOOTS) {
    			return true;
    		}
    		
    	return false;
    }
    
    public static boolean isCustomItem(ItemStack item){
        if(item.hasItemMeta()){
            if((item.getItemMeta().hasDisplayName()) ||
                    item.getItemMeta().hasLore()){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
    public static boolean isCustomItem(ItemStack item, String displayName){
        if(item.hasItemMeta()){
            if((item.getItemMeta().hasDisplayName())){
                if(item.getItemMeta().getDisplayName().equalsIgnoreCase(displayName)){
                    return true;
                }else{
                    return false;
                }
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
    public static boolean isCustomItem(ItemStack item, List<String> lore){
        if(item.hasItemMeta()){
            if((item.getItemMeta().hasLore())){
                if(item.getItemMeta().getLore().equals(lore)){
                    return true;
                }else{
                    return false;
                }
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
    public static boolean isCustomItem(ItemStack item, String displayName, List<String> lore){
        if(item.hasItemMeta()){
            if((item.getItemMeta().hasDisplayName()) &&
                    item.getItemMeta().hasLore()){
                if(item.getItemMeta().getDisplayName().equalsIgnoreCase(displayName) &&
                        item.getItemMeta().getLore().equals(lore)){
                    return true;
                }else{
                    return false;
                }
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
    
    public static ItemStack getPlayerHead(Player player) {
        ItemStack head = new ItemStack(Material.SKULL_ITEM, 1); // Generate the initial ItemStack
        SkullMeta meta = (SkullMeta) head.getItemMeta(); // Notice how this has to be cast to a SkullMeta object

        meta.setOwner(player.getName()); // Set the uuid of the player
        // You can ping the mojang API for offline players
        // https://api.mojang.com/users/profiles/minecraft/USERNAME
        head.setItemMeta(meta); // Apply the meta to the actual stack

        return head; // Return the ItemStack
    }
    
    public static List<ItemStack> getArmourSet(Material a, Material b, Material c, Material d, Color colour){
    	List<ItemStack> list = new ArrayList<>();
    	list.add(new ItemStack(a));
    	list.add(new ItemStack(b));
    	list.add(new ItemStack(c));
    	list.add(new ItemStack(d));
    	if (colour != null) {
    	list.forEach(item -> {
    		LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
    		meta.setColor(colour);
    		item.setItemMeta(meta);
    	});
    	}
    	return list;
    }
    
    public static List<ItemStack> getArmourSet(Material a, Material b, Material c, Color colour){
    	List<ItemStack> list = new ArrayList<>();
    	list.add(new ItemStack(a));
    	list.add(new ItemStack(b));
    	list.add(new ItemStack(c));
    	if (colour != null) {
    	list.forEach(item -> {
    		LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
    		meta.setColor(colour);
    		item.setItemMeta(meta);
    	});
    	}
    	return list;
    }
    
    public static List<ItemStack> getArmourSet(Material a, Material b, Material c, Material d){
    	List<ItemStack> list = new ArrayList<>();
    	list.add(new ItemStack(a));
    	list.add(new ItemStack(b));
    	list.add(new ItemStack(c));
    	list.add(new ItemStack(d));
    	return list;
    }
    
    public static List<ItemStack> getArmourSet(Material a, Material b, Color colour){
    	List<ItemStack> list = new ArrayList<>();
    	list.add(new ItemStack(a));
    	list.add(new ItemStack(b));
    	if (colour != null) {
    	list.forEach(item -> {
    		LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
    		meta.setColor(colour);
    		item.setItemMeta(meta);
    	});
    	}
    	return list;
    }
    
    public static List<ItemStack> nameArmourSet(List<ItemStack> items, String name){
    	items.forEach(item -> {
    		if (item.getType() == Material.CHAINMAIL_HELMET || item.getType() == Material.IRON_HELMET || item.getType() == Material.GOLD_HELMET || item.getType() == Material.IRON_HELMET || item.getType() == Material.LEATHER_HELMET) {
    			ItemMeta meta = item.getItemMeta();
    			meta.setDisplayName(name + " Helmet");
    			item.setItemMeta(meta);
    		}
    		
    		if (item.getType() == Material.CHAINMAIL_CHESTPLATE || item.getType() == Material.IRON_CHESTPLATE || item.getType() == Material.GOLD_CHESTPLATE || item.getType() == Material.IRON_CHESTPLATE || item.getType() == Material.LEATHER_CHESTPLATE) {
    			ItemMeta meta = item.getItemMeta();
    			meta.setDisplayName(name + " Chestplate");
    			item.setItemMeta(meta);
    		}
    		
    		if (item.getType() == Material.CHAINMAIL_LEGGINGS || item.getType() == Material.IRON_LEGGINGS || item.getType() == Material.GOLD_LEGGINGS || item.getType() == Material.IRON_LEGGINGS || item.getType() == Material.LEATHER_LEGGINGS) {
    			ItemMeta meta = item.getItemMeta();
    			meta.setDisplayName(name + " Leggings");
    			item.setItemMeta(meta);
    		}
    		
    		if (item.getType() == Material.CHAINMAIL_BOOTS|| item.getType() == Material.IRON_BOOTS || item.getType() == Material.GOLD_BOOTS || item.getType() == Material.IRON_BOOTS || item.getType() == Material.LEATHER_BOOTS) {
    			ItemMeta meta = item.getItemMeta();
    			meta.setDisplayName(name + " Boots");
    			item.setItemMeta(meta);
    		}
    		
    	});
    	return items;
    }
    
    public static ItemStack makeBook(ItemStack book, Enchantment ench, int level) {
    	EnchantmentStorageMeta meta = (EnchantmentStorageMeta) book.getItemMeta();
    	meta.addStoredEnchant(ench, level, true);
    	book.setItemMeta(meta);
    	return book;
    }
    
    public static ItemStack makeBook(ItemStack book, Enchantment ench, int level, String name) {
    	EnchantmentStorageMeta meta = (EnchantmentStorageMeta) book.getItemMeta();
    	meta.addStoredEnchant(ench, level, true);
    	meta.setDisplayName(name);
    	book.setItemMeta(meta);
    	return book;
    }
    
    public static ItemStack makeBook(ItemStack book, Enchantment ench, int level, String name, String lore) {
    	EnchantmentStorageMeta meta = (EnchantmentStorageMeta) book.getItemMeta();
    	meta.addStoredEnchant(ench, level, true);
    	meta.setDisplayName(name);
    	meta.setLore(Arrays.asList(lore));
    	book.setItemMeta(meta);
    	return book;
    }
    
    public static void addRecipe(ItemStack item, String a, String b, String c, Character aa, Material mat) {
    	ShapedRecipe recipe = new ShapedRecipe(item);
    	recipe.shape(a, b, c);
    	recipe.setIngredient(aa, mat);
    	Bukkit.addRecipe(recipe);
    }
    
    public static List<ItemStack> colourLeatherArmour(List<ItemStack> items, Color colour){
    	items.forEach(item -> {
    		LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
    		meta.setColor(colour);
    	});
    	
    	return items;
    }
    
}
