package DoD;

import java.util.Random;
import java.util.logging.Logger;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class dropOnDeath extends JavaPlugin implements Listener{

	@Override
	public void onEnable() {

		PluginDescriptionFile pdfFile = getDescription();
		Logger logger = getLogger();
		logger.info(pdfFile.getName() + " has been enabled! ");
		getServer().getPluginManager().registerEvents(this, this);
	}

	@Override
	public void onDisable() {

		PluginDescriptionFile pdfFile = getDescription();
		Logger logger = getLogger();
		logger.info(pdfFile.getName() + " has been disabled! ");

	}

	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		Random rand = new Random();
		int first = rand.nextInt(36);
		int second = rand.nextInt(36);

		while (first == second) {
			second = rand.nextInt(36);

		}

		e.getEntity().getWorld().dropItemNaturally(e.getEntity().getLocation(), e.getEntity().getInventory().getItem(first));
		e.getEntity().getWorld().dropItemNaturally(e.getEntity().getLocation(), e.getEntity().getInventory().getItem(second));

		e.getEntity().getInventory().clear(first);
		e.getEntity().getInventory().clear(second);

	}

}
