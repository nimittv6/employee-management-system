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
public class Address {
    @Id
    @Type(type = "uuid-char")
    UUID id;

    @Column(name = "apt Num")
    int aptNum;

    @Column(name = "Flat Name")
    String flatName;

    @Column(name = "Street")
    String street;

    @Column(name = "City")
    String city;

    @Column(name = "PinCode")
    int pinCode;
}
