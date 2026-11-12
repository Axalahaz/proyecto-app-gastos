-- PostgresSQL 

-- ------------------------------------------
-- CATEGORIAS DE GASTOS
-- ------------------------------------------

INSERT INTO categorias_gastos (
    id,
    usuario_id,
    nombre,
    tipo_objeto,
    fecha_creacion
) VALUES
(1, NULL, 'Hogar', 'SISTEMA', '2025-01-10 00:00:00'),
(2, NULL, 'Compras', 'SISTEMA', '2025-01-12 00:00:00'),
(3, NULL, 'Mascotas', 'SISTEMA', '2026-01-15 00:00:00'),
(4, NULL, 'Bar', 'SISTEMA', '2026-01-18 00:00:00'),
(5, NULL, 'Transporte', 'SISTEMA', '2026-01-20 00:00:00'),
(6, NULL, 'Educacion', 'SISTEMA', '2026-01-22 00:00:00'),
(7, NULL, 'Entretenimiento', 'SISTEMA', '2026-01-25 00:00:00'),
(8, NULL, 'Plataformas', 'SISTEMA', '2026-01-27 00:00:00'),
(9, NULL, 'Regalos', 'SISTEMA', '2026-01-30 00:00:00'),
(10, NULL, 'Restaurante', 'SISTEMA', '2026-02-01 00:00:00'),
(11, NULL, 'Ropa', 'SISTEMA', '2026-02-03 00:00:00'),
(12, NULL, 'Salud', 'SISTEMA', '2026-02-05 00:00:00'),
(13, NULL, 'Servicios', 'SISTEMA', '2026-02-08 00:00:00'),
(14, NULL, 'Trabajo', 'SISTEMA', '2026-02-10 00:00:00'),
(15, NULL, 'Viajes', 'SISTEMA', '2026-02-15 00:00:00');

SELECT setval(
    pg_get_serial_sequence('categorias_gastos', 'id'),
    (SELECT MAX(id) FROM categorias_gastos)
);