package me.codalot.warpme.menus;

import me.codalot.warpme.Loader;
import me.codalot.warpme.managers.WarpManager;
import me.codalot.warpme.menus.buttons.general.EscButton;
import me.codalot.warpme.menus.buttons.general.PageButton;
import me.codalot.warpme.menus.buttons.general.WarpButton;
import me.codalot.warpme.utils.ItemUtils;
import me.codalot.warpme.warps.Warp;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class WarpsMenu extends Menu {

    private static final ItemStack BARRIER = ItemUtils.build(Material.STAINED_GLASS_PANE, 0, " ");

    private int page;

    public WarpsMenu() {
        super(54, "§3§lWarps");
        page = 0;

        setItems();
    }

    private void setItems() {
        setButton(new EscButton(), 49);

        for (int i = 36; i < 45; i++)
            inventory.setItem(i, BARRIER);

        setWarpItems();
    }

    private void setWarpItems() {
        for (int i = 0; i < 14; i++) {
            int slot = i + 10 + (i >= 7 ? 2 : 0);
            Warp warp = null;
            try {
                warp = getManager().getWarps().get(i + page * 14);
            } catch (IndexOutOfBoundsException ignored) {}
            setButton(warp == null ? null : new WarpButton(warp), slot);
        }

        if (page > 0)
            setButton(new PageButton(this, PageButton.PageAction.PREVIOUS_PAGE), 36);
        else
            inventory.setItem(36, BARRIER);
        if (page < getMaxPages())
            setButton(new PageButton(this, PageButton.PageAction.NEXT_PAGE), 44);
        else
            inventory.setItem(44, BARRIER);

    }

    private int getMaxPages() {
        int items = getManager().getWarps().size();
        return items / 14  - (items % 14 == 0 ? 1 : 0);
    }

    public void nextPage(int next) {
        page += next;
        if (page < 0)
            page = 0;
        if (page > getMaxPages())
            page = getMaxPages();

        setWarpItems();
    }

    private WarpManager getManager() {
        return Loader.getInstance().getManager().getWarpManager();
    }

}