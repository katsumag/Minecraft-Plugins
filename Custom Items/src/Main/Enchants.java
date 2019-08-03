package Main;

import org.bukkit.craftbukkit.Main;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class Enchants extends Enchantment implements Listener {

	Main plugin;
	
	public Enchants(int id) {
		super(id);
	}
	@Override
	public int getId() {
		return 460;
	}
	@Override
	public boolean canEnchantItem(ItemStack arg0) {
		if (CustomItem.isArmour(arg0) == true) {
			return true;
		}
		return false;
	}
	@Override
	public boolean conflictsWith(Enchantment arg0) {
		return false;
	}
	@Override
	public EnchantmentTarget getItemTarget() {
		return null;
	}
	@Override
	public int getMaxLevel() {
		return 5;
	}
	@Override
	public String getName() {
		return "Health";
	}
	@Override
	public int getStartLevel() {
		return 1;
	}
}


