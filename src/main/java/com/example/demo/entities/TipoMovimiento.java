package com.example.demo.entities;

import java.math.BigDecimal;

public enum TipoMovimiento {
    CREDITO, DEBITO;

    public static TipoMovimiento get(BigDecimal valor) {
        if (valor.compareTo(BigDecimal.ZERO) > 0)
            return TipoMovimiento.DEBITO;
        if (valor.compareTo(BigDecimal.ZERO) < 0)
            return TipoMovimiento.CREDITO;

        throw new MontoInvalidoException();
    }
}