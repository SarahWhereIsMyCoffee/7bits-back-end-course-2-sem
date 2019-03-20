package it.sevenbits.homeworktwo.config;

import it.sevenbits.homeworktwo.core.repository.ITasksRepository;
import it.sevenbits.homeworktwo.core.repository.TasksRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {
    @Bean
    public ITasksRepository tasksRepository() {
        return new TasksRepository();
    }
}