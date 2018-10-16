package me.codalot.warpme.managers;

public class Manager extends BaseManager {

    private MenuManager menuManager;
    private CommandManager commandManager;
    private ListenerManager listenerManager;
    private FileManager fileManager;
    private PlayerManager playerManager;
    private WarpManager warpManager;
    private TaskManager taskManager;

    public void load() {
        menuManager = new MenuManager();
        commandManager = new CommandManager();
        listenerManager = new ListenerManager();
        fileManager = new FileManager();
        warpManager = new WarpManager();
        playerManager = new PlayerManager();
        taskManager = new TaskManager();
    }

    @Override
    public void save() {
        taskManager.save();
        menuManager.save();
        playerManager.save();
        warpManager.save();
        fileManager.save();
    }

    public MenuManager getMenuManager() {
        return menuManager;
    }

    public WarpManager getWarpManager() {
        return warpManager;
    }

    public FileManager getFileManager() {
        return fileManager;
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }
}
