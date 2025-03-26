package com.karpen.lFishing.listeners;

import com.karpen.lFishing.boxes.DefaultBox;
import com.karpen.lFishing.boxes.EpicBox;
import com.karpen.lFishing.boxes.NormalBox;
import com.karpen.lFishing.models.Config;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.Random;

public class FishingListener implements Listener {

    private DefaultBox defaultBox;
    private EpicBox epicBox;
    private NormalBox normalBox;

    private Config config;

    private Random random = new Random();

    public FishingListener(DefaultBox defaultBox, EpicBox epicBox, NormalBox normalBox, Config config) {
        this.defaultBox = defaultBox;
        this.epicBox = epicBox;
        this.normalBox = normalBox;
        this.config = config;
    }

    @EventHandler
    public void onPlayerFishing(PlayerFishEvent event) {
        if (event.getState() == PlayerFishEvent.State.CAUGHT_FISH) {
            if (random.nextInt(0, 100) < config.getDefaultChance()) {
                dropDefaultBox(event.getPlayer());

                event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.UI_BUTTON_CLICK, 1.0f, 5.0f);

                event.getPlayer().sendMessage(config.getDefaultMsg());
            } else if (random.nextInt(0, 100) < config.getEpicChance()) {
                dropEpicBox(event.getPlayer());

                event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.UI_BUTTON_CLICK, 1.0f, 5.0f);

                event.getPlayer().sendMessage(ChatColor.DARK_PURPLE + config.getEpicMsg());
            } else if (random.nextInt(0, 100) < config.getNormalChance()){
                dropNormalBox(event.getPlayer());

                event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.UI_BUTTON_CLICK, 1.0f, 5.0f);

                event.getPlayer().sendMessage(ChatColor.GREEN + config.getNormalMsg());
            }
        }
    }

    @EventHandler
    public void playerInteractEvent(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Player player = event.getPlayer();

            if (isDefaultBox(player.getInventory().getItemInMainHand())) {
                defaultBox.openBox(player);

                if (event.getAction() == Action.RIGHT_CLICK_BLOCK){
                    event.setCancelled(true);
                }
            }

            if (isEpicBox(player.getInventory().getItemInMainHand())){
                epicBox.openBox(player);

                if (event.getAction() == Action.RIGHT_CLICK_BLOCK){
                    event.setCancelled(true);
                }
            }

            if (isNormalBox(player.getInventory().getItemInMainHand())){
                normalBox.openBox(player);

                if (event.getAction() == Action.RIGHT_CLICK_BLOCK){
                    event.setCancelled(true);
                }
            }
        }
    }

    private boolean isDefaultBox(ItemStack item) {
        if (item == null || item.getType() != Material.CHEST) {
            return false;
        }

        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            NamespacedKey key = new NamespacedKey("lfishing", "default_box");
            return meta.getPersistentDataContainer().has(key, PersistentDataType.STRING) &&
                    "default_box".equals(meta.getPersistentDataContainer().get(key, PersistentDataType.STRING));
        }

        return false;
    }

    private boolean isEpicBox(ItemStack item){
        if (item == null || item.getType() != Material.ENDER_CHEST){
            return false;
        }

        ItemMeta meta = item.getItemMeta();
        if (meta != null){
            NamespacedKey key = new NamespacedKey("lfishing", "epic_box");
            return meta.getPersistentDataContainer().has(key, PersistentDataType.STRING) &&
                    "epic_box".equals(meta.getPersistentDataContainer().get(key, PersistentDataType.STRING));
        }

        return false;
    }

    private boolean isNormalBox(ItemStack item){
        if (item == null || item.getType() != Material.BARREL){
            return false;
        }

        ItemMeta meta = item.getItemMeta();
        if (meta != null){
            NamespacedKey key = new NamespacedKey("lfishing", "normal_box");
            return meta.getPersistentDataContainer().has(key, PersistentDataType.STRING) &&
                    "normal_box".equals(meta.getPersistentDataContainer().get(key, PersistentDataType.STRING));
        }

        return false;
    }

    private void dropDefaultBox(Player player) {
        ItemStack item = new ItemStack(Material.CHEST);
        ItemMeta meta = item.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(ChatColor.WHITE + config.getDefaultName());
            NamespacedKey key = new NamespacedKey("lfishing", "default_box");
            meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "default_box");
            item.setItemMeta(meta);
        }

        player.getWorld().dropItem(player.getLocation(), item);
    }

    private void dropNormalBox(Player player){
        ItemStack item = new ItemStack(Material.BARREL);
        ItemMeta meta = item.getItemMeta();

        if (meta != null){
            meta.setDisplayName(ChatColor.GREEN + config.getNormalName());
            NamespacedKey key = new NamespacedKey("lfishing", "normal_box");
            meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "normal_box");
            item.setItemMeta(meta);
        }

        player.getWorld().dropItem(player.getLocation(), item);
    }

    private void dropEpicBox(Player player){
        ItemStack item = new ItemStack(Material.ENDER_CHEST);
        ItemMeta meta = item.getItemMeta();

        if (meta != null){
            meta.setDisplayName(ChatColor.DARK_PURPLE + config.getEpicName());
            NamespacedKey key = new NamespacedKey("lfishing", "epic_box");
            meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "epic_box");
            item.setItemMeta(meta);
        }

        player.getWorld().dropItem(player.getLocation(), item);
    }
}
