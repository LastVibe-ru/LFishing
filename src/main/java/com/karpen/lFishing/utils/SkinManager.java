package com.karpen.lFishing.utils;

import com.karpen.lFishing.models.Config;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerProfile;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

public class SkinManager {

    public ItemStack getHead(String textureUrl) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) item.getItemMeta();

        try {
            if (meta != null) {
                // Создаем новый профиль игрока с уникальным UUID
                PlayerProfile profile = Bukkit.getServer().createPlayerProfile(UUID.randomUUID());

                // Устанавливаем текстуру через URL
                URL url = new URL(textureUrl);
                profile.getTextures().setSkin(url);  // Устанавливаем текстуру с использованием URL

                // Назначаем профиль игрока для головы
                meta.setOwnerProfile(profile);
                item.setItemMeta(meta);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return item;
    }

}
