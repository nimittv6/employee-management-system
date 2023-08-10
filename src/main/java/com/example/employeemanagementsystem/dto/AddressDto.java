package com.example.employeemanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {

    int aptNum;

    String flatName;

    String street;

    String city;

    int pinCode;
}
