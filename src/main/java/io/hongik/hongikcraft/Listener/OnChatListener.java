package io.hongik.hongikcraft.Listener;

import io.hongik.hongikcraft.Data.DataBase;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import java.sql.SQLException;

public class OnChatListener implements Listener {
    @EventHandler
    public void onChat(PlayerChatEvent e) throws SQLException {
        e.setCancelled(true);
        Player p = e.getPlayer();
        String query = String.format("SELECT uuid FROM server_data.user WHERE uuid = \"%s\";", p.getUniqueId());
        if(DataBase.getDatabaseString(query,"uuid") != null) {
            Bukkit.getServer().broadcastMessage(p.getDisplayName() + " §7|§f " + e.getMessage());
        }
    }
}
