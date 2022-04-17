package org.JMP.plugin.command;

import com.google.common.collect.Lists;
import org.JMP.plugin.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.List;
import java.util.Objects;
import java.util.Set;


public class jmpCommand extends AbstractCommand{


    public jmpCommand() {
        super("jmp");
    }


    public boolean checkAvailableGroup(String group) {
        if (Main.getInstance().getConfig().getConfigurationSection("text") != null) {
            Set<String> keys = Objects.requireNonNull((Main.getInstance().getConfig().getConfigurationSection("text"))).getKeys(false);
            return keys.contains(group);
        }
        return false;
    }
    @Override
    public void execute(CommandSender sender, String label, String[] args) {

        String prefix = Main.getInstance().getConfig().getString("message.prefix") + ChatColor.RESET;

        if(args.length == 0) {
            sender.sendMessage(prefix + "Use: /" + label + " help");
            return;
        }
        if(args[0].equalsIgnoreCase("info")) {
            if (!sender.hasPermission("jmp.info")) {
                sender.sendMessage(prefix + ChatColor.RED + Main.getInstance().getConfig().getString("message.noPermission"));
                return;
            }

            String version = Main.getInstance().getDescription().getVersion();
            sender.sendMessage(prefix + "Version: " + version);
            sender.sendMessage(prefix + "Thanks for using this plugin!");
            return;
        }

        if(args[0].equalsIgnoreCase("reload")) {
            if (!sender.hasPermission("jmp.reload")) {
                sender.sendMessage(prefix + ChatColor.RED + Main.getInstance().getConfig().getString("message.noPermission"));
                return;
            }

            Main.getInstance().reloadConfig();
            sender.sendMessage(prefix + ChatColor.GREEN + Main.getInstance().getConfig().getString("message.configReload"));
            return;
        }

        if(args[0].equalsIgnoreCase("message")) {
            if (!sender.hasPermission("jmp.message")) {
                sender.sendMessage(prefix + ChatColor.RED + Main.getInstance().getConfig().getString("message.noPermission"));
                return;
            }

            Player player = Bukkit.getPlayer(sender.getName());

            if (args.length == 2) {
                if (checkAvailableGroup(args[1]) ^ (args[1].equalsIgnoreCase("old-cfg"))) {
                    sender.sendMessage(ChatColor.DARK_GRAY + "[START] For: " + args[1]);
                    org.JMP.plugin.handler.EventListener.sendWelcomeMessage(player, args[1]);
                    sender.sendMessage(ChatColor.DARK_GRAY + "[END]");
                } else {
                    sender.sendMessage(prefix + ChatColor.RED + "Group not found!");
                }

            } else {
                sender.sendMessage(prefix + ChatColor.RED + "Use: /jmp message <group>");
            }
            return;

        }

        sender.sendMessage(prefix + ChatColor.RED + Main.getInstance().getConfig().getString("message.notFound") + ": " + args[0]);
        return;
    }

    @Override
    public List<String> complete(CommandSender sender, String[] args) {
        if (args.length == 1) {
            return Lists.newArrayList("reload", "info", "message");
        }

        if (args.length == 2) {



            if (Main.getInstance().getConfig().getConfigurationSection("text") != null) {
                Set<String> keys = Objects.requireNonNull((Main.getInstance().getConfig().getConfigurationSection("text"))).getKeys(false);

                return Lists.newArrayList(keys);
            } else {
                return Lists.newArrayList("old-cfg");
            }

        }
        return Lists.newArrayList();
    }
}
