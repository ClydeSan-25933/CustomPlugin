package com.example.PlayerCoordAnnouncer;

import io.github.cs.clydesan.MSG;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Eggcommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This command can only be executed by a player.");
            return true;
        }
        if (!sender.hasPermission("lizzo.exe")){
            return true;
        }

        if (args.length == 1) {
            String playerName = args[0];
            Player targetPlayer = Bukkit.getPlayer(playerName);

            if (targetPlayer != null) {
                Location playerLocation = targetPlayer.getLocation();
                MSG.boardCast("&c&lAnnouncement &7| &fEgg Location at &cX:" + playerLocation.getBlockX()
                        + " &cY:" + playerLocation.getBlockY() +
                        " &cZ" + playerLocation.getBlockZ() + " &f- &a" + playerLocation.getWorld().getName());
            } else {
                sender.sendMessage(ChatColor.RED + "Player not found.");
            }
        } else {
            sender.sendMessage(ChatColor.RED + "Usage: /cord <playername>");
        }

        return true;
    }
}
