package it.sevenbits.homeworktwo.core.repository;

import it.sevenbits.homeworktwo.core.model.Task;
import it.sevenbits.homeworktwo.web.model.AddTaskRequest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class TasksRepository implements ITasksRepository {
    private Map<String, Task> taskMap;

    public TasksRepository() {
        taskMap = new HashMap<>();
    }

    @Override
    public String addTask(final AddTaskRequest addTaskRequest) {
        UUID taskID = UUID.randomUUID();
        taskMap.put(taskID.toString(),
                new Task(taskID.toString(),
                        addTaskRequest.getText(),
                        addTaskRequest.getStatus()));
        return taskID.toString();
    }

    @Override
    public List<Task> getAllTasks() {
        return Collections.unmodifiableList(new ArrayList<>(taskMap.values()));
    }

    @Override
    public Task getTask(String id) {
        return taskMap.get(id);
    }

    @Override
    public Task deleteTask(String id) {
        return taskMap.remove(id);
    }

    @Override
    public Task replaceTask(String id, Task newTask) {
        return taskMap.replace(id, newTask);
    }
}
