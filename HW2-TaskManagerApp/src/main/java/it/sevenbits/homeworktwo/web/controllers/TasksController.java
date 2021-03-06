package it.sevenbits.homeworktwo.web.controllers;

import it.sevenbits.homeworktwo.core.model.Task;
import it.sevenbits.homeworktwo.core.repository.ITasksRepository;
import it.sevenbits.homeworktwo.web.exceptions.InvalidTaskStatusException;
import it.sevenbits.homeworktwo.web.exceptions.InvalidTaskTextException;
import it.sevenbits.homeworktwo.web.exceptions.TaskNotFoundException;
import it.sevenbits.homeworktwo.web.exceptions.InvalidTaskIDException;
import it.sevenbits.homeworktwo.web.model.AddTaskRequest;
import it.sevenbits.homeworktwo.web.model.UpdateTaskRequest;
import it.sevenbits.homeworktwo.core.validation.id.service.ITaskIDValidator;
import it.sevenbits.homeworktwo.core.validation.status.service.ITaskStatusValidator;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

/**
 * This class presents Spring REST @Controller controller functionality
 * using for handling different HTTP requests.
 */

@Controller
@RequestMapping("/tasks")
public class TasksController {
    private final ITasksRepository tasksRepository;
    private final ITaskIDValidator taskIDValidator;
    private final ITaskStatusValidator taskStatusValidator;

    /**
     * Class constructor.
     *
     * @param tasksRepository ITaskRepository instance.
     * @param taskIDValidator ITaskIDValidator service instance.
     * @param taskStatusValidator ITaskStatusValidator service instance.
     */
    public TasksController(final ITasksRepository tasksRepository,
                           final ITaskIDValidator taskIDValidator,
                           final ITaskStatusValidator taskStatusValidator) {
        this.tasksRepository = tasksRepository;
        this.taskIDValidator = taskIDValidator;
        this.taskStatusValidator = taskStatusValidator;
    }

    /**
     * Method that returns list of all tasks from task repository.
     * That method handles GET request to "/".

     * @return Response that contains information about:
     *                                -Request body
     *                                -Content type
     *                                -HTTP status
     */
    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Task>> getAllTasks(
            @RequestParam(value = "status", required = false) final String status
    ) {
        if (status != null && !status.equals("") && !taskStatusValidator.isValidStatus(status)) {
            throw new InvalidTaskStatusException();
        }

        String taskStatus = Optional
                .ofNullable(status)
                .orElse("inbox");

        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(tasksRepository.getAllTasks(taskStatus));
    }

    /**
     * Method that adds new task to repository.
     * That method handles POST request to "/".
     *
     * @param addTaskRequest "Add task" request model. It is required parameter.
     *                       If it is not valid, status code "400 - Bad Request" will be returned.
     * @return Response that contains all the information about the operation status,
     *      content type and location.
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<Task> create(@Valid @RequestBody final AddTaskRequest addTaskRequest) {
        URI location = UriComponentsBuilder
                .fromPath("/tasks/")
                .path(String.valueOf(tasksRepository.addTask(addTaskRequest)))
                .build()
                .toUri();

        return ResponseEntity
                .created(location)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .build();
    }

    /**
     * Method that returns task by passed task id.
     * That method handles GET request to "/tasks/{id}".
     *
     * @param id Id of task to return. It is required parameter.
     * @return Response that contains all the information
     *      about the operation status,content type and location.
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<Task> getTaskByID(@PathVariable("id") final String id) {
        if (!taskIDValidator.isValidTaskID(id)) {
            throw new InvalidTaskIDException();
        }

        Task currentTask = tasksRepository.getTask(id);
        if (currentTask == null) {
            throw new TaskNotFoundException();
        }

        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(currentTask);
    }
    /**
     *
     * Method that deletes task by passed task id.
     * That method handles DELETE request to "/tasks/{id}".
     *
     * @param id Id of task to remove. It is required parameter.
     * @return Response that contains all the information
     *      about the operation status,content type and location.
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Task> deleteTaskByID(@PathVariable("id") final String id) {
        if (!taskIDValidator.isValidTaskID(id)) {
            throw new InvalidTaskIDException();
        }

        Task currentTask = tasksRepository.getTask(id);
        if (currentTask == null) {
            throw new TaskNotFoundException();
        }
        tasksRepository.deleteTask(id);

        return ResponseEntity
                .ok()
                .build();
    }

    /**
     * Method that updates task with passed new task text and/or task status by passed task id.
     * That method handles PATCH request to "/tasks/{id}".
     *
     * @param id Id of task to update. It is required parameter.
     * @param updateTaskRequest "Update task" request model.
     *                          If it is not valid, status code "400 - Bad Request" will be returned.
     *
     * @return Response that contains all the information
     *      about the operation status,content type and location.
     */
    @PatchMapping(value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Object> patchTask(@PathVariable("id") final String id,
                                            @Valid @RequestBody final UpdateTaskRequest updateTaskRequest) {
        if (!taskIDValidator.isValidTaskID(id)) {
            throw new InvalidTaskIDException();
        }

        if (updateTaskRequest.getStatus() != null
                && !taskStatusValidator.isValidStatus(updateTaskRequest.getStatus())) {
            throw new InvalidTaskStatusException();
        }

        if (updateTaskRequest.getText() != null
                && updateTaskRequest.getText().equals("")) {
            throw new InvalidTaskTextException();
        }

        if (tasksRepository.getTask(id) == null) {
            throw new TaskNotFoundException();
        }

        tasksRepository.replaceTask(id, new Task(
                id,
                Optional.ofNullable(updateTaskRequest.getText())
                        .orElse(tasksRepository.getTask(id).getText()),
                Optional.ofNullable(updateTaskRequest.getStatus())
                        .orElse(tasksRepository.getTask(id).getStatus())
                )
        );

        return ResponseEntity
                .noContent()
                .build();
    }
}