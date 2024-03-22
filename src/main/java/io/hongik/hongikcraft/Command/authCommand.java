package io.hongik.hongikcraft.Command;

import io.hongik.hongikcraft.Data.DataBase;
import io.hongik.hongikcraft.Hongik;
import io.hongik.hongikcraft.Listener.OnJoinListener;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;

public class authCommand implements CommandExecutor {

    private Hongik instance = Hongik.getInstance();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player) || args.length != 1){
            return false;
        }
        try {
            authUser((Player) sender, args[0]);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean authUser(Player p, String id) throws SQLException {
        if(id.length() != 7){
            p.sendTitle("§c§l등록 실패", "§e올바르지 않은 학번입니다. ", 0, 50, 5);
            return false;
        }
        id = id.toUpperCase();
        String sql = String.format("SELECT uuid FROM server_data.user WHERE uuid = \"%s\";", p.getUniqueId());
        String getData = DataBase.getDatabaseString(sql, "uuid");
        if (getData == null) {
            int joined = Integer.parseInt(id.charAt(0) - 65 + id.substring(1, 2));
            String class_ = id.substring(2, 4);
            class_ = instance.getClassDataFile().getString(String.format("%s.name", class_));
            if(class_ == null){
                p.sendTitle("§c§l등록 실패", "§e올바르지 않은 학번입니다. ", 0, 50, 5);
                return false;
            }
            String inserts_sql = String.format("INSERT INTO server_data.user (uuid, ID, Joined, Class) VALUES (\"%s\", \"%s\", \"%s\", \"%s\");", p.getUniqueId(), id, joined, class_);
            DataBase.insertDataBaseString(inserts_sql);
            p.sendTitle("§a§l등록 성공", String.format("§b§l[ §f§l%s §b§l]", id), 0, 50, 5);
            p.setGameMode(GameMode.SURVIVAL);
            OnJoinListener.setDisplay(p);
            return true;
        }
        p.sendTitle("§c§l등록 실패", "§e이미 등록된 계정 & 학번입니다. ", 0, 50, 5);
        return false;
    }

}
