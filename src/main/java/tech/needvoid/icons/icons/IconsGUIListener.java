package tech.needvoid.icons.icons;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import tech.needvoid.icons.IconsPlugin;
import tech.needvoid.icons.profile.Profile;
import tech.needvoid.icons.utils.CC;

public class IconsGUIListener implements Listener {

    private final IconsPlugin plugin;

    public IconsGUIListener(IconsPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onClickInventory(InventoryClickEvent event) {
        if (event.getClickedInventory() == null) return;
        if (!event.getView().getTitle().equalsIgnoreCase(CC.colour(this.plugin.getSettingsFile().getString("TITLE")))) return;
        event.setCancelled(true);
        if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR) return;
        if (event.getCurrentItem().getItemMeta() == null) return;
        Player player = (Player) event.getWhoClicked();
        String clickedItemName = event.getCurrentItem().getItemMeta().getDisplayName();
        Profile profile = this.plugin.getProfileManager().getProfileByPlayer(player);

        for (Icons icons : this.plugin.getIconsManager().getIconsList()) {
            if (clickedItemName.equalsIgnoreCase(CC.colour(icons.getNameWithColor()))) {
                if (player.hasPermission(icons.getPermission())) {
                    profile.setIcon(icons.getIconWithColor());
                    player.sendMessage(CC.colour(this.plugin.getLangFile().getString("MESSAGES.ICONS.CHANGE-ICON")
                            .replace("%icon%", CC.colour(icons.getIconWithColor()))));
                    player.closeInventory();
                } else {
                    player.sendMessage(CC.colour(this.plugin.getLangFile().getString("MESSAGES.NO-PERMISSION-PURCHASE")));
                }
            }
        }

        if (clickedItemName.equalsIgnoreCase(CC.colour("&cReset your icon"))) {
            profile.resetIcon();
            player.sendMessage(CC.colour(this.plugin.getLangFile().getString("MESSAGES.ICONS.RESET-ICON")));
        }
    }
}
