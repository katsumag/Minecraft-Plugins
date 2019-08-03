package Main;

import java.util.List;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class EmeraldArmour extends main implements Listener{

	List<ItemStack> emeraldArmour = getEmeraldArmour();
	
	public static List<ItemStack> getEmeraldArmour() {
		return CustomItem.nameArmourSet(CustomItem.getArmourSet(Material.LEATHER_HELMET, Material.LEATHER_CHESTPLATE, Material.LEATHER_HELMET, Material.LEATHER_BOOTS, Color.GREEN), "Emerald");
	}
	
}
