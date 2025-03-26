package com.karpen.lFishing;

import com.karpen.lFishing.boxes.DefaultBox;
import com.karpen.lFishing.boxes.EpicBox;
import com.karpen.lFishing.boxes.NormalBox;
import com.karpen.lFishing.commands.ReloadCommand;
import com.karpen.lFishing.commands.ReloadCompleter;
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

    private ReloadCommand reloadCommand;
    private ReloadCompleter reloadCompleter;

    @Override
    public void onEnable() {
        config = new Config();

        loadConfig();

        defaultBox = new DefaultBox(config);
        epicBox = new EpicBox(config);
        normalBox = new NormalBox(config);

        reloadCommand = new ReloadCommand(config, this);
        reloadCompleter = new ReloadCompleter();

        fishingListener = new FishingListener(defaultBox, epicBox, normalBox, config);

        Bukkit.getPluginManager().registerEvents(defaultBox, this);
        Bukkit.getPluginManager().registerEvents(epicBox, this);
        Bukkit.getPluginManager().registerEvents(normalBox, this);

        Bukkit.getPluginManager().registerEvents(fishingListener, this);

        getCommand("lfishing").setExecutor(reloadCommand);
        getCommand("lfishing").setTabCompleter(reloadCompleter);
    }

    public void loadConfig(){
        saveDefaultConfig();

        Configuration configuration = getConfig();

        config.setDefaultChance(configuration.getInt("chances.default", 10));
        config.setNormalChance(configuration.getInt("chances.normal", 5));
        config.setEpicChance(configuration.getInt("chances.epic", 3));

        config.setDefaultMsg(configuration.getString("msg.default", "Вы поймали стандартную коробку!"));
        config.setNormalMsg(configuration.getString("msg.normal", "Вы поймали стандартную бочку!"));
        config.setEpicMsg(configuration.getString("msg.epic", "Вы поймали эпическую коробку!"));

        config.setErrArgs(configuration.getString("reload.args", "Используйте /lfishing reload"));
        config.setReloadDone(configuration.getString("reload.done", "Плагин перезагружен"));

        config.setNormalName(configuration.getString("names.normal", "Стандартная коробка"));
        config.setDefaultName(configuration.getString("names.default", "Стандартная бочка"));
        config.setEpicName(configuration.getString("names.epic", "Эпическая коробка"));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
