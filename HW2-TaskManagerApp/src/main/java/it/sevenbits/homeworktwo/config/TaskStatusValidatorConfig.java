package it.sevenbits.homeworktwo.config;

import it.sevenbits.homeworktwo.core.validation.status.service.ITaskStatusValidator;
import it.sevenbits.homeworktwo.core.validation.status.service.TaskStatusValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring configuration file for TaskStatusValidator service.
 */
@Configuration
public class TaskStatusValidatorConfig {
    /**
     * This
     * @return ITaskStatusValidator instance that presents task status validator service.
     */
    @Bean
    public ITaskStatusValidator taskStatusValidator() {
        return new TaskStatusValidator();
    }
}