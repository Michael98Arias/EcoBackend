
-- Load initial data,generic password for all initial users->'Admin123@'
INSERT INTO user (fullname, username, email, password, role, state, created_at, updated_at)
VALUES
('Admin One', 'admin123', 'admin@admin.com', '$2a$10$kSA0H2rYlcc2H7NkRrsf1uAxsPl8rLCUo9TFtX69N/gKhfAmPok3i', 'ADMIN', 'ACTIVE', NOW(), NULL),
('Customer One', 'customer123', 'customer@customer.com', '$2a$10$kSA0H2rYlcc2H7NkRrsf1uAxsPl8rLCUo9TFtX69N/gKhfAmPok3i', 'CUSTOMER', 'ACTIVE', NOW(), NULL),
('Seller One', 'seller123', 'seller@seller.com', '$2a$10$kSA0H2rYlcc2H7NkRrsf1uAxsPl8rLCUo9TFtX69N/gKhfAmPok3i', 'SELLER', 'ACTIVE', NOW(), NULL);

INSERT INTO categories (name, state, created_at, updated_at) VALUES
('Bedrooms', 'ACTIVE', NOW(),  NULL),
('Offices', 'ACTIVE', NOW(),  NULL),
('Kitchens', 'ACTIVE', NOW(),  NULL),
('Decor & Accessories', 'ACTIVE', NOW(),  NULL);

INSERT INTO product (category_id, seller_id, name, description, amount, price, state, created_at, updated_at)
VALUES
(1, 3, 'Product 1', 'Description for Product 1', 10, 20.00, 'ACTIVE', NOW(), NULL),
(2, 3, 'Product 2', 'Description for Product 2', 15, 25.00, 'ACTIVE', NOW(), NULL),
(3, 3, 'Product 3', 'Description for Product 3', 20, 30.00, 'ACTIVE', NOW(), NULL),
(4, 3, 'Product 4', 'Description for Product 4', 25, 35.00, 'ACTIVE', NOW(), NULL),
(1, 3, 'Product 5', 'Description for Product 5', 30, 40.00, 'ACTIVE', NOW(), NULL),
(2, 3, 'Product 6', 'Description for Product 6', 35, 45.00, 'ACTIVE', NOW(), NULL),
(3, 3, 'Product 7', 'Description for Product 7', 40, 50.00, 'ACTIVE', NOW(), NULL),
(4, 3, 'Product 8', 'Description for Product 8', 45, 55.00, 'ACTIVE', NOW(), NULL),
(1, 3, 'Product 9', 'Description for Product 9', 50, 60.00, 'ACTIVE', NOW(), NULL),
(2, 3, 'Product 10', 'Description for Product 10', 55, 65.00, 'ACTIVE', NOW(), NULL);
