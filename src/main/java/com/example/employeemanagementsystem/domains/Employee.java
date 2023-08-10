package com.example.employeemanagementsystem.domains;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Employee {

    @Id
    UUID id;
    @Column(name = "First Name" )
    @Size(min = 3, message = "{validation.name.size.too_short}")
    @Size(max = 15, message = "{validation.name.size.too_long}")
    String firstName;

    @Column(name = "Last Name" )
    @Size(min = 3, message = "{validation.name.size.too_short}")
    @Size(max = 15, message = "{validation.name.size.too_long}")
    String lastName;

    @Column
    String email;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "Joining Date")
    Date joiningDate;

    @OneToOne(cascade = CascadeType.ALL)
    Address address;

    @Column(name = "Employee Type")
    String employeeType;
}
