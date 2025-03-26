package com.karpen.lFishing.commands;

import com.karpen.lFishing.models.PlayerTop;
import com.karpen.lFishing.utils.TopManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TopCommand implements CommandExecutor {

    private TopManager manager;

    public TopCommand(TopManager manager){
        this.manager = manager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        List<PlayerTop> tops = manager.getTop(10);

        commandSender.sendMessage("Топ 10 игроков по количеству открытых коробок: ");

        for (PlayerTop top : tops){
            commandSender.sendMessage(top.getPlayerName() + ": " + top.getScore());
        }

        return true;
    }
}
