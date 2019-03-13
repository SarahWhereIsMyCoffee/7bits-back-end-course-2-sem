package it.sevenbits.practicetwo.web.controllers;

import it.sevenbits.practicetwo.core.model.Task;
import it.sevenbits.practicetwo.core.repository.ITasksRepository;
import it.sevenbits.practicetwo.web.model.AddTaskRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TasksController {
    private final ITasksRepository tasksRepository;

    public TasksController(ITasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Task>> getAllTasks() {
        ResponseEntity.status(HttpStatus.OK);
        return ResponseEntity.ok().body(tasksRepository.getAllTasks());
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Task> create(@Valid @RequestBody AddTaskRequest addTaskRequest) {
        URI location = UriComponentsBuilder
                .fromPath("/tasks/")
                .path(String.valueOf(tasksRepository.addTask(addTaskRequest)))
                .build().toUri();

        ResponseEntity.status(HttpStatus.CREATED);
        return ResponseEntity.created(location).build();
    }
}
