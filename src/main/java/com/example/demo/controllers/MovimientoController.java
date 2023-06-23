package com.example.demo.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.MovimientoRequest;
import com.example.demo.entities.Movimiento;
import com.example.demo.repositories.MovimientoRepository;
import com.example.demo.services.MovimientoService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("movimientos")
public class MovimientoController {
    MovimientoRepository movimientoRepository;
    MovimientoService movimientoService;

    @GetMapping
    public Page<Movimiento> findAll(Pageable pageable) {
        return movimientoRepository.findAll(pageable);
    }

    @GetMapping("{id}")
    public ResponseEntity<Movimiento> findById(@PathVariable Long id) {
        return ResponseEntity.of(movimientoRepository.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Movimiento register(@Valid @RequestBody MovimientoRequest request) {
        return movimientoService.register(request);
    }
}
