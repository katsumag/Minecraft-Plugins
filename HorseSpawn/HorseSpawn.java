package com.MobSpawn.custom;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftAbstractHorse;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftHorse;
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
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.vehicle.VehicleEnterEvent;
import org.bukkit.inventory.HorseInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import net.minecraft.server.v1_12_R1.EntityHorse;
import net.minecraft.server.v1_12_R1.EntityHorseAbstract;
import net.minecraft.server.v1_12_R1.GenericAttributes;

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
				if (spaceCheck(player) == true) {
				Horse h = (Horse) player.getWorld().spawnEntity(player.getLocation(), EntityType.HORSE);
				h.setTamed(true);
				h.setOwner(player);
				h.getInventory().setSaddle(new ItemStack(Material.SADDLE, 1));
				h.setAdult();
				EntityHorse horse = ((CraftHorse) h).getHandle();
				horse.getAttributeInstance(GenericAttributes.h).setValue(1.42);
				nameHorse(player, h);
				} else {
				}
				} else {
				if (args.length >= 1) {
				if (args[0].equals("black")) {
					if (spaceCheck(player) == false) {
						break;
					} else {
					Horse h = (Horse) player.getWorld().spawnEntity(player.getLocation(), EntityType.HORSE);
					h.setTamed(true);
					h.setOwner(player);
					h.getInventory().setSaddle(new ItemStack(Material.SADDLE, 45));
					h.setAdult();
					h.setStyle(Style.NONE);
					h.setColor(Color.BLACK);
					EntityHorse horse = ((CraftHorse) h).getHandle();
					horse.getAttributeInstance(GenericAttributes.h).setValue(1.42);
					nameHorse(player, h);
					return true;
					}
				
				}
				if (args[0].equals("brown")) {
					if (spaceCheck(player) == false) {
						break;
					} else {
					Horse h = (Horse) player.getWorld().spawnEntity(player.getLocation(), EntityType.HORSE);
					h.setTamed(true);
					h.setOwner(player);
					h.getInventory().setSaddle(new ItemStack(Material.SADDLE, 1));
					h.setAdult();
					h.setStyle(Style.NONE);
					h.setColor(Color.BROWN);
					EntityHorse horse = ((CraftHorse) h).getHandle();
					horse.getAttributeInstance(GenericAttributes.h).setValue(1.42);
					nameHorse(player, h);
					return true;
					}
				}
				if (args[0].equals("chestnut")) {
					if (spaceCheck(player) == false) {
						break;
					} else {
					Horse h = (Horse) player.getWorld().spawnEntity(player.getLocation(), EntityType.HORSE);
					h.setTamed(true);
					h.setOwner(player);
					h.getInventory().setSaddle(new ItemStack(Material.SADDLE, 1));
					h.setAdult();
					h.setStyle(Style.NONE);
					h.setColor(Color.CHESTNUT);
					EntityHorse horse = ((CraftHorse) h).getHandle();
					horse.getAttributeInstance(GenericAttributes.h).setValue(1.42);
					nameHorse(player, h);
					h.addScoreboardTag("Chestnut");
					return true;
					}
				}
				if (args[0].equals("cream")) {
					if (spaceCheck(player) == false) {
						break;
					} else {
					Horse h = (Horse) player.getWorld().spawnEntity(player.getLocation(), EntityType.HORSE);
					h.setTamed(true);
					h.setOwner(player);
					h.getInventory().setSaddle(new ItemStack(Material.SADDLE, 1));
					h.setAdult();
					h.setStyle(Style.NONE);
					h.setColor(Color.CREAMY);
					EntityHorse horse = ((CraftHorse) h).getHandle();
					horse.getAttributeInstance(GenericAttributes.h).setValue(1.42);
					nameHorse(player, h);
					return true;
					}
			    }
				if (args[0].equals("darkBrown")) {
					if (spaceCheck(player) == false) {
						break;
					} else {
					Horse h = (Horse) player.getWorld().spawnEntity(player.getLocation(), EntityType.HORSE);
					h.setTamed(true);
					h.setOwner(player);
					h.getInventory().setSaddle(new ItemStack(Material.SADDLE, 1));
					h.setAdult();
					h.setStyle(Style.NONE);
					h.setColor(Color.DARK_BROWN);
					EntityHorse horse = ((CraftHorse) h).getHandle();
					horse.getAttributeInstance(GenericAttributes.h).setValue(1.42);
					nameHorse(player, h);
					return true;
					}
				}
				if (args[0].equals("grey")) {
					if (spaceCheck(player) == false) {
						break;
					} else {
					Horse h = (Horse) player.getWorld().spawnEntity(player.getLocation(), EntityType.HORSE);
					h.setTamed(true);
					h.setOwner(player);
					h.getInventory().setSaddle(new ItemStack(Material.SADDLE, 1));
					h.setAdult();
					h.setStyle(Style.NONE);
					h.setColor(Color.GRAY);
					EntityHorse horse = ((CraftHorse) h).getHandle();
					horse.getAttributeInstance(GenericAttributes.h).setValue(1.42);
					nameHorse(player, h);
					return true;
					}
				}
				if (args[0].equals("white")) {
					if (spaceCheck(player) == false) {
						break;
					} else {
					Horse h = (Horse) player.getWorld().spawnEntity(player.getLocation(), EntityType.HORSE);
					h.setTamed(true);
					h.setOwner(player);
					h.getInventory().setSaddle(new ItemStack(Material.SADDLE, 1));
					h.setAdult();
					h.setStyle(Style.NONE);
					h.setColor(Color.WHITE);
					EntityHorse horse = ((CraftHorse) h).getHandle();
					horse.getAttributeInstance(GenericAttributes.h).setValue(1.42);
					nameHorse(player, h);
					return true;
					}
				}
				
				if (args[0].equals("ChI")) {
					if (spaceCheck(player) == false) {
						break;
					} else {
					Horse h = (Horse) player.getWorld().spawnEntity(player.getLocation(), EntityType.HORSE);
					h.setTamed(true);
					h.setOwner(player);
					h.getInventory().setSaddle(new ItemStack(Material.SADDLE, 1));
					h.setAdult();
					h.setStyle(Style.NONE);
					h.setColor(Color.CHESTNUT);
					h.getInventory().setArmor(new ItemStack(Material.IRON_BARDING));
					EntityHorse horse = ((CraftHorse) h).getHandle();
					horse.getAttributeInstance(GenericAttributes.h).setValue(1.2);
					nameHorse(player, h);
					return true;
					}
				}
				
				if (args[0].equals("GrayD")) {
					if (spaceCheck(player) == false) {
						break;
					} else {
					Horse h = (Horse) player.getWorld().spawnEntity(player.getLocation(), EntityType.HORSE);
					h.setTamed(true);
					h.setOwner(player);
					h.getInventory().setSaddle(new ItemStack(Material.SADDLE, 1));
					h.setAdult();
					h.setStyle(Style.NONE);
					h.setColor(Color.GRAY);
					h.getInventory().setArmor(new ItemStack(Material.DIAMOND_BARDING));
					EntityHorse horse = ((CraftHorse) h).getHandle();
					horse.getAttributeInstance(GenericAttributes.h).setValue(1.42);
					nameHorse(player, h);
					return true;
					}
				}
				
				if (args[0].equals("Skeleton")) {
					if (spaceCheck(player) == false) {
						break;
					} else {
					AbstractHorse h = (AbstractHorse) player.getWorld().spawnEntity(player.getLocation(), EntityType.SKELETON_HORSE);
					h.setTamed(true);
					h.setOwner(player);
					h.getInventory().setSaddle(new ItemStack(Material.SADDLE, 1));
					h.setAdult();
					EntityHorseAbstract horse = ((CraftAbstractHorse) h).getHandle();
					horse.getAttributeInstance(GenericAttributes.h).setValue(1.42);
					nameCustomHorse(player, h);
					return true;
					}
				}
				
				if (args[0].equals("Zombie")) {
					if (spaceCheck(player) == false) {
						break;
					} else {
					AbstractHorse h = (AbstractHorse) player.getWorld().spawnEntity(player.getLocation(), EntityType.ZOMBIE_HORSE);
					h.setTamed(true);
					h.setOwner(player);
					h.getInventory().setSaddle(new ItemStack(Material.SADDLE, 1));
					h.setAdult();
					EntityHorseAbstract horse = ((CraftAbstractHorse) h).getHandle();
					horse.getAttributeInstance(GenericAttributes.h).setValue(1.42);
					nameCustomHorse(player, h);
					return true;
					}
				}
				
				if (args[0].equals("CustomWhite")) {
					if (spaceCheck(player) == false) {
						break;
					} else {
					Horse h = (Horse) player.getWorld().spawnEntity(player.getLocation(), EntityType.HORSE);
					h.setTamed(true);
					h.setOwner(player);
					h.getInventory().setSaddle(new ItemStack(Material.SADDLE, 1));
					h.setAdult();
					h.setStyle(Style.NONE);
					h.setColor(Color.WHITE);
					h.getInventory().setArmor(new ItemStack(Material.GOLD_BARDING));
					EntityHorse horse = ((CraftHorse) h).getHandle();
					horse.getAttributeInstance(GenericAttributes.h).setValue(1.42);
					nameHorse(player, h);
					h.addScoreboardTag("CustomWhite");
					return true;
					}
				}
				
				if (args[0].equals("CustomBlack")) {
					if (spaceCheck(player) == false) {
						break;
					} else {
					Horse h = (Horse) player.getWorld().spawnEntity(player.getLocation(), EntityType.HORSE);
					h.setTamed(true);
					h.setOwner(player);
					h.getInventory().setSaddle(new ItemStack(Material.SADDLE, 1));
					h.setAdult();
					h.setStyle(Style.NONE);
					h.setColor(Color.BLACK);
					EntityHorse horse = ((CraftHorse) h).getHandle();
					horse.getAttributeInstance(GenericAttributes.h).setValue(1.32);
					nameHorse(player, h);
					h.addScoreboardTag("CustomBlack");					
					return true;
					}
				}
				
			}
			}
		}
		}
		return true;
			
	}
	public boolean tf = true;
	public boolean spaceCheck(Player player) {
		tf = true;
		List<Block> done = new ArrayList<>();
		List<Block> list = GetBlocksInCube(player.getLocation(), 2, 0, 2);
		list.forEach(block -> {
			done.add(block);
			done.add(block.getLocation().add(new Vector(0, 1, 0)).getBlock());
		});
		done.forEach(block -> {
			if (block.getType() != Material.AIR) {
				tf = false;
			}
		});
		return tf;
	}
	
	public static List<Block> GetBlocksInCube(Location middle, int _x, int _y, int _z) {
        List<Block> selectedBlocks = new ArrayList<>();

        Location iterator = middle.clone();
        iterator.subtract(new Vector(_x, _y, _z));
        Location reset_point = iterator.clone();

        // LoopSize
        int loopsizeX = _x * 2 + 1,
            loopsizeY = _y * 2 + 1,
            loopsizeZ = _z * 2 + 1;

        for ( int x = 0; x < loopsizeX; x++ ) {
            iterator.setX(reset_point.getX() + x );
            for ( int y = 0; y < loopsizeY; y++ ) {
                iterator.setY(reset_point.getY() + y );
                for ( int z = 0; z < loopsizeZ; z++ ) {
                    iterator.setZ(reset_point.getZ() + z );
                    Block sel = middle.getWorld().getBlockAt(iterator);
                    selectedBlocks.add(sel);
                }
                iterator.setZ(reset_point.getZ());
            }
            iterator.setY(reset_point.getY());
        }

        return selectedBlocks;
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
		h.setCustomNameVisible(true);
	}
	
	public void nameCustomHorse(Player player, AbstractHorse h) {
		String name = player.toString();
		//System.out.println(name);
		String finishedname = trimHorseName(name);
		h.setCustomName(finishedname + "'s Horse");
		h.setCustomNameVisible(true);
	}	
	
	@EventHandler
	public void worldChange(PlayerChangedWorldEvent e) {
		List<Entity> count = e.getFrom().getLivingEntities().stream().filter(d -> d instanceof Horse && e.getPlayer() == ((Horse)d).getOwner()).collect(Collectors.toList());
		List<Entity> count1 = e.getFrom().getLivingEntities().stream().filter(d -> d instanceof AbstractHorse && e.getPlayer() == ((Horse)d).getOwner()).collect(Collectors.toList());
		count.forEach(entity -> entity.remove());
		count1.forEach(entity -> entity.remove());
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
					return;
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
						return;
					}
			}
		}
				}
		if (h.getType().equals(EntityType.SKELETON_HORSE)) {
			if (!(h.getCustomName().equals(null))) {
				String name = h.getCustomName();
				String pass1[] = name.split("'");
				String newhorsename = pass1[0];
				newhorsename.replaceAll("\\s+", "");
				if (newhorsename.equalsIgnoreCase(p.getName())) {
					
				} else {
					e.setCancelled(true);
					p.sendMessage(ChatColor.RED + "You do not own this Horse!");
					return;
				}
		}
		}
		
		if (h.getType().equals(EntityType.ZOMBIE_HORSE)) {
			if (!(h.getCustomName().equals(null))) {
				String name = h.getCustomName();
				String pass1[] = name.split("'");
				String newhorsename = pass1[0];
				newhorsename.replaceAll("\\s+", "");
				if (newhorsename.equalsIgnoreCase(p.getName())) {
					
				} else {
					e.setCancelled(true);
					p.sendMessage(ChatColor.RED + "You do not own this Horse!");
					return;
				}
		}
		}
		
		if (!(h.getCustomName().equals(null))) {
			String name = h.getCustomName();
			String pass1[] = name.split("'");
			String newhorsename = pass1[0];
			newhorsename.replaceAll("\\s+", "");
			if (newhorsename.equalsIgnoreCase(p.getName())) {
				
			} else {
				e.setCancelled(true);
				p.sendMessage(ChatColor.RED + "You do not own this Horse!");
				return;
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
			return;
		}
		
		if (e.getInventory().getHolder() instanceof Horse) {
			Horse horse = (Horse) e.getInventory().getHolder();
		if (horse.getCustomName()!= null) {
			e.setCancelled(true);
		}
		}

		if (e.getInventory().getHolder() instanceof SkeletonHorse) {
			AbstractHorse horse = (AbstractHorse) e.getInventory().getHolder();
			if (horse.getCustomName() != null) {
				e.setCancelled(true);
			}
		}
		
		if (e.getInventory().getHolder() instanceof ZombieHorse) {
			AbstractHorse horse = (AbstractHorse) e.getInventory().getHolder();
			if (horse.getCustomName() != null) {
				e.setCancelled(true);
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