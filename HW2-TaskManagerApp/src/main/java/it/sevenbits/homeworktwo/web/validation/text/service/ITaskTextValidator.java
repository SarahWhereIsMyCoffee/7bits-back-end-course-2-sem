package it.sevenbits.homeworktwo.web.validation.text.service;

import org.springframework.stereotype.Service;

@Service
public interface ITaskTextValidator {
    boolean isValidTaskID(String status);
}
