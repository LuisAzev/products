
INSERT INTO destiny (id, code, name) VALUES(1, 1, 'Mercadona España');
INSERT INTO destiny (id, code, name) VALUES(2, 2, 'Mercadona España');
INSERT INTO destiny (id, code, name) VALUES(3, 3, 'Mercadona España');
INSERT INTO destiny (id, code, name) VALUES(4, 4, 'Mercadona España');
INSERT INTO destiny (id, code, name) VALUES(5, 5, 'Mercadona España');
INSERT INTO destiny (id, code, name) VALUES(6, 6, 'Mercadona Portugal');
INSERT INTO destiny (id, code, name) VALUES(7, 8, 'Almacenes');
INSERT INTO destiny (id, code, name) VALUES(8, 9, 'Oficinas Mercadona');
INSERT INTO destiny (id, code, name) VALUES(9, 0, 'Colmenas');

INSERT INTO provider (id, code, name, direction) VALUES(1, 8437008, 'Hacendado', 'Valencia');
INSERT INTO provider (id, code, name, direction) VALUES(2, 1234567, 'Tarradellas', 'Madrid');
INSERT INTO provider (id, code, name, direction) VALUES(3, 7654321, 'CocaCola Company', 'Barcelona');
INSERT INTO provider (id, code, name, direction) VALUES(4, 5678987, 'DISPAPALI', 'Barcelona');
INSERT INTO provider (id, code, name, direction) VALUES(5, 9876543, 'Gillette', 'Madrid');

INSERT INTO product(id, code, name, destiny_id, provider_id) VALUES(1, 45612, 'Galletas', 1, 1);
INSERT INTO product(id, code, name, destiny_id, provider_id) VALUES(2, 32145, 'Pizza', 6, 2);
INSERT INTO product(id, code, name, destiny_id, provider_id) VALUES(3, 98742, 'CocaCola', 9, 3);
INSERT INTO product(id, code, name, destiny_id, provider_id) VALUES(4, 45879, 'Hojas A4', 8, 4);
INSERT INTO product(id, code, name, destiny_id, provider_id) VALUES(5, 39154, 'Cuchillas', 7, 5);

ALTER TABLE product ALTER COLUMN id RESTART WITH 6;
ALTER TABLE provider ALTER COLUMN id RESTART WITH 6;
ALTER TABLE destiny ALTER COLUMN id RESTART WITH 10;
