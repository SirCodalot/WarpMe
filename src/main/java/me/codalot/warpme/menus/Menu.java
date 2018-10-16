package me.codalot.warpme.menus;

import me.codalot.warpme.Loader;
import me.codalot.warpme.menus.buttons.Button;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;

public abstract class Menu {

    protected Inventory inventory;
    public HashMap<Integer, Button> buttons;

    public Menu(int size, String title) {
        inventory = Bukkit.createInventory(null, size, title);
        buttons = new HashMap<>();
        Loader.getInstance().getManager().getMenuManager().addMenu(this);
    }

    public void open(Player player) {
        player.openInventory(inventory);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setButton(Button button, int slot) {
        if (button == null) {
            inventory.setItem(slot, null);
            buttons.remove(slot);
        } else {
            inventory.setItem(slot, button.getItem());
            buttons.put(slot, button);
        }
    }

    public void onClick(InventoryClickEvent event) {
        event.setCancelled(true);

        Button button = buttons.get(event.getSlot());
        if (button != null)
            button.onClick(event);

        for (int i : buttons.keySet())
            setButton(buttons.get(i), i);
    }

}
