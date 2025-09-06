package com.karpen.lFishing.utils;

import com.karpen.lFishing.LFishing;
import org.bukkit.Material;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class MaterialUtils {
    public static List<String> getAllMaterialNames() {
        return Arrays.stream(Material.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    public static void saveMaterials(List<String> materials, String name) throws IOException {
        File file = new File(LFishing.getInstance().getDataFolder(), STR."materials-\{name}.dat");

        if (!file.exists()) {
            file.createNewFile();
        }

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        oos.writeObject(materials);
    }

    public static List<String> loadMaterials(String name) throws IOException, ClassNotFoundException {
        File file = new File(LFishing.getInstance().getDataFolder(), STR."materials-\{name}.dat");

        if (!file.exists()) {
            file.createNewFile();
            return null;
        }

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        List<String> res = (List<String>) ois.readObject();
        return res;
    }
}
