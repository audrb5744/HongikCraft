package io.hongik.hongikcraft;

import io.hongik.hongikcraft.Command.LinkCommand;
import io.hongik.hongikcraft.Command.authCommand;
import io.hongik.hongikcraft.Listener.OnChatListener;
import io.hongik.hongikcraft.Listener.OnJoinListener;
import io.hongik.hongikcraft.Listener.OnQuitListener;
import io.hongik.hongikcraft.util.TebManager;
import io.hongik.hongikcraft.util.YmlData;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class Hongik extends JavaPlugin {

    public static Hongik instance;

    private static FileConfiguration classData;
    private static FileConfiguration messageData;

    @Override
    public void onEnable() {
        instance = this;
        DataFileLoad();

        getCommand("등록").setExecutor(new authCommand());
        getCommand("링크").setExecutor(new LinkCommand());

        getServer().getPluginManager().registerEvents(new OnJoinListener(), this);
        getServer().getPluginManager().registerEvents(new OnQuitListener(), this);
        getServer().getPluginManager().registerEvents(new OnChatListener(), this);

        getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            public void run() {
                TebManager.setTebList();
            }
        }, 0L, 20L);

        getLogger().info("굿");
        Bukkit.getServer().setMotd(ChatColor.of("#002c62") + "§l          [ §f§lWELCOME TO HONGIK UNIVERSITY" + ChatColor.of("#002c62") + " §l]");
    }

    private void DataFileLoad(){
        classData = new YmlData().CreateDataFile("ClassData.yml");
        messageData = new YmlData().CreateDataFile("MessageData.yml");
    }

    public FileConfiguration getClassDataFile(){
        return classData;
    }

    public FileConfiguration getMessageDataFile(){
        return messageData;
    }


    public static Hongik getInstance(){
        return instance;
    }
}
