-- ===================================================================
-- 1. SEEDING USERS (20 Rows)
-- Passwords have been securely hashed using BCrypt to support authentication provider verification.
-- ALL SEED USERS CAPITALIZE THE SAME ACCESS PASSWORD: password123
-- ===================================================================
INSERT IGNORE INTO users (first_name, last_name, email, password, role, created_at, updated_at) VALUES
('Admin', 'Supremo', 'admin@oasishotels.com', '$2a$10$gBcaI0pQG38HmWvC0.WkX.9Wb/K0XgL6/W0o8gZ6hI.fU.H7a4r2K', 'ADMIN', NOW(), NOW()),
('Root', 'System', 'root@oasishotels.com', '$2a$10$gBcaI0pQG38HmWvC0.WkX.9Wb/K0XgL6/W0o8gZ6hI.fU.H7a4r2K', 'ADMIN', NOW(), NOW()),
('Roberto', 'Gómez', 'roberto.gerente@oasishotels.com', '$2a$10$gBcaI0pQG38HmWvC0.WkX.9Wb/K0XgL6/W0o8gZ6hI.fU.H7a4r2K', 'HOTEL_MANAGER', NOW(), NOW()),
('Alicia', 'Vargas', 'alicia.gerente@oasishotels.com', '$2a$10$gBcaI0pQG38HmWvC0.WkX.9Wb/K0XgL6/W0o8gZ6hI.fU.H7a4r2K', 'HOTEL_MANAGER', NOW(), NOW()),
('Fernando', 'Ruiz', 'fernando.gerente@oasishotels.com', '$2a$10$gBcaI0pQG38HmWvC0.WkX.9Wb/K0XgL6/W0o8gZ6hI.fU.H7a4r2K', 'HOTEL_MANAGER', NOW(), NOW()),
('Laura', 'Martínez', 'laura.cliente@email.com', '$2a$10$gBcaI0pQG38HmWvC0.WkX.9Wb/K0XgL6/W0o8gZ6hI.fU.H7a4r2K', 'CUSTOMER', NOW(), NOW()),
('Carlos', 'López', 'carlos.lopez@email.com', '$2a$10$gBcaI0pQG38HmWvC0.WkX.9Wb/K0XgL6/W0o8gZ6hI.fU.H7a4r2K', 'CUSTOMER', NOW(), NOW()),
('María', 'González', 'maria.gonzalez@email.com', '$2a$10$gBcaI0pQG38HmWvC0.WkX.9Wb/K0XgL6/W0o8gZ6hI.fU.H7a4r2K', 'CUSTOMER', NOW(), NOW()),
('Juan', 'Pérez', 'juan.perez@email.com', '$2a$10$gBcaI0pQG38HmWvC0.WkX.9Wb/K0XgL6/W0o8gZ6hI.fU.H7a4r2K', 'CUSTOMER', NOW(), NOW()),
('Ana', 'García', 'ana.garcia@email.com', '$2a$10$gBcaI0pQG38HmWvC0.WkX.9Wb/K0XgL6/W0o8gZ6hI.fU.H7a4r2K', 'CUSTOMER', NOW(), NOW()),
('Luis', 'Fernández', 'luis.fernandez@email.com', '$2a$10$gBcaI0pQG38HmWvC0.WkX.9Wb/K0XgL6/W0o8gZ6hI.fU.H7a4r2K', 'CUSTOMER', NOW(), NOW()),
('Carmen', 'Sánchez', 'carmen.sanchez@email.com', '$2a$10$gBcaI0pQG38HmWvC0.WkX.9Wb/K0XgL6/W0o8gZ6hI.fU.H7a4r2K', 'CUSTOMER', NOW(), NOW()),
('Jorge', 'Ramírez', 'jorge.ramirez@email.com', '$2a$10$gBcaI0pQG38HmWvC0.WkX.9Wb/K0XgL6/W0o8gZ6hI.fU.H7a4r2K', 'CUSTOMER', NOW(), NOW()),
('Sofía', 'Torres', 'sofia.torres@email.com', '$2a$10$gBcaI0pQG38HmWvC0.WkX.9Wb/K0XgL6/W0o8gZ6hI.fU.H7a4r2K', 'CUSTOMER', NOW(), NOW()),
('Diego', 'Flores', 'diego.flores@email.com', '$2a$10$gBcaI0pQG38HmWvC0.WkX.9Wb/K0XgL6/W0o8gZ6hI.fU.H7a4r2K', 'CUSTOMER', NOW(), NOW()),
('Lucía', 'Rivera', 'lucia.rivera@email.com', '$2a$10$gBcaI0pQG38HmWvC0.WkX.9Wb/K0XgL6/W0o8gZ6hI.fU.H7a4r2K', 'CUSTOMER', NOW(), NOW()),
('Miguel', 'Gómez', 'miguel.gomez@email.com', '$2a$10$gBcaI0pQG38HmWvC0.WkX.9Wb/K0XgL6/W0o8gZ6hI.fU.H7a4r2K', 'CUSTOMER', NOW(), NOW()),
('Elena', 'Díaz', 'elena.diaz@email.com', '$2a$10$gBcaI0pQG38HmWvC0.WkX.9Wb/K0XgL6/W0o8gZ6hI.fU.H7a4r2K', 'CUSTOMER', NOW(), NOW()),
('Pedro', 'Morales', 'pedro.morales@email.com', '$2a$10$gBcaI0pQG38HmWvC0.WkX.9Wb/K0XgL6/W0o8gZ6hI.fU.H7a4r2K', 'CUSTOMER', NOW(), NOW()),
('Paula', 'Ortiz', 'paula.ortiz@email.com', '$2a$10$gBcaI0pQG38HmWvC0.WkX.9Wb/K0XgL6/W0o8gZ6hI.fU.H7a4r2K', 'CUSTOMER', NOW(), NOW());


-- ==========================================
-- 2. POBLAR HOTELES (35 Filas con Imágenes Optimizadas para Next.js)
-- ==========================================
INSERT IGNORE INTO hotels (id, name, address, city, stars_rating, status, image_url, created_at, updated_at) VALUES
(1, 'Oasis Grand Resort', 'Blvd. Kukulcan Km 10', 'Cancún', 5, 'ACTIVE', 'https://images.unsplash.com/photo-1571003123894-1f0594d2b5d9?q=80&w=800&auto=format&fit=crop', NOW(), NOW()),
(2, 'Oasis Business Center', 'Av. Reforma 456', 'Ciudad de México', 4, 'ACTIVE', 'https://images.unsplash.com/photo-1566073771259-6a8506099945?q=80&w=800&auto=format&fit=crop', NOW(), NOW()),
(3, 'Oasis Mountain Lodge', 'Bosque de Pinos S/N', 'Valle de Bravo', 3, 'ACTIVE', 'https://images.unsplash.com/photo-1518780664697-55e3ad937233?q=80&w=800&auto=format&fit=crop', NOW(), NOW()),
(4, 'Oasis Pacific View', 'Malecón 123', 'Puerto Vallarta', 4, 'ACTIVE', 'https://images.unsplash.com/photo-1540541338287-41700207dee6?q=80&w=800&auto=format&fit=crop', NOW(), NOW()),
(5, 'Oasis Colonial', 'Centro Histórico 89', 'Mérida', 5, 'ACTIVE', 'https://images.unsplash.com/photo-1520250497591-112f2f40a3f4?q=80&w=800&auto=format&fit=crop', NOW(), NOW()),
(6, 'Oasis Boutique Tulum', 'Zona Hotelera Km 5', 'Tulum', 5, 'ACTIVE', 'https://images.unsplash.com/photo-1582719508461-905c673771fd?q=80&w=800&auto=format&fit=crop', NOW(), NOW()),
(7, 'Oasis Aeropuerto', 'Terminal 1 Blvd', 'Monterrey', 3, 'ACTIVE', 'https://images.unsplash.com/photo-1551882547-ff40c0d12256?q=80&w=800&auto=format&fit=crop', NOW(), NOW()),
(8, 'Oasis Centro Histórico', 'Plaza de Armas 10', 'Querétaro', 4, 'ACTIVE', 'https://images.unsplash.com/photo-1445019980597-93fa8acb246c?q=80&w=800&auto=format&fit=crop', NOW(), NOW()),
(9, 'Oasis Eco Resort', 'Selva Lacandona S/N', 'Chiapas', 4, 'ACTIVE', 'https://images.unsplash.com/photo-1590490360182-c33d57733427?q=80&w=800&auto=format&fit=crop', NOW(), NOW()),
(10, 'Oasis Ejecutivo', 'Zona Financiera Andares', 'Guadalajara', 5, 'ACTIVE', 'https://images.unsplash.com/photo-1571896349842-33c89424de2d?q=80&w=800&auto=format&fit=crop', NOW(), NOW()),
(11, 'Oasis Playa Norte', 'Malecón 400', 'Mazatlán', 3, 'ACTIVE', 'https://images.unsplash.com/photo-1522798514-97ceb8c4f1c8?q=80&w=800&auto=format&fit=crop', NOW(), NOW()),
(12, 'Oasis Valle de Guadalupe', 'Ruta del Vino Km 12', 'Ensenada', 5, 'ACTIVE', 'https://images.unsplash.com/photo-1560319080-b046160eb88c?q=80&w=800&auto=format&fit=crop', NOW(), NOW()),
(13, 'Oasis Imperial', 'Paseo Montejo 55', 'Mérida', 4, 'ACTIVE', 'https://images.unsplash.com/photo-1584132967334-10e028bd69f7?q=80&w=800&auto=format&fit=crop', NOW(), NOW()),
(14, 'Oasis de la Plata', 'Callejón del Beso', 'Guanajuato', 4, 'ACTIVE', 'https://images.unsplash.com/photo-1555854877-bab0e564b8d5?q=80&w=800&auto=format&fit=crop', NOW(), NOW()),
(15, 'Oasis Desierto', 'Dunas de Samalayuca', 'Ciudad Juárez', 3, 'ACTIVE', 'https://images.unsplash.com/photo-1534612899740-55c821a90129?q=80&w=800&auto=format&fit=crop', NOW(), NOW()),
(16, 'Oasis Cañón', 'Barrancas del Cobre', 'Chihuahua', 5, 'ACTIVE', 'https://images.unsplash.com/photo-1506059612708-99d6c258160e?q=80&w=800&auto=format&fit=crop', NOW(), NOW()),
(17, 'Oasis Surf', 'Zicatela 100', 'Puerto Escondido', 4, 'ACTIVE', 'https://images.unsplash.com/photo-1544085311-11a028465b03?q=80&w=800&auto=format&fit=crop', NOW(), NOW()),
(18, 'Oasis Capital', 'Reforma 500', 'Ciudad de México', 5, 'ACTIVE', 'https://images.unsplash.com/photo-1618773928120-221657c79e60?q=80&w=800&auto=format&fit=crop', NOW(), NOW()),
(19, 'Oasis Coyoacán', 'Centro de Coyoacán', 'Ciudad de México', 4, 'ACTIVE', 'https://images.unsplash.com/photo-1596394516093-501ba68a0ba6?q=80&w=800&auto=format&fit=crop', NOW(), NOW()),
(20, 'Oasis Los Cabos', 'Corredor Turístico', 'Los Cabos', 5, 'ACTIVE', 'https://images.unsplash.com/photo-1580216643062-cf460548a66a?q=80&w=800&auto=format&fit=crop', NOW(), NOW()),
(21, 'Oasis Malecón', 'Malecón 800', 'Veracruz', 3, 'ACTIVE', 'https://images.unsplash.com/photo-1520250497591-112f2f40a3f4?q=80&w=800&auto=format&fit=crop', NOW(), NOW()),
(22, 'Oasis Huasteca', 'Ruta de las Cascadas', 'San Luis Potosí', 4, 'ACTIVE', 'https://images.unsplash.com/photo-1518780664697-55e3ad937233?q=80&w=800&auto=format&fit=crop', NOW(), NOW()),
(23, 'Oasis Industrial', 'Parque Industrial 200', 'Saltillo', 3, 'ACTIVE', 'https://images.unsplash.com/photo-1551882547-ff40c0d12256?q=80&w=800&auto=format&fit=crop', NOW(), NOW()),
(24, 'Oasis del Sol', 'Costera Miguel Alemán', 'Acapulco', 4, 'UNDER_MAINTENANCE', 'https://images.unsplash.com/photo-1540541338287-41700207dee6?q=80&w=800&auto=format&fit=crop', NOW(), NOW()),
(25, 'Oasis Frontera', 'Garita de San Ysidro', 'Tijuana', 3, 'ACTIVE', 'https://images.unsplash.com/photo-1590490360182-c33d57733427?q=80&w=800&auto=format&fit=crop', NOW(), NOW()),
(26, 'Oasis Mágico', 'Pueblo Mágico 1', 'San Cristóbal', 4, 'ACTIVE', 'https://images.unsplash.com/photo-1555854877-bab0e564b8d5?q=80&w=800&auto=format&fit=crop', NOW(), NOW()),
(27, 'Oasis Volcán', 'Faldas del Popo', 'Puebla', 3, 'ACTIVE', 'https://images.unsplash.com/photo-1506059612708-99d6c258160e?q=80&w=800&auto=format&fit=crop', NOW(), NOW()),
(28, 'Oasis Diamante', 'Zona Diamante', 'Acapulco', 5, 'ACTIVE', 'https://images.unsplash.com/photo-1571896349842-33c89424de2d?q=80&w=800&auto=format&fit=crop', NOW(), NOW()),
(29, 'Oasis Sierra', 'Sierra Gorda', 'Querétaro', 4, 'ACTIVE', 'https://images.unsplash.com/photo-1534612899740-55c821a90129?q=80&w=800&auto=format&fit=crop', NOW(), NOW()),
(30, 'Oasis Paraíso', 'Playa Paraíso', 'Tulum', 5, 'ACTIVE', 'https://images.unsplash.com/photo-1582719508461-905c673771fd?q=80&w=800&auto=format&fit=crop', NOW(), NOW()),
(31, 'Oasis Express Sur', 'Periférico Sur', 'Ciudad de México', 3, 'ACTIVE', 'https://images.unsplash.com/photo-1566073771259-6a8506099945?q=80&w=800&auto=format&fit=crop', NOW(), NOW()),
(32, 'Oasis Dorado', 'Zona Dorada', 'Mazatlán', 4, 'ACTIVE', 'https://images.unsplash.com/photo-1522798514-97ceb8c4f1c8?q=80&w=800&auto=format&fit=crop', NOW(), NOW()),
(33, 'Oasis Arqueológico', 'Zona Arqueológica', 'Palenque', 3, 'ACTIVE', 'https://images.unsplash.com/photo-1584132967334-10e028bd69f7?q=80&w=800&auto=format&fit=crop', NOW(), NOW()),
(34, 'Oasis Termal', 'Aguas Termales', 'Hidalgo', 4, 'ACTIVE', 'https://images.unsplash.com/photo-1560319080-b046160eb88c?q=80&w=800&auto=format&fit=crop', NOW(), NOW()),
(35, 'Oasis Gran Lujo', 'Punta Mita', 'Nayarit', 5, 'ACTIVE', 'https://images.unsplash.com/photo-1571003123894-1f0594d2b5d9?q=80&w=800&auto=format&fit=crop', NOW(), NOW());

-- ===================================================================
-- 3. POBLAR HABITACIONES (50 Habitaciones distribuidas en los hoteles)
-- ===================================================================
INSERT IGNORE INTO rooms (id, hotel_id, room_number, room_type, capacity, price_per_night, room_status, created_at, updated_at) VALUES
-- Hotel 1 (Oasis Grand Resort)
(1, 1, '101', 'SINGLE', 1, 100.00, 'AVAILABLE', NOW(), NOW()),
(2, 1, '102', 'DOUBLE', 2, 150.00, 'AVAILABLE', NOW(), NOW()),
(3, 1, '103', 'SUITE', 4, 300.00, 'AVAILABLE', NOW(), NOW()),
(4, 1, '104', 'MATRIMONIAL', 2, 160.00, 'AVAILABLE', NOW(), NOW()),
(5, 1, '105', 'PRESIDENTIAL', 6, 800.00, 'AVAILABLE', NOW(), NOW()),
-- Hotel 2 (Oasis Business Center)
(6, 2, '201', 'SINGLE', 1, 90.00, 'AVAILABLE', NOW(), NOW()),
(7, 2, '202', 'DOUBLE', 2, 140.00, 'AVAILABLE', NOW(), NOW()),
(8, 2, '203', 'SUITE', 4, 280.00, 'AVAILABLE', NOW(), NOW()),
(9, 2, '204', 'MATRIMONIAL', 2, 150.00, 'AVAILABLE', NOW(), NOW()),
-- Hotel 3 (Oasis Mountain Lodge)
(10, 3, '301', 'DOUBLE', 2, 120.00, 'AVAILABLE', NOW(), NOW()),
(11, 3, '302', 'DOUBLE', 2, 120.00, 'AVAILABLE', NOW(), NOW()),
(12, 3, '303', 'SUITE', 4, 250.00, 'AVAILABLE', NOW(), NOW()),
-- Hotel 4 (Oasis Pacific View)
(13, 4, '401', 'SINGLE', 1, 110.00, 'AVAILABLE', NOW(), NOW()),
(14, 4, '402', 'DOUBLE', 2, 170.00, 'AVAILABLE', NOW(), NOW()),
(15, 4, '403', 'MATRIMONIAL', 2, 180.00, 'AVAILABLE', NOW(), NOW()),
-- Hotel 5 (Oasis Colonial)
(16, 5, '501', 'DOUBLE', 2, 200.00, 'AVAILABLE', NOW(), NOW()),
(17, 5, '502', 'SUITE', 4, 400.00, 'AVAILABLE', NOW(), NOW()),
(18, 5, '503', 'PRESIDENTIAL', 6, 900.00, 'AVAILABLE', NOW(), NOW()),
-- Hotel 6 (Oasis Boutique Tulum)
(19, 6, '601', 'SINGLE', 1, 130.00, 'AVAILABLE', NOW(), NOW()),
(20, 6, '602', 'DOUBLE', 2, 190.00, 'AVAILABLE', NOW(), NOW()),
-- Hotel 7 (Oasis Aeropuerto)
(21, 7, '701', 'DOUBLE', 2, 100.00, 'AVAILABLE', NOW(), NOW()),
(22, 7, '702', 'MATRIMONIAL', 2, 110.00, 'AVAILABLE', NOW(), NOW()),
-- Hotel 8 (Oasis Centro Histórico)
(23, 8, '801', 'SINGLE', 1, 80.00, 'AVAILABLE', NOW(), NOW()),
(24, 8, '802', 'DOUBLE', 2, 130.00, 'AVAILABLE', NOW(), NOW()),
-- Hotel 9 (Oasis Eco Resort)
(25, 9, '901', 'SUITE', 4, 220.00, 'AVAILABLE', NOW(), NOW()),
(26, 9, '902', 'SUITE', 4, 220.00, 'AVAILABLE', NOW(), NOW()),
-- Hotel 10 (Oasis Ejecutivo)
(27, 10, '1001', 'DOUBLE', 2, 160.00, 'AVAILABLE', NOW(), NOW()),
(28, 10, '1002', 'PRESIDENTIAL', 6, 750.00, 'AVAILABLE', NOW(), NOW()),
-- Hoteles 11 a 30 (1 Habitación cada uno para dar cobertura masiva)
(29, 11, '1101', 'SINGLE', 1, 70.00, 'AVAILABLE', NOW(), NOW()),
(30, 11, '1102', 'DOUBLE', 2, 110.00, 'AVAILABLE', NOW(), NOW()),
(31, 12, '1201', 'MATRIMONIAL', 2, 210.00, 'AVAILABLE', NOW(), NOW()),
(32, 12, '1202', 'SUITE', 4, 320.00, 'AVAILABLE', NOW(), NOW()),
(33, 13, '1301', 'DOUBLE', 2, 140.00, 'AVAILABLE', NOW(), NOW()),
(34, 14, '1401', 'SINGLE', 1, 85.00, 'AVAILABLE', NOW(), NOW()),
(35, 15, '1501', 'DOUBLE', 2, 95.00, 'AVAILABLE', NOW(), NOW()),
(36, 16, '1601', 'SUITE', 4, 260.00, 'AVAILABLE', NOW(), NOW()),
(37, 17, '1701', 'MATRIMONIAL', 2, 145.00, 'AVAILABLE', NOW(), NOW()),
(38, 18, '1801', 'PRESIDENTIAL', 6, 850.00, 'AVAILABLE', NOW(), NOW()),
(39, 19, '1901', 'DOUBLE', 2, 125.00, 'AVAILABLE', NOW(), NOW()),
(40, 20, '2001', 'SUITE', 4, 310.00, 'AVAILABLE', NOW(), NOW()),
(41, 21, '2101', 'SINGLE', 1, 75.00, 'AVAILABLE', NOW(), NOW()),
(42, 22, '2201', 'DOUBLE', 2, 105.00, 'AVAILABLE', NOW(), NOW()),
(43, 23, '2301', 'MATRIMONIAL', 2, 115.00, 'AVAILABLE', NOW(), NOW()),
(44, 24, '2401', 'SUITE', 4, 240.00, 'AVAILABLE', NOW(), NOW()),
(45, 25, '2501', 'DOUBLE', 2, 135.00, 'AVAILABLE', NOW(), NOW()),
(46, 26, '2601', 'SINGLE', 1, 95.00, 'AVAILABLE', NOW(), NOW()),
(47, 27, '2701', 'DOUBLE', 2, 110.00, 'AVAILABLE', NOW(), NOW()),
(48, 28, '2801', 'PRESIDENTIAL', 6, 950.00, 'AVAILABLE', NOW(), NOW()),
(49, 29, '2901', 'SUITE', 4, 290.00, 'AVAILABLE', NOW(), NOW()),
(50, 30, '3001', 'MATRIMONIAL', 2, 160.00, 'AVAILABLE', NOW(), NOW());