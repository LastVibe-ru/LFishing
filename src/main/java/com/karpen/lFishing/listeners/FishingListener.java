package com.karpen.lFishing.listeners;

import com.karpen.lFishing.boxes.*;
import com.karpen.lFishing.models.Config;
import com.karpen.lFishing.utils.SkinManager;
import com.karpen.lFishing.utils.TopManager;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import ru.mrbrikster.chatty.api.ChattyApi;

import java.util.Random;

public class FishingListener implements Listener {

    private ChattyApi chattyApi;

    private DefaultBox defaultBox;
    private EpicBox epicBox;
    private NormalBox normalBox;
    private MifikBox mifikBox;
    private LegendBox legendBox;

    private Config config;

    private TopManager manager;
    private SkinManager skinManager;

    private Random random = new Random();

    public FishingListener(DefaultBox defaultBox, EpicBox epicBox, NormalBox normalBox, Config config, TopManager manager, SkinManager skinManager, MifikBox mifikBox, LegendBox legendBox, ChattyApi chattyApi) {
        this.defaultBox = defaultBox;
        this.epicBox = epicBox;
        this.mifikBox = mifikBox;
        this.normalBox = normalBox;
        this.config = config;
        this.manager = manager;
        this.skinManager = skinManager;
        this.legendBox = legendBox;
        this.chattyApi = chattyApi;
    }

    @EventHandler
    public void onPlayerFishing(PlayerFishEvent event) {
        if (event.getState() == PlayerFishEvent.State.CAUGHT_FISH) {
            if (config.getLuck() && isLuckRod(event.getPlayer())) {
                if (random.nextDouble(0, 500) < config.getBreakFishingRod()) {
                    event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.AMBIENT_WARPED_FOREST_MOOD, 10.0f, 1.0f);
                } else if (random.nextDouble(0, 500) < config.getDefaultLuckChance()) {
                    dropDefaultBox(event.getPlayer());

                    manager.increaseScore(event.getPlayer().getName());

                    event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 10.0f);
                    event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', config.getDefaultMsg()));

                    if (event.getCaught() != null){
                        event.getCaught().remove();
                    }
                } else if (random.nextDouble(0, 500) < config.getEpicLuckChance()) {
                    dropEpicBox(event.getPlayer());

                    manager.increaseScore(event.getPlayer().getName());

                    event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 10.0f);

                    if (config.getChatty()){
                        chattyApi.getChat(config.getChat()).get().sendMessage(ChatColor.translateAlternateColorCodes('&', config.getEpicMsg()).replace("%name%", event.getPlayer().getName()));
                    } else if (config.isUsingBroadcast()) {
                        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', config.getEpicMsg()).replace("%name%", event.getPlayer().getName()));
                    } else {
                        event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', config.getEpicMsg()));
                    }

                    if (event.getCaught() != null){
                        event.getCaught().remove();
                    }
                } else if (random.nextDouble(0, 500) < config.getNormalLuckChance()) {
                    dropNormalBox(event.getPlayer());

                    manager.increaseScore(event.getPlayer().getName());

                    event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 10.0f);
                    event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', config.getNormalMsg()));

                    if (event.getCaught() != null){
                        event.getCaught().remove();
                    }
                } else if (random.nextDouble(0, 500) < config.getMifikLuckChance()) {
                    dropMifikBox(event.getPlayer());

                    manager.increaseScore(event.getPlayer().getName());

                    event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 10.0f);

                    if (config.getChatty()){
                        chattyApi.getChat(config.getChat()).get().sendMessage(ChatColor.translateAlternateColorCodes('&', config.getMifikMsg()).replace("%name%", event.getPlayer().getName()));
                    } else if (config.isUsingBroadcast()) {
                        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', config.getMifikMsg()).replace("%name%", event.getPlayer().getName()));
                    } else {
                        event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', config.getMifikMsg()));
                    }

                    if (event.getCaught() != null){
                        event.getCaught().remove();
                    }
                } else if (random.nextDouble(0, 500) < config.getLegendLuckChance()) {
                    dropLegendBox(event.getPlayer());

                    manager.increaseScore(event.getPlayer().getName());

                    event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 10.0f);

                    if (config.getChatty()){
                        chattyApi.getChat(config.getChat()).get().sendMessage(ChatColor.translateAlternateColorCodes('&', config.getLegendMsg()).replace("%name%", event.getPlayer().getName()));
                    } else if (config.isUsingBroadcast()) {
                        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', config.getLegendMsg()).replace("%name%", event.getPlayer().getName()));
                    } else {
                        event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', config.getLegendMsg()));
                    }

                    if (event.getCaught() != null){
                        event.getCaught().remove();
                    }
                } else if (random.nextDouble(0, 500) < config.getSilverfishChance()) {
                    Player player = event.getPlayer();

                    player.playSound(player, Sound.AMBIENT_CRIMSON_FOREST_ADDITIONS, 1.0f, 10.0f);
                    player.getWorld().spawnEntity(player.getLocation(), EntityType.SILVERFISH);

                    if (event.getCaught() != null){
                        event.getCaught().remove();
                    }
                }
            } else {
                if (random.nextDouble(0, 1000) < config.getBreakFishingRod()) {
                    event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.AMBIENT_WARPED_FOREST_MOOD, 10.0f, 1.0f);
                    event.getCaught().remove();

                    if (event.getCaught() != null){
                        event.getCaught().remove();
                    }
                } else if (random.nextDouble(0, 500) < config.getDefaultChance()) {
                    dropDefaultBox(event.getPlayer());

                    manager.increaseScore(event.getPlayer().getName());

                    event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 10.0f);
                    event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', config.getDefaultMsg()));

                    if (event.getCaught() != null){
                        event.getCaught().remove();
                    }
                } else if (random.nextDouble(0, 500) < config.getEpicChance()) {
                    dropEpicBox(event.getPlayer());

                    manager.increaseScore(event.getPlayer().getName());

                    event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 10.0f);

                    if (config.getChatty()){
                        chattyApi.getChat(config.getChat()).get().sendMessage(ChatColor.translateAlternateColorCodes('&', config.getEpicMsg()).replace("%name%", event.getPlayer().getName()));
                    } else if (config.isUsingBroadcast()) {
                        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', config.getEpicMsg().replace("%name%", event.getPlayer().getName())));
                    } else {
                        event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', config.getEpicMsg()));
                    }

                    if (event.getCaught() != null){
                        event.getCaught().remove();
                    }
                } else if (random.nextDouble(0, 500) < config.getNormalChance()) {
                    dropNormalBox(event.getPlayer());

                    manager.increaseScore(event.getPlayer().getName());

                    event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 10.0f);
                    event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', config.getNormalMsg()));

                    if (event.getCaught() != null){
                        event.getCaught().remove();
                    }
                } else if (random.nextDouble(0, 500) < config.getMifikChance()) {
                    dropMifikBox(event.getPlayer());

                    manager.increaseScore(event.getPlayer().getName());

                    event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 10.0f);

                    if (config.getChatty()){
                        chattyApi.getChat(config.getChat()).get().sendMessage(ChatColor.translateAlternateColorCodes('&', config.getMifikMsg()).replace("%name%", event.getPlayer().getName()));
                    } else if (config.isUsingBroadcast()) {
                        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', config.getMifikMsg().replace("%name%", event.getPlayer().getName())));
                    } else {
                        event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', config.getMifikMsg()));
                    }

                    if (event.getCaught() != null){
                        event.getCaught().remove();
                    }
                } else if (random.nextDouble(0, 500) < config.getLegendChance()) {
                    dropLegendBox(event.getPlayer());

                    manager.increaseScore(event.getPlayer().getName());

                    event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 10.0f);

                    if (config.getChatty()){
                        chattyApi.getChat(config.getChat()).get().sendMessage(ChatColor.translateAlternateColorCodes('&', config.getLegendMsg()).replace("%name%", event.getPlayer().getName()));
                    } else if (config.isUsingBroadcast()) {
                        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', config.getLegendMsg().replace("%name%", event.getPlayer().getName())));
                    } else {
                        event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', config.getLegendMsg()));
                    }

                    if (event.getCaught() != null){
                        event.getCaught().remove();
                    }
                } else if (random.nextDouble(0, 500) < config.getSilverfishLuckChance()) {
                    Player player = event.getPlayer();

                    player.playSound(player, Sound.AMBIENT_CRIMSON_FOREST_ADDITIONS, 1.0f, 10.0f);
                    player.getWorld().spawnEntity(player.getLocation(), EntityType.SILVERFISH);

                    if (event.getCaught() != null){
                        event.getCaught().remove();
                    }
                }
            }
        }
    }

    @EventHandler
    public void playerInteractEvent(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Player player = event.getPlayer();

            if (isDefaultBox(player.getInventory().getItemInMainHand())) {
                defaultBox.openBox(player);

                event.setCancelled(true);
            }

            if (isEpicBox(player.getInventory().getItemInMainHand())){
                epicBox.openBox(player);

                event.setCancelled(true);
            }

            if (isNormalBox(player.getInventory().getItemInMainHand())){
                normalBox.openBox(player);

                event.setCancelled(true);
            }

            if (isMifikBox(player.getInventory().getItemInMainHand())){
                mifikBox.openBox(player);

                event.setCancelled(true);
            }

            if (isLegendBox(player.getInventory().getItemInMainHand())){
                legendBox.openBox(player);

                event.setCancelled(true);
            }
        }
    }

    private boolean isLuckRod(Player player) {
        ItemStack item = player.getInventory().getItemInMainHand();
        ItemMeta meta = item.getItemMeta();

        return meta != null && item.getType().equals(Material.FISHING_ROD) && meta.hasEnchant(Enchantment.LUCK_OF_THE_SEA);
    }

    private boolean isDefaultBox(ItemStack item) {
        if (item == null || item.getType() != Material.PLAYER_HEAD) {
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
        if (item == null || item.getType() != Material.PLAYER_HEAD){
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
        if (item == null || item.getType() != Material.PLAYER_HEAD){
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

    private boolean isMifikBox(ItemStack item){
        if (item == null || item.getType() != Material.PLAYER_HEAD){
            return false;
        }

        ItemMeta meta = item.getItemMeta();
        if (meta != null){
            NamespacedKey key = new NamespacedKey("lfishing", "mifik_box");
            return meta.getPersistentDataContainer().has(key, PersistentDataType.STRING) &&
                    "mifik_box".equals(meta.getPersistentDataContainer().get(key, PersistentDataType.STRING));
        }

        return false;
    }

    private boolean isLegendBox(ItemStack item){
        if (item == null || item.getType() != Material.PLAYER_HEAD){
            return false;
        }

        ItemMeta meta = item.getItemMeta();
        if (meta != null){
            NamespacedKey key = new NamespacedKey("lfishing", "legend_box");
            return meta.getPersistentDataContainer().has(key, PersistentDataType.STRING) &&
                    "legend_box".equals(meta.getPersistentDataContainer().get(key, PersistentDataType.STRING));
        }

        return false;
    }

    private void dropDefaultBox(Player player) {
        ItemStack item = skinManager.getHead(config.getSkinDefault());
        ItemMeta meta = item.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', config.getDefaultName()));
            NamespacedKey key = new NamespacedKey("lfishing", "default_box");
            meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "default_box");
            item.setItemMeta(meta);
        }

        player.getWorld().dropItem(player.getLocation(), item);
    }

    private void dropNormalBox(Player player){
        ItemStack item = skinManager.getHead(config.getSkinNormal());
        ItemMeta meta = item.getItemMeta();

        if (meta != null){
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', config.getNormalName()));
            NamespacedKey key = new NamespacedKey("lfishing", "normal_box");
            meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "normal_box");
            item.setItemMeta(meta);
        }

        player.getWorld().dropItem(player.getLocation(), item);
    }

    private void dropEpicBox(Player player){
        ItemStack item = skinManager.getHead(config.getSkinEpic());
        ItemMeta meta = item.getItemMeta();

        if (meta != null){
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', config.getEpicName()));
            NamespacedKey key = new NamespacedKey("lfishing", "epic_box");
            meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "epic_box");
            item.setItemMeta(meta);
        }

        player.getWorld().dropItem(player.getLocation(), item);
    }

    private void dropMifikBox(Player player){
        ItemStack item = skinManager.getHead(config.getSkinMifik());
        ItemMeta meta = item.getItemMeta();

        if (meta != null){
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', config.getMifikName()));
            NamespacedKey key = new NamespacedKey("lfishing", "mifik_box");
            meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "mifik_box");
            item.setItemMeta(meta);
        }

        player.getWorld().dropItem(player.getLocation(), item);
    }

    private void dropLegendBox(Player player){
        ItemStack item = skinManager.getHead(config.getSkinLegend());
        ItemMeta meta = item.getItemMeta();

        if (meta != null){
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', config.getLegendName()));
            NamespacedKey key = new NamespacedKey("lfishing", "legend_box");
            meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "legend_box");
            item.setItemMeta(meta);
        }

        player.getWorld().dropItem(player.getLocation(), item);
    }
}
