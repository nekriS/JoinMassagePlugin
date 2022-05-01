package org.JMP.plugin.util;

import org.JMP.plugin.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import static org.JMP.plugin.util.Lines.getLine;
import static org.JMP.plugin.util.Lines.prefix;

public class Settings {

    public static String getLang() {
        return  Main.getInstance().getConfig().getString("lang","en-US");
    }
    private static void sendSetLine(Player player, String nameSet) {
        String text = Main.getInstance().getConfig().getString(nameSet, ChatColor.RED + "null");

        if (text.equalsIgnoreCase("true")){
            text = ChatColor.GREEN + text;
        } else if (text.equalsIgnoreCase("false")) {
            text = ChatColor.RED + text;
        }
        player.sendMessage( ChatColor.YELLOW +"◆ " + ChatColor.GRAY + nameSet + ": " + ChatColor.RESET + text);
    }
    public static void sendSettings(Player player) {

        String set = getLine("lang", getLang(), "message.settings", "Settings");

        player.sendMessage(ChatColor.GRAY + "[" + set +"] - " + prefix);
        sendSetLine(player, "lang");
        sendSetLine(player, "nameserver");
        sendSetLine(player, "second-from-start");
        sendSetLine(player, "use-permission");
        sendSetLine(player, "auth-me.send-message-after-logging");

    }
}
