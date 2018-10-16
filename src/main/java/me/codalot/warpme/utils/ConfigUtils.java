package me.codalot.warpme.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class ConfigUtils {

    public static HashMap<String, Object> getConfigSectionValue(Object o, boolean deep) {
        if (o == null) {
            return null;
        }
        Map<String, Object> map;
        if (o instanceof ConfigurationSection) {
            map = ((ConfigurationSection) o).getValues(deep);
        } else if (o instanceof Map) {
            map = (Map<String, Object>) o;
        } else {
            return null;
        }
        return new HashMap<>(map);
    }

    public static String serializeLocation(Location location) {
        World world = location.getWorld();
        double x = location.getX();
        double y = location.getY();
        double z = location.getZ();
        float yaw = location.getYaw();
        float pitch = location.getPitch();

        return world.getName() + ":" + x + ":" + y + ":" + z + ":" + yaw + ":" + pitch;
    }

    public static Location deserializeLocation(String string) {
        String[] split = string.split(":");

        World world = Bukkit.getWorld(split[0]);
        double x = Double.valueOf(split[1]);
        double y = Double.valueOf(split[2]);
        double z = Double.valueOf(split[3]);
        float yaw = Float.valueOf(split[4]);
        float pitch = Float.valueOf(split[5]);

        return new Location(world, x, y, z, yaw, pitch);
    }

    public static String serializeItem(ItemStack item) {
        return item.getType().toString() + ":" + item.getDurability();
    }

    public static ItemStack deserializeItem(String string) {
        String[] split = string.split(":");
        return new ItemStack(Material.matchMaterial(split[0]), 1, Short.valueOf(split[1]));
    }

}
