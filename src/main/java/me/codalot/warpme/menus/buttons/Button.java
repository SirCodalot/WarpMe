package me.codalot.warpme.menus.buttons;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public abstract class Button {

    protected ItemStack item;

    public Button(ItemStack item) {
        this.item = item;
    }

    public abstract void onClick(InventoryClickEvent event);

    public ItemStack getItem() {
        return item;
    }

    public void setItem(ItemStack item) {
        this.item = item;
    }
}
