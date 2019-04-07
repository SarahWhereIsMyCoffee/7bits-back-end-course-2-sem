package it.sevenbits.homeworktwo.core.repository;

import it.sevenbits.homeworktwo.core.model.Task;
import it.sevenbits.homeworktwo.web.model.AddTaskRequest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Task ITasksRepository implementation.
 */
public class HashMapTasksRepository implements ITasksRepository {
    private Map<String, Task> taskMap;

    /**
     * Constructor of HashMapTasksRepository class.
     */
    public HashMapTasksRepository() {
        taskMap = new HashMap<>();
    }

    /**
     * This method provides a new "Task" model for the adding a new object to repository.
     *
     * @param addTaskRequest "Task" model
     * @return new "Task" model id.
     */
    @Override
    public String addTask(final AddTaskRequest addTaskRequest) {
        UUID taskID = UUID.randomUUID();
        String taskStatus = "inbox";
        taskMap.put(taskID.toString(),
                new Task(taskID.toString(),
                        addTaskRequest.getText(),
                        taskStatus));
        return taskID.toString();
    }

    /**
     * This method returns all the objects from "Task" repository.
     * @return "Task" list.
     */
    @Override
    public List<Task> getAllTasks() {
        String inboxStatus = "inbox";

        return Collections.unmodifiableList(new ArrayList<>(taskMap
                .values()
                .stream()
                .filter(task -> task.getStatus().equals(inboxStatus))
        .collect(Collectors.toList())));
    }

    /**
     * This method returns a "Task" model from repository taken by ID.
     *
     * @param id String parameter.
     * @return "Task" model.
     */
    @Override
    public Task getTask(final String id) {
        return taskMap.get(id);
    }

    /**
     * This method removes a "Task" model from repository by ID.
     *
     * @param id String parameter
     * @return deleted "Task" model
     */
    @Override
    public Task deleteTask(final String id) {
        return taskMap.remove(id);
    }

    /**
     /**
     * This method replaces a "Task" model from repository by ID.
     *
     * @param id String parameter for define a "Task" model we wanna to replace.
     * @param newTask new "Task" model
     * @return deleted "Task" model.
     */
    @Override
    public Task replaceTask(final String id, final Task newTask) {
        return taskMap.replace(id, newTask);
    }
}
