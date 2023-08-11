package com.example.employeemanagementsystem.controller;

import com.example.employeemanagementsystem.domains.Address;
import com.example.employeemanagementsystem.domains.Employee;
import com.example.employeemanagementsystem.dto.EmployeeDto;
import com.example.employeemanagementsystem.mapper.EmployeeMapper;
import com.example.employeemanagementsystem.repos.EmployeeRepository;
import com.example.employeemanagementsystem.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@AllArgsConstructor
public class HomeController {

    @Autowired
    EmployeeService employeeService;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    EmployeeMapper employeeMapper;

    @GetMapping("/homeEmp")
    public String getAllEmployeeOnDirectory(Model model){
        List<EmployeeDto>employeeDtoList = employeeService.getAllEmployee();
        model.addAttribute("employeeList", employeeDtoList);
        return "home";
    }

    @GetMapping("/form")
    public String showFormForAdd(Employee employee){
        return "employee-form";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee")EmployeeDto employeeDto){
        employeeService.saveEmployee(employeeDto);
        return "redirect:/homeEmp";
    }

    @PutMapping("/update")
    public String updateEmployee(@PathVariable("id")UUID id, @ModelAttribute("employee") EmployeeDto employeeDto, BindingResult result){
        Employee employee = employeeRepository.findById(id).get();
        employee.setId(employeeDto.getId());
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setJoiningDate(employeeDto.getJoiningDate());
        employee.setEmail(employeeDto.getEmail());
        employee.setEmployeeType(employeeDto.getEmployeeType());
        Address address = employee.getAddress();
        address.setCity(employeeDto.getAddress().getCity());
        address.setStreet(employeeDto.getAddress().getStreet());
        address.setPinCode(employeeDto.getAddress().getPinCode());
        address.setFlatName(employeeDto.getAddress().getFlatName());
        address.setAptNum(employeeDto.getAddress().getAptNum());
        employeeRepository.save(employee);
        return "redirect:/home";
    }
}
