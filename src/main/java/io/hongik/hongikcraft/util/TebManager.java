package io.hongik.hongikcraft.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class TebManager {
    public static void setTebList(){
        int playerAmount = Bukkit.getOnlinePlayers().size();
        for(Player p : Bukkit.getOnlinePlayers()){
            p.setPlayerListFooter(String.format("§b접속자 §e%d§b명 §7/ §bTps §e%f", playerAmount, Bukkit.getServer().getTPS()[0]));
        }
    }
}
