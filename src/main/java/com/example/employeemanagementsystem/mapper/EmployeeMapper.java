package com.example.employeemanagementsystem.mapper;

import com.example.employeemanagementsystem.domains.Employee;
import com.example.employeemanagementsystem.dto.EmployeeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Mapper(componentModel = "spring", imports = UUID.class)
public interface EmployeeMapper {
    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);
    EmployeeDto employeeToEmployeeDto(Employee employee);

    @Mapping(target = "id", expression = "java(UUID.randomUUID())")
    @Mapping(target = "address.id", expression = "java(UUID.randomUUID())")
    Employee employeeDtoToEmployee(EmployeeDto employeeDto);
}
