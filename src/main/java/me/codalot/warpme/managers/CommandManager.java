package me.codalot.warpme.managers;

import me.codalot.warpme.commands.*;

public class CommandManager extends BaseManager {

    public CommandManager() {
        new MakeWarpCommand();
        new DelWarpCommand();
        new WarpsCommand();
        new WarpCommand();
        new EditWarpCommand();
    }

    @Override
    protected void save() {}
}
