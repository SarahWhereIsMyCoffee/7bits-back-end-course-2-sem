package it.sevenbits.homeworktwo.web.validation.text.constraint;

import it.sevenbits.homeworktwo.web.validation.text.service.ITaskTextValidator;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TaskTextConstraintValidator implements ConstraintValidator<ITaskTextConstraint, String> {
    private final ITaskTextValidator taskIDValidator;

    @Autowired
    public TaskTextConstraintValidator(ITaskTextValidator taskIDValidator) {
        this.taskIDValidator = taskIDValidator;
    }

    @Override
    public boolean isValid(String id, ConstraintValidatorContext constraintValidatorContext) {
        return taskIDValidator.isValidTaskID(id);
    }
}
