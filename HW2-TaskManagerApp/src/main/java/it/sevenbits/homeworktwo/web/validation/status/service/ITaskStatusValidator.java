package it.sevenbits.homeworktwo.web.validation.status.service;

import org.springframework.stereotype.Service;

@Service
public interface ITaskStatusValidator {
    boolean isValidTaskID(String status);
}
