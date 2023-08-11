package com.example.employeemanagementsystem.validators;

import org.springframework.stereotype.Component;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
@Constraint(validatedBy = EmployeeIdValidator.class)
public @interface ValidateId {

    String message() default "Invalid Employee Id!";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
