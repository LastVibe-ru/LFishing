package com.karpen.lFishing.commands;

import com.karpen.lFishing.models.Config;
import com.karpen.lFishing.utils.SkinManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import ru.mrbrikster.chatty.api.ChattyApi;

public class GetBoxCommand implements CommandExecutor {

    private SkinManager skinManager;
    private Config config;

    // FOR TESTS
    private ChattyApi chattyApi;
    // REMOVE THIS

    public GetBoxCommand(SkinManager skinManager, Config config, ChattyApi chattyApi){
        this.skinManager = skinManager;
        this.config = config;
        this.chattyApi = chattyApi;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 1){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getUsingErr()));

            return true;
        }

        if (!(sender instanceof Player)){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getConsoleErr()));

            return true;
        }

        Player player = (Player) sender;

        switch (args[0].toLowerCase()){
            case "default" -> dropDefaultBox(player);
            case "normal" -> dropNormalBox(player);
            case "epic" -> dropEpicBox(player);
            case "mifik" -> dropMifikBox(player);
            case "legend" -> dropLegendBox(player);
            default -> {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getUsingErr()));

                return true;
            }
        }

        return true;
    }

    private boolean dropDefaultBox(Player player) {
        ItemStack item = skinManager.getHead(config.getSkinDefault());
        ItemMeta meta = item.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', config.getDefaultName()));
            NamespacedKey key = new NamespacedKey("lfishing", "default_box");
            meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "default_box");
            item.setItemMeta(meta);
        }

        player.getWorld().dropItem(player.getLocation(), item);

        return true;
    }

    private boolean dropNormalBox(Player player){
        ItemStack item = skinManager.getHead(config.getSkinNormal());
        ItemMeta meta = item.getItemMeta();

        if (meta != null){
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', config.getNormalName()));
            NamespacedKey key = new NamespacedKey("lfishing", "normal_box");
            meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "normal_box");
            item.setItemMeta(meta);
        }

        player.getWorld().dropItem(player.getLocation(), item);

        return true;
    }

    private boolean dropEpicBox(Player player){
        ItemStack item = skinManager.getHead(config.getSkinEpic());
        ItemMeta meta = item.getItemMeta();

        if (meta != null){
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', config.getEpicName()));
            NamespacedKey key = new NamespacedKey("lfishing", "epic_box");
            meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "epic_box");
            item.setItemMeta(meta);
        }

        player.getWorld().dropItem(player.getLocation(), item);

        return true;
    }

    private boolean dropMifikBox(Player player){
        ItemStack item = skinManager.getHead(config.getSkinMifik());
        ItemMeta meta = item.getItemMeta();

        if (meta != null){
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', config.getMifikName()));
            NamespacedKey key = new NamespacedKey("lfishing", "mifik_box");
            meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "mifik_box");
            item.setItemMeta(meta);
        }

        player.getWorld().dropItem(player.getLocation(), item);

        return true;
    }

    private boolean dropLegendBox(Player player){
        ItemStack item = skinManager.getHead(config.getSkinLegend());
        ItemMeta meta = item.getItemMeta();

        if (meta != null){
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', config.getLegendName()));
            NamespacedKey key = new NamespacedKey("lfishing", "legend_box");
            meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "legend_box");
            item.setItemMeta(meta);
        }

        // FOR TESTS
        if (config.getChatty()){
            chattyApi.getChat(config.getChat()).get().sendMessage(ChatColor.translateAlternateColorCodes('&', config.getLegendMsg()).replace("%name%", player.getName()));
        } else if (config.isUsingBroadcast()) {
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', config.getLegendMsg()).replace("%name%", player.getName()));
        } else {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getLegendMsg()));
        }
        // REMOVE THIS

        player.getWorld().dropItem(player.getLocation(), item);

        return true;
    }
}
