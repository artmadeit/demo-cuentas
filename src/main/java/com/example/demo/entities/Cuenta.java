package com.example.demo.entities;

import java.math.BigDecimal;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@SQLDelete(sql = "UPDATE cuenta SET is_deleted = true WHERE id=?")
@Where(clause = "is_deleted=false")
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull
    String numero;

    @NotNull
    @Enumerated(EnumType.STRING)
    TipoCuenta tipo;

    @NotNull
    BigDecimal saldoInicial;

    @NotNull
    @ManyToOne
    Cliente cliente;

    Boolean isDeleted = false;

    Cuenta() {
        // jpa only
    }

    @Builder
    public Cuenta(String numero, TipoCuenta tipo, BigDecimal saldoInicial, Cliente cliente) {
        this.numero = numero;
        this.tipo = tipo;
        this.saldoInicial = saldoInicial;
        this.cliente = cliente;
    }

    public enum TipoCuenta {
        AHORRO, CORRIENTE
    }
}
