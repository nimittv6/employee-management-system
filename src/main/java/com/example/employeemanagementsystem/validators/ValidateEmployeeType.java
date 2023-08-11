package com.example.employeemanagementsystem.validators;

import org.springframework.stereotype.Component;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
@Constraint(validatedBy = EmployeeTypeValidator.class)
public @interface ValidateEmployeeType {
    String message() default "Invalid Employee Type: It should be Permanent, Contractor or Intern";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
