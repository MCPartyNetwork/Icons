package tech.needvoid.icons;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import tech.needvoid.icons.profile.Profile;
import tech.needvoid.icons.utils.CC;

public class PlaceholderHook extends PlaceholderExpansion {

    private final IconsPlugin plugin;

    public PlaceholderHook(IconsPlugin plugin) {
        this.plugin = plugin;
        this.register();
    }

    @Override
    public @NotNull String getIdentifier() {
        return "icons";
    }

    @Override
    public @NotNull String getAuthor() {
        return "NeedVoid";
    }

    @Override
    public @NotNull String getVersion() {
        return "0.0.1";
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onPlaceholderRequest(Player player, @NotNull String identifier) {
        Profile profile = this.plugin.getProfileManager().getProfileByPlayer(player);
        if (identifier.equalsIgnoreCase("icon")) {
            return CC.colour(profile.getIcon());
        }

        return "[Icons] Invalid Placeholder Request";
    }
}
