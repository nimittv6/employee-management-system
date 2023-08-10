package com.example.employeemanagementsystem.dto;

import com.example.employeemanagementsystem.beanValidators.validationGroups.OrderOnePost;
import com.example.employeemanagementsystem.domains.Address;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    @Id
    @Null(groups = OrderOnePost.class)
    UUID id;
    @NotNull
    String firstName;

    @NotNull
    String lastName;

    @Email(groups = OrderOnePost.class,message = "Invalid Email", regexp = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    String email;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull
    Date joiningDate;

    @OneToOne(cascade = CascadeType.ALL)
    Address address;

    @NotNull
    String employeeType;

    @Valid
    AddressDto addressDto;
    UUID departmentId;
}

