package io.github.cs.clydesan.Listeners;

import io.github.cs.clydesan.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Random;

public class RandomSpawnEvent implements Listener, CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("CustomPlugin")) {
                teleportRandom(player);
                return true;
            }
        }
        return false;
    }

    @EventHandler
    public void onFirstJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPlayedBefore()) {
            teleportRandom(player);
        }
    }

    public static void teleportRandom(Player player) {
        World world = player.getWorld();
        Random random = new Random();

        int maxAttempts = 10;
        int attempt = 0;
        Location randomLocation;

        do {
            int x = random.nextInt(100);
            int z = random.nextInt(100);
            int y = world.getHighestBlockYAt(x, z);

            randomLocation = new Location(world, x + 0.5, y + 0.5, z + 0.5);

            Material blockType = randomLocation.getBlock().getType();
            boolean isWater = blockType == Material.WATER;

            if (!isWater) {
                break;
            }

            attempt++;
        } while (attempt < maxAttempts);

        if (attempt >= maxAttempts) {
            Main.getInstance().getLogger().warning("Failed to find a suitable location after multiple attempts.");
        } else {
            player.teleport(randomLocation);
        }
    }
}
