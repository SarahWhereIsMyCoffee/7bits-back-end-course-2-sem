package it.sevenbits.homeworktwo.core.repository;

import it.sevenbits.homeworktwo.core.model.Task;
import it.sevenbits.homeworktwo.web.model.AddTaskRequest;

import java.util.List;

public interface ITasksRepository {
    String addTask(AddTaskRequest addTaskRequest);
    List<Task> getAllTasks();
    Task getTask(String id);
    Task deleteTask(String id);
    Task replaceTask(String id, Task newTask);
}
