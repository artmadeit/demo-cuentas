package com.example.demo.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.CuentaReporte;
import com.example.demo.repositories.CuentaRepository;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@RestController
@RequestMapping("reportes")
@AllArgsConstructor
@Validated
public class ReporteController {
    private final CuentaRepository cuentaRepository;

    @GetMapping
    public List<CuentaReporte> estadosCuentas(EstadoCuentaRequest request) {
        return cuentaRepository.estadosCuentas(
                request.clienteId,
                request.fechaInicio.atStartOfDay(), request.fechaFin.atTime(23, 59, 59));
    }

    @Data
    public static class EstadoCuentaRequest {
        @NotNull
        Long clienteId;

        @NotNull
        LocalDate fechaInicio;

        @NotNull
        LocalDate fechaFin;

        @AssertTrue
        public boolean isFechaInicioBeforeOrEqualsFechaFin() {
            return fechaInicio.isBefore(fechaFin) || fechaInicio.equals(fechaFin);
        }
    }
}
