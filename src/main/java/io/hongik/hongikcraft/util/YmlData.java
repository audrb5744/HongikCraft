package io.hongik.hongikcraft.util;

import io.hongik.hongikcraft.Hongik;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class YmlData {
    private final Hongik instance = Hongik.getInstance();
    public FileConfiguration dataFile;

    public FileConfiguration CreateDataFile(String dataFileName) {
        File playerDataFile = new File(instance.getDataFolder(), dataFileName);
        if (!playerDataFile.exists()) {
            playerDataFile.getParentFile().mkdirs();
            instance.saveResource(dataFileName, true);
        }

        dataFile = new YamlConfiguration();
        try {
            dataFile.load(playerDataFile);
        } catch (IOException | InvalidConfigurationException e) {
            System.out.println(e.getMessage());
        }
        return dataFile;
    }
}
