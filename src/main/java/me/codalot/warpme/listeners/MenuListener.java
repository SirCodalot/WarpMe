package me.codalot.warpme.listeners;

import me.codalot.warpme.managers.MenuManager;
import me.codalot.warpme.menus.Menu;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class MenuListener extends BaseListener {

    @EventHandler (priority = EventPriority.HIGH)
    public void onInventoryClick(InventoryClickEvent event) {
        Menu menu = getMenuManager().getMenu(event.getInventory());
        if (menu == null)
            return;

        menu.onClick(event);
    }

    @EventHandler (priority = EventPriority.LOWEST)
    public void onInventoryClose(InventoryCloseEvent event) {
        Menu menu = getMenuManager().getMenu(event.getInventory());
        if (menu == null)
            return;

        getMenuManager().removeMenu(menu);
    }


    private MenuManager getMenuManager() {
        return loader.getManager().getMenuManager();
    }

}
