package org.JMP.plugin;


import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.Listener;
import org.bukkit.entity.Player;



public class Main extends JavaPlugin implements Listener {

    String prefix = ChatColor.YELLOW + "[JoinMessagePlugin] " + ChatColor.RESET;

    @Override
    public void onEnable() {
        getServer().getConsoleSender().sendMessage(prefix + "Plugin is " + ChatColor.GREEN + "enabled" + ChatColor.RESET + "!");
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {

        getServer().getConsoleSender().sendMessage(prefix + "Plugin is " + ChatColor.RED + "disabled" + ChatColor.RESET + "!");
    }

    @EventHandler
    public void handleJoinServer(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.sendMessage(ChatColor.YELLOW + "Добро пожаловать на сервер!");
    }
}
