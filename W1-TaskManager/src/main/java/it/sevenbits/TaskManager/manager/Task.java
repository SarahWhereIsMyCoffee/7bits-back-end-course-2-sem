package it.sevenbits.TaskManager.manager;

import java.util.UUID;

public class Task {
    public UUID getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    private UUID id;
    private String message;

    public Task(final UUID id, final String message) {
        this.id = id;
        this.message = message;
    }
}
