package org.JMP.plugin.handler;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.EventHandler;

public class EventListener implements Listener {

    @EventHandler
    public void handleJoinServer(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        player.sendMessage("");
        player.sendMessage(ChatColor.YELLOW + "Добро пожаловать на сервер!");
    }

}
