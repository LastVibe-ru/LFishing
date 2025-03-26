package com.karpen.lFishing.listeners;

import com.karpen.lFishing.utils.UserManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class LoginListener implements Listener {

    private UserManager manager;

    public LoginListener(UserManager manager){
        this.manager = manager;
    }

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event){
        manager.regUser(event.getPlayer());
    }
}
