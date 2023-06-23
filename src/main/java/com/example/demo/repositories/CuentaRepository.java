package com.example.demo.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entities.Cuenta;
import com.example.demo.entities.CuentaReporte;

public interface CuentaRepository extends JpaRepository<Cuenta, Long> {

    @Query("""
            SELECT new com.example.demo.entities.CuentaReporte(
                c.id,
                c.numero,
                c.tipo,
                c.saldoInicial,
                (
                    SELECT SUM(m.valor)
                    FROM Movimiento m
                    WHERE m.cuenta.id = c.id
                        AND m.tipo = TipoMovimiento.DEBITO
                        AND m.fecha BETWEEN :fechaInicio AND :fechaFin
                ) as totalDebitos,
                (
                    SELECT SUM(m.valor)
                    FROM Movimiento m
                    WHERE m.cuenta.id = c.id
                        AND m.tipo = TipoMovimiento.CREDITO
                        AND m.fecha BETWEEN :fechaInicio AND :fechaFin
                ) as totalCreditos
            )
            FROM Cuenta c
            WHERE c.cliente.id = :clienteId
            AND c.isDeleted = false
            """)
    List<CuentaReporte> estadosCuentas(
            Long clienteId,
            LocalDateTime fechaInicio,
            LocalDateTime fechaFin);
}
