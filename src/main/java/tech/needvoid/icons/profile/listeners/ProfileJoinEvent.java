package tech.needvoid.icons.profile.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import tech.needvoid.icons.IconsPlugin;

public class ProfileJoinEvent implements Listener {

    private final IconsPlugin plugin;

    public ProfileJoinEvent(IconsPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onProfileJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        this.plugin.getProfileManager().loadProfile(player);
    }
}
