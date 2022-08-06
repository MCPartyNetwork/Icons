package tech.needvoid.icons.icons;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import tech.needvoid.icons.IconsPlugin;
import tech.needvoid.icons.utils.CC;
import tech.needvoid.icons.utils.ItemFactory;
import tech.needvoid.icons.utils.ListUtils;

import java.util.List;

public class IconsGUI {

    private final IconsPlugin plugin;
    private final Player player;
    private final ItemStack glassFiller = new ItemFactory(Material.BLACK_STAINED_GLASS_PANE).setName(" ").build();

    public IconsGUI(IconsPlugin plugin, Player player) {
        this.plugin = plugin;
        this.player = player;
    }

    public void openIconGUI(int page) {
        Inventory iconInv = Bukkit.createInventory(null, this.plugin.getSettingsFile().getInt("GUI-SIZE"),
                (CC.colour(this.plugin.getSettingsFile().getString("TITLE"))));

        this.plugin.getIconsManager().setCurrentPage(player, page);
        for (int x = 0; x < iconInv.getSize(); x++) {
            iconInv.setItem(x, this.glassFiller); //Sets this in every single slot as a filler
        }

        List<Icons> icons = ListUtils.getSublist(
                this.plugin.getIconsManager().getIconsList(), page,
                this.plugin.getSettingsFile().getInt("GUI-PAGE-SIZE")
        );

        icons.forEach(icon -> {
            ItemFactory factory = new ItemFactory(icon.getMaterial());
            factory.setDurability(icon.getData());
            factory.setName(icon.getNameWithColor());

            if (player.hasPermission(icon.getPermission())) {
                factory.setLore(icon.getUnlockedLore());
            } else {
                factory.setLore(icon.getLockedLore());
            }

            iconInv.setItem(icon.getSlot(), factory.build());
        });

        //Back page item
        iconInv.setItem(this.plugin.getSettingsFile().getInt("FORMATTING.BACK-PAGE.SLOT"),
                itemFromSection(this.plugin.getSettingsFile().getAsYaml().getConfigurationSection("FORMATTING.BACK-PAGE")).build());

        //Next page item
        iconInv.setItem(this.plugin.getSettingsFile().getInt("FORMATTING.NEXT-PAGE.SLOT"),
                itemFromSection(this.plugin.getSettingsFile().getAsYaml().getConfigurationSection("FORMATTING.NEXT-PAGE")).build());

        //Name color reset item
        iconInv.setItem(this.plugin.getSettingsFile().getInt("FORMATTING.RESET.SLOT"),
                new ItemFactory(Material.BARRIER).setName("&cReset your icon").build());

        this.player.openInventory(iconInv);
    }
    
    public ItemFactory itemFromSection(ConfigurationSection section) {
        ItemFactory ret = new ItemFactory(Material.matchMaterial(section.getString("MATERIAL")), 1, (byte) section.getInt("DATA"));

        if (section.contains("NAME")) {
            ret.setName(section.getString("NAME"));
        }

        if (section.contains("LORE")) {
            ret.setName(section.getString("LORE"));
        }

        return ret;
    }
    
}
