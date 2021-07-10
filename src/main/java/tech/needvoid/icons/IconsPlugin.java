package tech.needvoid.icons;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import tech.needvoid.icons.icons.IconsGUIListener;
import tech.needvoid.icons.icons.IconsManager;
import tech.needvoid.icons.icons.commands.IconCommand;
import tech.needvoid.icons.icons.commands.IconsCommand;
import tech.needvoid.icons.profile.ProfileManager;
import tech.needvoid.icons.profile.listeners.ProfileJoinEvent;
import tech.needvoid.icons.profile.listeners.ProfileQuitEvent;
import tech.needvoid.icons.utils.CC;
import tech.needvoid.icons.utils.ConfigFile;
import tech.needvoid.icons.utils.command.CommandHandler;

import java.util.Arrays;

public final class IconsPlugin extends JavaPlugin {

    private ConfigFile iconsFile;
    private ConfigFile settingsFile;
    private ConfigFile langFile;
    private ProfileManager profileManager;
    private IconsManager iconsManager;

    @Override
    public void onEnable() {
        long start = System.currentTimeMillis();
        CC.log("Starting plugin");

        this.iconsFile = new ConfigFile(this, "icons.yml");
        this.settingsFile = new ConfigFile(this, "settings.yml");
        this.langFile = new ConfigFile(this, "lang.yml");

        this.profileManager = new ProfileManager(this);
        this.iconsManager = new IconsManager(this);

        Arrays.asList(
                new ProfileJoinEvent(this),
                new ProfileQuitEvent(this),
                new IconsGUIListener(this)
        ).forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, this));

        CommandHandler commandHandler = new CommandHandler(this);
        commandHandler.setNoPermissionMessage(this.getLangFile().getString("MESSAGES.NO-PERMISSION"));
        commandHandler.registerSimpleCommand(new IconCommand(this));
        commandHandler.registerAdvancedCommand(new IconsCommand(this));

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new PlaceholderHook(this);
        }

        CC.log("Plugin started in " + (System.currentTimeMillis() - start) + "ms");
    }

    @Override
    public void onDisable() {
        Bukkit.getOnlinePlayers().forEach(player -> this.profileManager.saveProfile(player));
    }

    public ProfileManager getProfileManager() {
        return this.profileManager;
    }

    public IconsManager getIconsManager() {
        return iconsManager;
    }

    public ConfigFile getSettingsFile() {
        return settingsFile;
    }

    public ConfigFile getIconsFile() {
        return iconsFile;
    }

    public ConfigFile getLangFile() {
        return langFile;
    }
}
