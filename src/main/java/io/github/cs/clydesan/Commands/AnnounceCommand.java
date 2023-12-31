package io.github.cs.clydesan.Commands;

import io.github.cs.clydesan.MSG;
import io.github.cs.clydesan.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class AnnounceCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (args.length == 0){
            MSG.sendCS(sender, "&c/announce <message>");
            return true;
        }
        if (sender.hasPermission("lizzo.exe")) {
            String message = String.join(" ", Arrays.copyOfRange(args, 0, args.length));
            for (Player player : Main.getInstance().getServer().getOnlinePlayers()){
                MSG.send(player, "&c&lAnnouncement &7| " + message);
            }
            Main.getInstance().log(message);
            return true;
        }

        return false;
    }
}
