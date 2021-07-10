package tech.needvoid.icons.icons.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import tech.needvoid.icons.IconsPlugin;
import tech.needvoid.icons.icons.IconsGUI;
import tech.needvoid.icons.utils.CC;
import tech.needvoid.icons.utils.command.SimpleCommand;

import java.util.Collections;

public class IconCommand extends SimpleCommand {

    private final IconsPlugin plugin;

    public IconCommand(IconsPlugin plugin) {
        super("icon");

        this.setAliases(Collections.singletonList("icongui"));

        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            sender.sendMessage(CC.colour("&cThis is a player command only"));
            return true;
        }

        Player player = (Player) sender;
        player.sendMessage(CC.colour("&aOpening the Icons GUI"));
        new IconsGUI(this.plugin, player).openIconGUI();
        return true;
    }
}
