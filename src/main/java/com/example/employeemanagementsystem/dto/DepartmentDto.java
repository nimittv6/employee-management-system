package com.example.employeemanagementsystem.dto;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class DepartmentDto {
    @Id
    UUID id;
    @NotNull
    String departmentName;
    @NotNull
    String description;
    List<UUID> employeeList;

}
