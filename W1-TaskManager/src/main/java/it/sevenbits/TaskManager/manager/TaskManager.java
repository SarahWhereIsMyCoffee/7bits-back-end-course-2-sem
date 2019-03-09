package it.sevenbits.TaskManager.manager;


import java.util.HashMap;
import java.util.UUID;

public class TaskManager {
    private HashMap<UUID, Task> taskMap = new HashMap<>();
    private static TaskManager taskManager;

    private TaskManager() {
    }

    public static TaskManager getInstance() {
        if (taskManager == null) {
            taskManager = new TaskManager();
        }
        return taskManager;
    }

    public void put(final UUID uuid, final String message) {
        taskMap.put(uuid, new Task(uuid, message));
    }

    public void delete(final UUID uuid) {
        taskMap.remove(uuid);
    }

    public Task get(final UUID uuid) {
        return taskMap.get(uuid);
    }

    public HashMap<UUID, Task> getTaskList() {
        return taskMap;
    }
}
