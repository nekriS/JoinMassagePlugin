package org.JMP.plugin.handler;

import org.JMP.plugin.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.EventHandler;
import me.clip.placeholderapi.PlaceholderAPI;
import java.util.List;
import java.util.Objects;

public class EventListener implements Listener {


    public static void sendWelcomeMessage(Player player) {

        List<String> welcome_text_line = Main.getInstance().getConfig().getStringList("text");
        //getString("text");
        //welcome_text_line = welcome_text_line.substring(1, welcome_text_line.length()-1);


        //String[] welcome_text_array = welcome_text_line.split(", ");

        for (String s : welcome_text_line) {

            int players_online = player.getServer().getOnlinePlayers().size();
            String player_name = Objects.requireNonNull(player.getPlayer()).getDisplayName();
            String name_server = Main.getInstance().getConfig().getString("nameserver");
            String world_name_of_player = player.getWorld().getName();
            long time_world = player.getWorld().getTime();



            //Long time = Bukkit.getServer().getWorld(wrld).getTime();
            //String world_player = player.getServer().getWorld(player.getUniqueId()).getName();
            //String world_time = Long.toString(Objects.requireNonNull(player.getServer().getWorld(world_player)).getTime());


            s = s.replace("{online}", Integer.toString(players_online));
            s = s.replace("{player}", player_name);
            s = s.replace("{worldname}", world_name_of_player);
            s = s.replace("{worldtime}", Long.toString(time_world));
            s = s.replace("{nameserver}", name_server != null ? name_server : "Null");

            s = PlaceholderAPI.setPlaceholders(player.getPlayer(), s);
            player.sendMessage(s);
        }
    }

    @EventHandler
    public void handleJoinServer(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        long time = Math.round(20 * Main.getInstance().getConfig().getDouble("second-from-start"));
        if (time != 0) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () -> sendWelcomeMessage(player), time);
        } else {
            sendWelcomeMessage(player);
        }


    }

}
