package com.karpen.lFishing.boxes;

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

public class EpicBox implements Listener {

    private final Map<Player, Inventory> playerInventories = new HashMap<>();

    public void openBox(Player player) {
        Inventory inventory = Bukkit.createInventory(player, 27, ChatColor.DARK_PURPLE + "Эпическая коробка");
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
        if (isEpicBox(itemInHand)) {
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
                items.add(new ItemStack(Material.DIAMOND_HORSE_ARMOR, 1));
                items.add(new ItemStack(Material.GOLD_NUGGET, random.nextInt(1, 9)));
                items.add(new ItemStack(Material.IRON_NUGGET, random.nextInt(1, 8)));
                items.add(new ItemStack(Material.GOLDEN_APPLE, random.nextInt(1, 2)));
                break;
            case 1:
                items.add(new ItemStack(Material.BLAZE_ROD, random.nextInt(1, 2)));
                items.add(new ItemStack(Material.EMERALD, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.LAPIS_LAZULI, random.nextInt(1, 4)));
                items.add(new ItemStack(Material.IRON_BLOCK, random.nextInt(1, 2)));
                break;
            case 2:
                items.add(new ItemStack(Material.GOLD_NUGGET, random.nextInt(1, 2)));
                items.add(new ItemStack(Material.REDSTONE, random.nextInt(1, 9)));
                items.add(new ItemStack(Material.LAPIS_BLOCK, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.EMERALD_ORE, random.nextInt(1, 2)));
                break;
            case 3:
                items.add(new ItemStack(Material.GOLD_BLOCK, random.nextInt(1, 2)));
                items.add(new ItemStack(Material.DIAMOND, random.nextInt(1, 2)));
                items.add(new ItemStack(Material.BLAZE_ROD, random.nextInt(1, 4)));
                items.add(new ItemStack(Material.GOLD_NUGGET, random.nextInt(1, 10)));
                break;
            case 4:
                items.add(new ItemStack(Material.ENDER_EYE, random.nextInt(1, 5)));
                items.add(new ItemStack(Material.IRON_BLOCK, random.nextInt(1, 2)));
                items.add(new ItemStack(Material.END_ROD, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.IRON_NUGGET, random.nextInt(1, 10)));
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

    private boolean isEpicBox(ItemStack item) {
        if (item == null || item.getType() != Material.ENDER_CHEST) {
            return false;
        }

        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            NamespacedKey key = new NamespacedKey("lfishing", "epic_box");
            return meta.getPersistentDataContainer().has(key, PersistentDataType.STRING) &&
                    "epic_box".equals(meta.getPersistentDataContainer().get(key, PersistentDataType.STRING));
        }

        return false;
    }
}

