package it.sevenbits.homeworktwo.web.validation.id.service;

import org.springframework.stereotype.Service;

@Service
public interface ITaskIDValidator {
    boolean isValidTaskID(String id);
}
