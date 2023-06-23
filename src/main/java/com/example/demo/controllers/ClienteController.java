package com.example.demo.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.ClienteRequest;
import com.example.demo.entities.Cliente;
import com.example.demo.repositories.ClienteRepository;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("clientes")
public class ClienteController {

    ClienteRepository clienteRepository;
    PasswordEncoder passwordEncoder;

    @GetMapping
    public Page<Cliente> findAll(Pageable pageable) {
        return clienteRepository.findAll(pageable);
    }

    @GetMapping("{id}")
    public ResponseEntity<Cliente> findById(@PathVariable Long id) {
        return ResponseEntity.of(clienteRepository.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente register(@Valid @RequestBody ClienteRequest clienteRequest) {
        var cliente = toEntity(clienteRequest, new Cliente());
        return clienteRepository.save(cliente);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        clienteRepository.deleteById(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> edit(@PathVariable Long id, @Valid @RequestBody ClienteRequest clienteRequest) {
        return clienteRepository.findById(id).map(cliente -> {
            cliente = toEntity(clienteRequest, cliente);
            return ResponseEntity.ok(clienteRepository.save(cliente));
        }).orElse(ResponseEntity.notFound().build());
    }

    private Cliente toEntity(ClienteRequest clienteRequest, Cliente cliente) {
        cliente.setDireccion(clienteRequest.getDireccion());
        cliente.setEdad(clienteRequest.getEdad());
        cliente.setGenero(clienteRequest.getGenero());
        cliente.setNombres(clienteRequest.getNombres());
        cliente.setNumeroIdentificacion(clienteRequest.getNumeroIdentificacion());
        cliente.setPassword(passwordEncoder.encode(clienteRequest.getPassword()));
        cliente.setTelefono(clienteRequest.getTelefono());
        return cliente;
    }
}
