Notas generales

- El archivo BaseDatos.sql esta en resources
- Se uso como patron de diseño el builder, DTO, repository, service.
- Se crearon un custom excepcion, MontoInvalidoExcepcion.
- Para el exception handling
- Unit tests: TipoMovimientoTests y EstadoCuentaRequestTests
- Integration test, a nivel de persistence test: MovimientoRepositoryTests

Suposiciones:

- Validacion de telefonos de 9 digitos como sale en el ejemplo (sin embargo depende mucho si aceptaran telefonos internacionales).
- En cliente y persona solo los campos usados para registrar como sale en el ejemplo han sido validados
- Entiendo que una cuenta una vez que se crea no es editable. Por eso he emovido el PUT
- De igual forma, un movimiento no se puede editar ni eliminar, al menos asi funciona en los bancos.
