package com.example.employeemanagementsystem.validators;

import com.example.employeemanagementsystem.exceptionhandlers.exceptions.EmployeeIdNotFoundException;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.UUID;

@Component
public class EmployeeIdValidator implements ConstraintValidator<ValidateId, UUID> {

    @Override
    public boolean isValid(UUID id, ConstraintValidatorContext context){
        if (id==null){
            throw new EmployeeIdNotFoundException("Id not found!");
        }
        else{
            return true;
        }
    }
}
