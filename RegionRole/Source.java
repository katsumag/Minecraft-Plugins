package com.worldboard.custom;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import com.mewin.WGRegionEvents.events.RegionEnterEvent;
import com.mewin.WGRegionEvents.events.RegionLeaveEvent;

import me.lucko.luckperms.LuckPerms;
import me.lucko.luckperms.api.LuckPermsApi;
import me.lucko.luckperms.api.Node;
import me.lucko.luckperms.api.User;

public class regionboard extends JavaPlugin implements Listener{

	public void onEnable() {
		PluginDescriptionFile pdfFile = getDescription();
		Logger logger = getLogger();
		
		logger.info(pdfFile.getName() + " has been enabled! ");
		
		getServer().getPluginManager().registerEvents(this, this);
		
	}
	
	public void onDisable() {
		PluginDescriptionFile pdfFile = getDescription();
		Logger logger = getLogger();
		
		logger.info(pdfFile.getName() + " has been disabled! ");
	}
	

	
	@EventHandler
	public void PlayerEnterRegion(RegionEnterEvent e) {
		LuckPermsApi api = LuckPerms.getApi();
		Node node = api.getNodeFactory().makeGroupNode("grinder_game").build();
		if (e.getRegion().getId().equals("grinder0")) {
			loadUser(e.getPlayer()).setPermission(node);
			User user = loadUser(e.getPlayer());
			api.getUserManager().saveUser((user));
			e.getPlayer().sendMessage(ChatColor.AQUA +"Welcome to the Grinder Minigame!");

		}
	}
	@EventHandler
	public void PlayerLeaveRegion(RegionLeaveEvent e) {
		LuckPermsApi api = LuckPerms.getApi();
		Node node = api.getNodeFactory().makeGroupNode("grinder_game").build();
		if (e.getRegion().getId().equals("grinder0")) {
			User user = loadUser(e.getPlayer());
			user.unsetPermission(node);
			api.getUserManager().saveUser(user);
			e.getPlayer().sendMessage(ChatColor.AQUA + "You have left the Grinder Minigame!");
		}
	}
	
	
	public User loadUser(Player player) {
	    if (!player.isOnline()) {
	        throw new IllegalStateException("Player is offline");
	    }
	    LuckPermsApi api = LuckPerms.getApi(); 
	    return api.getUserManager().getUser(player.getUniqueId());
	}
	
}
