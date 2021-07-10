package tech.needvoid.icons.icons.commands;

import tech.needvoid.icons.IconsPlugin;
import tech.needvoid.icons.icons.commands.subcommands.ReloadCommand;
import tech.needvoid.icons.icons.commands.subcommands.ResetIconCommand;
import tech.needvoid.icons.icons.commands.subcommands.SetIconCommand;
import tech.needvoid.icons.utils.command.AdvancedCommand;

import java.util.Arrays;


public class IconsCommand extends AdvancedCommand {

    private final IconsPlugin plugin;

    public IconsCommand(IconsPlugin plugin) {
        super("icons");

        this.setDescription("The admin command for icons");
        this.setAliases(Arrays.asList("iconadmin"));
        this.setPermission("icons.admin");

        this.addSubCommands(Arrays.asList(
                new ReloadCommand(plugin),
                new SetIconCommand(plugin),
                new ResetIconCommand(plugin)
        ));

        this.plugin = plugin;
    }
}
