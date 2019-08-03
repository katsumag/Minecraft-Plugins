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
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class AirArmour extends main implements Listener{

	public List<ItemStack> airArmour = getAirArmour();
	public static List<Player> airList = new ArrayList<>();
	public HashMap<Player, Integer> airCount = new HashMap<>();
	
	public static List<ItemStack> getAirArmour(){
		List<ItemStack> items = CustomItem.nameArmourSet(CustomItem.getArmourSet(Material.LEATHER_HELMET, Material.LEATHER_CHESTPLATE, Material.LEATHER_LEGGINGS, Color.WHITE), "Air");
		ItemStack AirBoots = CustomItem.customItem(new ItemStack(Material.LEATHER_BOOTS), "Air Boots");
		AirBoots.addEnchantment(Enchantment.PROTECTION_FALL, 2);
		LeatherArmorMeta meta = (LeatherArmorMeta) AirBoots.getItemMeta();
		meta.setColor(Color.WHITE);
		AirBoots.setItemMeta(meta);
		items.add(AirBoots);
		return items;
	}
	
	@EventHandler
	public void AirEquip(InventoryCloseEvent e) {
		
		Player p = (Player) e.getPlayer();
		
		if (airCount.containsKey(p)) {
			airCount.replace(p, 0);
		} else {
			airCount.put(p, 0);
		}
		
		ItemStack[] armour = p.getInventory().getArmorContents();
		
		for (ItemStack item: armour) {
			if (item == null || item.getType() == Material.AIR) {
				//do nothing
			} else {
				
				//check against every item in airArmour
				for (ItemStack a: airArmour) {
					if (item == a) {
						airCount.put(p, airCount.get(p) + 1);
					}
				}
				
			}
		}
		
		if (airCount.get(p) == 4) {
			if (! airList.contains(p)) {
				airList.add(p);
				p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 5));
			}
		} else {
			if (airList.contains(p)) {
				airList.remove(p);
				p.removePotionEffect(PotionEffectType.SPEED);
			}
		}
		
	}
	
}
