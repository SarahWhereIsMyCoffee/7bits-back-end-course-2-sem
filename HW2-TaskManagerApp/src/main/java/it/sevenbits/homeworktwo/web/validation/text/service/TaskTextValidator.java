package it.sevenbits.homeworktwo.web.validation.text.service;

public class TaskTextValidator implements ITaskTextValidator {
    @Override
    public boolean isValidTaskID(String status) {

        return status != null;
    }
}
