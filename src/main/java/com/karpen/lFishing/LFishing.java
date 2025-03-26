package com.karpen.lFishing;

import com.karpen.lFishing.boxes.DefaultBox;
import com.karpen.lFishing.boxes.EpicBox;
import com.karpen.lFishing.boxes.NormalBox;
import com.karpen.lFishing.commands.ReloadCommand;
import com.karpen.lFishing.commands.ReloadCompleter;
import com.karpen.lFishing.commands.TopCommand;
import com.karpen.lFishing.listeners.FishingListener;
import com.karpen.lFishing.models.Config;
import com.karpen.lFishing.utils.TopManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class LFishing extends JavaPlugin {

    private DefaultBox defaultBox;
    private EpicBox epicBox;
    private NormalBox normalBox;

    private Config config;

    private File file;

    private TopManager topManager;

    private FishingListener fishingListener;

    private ReloadCommand reloadCommand;
    private ReloadCompleter reloadCompleter;

    private TopCommand topCommand;

    @Override
    public void onEnable() {
        config = new Config();

        loadConfig();

        file = new File(getDataFolder(), "top.json");

        if (!file.exists()){
            getDataFolder().mkdirs();
        }

        defaultBox = new DefaultBox(config);
        epicBox = new EpicBox(config);
        normalBox = new NormalBox(config);

        topManager = new TopManager(file);

        reloadCommand = new ReloadCommand(config, this);
        reloadCompleter = new ReloadCompleter();

        topCommand = new TopCommand(topManager);

        fishingListener = new FishingListener(defaultBox, epicBox, normalBox, config, topManager);

        Bukkit.getPluginManager().registerEvents(defaultBox, this);
        Bukkit.getPluginManager().registerEvents(epicBox, this);
        Bukkit.getPluginManager().registerEvents(normalBox, this);

        Bukkit.getPluginManager().registerEvents(fishingListener, this);

        getCommand("lfishing").setExecutor(reloadCommand);
        getCommand("lfishing").setTabCompleter(reloadCompleter);

        getCommand("topbox").setExecutor(topCommand);
    }

    public void loadConfig(){
        saveDefaultConfig();

        Configuration configuration = getConfig();

        config.setDefaultChance(configuration.getInt("chances.default", 10));
        config.setNormalChance(configuration.getInt("chances.normal", 5));
        config.setEpicChance(configuration.getInt("chances.epic", 3));
        config.setMifikChance(configuration.getInt("chances.mifik", 1));
        config.setLegendChance(configuration.getInt("chances.legend", 0));

        config.setDefaultMsg(configuration.getString("msg.default", "Вы поймали стандартную коробку!"));
        config.setNormalMsg(configuration.getString("msg.normal", "Вы поймали стандартную бочку!"));
        config.setEpicMsg(configuration.getString("msg.epic", "Вы поймали эпическую коробку!"));
        config.setMifikMsg(configuration.getString("msg.mifik", "Вы поймали мифическую коробку!"));
        config.setLegendMsg(configuration.getString("msg.legend", "Вы поймали легендарную коробку!"));

        config.setChatEpic(configuration.getString("chat.epic", " выловил эпическую коробку!"));
        config.setChatMifik(configuration.getString("chat.mifik", " выловил мифическую коробку!"));
        config.setChatLegend(configuration.getString("chat.legend", " выловил легендарную коробку!"));

        config.setErrArgs(configuration.getString("reload.args", "Используйте /lfishing reload"));
        config.setReloadDone(configuration.getString("reload.done", "Плагин перезагружен"));

        config.setNormalName(configuration.getString("names.normal", "Стандартная коробка"));
        config.setDefaultName(configuration.getString("names.default", "Стандартная бочка"));
        config.setEpicName(configuration.getString("names.epic", "Эпическая коробка"));
        config.setMifikName(configuration.getString("names.mifik","Мифическая коробка"));
        config.setLegendName(configuration.getString("names.legend", "Легендарная коробка"));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
