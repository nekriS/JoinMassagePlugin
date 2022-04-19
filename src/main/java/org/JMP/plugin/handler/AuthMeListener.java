package org.JMP.plugin.handler;

import fr.xephi.authme.events.LoginEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import static org.JMP.plugin.handler.EventListener.checkSettingHookedAuth;

public class AuthMeListener implements Listener {

    @EventHandler
    public void onLogin(LoginEvent event) {
        Player player = event.getPlayer();
        checkSettingHookedAuth(player, true);
    }
}
