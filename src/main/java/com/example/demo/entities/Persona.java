package com.example.demo.entities;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@SQLDelete(sql = "UPDATE persona SET is_deleted = true WHERE id=?")
@Where(clause = "is_deleted=false")
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank
    @Setter
    String nombres;

    @Setter
    Genero genero;

    @Setter
    Integer edad; // TODO: Pienso que seria mejor guardar la fecha de nacimiento

    @Setter
    String numeroIdentificacion;

    @NotBlank
    @Setter
    String direccion;

    @NotBlank
    @Digits(integer = 9, fraction = 0)
    @Setter
    String telefono;

    Boolean isDeleted = false;

    public enum Genero {
        // Agregar otros si es necesario
        MASCULINO, FEMENINO
    }
}
