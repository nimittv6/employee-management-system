package com.example.employeemanagementsystem.validators;

import com.example.employeemanagementsystem.exceptionhandlers.exceptions.EmployeeTypeException;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

@Component
public class EmployeeTypeValidator implements ConstraintValidator<ValidateEmployeeType, String> {

    @Override
    public boolean isValid(String employeeType, ConstraintValidatorContext context){
        List<String> empTypes = Arrays.asList("Permanent", "Contractor", "Intern");
        if (empTypes.contains(employeeType)== false){
            throw new EmployeeTypeException("Invalid Employee Type!");
        }
        else{
            return empTypes.contains(employeeType);
        }
    }
}
