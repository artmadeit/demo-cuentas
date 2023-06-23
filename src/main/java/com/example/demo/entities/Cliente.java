package com.example.demo.entities;

import org.hibernate.annotations.SQLDelete;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@SQLDelete(sql = "UPDATE persona SET is_deleted = true WHERE id=?")
public class Cliente extends Persona {
    @NotBlank
    @Setter
    @JsonIgnore
    String password;
}
