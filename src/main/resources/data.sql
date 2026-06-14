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

