package it.sevenbits.practicetwo.core.repository;

import it.sevenbits.practicetwo.core.model.Task;
import it.sevenbits.practicetwo.web.model.AddTaskRequest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class TasksRepository implements ITasksRepository {
    private Map<String, Task> taskMap = new HashMap<>();

    @Override
    public String addTask(AddTaskRequest addTaskRequest) {
        UUID taskID = UUID.randomUUID();
        taskMap.put(taskID.toString(), new Task(taskID.toString(), addTaskRequest.getText()));
        return taskID.toString();
    }

    @Override
    public List<Task> getAllTasks() {
        return Collections.unmodifiableList(new ArrayList<>(taskMap.values()));
    }
}
