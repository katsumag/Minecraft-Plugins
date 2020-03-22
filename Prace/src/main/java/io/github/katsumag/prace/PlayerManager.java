package io.github.katsumag.prace;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class PlayerManager {

    private static HashMap<UUID, Double> money = new HashMap<>();
    private static HashMap<UUID, Double> exp = new HashMap<>();
    private BarManager manager = new BarManager();
    private Prace main;
    private BukkitRunnable task = new BukkitRunnable(){
        @Override
        public void run() {

            money.forEach((uuid, aDouble) -> {

                if (main.getEconomy().hasAccount(Bukkit.getOfflinePlayer(uuid))){
                    main.getEconomy().depositPlayer(Bukkit.getOfflinePlayer(uuid), aDouble);
                } else {
                    main.getEconomy().createPlayerAccount(Bukkit.getOfflinePlayer(uuid));
                    main.getEconomy().depositPlayer(Bukkit.getOfflinePlayer(uuid), aDouble);
                }

                money.remove(uuid);

            });
        }
    };

    public PlayerManager(Prace main){
        this.main = main;

        Bukkit.getServer().getScheduler().scheduleAsyncRepeatingTask(main, task, (20 * 60) * 5, 40);

    }

    public boolean hasMoney(UUID player){
        if (money.containsKey(player)){
            return true;
        } else return false;
    }

    public double getMoney(UUID player){
        if (hasMoney(player)){
            return money.get(player);
        }

        return 0;
    }

    public void addMoney(UUID player, double amount){
        if (hasMoney(player)){
            money.replace(player, money.get(player) + amount);
        } else {
            money.put(player, amount);
        }
    }

    public void setMoney(UUID player, double amount){
        if (hasMoney(player)){
            money.replace(player, amount);
        } else money.put(player, amount);
    }

    public void addEXP(UUID player, double amount){

        if (exp.containsKey(player)){
            this.exp.replace(player, this.exp.get(player) + amount);
        } else{
            exp.put(player, amount);
        }

        if (manager.hasBar(player)){
            manager.getBar(player).setProgress(exp.get(player));
        } else{
            manager.createBar(player, Prace.BarTime, BarColor.GREEN, BarStyle.SOLID);
        }


    }

    public double getEXP(UUID player){

        if (exp.containsKey(player)) return this.exp.get(player);
        return 0;

    }

}
