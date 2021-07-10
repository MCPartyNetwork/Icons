package tech.needvoid.icons.icons.commands.subcommands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import tech.needvoid.icons.IconsPlugin;
import tech.needvoid.icons.icons.Icons;
import tech.needvoid.icons.profile.Profile;
import tech.needvoid.icons.utils.CC;
import tech.needvoid.icons.utils.command.SubCommand;

import java.util.Arrays;

public class SetIconCommand extends SubCommand {

    private final IconsPlugin plugin;

    public SetIconCommand(IconsPlugin plugin) {
        super("set");

        this.setDescription("Set the icon for someone");
        this.setAliases(Arrays.asList("iconset", "seticon"));

        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 2) {
            sender.sendMessage(CC.colour(this.plugin.getLangFile().getString("MESSAGES.INVALID-TARGET")));
            return true;
        }

        Player target = Bukkit.getPlayer(args[1]);

        if (target == null) {
            sender.sendMessage(CC.colour(this.plugin.getLangFile().getString("MESSAGES.INVALID-TARGET")));
            return true;
        }

        final Profile targetProfile = this.plugin.getProfileManager().getProfileByPlayer(target.getUniqueId());

        Icons icon = null;

        for (Icons icons : this.plugin.getIconsManager().getIconsList()) {
            if (args[2].equalsIgnoreCase(CC.colour(icons.getName()))) {
                icon = icons;
                break;
            }
        }

        if(icon == null) {
            sender.sendMessage(CC.colour(this.plugin.getLangFile().getString("MESSAGES.INVALID-ICON")));
            return false;
        }

        targetProfile.setIcon(icon.getIconWithColor());
        target.sendMessage(CC.colour(this.plugin.getLangFile().getString("MESSAGES.ICONS.ADMIN.TARGET-CHANGE-ICON-RECEIVED-MESSAGE"))
                .replace("%icon%", CC.colour(icon.getIconWithColor())));
        sender.sendMessage(CC.colour(this.plugin.getLangFile().getString("MESSAGES.ICONS.ADMIN.CHANGE-TARGET-ICON"))
                .replace("%target%", CC.colour(target.getDisplayName()))
                .replace("%icon%", CC.colour(icon.getIconWithColor())));

        return true;
    }
}
