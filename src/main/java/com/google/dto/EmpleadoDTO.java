package com.google.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpleadoDTO {

    private String name;
    private String LastName;
    private String dateOfBirth;
}
