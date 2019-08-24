package Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class EarthArmour implements Listener{

	public List<ItemStack> earthArmour = getEarthArmour();
	public List<Player> earthList = new ArrayList<>();
	public HashMap<Player, Integer> earthCount = new HashMap<>();
	public HashMap<Player, Double> Health = new HashMap<>();
	
	Plugin plugin;
	
	public EarthArmour(Plugin main) {
		this.plugin = main;
	}
	
	public static List<ItemStack> getEarthArmour(){
		
		List<ItemStack> items = new ArrayList<>();
		ItemStack earthHelmet = CustomItem.customItem(new ItemStack(Material.LEATHER_HELMET), "Earth Helmet");
		earthHelmet.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
		ItemStack earthChest = CustomItem.customItem(new ItemStack(Material.LEATHER_CHESTPLATE), "Earth Chestplate");
		earthChest.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
		ItemStack earthLeg = CustomItem.customItem(new ItemStack(Material.LEATHER_LEGGINGS), "Earth Leggings");
		earthLeg.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
		ItemStack earthBoots = CustomItem.customItem(new ItemStack(Material.LEATHER_BOOTS), "Earth Boots");
		earthBoots.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
		items.add(earthHelmet);
		items.add(earthChest);
		items.add(earthLeg);
		items.add(earthBoots);
		List<ItemStack> finall = CustomItem.colourLeatherArmour(items, Color.GREEN);
		return finall;
	}
	
	@EventHandler
	public void EarthEquip(InventoryCloseEvent e) {
		
		Player p = (Player) e.getPlayer();
		
		if (earthCount.containsKey(p)) {
			earthCount.replace(p, 0);
		} else {
			earthCount.put(p, 0);
		}
		
		ItemStack[] armour = p.getInventory().getArmorContents();
		
		for (ItemStack item: armour) {
			if (item == null || item.getType() == Material.AIR) {
				//do nothing
			} else {
				
				//check against every item in earthArmour
				for (ItemStack a: earthArmour) {
					if (isSimilar(item, a)) {
						earthCount.put(p, earthCount.get(p) + 1);
					}else {
					}
				} 
				
			}
		}
		
		if (earthCount.get(p) == 4) {
			if (! earthList.contains(p)) {
				earthList.add(p);
				Health.put(p, p.getMaxHealth());
				p.setMaxHealth(40.D);
				if (p.getHealth() == 20) {
					p.setHealth(40);
				}
			}
		} else {
			if (earthList.contains(p)) {
				earthList.remove(p);
				if (Health.containsKey(p)) {
					p.setMaxHealth(Health.get(p) + 0.D);
				} else {
					p.setMaxHealth(20.D);
				}
				
			}
		}
		
	}
	
	public boolean isSimilar(ItemStack first,ItemStack second){

        boolean similar = false;

        if(first == null || second == null){
            return similar;
        }

        boolean sameTypeId = (first.getTypeId() == second.getTypeId());
        boolean sameDurability = (first.getDurability() == second.getDurability());
        boolean sameAmount = (first.getAmount() == second.getAmount());
        boolean sameHasItemMeta = (first.hasItemMeta() == second.hasItemMeta());
        boolean sameEnchantments = (first.getEnchantments().equals(second.getEnchantments()));
        boolean sameItemMeta = true;

        if(sameHasItemMeta) {
            sameItemMeta = Bukkit.getItemFactory().equals(first.getItemMeta(), second.getItemMeta());
        }

        if(sameTypeId && sameDurability && sameAmount && sameHasItemMeta && sameEnchantments && sameItemMeta){
            similar = true;
        }

        return similar;

    }

	
}
