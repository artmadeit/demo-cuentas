package com.example.demo.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.entities.Cliente;
import com.example.demo.entities.Cuenta;
import com.example.demo.entities.Movimiento;
import com.example.demo.entities.Cuenta.TipoCuenta;
import static org.assertj.core.api.Assertions.*;

@DataJpaTest
public class MovimientoRepositoryTests {
    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    CuentaRepository cuentaRepository;

    @Autowired
    MovimientoRepository movimientoRepository;

    Cuenta cuenta1;
    Cuenta cuenta2;

    @BeforeEach
    void setUp() {
        var cliente = new Cliente();
        cliente.setNombres("Arthur");
        cliente.setDireccion("Lima Peru");
        cliente.setTelefono("979705333");
        cliente.setPassword("unpassword");
        clienteRepository.save(cliente);

        cuenta1 = cuentaRepository.save(Cuenta.builder()
                .cliente(cliente)
                .numero("1111")
                .saldoInicial(new BigDecimal(200))
                .tipo(TipoCuenta.AHORRO).build());

        cuenta2 = cuentaRepository.save(Cuenta.builder()
                .cliente(cliente)
                .numero("2222")
                .saldoInicial(new BigDecimal(5000))
                .tipo(TipoCuenta.AHORRO).build());

        movimientoRepository.save(new Movimiento(new BigDecimal(100), cuenta1, new BigDecimal(200)));
        movimientoRepository.save(new Movimiento(new BigDecimal(-50), cuenta1, new BigDecimal(300)));
        movimientoRepository.save(new Movimiento(new BigDecimal(-20), cuenta1, new BigDecimal(250)));
        movimientoRepository.save(new Movimiento(new BigDecimal(500), cuenta1, new BigDecimal(230)));
    }

    @Test
    void findFirstByCuentaOrderByFechaDesc() {
        var ultimoMovimiento = movimientoRepository.findFirstByCuentaOrderByFechaDesc(cuenta1);
        assertTrue(ultimoMovimiento.isPresent());
        assertEquals(new BigDecimal(500), ultimoMovimiento.get().getValor());
        assertEquals(new BigDecimal(730), ultimoMovimiento.get().getSaldo());

        ultimoMovimiento = movimientoRepository.findFirstByCuentaOrderByFechaDesc(cuenta2);
        assertFalse(ultimoMovimiento.isPresent());
    }

    @Test
    void montoRetiradoByCuenta() {
        assertThat(new BigDecimal(-70))
                .isEqualByComparingTo(movimientoRepository.montoRetiradoByCuenta(cuenta1, LocalDate.now()));
        assertThat(new BigDecimal(0))
                .isEqualByComparingTo(movimientoRepository.montoRetiradoByCuenta(cuenta2, LocalDate.now()));

    }
}
