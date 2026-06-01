-- ==========================================
-- 2. POPULATE HOTELS (5 Rows)
-- ==========================================

INSERT IGNORE INTO
    hotels (
        id,
        name,
        address,
        city,
        stars_rating,
        status,
        created_at,
        updated_at
    )
VALUES (
        1,
        'Oasis Grand Resort',
        'Blvd. Kukulcan Km 10',
        'Cancun',
        5,
        'ACTIVE',
        NOW(),
        NOW()
    ),
    (
        2,
        'Oasis Business Center',
        'Av. Reforma 222',
        'Mexico City',
        4,
        'ACTIVE',
        NOW(),
        NOW()
    ),
    (
        3,
        'Oasis Mountain Lodge',
        'Monte de los Pinos 100',
        'Valle de Bravo',
        4,
        'ACTIVE',
        NOW(),
        NOW()
    ),
    (
        4,
        'Oasis Pacific View',
        'Av. Malecon 101',
        'Puerto Vallarta',
        5,
        'ACTIVE',
        NOW(),
        NOW()
    ),
    (
        5,
        'Oasis Colonial',
        'Calle 60 #300',
        'Merida',
        3,
        'ACTIVE',
        NOW(),
        NOW()
    );

-- ==========================================
-- 3. POPULATE ROOMS (20 Rows)
-- Distributed across the first 5 Hotels
-- ==========================================

-- Rooms for Hotel ID 1 (Oasis Grand Resort - Cancun)
INSERT IGNORE INTO
    rooms (
        room_number,
        capacity,
        price_per_night,
        room_type,
        room_status,
        hotel_id,
        created_at,
        updated_at
    )
VALUES (
        '101A',
        2,
        150.00,
        'DOUBLE',
        'AVAILABLE',
        1,
        NOW(),
        NOW()
    ),
    (
        '102B',
        4,
        300.50,
        'SUITE',
        'AVAILABLE',
        1,
        NOW(),
        NOW()
    ),
    (
        '201A',
        2,
        850.00,
        'PRESIDENTIAL',
        'AVAILABLE',
        1,
        NOW(),
        NOW()
    ),
    (
        '202B',
        2,
        150.00,
        'DOUBLE',
        'OCCUPIED',
        1,
        NOW(),
        NOW()
    );

-- Rooms for Hotel ID 2 (Oasis Business Center - Mexico City)
INSERT IGNORE INTO
    rooms (
        room_number,
        capacity,
        price_per_night,
        room_type,
        room_status,
        hotel_id,
        created_at,
        updated_at
    )
VALUES (
        '101',
        1,
        90.00,
        'SINGLE',
        'AVAILABLE',
        2,
        NOW(),
        NOW()
    ),
    (
        '102',
        2,
        120.00,
        'DOUBLE',
        'MAINTENANCE',
        2,
        NOW(),
        NOW()
    ),
    (
        '103',
        1,
        90.00,
        'SINGLE',
        'AVAILABLE',
        2,
        NOW(),
        NOW()
    ),
    (
        'PENTHOUSE',
        4,
        500.00,
        'SUITE',
        'AVAILABLE',
        2,
        NOW(),
        NOW()
    );

-- Rooms for Hotel ID 3 (Oasis Mountain Lodge - Valle de Bravo)
INSERT IGNORE INTO
    rooms (
        room_number,
        capacity,
        price_per_night,
        room_type,
        room_status,
        hotel_id,
        created_at,
        updated_at
    )
VALUES (
        'CAB-01',
        2,
        200.00,
        'MATRIMONIAL',
        'AVAILABLE',
        3,
        NOW(),
        NOW()
    ),
    (
        'CAB-02',
        4,
        250.00,
        'SUITE',
        'AVAILABLE',
        3,
        NOW(),
        NOW()
    ),
    (
        'CAB-03',
        2,
        200.00,
        'MATRIMONIAL',
        'OCCUPIED',
        3,
        NOW(),
        NOW()
    ),
    (
        'CAB-04',
        6,
        350.00,
        'SUITE',
        'MAINTENANCE',
        3,
        NOW(),
        NOW()
    );

-- Rooms for Hotel ID 4 (Oasis Pacific View - Puerto Vallarta)
INSERT IGNORE INTO
    rooms (
        room_number,
        capacity,
        price_per_night,
        room_type,
        room_status,
        hotel_id,
        created_at,
        updated_at
    )
VALUES (
        'VIEW-1',
        2,
        180.00,
        'MATRIMONIAL',
        'AVAILABLE',
        4,
        NOW(),
        NOW()
    ),
    (
        'VIEW-2',
        2,
        180.00,
        'MATRIMONIAL',
        'AVAILABLE',
        4,
        NOW(),
        NOW()
    ),
    (
        'VIEW-3',
        4,
        280.00,
        'DOUBLE',
        'OCCUPIED',
        4,
        NOW(),
        NOW()
    ),
    (
        'OCEAN-1',
        2,
        400.00,
        'SUITE',
        'AVAILABLE',
        4,
        NOW(),
        NOW()
    );

-- Rooms for Hotel ID 5 (Oasis Colonial - Merida)
INSERT IGNORE INTO
    rooms (
        room_number,
        capacity,
        price_per_night,
        room_type,
        room_status,
        hotel_id,
        created_at,
        updated_at
    )
VALUES (
        'COL-101',
        1,
        100.00,
        'SINGLE',
        'AVAILABLE',
        5,
        NOW(),
        NOW()
    ),
    (
        'COL-102',
        2,
        130.00,
        'DOUBLE',
        'AVAILABLE',
        5,
        NOW(),
        NOW()
    ),
    (
        'COL-201',
        2,
        150.00,
        'MATRIMONIAL',
        'AVAILABLE',
        5,
        NOW(),
        NOW()
    ),
    (
        'KING-01',
        2,
        600.00,
        'PRESIDENTIAL',
        'OUT_OF_SERVICE',
        5,
        NOW(),
        NOW()
    );
-- ==========================================
-- 4. ADDITIONAL 20 ROOMS
-- ==========================================

-- Extra Rooms for Hotel 1
INSERT IGNORE INTO
    rooms (
        room_number,
        capacity,
        price_per_night,
        room_type,
        room_status,
        hotel_id,
        created_at,
        updated_at
    )
VALUES (
        '103C',
        2,
        160.00,
        'DOUBLE',
        'AVAILABLE',
        1,
        NOW(),
        NOW()
    ),
    (
        '104D',
        2,
        160.00,
        'DOUBLE',
        'OCCUPIED',
        1,
        NOW(),
        NOW()
    ),
    (
        '203C',
        4,
        320.00,
        'SUITE',
        'MAINTENANCE',
        1,
        NOW(),
        NOW()
    ),
    (
        '204D',
        4,
        320.00,
        'SUITE',
        'AVAILABLE',
        1,
        NOW(),
        NOW()
    );

-- Extra Rooms for Hotel 2
INSERT IGNORE INTO
    rooms (
        room_number,
        capacity,
        price_per_night,
        room_type,
        room_status,
        hotel_id,
        created_at,
        updated_at
    )
VALUES (
        '104',
        1,
        95.00,
        'SINGLE',
        'OCCUPIED',
        2,
        NOW(),
        NOW()
    ),
    (
        '105',
        2,
        125.00,
        'DOUBLE',
        'AVAILABLE',
        2,
        NOW(),
        NOW()
    ),
    (
        '106',
        2,
        125.00,
        'MATRIMONIAL',
        'AVAILABLE',
        2,
        NOW(),
        NOW()
    ),
    (
        'PENTHOUSE-2',
        4,
        550.00,
        'PRESIDENTIAL',
        'OUT_OF_SERVICE',
        2,
        NOW(),
        NOW()
    );

-- Extra Rooms for Hotel 3
INSERT IGNORE INTO
    rooms (
        room_number,
        capacity,
        price_per_night,
        room_type,
        room_status,
        hotel_id,
        created_at,
        updated_at
    )
VALUES (
        'CAB-05',
        2,
        210.00,
        'MATRIMONIAL',
        'AVAILABLE',
        3,
        NOW(),
        NOW()
    ),
    (
        'CAB-06',
        4,
        260.00,
        'SUITE',
        'OCCUPIED',
        3,
        NOW(),
        NOW()
    ),
    (
        'CAB-07',
        2,
        210.00,
        'DOUBLE',
        'AVAILABLE',
        3,
        NOW(),
        NOW()
    ),
    (
        'CAB-08',
        6,
        360.00,
        'PRESIDENTIAL',
        'AVAILABLE',
        3,
        NOW(),
        NOW()
    );

-- Extra Rooms for Hotel 4
INSERT IGNORE INTO
    rooms (
        room_number,
        capacity,
        price_per_night,
        room_type,
        room_status,
        hotel_id,
        created_at,
        updated_at
    )
VALUES (
        'VIEW-4',
        2,
        190.00,
        'MATRIMONIAL',
        'AVAILABLE',
        4,
        NOW(),
        NOW()
    ),
    (
        'VIEW-5',
        2,
        190.00,
        'DOUBLE',
        'MAINTENANCE',
        4,
        NOW(),
        NOW()
    ),
    (
        'VIEW-6',
        4,
        290.00,
        'SUITE',
        'AVAILABLE',
        4,
        NOW(),
        NOW()
    ),
    (
        'OCEAN-2',
        2,
        450.00,
        'PRESIDENTIAL',
        'OCCUPIED',
        4,
        NOW(),
        NOW()
    );

-- Extra Rooms for Hotel 5
INSERT IGNORE INTO
    rooms (
        room_number,
        capacity,
        price_per_night,
        room_type,
        room_status,
        hotel_id,
        created_at,
        updated_at
    )
VALUES (
        'COL-103',
        1,
        110.00,
        'SINGLE',
        'AVAILABLE',
        5,
        NOW(),
        NOW()
    ),
    (
        'COL-104',
        2,
        140.00,
        'DOUBLE',
        'OCCUPIED',
        5,
        NOW(),
        NOW()
    ),
    (
        'COL-202',
        2,
        160.00,
        'MATRIMONIAL',
        'AVAILABLE',
        5,
        NOW(),
        NOW()
    ),
    (
        'KING-02',
        2,
        650.00,
        'PRESIDENTIAL',
        'AVAILABLE',
        5,
        NOW(),
        NOW()
    );