package io.hongik.hongikcraft.Command;

import io.hongik.hongikcraft.Hongik;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class LinkCommand implements CommandExecutor {

    private Hongik instance = Hongik.getInstance();
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(args.length != 0){
            return false;
        }
        List<String> links = instance.getMessageDataFile().getStringList("linkMessage");
        sender.sendMessage("§e§l==================================");
        for (String i : links) {
            sender.sendMessage(ChatColor.of(i.substring(0,7)) + i.substring(7));
        }
        sender.sendMessage("§e§l==================================");
        return true;
    }
}
