package com.karpen.lFishing.commands;

import com.karpen.lFishing.models.Config;
import com.karpen.lFishing.models.PlayerTop;
import com.karpen.lFishing.utils.TopManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TopCommand implements CommandExecutor {

    private TopManager manager;
    private Config config;

    public TopCommand(TopManager manager, Config config){
        this.manager = manager;
        this.config = config;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        List<PlayerTop> tops = manager.getTop(10);

        commandSender.sendMessage(config.getTopMsg());

        if (tops.isEmpty()){
            commandSender.sendMessage(ChatColor.RED + config.getTopIsEmpty());

            return true;
        }

        for (PlayerTop top : tops){
            commandSender.sendMessage("%s%s%s: %d".formatted(ChatColor.GRAY, top.getPlayerName(), ChatColor.WHITE, top.getScore()));
        }

        return true;
    }
}
