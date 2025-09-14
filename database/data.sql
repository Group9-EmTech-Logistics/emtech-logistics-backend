-- Insert sample inventory items
INSERT INTO inventory_items (name, sku, category, quantity, status, origin) VALUES
('Samsung Galaxy S24 Ultra', 'SGS24U-001', 'Electronics', 15, 'Low Stock', 'South Korea'),
('Premium Jasmine Rice - 50kg', 'PJR50-002', 'Food', 85, 'Normal', 'Thailand'),
('Golden Harvest Cooking Oil - 5L', 'GHCO5-003', 'Food', 32, 'Expiring Soon', 'Malaysia'),
('iPhone 15 Pro Max', 'IPH15PM-004', 'Electronics', 8, 'Low Stock', 'China'),
('MacBook Pro M3 - 16"', 'MBP16M3-005', 'Electronics', 0, 'Out of Stock', 'China'),
('Sony Bravia 65" 4K TV', 'SONY65-006', 'Electronics', 12, 'Normal', 'Japan'),
('Nestle Milo 2kg', 'MILO2-007', 'Food', 45, 'Normal', 'Ghana'),
('Toyota Corolla Parts Kit', 'TCOR-008', 'Automotive', 7, 'Low Stock', 'Japan'),
('Pfizer COVID-19 Vaccine', 'PFVC-009', 'Medical', 1200, 'Normal', 'USA'),
('Adidas Ultraboost', 'ADUB-010', 'Footwear', 25, 'Normal', 'Vietnam');

-- Insert sample customers
INSERT INTO customers (name, business_type, phone, total_orders, last_order_date) VALUES
('Kwame Electronics Enterprise', 'Wholesale', '+233 20 123 4567', 25, '2025-08-10 14:30:00'),
('Abena Grocery & Supermarket', 'Retail', '+233 24 567 8901', 42, '2025-08-09 10:15:00'),
('TechHub Ghana Limited', 'Wholesale', '+233 50 111 2233', 18, '2025-08-08 16:45:00'),
('Nana Yaa Trading Company', 'Wholesale', '+233 26 888 9999', 32, '2025-08-07 09:20:00'),
('Accra Medical Supplies', 'Healthcare', '+233 27 444 5555', 15, '2025-08-06 11:30:00'),
('Kumasi Auto Parts', 'Automotive', '+233 54 777 8888', 28, '2025-08-05 13:15:00');

-- Insert sample sales transactions
INSERT INTO sales_transactions (transaction_id, customer_id, items, total_amount, transaction_type, transaction_date) VALUES
('TXN-2025-001', 1, 'Samsung Galaxy S24 Ultra x2', 6400.00, 'Wholesale', '2025-08-10 14:30:00'),
('TXN-2025-002', 2, 'Premium Jasmine Rice 50kg x5', 1100.00, 'Wholesale', '2025-08-10 10:15:00'),
('TXN-2025-003', 3, 'iPhone 15 Pro Max x1', 5200.00, 'Wholesale', '2025-08-08 16:45:00'),
('TXN-2025-004', 4, 'Sony Bravia 65" 4K TV x3', 7500.00, 'Wholesale', '2025-08-07 09:20:00'),
('TXN-2025-005', 5, 'Pfizer COVID-19 Vaccine x100', 5000.00, 'Healthcare', '2025-08-06 11:30:00'),
('TXN-2025-006', 6, 'Toyota Corolla Parts Kit x2', 1200.00, 'Automotive', '2025-08-05 13:15:00');

-- Insert sample shipments
INSERT INTO shipments (tracking_id, origin, destination, status, eta, progress, current_location) VALUES
('SH-2025-089', 'Shenzhen, China', 'Accra, Ghana', 'In Transit', '2025-08-24 14:30:00', 75, 'Indian Ocean'),
('SH-2025-087', 'Bangkok, Thailand', 'Tema, Ghana', 'At Port', '2025-08-23 09:00:00', 90, 'Port of Tema'),
('SH-2025-091', 'Tokyo, Japan', 'Accra, Ghana', 'In Transit', '2025-08-25 16:45:00', 60, 'Red Sea'),
('SH-2025-092', 'Hamburg, Germany', 'Tema, Ghana', 'Customs Clearance', '2025-08-22 12:00:00', 95, 'Tema Port Customs'),
('SH-2025-093', 'New York, USA', 'Kumasi, Ghana', 'Out for Delivery', '2025-08-21 14:00:00', 98, 'Kumasi Distribution Center');