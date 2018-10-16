package me.codalot.warpme.managers;

import me.codalot.warpme.menus.Menu;
import org.bukkit.inventory.Inventory;

import java.util.HashSet;
import java.util.Set;

public class MenuManager extends BaseManager {

    public Set<Menu> menus;

    public MenuManager() {
        menus = new HashSet<>();
    }

    @Override
    protected void save() {}

    public void addMenu(Menu menu) {
        menus.add(menu);
    }

    public void removeMenu(Menu menu) {
        menus.remove(menu);
    }

    public Menu getMenu(Inventory inventory) {
        for (Menu menu : menus)
            if (menu.getInventory().equals(inventory))
                return menu;
        return null;
    }

}
