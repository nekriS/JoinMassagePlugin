package org.JMP.plugin.util;

import org.JMP.plugin.Main;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.List;

public class Lines {

    static String prefix = ChatColor.YELLOW + "[JoinMessagePlugin] " + ChatColor.RESET;

    public static File returnFile(String type, String nameFile, String path) {

        File lines = new File(Main.getInstance().getDataFolder(),"/" + type + "/" + nameFile + ".yml");
        if ( Main.getInstance().getResource(type + "/" + nameFile + ".yml") != null ) {

            if (!lines.exists()) {
                Main.getInstance().saveResource(type + "/" + nameFile + ".yml", false);
                lines = new File(Main.getInstance().getDataFolder(), "/" + type + "/" + nameFile + ".yml");
            }
        } else {
            return returnFile(type, "en-US", path);
        }
        return lines; //return file with lines
    }

    public static String getLine(String type, String language, String path, String def) {

        File lines = returnFile(type, language, path);
        FileConfiguration messagesConfig = YamlConfiguration.loadConfiguration(lines);

        return messagesConfig.getString(path, def).replace('&','§');
    }


    public static List<String> getLines(String type, String language, String path) {

        File lines = returnFile(type, language, path);
        FileConfiguration messagesConfig = YamlConfiguration.loadConfiguration(lines);

        List<String> strings = messagesConfig.getStringList(path);

        if (strings.size() > 0) {
            return strings;
        } else {
            return List.of(prefix + ChatColor.RED + "Help not founded!");
        }
    }
}
