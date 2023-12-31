package io.github.cs.clydesan.Commands;

import io.github.cs.clydesan.Main;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class PVP implements CommandExecutor {

    private String status;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

            if (args.length == 0){
                if (sender.hasPermission("togglePVP")) {
                    togglePvP();
                }
                return true;
            }

            return false;
        }

    private void togglePvP() {


        for(World worlds : Bukkit.getWorlds()){
            boolean cpvp = worlds.getPVP();

            worlds.setPVP(!cpvp);

            status = !cpvp ? "enabled" : "disabled";

        }

        if(status.equalsIgnoreCase("enabled")){
            Main.getInstance().getServer().broadcastMessage(ChatColor.GREEN + "PvP has been " + status);
            return;
        }
        if(status.equalsIgnoreCase("disabled")){
            Main.getInstance().getServer().broadcastMessage(ChatColor.RED + "PvP has been " + status);
        }
    }
}
