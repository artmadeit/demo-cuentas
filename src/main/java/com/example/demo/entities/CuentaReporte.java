package com.example.demo.entities;

import java.math.BigDecimal;

import com.example.demo.entities.Cuenta.TipoCuenta;

public record CuentaReporte(
        Long id,
        String numero,
        TipoCuenta tipo,
        BigDecimal saldoInicial,
        BigDecimal totalDebitos,
        BigDecimal totalCreditos) {
}
