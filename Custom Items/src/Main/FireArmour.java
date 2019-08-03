package Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

public class FireArmour extends main implements Listener{
	
	public static List<ItemStack> fireArmour = CustomItem.nameArmourSet(CustomItem.getArmourSet(Material.LEATHER_HELMET, Material.LEATHER_CHESTPLATE, Material.LEATHER_LEGGINGS, Material.LEATHER_BOOTS, Color.RED), "Fire");
	public HashMap<Player, Integer> fireCount = new HashMap<>();
	public List<Player> fireList = new ArrayList<>();
	
	
	public static List<ItemStack> getFireArmour() {
		fireArmour.forEach(a -> a.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 10));
		return fireArmour;
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
					if (item == a) {
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
	
	public void fireEffects(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if (fireList.contains(p)) {
			p.getLocation().getBlock().getRelative(BlockFace.UP).setType(Material.FIRE);
		}
	}
	
}
