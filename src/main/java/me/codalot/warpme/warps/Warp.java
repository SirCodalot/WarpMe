package me.codalot.warpme.warps;

import me.codalot.warpme.utils.ConfigUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Warp {

    private String name;
    private String displayName;

    private boolean unlockable;
    private int radius;

    private Location location;

    private ItemStack icon;

    public Warp(String name, Location location) {
        this.name = name;
        this.location = location;
        displayName = name;
        unlockable = false;
        radius = 5;
        icon = new ItemStack(Material.ENDER_PEARL);
    }

    public Warp(String name, Map<String, Object> map) {
        this.name = name;
        displayName = (String) map.get("display_name");
        location = ConfigUtils.deserializeLocation((String) map.get("location"));
        unlockable = (Boolean) map.get("unlockable");
        radius = (int) map.get("radius");
        icon = ConfigUtils.deserializeItem((String) map.get("icon"));
    }

    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("display_name", displayName);
        map.put("unlockable", unlockable);
        map.put("location", location.getWorld().getName() + ":" + location.getX() + ":" + location.getY() + ":" + location.getZ() + ":" + location.getYaw() + ":" + location.getPitch());
        map.put("icon", icon.getType().toString() + ":" + icon.getDurability());
        map.put("radius", radius);
        return map;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public boolean isUnlockable() {
        return unlockable;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setIcon(ItemStack icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setUnlockable(boolean unlockable) {
        this.unlockable = unlockable;
    }

    public Location getLocation() {
        return location;
    }

    public ItemStack getIcon() {
        return icon;
    }

}
