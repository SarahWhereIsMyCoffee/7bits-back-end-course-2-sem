package it.sevenbits.homeworktwo.web.validation.id.constraint;

import it.sevenbits.homeworktwo.web.validation.id.service.ITaskIDValidator;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TaskIDConstraintValidator implements ConstraintValidator<ITaskIDConstraint, String> {
    private final ITaskIDValidator taskIDValidator;

    @Autowired
    public TaskIDConstraintValidator(ITaskIDValidator taskIDValidator) {
        this.taskIDValidator = taskIDValidator;
    }

    @Override
    public boolean isValid(String id, ConstraintValidatorContext constraintValidatorContext) {
        return taskIDValidator.isValidTaskID(id);
    }
}
