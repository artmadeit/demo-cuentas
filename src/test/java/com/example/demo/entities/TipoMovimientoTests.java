package com.example.demo.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class TipoMovimientoTests {
    @Test
    public void should_be_debito_on_positive() {
        assertEquals(TipoMovimiento.DEBITO, TipoMovimiento.get(new BigDecimal(200)));
        assertEquals(TipoMovimiento.DEBITO, TipoMovimiento.get(new BigDecimal(20.5)));
    }

    @Test
    public void should_be_credito_on_positive() {
        assertEquals(TipoMovimiento.CREDITO, TipoMovimiento.get(new BigDecimal(-200)));
        assertEquals(TipoMovimiento.CREDITO, TipoMovimiento.get(new BigDecimal(-20.5)));
    }

    @Test
    public void should_throw_exception_on_0() {
        assertThrows(MontoInvalidoException.class, () -> TipoMovimiento.get(new BigDecimal(0)));
        assertThrows(MontoInvalidoException.class, () -> TipoMovimiento.get(new BigDecimal(0.0)));
    }
}
