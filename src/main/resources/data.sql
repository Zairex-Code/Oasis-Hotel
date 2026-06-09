-- ==========================================
-- 1. POBLAR USUARIOS (20 Filas)
-- Roles: 2 ADMIN, 3 HOTEL_MANAGER, 15 CUSTOMER
-- ==========================================
INSERT IGNORE INTO users (first_name, last_name, email, password, role, created_at, updated_at) VALUES
('Admin', 'Supremo', 'admin@oasishotels.com', 'admin123', 'ADMIN', NOW(), NOW()),
('Root', 'System', 'root@oasishotels.com', 'admin123', 'ADMIN', NOW(), NOW()),
('Roberto', 'Gómez', 'roberto.gerente@oasishotels.com', 'manager123', 'HOTEL_MANAGER', NOW(), NOW()),
('Alicia', 'Vargas', 'alicia.gerente@oasishotels.com', 'manager123', 'HOTEL_MANAGER', NOW(), NOW()),
('Fernando', 'Ruiz', 'fernando.gerente@oasishotels.com', 'manager123', 'HOTEL_MANAGER', NOW(), NOW()),
('Laura', 'Martínez', 'laura.cliente@email.com', 'cliente123', 'CUSTOMER', NOW(), NOW()),
('Carlos', 'López', 'carlos.lopez@email.com', 'cliente123', 'CUSTOMER', NOW(), NOW()),
('María', 'González', 'maria.gonzalez@email.com', 'cliente123', 'CUSTOMER', NOW(), NOW()),
('Juan', 'Pérez', 'juan.perez@email.com', 'cliente123', 'CUSTOMER', NOW(), NOW()),
('Ana', 'García', 'ana.garcia@email.com', 'cliente123', 'CUSTOMER', NOW(), NOW()),
('Luis', 'Fernández', 'luis.fernandez@email.com', 'cliente123', 'CUSTOMER', NOW(), NOW()),
('Carmen', 'Sánchez', 'carmen.sanchez@email.com', 'cliente123', 'CUSTOMER', NOW(), NOW()),
('Jorge', 'Ramírez', 'jorge.ramirez@email.com', 'cliente123', 'CUSTOMER', NOW(), NOW()),
('Sofía', 'Torres', 'sofia.torres@email.com', 'cliente123', 'CUSTOMER', NOW(), NOW()),
('Diego', 'Flores', 'diego.flores@email.com', 'cliente123', 'CUSTOMER', NOW(), NOW()),
('Lucía', 'Rivera', 'lucia.rivera@email.com', 'cliente123', 'CUSTOMER', NOW(), NOW()),
('Miguel', 'Gómez', 'miguel.gomez@email.com', 'cliente123', 'CUSTOMER', NOW(), NOW()),
('Elena', 'Díaz', 'elena.diaz@email.com', 'cliente123', 'CUSTOMER', NOW(), NOW()),
('Pedro', 'Morales', 'pedro.morales@email.com', 'cliente123', 'CUSTOMER', NOW(), NOW()),
('Paula', 'Ortiz', 'paula.ortiz@email.com', 'cliente123', 'CUSTOMER', NOW(), NOW());


-- ==========================================
-- 2. POBLAR HOTELES (35 Filas)
-- Forzamos los IDs del 1 al 35 para asegurar las relaciones
-- ==========================================
INSERT IGNORE INTO hotels (id, name, address, city, stars_rating, status, created_at, updated_at) VALUES
(1, 'Oasis Grand Resort', 'Blvd. Kukulcan Km 10', 'Cancún', 5, 'ACTIVE', NOW(), NOW()),
(2, 'Oasis Business Center', 'Av. Reforma 456', 'Ciudad de México', 4, 'ACTIVE', NOW(), NOW()),
(3, 'Oasis Mountain Lodge', 'Bosque de Pinos S/N', 'Valle de Bravo', 3, 'ACTIVE', NOW(), NOW()),
(4, 'Oasis Pacific View', 'Malecón 123', 'Puerto Vallarta', 4, 'ACTIVE', NOW(), NOW()),
(5, 'Oasis Colonial', 'Centro Histórico 89', 'Mérida', 5, 'ACTIVE', NOW(), NOW()),
(6, 'Oasis Boutique Tulum', 'Zona Hotelera Km 5', 'Tulum', 5, 'ACTIVE', NOW(), NOW()),
(7, 'Oasis Aeropuerto', 'Terminal 1 Blvd', 'Monterrey', 3, 'ACTIVE', NOW(), NOW()),
(8, 'Oasis Centro Histórico', 'Plaza de Armas 10', 'Querétaro', 4, 'ACTIVE', NOW(), NOW()),
(9, 'Oasis Eco Resort', 'Selva Lacandona S/N', 'Chiapas', 4, 'ACTIVE', NOW(), NOW()),
(10, 'Oasis Ejecutivo', 'Zona Financiera Andares', 'Guadalajara', 5, 'ACTIVE', NOW(), NOW()),
(11, 'Oasis Playa Norte', 'Malecón 400', 'Mazatlán', 3, 'ACTIVE', NOW(), NOW()),
(12, 'Oasis Valle de Guadalupe', 'Ruta del Vino Km 12', 'Ensenada', 5, 'ACTIVE', NOW(), NOW()),
(13, 'Oasis Imperial', 'Paseo Montejo 55', 'Mérida', 4, 'ACTIVE', NOW(), NOW()),
(14, 'Oasis de la Plata', 'Callejón del Beso', 'Guanajuato', 4, 'ACTIVE', NOW(), NOW()),
(15, 'Oasis Desierto', 'Dunas de Samalayuca', 'Ciudad Juárez', 3, 'ACTIVE', NOW(), NOW()),
(16, 'Oasis Cañón', 'Barrancas del Cobre', 'Chihuahua', 5, 'ACTIVE', NOW(), NOW()),
(17, 'Oasis Surf', 'Zicatela 100', 'Puerto Escondido', 4, 'ACTIVE', NOW(), NOW()),
(18, 'Oasis Capital', 'Reforma 500', 'Ciudad de México', 5, 'ACTIVE', NOW(), NOW()),
(19, 'Oasis Coyoacán', 'Centro de Coyoacán', 'Ciudad de México', 4, 'ACTIVE', NOW(), NOW()),
(20, 'Oasis Los Cabos', 'Corredor Turístico', 'Los Cabos', 5, 'ACTIVE', NOW(), NOW()),
(21, 'Oasis Malecón', 'Malecón 800', 'Veracruz', 3, 'ACTIVE', NOW(), NOW()),
(22, 'Oasis Huasteca', 'Ruta de las Cascadas', 'San Luis Potosí', 4, 'ACTIVE', NOW(), NOW()),
(23, 'Oasis Industrial', 'Parque Industrial 200', 'Saltillo', 3, 'ACTIVE', NOW(), NOW()),
(24, 'Oasis del Sol', 'Costera Miguel Alemán', 'Acapulco', 4, 'UNDER_MAINTENANCE', NOW(), NOW()),
(25, 'Oasis Frontera', 'Garita de San Ysidro', 'Tijuana', 3, 'ACTIVE', NOW(), NOW()),
(26, 'Oasis Mágico', 'Pueblo Mágico 1', 'San Cristóbal', 4, 'ACTIVE', NOW(), NOW()),
(27, 'Oasis Volcán', 'Faldas del Popo', 'Puebla', 3, 'ACTIVE', NOW(), NOW()),
(28, 'Oasis Diamante', 'Zona Diamante', 'Acapulco', 5, 'ACTIVE', NOW(), NOW()),
(29, 'Oasis Sierra', 'Sierra Gorda', 'Querétaro', 4, 'ACTIVE', NOW(), NOW()),
(30, 'Oasis Paraíso', 'Playa Paraíso', 'Tulum', 5, 'ACTIVE', NOW(), NOW()),
(31, 'Oasis Express Sur', 'Periférico Sur', 'Ciudad de México', 3, 'ACTIVE', NOW(), NOW()),
(32, 'Oasis Dorado', 'Zona Dorada', 'Mazatlán', 4, 'ACTIVE', NOW(), NOW()),
(33, 'Oasis Arqueológico', 'Zona Arqueológica', 'Palenque', 3, 'ACTIVE', NOW(), NOW()),
(34, 'Oasis Termal', 'Aguas Termales', 'Hidalgo', 4, 'ACTIVE', NOW(), NOW()),
(35, 'Oasis Gran Lujo', 'Punta Mita', 'Nayarit', 5, 'ACTIVE', NOW(), NOW());


-- ==========================================
-- 3. POBLAR HABITACIONES (Múltiples Filas)
-- ==========================================
INSERT IGNORE INTO rooms (room_number, capacity, price_per_night, room_type, room_status, hotel_id, created_at, updated_at) VALUES
-- Hotel 1 a 5
('101A', 2, 150.00, 'DOUBLE', 'AVAILABLE', 1, NOW(), NOW()),
('102B', 4, 300.50, 'SUITE', 'AVAILABLE', 1, NOW(), NOW()),
('201A', 2, 850.00, 'PRESIDENTIAL', 'AVAILABLE', 1, NOW(), NOW()),
('202B', 2, 150.00, 'DOUBLE', 'OCCUPIED', 1, NOW(), NOW()),
('101', 1, 90.00, 'SINGLE', 'AVAILABLE', 2, NOW(), NOW()),
('102', 2, 120.00, 'DOUBLE', 'MAINTENANCE', 2, NOW(), NOW()),
('103', 1, 90.00, 'SINGLE', 'AVAILABLE', 2, NOW(), NOW()),
('PENTHOUSE', 4, 500.00, 'SUITE', 'AVAILABLE', 2, NOW(), NOW()),
('CAB-01', 2, 200.00, 'MATRIMONIAL', 'AVAILABLE', 3, NOW(), NOW()),
('CAB-02', 4, 250.00, 'SUITE', 'AVAILABLE', 3, NOW(), NOW()),
('CAB-03', 2, 200.00, 'MATRIMONIAL', 'OCCUPIED', 3, NOW(), NOW()),
('CAB-04', 6, 350.00, 'SUITE', 'MAINTENANCE', 3, NOW(), NOW()),
('VIEW-1', 2, 180.00, 'MATRIMONIAL', 'AVAILABLE', 4, NOW(), NOW()),
('VIEW-2', 2, 180.00, 'MATRIMONIAL', 'AVAILABLE', 4, NOW(), NOW()),
('VIEW-3', 4, 280.00, 'DOUBLE', 'OCCUPIED', 4, NOW(), NOW()),
('OCEAN-1', 2, 400.00, 'SUITE', 'AVAILABLE', 4, NOW(), NOW()),
('COL-101', 1, 100.00, 'SINGLE', 'AVAILABLE', 5, NOW(), NOW()),
('COL-102', 2, 130.00, 'DOUBLE', 'AVAILABLE', 5, NOW(), NOW()),
('COL-201', 2, 150.00, 'MATRIMONIAL', 'AVAILABLE', 5, NOW(), NOW()),
('KING-01', 2, 600.00, 'PRESIDENTIAL', 'OUT_OF_SERVICE', 5, NOW(), NOW()),

-- Hotel 6 al 30 (Nuevas Habitaciones)
('TUL-01', 2, 450.00, 'MATRIMONIAL', 'AVAILABLE', 6, NOW(), NOW()),
('TUL-02', 4, 800.00, 'PRESIDENTIAL', 'OCCUPIED', 6, NOW(), NOW()),
('AERO-10', 1, 80.00, 'SINGLE', 'AVAILABLE', 7, NOW(), NOW()),
('AERO-11', 2, 110.00, 'DOUBLE', 'AVAILABLE', 7, NOW(), NOW()),
('QRO-101', 2, 150.00, 'DOUBLE', 'MAINTENANCE', 8, NOW(), NOW()),
('QRO-102', 2, 180.00, 'MATRIMONIAL', 'AVAILABLE', 8, NOW(), NOW()),
('ECO-01', 4, 200.00, 'SUITE', 'AVAILABLE', 9, NOW(), NOW()),
('ECO-02', 2, 120.00, 'DOUBLE', 'AVAILABLE', 9, NOW(), NOW()),
('GDL-P1', 2, 600.00, 'PRESIDENTIAL', 'AVAILABLE', 10, NOW(), NOW()),
('GDL-P2', 4, 350.00, 'SUITE', 'OCCUPIED', 10, NOW(), NOW()),
('MAZ-101', 2, 90.00, 'DOUBLE', 'AVAILABLE', 11, NOW(), NOW()),
('MAZ-102', 4, 150.00, 'SUITE', 'AVAILABLE', 11, NOW(), NOW()),
('VINO-01', 2, 400.00, 'MATRIMONIAL', 'AVAILABLE', 12, NOW(), NOW()),
('VINO-02', 2, 400.00, 'MATRIMONIAL', 'OCCUPIED', 12, NOW(), NOW()),
('MER-100', 2, 140.00, 'DOUBLE', 'AVAILABLE', 13, NOW(), NOW()),
('GTO-01', 2, 130.00, 'MATRIMONIAL', 'AVAILABLE', 14, NOW(), NOW()),
('JUA-01', 1, 70.00, 'SINGLE', 'AVAILABLE', 15, NOW(), NOW()),
('CHI-01', 4, 500.00, 'SUITE', 'AVAILABLE', 16, NOW(), NOW()),
('SURF-1', 2, 100.00, 'DOUBLE', 'OCCUPIED', 17, NOW(), NOW()),
('CDMX-1', 2, 700.00, 'PRESIDENTIAL', 'AVAILABLE', 18, NOW(), NOW()),
('COY-01', 2, 160.00, 'MATRIMONIAL', 'AVAILABLE', 19, NOW(), NOW()),
('CABO-1', 4, 900.00, 'SUITE', 'AVAILABLE', 20, NOW(), NOW()),
('VER-01', 2, 95.00, 'DOUBLE', 'AVAILABLE', 21, NOW(), NOW()),
('HUA-01', 4, 180.00, 'SUITE', 'AVAILABLE', 22, NOW(), NOW()),
('SAL-01', 1, 85.00, 'SINGLE', 'AVAILABLE', 23, NOW(), NOW()),
('ACA-01', 2, 150.00, 'DOUBLE', 'OUT_OF_SERVICE', 24, NOW(), NOW()),
('TIJ-01', 2, 100.00, 'DOUBLE', 'AVAILABLE', 25, NOW(), NOW()),
('CRI-01', 2, 140.00, 'MATRIMONIAL', 'AVAILABLE', 26, NOW(), NOW()),
('PUE-01', 1, 90.00, 'SINGLE', 'AVAILABLE', 27, NOW(), NOW()),
('DIA-01', 2, 800.00, 'PRESIDENTIAL', 'AVAILABLE', 28, NOW(), NOW());

