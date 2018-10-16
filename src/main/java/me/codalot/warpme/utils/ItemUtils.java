package me.codalot.warpme.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class ItemUtils {

    public static ItemStack build(Material material, int data) {
        return new ItemStack(material, 1, (short) data);
    }

    public static ItemStack build(Material material, int data, String name) {
        ItemStack item = build(material, data);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack build(Material material, int data, String name, String... lore) {
        ItemStack item = build(material, data, name);
        ItemMeta meta = item.getItemMeta();
        meta.setLore(Arrays.asList(lore));
        item.setItemMeta(meta);
        return item;
    }

}
