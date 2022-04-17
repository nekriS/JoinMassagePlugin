package org.JMP.plugin.handler;


import org.JMP.plugin.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.EventHandler;
import me.clip.placeholderapi.PlaceholderAPI;

import java.util.*;

public class EventListener implements Listener {

    String prefix = ChatColor.YELLOW + "[JoinMessagePlugin] " + ChatColor.RESET;

    public static void sendWelcomeMessage(Player player, String group) {

        String path;

        if (!group.equalsIgnoreCase("old-cfg")) {
            path = "text." + group;
        } else {
            path = "text";
        }


        List<String> welcome_text_line = Main.getInstance().getConfig().getStringList(path);

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

    private static String getPlayerGroup(Player player, List<String> possibleGroups) {
        for (String group : possibleGroups) {
            if (player.hasPermission("group." + group)) {
                return group;
            }
        }
        return null;
    }
    public static void toCheckRole(Player player) {


        if (Main.getInstance().getConfig().getConfigurationSection("text") != null) {

            Set<String> keys = Objects.requireNonNull((Main.getInstance().getConfig().getConfigurationSection("text"))).getKeys(false);

            List<String> groups = new ArrayList<>(keys);

            String group = getPlayerGroup(player, groups);

            if (group == null) group = "default";

            sendWelcomeMessage(player, group);
        } else {
            sendWelcomeMessage(player, "old_cfg");
        }

    }

    public void toCheckDelay(Player player) {
        long time = Math.round(20 * Main.getInstance().getConfig().getDouble("second-from-start"));
        if (time != 0) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () -> toCheckRole(player), time);
        } else {
            toCheckRole(player);
        }
    }

    @EventHandler
    public void handleJoinServer(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (!Main.getInstance().getConfig().getBoolean("use-permission")) {
            toCheckDelay(player);
        } else {
            if (player.hasPermission("jmp.view")) {
                toCheckDelay(player);
            } else {
                Main.getInstance().getServer().getConsoleSender().sendMessage(prefix + "Player join, but haven't permission!");
            }
        }
    }
}
