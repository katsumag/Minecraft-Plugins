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
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class FireArmour implements Listener{
	
	public static List<ItemStack> fireArmour = CustomItem.nameArmourSet(CustomItem.getArmourSet(Material.LEATHER_HELMET, Material.LEATHER_CHESTPLATE, Material.LEATHER_LEGGINGS, Material.LEATHER_BOOTS, Color.RED), "Fire");
	public HashMap<Player, Integer> fireCount = new HashMap<>();
	public List<Player> fireList = new ArrayList<>();
	
	Plugin plugin;
	
	public static List<ItemStack> getFireArmour() {
		fireArmour.forEach(a -> a.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 10));
		return fireArmour;
	}
	
	public FireArmour(Plugin main) {
		this.plugin = main;
	}
	
	@EventHandler
	public void fireDamage(EntityDamageEvent e) {
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if (fireList.contains(p)) {
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void fireEquip(InventoryCloseEvent e) {
		
		Player p = (Player) e.getPlayer();
		
		if (fireCount.containsKey(p)) {
			fireCount.replace(p, 0);
		} else {
			fireCount.put(p, 0);
		}
		
		
		ItemStack[] armour = p.getInventory().getArmorContents();
		
		for (ItemStack item: armour) {
			if (item == null || item.getType() == Material.AIR) {
				//do nothing
			} else {
				//check against every item in fireArmour
				for (ItemStack a: fireArmour) {
					if (isSimilar(item, a)) {
						fireCount.put(p, fireCount.get(p) + 1);
					}
				}
				
			}
		}
		
		if (fireCount.get(p) == 4) {
			if (! fireList.contains(p)) {
				fireList.add(p);
			}
		} else {
			if (fireList.contains(p)) {
				fireList.remove(p);
			}
		}
		
	}
	
	@EventHandler
	public void fireEffects(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if (fireList.contains(p)) {
			p.getLocation().getBlock().setType(Material.FIRE);
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
