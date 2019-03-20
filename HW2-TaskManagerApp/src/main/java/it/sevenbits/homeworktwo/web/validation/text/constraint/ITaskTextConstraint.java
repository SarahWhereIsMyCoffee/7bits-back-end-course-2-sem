package it.sevenbits.homeworktwo.web.validation.text.constraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy= TaskTextConstraintValidator.class)
public @interface ITaskTextConstraint {
    String message() default "{value.negative}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}