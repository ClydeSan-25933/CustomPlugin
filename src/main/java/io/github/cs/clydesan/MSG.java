package io.github.cs.clydesan;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public class MSG {

    public static void send(CommandSender player, String message) {
        String formattedMessage = ChatColor.translateAlternateColorCodes('&', message);
        player.sendMessage(formattedMessage);
    }
    public static void sendCS(CommandSender player, String message) {
        String formattedMessage = ChatColor.translateAlternateColorCodes('&', message);
        player.sendMessage(formattedMessage);
    }

    public static void boardCast(String message) {
        String formattedMessage = ChatColor.translateAlternateColorCodes('&', message);
        Bukkit.broadcastMessage(formattedMessage);
    }
}


