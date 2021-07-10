package tech.needvoid.icons.icons;

import tech.needvoid.icons.IconsPlugin;
import tech.needvoid.icons.utils.CC;
import tech.needvoid.icons.utils.ConfigFile;

import java.util.ArrayList;
import java.util.List;

public class IconsManager {

    private final IconsPlugin plugin;
    private final List<Icons> iconsList = new ArrayList<>();

    public IconsManager(IconsPlugin plugin) {
        this.plugin = plugin;
        reload();
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
