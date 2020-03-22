package io.github.katsumag.zmieniaczenazw;

import io.github.katsumag.zmieniaczenazw.AnvilAPI.AnvilGUI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

public final class ZmieniaczeNazw extends JavaPlugin implements Listener {

    private final ItemStack item = new ItemStack(Material.NAME_TAG);
    private Plugin plugin = this;
    public static Inventory inventory = Bukkit.createInventory(new ZmieniaczeNazwHolder(), 9, ChatColor.translateAlternateColorCodes('&', "&6Zmieniacz Nazwy"));
    public static HashMap<UUID, Inventory> anvilInventory = new HashMap<>();

    @Override
    public void onEnable() {
        // Plugin startup logic

        ItemStack pane = new ItemStack(Material.STAINED_GLASS_PANE);

        inventory.setItem(0, pane);
        inventory.setItem(1, pane);
        inventory.setItem(2, pane);
        inventory.setItem(3, new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14));
        inventory.setItem(4, new ItemStack(Material.AIR));
        inventory.setItem(5, new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14));
        inventory.setItem(6, pane);
        inventory.setItem(7, pane);
        inventory.setItem(8, pane);

        ItemMeta meta = this.item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Zmieniacz Nazwy"));
        meta.setLore(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&eKliknij PPM aby ustawić nazwę przedmiotu")));
        this.item.setItemMeta(meta);

        Bukkit.getServer().getPluginManager().registerEvents(this, this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onRMB(PlayerInteractEvent e){
        Player p = e.getPlayer();
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){

            if (p.getInventory().getItemInMainHand().isSimilar(this.item)){

                p.openInventory(this.inventory);
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onClick(InventoryClickEvent e){
        if (e.getClickedInventory() == null){
            return;
        }
        InventoryHolder holder = e.getClickedInventory().getHolder();
        if (holder instanceof ZmieniaczeNazwHolder){
            if (e.getSlot() != 4){
                e.setCancelled(true);
                return;
            } else{
                new BukkitRunnable(){

                    @Override
                    public void run() {
                        if (inventory.getItem(e.getSlot()) == null) return;
                        if (inventory.getItem(e.getSlot()) != null || inventory.getItem(e.getSlot()).getType() != Material.AIR){
                            ItemStack lostItem = inventory.getItem(e.getSlot());
                            e.getWhoClicked().closeInventory();
                            //this.anvilInventory.clear();
                            inventory.setItem(4, new ItemStack(Material.AIR));

                            AnvilGUI gui = new AnvilGUI.Builder().onComplete((player, s) -> {
                                player.getInventory().remove(item);


                                new BukkitRunnable(){

                                    @Override
                                    public void run() {
                                        ItemStack item = ZmieniaczeNazw.anvilInventory.get(e.getWhoClicked().getUniqueId()).getItem(2);
                                        ItemMeta meta = item.getItemMeta();
                                        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', meta.getDisplayName()));
                                        item.setItemMeta(meta);
                                        ((Player) e.getWhoClicked()).getInventory().addItem(item);
                                    }
                                }.runTaskLater(plugin, 20);
                                return AnvilGUI.Response.close();

                            }).item(lostItem).title("ZmieniaczeNazw").plugin(plugin).open((Player) e.getWhoClicked());

                            if (anvilInventory.containsKey(e.getWhoClicked().getUniqueId())){
                                anvilInventory.replace(e.getWhoClicked().getUniqueId(), gui.getInventory());
                            } else{
                                anvilInventory.put(e.getWhoClicked().getUniqueId(), gui.getInventory());
                            }

                        }
                    }
                }.runTask(this);

            }

        } else {
            return;
        }

        if (e.getInventory() instanceof AnvilInventory){
            HumanEntity hu = e.getWhoClicked();

            if (hu instanceof Player){
                Player p = (Player) hu;
                InventoryView view = e.getView();
                int rawSlot = e.getRawSlot();
                if (rawSlot == view.convertSlot(rawSlot)){
                    if (rawSlot == 2){
                        ItemStack item = e.getCurrentItem();
                        if (item != null || item.getType() != Material.AIR){
                            if (item.hasItemMeta()){
                                ItemMeta meta = item.getItemMeta();
                                if (meta.hasDisplayName()){
                                    String renamedTo = meta.getDisplayName();
                                    //has been named.
                                    if (p.getOpenInventory().getType() != InventoryType.PLAYER){
                                        p.getInventory().remove(this.item);
                                    } else{
                                        new BukkitRunnable(){

                                            @Override
                                            public void run() {
                                                while (p.getOpenInventory().getType() == InventoryType.PLAYER){
                                                    continue;
                                                }

                                                p.getInventory().remove(item);

                                            }
                                        }.runTaskTimerAsynchronously(this, 80, 80);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args){

        if (command.getName().equalsIgnoreCase("zmieniacz")){

            if (sender instanceof ConsoleCommandSender){
                if (args.length != 2){
                    sender.sendMessage(ChatColor.RED + "Niepoprawne dane wejściowe!");
                } else{
                    Player p = Bukkit.getPlayer(args[0]);
                    if (p.isOnline()){
                        int amount = Integer.parseInt(args[1]);
                        if (amount > 1){
                            for (int x = 1; x <= amount; x++){
                                p.getInventory().addItem(this.item);
                            }
                        } else {
                            p.getInventory().addItem(this.item);
                        }
                    }
                }
            } else{
                if (sender instanceof Player){

                    Player p = (Player) sender;

                    if (p.hasPermission("zmieniacz.admin")){

                        if (args.length != 1){
                            p.sendMessage(ChatColor.RED + "Niepoprawne dane wejściowe!");
                        } else{

                            int amount = Integer.parseInt(args[0]);

                            if (amount > 1){
                                for (int x = 1; x <= amount; x++){
                                    p.getInventory().addItem(this.item);
                                }
                            } else {
                                p.getInventory().addItem(this.item);
                            }

                        }

                    } else{
                        p.sendMessage(ChatColor.RED + "Nie masz uprawnień do wykonania tego polecenia!");
                    }

                } else{
                    return true;
                }
            }

        }

        return true;

    }

}
