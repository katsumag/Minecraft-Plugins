package io.github.katsumag.prace.GUI;

import io.github.katsumag.prace.Prace;
import io.github.katsumag.prace.misc.Skull;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.Objects;

public class Selector implements Listener {

    private Prace main;
    public static final ItemStack BUILDER_HEAD =  Skull.getCustomSkull("http://textures.minecraft.net/texture/dcb7a15eda1cbe47a8d5d7f780e89bbc35e0c177fcb9c6480a11b02cc8165c1c");
    public static final ItemStack LOG_HEAD = Skull.getCustomSkull("http://textures.minecraft.net/texture/dc1754851e367e8beba2a6d8f7c2fede87ae793ac546b0f299d673215b293");
    public static final ItemStack COBBLE_HEAD = Skull.getCustomSkull("http://textures.minecraft.net/texture/6d2e310879a6450af5625bcd45093dd7e5d8f827ccbfeac69c81537768406b");

    public Inventory inventory;

    public Selector(Prace main){
        this.main = main;

        if (inventory == null){
            inventory = Bukkit.createInventory(new JobHolder(main), 9, "Prace");
        }

        ItemStack stack = new ItemStack(Material.STAINED_GLASS_PANE);
        inventory.setItem(0, stack);
        inventory.setItem(1, stack);
        inventory.setItem(2, stack);
        inventory.setItem(3, COBBLE_HEAD);
        inventory.setItem(4, LOG_HEAD);
        inventory.setItem(5, BUILDER_HEAD);
        inventory.setItem(6, stack);
        inventory.setItem(7, stack);
        inventory.setItem(8, stack);

    }

    @EventHandler
    public void onClick(InventoryClickEvent e){

        if (!(e.getClickedInventory().getHolder() instanceof JobHolder)) return;

        if (!(e.getSlot() >= 3) && e.getSlot() <= 5) return;

        ItemStack item = e.getClickedInventory().getItem(e.getSlot());

        if (item.isSimilar(BUILDER_HEAD)){
            //Builder
            main.getDataBase().setCurrentJob(e.getWhoClicked().getUniqueId(), "Builder");
            ((Player) e.getWhoClicked()).setMetadata("Job", new FixedMetadataValue(main, "Builder"));
            ((Player) e.getWhoClicked()).closeInventory();

        } else{
            if (item.isSimilar(LOG_HEAD)){
                //WoodCutter
                main.getDataBase().setCurrentJob(e.getWhoClicked().getUniqueId(), "WoodCutter");
                ((Player) e.getWhoClicked()).setMetadata("Job", new FixedMetadataValue(main, "WoodCutter"));
                ((Player) e.getWhoClicked()).closeInventory();

            } else{
                //Miner
                main.getDataBase().setCurrentJob(e.getWhoClicked().getUniqueId(), "Miner");
                ((Player) e.getWhoClicked()).setMetadata("Job", new FixedMetadataValue(main, "Miner"));
                ((Player) e.getWhoClicked()).closeInventory();

            }
        }

    }

}
