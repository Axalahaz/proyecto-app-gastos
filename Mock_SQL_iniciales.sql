USE finanzas_prueba_v2;

TRUNCATE TABLE gastos;
TRUNCATE TABLE gastos_recurrentes;
TRUNCATE TABLE plantilla_gastos;
TRUNCATE TABLE categorias_gastos;

-- ------------------------------------------
-- ENTITY CATEGORIAS GASTOS
-- ------------------------------------------
INSERT INTO categorias_gastos (
    id,
    nombre,
    tipo,
    fecha_creacion
) VALUES
(1, 'hogar', 'SISTEMA', '2025-01-10 00:00:00'),
(2, 'comida', 'SISTEMA', '2025-01-12 00:00:00'),
(3, 'mascota', 'SISTEMA', '2026-01-15 00:00:00'),
(4, 'bar', 'SISTEMA', '2026-01-18 00:00:00'),
(5, 'bienestar', 'SISTEMA', '2026-01-20 00:00:00'),
(6, 'educacion', 'SISTEMA', '2026-01-22 00:00:00'),
(7, 'entretenimiento', 'SISTEMA', '2026-01-25 00:00:00'),
(8, 'plataformas', 'SISTEMA', '2026-01-27 00:00:00'),
(9, 'regalos', 'USUARIO', '2026-01-30 00:00:00'),
(10, 'restaurante', 'USUARIO', '2026-02-01 00:00:00'),
(11, 'ropa', 'USUARIO', '2026-02-03 00:00:00'),
(12, 'salud', 'USUARIO', '2026-02-05 00:00:00'),
(13, 'servicios', 'SISTEMA', '2026-02-08 00:00:00'),
(14, 'trabajo', 'USUARIO', '2026-02-10 00:00:00'),
(15, 'transporte', 'USUARIO', '2026-02-12 00:00:00'),
(16, 'viajes', 'USUARIO', '2026-02-15 00:00:00'),
(17, 'compras', 'SISTEMA', '2025-01-12 00:00:00');

-- ------------------------------------------
-- ENTITY PLANTILLA GASTOS
-- ------------------------------------------
INSERT INTO plantilla_gastos (
    id,
    categoria_gasto_id,
    descripcion,
    tipo,
    fecha_creacion
) VALUES
(1, 13, 'Internet', 'SISTEMA', '2026-01-05 00:00:00'),
(2, 13, 'Luz', 'SISTEMA', '2026-01-06 00:00:00'),
(3, 13, 'Gas', 'SISTEMA', '2026-01-07 00:00:00'),
(4, 1, 'Alquiler', 'SISTEMA', '2026-01-08 00:00:00'),
(5, 8, 'Netflix', 'SISTEMA', '2026-01-09 00:00:00'),
(6, 8, 'Spotify', 'SISTEMA', '2026-01-10 00:00:00'),
(7, 15, 'SUBE', 'SISTEMA', '2026-01-11 00:00:00'),
(8, 2, 'Carniceria', 'USUARIO', '2026-01-12 00:00:00'),
(9, 2, 'Panaderia', 'USUARIO', '2025-01-13 00:00:00'),
(10, 3, 'ComidaPerro', 'USUARIO', '2026-01-14 00:00:00'),
(11, 12, 'Anticonseptivas', 'USUARIO', '2026-01-15 00:00:00'),
(12, 17, 'Supermercado', 'USUARIO', '2025-01-16 00:00:00');

-- ------------------------------------------
-- ENTITY GASTOS RECURRENTES
-- ------------------------------------------
INSERT INTO gastos_recurrentes (
    id,
    plantilla_gasto_id,
    descripcion,
    dia_vencimiento,
    mes_vencimiento,
    frecuencia,
    activo,
    fecha_creacion,
    fecha_cambio_activo
) VALUES
(1, 1, 'Internet', 5, NULL, 'MENSUAL', TRUE, '2026-01-05 00:00:00', NULL),
(2, 2, 'Luz', 10, NULL, 'MENSUAL', TRUE, '2026-01-10 00:00:00', NULL),
(3, 3, 'Gas', 15, NULL, 'MENSUAL', TRUE, '2026-01-15 00:00:00', '2026-03-01 00:00:00'),
(4, 4, 'Alquiler', 1, NULL, 'MENSUAL', TRUE, '2026-01-20 00:00:00', NULL),
(5, NULL, 'Seguro del auto', 22, 8, 'ANUAL', TRUE,'2026-02-01 00:00:00', '2026-05-10 00:00:00'),
(6, NULL, 'Patente', 7, 3, 'ANUAL', FALSE, '2026-02-10 00:00:00', '2026-06-15 00:00:00'),
(7, NULL, 'Expensas', 20, NULL, 'MENSUAL', TRUE, '2026-02-20 00:00:00', NULL);

-- ------------------------------------------
-- ENTITY GASTOS
-- ------------------------------------------
INSERT INTO gastos (
    id,
    categoria_gasto_id,
    plantilla_gasto_id,
    gasto_recurrente_id,
    descripcion,
    monto,
    estado,
    fecha_creacion,
    fecha_vencimiento,
    fecha_cambio_estado
) VALUES
(1, 17, 12, NULL, 'Supermercado', 25430.00, 'ANULADO', '2026-06-25 00:00:00', NULL, NULL),
(2, 8, 5, NULL, 'Netflix', 8990.00, 'ACTIVO', '2026-04-24 00:00:00', NULL, '2026-04-24 00:00:00'),
(3, 15, NULL, NULL, 'Uber', 4200.00, 'ACTIVO', '2026-06-23 00:00:00', NULL, NULL),
(4, 12, NULL, NULL, 'Farmacia', 7850.00, 'ACTIVO', '2026-05-22 00:00:00', NULL, NULL),
(5, 13, NULL, 1, 'Fibertel', 15900.00, 'ACTIVO', '2026-06-21 00:00:00', '2026-06-05 00:00:00', '2026-06-21 00:00:00'),
(6, 2, NULL, NULL, 'Café', 3200.00, 'ANULADO', '2026-06-20 00:00:00', NULL, NULL),
(7, 15, NULL, NULL, 'SUBE', 5000.00, 'ACTIVO', '2026-06-19 00:00:00', NULL, NULL),
(8, 15, NULL, NULL, 'SUBE1', 5000.00, 'ANULADO', '2025-06-19 00:00:00', NULL, '2025-06-19 00:00:00'),
(9, 3, 10, NULL, 'ComidaPerro', 5000.00, 'ACTIVO', '2026-07-19 00:00:00', NULL, NULL),
(10, 13, 2, 2, 'Edesur', 5000.00, 'ACTIVO', '2025-07-15 00:00:00', '2025-07-10 00:00:00', NULL),
(11, 1, NULL, NULL, 'Sillon', 25430.00, 'ACTIVO', '2026-06-26 00:00:00', NULL, NULL),
(12, 1, NULL, 7, 'Expensas', 25430.00, 'ACTIVO', '2026-06-26 00:00:00', '2026-06-20 00:00:00', NULL);

