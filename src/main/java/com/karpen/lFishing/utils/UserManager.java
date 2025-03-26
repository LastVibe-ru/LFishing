package com.karpen.lFishing.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.karpen.lFishing.models.User;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserManager {

    public List<User> users = new ArrayList<>();

    private User user = new User();

    private Gson gson;
    private JavaPlugin plugin;
    private File file;

    public UserManager(JavaPlugin plugin){
        this.plugin = plugin;

        this.file = new File(plugin.getDataFolder() + "users.json");
        this.gson = new GsonBuilder().setPrettyPrinting().create();

        if (!plugin.getDataFolder().exists()){
            plugin.getDataFolder().mkdirs();
        }
    }

    public void regUser(Player player){
        loadUsers();

        if (getUser(player) == null){
            user.setPlayer(player);
            user.setDefaultCount(0);
            user.setNormalCount(0);
            user.setEpicCount(0);
            user.setMifikCount(0);
            user.setLegendCount(0);

            users.add(user);
        }

        saveUsers();
    }

    public void addDefault(Player player){
        loadUsers();

        if (getUser(player) != null){
            getUser(player).setDefaultCount(+1);
        }

        saveUsers();
    }

    public void addNormal(Player player){
        loadUsers();

        if (getUser(player) != null){
            getUser(player).setNormalCount(+1);
        }

        saveUsers();
    }

    public void addEpic(Player player){
        loadUsers();

        if (getUser(player) != null){
            getUser(player).setEpicCount(+1);
        }

        saveUsers();
    }

    public void addMifik(Player player){
        loadUsers();

        if (getUser(player) != null){
            getUser(player).setMifikCount(+1);
        }

        saveUsers();
    }

    public void addLegend(Player player){
        loadUsers();

        if (getUser(player) != null){
            getUser(player).setLegendCount(+1);
        }

        saveUsers();
    }

    public User getUser(Player player){
        loadUsers();

        for (User user : users){
            if (user.getPlayer().equals(player)){
                return user;
            }
        }

        return null;
    }

    private void saveUsers(){
        try(FileWriter writer = new FileWriter(file)) {
            gson.toJson(users, writer);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void loadUsers(){
        try(FileReader reader = new FileReader(file)) {
            users = List.of(gson.fromJson(reader, User[].class));
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
