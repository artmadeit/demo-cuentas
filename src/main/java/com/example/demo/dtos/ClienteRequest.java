package com.example.demo.dtos;

import com.example.demo.entities.Persona.Genero;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ClienteRequest {
    @NotBlank
    String nombres;
    Genero genero;
    Integer edad;
    String numeroIdentificacion;
    @NotBlank
    String direccion;
    @NotBlank
    @Digits(integer = 9, fraction = 0)
    String telefono;
    String password;
}
