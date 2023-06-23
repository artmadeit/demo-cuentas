package com.example.demo.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.dtos.CuentaRequest;
import com.example.demo.entities.Cuenta;
import com.example.demo.repositories.ClienteRepository;
import com.example.demo.repositories.CuentaRepository;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("cuentas")
public class CuentaController {
    CuentaRepository cuentaRepository;
    ClienteRepository clienteRepository;

    @GetMapping
    public Page<Cuenta> findAll(Pageable pageable) {
        return cuentaRepository.findAll(pageable);
    }

    @GetMapping("{id}")
    public ResponseEntity<Cuenta> findById(@PathVariable Long id) {
        return ResponseEntity.of(cuentaRepository.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cuenta register(@Valid @RequestBody CuentaRequest cuentaRequest) {
        var cuenta = toEntity(cuentaRequest);
        return cuentaRepository.save(cuenta);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        cuentaRepository.deleteById(id);
    }

    private Cuenta toEntity(CuentaRequest cuentaRequest) {
        return Cuenta.builder()
                .numero(cuentaRequest.getNumero())
                .saldoInicial(cuentaRequest.getSaldoInicial())
                .tipo(cuentaRequest.getTipo())
                .cliente(clienteRepository.findById(cuentaRequest.getClienteId())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente no existe")))
                .build();
    }
}
