package com.karpen.lFishing;

import com.karpen.lFishing.boxes.*;
import com.karpen.lFishing.commands.*;
import com.karpen.lFishing.listeners.FishingListener;
import com.karpen.lFishing.models.Config;
import com.karpen.lFishing.utils.SkinManager;
import com.karpen.lFishing.utils.TopManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.java.JavaPlugin;
import ru.mrbrikster.chatty.api.ChattyApi;

import java.io.File;

public final class LFishing extends JavaPlugin {

    private ChattyApi chattyApi;

    private DefaultBox defaultBox;
    private EpicBox epicBox;
    private NormalBox normalBox;
    private MifikBox mifikBox;
    private LegendBox legendBox;

    private Config config;

    private File file;

    private TopManager topManager;
    private SkinManager skinManager;

    private FishingListener fishingListener;

    private ReloadCommand reloadCommand;
    private ReloadCompleter reloadCompleter;

    private GetBoxCommand getBoxCommand;
    private GetBoxCompleter getBoxCompleter;

    private TopCommand topCommand;

    @Override
    public void onEnable() {
        config = new Config();

        loadConfig();

        file = new File(getDataFolder(), "top.json");

        if (!file.exists()){
            getDataFolder().mkdirs();
        }

        if (config.getChatty()){
            getLogger().info("Use chatty mode");

            chattyApi = ChattyApi.get();
        }

        defaultBox = new DefaultBox(config);
        epicBox = new EpicBox(config);
        normalBox = new NormalBox(config);
        mifikBox = new MifikBox(config);
        legendBox = new LegendBox(config);

        topManager = new TopManager(file);
        skinManager = new SkinManager();

        reloadCommand = new ReloadCommand(config, this);
        reloadCompleter = new ReloadCompleter();

        getBoxCommand = new GetBoxCommand(skinManager, config);
        getBoxCompleter = new GetBoxCompleter();

        topCommand = new TopCommand(topManager, config);

        fishingListener = new FishingListener(defaultBox, epicBox, normalBox, config, topManager, skinManager, mifikBox, legendBox, chattyApi);

        Bukkit.getPluginManager().registerEvents(defaultBox, this);
        Bukkit.getPluginManager().registerEvents(epicBox, this);
        Bukkit.getPluginManager().registerEvents(normalBox, this);
        Bukkit.getPluginManager().registerEvents(mifikBox, this);
        Bukkit.getPluginManager().registerEvents(legendBox, this);

        Bukkit.getPluginManager().registerEvents(fishingListener, this);

        getCommand("lfishing").setExecutor(reloadCommand);
        getCommand("lfishing").setTabCompleter(reloadCompleter);

        getCommand("getbox").setExecutor(getBoxCommand);
        getCommand("getbox").setTabCompleter(getBoxCompleter);

        getCommand("topbox").setExecutor(topCommand);
    }

    public void loadConfig(){
        saveDefaultConfig();

        Configuration configuration = getConfig();

        config.setDefaultChance(configuration.getDouble("chances.default", 10));
        config.setNormalChance(configuration.getDouble("chances.normal", 5));
        config.setEpicChance(configuration.getDouble("chances.epic", 3));
        config.setMifikChance(configuration.getDouble("chances.mifik", 1));
        config.setLegendChance(configuration.getDouble("chances.legend", 0.1));
        config.setSilverfishChance(configuration.getDouble("chances.silverfish", 8));

        config.setLuckChances(configuration.getBoolean("luck.enabled", true));
        config.setDefaultLuckChance(configuration.getDouble("luck.default", 12));
        config.setNormalLuckChance(configuration.getDouble("luck.normal", 8));
        config.setEpicLuckChance(configuration.getDouble("luck.epic", 5));
        config.setMifikLuckChance(configuration.getDouble("luck.mifik", 2));
        config.setLegendLuckChance(configuration.getDouble("luck.legend", 0.8));
        config.setSilverfishLuckChance(configuration.getDouble("luck.silverfish", 10));

        config.setBreakFishingRod(configuration.getDouble("chances.break", 20));

        config.setUsingChatty(configuration.getBoolean("chatty.using", false));
        config.setChat(configuration.getString("chatty.chat", "global"));

        config.setUsingBroadcast(configuration.getBoolean("broadcast.using", true));
        
        if (config.isUsingChatty() && config.isUsingBroadcast()) {
            getLogger().warning("Don't use chatty mode and broadcast mode together");
            getLogger().warning("Disabling server");

            Bukkit.getPluginManager().disablePlugin(this);
        }

        config.setDefaultMsg(configuration.getString("msg.default", "Вы поймали стандартную коробку!"));
        config.setNormalMsg(configuration.getString("msg.normal", "Вы поймали стандартную бочку!"));

        if (config.isUsingBroadcast() || config.isUsingChatty()) {
            config.setEpicMsg(configuration.getString("chat.epic", "%name% выловил эпическую коробку!"));
            config.setMifikMsg(configuration.getString("chat.mifik", "%name% выловил мифическую коробку!"));
            config.setLegendMsg(configuration.getString("chat.legend", "%name% выловил легендарную коробку!"));
        } else {
            config.setEpicMsg(configuration.getString("msg.epic", "&dВы выловили эпическую коробку!"));
            config.setMifikMsg(configuration.getString("msg.mifik", "&5Вы выловили мифическую коробку!"));
            config.setLegendMsg(configuration.getString("msg.legend", "&6Вы выловили легендарную коробку!"));
        }

        config.setSkinDefault(configuration.getString("skin.default", "https://textures.minecraft.net/texture/2cf5b1cfed1c27dd4c3bef6b9844994739851e46b3fc7fda1cbc25b80ab3b"));
        config.setSkinNormal(configuration.getString("skin.normal", "https://textures.minecraft.net/texture/f7145c0ee1cd493664fb1b26622db0492a1969a659931e7a3362d9071fbd4cf8"));
        config.setSkinEpic(configuration.getString("skin.epic", "https://textures.minecraft.net/texture/a1b96fc7d915b57f683493106704353d8837772dff51b20bf3bda09f2fa8b3ba"));
        config.setSkinMifik(configuration.getString("skin.mifik", "https://textures.minecraft.net/texture/2ff4242b1a17cf9ce781a1e33415bb19341f996ec1388ec126ee824af9e72a8d"));
        config.setSkinLegend(configuration.getString("skin.legend", "https://textures.minecraft.net/texture/cdf81449131dcdd3578899fcd9592e13f5cca57ae7481fd6710bb6ca85d65c9"));

        config.setTopMsg(configuration.getString("msg.top", "Топ игроков по количеству открытых коробок: "));
        config.setTopIsEmpty(configuration.getString("msg.topIsEmpty", "Топ пуст >_<"));

        config.setUsingErr(configuration.getString("msg.using", "Используй /getbox <default | normal | epic | mifik | legend>"));

        config.setErrArgs(configuration.getString("reload.args", "Используйте /lfishing reload"));
        config.setReloadDone(configuration.getString("reload.done", "Плагин перезагружен"));

        config.setNormalName(configuration.getString("names.normal", "Стандартная коробка"));
        config.setDefaultName(configuration.getString("names.default", "Стандартная бочка"));
        config.setEpicName(configuration.getString("names.epic", "Эпическая коробка"));
        config.setMifikName(configuration.getString("names.mifik","Мифическая коробка"));
        config.setLegendName(configuration.getString("names.legend", "Легендарная коробка"));
    }

    public static JavaPlugin getInstance() {
        return getInstance();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
