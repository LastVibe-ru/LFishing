package com.karpen.lFishing;

import com.karpen.lFishing.boxes.DefaultBox;
import com.karpen.lFishing.boxes.EpicBox;
import com.karpen.lFishing.boxes.NormalBox;
import com.karpen.lFishing.listeners.FishingListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class LFishing extends JavaPlugin {

    private DefaultBox defaultBox;
    private EpicBox epicBox;
    private NormalBox normalBox;

    private FishingListener fishingListener;

    @Override
    public void onEnable() {
        defaultBox = new DefaultBox();
        epicBox = new EpicBox();
        normalBox = new NormalBox();

        fishingListener = new FishingListener(defaultBox, epicBox, normalBox);

        Bukkit.getPluginManager().registerEvents(defaultBox, this);
        Bukkit.getPluginManager().registerEvents(epicBox, this);
        Bukkit.getPluginManager().registerEvents(normalBox, this);

        Bukkit.getPluginManager().registerEvents(fishingListener, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
