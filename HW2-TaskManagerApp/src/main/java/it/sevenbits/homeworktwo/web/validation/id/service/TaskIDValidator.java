package it.sevenbits.homeworktwo.web.validation.id.service;

import java.util.regex.Pattern;

public class TaskIDValidator implements ITaskIDValidator{
    @Override
    public boolean isValidTaskID(String id) {
        String reg = "([a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12})";
        return Pattern.matches(reg, id);
    }
}
