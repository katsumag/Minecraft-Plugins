package io.github.katsumag.prace;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.HashMap;
import java.util.UUID;

public class BarManager implements Listener{

    private static HashMap<UUID, BossBar> bars = new HashMap<>();

    public HashMap<UUID, BossBar> getBars(){
        return bars;
    }

    public boolean hasBar(UUID player){
        if (getBars().containsKey(player)) return true;
        return false;
    }

    public BossBar getBar(UUID player){

        if (hasBar(player)){
            return getBars().get(player);
        } else return null;

    }

    public void createBar(UUID player, String title, BarColor colour, BarStyle style){
        BossBar bar = Bukkit.createBossBar(title, colour, style);
        getBars().put(player, bar);
        bar.addPlayer(Bukkit.getPlayer(player));

    }

    public void updateBar(UUID player, String title){
        if (hasBar(player)){
            getBar(player).setTitle(title);
        }
    }

    public double getProgress(UUID player){
        return getBar(player).getProgress();
    }

    public void setProgress(UUID player, int progress){
        switch (Prace.db.getCurrentJob(player)){
            case "Miner":
                int base = 400 + (Prace.db.getMinerLevel(player) * 200);
                int newProgress = base + progress;
                double toGet = newProgress + 200;
                getBar(player).setProgress(newProgress / toGet);
                Prace.db.setMinerEXP(player, newProgress);

                if (toGet >= 1){
                    Prace.db.setMinerLevel(player, Prace.db.getMinerLevel(player) + 1);
                }

                return;

            case "WoodCutter":
                double base1 = 400 + (Prace.db.getWoodCutterLevel(player) * 200);
                double newProgress1 = base1 + progress;
                double toGet1 = newProgress1 + 200;
                getBar(player).setProgress(newProgress1 / toGet1);

                if (toGet1 >= 1){
                    Prace.db.setWoodCutterLevel(player, Prace.db.getWoodCutterLevel(player) + 1);
                }

                return;

            case "Builder":
                double base2 = 400 + (Prace.db.getBuilderLevel(player) * 200);
                double newProgress2 = base2 + progress;
                double toGet2 = newProgress2 + 200;
                getBar(player).setProgress(newProgress2 / toGet2);

                if (toGet2 >= 1){
                    Prace.db.setBuilderLevel(player, Prace.db.getBuilderLevel(player) + 1);
                }

        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        if (hasBar(e.getPlayer().getUniqueId())){
            getBar(e.getPlayer().getUniqueId()).removePlayer(e.getPlayer());
            createBar(e.getPlayer().getUniqueId(), Prace.BarTime, BarColor.BLUE, BarStyle.SOLID);
        } else createBar(e.getPlayer().getUniqueId(), Prace.BarTime, BarColor.BLUE, BarStyle.SOLID);
    }

}
