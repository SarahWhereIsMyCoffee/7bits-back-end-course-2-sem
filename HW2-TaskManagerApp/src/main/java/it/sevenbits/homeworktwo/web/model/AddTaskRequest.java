package it.sevenbits.homeworktwo.web.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.sevenbits.homeworktwo.core.validation.text.constraint.ITaskTextConstraint;

import javax.validation.constraints.NotBlank;

/**
 * This model separates query logic of adding new task.
 * from application business logic.
 */
public class AddTaskRequest {
    /**
     *
     */
    @NotBlank
    @ITaskTextConstraint
    private String text;



    /**
     * Constructor of the model.
     * Creates a JSON object.
     *
     * @param text String Json property.
     */
    @JsonCreator
    public AddTaskRequest(@JsonProperty("text") final String text) {
        this.text = text;
    }

    /**
     * Getter for the text field.
     *
     * @return String text of the model.
     */
    public final String getText() {
        return text;
    }
}
