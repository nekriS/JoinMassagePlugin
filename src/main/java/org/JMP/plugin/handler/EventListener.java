package org.JMP.plugin.handler;

import org.JMP.plugin.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.EventHandler;
import me.clip.placeholderapi.PlaceholderAPI;
import java.util.List;
import java.util.Objects;

public class EventListener implements Listener {

    String prefix = ChatColor.YELLOW + "[JoinMessagePlugin] " + ChatColor.RESET;
    public static void sendWelcomeMessage(Player player) {

        List<String> welcome_text_line = Main.getInstance().getConfig().getStringList("text");

        for (String s : welcome_text_line) {

            int players_online = player.getServer().getOnlinePlayers().size();
            String player_name = Objects.requireNonNull(player.getPlayer()).getDisplayName();
            String name_server = Main.getInstance().getConfig().getString("nameserver");
            String world_name_of_player = player.getWorld().getName();
            long time_world = player.getWorld().getTime();

            s = s.replace("{online}", Integer.toString(players_online));
            s = s.replace("{player}", player_name);
            s = s.replace("{world-name}", world_name_of_player);
            s = s.replace("{world-time}", Long.toString(time_world));
            s = s.replace("{nameserver}", name_server != null ? name_server : "Null");

            s = PlaceholderAPI.setPlaceholders(player.getPlayer(), s);
            player.sendMessage(s);
        }
    }

    public void sendMessage(Player player) {
        long time = Math.round(20 * Main.getInstance().getConfig().getDouble("second-from-start"));
        if (time != 0) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () -> sendWelcomeMessage(player), time);
        } else {
            sendWelcomeMessage(player);
        }
    }

    @EventHandler
    public void handleJoinServer(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (!Main.getInstance().getConfig().getBoolean("use-permission")) {
            sendMessage(player);
        } else {
            if (player.hasPermission("jmp.view")) {
                sendMessage(player);
            } else {
                Main.getInstance().getServer().getConsoleSender().sendMessage(prefix + "Player join, but haven't permission!");
            }
        }
    }
}
