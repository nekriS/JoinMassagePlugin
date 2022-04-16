package org.JMP.plugin.command;

import com.google.common.collect.Lists;
import org.JMP.plugin.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import java.util.List;

public class jmpCommand extends AbstractCommand{


    //ChatColor.YELLOW + "[JoinMessagePlugin] " + ChatColor.RESET;
    public jmpCommand() {
        super("jmp");
    }
    @Override
    public void execute(CommandSender sender, String label, String[] args) {

        String prefix = Main.getInstance().getConfig().getString("message.translation.prefix") + ChatColor.RESET;

        if(args.length == 0) {
            sender.sendMessage(prefix + "Use: /" + label + " help");
            return;
        }
        if(args[0].equalsIgnoreCase("info")) {
            if (!sender.hasPermission("jmp.info")) {
                sender.sendMessage(prefix + ChatColor.RED + Main.getInstance().getConfig().getString("message.translation.noPermission"));
                return;
            }

            sender.sendMessage(prefix + "Version: 1.0.1");
            sender.sendMessage(prefix + "Thanks for using this plugin!");
            return;
        }

        if(args[0].equalsIgnoreCase("reload")) {
            if (!sender.hasPermission("jmp.reload")) {
                sender.sendMessage(prefix + ChatColor.RED + Main.getInstance().getConfig().getString("message.translation.noPermission"));
            }

            Main.getInstance().reloadConfig();
            sender.sendMessage(prefix + ChatColor.GREEN + Main.getInstance().getConfig().getString("message.translation.configReload"));
            return;
        }

        sender.sendMessage(prefix + ChatColor.RED + Main.getInstance().getConfig().getString("message.translation.notFound") + ": " + args[0]);
        return;
    }

    @Override
    public List<String> complete(CommandSender sender, String[] args) {
        if (args.length == 1) {
            return Lists.newArrayList("reload", "info");
            /**
            if (args[0].charAt(0) == 'r') {

            } else if (args[0].charAt(0) == 'i') {
                return Lists.newArrayList("info");
            } else {
                return Lists.newArrayList("info");
            }*/
        }
        return Lists.newArrayList();
    }
}
