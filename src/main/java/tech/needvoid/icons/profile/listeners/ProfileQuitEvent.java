package tech.needvoid.icons.profile.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import tech.needvoid.icons.IconsPlugin;

public class ProfileQuitEvent implements Listener {

    private final IconsPlugin plugin;

    public ProfileQuitEvent(IconsPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onProfileQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        this.plugin.getProfileManager().saveProfile(player);
    }
}
