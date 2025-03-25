package com.karpen.lFishing;

import com.karpen.lFishing.boxes.DefaultBox;
import com.karpen.lFishing.boxes.EpicBox;
import com.karpen.lFishing.boxes.NormalBox;
import com.karpen.lFishing.listeners.FishingListener;
import com.karpen.lFishing.models.Config;
import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.java.JavaPlugin;

public final class LFishing extends JavaPlugin {

    private DefaultBox defaultBox;
    private EpicBox epicBox;
    private NormalBox normalBox;

    private Config config;

    private FishingListener fishingListener;

    @Override
    public void onEnable() {
        config = new Config();

        loadConfig();

        defaultBox = new DefaultBox(config);
        epicBox = new EpicBox(config);
        normalBox = new NormalBox(config);

        fishingListener = new FishingListener(defaultBox, epicBox, normalBox, config);

        Bukkit.getPluginManager().registerEvents(defaultBox, this);
        Bukkit.getPluginManager().registerEvents(epicBox, this);
        Bukkit.getPluginManager().registerEvents(normalBox, this);

        Bukkit.getPluginManager().registerEvents(fishingListener, this);
    }

    private void loadConfig(){
        saveDefaultConfig();

        Configuration configuration = getConfig();

        config.setDefaultChance(configuration.getInt("chances.default", 20));
        config.setNormalChance(configuration.getInt("chances.normal", 10));
        config.setEpicChance(configuration.getInt("chances.epic", 5));

        config.setDefaultMsg(configuration.getString("msg.default", "Вы поймали стандартную коробку!"));
        config.setNormalMsg(configuration.getString("msg.normal", "Вы поймали стандартную бочку!"));
        config.setEpicMsg(configuration.getString("msg.epic", "Вы поймали эпическую коробку!"));

        config.setNormalName(configuration.getString("names.normal", "Стандартная коробка"));
        config.setDefaultName(configuration.getString("names.default", "Стандартная бочка"));
        config.setEpicName(configuration.getString("names.epic", "Эпическая коробка"));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
