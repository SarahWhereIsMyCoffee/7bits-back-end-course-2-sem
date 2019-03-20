package it.sevenbits.homeworktwo.web.validation.status.service;

public class TaskStatusValidator implements ITaskStatusValidator {
    @Override
    public boolean isValidTaskID(String status) {
        String statusToDo = "ToDo";
        String statusDone = "Done";
        return status.equals(statusDone) || status.equals(statusToDo);
    }
}
