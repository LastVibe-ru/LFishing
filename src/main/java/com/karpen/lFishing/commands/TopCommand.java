package com.karpen.lFishing.commands;

import com.karpen.lFishing.models.User;
import com.karpen.lFishing.utils.UserManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TopCommand implements CommandExecutor {

    private UserManager manager;

    public TopCommand(UserManager manager){
        this.manager = manager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        String[] args = strings;

        if (args.length != 1){
            commandSender.sendMessage(ChatColor.RED + "Используйте /fishtop <default | normal | epic | mifik | legend>");

            return true;
        }

        switch (args[0].toLowerCase()){
            case "default" -> getDefaultTop(commandSender);
            case "normal" -> getNormalTop(commandSender);
            case "epic" -> getEpicTop(commandSender);
            case "mifik" -> getMifikTop(commandSender);
            case "legend" -> getLegendTop(commandSender);
            default -> {
                commandSender.sendMessage(ChatColor.RED + "Используйте /fishtop <default | normal | epic | mifik | legend>");

                return true;
            }
        }

        return false;
    }

    private boolean getLegendTop(CommandSender sender){
        List<User> topUsers = manager.users.stream()
                .sorted(Comparator.comparingInt(User::getLegendCount).reversed())
                .toList();

        int topStage = 0;

        List<User> topTen = new ArrayList<>();

        for (User user : topUsers){
            if (topStage <= 10){
                topStage++;

                topTen.add(user);

                sender.sendMessage(ChatColor.WHITE + user.getPlayer().getName() + " " + user.getLegendCount());
            }
        }

        if (sender instanceof Player){
            Player player = (Player) sender;

            if (!topTen.contains(manager.getUser(player))){
                player.sendMessage(ChatColor.WHITE + player.getName() + " " + manager.getUser(player).getLegendCount());
            }
        }

        return true;
    }

    private boolean getMifikTop(CommandSender sender){
        List<User> topUsers = manager.users.stream()
                .sorted(Comparator.comparingInt(User::getMifikCount).reversed())
                .toList();

        int topStage = 0;

        List<User> topTen = new ArrayList<>();

        for (User user : topUsers){
            if (topStage <= 10){
                topStage++;

                topTen.add(user);

                sender.sendMessage(ChatColor.WHITE + user.getPlayer().getName() + " " + user.getMifikCount());
            }
        }

        if (sender instanceof Player){
            Player player = (Player) sender;

            if (!topTen.contains(manager.getUser(player))){
                player.sendMessage(ChatColor.WHITE + player.getName() + " " + manager.getUser(player).getMifikCount());
            }
        }

        return true;
    }

    private boolean getEpicTop(CommandSender sender){
        List<User> topUsers = manager.users.stream()
                .sorted(Comparator.comparingInt(User::getEpicCount).reversed())
                .toList();

        int topStage = 0;

        List<User> topTen = new ArrayList<>();

        for (User user : topUsers){
            if (topStage <= 10){
                topStage++;

                topTen.add(user);

                sender.sendMessage(ChatColor.WHITE + user.getPlayer().getName() + " " + user.getEpicCount());
            }
        }

        if (sender instanceof Player){
            Player player = (Player) sender;

            if (!topTen.contains(manager.getUser(player))){
                player.sendMessage(ChatColor.WHITE + player.getName() + " " + manager.getUser(player).getEpicCount());
            }
        }

        return true;
    }

    private boolean getNormalTop(CommandSender sender){
        List<User> topUsers = manager.users.stream()
                .sorted(Comparator.comparingInt(User::getNormalCount).reversed())
                .toList();

        int topStage = 0;

        List<User> topTen = new ArrayList<>();

        for (User user : topUsers){
            if (topStage <= 10){
                topStage++;

                topTen.add(user);

                sender.sendMessage(ChatColor.WHITE + user.getPlayer().getName() + " " + user.getNormalCount());
            }
        }

        if (sender instanceof Player){
            Player player = (Player) sender;

            if (!topTen.contains(manager.getUser(player))){
                player.sendMessage(ChatColor.WHITE + player.getName() + " " + manager.getUser(player).getNormalCount());
            }
        }

        return true;
    }

    private boolean getDefaultTop(CommandSender sender){
        List<User> topUsers = manager.users.stream()
                .sorted(Comparator.comparingInt(User::getDefaultCount).reversed())
                .toList();

        int topStage = 0;

        List<User> topTen = new ArrayList<>();

        for (User user : topUsers){
            if (topStage <= 10){
                topStage++;

                topTen.add(user);

                sender.sendMessage(ChatColor.WHITE + user.getPlayer().getName() + " " + user.getDefaultCount());
            }
        }

        if (sender instanceof Player){
            Player player = (Player) sender;

            if (!topTen.contains(manager.getUser(player))){
                player.sendMessage(ChatColor.WHITE + player.getName() + " " + manager.getUser(player).getDefaultCount());
            }
        }

        return true;
    }
}
