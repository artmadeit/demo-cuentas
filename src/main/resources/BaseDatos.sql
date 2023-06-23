-- DROP DATABASE devsu;
-- CREATE DATABASE devsu;
;

CREATE TABLE IF NOT EXISTS persona (
	id bigserial NOT NULL,
	direccion varchar(255) NULL, -- o quiza text
	edad int4 NULL,
	genero int2 NULL,
	is_deleted bool NULL,
	nombres varchar(255) NULL,
	numero_identificacion varchar(255) NULL,
	telefono varchar(10) NULL,
	CONSTRAINT persona_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS cliente (
	"password" varchar(255) NULL,
	id int8 NOT NULL,
	CONSTRAINT cliente_pkey PRIMARY KEY (id),
	CONSTRAINT cliente_persona_fk FOREIGN KEY (id) REFERENCES persona(id)
);


CREATE TABLE IF NOT EXISTS cuenta (
	id bigserial NOT NULL,
	is_deleted bool NULL,
	numero varchar(255) NOT NULL,
	saldo_inicial numeric(38, 2) NOT NULL,
	tipo varchar(255) NOT NULL,
	cliente_id int8 NOT NULL,
	CONSTRAINT cuenta_pkey PRIMARY KEY (id),
	CONSTRAINT cuenta_cliente_fk FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);


CREATE TABLE IF NOT EXISTS movimiento (
	id bigserial NOT NULL,
	fecha timestamp(6) NULL,
	saldo numeric(38, 2) NOT NULL,
	tipo varchar(255) NOT NULL,
	valor numeric(38, 2) NOT NULL,
	cuenta_id int8 NOT NULL,
	CONSTRAINT movimiento_pkey PRIMARY KEY (id),
	CONSTRAINT movimiento_cuenta_fk FOREIGN KEY (cuenta_id) REFERENCES cuenta(id)
);