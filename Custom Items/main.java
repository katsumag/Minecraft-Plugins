package Main;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_9_R1.entity.CraftArrow;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Dye;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import Utils.*;
import net.minecraft.server.v1_9_R1.EntityArrow;

public class main extends JavaPlugin implements Listener{
	
	public void onEnable(){
		PluginDescriptionFile pdfFile = getDescription();
		Logger logger = getLogger();
		logger.info(pdfFile.getName() + " has been enabled! ");
		getServer().getPluginManager().registerEvents(this, this);
		LoadEnchantments();
		customRecipe();
	}
	
	@SuppressWarnings({ "unchecked", "unlikely-arg-type" })
	public void onDisable() {
		PluginDescriptionFile pdfFile = getDescription();
		Logger logger = getLogger();
		logger.info(pdfFile.getName() + " has been disabled! ");
		try {
			Field byIdField = Enchantment.class.getDeclaredField("byId");
			Field byNameField = Enchantment.class.getDeclaredField("byName");
			byIdField.setAccessible(true);
			byNameField.setAccessible(true);
			HashMap<Integer, Enchantment> byId = (HashMap<Integer, Enchantment>) byIdField.get(null);
			HashMap<Integer, Enchantment> byName = (HashMap<Integer, Enchantment>) byNameField.get(null);
			
			if(byId.containsKey(health.getId())) {
				byId.remove(health.getId());
			}
			
			if(byName.containsKey(health.getName())) {
				byName.remove(health.getName());
			}
			
		} catch (Exception ignored) {
			
		}
	}
	
	private void LoadEnchantments() {
		try {
				Field f = Enchantment.class.getDeclaredField("acceptingNew");
				f.setAccessible(true);
				f.set(null, true);
				f.setAccessible(false);
				Enchantment.registerEnchantment(health);
				Enchantment.stopAcceptingRegistrations();
			} catch (Exception ignored) {
			}
		
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		switch(cmd.getName().toLowerCase()) {
		case "give":
		}
		return true;
	}
	public Enchants health = new Enchants(460);
	public ItemStack healthBook = CustomItem.makeBook(new ItemStack(Material.ENCHANTED_BOOK), health, 1, "Health I");
	public ItemStack healthBook2 = CustomItem.makeBook(new ItemStack(Material.ENCHANTED_BOOK), health, 2, "Health II");
	public ItemStack healthBook3 = CustomItem.makeBook(new ItemStack(Material.ENCHANTED_BOOK), health, 3, "Health III");
	public ItemStack healthBook4 = CustomItem.makeBook(new ItemStack(Material.ENCHANTED_BOOK), health, 4, "Health IV");
	public ItemStack healthBook5 = CustomItem.makeBook(new ItemStack(Material.ENCHANTED_BOOK), health, 5, "Health V");
	public ItemStack gH = CustomItem.customItem(new ItemStack(Material.FISHING_ROD), "Grappling Hook");
	public HashMap<Player, Integer> fireCount = new HashMap<>();
	public List<Player> fireList = new ArrayList<>();
	public HashMap<Player, Integer> waterCount = new HashMap<>();
	public List<Player> waterList = new ArrayList<>();
	public HashMap<Player, Integer> earthCount = new HashMap<>();
	public List<Player> earthList = new ArrayList<>();
	public HashMap<Player, Integer> airCount = new HashMap<>();
	public List<Player> airList = new ArrayList<>();
	public HashMap<Player, Integer> emeraldCount = new HashMap<>();
	public List<Player> emeraldList = new ArrayList<>();
	public ItemStack fireRod = CustomItem.customItem(new ItemStack(Material.BLAZE_ROD), "Flame Rod", null);
	public ItemMeta rodMeta = fireRod.getItemMeta();
	public ItemStack tpBow = CustomItem.customItem(new ItemStack(Material.BOW), "Ender Bow", null);
	public ItemMeta tpMeta = tpBow.getItemMeta();
	
	public void customRecipe() {
		
		List<ItemStack> emeraldArmour = CustomItem.nameArmourSet(CustomItem.getArmourSet(Material.LEATHER_BOOTS, Material.LEATHER_CHESTPLATE, Material.LEATHER_HELMET, Material.LEATHER_LEGGINGS, Color.GREEN), "Emerald");
		List<ItemStack> fireArmour = CustomItem.nameArmourSet(CustomItem.getArmourSet(Material.LEATHER_BOOTS, Material.LEATHER_CHESTPLATE, Material.LEATHER_HELMET, Material.LEATHER_LEGGINGS, Color.RED), "Fire");
		List<ItemStack> waterArmour = CustomItem.nameArmourSet(CustomItem.getArmourSet(Material.LEATHER_BOOTS, Material.LEATHER_CHESTPLATE, Material.LEATHER_HELMET, Material.LEATHER_LEGGINGS, Color.AQUA), "Water");
		List<ItemStack> earthArmour = CustomItem.nameArmourSet(CustomItem.getArmourSet(Material.LEATHER_BOOTS, Material.LEATHER_CHESTPLATE, Material.LEATHER_HELMET, Material.LEATHER_LEGGINGS, Color.GREEN), "Earth");
		List<ItemStack> airArmour = CustomItem.nameArmourSet(CustomItem.getArmourSet(Material.LEATHER_BOOTS, Material.LEATHER_CHESTPLATE, Material.LEATHER_HELMET, Material.LEATHER_LEGGINGS, Color.WHITE), "Air");
		
		//fire recipies
		ShapedRecipe fH = new ShapedRecipe(fireArmour.get(2));
		ShapedRecipe fC = new ShapedRecipe(fireArmour.get(1));
		ShapedRecipe fL = new ShapedRecipe(fireArmour.get(3));
		ShapedRecipe fB = new ShapedRecipe(fireArmour.get(0));
		//water recipie
		ShapedRecipe wH = new ShapedRecipe(waterArmour.get(2));
		ShapedRecipe wC = new ShapedRecipe(waterArmour.get(1));
		ShapedRecipe wL = new ShapedRecipe(waterArmour.get(3));
		ShapedRecipe wB = new ShapedRecipe(waterArmour.get(0));
		//earth recipie
		ShapedRecipe eH = new ShapedRecipe(earthArmour.get(2));
		ShapedRecipe eC = new ShapedRecipe(earthArmour.get(1));
		ShapedRecipe eL = new ShapedRecipe(earthArmour.get(3));
		ShapedRecipe eB = new ShapedRecipe(earthArmour.get(0));
		//air recipie
		ShapedRecipe aH = new ShapedRecipe(airArmour.get(2));
		ShapedRecipe aC = new ShapedRecipe(airArmour.get(1));
		ShapedRecipe aL = new ShapedRecipe(airArmour.get(3));
		ShapedRecipe aB = new ShapedRecipe(airArmour.get(0));
		//emerald recipie
		ShapedRecipe emeraldH = new ShapedRecipe(emeraldArmour.get(2));
		ShapedRecipe emeraldC = new ShapedRecipe(emeraldArmour.get(1));
		ShapedRecipe emeraldL = new ShapedRecipe(emeraldArmour.get(3));
		ShapedRecipe emeraldB = new ShapedRecipe(emeraldArmour.get(0));
		
		//set shapes
		//fire
		fH.shape("aaa", "a a", "   ");
		fH.setIngredient('a', Material.BLAZE_POWDER);
		fC.shape("a a", "aaa", "aaa");
		fC.setIngredient('a', Material.BLAZE_POWDER);
		fL.shape("aaa", "a a", "a a");
		fL.setIngredient('a', Material.BLAZE_POWDER);
		fB.shape("a a", "a a", "   ");
		fB.setIngredient('a', Material.BLAZE_POWDER);
		//water
		//set lapis
		Dye l = new Dye();
		l.setColor(DyeColor.BLUE);
		//Material lapis = l.toItemStack().getType();
		Material lapis = new ItemStack(Material.INK_SACK, 1, (short) 4).getType();
		//set recipe
		wH.shape("aaa", "a a", "   ");
		wH.setIngredient('a', lapis);
		wC.shape("a a", "aaa", "aaa");
		wC.setIngredient('a', lapis);
		wL.shape("aaa", "a a", "a a");
		wL.setIngredient('a', lapis);
		wB.shape("a a", "a a", "   ");
		wB.setIngredient('a', lapis);
		//earth
		eH.shape("aaa", "a a", "   ");
		eH.setIngredient('a', Material.GRASS);
		eC.shape("a a", "aaa", "aaa");
		eC.setIngredient('a', Material.GRASS);
		eL.shape("aaa", "a a", "a a");
		eL.setIngredient('a', Material.GRASS);
		eB.shape("a a", "a a", "   ");
		eB.setIngredient('a', Material.GRASS);
		//air
		aH.shape("aaa", "a a", "   ");
		aH.setIngredient('a', Material.FEATHER);
		aC.shape("a a", "aaa", "aaa");
		aC.setIngredient('a', Material.FEATHER);
		aL.shape("aaa", "a a", "a a");
		aL.setIngredient('a', Material.FEATHER);
		aB.shape("a a", "a a", "   ");
		aB.setIngredient('a', Material.FEATHER);
		//emerald
		emeraldH.shape("aaa", "a a", "   ");
		emeraldH.setIngredient('a', Material.EMERALD);
		emeraldC.shape("aaa", "a a", "   ");
		emeraldC.setIngredient('a', Material.EMERALD);
		emeraldL.shape("aaa", "a a", "   ");
		emeraldL.setIngredient('a', Material.EMERALD);
		emeraldB.shape("aaa", "a a", "   ");
		emeraldB.setIngredient('a', Material.EMERALD);
		
		//add recipe to bukkit
		Bukkit.addRecipe(fH);
		Bukkit.addRecipe(fC);
		Bukkit.addRecipe(fL);
		Bukkit.addRecipe(fB);
		Bukkit.addRecipe(wH);
		Bukkit.addRecipe(wC);
		Bukkit.addRecipe(wL);
		Bukkit.addRecipe(wB);
		Bukkit.addRecipe(eH);
		Bukkit.addRecipe(eC);
		Bukkit.addRecipe(eL);
		Bukkit.addRecipe(eB);
		Bukkit.addRecipe(aH);
		Bukkit.addRecipe(aC);
		Bukkit.addRecipe(aL);
		Bukkit.addRecipe(aB);
		Bukkit.addRecipe(emeraldH);
		Bukkit.addRecipe(emeraldC);
		Bukkit.addRecipe(emeraldL);
		Bukkit.addRecipe(emeraldB);
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if (e.getAction() == Action.RIGHT_CLICK_AIR) {
			if (e.getPlayer().getInventory().getItemInMainHand().getType() == Material.BLAZE_ROD) {
				for (int count = 0; count <= 5; count++) {
					e.getPlayer().launchProjectile(Fireball.class);
				}
			}
		}
	}
	
	@EventHandler
	public void bowShoot(EntityShootBowEvent e) {
		if (e.getEntity() instanceof Player) {
			if (e.getBow().hasItemMeta()) {
				if (e.getBow().getItemMeta().getDisplayName().equals("Teleportation Bow")) {
					Entity arrow = e.getProjectile();
		            World world = arrow.getWorld();

		            EntityArrow entityArrow = ((CraftArrow)arrow).getHandle();
		            
		           Field fieldZ = null;
		           Field fieldX = null;
		           Field fieldY = null;
					try {
						fieldZ = EntityArrow.class.getDeclaredField("g");
						fieldX = EntityArrow.class.getDeclaredField("e");
						fieldY = EntityArrow.class.getDeclaredField("f");
					} catch (NoSuchFieldException e1) {
						e1.printStackTrace();
					} catch (SecurityException e1) {
						e1.printStackTrace();
					}

		            fieldX.setAccessible(true);
		            fieldY.setAccessible(true);
		            fieldZ.setAccessible(true);
		            
		            try {
						int x = fieldX.getInt(entityArrow);
						int y = fieldY.getInt(entityArrow);
			            int z = fieldZ.getInt(entityArrow);
			            e.getEntity().teleport(new Location(world, x, y, z));
					} catch (IllegalArgumentException e1) {
						e1.printStackTrace();
					} catch (IllegalAccessException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
	}
	
	@EventHandler
	public void emeraldEquip(InventoryCloseEvent e) {
		if (emeraldCount.containsKey((Player) e.getPlayer())){
			emeraldCount.replace((Player) e.getPlayer(), 0);
			
		} else {
			emeraldCount.put((Player) e.getPlayer(), 0);
		}
		if (e.getInventory().getType() == InventoryType.PLAYER) {
			ItemStack[] inv = e.getPlayer().getInventory().getArmorContents();
			for (ItemStack item: emeraldArmour) {
				for (ItemStack invitem: inv) {
					if (item == invitem) {
						int count = emeraldCount.get((Player) e.getPlayer());
						emeraldCount.put((Player) e.getPlayer(), count + 1);
					}
				}
			}
			
			if (emeraldCount.get((Player) e.getPlayer()) == 4) {
				//full set of armour
				emeraldList.add((Player) e.getPlayer());
				e.getPlayer().setMaxHealth(40);
				e.getPlayer().setHealth(40);
			} else {
				//not a full set
				if (emeraldList.contains((Player) e.getPlayer())) {
					emeraldList.remove((Player) e.getPlayer());
					e.getPlayer().setMaxHealth(20);
					e.getPlayer().setHealth(20);
				}
			}
			
		}
	}
	
	@EventHandler
	public void fireEquip(InventoryCloseEvent e) {
		if (fireCount.containsKey((Player) e.getPlayer())){
			fireCount.replace((Player) e.getPlayer(), 0);
			
		} else {
			fireCount.put((Player) e.getPlayer(), 0);
		}
		if (e.getInventory().getType() == InventoryType.PLAYER) {
			ItemStack[] inv = e.getPlayer().getInventory().getArmorContents();
			for (ItemStack item: fireArmour) {
				for (ItemStack invitem: inv) {
					if (item == invitem) {
						int count = fireCount.get((Player) e.getPlayer());
						fireCount.put((Player) e.getPlayer(), count + 1);
					}
				}
			}
			
			if (fireCount.get((Player) e.getPlayer()) == 4) {
				//full set of armour
				e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 10));
				fireList.add((Player) e.getPlayer());
			} else {
				if (fireList.contains((Player) e.getPlayer())) {
					fireList.remove((Player) e.getPlayer());
					e.getPlayer().removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
				}
			}
			
		}
	}
	
	@EventHandler
	public void waterEquip(InventoryCloseEvent e) {
		if (waterCount.containsKey((Player) e.getPlayer())){
			waterCount.replace((Player) e.getPlayer(), 0);
			
		} else {
			waterCount.put((Player) e.getPlayer(), 0);
		}
		if (e.getInventory().getType() == InventoryType.PLAYER) {
			ItemStack[] inv = e.getPlayer().getInventory().getArmorContents();
			for (ItemStack item: waterArmour) {
				if (item.getType() == Material.LEATHER_HELMET) {
					item.addEnchantment(Enchantment.WATER_WORKER, 10);
					item.addEnchantment(Enchantment.OXYGEN, 10);
				}
				
				if (item.getType() == Material.LEATHER_BOOTS) {
					item.addEnchantment(Enchantment.DEPTH_STRIDER, 10);
				}
				
				for (ItemStack invitem: inv) {
					if (item == invitem) {
						int count = waterCount.get((Player) e.getPlayer());
						waterCount.put((Player) e.getPlayer(), count + 1);//flame trail as you run as well there children.
					}
				}
			}
			
			if (waterCount.get((Player) e.getPlayer()) == 4) {
				//full set of armour
				waterList.add((Player) e.getPlayer());
			} else {
				//not a full set
				if (waterList.contains((Player) e.getPlayer())) {
					waterList.remove((Player) e.getPlayer());
				}
			}
			
		}
	}
	
	@EventHandler
	public void earthEquip(InventoryCloseEvent e) {
		if (earthCount.containsKey((Player) e.getPlayer())){
			earthCount.replace((Player) e.getPlayer(), 0);
			
		} else {
			earthCount.put((Player) e.getPlayer(), 0);
		}
		if (e.getInventory().getType() == InventoryType.PLAYER) {
			ItemStack[] inv = e.getPlayer().getInventory().getArmorContents();
			for (ItemStack item: earthArmour) {
				if (item.getType() != Material.LEATHER_CHESTPLATE) {
					item.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
				} else {
					item.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
				}
				
				for (ItemStack invitem: inv) {
					if (item == invitem) {
						int count = earthCount.get((Player) e.getPlayer());
						earthCount.put((Player) e.getPlayer(), count + 1);
					}
				}
			}
			
			if (earthCount.get((Player) e.getPlayer()) == 4) {
				//full set of armour
				earthList.add((Player) e.getPlayer());
				e.getPlayer().setMaxHealth(40);
				e.getPlayer().setHealth(40);
			} else {
				//not a full set
				if (earthList.contains((Player) e.getPlayer())) {
					earthList.remove((Player) e.getPlayer());
					e.getPlayer().setMaxHealth(20);
					e.getPlayer().setHealth(20);
				}
			}
			
		}
	}
	
	@EventHandler
	public void airEquip(InventoryCloseEvent e) {
		if (airCount.containsKey((Player) e.getPlayer())){
			airCount.replace((Player) e.getPlayer(), 0);
			
		} else {
			airCount.put((Player) e.getPlayer(), 0);
		}
		if (e.getInventory().getType() == InventoryType.PLAYER) {
			ItemStack[] inv = e.getPlayer().getInventory().getArmorContents();
			for (ItemStack item: airArmour) {
				if (item.getType() == Material.LEATHER_BOOTS) {
					item.addEnchantment(Enchantment.PROTECTION_FALL, 5);
				}
				
				for (ItemStack invitem: inv) {
					if (item == invitem) {
						int count = airCount.get((Player) e.getPlayer());
						airCount.put((Player) e.getPlayer(), count + 1);
					}
				}
			}
			
			if (airCount.get((Player) e.getPlayer()) == 4) {
				//full set of armour
				airList.add((Player) e.getPlayer());
				e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 2));
			} else {
				//not a full set
				if (airList.contains((Player) e.getPlayer())) {
					airList.remove((Player) e.getPlayer());
					e.getPlayer().removePotionEffect(PotionEffectType.SPEED);
				}
			}
			
		}
	}
	
	@EventHandler
	public void waterEffects(PlayerMoveEvent e) {
		Material m = e.getPlayer().getLocation().getBlock().getType();
		if (m == Material.STATIONARY_WATER || m == Material.WATER) {
			if (waterList.contains(e.getPlayer())) {
				e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, Integer.MAX_VALUE, 1));
				e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 1));
			} else {
			}
		} else {
			if (waterList.contains(e.getPlayer())) {
				if (e.getPlayer().hasPotionEffect(PotionEffectType.NIGHT_VISION) && e.getPlayer().hasPotionEffect(PotionEffectType.WATER_BREATHING)) {
					e.getPlayer().removePotionEffect(PotionEffectType.WATER_BREATHING);
					e.getPlayer().removePotionEffect(PotionEffectType.NIGHT_VISION);
				}
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void fireEffects(PlayerMoveEvent e) {
		if (fireList.contains(e.getPlayer())) {
			e.getPlayer().playEffect(e.getPlayer().getLocation(), Effect.MOBSPAWNER_FLAMES, 2);
		}
	}
	
	@EventHandler
	public void onDoubleJump(PlayerToggleFlightEvent e) {
		Player p = e.getPlayer();
		if (airList.contains(p)) {
		if (p.getGameMode() != GameMode.CREATIVE) {
			e.setCancelled(true);
			Block b = p.getWorld().getBlockAt(p.getLocation().subtract(0, 2, 0));
			if (b.getType() != Material.AIR) {
				Vector v = p.getLocation().getDirection().multiply(1).setY(1);
				p.setVelocity(v);
			}
		}
		}
	}
	
	@EventHandler
	public void join(PlayerJoinEvent e) {
		e.getPlayer().setAllowFlight(true);
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void dragDrop(InventoryClickEvent e) {
		if (!(e.getCurrentItem() == null)) {
			if (!(e.getCursor() == null)) {
				ItemStack clicked = e.getCurrentItem();
				ItemStack item =  e.getCursor();
				if (!(item.getItemMeta() == null)) {
					if (!(item.getItemMeta().getDisplayName() == null)) {
						if (item.getItemMeta().getDisplayName().toString().contains("health")) {
							String[] splitlevel = item.getItemMeta().getDisplayName().toString().split(" ");
							int level = Integer.parseInt(splitlevel[1]);
							if (!(clicked.getItemMeta().hasEnchant(health))) {
								ItemMeta cm = clicked.getItemMeta();
								List<String> lore = cm.getLore();
								if (!(lore == null)) {
									cm.addEnchant(health, level, true);
									cm.setLore(Arrays.asList(lore + "Health " + level));
									clicked.setItemMeta(cm);
									e.setCursor(null);
						
		} else {
			cm.addEnchant(health, level, true);
			cm.setLore(Arrays.asList("Health " + level));
			clicked.setItemMeta(cm);
			Player p = (Player) e.getWhoClicked();
			p.getInventory().remove(item);
			e.setCursor(null);
		}
				}
	}
	}
	}
	}
	}
	}
	
	private ArrayList<Player> cooldown = new ArrayList<Player>(), nofall = new ArrayList<Player>();
    
    @SuppressWarnings("deprecation")
    @EventHandler
    public void onGrappleThrow(ProjectileLaunchEvent e) {
            if (!(e.getEntityType() == EntityType.FISHING_HOOK)) return;
            if (!(e.getEntity().getShooter() instanceof Player)) return;
            
            final Player p = (Player) e.getEntity().getShooter(); 
 
            Player shooter = (Player) e.getEntity().getShooter();
            ItemStack item = shooter.getInventory().getItemInMainHand();
            if (item.hasItemMeta()) {
            	if (item.getItemMeta().hasDisplayName()) {
            		if (item.getItemMeta().getDisplayName() == "Grappling Hook") {
            			if (cooldown.contains(p)) {
                            e.setCancelled(true);
                            return;
                    }
                    
                    Location target = null;
                    
                    for (Block block : p.getLineOfSight((HashSet<Byte>) null, 100)) {
                            if (!(block.getType() == Material.AIR)) {
                                    target = block.getLocation();
                                    break;
                            }
                    }
                    
                    if (target == null) {
                            e.setCancelled(true);
                            return;
                    }
                    
                    p.teleport(p.getLocation().add(0, 0.5, 0));
                    
                    final Vector v = getVectorForPoints(p.getLocation(), target);
                    
                    e.getEntity().setVelocity(v);
                    
                    if (!nofall.contains(p)) nofall.add(p);
                    
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                            public void run() {
                                    p.setVelocity(v);
                            }
                    }, 5);
                    
                    cooldown.add(p);
                    
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                            public void run() {
                                    cooldown.remove(p);
                            }
                    }, 15);
            		}
            	}
            }
            
    }
    
    @EventHandler
    public void onEntityDamage(EntityDamageEvent e) {
            if (!(e.getEntity() instanceof Player)) return;
            if (!(e.getCause() == DamageCause.FALL)) return;
            
            Player p = (Player) e.getEntity();
            
            if (nofall.contains(p)) {
                    e.setCancelled(true);
                    nofall.remove(p);
            }
    }
    
    private Vector getVectorForPoints(Location l1, Location l2) {
            double g = -0.08;
            double d = l2.distance(l1);
            double t = d;
            double vX = (1.0+0.07*t) * (l2.getX() - l1.getX())/t;
            double vY = (1.0+0.03*t) * (l2.getY() - l1.getY())/t - 0.5*g*t;
            double vZ = (1.0+0.07*t) * (l2.getZ() - l1.getZ())/t;
            return new Vector(vX, vY, vZ);
    }
	
}
