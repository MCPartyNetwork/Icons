package tech.needvoid.icons.icons;

import org.bukkit.Material;

import java.util.List;

public class Icons {

    private final String icon;
    private final String color;
    private final int slot;
    private final String permission;
    private final Material material;
    private final byte data;
    private final String iconWithColor;
    private final String name;
    private final String nameWithColor;
    private final List<String> unlockedLore;
    private final List<String> lockedLore;

    public Icons(String icon, String color, int slot, String permission, String material, int data, String name, List<String> unlockedLore, List<String> lockedLore) {
        this.icon = icon;
        this.color = color;
        this.slot = slot;
        this.permission = permission;
        this.material = Material.valueOf(material.toUpperCase());
        this.data = (byte) data;
        this.iconWithColor = this.color + this.icon;
        this.name = name;
        this.nameWithColor = this.color + this.name;
        this.unlockedLore = unlockedLore;
        this.lockedLore = lockedLore;
    }

    public String getIcon() {
        return this.icon;
    }

    public String getColor() {
        return this.color;
    }

    public int getSlot() {
        return this.slot;
    }

    public String getPermission() {
        return this.permission;
    }

    public Material getMaterial() {
        return this.material;
    }

    public byte getData() {
        return this.data;
    }

    public String getIconWithColor() {
        return this.iconWithColor;
    }

    public String getName() {
        return this.name;
    }

    public String getNameWithColor() {
        return this.nameWithColor;
    }

    public List<String> getUnlockedLore() {
        return this.unlockedLore;
    }

    public List<String> getLockedLore() {
        return this.lockedLore;
    }
}
