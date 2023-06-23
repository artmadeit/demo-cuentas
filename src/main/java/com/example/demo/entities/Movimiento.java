package com.example.demo.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Entity
@Getter
@EntityListeners(value = AuditingEntityListener.class)
public class Movimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Enumerated(EnumType.STRING)
    @NotNull
    TipoMovimiento tipo;
    @NotNull
    BigDecimal saldo;
    @NotNull
    BigDecimal valor;
    @CreatedDate
    LocalDateTime fecha;
    @ManyToOne
    @NotNull
    Cuenta cuenta;

    public Movimiento() {
        // jpa only
    }

    public Movimiento(BigDecimal valor, Cuenta cuenta, BigDecimal ultimoSaldo) {
        this.valor = valor;
        this.cuenta = cuenta;
        this.tipo = TipoMovimiento.get(valor);
        this.saldo = ultimoSaldo.add(valor);
    }

}
