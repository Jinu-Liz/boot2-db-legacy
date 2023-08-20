create table item
(
    dtype          varchar(31)  not null,
    item_id        bigint auto_increment
        primary key,
    name           varchar(255) null,
    price          int          not null,
    stock_quantity int          not null,
    author         varchar(255) null,
    isbn           varchar(255) null
);

INSERT INTO item (dtype, item_id, name, price, stock_quantity, author, isbn) VALUES ('B', 1, 'JPA1 BOOK', 10000, 99, null, null);
INSERT INTO item (dtype, item_id, name, price, stock_quantity, author, isbn) VALUES ('B', 2, 'JPA2 BOOK', 20000, 98, null, null);
INSERT INTO item (dtype, item_id, name, price, stock_quantity, author, isbn) VALUES ('B', 3, 'SPRING1 BOOK', 20000, 197, null, null);
INSERT INTO item (dtype, item_id, name, price, stock_quantity, author, isbn) VALUES ('B', 4, 'SPRING2 BOOK', 40000, 296, null, null);