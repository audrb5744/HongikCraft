package io.hongik.hongikcraft.Listener;

import io.hongik.hongikcraft.Hongik;
import io.hongik.hongikcraft.util.AdvancementInfo;
import io.hongik.hongikcraft.Data.DataBase;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.SQLException;

public class OnJoinListener implements Listener {
    private Hongik instance = Hongik.getInstance();

    @EventHandler
    public void onFirstJoin(PlayerJoinEvent e) throws SQLException {
        Player p = e.getPlayer();
        e.setJoinMessage(null);
        //e.setJoinMessage("§a[+] §f" + p.getName());
        String query = String.format("SELECT uuid FROM server_data.user WHERE uuid = \"%s\";", p.getUniqueId());
        if(!p.hasPlayedBefore()){{
            Bukkit.getServer().broadcastMessage("§a§l[+] §f" + p.getName());
        }}
        if(DataBase.getDatabaseString(query,"uuid") == null){
            p.sendTitle("§f채팅창에 §b/등록 {학번} §f을 입력해주세요", "§eex) /등록 C000000", 0, 999999999, 0);
            p.setGameMode(GameMode.ADVENTURE);
            return;
        }
        setDisplay(p);
        AdvancementInfo.displayTo(p, Material.DIAMOND,instance.getMessageDataFile().getString("joinMessage.message"), "task");
        p.setPlayerListHeader(ChatColor.of("#002c62") + "§l [ §f§lHONGIK UNIVERSITY " + ChatColor.of("#002c62") + "§l]");
    }

    public static void setDisplay(Player p) throws SQLException {
        String query = String.format("SELECT Joined, Class FROM server_data.user WHERE uuid = \"%s\";", p.getUniqueId());
        String joined = DataBase.getDatabaseString(query,"Joined");
        String class_ = DataBase.getDatabaseString(query, "Class");
        String name = String.format("§b[%s학번] §a[%s] §f%s", joined, class_, p.getName());
        p.setDisplayName(name);
        p.setPlayerListName(name);
    }
}
