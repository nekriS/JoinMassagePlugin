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

import static org.JMP.plugin.util.Help.sendHelp;
import static org.JMP.plugin.util.Lang.getStringLang;


public class jmpCommand extends AbstractCommand{

    public String getLang() {
        return  Main.getInstance().getConfig().getString("lang","en-US");
    }
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

    private void sendInfo(CommandSender sender, String prefix) {

        if (!sender.hasPermission("jmp.info")) {
            sender.sendMessage(prefix + ChatColor.RED + getStringLang(getLang(), "message.noPermission") + " jmp.info");
            return;
        }

        String version = Main.getInstance().getDescription().getVersion();
        sender.sendMessage(prefix + ChatColor.GRAY + getStringLang(getLang(), "message.version") + ": " + ChatColor.WHITE + version);
        sender.sendMessage(prefix + ChatColor.GRAY + getStringLang(getLang(), "message.author") + ": " + ChatColor.WHITE + "nekriS");
        sender.sendMessage(prefix + ChatColor.GRAY + getStringLang(getLang(), "message.jmp-cmd") + ": " + ChatColor.WHITE + "/jmp help");

    }
    @Override
    public void execute(CommandSender sender, String label, String[] args) {

        String prefix = getStringLang(getLang(), "message.prefix") + ChatColor.RESET;

        if (args.length == 0) {
            if (!sender.hasPermission("jmp.info")) {
                sender.sendMessage(prefix + ChatColor.RED + getStringLang(getLang(), "message.noPermission") + " jmp.info");
                return;
            }
            sendInfo(sender, prefix);
            return;
        }

        if (args[0].equalsIgnoreCase("info")) {
            if (!sender.hasPermission("jmp.reload")) {
                sender.sendMessage(prefix + ChatColor.RED + getStringLang(getLang(), "message.noPermission") + " jmp.info");
                return;
            }
            sendInfo(sender, prefix);
            return;
        }

        if(args[0].equalsIgnoreCase("reload")) {
            if (!sender.hasPermission("jmp.reload")) {
                sender.sendMessage(prefix + ChatColor.RED + getStringLang(getLang(), "message.noPermission") + " jmp.reload");
                return;
            }

            Main.getInstance().reloadConfig();
            sender.sendMessage(prefix + ChatColor.GREEN + getStringLang(getLang(), "message.configReload"));
            return;
        }

        if(args[0].equalsIgnoreCase("message")) {
            if (!sender.hasPermission("jmp.message")) {
                sender.sendMessage(prefix + ChatColor.RED + getStringLang(getLang(), "message.noPermission") + " jmp.message");
                return;
            }

            Player player = Bukkit.getPlayer(sender.getName());

            if (args.length == 2) {
                if (checkAvailableGroup(args[1]) ^ (args[1].equalsIgnoreCase("old-cfg"))) {
                    sender.sendMessage(ChatColor.DARK_GRAY + "[" + getStringLang(getLang(), "message.start-separator") + "] " + getStringLang(getLang(), "message.for") + ": " + args[1]);
                    org.JMP.plugin.handler.EventListener.sendWelcomeMessage(player, args[1]);
                    sender.sendMessage(ChatColor.DARK_GRAY + "[" + getStringLang(getLang(), "message.end-separator") + "]");
                } else {
                    sender.sendMessage(prefix + ChatColor.RED + getStringLang(getLang(), "message.groupNotFound"));
                }

            } else {
                sender.sendMessage(prefix + ChatColor.RED + getStringLang(getLang(), "message.use") + ": /jmp message <group>");
            }
            return;

        }

        if (args[0].equalsIgnoreCase("help")) {
            if (!sender.hasPermission("jmp.help")) {
                sender.sendMessage(prefix + ChatColor.RED + getStringLang(getLang(), "message.noPermission") + " jmp.help");
                return;
            }

            Player player = Bukkit.getPlayer(sender.getName());

            sendHelp(player, getLang());

            return;
        }

        sender.sendMessage(prefix + ChatColor.RED + getStringLang(getLang(), "message.notFound") + ": " + args[0]);
        return;
    }


    @Override
    public List<String> complete(CommandSender sender, String[] args) {
        if (args.length == 1) {
            return Lists.newArrayList("reload", "info", "message", "help");
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
