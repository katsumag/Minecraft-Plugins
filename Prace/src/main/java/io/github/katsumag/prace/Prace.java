package io.github.katsumag.prace;

import io.github.katsumag.prace.GUI.Selector;
import io.github.katsumag.prace.Jobs.Builder;
import io.github.katsumag.prace.Jobs.Miner;
import io.github.katsumag.prace.Jobs.WoodCutter;
import io.github.katsumag.prace.SQL.Database;
import io.github.katsumag.prace.SQL.Errors;
import io.github.katsumag.prace.SQL.SQLite;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.apache.commons.lang.time.DurationFormatUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public final class Prace extends JavaPlugin {

    public static Database db;
    private  PlayerManager manager;
    public static String BarTime = "5:00";
    public static int Time = 300;
    private static Economy econ;

    @Override
    public void onEnable() {
        // Plugin startup logic

        getDataFolder().mkdir();

        this.db = new SQLite(this);
        this.db.load();

        Bukkit.getServer().getPluginManager().registerEvents(new Miner(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new WoodCutter(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new Builder(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new Selector(this), this);

        try {

            ResultSet set = getDataBase().getSQLConnection().prepareStatement("SELECT UUID FROM Prace;").executeQuery();

            for (String uuid : getStringList(set, "UUID")) {
                Player p = Bukkit.getPlayer(uuid);
                p.setMetadata("Job", new FixedMetadataValue(this, getDataBase().getCurrentJob(UUID.fromString(uuid))));
            }


        } catch (SQLException | IOException e) {
            Errors.sqlConnectionExecute();
            e.printStackTrace();
        }

        //VAULT LOAD ---------------------------------------------------------------------------------------------------
        if (! setupEconomy()){
            getLogger().severe("- Disabled due to no Vault dependency found!");
            getServer().getPluginManager().disablePlugin(this);
        }
         manager = new PlayerManager(this);


        BukkitRunnable task = new BukkitRunnable() {
            @Override
            public void run() {
                BarTime = DurationFormatUtils.formatDuration(Time, "m:ss");

                    new BarManager().getBars().forEach((uuid, bossBar) -> {
                        bossBar.setTitle(BarTime);
                    });
            }
        };

        Bukkit.getServer().getScheduler().scheduleAsyncRepeatingTask(this, task, 20, 0);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getDataBase().shutdown(getDataBase().getSQLConnection());
    }

    public Database getDataBase(){
        return this.db;
    }

    public String[] getStringList(ResultSet rs, String column) throws IOException, SQLException {
        return (String[]) rs.getArray(column).getArray();
    }

    public PlayerManager getPlayerManager(){
        return this.manager;
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    public static Economy getEconomy() {
        return econ;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase("Prace")){

            if (sender instanceof Player){
                ((Player) sender).openInventory(new Selector(this).inventory);
            } else return true;

        } else return true;

        return true;
    }
}
