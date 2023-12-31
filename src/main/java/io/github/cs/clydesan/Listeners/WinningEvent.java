package io.github.cs.clydesan.Listeners;

import io.github.cs.clydesan.MSG;
import io.github.cs.clydesan.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.bukkit.inventory.ItemStack;

public class WinningEvent implements Listener{

    @EventHandler
    public void onDragonKill(PlayerAdvancementDoneEvent dragon){

        if(dragon.getAdvancement().getKey().getKey().equals("end/kill_dragon")){
            String player = dragon.getPlayer().getDisplayName();

            MSG.boardCast(" ");
            MSG.boardCast("&e&l          &e&lEvent");
            MSG.boardCast("&b" + player + " &fKilled the Ender Dragon.");
            MSG.boardCast("&f    &fAnd achieve &e#2 &fposition.");
            MSG.boardCast(" ");
        }

        if (dragon.getAdvancement().getKey().getKey().equals("end/dragon_egg")){
            String player = dragon.getPlayer().getDisplayName();

            MSG.boardCast(" ");
            MSG.boardCast("&e&l                   &e&lEvent");
            MSG.boardCast("&b            &b" + player + " &f&fHas the &5&lDragon Egg.");
            MSG.boardCast("&f &fIn order to win a player needs to Place the Ender Dragon egg ");
            MSG.boardCast("&f              &fat &eX:1321 | Y:65 | Z:3855");
            MSG.boardCast(" ");

        }

    }
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        World world = Bukkit.getWorld("world");

        Player player = event.getPlayer();
        Block block = event.getBlockPlaced();

        if (block.getType() == Material.DRAGON_EGG && isEnderDragonEggLocation(block.getLocation())) {
            Location newLocation = new Location(world, 26, 18, 24);
            transferDragonEgg(block.getLocation(), newLocation);

            MSG.boardCast(" ");
            MSG.boardCast("&e&l                             Event");
            MSG.boardCast("&f       &b" + player.getName() + "&f has placed the Ender Dragon egg.");
            MSG.boardCast("&f                 &fAnd achieve &e#1 &fposition.");
            MSG.boardCast(" ");
            MSG.boardCast("&f        &fThanks to all players that have played the Event.");
            MSG.boardCast("&f                &fWe Hope you guys like the Event.");
            MSG.boardCast("&f         &fIn future more Event like this will be Hosted by ");
            MSG.boardCast("&e                    &eAzerith Asia Network&f.");
            MSG.boardCast("&f        &fIf you didn't win this time Better luck next Time.");
            MSG.boardCast(" ");

            MSG.boardCast("&c&lAnnounce &7| &fThe Winners of the Event are Requested to create ticket at &eAzerith MC &fDiscord Server.");
            Location teleportLOC = new Location(world,26.5, 14.5, 34.5, 180, -15);
            for (Player playerOnline: Main.getInstance().getServer().getOnlinePlayers()){
                playerOnline.teleport(teleportLOC);
            }

        }
        else if (block.getType() != Material.DRAGON_EGG && isEnderDragonEggLocation(block.getLocation())) {
            event.setCancelled(true);
            MSG.sendCS(player, "&cOnly Ender Dragon Egg can be placed here.");
        }


    }

    private boolean isEnderDragonEggLocation(Location location) {
        return location.getBlockX() == 1321 && location.getBlockY() == 65 && location.getBlockZ() == 3855;
    }

    private void transferDragonEgg(Location fromLocation, Location toLocation) {
        fromLocation.getBlock().setType(Material.AIR);

        toLocation.getBlock().setType(Material.DRAGON_EGG);
    }

}
