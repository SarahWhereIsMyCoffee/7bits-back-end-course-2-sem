package it.sevenbits.homeworktwo.config;

import it.sevenbits.homeworktwo.web.validation.id.service.ITaskIDValidator;
import it.sevenbits.homeworktwo.web.validation.id.service.TaskIDValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaskIDValidatorConfig {
    @Bean
    public ITaskIDValidator taskIDValidator() {
        return new TaskIDValidator();
    }
}