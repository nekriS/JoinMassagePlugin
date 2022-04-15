package org.JMP.plugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;

import static org.bukkit.Bukkit.getServer;

public class Info implements CommandExecutor {

    String prefix = ChatColor.YELLOW + "[JoinMessagePlugin] " + ChatColor.RESET;


    public void version(Player player) {
        player.sendMessage(prefix + "Version: 1.0.0");
        player.sendMessage(prefix + "Thanks for using this plugin!");
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;
        getServer().getConsoleSender().sendMessage(command.getName());
        if (command.getName().equalsIgnoreCase("joinmessageplugin")) {

            if (args.length >= 1){
                if (args[0].equalsIgnoreCase("info")) {
                    version(player);
                    return true;
                } else {
                    player.sendMessage(prefix + ChatColor.RED + "Command not found!");
                    return true;
                }
            } else {
                version(player);
                return true;
            }

        } else {
            return false;
        }
    }
}
