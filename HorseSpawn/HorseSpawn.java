package com.MobSpawn.custom;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Horse.Color;
import org.bukkit.entity.Horse.Style;
import org.bukkit.entity.Player;
import org.bukkit.entity.SkeletonHorse;
import org.bukkit.entity.ZombieHorse;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.vehicle.VehicleEnterEvent;
import org.bukkit.inventory.HorseInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class HorseSpawn extends JavaPlugin implements Listener{

	public void onEnable(){
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
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player player = (Player) sender;
		if (sender instanceof Player) {
			String lowercmd = cmd.getName().toLowerCase();
			switch(lowercmd) {
			
			case "horse":
				try {
					List<Entity> count = player.getWorld().getLivingEntities().stream().filter(d -> d instanceof Horse && player == ((Horse)d).getOwner()).collect(Collectors.toList());
					if (!(count.isEmpty())) {
						count.forEach(enitity -> enitity.remove());
						return true;
					} else {
						
					}
					List<Entity> count1 = player.getWorld().getLivingEntities().stream().filter(d -> d instanceof AbstractHorse && player == ((AbstractHorse)d).getOwner()).collect(Collectors.toList());
					if (!(count1.isEmpty())) {
						count1.forEach(entity -> entity.remove());
						return true;
					}
				} catch (NullPointerException exception){
					//exception.printStackTrace();
				}
				if (args.length == 0) {
				Horse h = (Horse) player.getWorld().spawnEntity(player.getLocation(), EntityType.HORSE);
				h.setTamed(true);
				h.setOwner(player);
				h.getInventory().setSaddle(new ItemStack(Material.SADDLE, 1));
				h.setAdult();
				nameHorse(player, h);
				} else {
				if (args.length >= 1) {
				if (args[0].equals("black")) {
					Horse h = (Horse) player.getWorld().spawnEntity(player.getLocation(), EntityType.HORSE);
					h.setTamed(true);
					h.setOwner(player);
					h.getInventory().setSaddle(new ItemStack(Material.SADDLE, 1));
					h.setAdult();
					h.setStyle(Style.NONE);
					h.setColor(Color.BLACK);
					nameHorse(player, h);
					return true;
				}
				if (args[0].equals("brown")) {
					Horse h = (Horse) player.getWorld().spawnEntity(player.getLocation(), EntityType.HORSE);
					h.setTamed(true);
					h.setOwner(player);
					h.getInventory().setSaddle(new ItemStack(Material.SADDLE, 1));
					h.setAdult();
					h.setStyle(Style.NONE);
					h.setColor(Color.BROWN);
					nameHorse(player, h);
					return true;
				}
				if (args[0].equals("chestnut")) {
					Horse h = (Horse) player.getWorld().spawnEntity(player.getLocation(), EntityType.HORSE);
					h.setTamed(true);
					h.setOwner(player);
					h.getInventory().setSaddle(new ItemStack(Material.SADDLE, 1));
					h.setAdult();
					h.setStyle(Style.NONE);
					h.setColor(Color.CHESTNUT);
					nameHorse(player, h);
					return true;
				}
				if (args[0].equals("cream")) {
					Horse h = (Horse) player.getWorld().spawnEntity(player.getLocation(), EntityType.HORSE);
					h.setTamed(true);
					h.setOwner(player);
					h.getInventory().setSaddle(new ItemStack(Material.SADDLE, 1));
					h.setAdult();
					h.setStyle(Style.NONE);
					h.setColor(Color.CREAMY);
					nameHorse(player, h);
					return true;
			    }
				if (args[0].equals("darkBrown")) {
					Horse h = (Horse) player.getWorld().spawnEntity(player.getLocation(), EntityType.HORSE);
					h.setTamed(true);
					h.setOwner(player);
					h.getInventory().setSaddle(new ItemStack(Material.SADDLE, 1));
					h.setAdult();
					h.setStyle(Style.NONE);
					h.setColor(Color.DARK_BROWN);
					nameHorse(player, h);
					return true;
				}
				if (args[0].equals("grey")) {
					Horse h = (Horse) player.getWorld().spawnEntity(player.getLocation(), EntityType.HORSE);
					h.setTamed(true);
					h.setOwner(player);
					h.getInventory().setSaddle(new ItemStack(Material.SADDLE, 1));
					h.setAdult();
					h.setStyle(Style.NONE);
					h.setColor(Color.GRAY);
					nameHorse(player, h);
					return true;
				}
				if (args[0].equals("white")) {
					Horse h = (Horse) player.getWorld().spawnEntity(player.getLocation(), EntityType.HORSE);
					h.setTamed(true);
					h.setOwner(player);
					h.getInventory().setSaddle(new ItemStack(Material.SADDLE, 1));
					h.setAdult();
					h.setStyle(Style.NONE);
					h.setColor(Color.WHITE);
					nameHorse(player, h);
					return true;
				}
				
				if (args[0].equals("ChI")) {
					Horse h = (Horse) player.getWorld().spawnEntity(player.getLocation(), EntityType.HORSE);
					h.setTamed(true);
					h.setOwner(player);
					h.getInventory().setSaddle(new ItemStack(Material.SADDLE, 1));
					h.setAdult();
					h.setStyle(Style.NONE);
					h.setColor(Color.CHESTNUT);
					h.getInventory().setArmor(new ItemStack(Material.IRON_BARDING));
					nameHorse(player, h);
					return true;
				}
				
				if (args[0].equals("GrayD")) {
					Horse h = (Horse) player.getWorld().spawnEntity(player.getLocation(), EntityType.HORSE);
					h.setTamed(true);
					h.setOwner(player);
					h.getInventory().setSaddle(new ItemStack(Material.SADDLE, 1));
					h.setAdult();
					h.setStyle(Style.NONE);
					h.setColor(Color.GRAY);
					h.getInventory().setArmor(new ItemStack(Material.DIAMOND_BARDING));
					nameHorse(player, h);
					return true;
				}
				
				if (args[0].equals("Skeleton")) {
					AbstractHorse h = (AbstractHorse) player.getWorld().spawnEntity(player.getLocation(), EntityType.SKELETON_HORSE);
					h.setTamed(true);
					h.setOwner(player);
					h.getInventory().setSaddle(new ItemStack(Material.SADDLE, 1));
					h.setAdult();
					nameCustomHorse(player, h);
					return true;
				}
				
				if (args[0].equals("Zombie")) {
					AbstractHorse h = (AbstractHorse) player.getWorld().spawnEntity(player.getLocation(), EntityType.ZOMBIE_HORSE);
					h.setTamed(true);
					h.setOwner(player);
					h.getInventory().setSaddle(new ItemStack(Material.SADDLE, 1));
					h.setAdult();
					nameCustomHorse(player, h);
					return true;
				}
				
				if (args[0].equals("CustomWhite")) {
					Horse h = (Horse) player.getWorld().spawnEntity(player.getLocation(), EntityType.HORSE);
					h.setTamed(true);
					h.setOwner(player);
					h.getInventory().setSaddle(new ItemStack(Material.SADDLE, 1));
					h.setAdult();
					h.setStyle(Style.NONE);
					h.setColor(Color.WHITE);
					h.getInventory().setArmor(new ItemStack(Material.GOLD_BARDING));
					nameHorse(player, h);
					final Runnable effectStart = new Runnable() {
						public void run() {
							if (!(h.isDead())) {
							h.getWorld().playEffect(h.getLocation(), Effect.FIREWORKS_SPARK, null);
						}
						}
					};
					
					player.getServer().getScheduler().scheduleAsyncRepeatingTask(this, effectStart, 20, 1);

				}
				
				if (args[0].equals("CustomBlack")) {
					Horse h = (Horse) player.getWorld().spawnEntity(player.getLocation(), EntityType.HORSE);
					h.setTamed(true);
					h.setOwner(player);
					h.getInventory().setSaddle(new ItemStack(Material.SADDLE, 1));
					h.setAdult();
					h.setStyle(Style.NONE);
					h.setColor(Color.BLACK);
					nameHorse(player, h);
					final Runnable effectStart = new Runnable() {
						public void run(){
							if (!(h.isDead())) {
								h.getWorld().playEffect(h.getLocation(), Effect.FLAME, null);
							}
						}
					};
					
					player.getServer().getScheduler().scheduleAsyncRepeatingTask(this, effectStart, 20, 1);
					
					return true;
				}
				
			}
			}
		}
		}
		return true;
			
	}
	
	public String trimHorseName(String str) {
		
		String[] pass1 = str.split("name=");
		String begin2 = pass1[1];
		//System.out.println(begin2);
		String pass2[] = begin2.split("\\}");
		String out = pass2[0];
		//System.out.println(out);
		return out;
	}
	
	public void nameHorse(Player player, Horse h) {
		String name = player.toString();
		//System.out.println(name);
		String finishedname = trimHorseName(name);
		h.setCustomName(finishedname + "'s Horse");
	}
	
	public void nameCustomHorse(Player player, AbstractHorse h) {
		String name = player.toString();
		//System.out.println(name);
		String finishedname = trimHorseName(name);
		h.setCustomName(finishedname + "'s Horse");
	}	
	
	@EventHandler
	public void RideHorse(VehicleEnterEvent e) {
		Entity h = e.getVehicle();
		Player p = (Player) e.getEntered();
		if (h instanceof Horse) {
			if (p instanceof Player) {
				if (!(h.getCustomName().equals(null))) {
				String name = h.getCustomName();
				String pass1[] = name.split("'");
				String newhorsename = pass1[0];
				newhorsename.replaceAll("\\s+", "");
				//System.out.println(newhorsename);
				//System.out.println(p.getName());
				if (newhorsename.equalsIgnoreCase(p.getName())) {
				} else {
					e.setCancelled(true);
					p.sendMessage(ChatColor.RED + "You do not own this Horse!");
				}
				} 
		if (h instanceof AbstractHorse) {
			if (p instanceof Player) {
				if (!(h.getCustomName().equals(null))) {
					String name = h.getCustomName();
					String pass1[] = name.split("'");
					String newhorsename = pass1[0];
					newhorsename.replaceAll("\\s+", "");
					if (newhorsename.equalsIgnoreCase(p.getName())) {
						
					} else {
						e.setCancelled(true);
						p.sendMessage(ChatColor.RED + "You do not own this Horse!");
					}
			}
		}
				}
			}
		}
	}
	//^ was all random horse generation and riding protection
	//start of inventory protection
	@EventHandler
	public void horseInvOpen(InventoryOpenEvent e) {

		if (e.getInventory() instanceof HorseInventory) {
			e.setCancelled(true);
			e.getPlayer().sendMessage(ChatColor.RED + "You cannot open horses inventories that are generated by the /horse command!");
			return;
		}
		
		Player player = (Player) e.getPlayer();
		if (e.getInventory().getHolder() instanceof Horse) {
			Horse horse = (Horse) e.getInventory().getHolder();
		if (horse.getCustomName()!= null) {
			e.setCancelled(true);
			player.sendMessage(ChatColor.RED + "You cannot open horses inventories that are generated by the /horse command!");
		}
		}

		if (e.getInventory().getHolder() instanceof SkeletonHorse) {
			AbstractHorse horse = (AbstractHorse) e.getInventory().getHolder();
			if (horse.getCustomName() != null) {
				e.setCancelled(true);
				player.sendMessage(ChatColor.RED + "You cannot open horses inventories that are generated by the /horse command!");
			}
		}
		
		if (e.getInventory().getHolder() instanceof ZombieHorse) {
			AbstractHorse horse = (AbstractHorse) e.getInventory().getHolder();
			if (horse.getCustomName() != null) {
				e.setCancelled(true);
				player.sendMessage(ChatColor.RED + "You cannot open horses inventories that are generated by the /horse command!");
			}
		}
	}

	@EventHandler
	public void horseDieOnDrop(PlayerDropItemEvent e) {
		Player player = e.getPlayer();
		if (player.getVehicle() != null) {
			if (player.getVehicle().getType().equals(EntityType.HORSE)) {
				player.getVehicle().remove();
			}
			if (player.getVehicle().getType().equals(EntityType.SKELETON_HORSE)) {
				player.getVehicle().remove();
			}
			if (player.getVehicle().getType().equals(EntityType.ZOMBIE_HORSE)) {
				player.getVehicle().remove();
			}
		}
		
		List<Entity> count = player.getWorld().getLivingEntities().stream().filter(d -> d instanceof Horse && player == ((Horse)d).getOwner()).collect(Collectors.toList());
		if (!(count.isEmpty())) {
			count.forEach(entity -> entity.remove());
		}
		List<Entity> count1 = player.getWorld().getLivingEntities().stream().filter(d -> d instanceof AbstractHorse && player == ((AbstractHorse)d).getOwner()).collect(Collectors.toList());
		if (!(count1.isEmpty())) {
			count1.forEach(entity -> entity.remove());
		}
	}
	
	@EventHandler
	public void despawnHorseOnInventory(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (p.getVehicle() == null) {
			List<Entity> count = p.getWorld().getLivingEntities().stream().filter(d -> d instanceof Horse && p == ((Horse)d).getOwner()).collect(Collectors.toList());
			if (!(count.isEmpty())) {
				count.forEach(entity -> entity.remove());
			}
			
			List<Entity> count1 = p.getWorld().getLivingEntities().stream().filter(d -> d instanceof AbstractHorse && p == ((AbstractHorse)d).getOwner()).collect(Collectors.toList());
			if (!(count1.isEmpty())) {
				count1.forEach(entity -> entity.remove());
			}
		}
		
	}
	
	@EventHandler
	public void invincibleHorses(EntityDamageEvent e) {
		if (e.getEntity().getType().equals(EntityType.HORSE)) {
			e.setCancelled(true);
		}
		if (e.getEntity().getType().equals(EntityType.SKELETON_HORSE)) {
			e.setCancelled(true);
		}
		if (e.getEntity().getType().equals(EntityType.ZOMBIE_HORSE)) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void despawnOnLogout(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		List<Entity> count = p.getWorld().getLivingEntities().stream().filter(d -> d instanceof Horse && p == ((Horse)d).getOwner()).collect(Collectors.toList());
		if (!(count.isEmpty())) {
			count.forEach(entity -> entity.remove());
		}
		List<Entity> count1 = p.getWorld().getLivingEntities().stream().filter(d -> d instanceof AbstractHorse && p == ((AbstractHorse)d).getOwner()).collect(Collectors.toList());
		if (!(count1.isEmpty())) {
			count1.forEach(entity -> entity.remove());
		}
	}
	
}
