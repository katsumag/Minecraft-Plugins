package Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class WaterArmour extends main implements Listener{

	public List<Player> waterList = new ArrayList<>();
	public HashMap<Player, Integer> waterCount = new HashMap<>();
	public List<ItemStack> waterArmour = getWaterArmour();
	
	public static List<ItemStack> getWaterArmour(){
		
		List<ItemStack> waterArmour = new ArrayList<>();
		ItemStack waterHelmet = CustomItem.customItem(new ItemStack(Material.LEATHER_HELMET), "Water Helmet");
		waterHelmet.addEnchantment(Enchantment.OXYGEN, 2);
		waterHelmet.addUnsafeEnchantment(Enchantment.WATER_WORKER, 2);
		ItemStack WaterBoots = CustomItem.customItem(new ItemStack(Material.LEATHER_BOOTS), "Water Boots");
		WaterBoots.addUnsafeEnchantment(Enchantment.DEPTH_STRIDER, 2);
		ItemStack waterChestPlate = CustomItem.customItem(new ItemStack(Material.LEATHER_CHESTPLATE), "Water Chestplate");
		ItemStack waterLeggings = CustomItem.customItem(new ItemStack(Material.LEATHER_LEGGINGS), "Water Leggings");
		waterArmour.add(waterHelmet);
		waterArmour.add(waterChestPlate);
		waterArmour.add(waterLeggings);
		waterArmour.add(WaterBoots);
		List<ItemStack> finall = CustomItem.colourLeatherArmour(waterArmour, Color.AQUA);
		return finall;
	}
	
	@EventHandler
	public void WaterEquip(InventoryCloseEvent e) {
		
		Player p = (Player) e.getPlayer();
		
		if (waterCount.containsKey(p)) {
			waterCount.replace(p, 0);
		} else {
			waterCount.put(p, 0);
		}
		
		ItemStack[] armour = p.getInventory().getArmorContents();
		
		for (ItemStack item: armour) {
			if (item == null || item.getType() == Material.AIR) {
				//do nothing
			} else {
				
				//check against every item in waterArmour
				for (ItemStack a: waterArmour) {
					if (item == a) {
						waterCount.put(p, waterCount.get(p) + 1);
					}
				}
				
			}
		}
		
		if (waterCount.get(p) == 4) {
			if (! waterList.contains(p)) {
				waterList.add(p);
			}
		} else {
			if (waterList.contains(p)) {
				waterList.remove(p);
			}
		}
		
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
	    Material m = e.getPlayer().getLocation().getBlock().getType();
	    if (m == Material.STATIONARY_WATER || m == Material.WATER) {
	        // player is in water
	    	if (waterList.contains(e.getPlayer())) {
	    		e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 1, 1));
	    		e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 1, 1));
	    	}
	    }
	}
	
}
