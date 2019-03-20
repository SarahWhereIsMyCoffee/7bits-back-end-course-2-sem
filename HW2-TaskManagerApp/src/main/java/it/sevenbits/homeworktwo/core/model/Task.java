package it.sevenbits.homeworktwo.core.model;

public class Task {
    private final String id;
    private final String text;
    private final String status;

    public Task(String id, String text, String status) {
        this.id = id;
        this.text = text;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getStatus() {
        return status;
    }
}
