package it.sevenbits.homeworktwo.config;

import it.sevenbits.homeworktwo.web.validation.status.service.ITaskStatusValidator;
import it.sevenbits.homeworktwo.web.validation.status.service.TaskStatusValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaskStatusValidatorConfig {
    @Bean
    public ITaskStatusValidator taskStatusValidator() {
        return new TaskStatusValidator();
    }
}