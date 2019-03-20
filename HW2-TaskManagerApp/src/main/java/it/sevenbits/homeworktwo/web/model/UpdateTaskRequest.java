package it.sevenbits.homeworktwo.web.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.sevenbits.homeworktwo.web.validation.status.constraint.ITaskStatusConstraint;

import javax.validation.constraints.NotBlank;

public class UpdateTaskRequest {
    @NotBlank
    private String text;
    @ITaskStatusConstraint
    @NotBlank
    private String status;

    @JsonCreator
    public UpdateTaskRequest(@JsonProperty("text") String text,
                             @JsonProperty("status") String status) {
        this.text = text;
        this.status = status;
    }

    public String getText() {
        return text;
    }

    public String getStatus() {
        return status;
    }
}
