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
        Inventory inventory = Bukkit.createInventory(player, 27, ChatColor.translateAlternateColorCodes('&', config.getNormalName()));
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
        int var = random.nextInt(0, 9);

        switch (var) {
            case 0:
                items.add(new ItemStack(Material.OAK_PLANKS, random.nextInt(1, 5)));
                items.add(new ItemStack(Material.OAK_PLANKS, random.nextInt(1, 5)));
                items.add(new ItemStack(Material.COBBLESTONE, random.nextInt(1, 9)));
                items.add(new ItemStack(Material.COBBLESTONE, random.nextInt(1, 9)));
                items.add(new ItemStack(Material.OAK_LOG, random.nextInt(1, 8)));
                items.add(new ItemStack(Material.BREAD, random.nextInt(1, 10)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                break;
            case 1:
                items.add(new ItemStack(Material.ACACIA_PLANKS, random.nextInt(1, 5)));
                items.add(new ItemStack(Material.ACACIA_PLANKS, random.nextInt(1, 5)));
                items.add(new ItemStack(Material.DEEPSLATE, random.nextInt(1, 9)));
                items.add(new ItemStack(Material.DEEPSLATE, random.nextInt(1, 9)));
                items.add(new ItemStack(Material.ACACIA_LOG, random.nextInt(1, 8)));
                items.add(new ItemStack(Material.COOKED_BEEF, random.nextInt(1, 10)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                break;
            case 2:
                items.add(new ItemStack(Material.CHERRY_PLANKS, random.nextInt(1, 8)));
                items.add(new ItemStack(Material.CHERRY_PLANKS, random.nextInt(1, 9)));
                items.add(new ItemStack(Material.ANDESITE, random.nextInt(1, 19)));
                items.add(new ItemStack(Material.ANDESITE, random.nextInt(1, 19)));
                items.add(new ItemStack(Material.CHERRY_LOG, random.nextInt(1, 12)));
                items.add(new ItemStack(Material.COOKED_RABBIT, random.nextInt(1, 20)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                break;
            case 3:
                items.add(new ItemStack(Material.JUNGLE_PLANKS, random.nextInt(1, 8)));
                items.add(new ItemStack(Material.JUNGLE_PLANKS, random.nextInt(1, 9)));
                items.add(new ItemStack(Material.GRANITE, random.nextInt(1, 19)));
                items.add(new ItemStack(Material.GRANITE, random.nextInt(1, 19)));
                items.add(new ItemStack(Material.JUNGLE_LOG, random.nextInt(1, 12)));
                items.add(new ItemStack(Material.COOKED_CHICKEN, random.nextInt(1, 20)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                break;
            case 4:
                items.add(new ItemStack(Material.BIRCH_PLANKS, random.nextInt(1, 8)));
                items.add(new ItemStack(Material.BIRCH_PLANKS, random.nextInt(1, 9)));
                items.add(new ItemStack(Material.STONE, random.nextInt(1, 19)));
                items.add(new ItemStack(Material.STONE, random.nextInt(1, 19)));
                items.add(new ItemStack(Material.BIRCH_LOG, random.nextInt(1, 12)));
                items.add(new ItemStack(Material.COOKED_COD, random.nextInt(1, 20)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                break;
            case 5:
                items.add(new ItemStack(Material.SPRUCE_PLANKS, random.nextInt(1, 8)));
                items.add(new ItemStack(Material.SPRUCE_PLANKS, random.nextInt(1, 9)));
                items.add(new ItemStack(Material.STICK, random.nextInt(1, 19)));
                items.add(new ItemStack(Material.STICK, random.nextInt(1, 19)));
                items.add(new ItemStack(Material.SPRUCE_LOG, random.nextInt(1, 12)));
                items.add(new ItemStack(Material.BREAD, random.nextInt(1, 20)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                break;
            case 6:
                items.add(new ItemStack(Material.DARK_OAK_PLANKS, random.nextInt(1, 8)));
                items.add(new ItemStack(Material.DARK_OAK_PLANKS, random.nextInt(1, 9)));
                items.add(new ItemStack(Material.DARK_OAK_LEAVES, random.nextInt(1, 19)));
                items.add(new ItemStack(Material.DARK_OAK_LEAVES, random.nextInt(1, 19)));
                items.add(new ItemStack(Material.DARK_OAK_LOG, random.nextInt(1, 12)));
                items.add(new ItemStack(Material.COOKED_RABBIT, random.nextInt(1, 20)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                break;
            case 7:
                items.add(new ItemStack(Material.PALE_OAK_PLANKS, random.nextInt(1, 8)));
                items.add(new ItemStack(Material.PALE_OAK_PLANKS, random.nextInt(1, 19)));
                items.add(new ItemStack(Material.PALE_MOSS_BLOCK, random.nextInt(1, 19)));
                items.add(new ItemStack(Material.PALE_MOSS_BLOCK, random.nextInt(1, 19)));
                items.add(new ItemStack(Material.PALE_OAK_LOG, random.nextInt(1, 18)));
                items.add(new ItemStack(Material.PALE_MOSS_CARPET, random.nextInt(1, 20)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                break;
            case 8:
                items.add(new ItemStack(Material.MANGROVE_PLANKS, random.nextInt(1, 9)));
                items.add(new ItemStack(Material.MANGROVE_PLANKS, random.nextInt(1, 9)));
                items.add(new ItemStack(Material.MOSS_BLOCK, random.nextInt(1, 19)));
                items.add(new ItemStack(Material.MOSS_BLOCK, random.nextInt(1, 19)));
                items.add(new ItemStack(Material.MANGROVE_LOG, random.nextInt(1, 12)));
                items.add(new ItemStack(Material.MOSS_CARPET, random.nextInt(1, 20)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
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
        if (item == null || item.getType() != Material.PLAYER_HEAD) {
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

