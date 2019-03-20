package it.sevenbits.homeworktwo.web.validation.status.constraint;

import it.sevenbits.homeworktwo.web.validation.status.service.ITaskStatusValidator;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TaskStatusConstraintValidator implements ConstraintValidator<ITaskStatusConstraint, String> {
    private final ITaskStatusValidator taskIDValidator;

    @Autowired
    public TaskStatusConstraintValidator(ITaskStatusValidator taskIDValidator) {
        this.taskIDValidator = taskIDValidator;
    }

    @Override
    public boolean isValid(String id, ConstraintValidatorContext constraintValidatorContext) {
        return taskIDValidator.isValidTaskID(id);
    }
}
