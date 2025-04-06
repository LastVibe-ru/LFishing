package com.karpen.lFishing.commands;

import com.karpen.lFishing.models.Config;
import com.karpen.lFishing.utils.SkinManager;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class GetBoxCommand implements CommandExecutor {

    private SkinManager skinManager;
    private Config config;

    public GetBoxCommand(SkinManager skinManager, Config config){
        this.skinManager = skinManager;
        this.config = config;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 1){
            sender.sendMessage(ChatColor.RED + "Юзай /getbox <default | normal | epic>");

            return true;
        }

        if (!(sender instanceof Player)){
            sender.sendMessage(ChatColor.RED + "Не юзай с консоли");

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
                sender.sendMessage(ChatColor.RED + "Юзай /getbox <default | normal | epic | mifik>");

                return true;
            }
        }

        return true;
    }

    private boolean dropDefaultBox(Player player) {
        ItemStack item = skinManager.getHead(config.getSkinDefault());
        ItemMeta meta = item.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(ChatColor.WHITE + config.getDefaultName());
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
            meta.setDisplayName(ChatColor.GREEN + config.getNormalName());
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
            meta.setDisplayName(ChatColor.DARK_PURPLE + config.getEpicName());
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
            meta.setDisplayName(ChatColor.DARK_PURPLE + config.getMifikName());
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
            meta.setDisplayName(ChatColor.GOLD + config.getLegendName());
            NamespacedKey key = new NamespacedKey("lfishing", "legend_box");
            meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "legend_box");
            item.setItemMeta(meta);
        }

        player.getWorld().dropItem(player.getLocation(), item);

        return true;
    }
}
