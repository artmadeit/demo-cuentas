package com.example.demo.services;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.dtos.MovimientoRequest;
import com.example.demo.entities.Cuenta;
import com.example.demo.entities.Movimiento;
import com.example.demo.entities.TipoMovimiento;
import com.example.demo.repositories.CuentaRepository;
import com.example.demo.repositories.MovimientoRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class MovimientoService {
    public static final BigDecimal LIMITE_DIARIO = new BigDecimal(1000);

    MovimientoRepository movimientoRepository;
    CuentaRepository cuentaRepository;

    public Movimiento register(MovimientoRequest request) {

        var cuenta = cuentaRepository.findById(request.getCuentaId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cuenta no existe"));
        var ultimoSaldo = getUltimoSaldo(cuenta);

        var movimiento = new Movimiento(request.getValor(), cuenta, ultimoSaldo);

        if (movimiento.getTipo().equals(TipoMovimiento.CREDITO)) {
            if (movimiento.getSaldo().compareTo(BigDecimal.ZERO) < 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Saldo no disponible");
            }

            var montoRetirado = movimientoRepository.montoRetiradoByCuenta(cuenta, LocalDate.now());
            if (montoRetirado.compareTo(LIMITE_DIARIO) > 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cupo diario excedido");
            }
        }

        return movimientoRepository.save(movimiento);
    }

    public BigDecimal getUltimoSaldo(Cuenta cuenta) {
        var ultimoMovimiento = movimientoRepository.findFirstByCuentaOrderByFechaDesc(cuenta);
        var ultimoSaldo = ultimoMovimiento.isPresent() ? ultimoMovimiento.get().getSaldo() : cuenta.getSaldoInicial();
        return ultimoSaldo;
    }
}
