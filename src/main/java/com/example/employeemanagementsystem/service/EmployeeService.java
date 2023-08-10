package com.example.employeemanagementsystem.service;

import com.example.employeemanagementsystem.domains.Address;
import com.example.employeemanagementsystem.domains.Department;
import com.example.employeemanagementsystem.domains.Employee;
import com.example.employeemanagementsystem.dto.DepartmentDto;
import com.example.employeemanagementsystem.dto.EmployeeDto;
import com.example.employeemanagementsystem.exceptionhandlers.exceptions.EmployeeAlreadyExistException;
import com.example.employeemanagementsystem.exceptionhandlers.exceptions.EmployeeNotFoundException;
import com.example.employeemanagementsystem.mapper.DepartmentMapper;
import com.example.employeemanagementsystem.mapper.EmployeeMapper;
import com.example.employeemanagementsystem.repos.DepartmentRepository;
import com.example.employeemanagementsystem.repos.EmployeeRepository;
import jdk.dynalink.linker.LinkerServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    DepartmentMapper departmentMapper;

    public EmployeeDto saveEmployee(EmployeeDto employeeDto){
        Employee employee = employeeMapper.employeeDtoToEmployee(employeeDto);
        Iterable<Employee> emp = employeeRepository.findAll();
        for (Employee tempEmp: emp){
            if (tempEmp.getFirstName().equals(employeeDto.getFirstName()) &&
                    tempEmp.getLastName().equals(employeeDto.getLastName())){
                throw new EmployeeAlreadyExistException("Employee Already Exist!");
            }
        }
        Employee savedEmployee = employeeRepository.save(employee);
        return employeeMapper.employeeToEmployeeDto(savedEmployee);
    }

    public List<EmployeeDto> getAllEmployee(){
        Iterable<Employee> savedEmployee = employeeRepository.findAll();
        return ((List<Employee>) savedEmployee)
                .stream()
                .map(employee -> employeeMapper.employeeToEmployeeDto(employee))
                .collect(Collectors.toList());
    }

    public Page<EmployeeDto> getAllEmployeeByPaging(int offset, int pageSize, String field){
        Page<Employee> emp = employeeRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
        Page<EmployeeDto> employeeDtoPage = emp.map(employee -> employeeMapper.employeeToEmployeeDto(employee));
        return employeeDtoPage;
    }

    public Optional<EmployeeDto> getEmployeeById(UUID id){
        Optional<Employee> savedEmployee = employeeRepository.findById(id);
        if(!savedEmployee.isPresent()){
            throw new EmployeeNotFoundException("No employee found with this id: ");
        }
        else{
            return Optional.of(employeeMapper.employeeToEmployeeDto(savedEmployee.get()));
        }
    }

    public Optional<EmployeeDto> getEmployeeByFirstName(String firstName){
        Optional<Employee> employeeFirstName = employeeRepository.findByFirstName(firstName);
        if (!employeeFirstName.isPresent()){
            throw new EmployeeNotFoundException("Employee not found with this first name");
        }
        else{
            return Optional.of(employeeMapper.employeeToEmployeeDto(employeeFirstName.get()));
        }
    }

    public Optional<EmployeeDto> getEmployeeByLastName(String lastName){
        Optional<Employee> employeeLastName = employeeRepository.findByFirstName(lastName);
        if (!employeeLastName.isPresent()){
            throw new EmployeeNotFoundException("Employee not found with this last name");
        }
        else{
            return Optional.of(employeeMapper.employeeToEmployeeDto(employeeLastName.get()));
        }
    }

    public void deleteAllEmployee(){
        employeeRepository.deleteAll();
    }

    public void deleteEmployeeById(UUID id){
        Optional<Employee> employeeSaved = employeeRepository.findById(id);
        if (!employeeSaved.isPresent()){
            throw new EmployeeNotFoundException("Database is already empty");
        }
        else{
            employeeRepository.deleteById(id);
        }
    }

    public void deleteAllByJoiningYear(String joiningYear){
        employeeRepository.deleteByJoiningYear(joiningYear);
    }

    public EmployeeDto editEmployee(EmployeeDto updatedEmployee){
        Employee employee = employeeRepository.findById(updatedEmployee.getId())
                .orElseThrow(() -> new EmployeeNotFoundException("Employee does not exist with this id" + updatedEmployee.getId()));
        if (!employee.getEmail().equalsIgnoreCase(updatedEmployee.getEmail())){
            throw new EmployeeNotFoundException("Email edit is not allowed");
        }
        employee.setFirstName(updatedEmployee.getFirstName());
        employee.setLastName(updatedEmployee.getLastName());

        employee.setEmployeeType(updatedEmployee.getEmployeeType());
        employee.setJoiningDate(updatedEmployee.getJoiningDate());
        Address address = employee.getAddress();
        address.setCity(updatedEmployee.getAddress().getCity());
        address.setStreet(updatedEmployee.getAddress().getStreet());
        address.setPinCode(updatedEmployee.getAddress().getPinCode());
        address.setAptNum(updatedEmployee.getAddress().getAptNum());
        address.setFlatName(updatedEmployee.getAddress().getFlatName());

        Employee savedEmployee = employeeRepository.save(employee);
        return employeeMapper.employeeToEmployeeDto(savedEmployee);
    }

    public DepartmentDto saveDepartment(DepartmentDto departmentDto){
        Department department = departmentMapper.departmentDtoToDepartment(departmentDto);
        Department savedDepartment = departmentRepository.save(department);
        return departmentMapper.departmentToDepartmentDto(savedDepartment);
    }

    public List<DepartmentDto> getAllDepartments(){
        Iterable<Department> savedDepartment = departmentRepository.findAll();
        return ((List<Department>) savedDepartment)
                .stream()
                .map(department -> departmentMapper.departmentToDepartmentDto(department))
                .collect(Collectors.toList());
    }
}
