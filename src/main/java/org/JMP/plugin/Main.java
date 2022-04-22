package org.JMP.plugin;


import org.JMP.plugin.command.jmpCommand;
import org.JMP.plugin.handler.AuthMeListener;
import org.JMP.plugin.handler.EventListener;
import org.JMP.plugin.util.Metrics;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import static org.JMP.plugin.util.Lang.getStringLang;

public class Main extends JavaPlugin implements Listener {

    String prefix = ChatColor.YELLOW + "[JoinMessagePlugin] " + ChatColor.RESET;

    private static Main instance;

    @Override
    public void onEnable() {

        boolean pl = this.getServer().getPluginManager().isPluginEnabled("AuthMe");

        this.loadMetrics();

        instance = this;

        saveDefaultConfig();

        int loadVersion = getConfig().getInt("version-cfg");

        getStringLang("en-US", "version", "version");
        getStringLang("ru-RU", "version", "version");

        new jmpCommand();
        getServer().getPluginManager().registerEvents(new EventListener(), this);

        if (pl) {
            getServer().getPluginManager().registerEvents(new AuthMeListener(), this);
            this.getServer().getConsoleSender().sendMessage(prefix + "AuthMe has been hooked!");
        }

        getServer().getConsoleSender().sendMessage(prefix + "Plugin is " + ChatColor.GREEN + "enabled" + ChatColor.RESET + "!");

    }



    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(prefix + "Plugin is " + ChatColor.RED + "disabled" + ChatColor.RESET + "!");
    }

    public static Main getInstance() { return instance; }

    private void loadMetrics() {
        int pluginId = 14964;
        new Metrics(this, pluginId);
    }



}



