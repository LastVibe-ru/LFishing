package com.karpen.lFishing.boxes;

import com.karpen.lFishing.models.Config;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.*;

public class NormalBox implements Listener {

    private final Map<Player, Inventory> playerInventories = new HashMap<>();

    private Config config;

    public NormalBox(Config config){
        this.config = config;
    }

    public void openBox(Player player) {
        Inventory inventory = Bukkit.createInventory(player, 27, ChatColor.BOLD + config.getNormalName());
        playerInventories.put(player, inventory);

        List<ItemStack> items = generateRandomItems();

        for (ItemStack item : items) {
            int slot;
            do {
                slot = new Random().nextInt(0, 27);
            } while (inventory.getItem(slot) != null);

            inventory.setItem(slot, item);
        }

        ItemStack itemInHand = player.getInventory().getItemInMainHand();
        if (isNormalBox(itemInHand)) {
            player.getInventory().getItemInMainHand().setAmount(itemInHand.getAmount() - 1);
        }

        player.playSound(player.getLocation(), Sound.UI_LOOM_TAKE_RESULT, 1.0f, 1.0f);

        player.openInventory(inventory);
    }

    private List<ItemStack> generateRandomItems() {
        List<ItemStack> items = new ArrayList<>();
        Random random = new Random();
        int var = random.nextInt(0, 5);

        switch (var) {
            case 0:
                items.add(new ItemStack(Material.OAK_PLANKS, random.nextInt(1, 10)));
                items.add(new ItemStack(Material.COBBLESTONE, random.nextInt(1, 12)));
                items.add(new ItemStack(Material.OAK_LOG, random.nextInt(1, 10)));
                items.add(new ItemStack(Material.BREAD, random.nextInt(1, 20)));
                break;
            case 1:
                items.add(new ItemStack(Material.ACACIA_PLANKS, random.nextInt(1, 10)));
                items.add(new ItemStack(Material.DEEPSLATE, random.nextInt(1, 12)));
                items.add(new ItemStack(Material.ACACIA_LOG, random.nextInt(1, 10)));
                items.add(new ItemStack(Material.COOKED_BEEF, random.nextInt(1, 20)));
                break;
            case 2:
                items.add(new ItemStack(Material.CHERRY_PLANKS, random.nextInt(1, 10)));
                items.add(new ItemStack(Material.ANDESITE, random.nextInt(1, 12)));
                items.add(new ItemStack(Material.CHERRY_LOG, random.nextInt(1, 10)));
                items.add(new ItemStack(Material.COOKED_RABBIT, random.nextInt(1, 20)));
                break;
            case 3:
                items.add(new ItemStack(Material.JUNGLE_PLANKS, random.nextInt(1, 10)));
                items.add(new ItemStack(Material.GRANITE, random.nextInt(1, 12)));
                items.add(new ItemStack(Material.JUNGLE_LOG, random.nextInt(1, 10)));
                items.add(new ItemStack(Material.COOKED_CHICKEN, random.nextInt(1, 20)));
                break;
            case 4:
                items.add(new ItemStack(Material.BIRCH_PLANKS, random.nextInt(1, 10)));
                items.add(new ItemStack(Material.STONE, random.nextInt(1, 12)));
                items.add(new ItemStack(Material.BIRCH_LOG, random.nextInt(1, 10)));
                items.add(new ItemStack(Material.COOKED_COD, random.nextInt(1, 20)));
                break;
        }
        return items;
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();

        if (playerInventories.containsKey(player)){
            Inventory inventory = playerInventories.get(player);

            for (ItemStack item : inventory.getContents()) {
                if (item != null && item.getType() != Material.AIR) {
                    HashMap<Integer, ItemStack> excess = player.getInventory().addItem(item);
                    for (ItemStack excessItem : excess.values()) {
                        player.getWorld().dropItemNaturally(player.getLocation(), excessItem);
                    }
                }
            }

            playerInventories.remove(player);
        }
    }

    private boolean isNormalBox(ItemStack item) {
        if (item == null || item.getType() != Material.BARREL) {
            return false;
        }

        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            NamespacedKey key = new NamespacedKey("lfishing", "normal_box");
            return meta.getPersistentDataContainer().has(key, PersistentDataType.STRING) &&
                    "normal_box".equals(meta.getPersistentDataContainer().get(key, PersistentDataType.STRING));
        }

        return false;
    }
}

