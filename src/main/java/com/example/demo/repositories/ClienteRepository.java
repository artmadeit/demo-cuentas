package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
