package it.sevenbits.homeworktwo.config;

import it.sevenbits.homeworktwo.web.validation.text.service.ITaskTextValidator;
import it.sevenbits.homeworktwo.web.validation.text.service.TaskTextValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaskTextValidatorConfig {
    @Bean
    public ITaskTextValidator taskTextValidator() {
        return new TaskTextValidator();
    }
}