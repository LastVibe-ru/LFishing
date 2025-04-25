package com.karpen.lFishing.commands;

import com.karpen.lFishing.LFishing;
import com.karpen.lFishing.models.Config;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadCommand implements CommandExecutor {

    private Config config;
    private LFishing plugin;

    public ReloadCommand(Config config, LFishing plugin){
        this.config = config;
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 1){
            sender.sendMessage(ChatColor.RED + config.getErrArgs());

            return true;
        }

        if (args[0].equalsIgnoreCase("reload")){
            plugin.loadConfig();
            sender.sendMessage(ChatColor.GREEN + config.getReloadDone());

            return true;
        }

        return false;
    }
}
