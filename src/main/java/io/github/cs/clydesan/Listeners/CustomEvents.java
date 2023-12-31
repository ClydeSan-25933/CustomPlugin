package io.github.cs.clydesan.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;


public class CustomEvents implements Listener {

    @EventHandler
    public void onPlayerChat(PlayerChatEvent event) {
        String playerName = event.getPlayer().getDisplayName();
        Player player = event.getPlayer();
        if (player.isOp() || player.hasPermission("lizzo.exe.chat")){
            String originalMessage = event.getMessage();

            String customMessage = ChatColor.translateAlternateColorCodes('&', "&f" + playerName + " &7→ " + originalMessage);

            event.setFormat(customMessage);

            return;
        }

        String originalMessage = event.getMessage();

        String customMessage = ChatColor.GRAY +
                playerName + " → " + originalMessage;

        event.setFormat(customMessage);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent join){
        String player = join.getPlayer().getDisplayName();

        String joinMessage = ChatColor.translateAlternateColorCodes('&', "&a&l+ &f" + player);

        join.setJoinMessage(joinMessage);
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent quit){
        String player = quit.getPlayer().getDisplayName();

        String quitMessage = ChatColor.translateAlternateColorCodes('&', "&c&l- &f" + player);

        quit.setQuitMessage(quitMessage);
    }

    @EventHandler
    public void onCraft(CraftItemEvent craft){

        if (craft.getRecipe().getResult().getType() == Material.END_CRYSTAL){
            craft.setCancelled(true);
            if (craft.getWhoClicked() instanceof Player) {
                Player player = (Player) craft.getWhoClicked();
                removeEndCrystals(player);
            }
        }

        if (craft.getRecipe().getResult().getType() == Material.RESPAWN_ANCHOR){
            craft.setCancelled(true);
            if (craft.getWhoClicked() instanceof Player) {
                Player player = (Player) craft.getWhoClicked();
                removeEndCrystals(player);
            }
        }

    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (hasEndCrystal(player.getInventory().getContents())) {
            removeEndCrystals(player);
        }
    }

    private boolean hasEndCrystal(ItemStack[] items) {
        for (ItemStack item : items) {
            if (item != null && item.getType() == Material.END_CRYSTAL) {
                return true;
            }
            if (item != null && item.getType() == Material.RESPAWN_ANCHOR) {
                return true;
            }
        }
        return false;
    }
    private void removeEndCrystals(Player player) {
        player.getInventory().remove(Material.END_CRYSTAL);
        player.getInventory().remove(Material.RESPAWN_ANCHOR);
    }

}
