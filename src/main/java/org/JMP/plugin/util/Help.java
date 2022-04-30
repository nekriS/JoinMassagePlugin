package org.JMP.plugin.util;

import org.bukkit.entity.Player;

import java.util.List;

import static org.JMP.plugin.util.Lines.getLines;
public class Help {



    public static void sendHelp(Player player, String lang) {
        List<String> help = getLines("lang", lang, "help");

        for (String s : help) {
            player.sendMessage(s.replace('&','§'));
        }
    }


}
