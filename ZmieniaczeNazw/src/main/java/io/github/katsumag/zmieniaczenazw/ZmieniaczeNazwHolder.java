package io.github.katsumag.zmieniaczenazw;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class ZmieniaczeNazwHolder implements InventoryHolder {

    @Override
    public Inventory getInventory() {
        return ZmieniaczeNazw.inventory;
    }
}
