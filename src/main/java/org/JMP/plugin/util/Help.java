package org.JMP.plugin.util;

import org.bukkit.entity.Player;

import static org.JMP.plugin.util.Lang.getStringLang;

public class Help {

    public static void sendHelp(Player player, String lang) {

        String helpLine = getStringLang(lang, "help");
        String[] help = helpLine.substring(1, helpLine.length()-1).split("#, ");
        for (String s : help) {
            player.sendMessage(s);
        }


    }


}
