INSERT INTO customers (first_name, last_name, address, phone_number)
VALUES ('John', 'Doe', '123 Main St', '+77471234567'),
       ('Alice', 'Smith', '456 Oak Ave', '+77021234567'),
       ('Bob', 'Johnson', '789 Pine Ln', '+77051234567');


INSERT INTO medicine_categories (name)
VALUES ('Pain Relief'),
       ('Antibiotics'),
       ('Vitamins'),
       ('Allergy'),
       ('Cough and Cold');

INSERT INTO medicines (name, manufacturer, dosage, form, price, category_id)
VALUES ('Ibuprofen', 'ABC Pharma', '200mg', 'Tablet', 10.99, 1),
       ('Amoxicillin', 'XYZ Pharma', '500mg', 'Capsule', 15.50, 2),
       ('Vitamin C', 'HealthCo', '1000mg', 'Tablet', 7.99, 3),
       ('Loratadine', 'MediCare', '10mg', 'Tablet', 8.75, 4),
       ('Cough Syrup', 'PharmaCare', '—', 'Liquid', 12.25, 5);

INSERT INTO orders (customer_id, order_date, total_amount)
VALUES (1, '2024-02-11', 36.98),
       (2, '2024-02-12', 45.25),
       (3, '2024-02-13', 22.50);

INSERT INTO order_details (order_id, medicine_id, quantity, subtotal)
VALUES (1, 1, 2, 21.98),
       (1, 3, 1, 7.99),
       (2, 2, 3, 46.50),
       (3, 4, 1, 8.75),
       (3, 5, 2, 24.50);
