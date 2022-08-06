package tech.needvoid.icons.icons;

import org.bukkit.entity.Player;
import tech.needvoid.icons.IconsPlugin;
import tech.needvoid.icons.utils.CC;
import tech.needvoid.icons.utils.ConfigFile;

import java.util.*;

public class IconsManager {

    private final IconsPlugin plugin;
    private final List<Icons> iconsList = new ArrayList<>();
    private final Map<UUID, Integer> currentPage = new HashMap<>();

    public IconsManager(IconsPlugin plugin) {
        this.plugin = plugin;
        reload();
    }

    public int getIconPerPage() {
        return plugin.getSettingsFile().getInt("GUI-PAGE-SIZE");
    }

    public void setCurrentPage(Player player, int page) {
        currentPage.put(player.getUniqueId(), page);
    }

    public int getCurrentPage(Player player) {
        return currentPage.getOrDefault(player.getUniqueId(), 1);
    }

    public List<Icons> getIconsList() {
        return this.iconsList;
    }

    public void reload() {
        iconsList.clear();

        ConfigFile iconsFile = this.plugin.getIconsFile();
        for (String icons : iconsFile.getAsYaml().getConfigurationSection("").getKeys(false)) {
            CC.log("loading icon " + iconsFile.getString(icons + ".ICON"));
            Icons icon = new Icons(iconsFile.getString(icons + ".ICON"), iconsFile.getString(icons + ".COLOR"), iconsFile.getInt(icons + ".SLOT"),
                    iconsFile.getString(icons + ".PERMISSION"), iconsFile.getString(icons + ".MATERIAL"), iconsFile.getInt(icons + ".DATA"),
                    iconsFile.getString(icons + ".NAME"), iconsFile.getStringList(icons + ".UNLOCKED-LORE"), iconsFile.getStringList(icons + ".LOCKED-LORE"));
            this.iconsList.add(icon);
            CC.log(icon.toString());
        }
    }
}
