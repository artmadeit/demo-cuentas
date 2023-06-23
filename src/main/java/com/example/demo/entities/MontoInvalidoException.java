package com.example.demo.entities;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Valor invalido")
public class MontoInvalidoException extends RuntimeException {
}
