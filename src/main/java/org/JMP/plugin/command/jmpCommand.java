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
import static org.JMP.plugin.util.Settings.sendSettings;
import static org.JMP.plugin.util.Lines.getLine;
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
            sender.sendMessage(prefix + ChatColor.RED + getLine("lang", getLang(), "message.noPermission", "You don't have permission:") + " jmp.info");
            return;
        }

        String version = Main.getInstance().getDescription().getVersion();
        sender.sendMessage(prefix + ChatColor.GRAY + getLine("lang", getLang(), "message.version", "Version") + ": " + ChatColor.WHITE + version);
        sender.sendMessage(prefix + ChatColor.GRAY + getLine("lang", getLang(), "message.author", "Author") + ": " + ChatColor.WHITE + "nekriS");
        sender.sendMessage(prefix + ChatColor.GRAY + getLine("lang", getLang(), "message.jmp-cmd", "JMP Commands") + ": " + ChatColor.WHITE + "/jmp help");

    }
    @Override
    public void execute(CommandSender sender, String label, String[] args) {

        String prefix = getLine("lang", getLang(), "message.prefix", "&e[JoinMessagePlugin] ") + ChatColor.RESET;

        if (args.length == 0) {
            if (!sender.hasPermission("jmp.info")) {
                sender.sendMessage(prefix + ChatColor.RED + getLine("lang", getLang(), "message.noPermission", "You don't have permission:") + " jmp.info");
                return;
            }
            sendInfo(sender, prefix);
            return;
        }

        if (args[0].equalsIgnoreCase("info")) {
            if (!sender.hasPermission("jmp.info")) {
                sender.sendMessage(prefix + ChatColor.RED + getLine("lang", getLang(), "message.noPermission", "You don't have permission:") + " jmp.info");
                return;
            }
            sendInfo(sender, prefix);
            return;
        }

        if(args[0].equalsIgnoreCase("reload")) {
            if (!sender.hasPermission("jmp.reload")) {
                sender.sendMessage(prefix + ChatColor.RED + getLine("lang", getLang(), "message.noPermission", "You don't have permission:") + " jmp.reload");
                return;
            }

            Main.getInstance().reloadConfig();
            sender.sendMessage(prefix + ChatColor.GREEN + getLine("lang", getLang(), "message.configReload", "Config is reloaded!"));
            return;
        }

        if(args[0].equalsIgnoreCase("message")) {
            if (!sender.hasPermission("jmp.message")) {
                sender.sendMessage(prefix + ChatColor.RED + getLine("lang", getLang(), "message.noPermission", "You don't have permission:") + " jmp.message");
                return;
            }

            Player player = Bukkit.getPlayer(sender.getName());

            if (args.length == 2) {
                if (checkAvailableGroup(args[1]) ^ (args[1].equalsIgnoreCase("old-cfg"))) {
                    sender.sendMessage(ChatColor.DARK_GRAY + "[" + getLine("lang", getLang(), "message.start-separator", "START") + "] " + getLine("lang", getLang(), "message.for", "For") + ": " + args[1]);
                    org.JMP.plugin.handler.Methods.sendWelcomeMessage(player, args[1]);
                    sender.sendMessage(ChatColor.DARK_GRAY + "[" + getLine("lang", getLang(), "message.end-separator", "END") + "]");
                } else {
                    sender.sendMessage(prefix + ChatColor.RED + getLine("lang", getLang(), "message.groupNotFound", "Group not found!"));
                }

            } else {
                sender.sendMessage(prefix + ChatColor.RED + getLine("lang", getLang(), "message.use", "Use:") + ": /jmp message <group>");
            }
            return;

        }

        if (args[0].equalsIgnoreCase("help")) {
            if (!sender.hasPermission("jmp.help")) {
                sender.sendMessage(prefix + ChatColor.RED + getLine("lang", getLang(), "message.noPermission", "You don't have permission:") + " jmp.help");
                return;
            }

            Player player = Bukkit.getPlayer(sender.getName());

            sendHelp(player, getLang());

            return;
        }

        if (args[0].equalsIgnoreCase("settings")) {
            if (!sender.hasPermission("jmp.settings")) {
                sender.sendMessage(prefix + ChatColor.RED + getLine("lang", getLang(), "message.noPermission", "You don't have permission:") + " jmp.settings");
                return;
            }

            Player player = Bukkit.getPlayer(sender.getName());

            sendSettings(player);

            return;
        }

        sender.sendMessage(prefix + ChatColor.RED + getLine("lang", getLang(), "message.notFound", "Command not found") + ": " + args[0]);
        return;
    }


    @Override
    public List<String> complete(CommandSender sender, String[] args) {

        if (args.length == 1) {
            return Lists.newArrayList("reload", "info", "message", "help", "settings");
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
