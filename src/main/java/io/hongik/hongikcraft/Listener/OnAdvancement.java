package io.hongik.hongikcraft.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;

public class OnAdvancement implements Listener {
    @EventHandler
    public void OnAdvancement(PlayerAdvancementDoneEvent e){
        e.message(null);
    }
}
