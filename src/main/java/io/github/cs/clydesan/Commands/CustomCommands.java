package io.github.cs.clydesan.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CustomCommands implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (sender instanceof Player){

            Player player = (Player) sender;

            if (args.length == 0){
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c/heart withdraw"));
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c/heart recipe"));
                return true;
            }

            if (args[0].equalsIgnoreCase("withdraw")){
                player.performCommand("lswithdraw");
                return true;
            }
            if (args[0].equalsIgnoreCase("recipe")){
                player.performCommand("lsrecipe");
                return true;
            }

        }else{
            return true;
        }

        return false;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> tabCompletions = new ArrayList<>();

        if (args.length == 1) {
            tabCompletions.add("withdraw");
            tabCompletions.add("recipe");
        }


        return tabCompletions;
    }
}
