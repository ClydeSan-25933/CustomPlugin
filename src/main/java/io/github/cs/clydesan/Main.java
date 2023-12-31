package io.github.cs.clydesan;

import io.github.cs.clydesan.Commands.*;
import io.github.cs.clydesan.Listeners.*;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main instance;

    @Override
    public void onEnable() {
        instance = this;

        log("Plugin Has Started");

        startCommand();
        startListeners();

    }

    public static Main getInstance() {
        return instance;
    }

    public void log(String message){
        getServer().getLogger().info(ChatColor.translateAlternateColorCodes('&', message));
    }

    public void startListeners(){
        getServer().getLogger().info("Plugin Has Successfully started");
        getServer().getPluginManager().registerEvents(new DeathListener(), this);
        getServer().getPluginManager().registerEvents(new RandomSpawnEvent(), this);
        getServer().getPluginManager().registerEvents(new CustomEvents(), this);
        getServer().getPluginManager().registerEvents(new WinningEvent(), this);
    }

    public void startCommand(){
        getCommand("heart").setExecutor(new CustomCommands());
        getCommand("random").setExecutor(new RandomSpawnEvent());
        getCommand("togglepvp").setExecutor(new PVP());
        getCommand("msg").setExecutor(new MSGCommand());
        getCommand("announce").setExecutor(new AnnounceCommand());
        getCommand("event").setExecutor(new EventCommand());
        getCommand("allow-end").setExecutor(new EndCommand());
        getCommand("cord").setExecutor(new EndCommand());
    }

    @Override
    public void onDisable() {
        getServer().getLogger().info("Plugin Has been Disabled");
    }
}
