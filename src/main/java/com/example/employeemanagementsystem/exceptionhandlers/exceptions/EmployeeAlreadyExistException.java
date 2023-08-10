package com.example.employeemanagementsystem.exceptionhandlers.exceptions;

public class EmployeeAlreadyExistException extends RuntimeException{
    public EmployeeAlreadyExistException(String message){
        super(message);
    }
}
