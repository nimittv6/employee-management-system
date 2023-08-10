package com.example.employeemanagementsystem.domains;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Department {

    @Id
    @Type(type = "uuid-char")
    UUID id;

    @Column(name = "Department Name")
    String departmentName;

    @Column(name = "description")
    String description;
}
