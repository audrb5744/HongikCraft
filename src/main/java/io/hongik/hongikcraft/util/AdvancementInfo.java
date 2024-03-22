package io.hongik.hongikcraft.util;

import io.hongik.hongikcraft.Hongik;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;

import java.util.UUID;

public final class AdvancementInfo {

    private final NamespacedKey key;
    private final Material icon;
    private final String message;
    private final String style;

    public static void displayTo(Player p, Material icon, String message, String style){
        new AdvancementInfo(icon, message, style).start(p);
    }

    private AdvancementInfo(Material icon, String message, String style){
        this.key = new NamespacedKey(Hongik.getInstance(), UUID.randomUUID().toString());
        this.icon = icon;
        this.message = message;
        this.style = style;
    }

    private void start(Player p){
        createAdvancement();
        grantAdvancement(p);

        Bukkit.getScheduler().runTaskLater(Hongik.getInstance(), () -> {
            revokeAdnacement(p);
        }, 10);
    }

    private void createAdvancement(){
        Bukkit.getUnsafe().loadAdvancement(key, "{\n" +
                "     \"criteria\": {\n" +
                "          \"trigger\": {\n" +
                "           \"trigger\": \"minecraft:impossible\"\n" +
                "       }\n" +
                "      }, \n" +
                "  \"display\": {\n" +
                "    \"icon\": {\n" +
                "      \"item\": \"minecraft:" + icon.name().toLowerCase() + "\"\n" +
                "    },\n" +
                "    \"title\": \"" + message.replace("|","\n") + "\",\n" +
                "    \"description\": {\n" +
                "           \"text\": \"\"\n" +
                "       },\n" +
                "      \"background\": \"minecraft:texture/gui/advancements/backgrounds/adventure.png\",\n" +
                "      \"frame\": \"" + style.toLowerCase() + "\",\n" +
                "      \"announce_to_chat\": false, \n" +
                "      \"show_toast\": true,\n" +
                "      \"hidden\": true\n" +
                "    },\n" +
                "    \"requirements\": [\n" +
                "      [\n" +
                "         \"trigger\"\n" +
                "      ]\n" +
                "  ]\n" +
                "}");
    }

    private void grantAdvancement(Player p){
        p.getAdvancementProgress(Bukkit.getAdvancement(key)).awardCriteria("trigger");
    }

    private void revokeAdnacement(Player p){
        p.getAdvancementProgress(Bukkit.getAdvancement(key)).revokeCriteria("trigger");
    }
}
