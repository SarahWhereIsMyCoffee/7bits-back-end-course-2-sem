package it.sevenbits.practicetwo.core.repository;

import it.sevenbits.practicetwo.core.model.Task;
import it.sevenbits.practicetwo.web.model.AddTaskRequest;

import java.util.List;

public interface ITasksRepository {
    String addTask(AddTaskRequest addTaskRequest);
    List<Task> getAllTasks();
}
