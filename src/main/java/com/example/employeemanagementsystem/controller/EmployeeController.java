package com.example.employeemanagementsystem.controller;

import com.example.employeemanagementsystem.beanValidators.validationGroups.OrderOnePost;
import com.example.employeemanagementsystem.beanValidators.validationGroups.OrderOnePut;
import com.example.employeemanagementsystem.domains.Employee;
import com.example.employeemanagementsystem.dto.DepartmentDto;
import com.example.employeemanagementsystem.dto.EmployeeDto;
import com.example.employeemanagementsystem.mapper.DepartmentMapper;
import com.example.employeemanagementsystem.mapper.EmployeeMapper;
import com.example.employeemanagementsystem.repos.DepartmentRepository;
import com.example.employeemanagementsystem.repos.EmployeeRepository;
import com.example.employeemanagementsystem.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Validated
@RestController
@AllArgsConstructor
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    DepartmentMapper departmentMapper;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    DepartmentRepository departmentRepository;


    @PostMapping("/employee")
    public ResponseEntity<Object> saveEmployee(@RequestBody @Validated(OrderOnePost.class) EmployeeDto employeeDto){
        return new ResponseEntity<>(employeeService.saveEmployee(employeeDto), HttpStatus.OK);
    }

    @PostMapping("/validateBody")
    ResponseEntity<String> validateBody(@Valid @RequestBody Employee employee){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/employee")
    public ResponseEntity<Object> getAllEmployee(){
        return new ResponseEntity<>(employeeService.getAllEmployee(), HttpStatus.OK);
    }

    @GetMapping("/employee/paging")
    public ResponseEntity<Object> getAllEmployeeByPaging(@RequestParam(value = "offset", required = false) Integer offset,
                                                         @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                         @RequestParam(value = "sortBy", required = false) String field){
        return new ResponseEntity<>(employeeService.getAllEmployeeByPaging(offset, pageSize, field), HttpStatus.OK);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<Object> getEmployeeById(@PathVariable UUID id){
        return new ResponseEntity<>(employeeService.getEmployeeById(id), HttpStatus.OK);
    }

    @GetMapping("/employee/firstName")
    public ResponseEntity<Object> getEmployeeByFirstName(@RequestParam String firstName){
        return new ResponseEntity<>(employeeService.getEmployeeByFirstName(firstName), HttpStatus.OK);
    }

    @GetMapping("/employee/lastName")
    public ResponseEntity<Object> getEmployeeByLastName(@RequestParam String lastName){
        return new ResponseEntity<>(employeeService.getEmployeeByLastName(lastName), HttpStatus.OK);
    }

    @DeleteMapping("/employee")
    public ResponseEntity<Object> deleteAllEmployee(){
        employeeService.deleteAllEmployee();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<Object> deleteEmployeeById(@PathVariable UUID id){
        employeeService.deleteEmployeeById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/employee/joiningYear")
    public ResponseEntity<Object> deleteAllByJoiningYear(@RequestParam String joiningYear){
        employeeService.deleteAllByJoiningYear(joiningYear);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/employee")
    public ResponseEntity<Object> editEmployee(@RequestBody @Validated(OrderOnePut.class) EmployeeDto updatedEmployee){
        employeeService.editEmployee(updatedEmployee);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/department")
    public ResponseEntity<Object> saveDepartment(@RequestBody DepartmentDto departmentDto){
        return new ResponseEntity<>(employeeService.saveDepartment(departmentDto), HttpStatus.OK);
    }

    @GetMapping("/department")
    public ResponseEntity<Object> getAllDepartments(){
        return new ResponseEntity<>(employeeService.getAllDepartments(), HttpStatus.OK);
    }
}
