package com.example.demo.dtos;

import java.math.BigDecimal;

import com.example.demo.entities.Cuenta.TipoCuenta;

import lombok.Data;

@Data
public class CuentaRequest {
    String numero;
    TipoCuenta tipo;
    BigDecimal saldoInicial;
    Long clienteId;
}
