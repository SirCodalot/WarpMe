package me.codalot.warpme.menus;

import me.codalot.warpme.menus.buttons.editor.*;
import me.codalot.warpme.menus.buttons.general.EscButton;
import me.codalot.warpme.warps.Warp;

public class WarpEditor extends Menu {

    private Warp warp;

    public WarpEditor(Warp warp) {
        super(36, "§3§lWarp Editor");
        this.warp = warp;

        setItems();
    }

    public void setItems() {
        setButton(new ReDisplayNameWarpButton(warp), 10);
        setButton(new RenameWarpButton(warp), 20);
        setButton(new ChangeIconButton(warp, this), 13);
        setButton(new RelocateWarpButton(warp), 22);
        setButton(new ToggleUnlockableWarpButton(warp), 16);
        setButton(new RadiusWarpButton(warp), 24);

        setButton(new DeleteWarpButton(warp), 34);
        setButton(new EscButton(), 35);
    }

}
