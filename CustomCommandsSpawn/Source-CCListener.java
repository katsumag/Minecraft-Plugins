package com.koofie.CustomCommands;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class CCListener implements Listener {
	
	//Constructor
	public CCListener (Main plugin) {
		
	}

	//Event Handler
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		
		Player player = (Player) event.getPlayer();
		
		player.sendMessage("Welcome to the Minigames Server!");
				
	}
	

		
	}
	
