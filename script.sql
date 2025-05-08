INSERT INTO role
VALUES (1, 'ROLE_USER'),
       (2, 'ROLE_MANAGER'),
       (3, 'ROLE_ADMIN');

INSERT INTO user (id, name, email, password, bun)
VALUES (1, 'Inna', 'inna@gmail.com', '$2a$10$R8bUoJiiUJ9AbKBYgRDvX.GSHuPAoOwl5whV6WyxgmzhJerULOMp.', 0),
       (2, 'Ivan', 'ivan@gmail.com', '$2a$10$R8bUoJiiUJ9AbKBYgRDvX.GSHuPAoOwl5whV6WyxgmzhJerULOMp.', 0),
       (3, 'Anna', 'anna@gmail.com', '$2a$10$R8bUoJiiUJ9AbKBYgRDvX.GSHuPAoOwl5whV6WyxgmzhJerULOMp.', 0),
       (4, 'Sergey', 'sergey@gmail.com', '$2a$10$R8bUoJiiUJ9AbKBYgRDvX.GSHuPAoOwl5whV6WyxgmzhJerULOMp.', 0),
       (5, 'Maria', 'maria@gmail.com', '$2a$10$R8bUoJiiUJ9AbKBYgRDvX.GSHuPAoOwl5whV6WyxgmzhJerULOMp.', 0),
       (6, 'Oleg', 'oleg@gmail.com', '$2a$10$R8bUoJiiUJ9AbKBYgRDvX.GSHuPAoOwl5whV6WyxgmzhJerULOMp.', 0),
       (7, 'Elena', 'elena@gmail.com', '$2a$10$R8bUoJiiUJ9AbKBYgRDvX.GSHuPAoOwl5whV6WyxgmzhJerULOMp.', 0),
       (8, 'Dmitry', 'dmitry@gmail.com', '$2a$10$R8bUoJiiUJ9AbKBYgRDvX.GSHuPAoOwl5whV6WyxgmzhJerULOMp.', 0),
       (9, 'Svetlana', 'svetlana@gmail.com', '$2a$10$R8bUoJiiUJ9AbKBYgRDvX.GSHuPAoOwl5whV6WyxgmzhJerULOMp.', 0),
       (10, 'Alexey', 'alexey@gmail.com', '$2a$10$R8bUoJiiUJ9AbKBYgRDvX.GSHuPAoOwl5whV6WyxgmzhJerULOMp.', 0);

INSERT INTO user_role
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (2, 1),
       (2, 2),
       (3, 1),
       (3, 3),
       (4, 1),
       (5, 1),
       (6, 1),
       (7, 1),
       (8, 1),
       (9, 1),
       (10, 1);

INSERT INTO product (id, name, description, quantity, price)
VALUES
       (5, 'Logitech Keyboard', 'Mechanical keyboard with backlighting', 30, 7000),
       (6, 'Razer Mouse', 'Gaming mouse with 12 programmable buttons', 40, 8000),
       (7, 'Sony Headphones', 'Wireless headphones with noise cancellation', 35, 15000),
       (8, 'Samsung External SSD', '1TB solid-state drive', 12, 14000),
       (9, 'Logitech Webcam', 'Full HD camera for video calls', 18, 9000),
       (10, 'HP Printer', 'Laser printer for office use', 10, 22000),
       (11, 'Kingston Flash Drive', 'USB flash drive 128GB', 50, 2000),
       (12, 'TP-Link Wi-Fi Router', 'Dual-band router', 22, 5500),
       (13, 'PlayStation 5 Gaming Console', 'Next-gen gaming console', 8, 75000),
       (14, 'Samsung TV', '4K UHD Smart TV 55 inches', 5, 85000),
       (15, 'Epson Projector', 'Full HD projector for home and office', 7, 65000),
       (16, 'Baseus Charging Station', 'Multi-functional charging device', 25, 4000),
       (17, 'Apple Watch Smartwatch', 'Smartwatch with health monitoring', 15, 45000),
       (18, 'Kindle eBook Reader', 'E-reader with backlight and touch screen', 20, 12000),
       (19, 'Xbox Gamepad', 'Wireless controller', 30, 7000),
       (20, 'NVIDIA RTX 4080 Graphics Card', 'Top-tier gaming graphics card', 5, 160000);

INSERT INTO customers (id, name, email, phone, address)
VALUES (1, 'Ivan Petrov', 'ivan.petrov@example.com', '+79161234567', 'Moscow, Lenin St., 10'),
       (2, 'Elena Smirnova', 'elena.smirnova@example.com', '+79263456789', 'Saint Petersburg, Nevsky Ave., 20'),
       (3, 'Alexey Kuznetsov', 'alexey.kuznetsov@example.com', '+79371234567', 'Yekaterinburg, Mira St., 5'),
       (4, 'Maria Vasilyeva', 'maria.vasilyeva@example.com', '+79451239876', 'Kazan, Baumana St., 15'),
       (5, 'Dmitry Ivanov', 'dmitry.ivanov@example.com', '+79567891234', 'Novosibirsk, Soviet St., 7'),
       (6, 'Olga Sokolova', 'olga.sokolova@example.com', '+79678912345', 'Chelyabinsk, Pobedy St., 11'),
       (7, 'Sergey Morozov', 'sergey.morozov@example.com', '+79781235678', 'Krasnoyarsk, Lenin Ave., 8'),
       (8, 'Anna Kozlova', 'anna.kozlova@example.com', '+79892345678', 'Samara, Gagarina St., 12'),
       (9, 'Pavel Nikolaev', 'pavel.nikolaev@example.com', '+79903456789', 'Rostov-on-Don, Taganrogskaya St., 3'),
       (10, 'Ekaterina Orlova', 'ekaterina.orlova@example.com', '+79012345678', 'Voronezh, Karl Marx St., 9');

INSERT INTO orders (id, order_date, status, customer_id, user_id, price)
VALUES (1, '2024-01-05 09:20:00', 'PENDING', 1, 1, 85000),
       (2, '2024-01-18 14:10:00', 'PROCESSING', 2, 2, 160000),
       (3, '2024-01-29 17:45:00', 'DELIVERED', 3, 1, 75000),

       (4, '2024-02-07 12:30:00', 'CANCELED', 4, 2, 30000),
       (5, '2024-02-22 18:15:00', 'PENDING', 5, 1, 50000),

       (6, '2024-03-03 09:40:00', 'PROCESSING', 6, 2, 7000),
       (7, '2024-03-15 15:20:00', 'SHIPPED', 7, 1, 8000),
       (8, '2024-03-20 11:10:00', 'DELIVERED', 8, 2, 15000),
       (9, '2024-03-30 16:55:00', 'PENDING', 9, 1, 4000),

       (10, '2024-04-12 10:05:00', 'PENDING', 10, 2, 45000),

       (11, '2024-05-02 14:50:00', 'PROCESSING', 3, 1, 12000),
       (12, '2024-05-09 16:35:00', 'SHIPPED', 5, 2, 60000),
       (13, '2024-05-18 09:20:00', 'DELIVERED', 7, 1, 32000),
       (14, '2024-05-24 12:40:00', 'CANCELED', 8, 2, 28000),
       (15, '2024-05-30 13:10:00', 'PENDING', 9, 1, 9000),

       (16, '2024-06-05 11:55:00', 'PROCESSING', 10, 2, 55000),
       (17, '2024-06-15 17:25:00', 'SHIPPED', 1, 1, 45000),
       (18, '2024-06-27 15:45:00', 'DELIVERED', 2, 2, 25000),

       (19, '2024-07-03 13:30:00', 'CANCELED', 4, 1, 74000),
       (20, '2024-07-10 16:50:00', 'PENDING', 6, 2, 38000);

INSERT INTO order_item (id, order_id, product_id, quantity)
VALUES (1, 1, 1, 2),
       (2, 1, 2, 1),

       (3, 2, 5, 3),

       (4, 3, 6, 1),
       (5, 3, 1, 4),

       (6, 4, 8, 2),

       (7, 5, 7, 1),
       (8, 5, 13, 2),

       (9, 6, 12, 3),
       (10, 7, 16, 1),
       (11, 7, 1, 2),

       (12, 8, 11, 5),

       (13, 9, 8, 2),
       (14, 9, 7, 1),
       (15, 10, 3, 3),
       (16, 10, 2, 2),

       (17, 11, 17, 4),

       (18, 12, 18, 1),
       (19, 12, 12, 2),
       (20, 13, 1, 3),
       (22, 14, 14, 1),
       (23, 14, 9, 2),
       (24, 15, 5, 1),
       (25, 15, 10, 2),
       (26, 16, 16, 3),
       (27, 16, 12, 1),
       (28, 17, 2, 3),
       (29, 18, 4, 2),
       (30, 18, 3, 2),
       (31, 19, 19, 1),
       (32, 20, 20, 2);

