package com.example.demo.dtos;

import java.math.BigDecimal;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MovimientoRequest {
    @NotNull
    BigDecimal valor;
    @NotNull
    Long cuentaId;

    @AssertTrue(message = "Monto de movimiento debe ser distinto de 0")
    public boolean isDifferentThan0() {
        return !this.valor.equals(BigDecimal.ZERO);
    }
}
