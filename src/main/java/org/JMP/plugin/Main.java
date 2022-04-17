package org.JMP.plugin;


import org.JMP.plugin.command.jmpCommand;
import org.JMP.plugin.handler.EventListener;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.Listener;
import org.JMP.plugin.util.Metrics;



public class Main extends JavaPlugin implements Listener {

    String prefix = ChatColor.YELLOW + "[JoinMessagePlugin] " + ChatColor.RESET;

    private static Main instance;

    @Override
    public void onEnable() {


        //Metrics metrics = new Metrics(this, pluginId);
        this.loadMetrics();

        instance = this;

        saveDefaultConfig();

        int loadVersion = getConfig().getInt("version-cfg");

        new jmpCommand();
        getServer().getPluginManager().registerEvents(new EventListener(), this);
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
