package Main;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Dye;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import com.snowgears.grapplinghook.api.*;
//TODO Sort out the Health enchantment Event
//TODO Armour effects.
public class main extends JavaPlugin implements Listener{
	
	Plugin plugin = Bukkit.getPluginManager().getPlugin("CustomItems");
	
	@Override
	public void onEnable(){
		PluginDescriptionFile pdfFile = getDescription();
		Logger logger = getLogger();
		logger.info(pdfFile.getName() + " has been enabled! ");
		getServer().getPluginManager().registerEvents(this, this);
		getServer().getPluginManager().registerEvents(new EarthArmour(this), this);
		getServer().getPluginManager().registerEvents(new AirArmour(this), this);
		getServer().getPluginManager().registerEvents(new FireArmour(this), this);
		getServer().getPluginManager().registerEvents(new WaterArmour(this), this);
		Plugin[] plugins = Bukkit.getServer().getPluginManager().getPlugins();
		for (Plugin plugin: plugins) {
			getLogger().info(plugin.getName());
		}
		LoadEnchantments();
		loadRecipes();
	}
	
	@Override
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
	
	public Enchants health = new Enchants(460);
	public ItemStack healthBook = CustomItem.makeBook(new ItemStack(Material.ENCHANTED_BOOK), health, 1, "Health I");
	public ItemStack healthBook2 = CustomItem.makeBook(new ItemStack(Material.ENCHANTED_BOOK), health, 2, "Health II");
	public ItemStack healthBook3 = CustomItem.makeBook(new ItemStack(Material.ENCHANTED_BOOK), health, 3, "Health III");
	public ItemStack healthBook4 = CustomItem.makeBook(new ItemStack(Material.ENCHANTED_BOOK), health, 4, "Health IV");
	public ItemStack healthBook5 = CustomItem.makeBook(new ItemStack(Material.ENCHANTED_BOOK), health, 5, "Health V");
	public ItemStack gH = HookAPI.createGrapplingHook(new ItemStack(Material.FISHING_ROD).getDurability() * 2);
	public ItemStack fireRod = CustomItem.customItem(new ItemStack(Material.BLAZE_ROD), "Flame Rod", null);
	public ItemMeta rodMeta = fireRod.getItemMeta();
	public ItemStack tpBow = CustomItem.customItem(new ItemStack(Material.BOW), "Teleportation Bow", null);
	public ItemMeta tpMeta = tpBow.getItemMeta();
	public ShapedRecipe fH;
	public ShapedRecipe fC;
	public ShapedRecipe fL;
	public ShapedRecipe fB;
	public ShapedRecipe wH;
	public ShapedRecipe wC;
	public ShapedRecipe wL;
	public ShapedRecipe wB;
	public ShapedRecipe eH;
	public ShapedRecipe eC;
	public ShapedRecipe eL;
	public ShapedRecipe eB;
	public ShapedRecipe aH;
	public ShapedRecipe aC;
	public ShapedRecipe aL;
	public ShapedRecipe aB;
	public ShapedRecipe emeraldH;
	public ShapedRecipe emeraldC;
	public ShapedRecipe emeraldL;
	public ShapedRecipe emeraldB;
	public List<ItemStack> emeraldArmour;
	public List<ItemStack> fireArmour;
	public List<ItemStack> waterArmour;
	public List<ItemStack> earthArmour;
	public List<ItemStack> airArmour;
	
	public void addRecipes(ItemStack result, String a, String b, String c, Character key, ItemStack ingredient) {
		CustomRecipe recipe = new CustomRecipe(result);
		recipe.shape(a, b, c);
		recipe.setIngredient(key, ingredient);
		recipe.register();
	}
	
	public void addRecipes(ItemStack result, String a, String b, String c, Character key, ItemStack ingredient, Character key2, ItemStack ingedient2) {
		CustomRecipe recipe = new CustomRecipe(result);
		recipe.shape(a, b, c);
		recipe.setIngredient(key, ingredient);
		recipe.setIngredient(key2, ingedient2);
		recipe.register();
	}
	
	public void loadRecipes() {
		List<ItemStack> emeraldArmour = EmeraldArmour.getEmeraldArmour();
		List<ItemStack> fireArmour = FireArmour.getFireArmour();
		List<ItemStack> waterArmour = WaterArmour.getWaterArmour();
		List<ItemStack> earthArmour = EarthArmour.getEarthArmour();
		List<ItemStack> airArmour = AirArmour.getAirArmour();
		
		ItemStack fire1 = new ItemStack(Material.BLAZE_POWDER);
		ItemMeta fire1meta = fire1.getItemMeta();
		fire1meta.setDisplayName("Super Blaze Powder");
		fire1meta.setLore(Arrays.asList("Used to craft Ultimate Blaze Powder!"));
		fire1.setItemMeta(fire1meta);
		
		ItemStack fire2 = new ItemStack(Material.BLAZE_POWDER);
		ItemMeta fire2meta = fire2.getItemMeta();
		fire2meta.setDisplayName("Ultimate Blaze Powder");
		fire2meta.setLore(Arrays.asList("Used to craft Fire Armour!"));
		fire2.setItemMeta(fire2meta);
		
		Dye l = new Dye();
		l.setColor(DyeColor.BLUE);
		ItemStack lapis = l.toItemStack(1);
		ItemMeta lapismeta = lapis.getItemMeta();
		lapismeta.setDisplayName("Super Lapis!");
		lapismeta.setLore(Arrays.asList("Used to craft Ultimate Lapis!"));
		lapis.setItemMeta(lapismeta);
		
		ItemStack lapis2 = l.toItemStack(1);
		ItemMeta lapis2meta = lapis2.getItemMeta();
		lapis2meta.setDisplayName("Ultimate Lapis");
		lapis2meta.setLore(Arrays.asList("Used to craft Water Armour!"));
		lapis2.setItemMeta(lapis2meta);
		
		ItemStack grass1 = new ItemStack(Material.GRASS);
		ItemMeta grass1meta = grass1.getItemMeta();
		grass1meta.setDisplayName("Super Grass");
		grass1meta.setLore(Arrays.asList("Used to make Ultimate Grass!"));
		grass1.setItemMeta(grass1meta);
		
		ItemStack grass2 = new ItemStack(Material.GRASS);
		ItemMeta grass2meta = grass2.getItemMeta();
		grass2meta.setDisplayName("Ultimate Grass");
		grass2meta.setLore(Arrays.asList("Used to make Earth Armour!"));
		grass2.setItemMeta(grass2meta);
		
		ItemStack air1 = new ItemStack(Material.FEATHER);
		ItemMeta air1meta = air1.getItemMeta();
		air1meta.setDisplayName("Super Feather");
		air1meta.setLore(Arrays.asList("Used to make an Ultimate Feather!"));
		air1.setItemMeta(air1meta);
		
		ItemStack air2 = new ItemStack(Material.FEATHER);
		ItemMeta air2meta = air2.getItemMeta();
		air2meta.setDisplayName("Ultimate Feather");
		air2meta.setLore(Arrays.asList("Used to make Air Armour!"));
		air2.setItemMeta(air2meta);
		
		ItemStack em1 = new ItemStack(Material.EMERALD);
		ItemMeta em1meta = em1.getItemMeta();
		em1meta.setDisplayName("Super Emerald");
		em1meta.setLore(Arrays.asList("Used to make an Ultimate Emerald!"));
		em1.setItemMeta(em1meta);
		
		ItemStack em2 = new ItemStack(Material.EMERALD);
		ItemMeta em2meta = em2.getItemMeta();
		em2meta.setDisplayName("Ultimate Emerald");
		em2meta.setLore(Arrays.asList("Used to make Emerald Armour!"));
		em2.setItemMeta(em2meta);
		
		ItemStack dg = new ItemStack(Material.GOLD_INGOT);
		ItemMeta dgm = dg.getItemMeta();
		dgm.setDisplayName("5 Gold Ingot");
		dgm.setLore(Arrays.asList("Can be used to craft other custom gold items!"));
		dg.setItemMeta(dgm);
		
		ItemStack tg = new ItemStack(Material.GOLD_INGOT);
		ItemMeta tgm = tg.getItemMeta();
		tgm.setDisplayName("10 Gold");
		tgm.setLore(Arrays.asList("Can be used to craft:", "other custom gold items", "or Health enchantments!"));
		tg.setItemMeta(tgm);
		
		ItemStack twg = new ItemStack(Material.GOLD_INGOT);
		ItemMeta twgm = twg.getItemMeta();
		twgm.setDisplayName("20 Gold Ingot");
		twgm.setLore(Arrays.asList("Can be used to craft:", "other custom gold items", "or Health enchantments!"));
		twg.setItemMeta(twgm);
		
		ItemStack thg = new ItemStack(Material.GOLD_INGOT);
		ItemMeta thgm = thg.getItemMeta();
		thgm.setDisplayName("30 Gold Ingot");
		thgm.setLore(Arrays.asList("Can be used to craft", "other custom gold items", "or Health enchantments!"));
		thg.setItemMeta(thgm);
		
		ItemStack fog = new ItemStack(Material.GOLD_INGOT);
		ItemMeta fogm = fog.getItemMeta();
		fogm.setDisplayName("40 Gold Ingot");
		fogm.setLore(Arrays.asList("Can be used to craft:", "other custom gold items", "or Health enchantments!"));
		fog.setItemMeta(fogm);
		
		ItemStack fig = new ItemStack(Material.GOLD_INGOT);
		ItemMeta figm = fig.getItemMeta();
		figm.setDisplayName("50 Gold Ingot");
		figm.setLore(Arrays.asList("Can be used to craft Health enchantments!"));
		fig.setItemMeta(figm);
		
		CustomRecipe superBlaze = new CustomRecipe(fire1);
		superBlaze.shape("aaa", "a a", "aaa");
		superBlaze.setIngredient('a', Material.BLAZE_POWDER);
		superBlaze.register();
		
		CustomRecipe ultraBlaze = new CustomRecipe(fire2);
		ultraBlaze.shape("aa ", "   ", "   ");
		ultraBlaze.setIngredient('a', fire1);
		ultraBlaze.register();
		
		//fire recipes
		CustomRecipe fireHelmet = new CustomRecipe(fireArmour.get(0));
		fireHelmet.shape("aaa", "a a", "   ");
		fireHelmet.setIngredient('a', fire2);
		fireHelmet.register();
		CustomRecipe fireChestplate = new CustomRecipe(fireArmour.get(1));
		fireChestplate.shape("a a", "aaa", "aaa");
		fireChestplate.setIngredient('a', fire2);
		fireChestplate.register();
		addRecipes(fireArmour.get(2), "aaa", "a a", "a a", 'a', fire2);
		addRecipes(fireArmour.get(3), "a a", "a a", "   ", 'a', fire2);
		
		//water recipes
		addRecipes(lapis, "aaa", "a a", "aaa", 'a', l.toItemStack(1));
		addRecipes(lapis2, "aa ", "   ", "   ", 'a', lapis);
		addRecipes(waterArmour.get(3), "a a", "a a", "   ", 'a', lapis2);
		addRecipes(waterArmour.get(1), "a a", "aaa", "aaa", 'a', lapis2);
		addRecipes(waterArmour.get(0), "aaa", "a a", "   ", 'a', lapis2);
		addRecipes(waterArmour.get(2), "aaa", "a a", "a a", 'a', lapis2);
		
		//earth recipes
		addRecipes(grass1, "aaa", "a a", "aaa", 'a', new ItemStack(Material.GRASS));
		addRecipes(grass2, "aa ", "   ", "   ", 'a', grass1);
		addRecipes(earthArmour.get(3), "a a", "a a", "   ", 'a', grass2);
		addRecipes(earthArmour.get(1), "a a", "aaa", "aaa", 'a', grass2);
		addRecipes(earthArmour.get(0), "aaa", "a a", "   ", 'a', grass2);
		addRecipes(earthArmour.get(2), "aaa", "a a", "a a", 'a', grass2);

		//air recipes
		addRecipes(air1, "aaa", "a a", "aaa", 'a', new ItemStack(Material.FEATHER));
		addRecipes(air2, "aa ", "   ", "   ", 'a', air1);
		addRecipes(airArmour.get(3), "a a", "a a", "   ", 'a', air2);
		addRecipes(airArmour.get(1), "a a", "aaa", "aaa", 'a', air2);
		addRecipes(airArmour.get(0), "aaa", "a a", "   ", 'a', air2);
		addRecipes(airArmour.get(2), "aaa", "a a", "a a", 'a', air2);
		//emerald recipes
		addRecipes(em1, "aaa", "a a", "aaa", 'a', new ItemStack(Material.EMERALD));
		addRecipes(em2, "aa ", "   ", "   ", 'a', em1);
		addRecipes(emeraldArmour.get(3), "a a", "a a", "   ", 'a', em2);
		addRecipes(emeraldArmour.get(1), "a a", "aaa", "aaa", 'a', em2);
		addRecipes(emeraldArmour.get(0), "aaa", "a a", "   ", 'a', em2);
		addRecipes(emeraldArmour.get(2), "aaa", "a a", "a a", 'a', em2);
		
		//extras
		ItemStack enderPearl = new ItemStack(Material.ENDER_PEARL);
		ItemMeta ep1 = enderPearl.getItemMeta();
		ep1.setDisplayName("Super Ender Pearl");
		ep1.setLore(Arrays.asList("Can be used to craft a Teleportation Bow!"));
		enderPearl.setItemMeta(ep1);
		
		ItemStack slime1 = new ItemStack(Material.SLIME_BALL);
		ItemMeta s1meta = slime1.getItemMeta();
		s1meta.setDisplayName("Super Slime Ball");
		s1meta.setLore(Arrays.asList("Can be used to craft an Ultimate Slime Ball!"));
		slime1.setItemMeta(s1meta);
		
		ItemStack slime2 = new ItemStack(Material.SLIME_BALL);
		ItemMeta s2meta = slime2.getItemMeta();
		s2meta.setDisplayName("Ultimate Slime Ball");
		s2meta.setLore(Arrays.asList("Can be used to craft a Grappling Hook!"));
		slime2.setItemMeta(s2meta);
		
		addRecipes(fireRod, "aaa", "aba", "aaa", 'a', fire2, 'b', new ItemStack(Material.BLAZE_ROD));
		addRecipes(enderPearl, "aaa", "a a", "aaa", 'a', new ItemStack(Material.ENDER_PEARL));
		addRecipes(tpBow, "aaa", "aba", "aaa", 'a', enderPearl, 'b', new ItemStack(Material.BOW));
		addRecipes(slime1, "aaa", "a a", "aaa", 'a', new ItemStack(Material.SLIME_BALL));
		addRecipes(slime2, "aa ", "   ", "   ", 'a', slime1);
		addRecipes(gH, "aaa", "aba", "aaa", 'a', slime2, 'b', new ItemStack(Material.FISHING_ROD));
		
		//gold
		
		addRecipes(dg, "aaa", " a ", " a ", 'a', new ItemStack(Material.GOLD_INGOT)); //5
		addRecipes(tg, "aa ", "   ", "   ", 'a', dg); //10
		addRecipes(twg, " a ", " a ", "   ", 'a', tg); //20
		addRecipes(thg, " a ", " b ", "   ", 'a', twg, 'b', tg); //30
		addRecipes(fog, " a ", " a ", "   ", 'a', twg); //40
		addRecipes(fig, " a ", " b ", "   ", 'a', fog, 'b', tg); //50
		
		//health book
		addRecipes(healthBook, "aaa", "aba", "aaa", 'a', twg, 'b', new ItemStack(Material.BOOK)); //1
		addRecipes(healthBook2, "aaa", "aba", "aaa", 'a', thg, 'b', new ItemStack(Material.BOOK)); //2
		addRecipes(healthBook3, "aaa", "aba", "aaa", 'a', thg, 'b', new ItemStack(Material.BOOK)); //3
		addRecipes(healthBook4, "aaa", "aba", "aaa", 'a', fog, 'b', new ItemStack(Material.BOOK)); //4
		addRecipes(healthBook5, "aaa", "aba", "aaa", 'a', fig, 'b', new ItemStack(Material.BOOK)); //5
	}
	
	@EventHandler
	public void onCraft(PrepareItemCraftEvent e) {
		CustomRecipe.process(e);
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if (e.getAction() == Action.RIGHT_CLICK_AIR) {
			if (e.getPlayer().getInventory().getItemInMainHand().getType() == Material.BLAZE_ROD && e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName() == "Flame Rod") {
				new BukkitRunnable() {
					private Plugin plugin;

					@Override
					public void run() {
						new BukkitRunnable() {
							@Override
							public void run() {
								for (int count = 0; count <= 5; count++) {
									try {
										Thread.sleep(100);
									} catch (InterruptedException e1) {
										e1.printStackTrace();
									}
									e.getPlayer().launchProjectile(Fireball.class);
								}
							}
						}.runTask(this.plugin);
					}
				}.runTaskAsynchronously(this);
			}
		}
	}
	 
	//tp bow stuff. 
	
	@EventHandler
	public void bowShoot(EntityShootBowEvent e) {
		if (e.getEntity() instanceof Player) {
			if (e.getBow().hasItemMeta()) {
				if (e.getBow().getItemMeta().getDisplayName().equals("Teleportation Bow")) {
					Entity arrow = e.getProjectile();
		            
		            arrow.setMetadata("TPBowOwner", new FixedMetadataValue(Bukkit.getPluginManager().getPlugin("CustomItems"), e.getEntity().getName()));
		            arrow.setMetadata("TPBow", new FixedMetadataValue(Bukkit.getPluginManager().getPlugin("CustomItems"), "TPBow"));	            
				}
			}
		}
	}
	
	@EventHandler
	public void getArrow(ProjectileHitEvent e) {
		Entity arrow = e.getEntity();
		if (arrow instanceof Arrow) {
			if (arrow.hasMetadata("TPBow")) {
				if (arrow.hasMetadata("TPBowOwner")) {
					String owner = arrow.getMetadata("TPBowOwner").get(0).asString();
					Player shooter = Bukkit.getPlayer(owner);
					shooter.teleport(arrow.getLocation());
				}
			}
		}
	}
	
	@EventHandler
	public void onDoubleJump(PlayerToggleFlightEvent e) {
		Player p = e.getPlayer();
		if (AirArmour.airList.contains(p)) {
		if (p.getGameMode() != GameMode.CREATIVE) {
			e.setCancelled(true);
			Block b = p.getWorld().getBlockAt(p.getLocation().subtract(0, 2, 0));
			if (b.getType() != Material.AIR) {
				Vector v = p.getLocation().getDirection().multiply(1).setY(1);
				p.setVelocity(v);
			}
		}
		} else {
			if (p.getGameMode() != GameMode.CREATIVE) {
				e.setCancelled(true);
			}
		}
	}
	//WORKS
	@EventHandler
	public void join(PlayerJoinEvent e) {
		e.getPlayer().setAllowFlight(true);
		e.getPlayer().setMaxHealth(20.D);
		e.getPlayer().setHealth(20);
	}
	
	
	//WORKS
	@SuppressWarnings("deprecation")
	@EventHandler
	public void dragDrop(InventoryClickEvent e) {
		if (!(e.getCurrentItem() == null)) {
			if (!(e.getCursor() == null)) {
				ItemStack clicked = e.getCurrentItem();
				ItemStack item =  e.getCursor();
				if (!(item.getItemMeta() == null)) {
					if (!(item.getItemMeta().getDisplayName() == null)) {
						getLogger().info("1");
						if (item.getItemMeta().getDisplayName().toString().contains("Health")) {
							getLogger().info("2");
							String[] splitlevel = item.getItemMeta().getDisplayName().toString().split(" ");
							int level = StringUtils.romanToInt(splitlevel[1]);
							if (!(clicked.getItemMeta().hasEnchant(health))) {
								getLogger().info("3");
								ItemMeta cm = clicked.getItemMeta();
								List<String> lore = cm.getLore();
								if (!(lore == null)) {
									getLogger().info("4");
									cm.addEnchant(health, level, true);
									cm.setLore(Arrays.asList(lore + "Health " + level));
									clicked.setItemMeta(cm);
									e.setCursor(null);
						
		} else {
			getLogger().info("5");
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
	
    Integer checkc = 0;
    
    @EventHandler //almost works
    public void healthStats(InventoryCloseEvent e) {
    	checkc = 0;
    	Player p = (Player) e.getPlayer();
    	ItemStack[] armour = p.getInventory().getArmorContents();
    	for (ItemStack check: armour) {
    		if (check.getType() != Material.AIR && check != null) {
    			for (ItemStack item: armour) {
    	    		if (item.containsEnchantment(health)) {
    	    			int level = item.getEnchantmentLevel(health);
    	    			if (level == 1) {
    	    				double healthValue = p.getMaxHealth() + 2;
        	    			p.setMaxHealth(healthValue + 0.D);
    	    			} else {
    	    				if (level == 2) {
    	    					double healthValue = p.getMaxHealth() + 4;
    	    	    			p.setMaxHealth(healthValue + 0.D);
    	    				} else {
    	    					if (level == 3) {
    	    						double healthValue = p.getMaxHealth() + 6;
    	        	    			p.setMaxHealth(healthValue + 0.D);
    	    					} else {
    	    						if (level == 4) {
    	    							double healthValue = p.getMaxHealth() + 8;
    	    	    	    			p.setMaxHealth(healthValue + 0.D);
    	    						} else {
    	    							if (level == 5) {
    	    								double healthValue = p.getMaxHealth() + 10;
    	    		    	    			p.setMaxHealth(healthValue + 0.D);
    	    							}
    	    						}
    	    					}
    	    				}
    	    			}
    	    			if (p.getHealth() == p.getMaxHealth()) {
    	    				p.setHealth(p.getHealth() + (level * 2));
    	    			}
    	    		}
    	    	}
    		} else {
    			checkc += 1;
    			if (checkc == 4) {
    				p.setMaxHealth(20);
    			}
    		}
    	}
    }
}
