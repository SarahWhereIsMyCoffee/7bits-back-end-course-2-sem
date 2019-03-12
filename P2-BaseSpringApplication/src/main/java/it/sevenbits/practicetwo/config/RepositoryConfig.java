package it.sevenbits.practicetwo.config;

import it.sevenbits.practicetwo.core.repository.ITasksRepository;
import it.sevenbits.practicetwo.core.repository.TasksRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {
    @Bean
    public ITasksRepository tasksRepository() {
        return new TasksRepository();
    }
}
