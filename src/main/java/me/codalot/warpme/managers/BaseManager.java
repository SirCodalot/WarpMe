package me.codalot.warpme.managers;

import me.codalot.warpme.Loader;

public abstract class BaseManager {

    protected static Loader loader = Loader.getInstance();

    protected abstract void save();

}
