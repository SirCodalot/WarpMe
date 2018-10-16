package me.codalot.warpme.menus.buttons.general;

import me.codalot.warpme.menus.WarpsMenu;
import me.codalot.warpme.menus.buttons.Button;
import me.codalot.warpme.utils.ItemUtils;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;

public class PageButton extends Button {

    private WarpsMenu menu;
    private PageAction action;

    public PageButton(WarpsMenu menu, PageAction action) {
        super(ItemUtils.build(Material.STAINED_GLASS_PANE, 4, action.name));
        this.menu = menu;
        this.action = action;
    }

    @Override
    public void onClick(InventoryClickEvent event) {
        menu.nextPage(action.page);
    }

    public enum PageAction {
        NEXT_PAGE("§e§l>", 1),
        PREVIOUS_PAGE("§e§l<", -1);

        public String name;
        public int page;

        PageAction(String name, int page) {
            this.name = name;
            this.page = page;
        }
    }
}
