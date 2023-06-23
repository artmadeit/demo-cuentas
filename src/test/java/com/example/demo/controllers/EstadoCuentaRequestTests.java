package com.example.demo.controllers;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class EstadoCuentaRequestTests {

    @Test
    void isFechaInicioBeforeOrEqualsFechaFin() {
        var request = new ReporteController.EstadoCuentaRequest();
        request.fechaInicio = LocalDate.of(2023, 6, 22);
        request.fechaFin = LocalDate.of(2023, 6, 22);
        assertTrue(request.isFechaInicioBeforeOrEqualsFechaFin());

        request = new ReporteController.EstadoCuentaRequest();
        request.fechaInicio = LocalDate.of(2023, 6, 22);
        request.fechaFin = LocalDate.of(2023, 6, 23);
        assertTrue(request.isFechaInicioBeforeOrEqualsFechaFin());

        request = new ReporteController.EstadoCuentaRequest();
        request.fechaInicio = LocalDate.of(2023, 6, 24);
        request.fechaFin = LocalDate.of(2023, 6, 22);
        assertFalse(request.isFechaInicioBeforeOrEqualsFechaFin());

    }

}
