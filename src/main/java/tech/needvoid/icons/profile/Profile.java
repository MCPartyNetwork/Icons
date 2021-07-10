package tech.needvoid.icons.profile;

import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import tech.needvoid.icons.IconsPlugin;
import tech.needvoid.icons.utils.CC;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class Profile {

    private final IconsPlugin plugin;

    private final OfflinePlayer player;
    private final UUID uuid;

    private String icon;

    /**
     * @param plugin The java plugin object
     * @param player OfflinePlayer object
     */
    public Profile(IconsPlugin plugin, OfflinePlayer player) {
        this.plugin = plugin;

        this.player = player;
        this.uuid = player.getUniqueId();

        this.loadProfile();
    }

    public void loadProfile() {
        File dataFile = new File(this.plugin.getProfileManager().getDataDirectory() + File.separator, this.uuid.toString() + ".yml");

        if (!dataFile.exists()) {
            this.loadFirstTimeData();
            return;
        }

        YamlConfiguration userData = YamlConfiguration.loadConfiguration(dataFile);
        this.icon = userData.getString("icon");
    }

    public void saveProfile() {
        File dataFile = new File(this.plugin.getProfileManager().getDataDirectory() + File.separator, this.uuid.toString() + ".yml");
        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                CC.log("Failed to save profile for " + this.player.getName());
                e.printStackTrace();
            }
        }

        YamlConfiguration userData = YamlConfiguration.loadConfiguration(dataFile);
        userData.set("icon", this.icon);

        try {
            userData.save(dataFile);
        } catch (IOException e) {
            CC.log("Failed to save profile for " + this.player.getName());
            e.printStackTrace();
        }

        this.plugin.getProfileManager().getProfileMap().remove(this.uuid);
    }

    public OfflinePlayer getPlayer() {
        return this.player;
    }

    public UUID getUUID() {
        return this.uuid;
    }

    public String getIcon() {
        return this.icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void resetIcon() {
        this.loadFirstTimeData();
    }

    private void loadFirstTimeData() {
        this.icon = this.plugin.getSettingsFile().getString("DEFAULT-ICON");
    }
}