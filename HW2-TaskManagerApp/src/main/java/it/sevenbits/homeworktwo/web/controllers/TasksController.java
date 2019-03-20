package it.sevenbits.homeworktwo.web.controllers;

import it.sevenbits.homeworktwo.core.model.Task;
import it.sevenbits.homeworktwo.core.repository.ITasksRepository;
import it.sevenbits.homeworktwo.web.exceptions.InvalidTaskStatusException;
import it.sevenbits.homeworktwo.web.exceptions.InvalidTaskTextException;
import it.sevenbits.homeworktwo.web.exceptions.TaskNotFoundException;
import it.sevenbits.homeworktwo.web.exceptions.InvalidTaskIDException;
import it.sevenbits.homeworktwo.web.model.AddTaskRequest;
import it.sevenbits.homeworktwo.web.model.UpdateTaskRequest;
import it.sevenbits.homeworktwo.web.validation.id.service.ITaskIDValidator;
import it.sevenbits.homeworktwo.web.validation.status.service.ITaskStatusValidator;
import it.sevenbits.homeworktwo.web.validation.text.service.ITaskTextValidator;

import org.springframework.http.HttpStatus;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/tasks")
public class TasksController {
    private final ITasksRepository tasksRepository;
    private final ITaskIDValidator taskIDValidator;
    private final ITaskStatusValidator taskStatusValidator;
    private final ITaskTextValidator taskTextValidator;


    public TasksController(ITasksRepository tasksRepository,
                           ITaskIDValidator taskIDValidator,
                           ITaskStatusValidator taskStatusValidator,
                           ITaskTextValidator taskTextValidator) {
        this.tasksRepository = tasksRepository;
        this.taskIDValidator = taskIDValidator;
        this.taskStatusValidator = taskStatusValidator;
        this.taskTextValidator = taskTextValidator;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Task>> getAllTasks() {
        ResponseEntity.status(HttpStatus.OK);
        return ResponseEntity
                .ok()
                .body(tasksRepository.getAllTasks());
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Task> create(@Valid @RequestBody AddTaskRequest addTaskRequest) {
        URI location = UriComponentsBuilder
                .fromPath("/tasks/")
                .path(String.valueOf(tasksRepository.addTask(addTaskRequest)))
                .build()
                .toUri();

        ResponseEntity.status(HttpStatus.CREATED);
        return ResponseEntity
                .created(location)
                .build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Task> getTask(@PathVariable("id") String id) {
        if (!taskIDValidator.isValidTaskID(id)) {
            throw new InvalidTaskIDException();
        }

        Task currentTask = tasksRepository.getTask(id);
        if (currentTask == null) {
            throw new TaskNotFoundException();
        }

        return ResponseEntity
                .ok()
                .body(currentTask);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteTask(@PathVariable("id") String id) {
        if (!taskIDValidator.isValidTaskID(id)) {
            throw new InvalidTaskIDException();
        }

        Task currentTask = tasksRepository.getTask(id);
        if (currentTask == null) {
            throw new TaskNotFoundException();
        }

        tasksRepository.deleteTask(id);
    }

    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Task> patchTask(@PathVariable("id") String id,
                                          @Valid @RequestBody UpdateTaskRequest updateTaskRequest) {
        if (!taskIDValidator.isValidTaskID(id)) {
            throw new InvalidTaskIDException();
        }

        if (!taskStatusValidator.isValidTaskID(updateTaskRequest.getStatus())) {
            throw new InvalidTaskIDException();
        }

        if (!taskTextValidator.isValidTaskID(updateTaskRequest.getText())) {
            throw new InvalidTaskIDException();
        }


        Task currentTask = new Task(
                id,
                Optional.ofNullable(updateTaskRequest.getText())
                        .orElseThrow(InvalidTaskTextException::new),
                Optional.ofNullable(updateTaskRequest.getStatus())
                        .orElseThrow(InvalidTaskStatusException::new)
        );

        return ResponseEntity.
                ok().
                body(tasksRepository.replaceTask(id, currentTask));
    }
}
