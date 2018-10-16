package me.codalot.warpme.managers;

import me.codalot.warpme.listeners.MenuListener;
import me.codalot.warpme.listeners.PlayerListener;

public class ListenerManager extends BaseManager {

    public ListenerManager() {
        new PlayerListener();
        new MenuListener();
    }

    @Override
    protected void save() {}
}
