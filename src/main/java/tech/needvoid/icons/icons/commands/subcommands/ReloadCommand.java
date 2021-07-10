package tech.needvoid.icons.icons.commands.subcommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import tech.needvoid.icons.IconsPlugin;
import tech.needvoid.icons.utils.CC;
import tech.needvoid.icons.utils.ConfigFile;
import tech.needvoid.icons.utils.command.SubCommand;

import java.util.Arrays;

public class ReloadCommand extends SubCommand {

    private final IconsPlugin plugin;

    public ReloadCommand(IconsPlugin plugin) {
        super("reload");

        this.setDescription("Reload the Icons plugin");
        this.setAliases(Arrays.asList("rl"));

        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        ConfigFile settingsFile = this.plugin.getSettingsFile();
        ConfigFile langFile = this.plugin.getLangFile();
        ConfigFile iconsFile = this.plugin.getIconsFile();

        settingsFile.reload(false);
        langFile.reload(false);
        iconsFile.reload(false);

        this.plugin.getIconsManager().reload();

        sender.sendMessage(CC.colour("&aIcons has been reloaded"));
        return true;
    }
}
