package io.github.cs.clydesan.Commands;

import io.github.cs.clydesan.MSG;
import io.github.cs.clydesan.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MSGCommand implements CommandExecutor, TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        Player player = (Player) sender;

        if (sender instanceof Player) {

            if (args.length == 0) {
                MSG.sendCS(sender, "&7/msg <player> <message>");
                return true;
            }
            if (args.length < 2) {
                MSG.sendCS(sender, "&7/msg <player> <message>");
                return true;
            }
            String targetName = args[0];
            Player targetPlayer = Main.getInstance().getServer().getPlayer(targetName);
            String message = String.join(" ", Arrays.copyOfRange(args, 1, args.length));

            if (targetPlayer == null) {
                MSG.sendCS(sender, "&cPlayer is not Online");
            }
            if (Objects.equals(targetPlayer.getName(), sender.getName())) {
                MSG.sendCS(sender, "&cYou can't Message Youself");
                return true;
            }
            sendPrivateMessage(player, targetPlayer.getName(), message);

            return true;

        }
        return false;
    }

    @Override
    public List<String> onTabComplete (CommandSender commandSender, Command command, String s, String[]args){
        List<String> suggestions = new ArrayList<>();

        if (args.length == 1) {
            for (Player player : Main.getInstance().getServer().getOnlinePlayers()) {
                String playerName = player.getName();
                if (playerName.toLowerCase().startsWith(args[0].toLowerCase())) {
                    suggestions.add(playerName);
                }
            }
        }

        return suggestions;
    }
    public static void sendPrivateMessage (Player sender, String targetName, String message){
        Player target = Main.getInstance().getServer().getPlayer(targetName);

        MSG.send(target, "&e" + sender.getDisplayName() + " -> You: &f" + message);
        MSG.send(sender, "&eYou -> " + target.getDisplayName() +": &f" + message);

    }
}
