package org.JMP.plugin;


import org.JMP.plugin.commands.Info;
import org.JMP.plugin.handler.EventHandler;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.Listener;




public class Main extends JavaPlugin implements Listener {

    String prefix = ChatColor.YELLOW + "[JoinMessagePlugin] " + ChatColor.RESET;

    @Override
    public void onEnable() {
        getServer().getConsoleSender().sendMessage(prefix + "Plugin is " + ChatColor.GREEN + "enabled" + ChatColor.RESET + "!");
        getServer().getPluginManager().registerEvents(new EventHandler(), this);
        getServer().getPluginCommand("joinmessageplugin").setExecutor(new Info());
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(prefix + "Plugin is " + ChatColor.RED + "disabled" + ChatColor.RESET + "!");
    }


}
