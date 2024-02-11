create table  customers
(
    customer_id  serial
        primary key,
    first_name   varchar(255),
    last_name    varchar(255),
    address      text,
    phone_number varchar(20)
);

create table  medicine_categories
(
    category_id serial
        primary key,
    name        varchar(255)
);

create table  medicines
(
    medicine_id  serial
        primary key,
    name         varchar(255),
    manufacturer varchar(255),
    dosage       varchar(50),
    form         varchar(50),
    price        numeric(10, 2),
    category_id  integer
        constraint medicines_medicine_categories_category_id_fk
            references medicine_categories
);

create table  orders
(
    order_id     serial
        primary key,
    customer_id  integer
        references customers,
    order_date   date,
    total_amount numeric(10, 2)
);


create table  order_details
(
    detail_id   serial
        primary key,
    order_id    integer
        references orders,
    medicine_id integer
        references medicines,
    quantity    integer,
    subtotal    numeric(10, 2)
);


