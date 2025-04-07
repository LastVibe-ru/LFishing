package com.karpen.lFishing.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerProfile;

import java.net.URL;
import java.util.UUID;

public class SkinManager {

    public ItemStack getHead(String textureUrl) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) item.getItemMeta();

        try {
            if (meta != null) {
                PlayerProfile profile = Bukkit.getServer().createPlayerProfile(UUID.randomUUID());

                URL url = new URL(textureUrl);
                profile.getTextures().setSkin(url);

                meta.setOwnerProfile(profile);
                item.setItemMeta(meta);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return item;
    }

}
