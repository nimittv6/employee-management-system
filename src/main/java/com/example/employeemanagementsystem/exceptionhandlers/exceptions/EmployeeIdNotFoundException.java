package com.example.employeemanagementsystem.exceptionhandlers.exceptions;

public class EmployeeIdNotFoundException extends RuntimeException{
    public EmployeeIdNotFoundException(String message){
        super(message);
    }
}
