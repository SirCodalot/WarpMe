package me.codalot.warpme.managers;

import me.codalot.warpme.tasks.WarpTeleportTask;

public class TaskManager extends BaseManager {
    @Override
    protected void save() {
        try {
            for (WarpTeleportTask task : WarpTeleportTask.instances)
                task.cancel();
        } catch (NoClassDefFoundError ignored) {}
    }
}
