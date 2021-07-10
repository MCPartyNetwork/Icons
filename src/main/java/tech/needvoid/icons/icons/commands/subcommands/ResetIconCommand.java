package tech.needvoid.icons.icons.commands.subcommands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import tech.needvoid.icons.IconsPlugin;
import tech.needvoid.icons.profile.Profile;
import tech.needvoid.icons.utils.CC;
import tech.needvoid.icons.utils.command.SubCommand;

import java.util.Arrays;

public class ResetIconCommand extends SubCommand {

    private final IconsPlugin plugin;

    public ResetIconCommand(IconsPlugin plugin) {
        super("reset");

        this.setDescription("Reset the icon of a target");
        this.setAliases(Arrays.asList("iconreset", "reseticon"));

        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 1) {
            sender.sendMessage(CC.colour(this.plugin.getLangFile().getString("MESSAGES.INVALID-TARGET")));
            return false;
        }

        Player target = Bukkit.getPlayer(args[1]);

        if (target == null) {
            sender.sendMessage(CC.colour(this.plugin.getLangFile().getString("MESSAGES.INVALID-TARGET")));
            return false;
        }

        Profile targetProfile = this.plugin.getProfileManager().getProfileByPlayer(target.getUniqueId());

        targetProfile.resetIcon();
        target.sendMessage(CC.colour(this.plugin.getLangFile().getString("MESSAGES.ICONS.ADMIN.TARGET-RESET-ICON-RECEIVED-MESSAGE")));
        sender.sendMessage(CC.colour(this.plugin.getLangFile().getString("MESSAGES.ICONS.ADMIN.RESET-TARGET-ICON"))
                .replace("%target%", target.getDisplayName()));

        return true;
    }
}
