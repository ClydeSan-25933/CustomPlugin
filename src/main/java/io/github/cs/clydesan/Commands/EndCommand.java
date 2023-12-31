package io.github.cs.clydesan.Commands;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class EndCommand implements CommandExecutor {

    private boolean allowEnd = true;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("true")) {
                allowEnd = true;
                sender.sendMessage("The End dimension is now enabled.");
                return true;
            }
            if (args[0].equalsIgnoreCase("false")) {
                allowEnd = false;
                closeEnd();
                sender.sendMessage("The End dimension is now disabled.");
            } else {
                sender.sendMessage("Usage: /allow-end <true|false>");
                return true;
            }
        } else {
            sender.sendMessage("Usage: /allow-end <true|false>");
            return true;
        }
        return true;
    }

    private void closeEnd() {
        World endWorld = Bukkit.getWorld("world_the_end");
        if (endWorld != null) {
            Bukkit.unloadWorld(endWorld, false);
        }
    }
}
