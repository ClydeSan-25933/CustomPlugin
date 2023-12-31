package io.github.cs.clydesan.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event){
        Player player = event.getEntity();

        if(player.hasPermission("lizzo.exe")){
           return;
        }

        Location location = player.getLocation();

        Double xd = location.getX(), yd = location.getY(), zd = location.getZ();
        Integer x = xd.intValue(), y = yd.intValue(), z = zd.intValue();

        player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                "&c[Death] &eYou died at &bx:&f" + x + ", &by:&f" + y + ", &bz:&f" + z));

    }
}
