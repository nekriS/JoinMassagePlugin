package org.JMP.plugin.util;

import org.JMP.plugin.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
public class Lang {

    public static String getStringLang(String language, String path) {
        File lang = new File(Main.getInstance().getDataFolder(),"/lang/" + language + ".yml");

        if ( Main.getInstance().getResource("lang/" + language + ".yml") != null ) {

            if (!lang.exists()) {
                    Main.getInstance().saveResource("lang/" + language + ".yml", false);
                    lang = new File(Main.getInstance().getDataFolder(), "/lang/" + language + ".yml");
                }

        } else {

            lang = new File(Main.getInstance().getDataFolder(), "/lang/en-US.yml");

        }
        FileConfiguration messagesConfig = YamlConfiguration.loadConfiguration(lang);

        return messagesConfig.getString(path).replace('&','§');
    }

}
