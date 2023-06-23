package com.example.demo.repositories;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.demo.entities.Cuenta;
import com.example.demo.entities.Movimiento;

public interface MovimientoRepository
                extends CrudRepository<Movimiento, Long>, PagingAndSortingRepository<Movimiento, Long> {

        Optional<Movimiento> findFirstByCuentaOrderByFechaDesc(Cuenta cuenta);

        @Query("""
                SELECT coalesce(SUM(m.valor), 0)
                FROM Movimiento m 
                WHERE m.cuenta = :cuenta AND m.tipo = TipoMovimiento.CREDITO AND cast(m.fecha as LocalDate) = :date
        """)
        BigDecimal montoRetiradoByCuenta(Cuenta cuenta, LocalDate date);

}
