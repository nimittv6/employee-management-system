package com.example.employeemanagementsystem.mapper;

import com.example.employeemanagementsystem.domains.Department;
import com.example.employeemanagementsystem.domains.Employee;
import com.example.employeemanagementsystem.dto.DepartmentDto;
import com.example.employeemanagementsystem.dto.EmployeeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Mapper(componentModel = "spring", imports = UUID.class)
public interface DepartmentMapper {
    DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);
    DepartmentDto departmentToDepartmentDto(Department department);

    @Mapping(target = "id", expression = "java(UUID.randomUUID())")
    @Mapping(target = "address.id", expression = "java(UUID.randomUUID())")
    Department departmentDtoToDepartment(DepartmentDto departmentDto);
}
