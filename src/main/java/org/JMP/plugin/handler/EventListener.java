package org.JMP.plugin.handler;


import org.JMP.plugin.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class EventListener implements Listener {

    static String prefix = ChatColor.YELLOW + "[JoinMessagePlugin] " + ChatColor.RESET;

    @EventHandler
    public void handleJoinServer(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        boolean pl = Main.getInstance().getServer().getPluginManager().isPluginEnabled("AuthMe");
        boolean setting = Main.getInstance().getConfig().getBoolean("auth-me.send-message-after-logging");

        Methods.checkSettingHookedAuth(player, false);
        if ((!pl) && setting) {
            Main.getInstance().getServer().getConsoleSender().sendMessage(ChatColor.YELLOW + "[WARNING] " + prefix + ChatColor.YELLOW + "Config: 'send-message-after-logging: true' but AuthMe not founded!");
            Methods.checkSettingHookedAuth(player, true);
        }

    }


}
