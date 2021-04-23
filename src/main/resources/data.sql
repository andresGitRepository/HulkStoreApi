INSERT INTO User (user_id,user_email,user_password,user_enabled,user_name,user_last_name,user_age) VALUES(1,'andres@gmail.com','$2a$05$nUzCt3HxE5Cbmk1i/BDWJO8RapQekRfOHs9SpzoK0RWt3WzHBsQeS',1,'Andres','Gonzalez',37);
INSERT INTO User (user_id,user_email,user_password,user_enabled,user_name,user_last_name,user_age) VALUES(2,'guest@gmail.com','$2a$05$nUzCt3HxE5Cbmk1i/BDWJO8RapQekRfOHs9SpzoK0RWt3WzHBsQeS',1,'Invitado','',0);

INSERT INTO Authority (authority_id,authority) VALUES (1, 'ROLE_ADMIN');
INSERT INTO Authority (authority_id,authority) VALUES (2, 'ROLE_USER');
INSERT INTO Authority (authority_id,authority) VALUES (3, 'ROLE_GUEST');

INSERT INTO user_authority (user_id, authority_id) VALUES (1,1);
INSERT INTO user_authority (user_id, authority_id) VALUES (1,2);
INSERT INTO user_authority (user_id, authority_id) VALUES (2,3);

INSERT INTO Product (product_id,product_description,product_stock,product_price,product_user_registry,product_date_registry) VALUES(1001,'Puño Hulk',10,1750,1,'20210101');
INSERT INTO Product (product_id,product_description,product_stock,product_price,product_user_registry,product_date_registry) VALUES(1002,'Escudo Cap',10,1950,1,'20210101');
INSERT INTO Product (product_id,product_description,product_stock,product_price,product_user_registry,product_date_registry) VALUES(1003,'Casco Iroman',10,2050,1,'20210101');
INSERT INTO Product (product_id,product_description,product_stock,product_price,product_user_registry,product_date_registry) VALUES(1004,'Mjolnir Thor',10,2000,1,'20210101');
INSERT INTO Product (product_id,product_description,product_stock,product_price,product_user_registry,product_date_registry) VALUES(1005,'Arco Hawkeye',10,1700,1,'20210101');
INSERT INTO Product (product_id,product_description,product_stock,product_price,product_user_registry,product_date_registry) VALUES(1006,'Cetro Loky',10,1770,1,'20210101');
INSERT INTO Product (product_id,product_description,product_stock,product_price,product_user_registry,product_date_registry) VALUES(1007,'Widow Bites Black Widow',10,1850,1,'20210101');
INSERT INTO Product (product_id,product_description,product_stock,product_price,product_user_registry,product_date_registry) VALUES(1008,'Guanteleta Thanos',10,1990,1,'20210101');
INSERT INTO Product (product_id,product_description,product_stock,product_price,product_user_registry,product_date_registry) VALUES(1009,'Mascara SpiderMan',10,1710,1,'20210101');
INSERT INTO Product (product_id,product_description,product_stock,product_price,product_user_registry,product_date_registry) VALUES(1010,'Garras Wolverine',10,1870,1,'20210101');
INSERT INTO Product (product_id,product_description,product_stock,product_price,product_user_registry,product_date_registry) VALUES(1011,'Brazo Winter Soldier',10,1750,1,'20210101');
INSERT INTO Product (product_id,product_description,product_stock,product_price,product_user_registry,product_date_registry) VALUES(1012,'Rompe Tormeta Thor',10,2990,1,'20210101');
INSERT INTO Product (product_id,product_description,product_stock,product_price,product_user_registry,product_date_registry) VALUES(1013,'Anillos del Mandarin',10,1540,1,'20210101');
INSERT INTO Product (product_id,product_description,product_stock,product_price,product_user_registry,product_date_registry) VALUES(2001,'Capa Superman',10,1650,1,'20210101');
INSERT INTO Product (product_id,product_description,product_stock,product_price,product_user_registry,product_date_registry) VALUES(2002,'Escudo Wonder Woman',10,1550,1,'20210101');
INSERT INTO Product (product_id,product_description,product_stock,product_price,product_user_registry,product_date_registry) VALUES(2003,'Lazo de Hestia Wonder Woman',10,1050,1,'20210101');
INSERT INTO Product (product_id,product_description,product_stock,product_price,product_user_registry,product_date_registry) VALUES(2004,'Casco Batman',10,1500,1,'20210101');
INSERT INTO Product (product_id,product_description,product_stock,product_price,product_user_registry,product_date_registry) VALUES(2005,'Arma de frio Flash',10,1660,1,'20210101');
INSERT INTO Product (product_id,product_description,product_stock,product_price,product_user_registry,product_date_registry) VALUES(2006,'Espada de Atenea Wonder Woman',10,1560,1,'20210101');
INSERT INTO Product (product_id,product_description,product_stock,product_price,product_user_registry,product_date_registry) VALUES(2007,'Pistola Dorada Joker',10,1810,1,'20210101');
INSERT INTO Product (product_id,product_description,product_stock,product_price,product_user_registry,product_date_registry) VALUES(2008,'Tridente de Atlan Aquaman',10,1820,1,'20210101');
INSERT INTO Product (product_id,product_description,product_stock,product_price,product_user_registry,product_date_registry) VALUES(2009,'Ojo de Cyborg',10,1720,1,'20210101');
INSERT INTO Product (product_id,product_description,product_stock,product_price,product_user_registry,product_date_registry) VALUES(2010,'Mazo Harley Quinn',10,1730,1,'20210101');
INSERT INTO Product (product_id,product_description,product_stock,product_price,product_user_registry,product_date_registry) VALUES(2011,'Batarang Batman',10,1740,1,'20210101');
INSERT INTO Product (product_id,product_description,product_stock,product_price,product_user_registry,product_date_registry) VALUES(2012,'Mata Dioses Wonder Woman',10,1800,1,'20210101');
INSERT INTO Product (product_id,product_description,product_stock,product_price,product_user_registry,product_date_registry) VALUES(2013,'Anillo Linterna Verde',10,1490,1,'20210101');
INSERT INTO Product (product_id,product_description,product_stock,product_price,product_user_registry,product_date_registry) VALUES(3001,'Remera Superman',10,59,1,'20210101');
INSERT INTO Product (product_id,product_description,product_stock,product_price,product_user_registry,product_date_registry) VALUES(3002,'Remera Wonder Woman',10,58,1,'20210101');
INSERT INTO Product (product_id,product_description,product_stock,product_price,product_user_registry,product_date_registry) VALUES(3003,'Remera Capitan America',10,59,1,'20210101');
INSERT INTO Product (product_id,product_description,product_stock,product_price,product_user_registry,product_date_registry) VALUES(3004,'Remera Batman',10,58,1,'20210101');
INSERT INTO Product (product_id,product_description,product_stock,product_price,product_user_registry,product_date_registry) VALUES(3005,'Remera Iron Man',10,59,1,'20210101');
INSERT INTO Product (product_id,product_description,product_stock,product_price,product_user_registry,product_date_registry) VALUES(3006,'Remera Thor',10,58,1,'20210101');
INSERT INTO Product (product_id,product_description,product_stock,product_price,product_user_registry,product_date_registry) VALUES(3007,'Remera Joker',10,57,1,'20210101');
INSERT INTO Product (product_id,product_description,product_stock,product_price,product_user_registry,product_date_registry) VALUES(3008,'Remera Aquaman',10,58,1,'20210101');
INSERT INTO Product (product_id,product_description,product_stock,product_price,product_user_registry,product_date_registry) VALUES(3009,'Remera Black Widow',10,57,1,'20210101');
INSERT INTO Product (product_id,product_description,product_stock,product_price,product_user_registry,product_date_registry) VALUES(3010,'Remera SpiderMan',10,57,1,'20210101');
INSERT INTO Product (product_id,product_description,product_stock,product_price,product_user_registry,product_date_registry) VALUES(3011,'Remera Black Panter',10,58,1,'20210101');
INSERT INTO Product (product_id,product_description,product_stock,product_price,product_user_registry,product_date_registry) VALUES(3012,'Remera Hulk',10,58,1,'20210101');
INSERT INTO Product (product_id,product_description,product_stock,product_price,product_user_registry,product_date_registry) VALUES(3013,'Remera Wolverine',10,58,1,'20210101');

INSERT INTO Kardex (kardex_id,product_id,kardex_date,kardex_description,kardex_count) VALUES (1,1001,'20210101','STOCK INICIAL',10);
INSERT INTO Kardex (kardex_id,product_id,kardex_date,kardex_description,kardex_count) VALUES (2,1002,'20210101','STOCK INICIAL',10);
INSERT INTO Kardex (kardex_id,product_id,kardex_date,kardex_description,kardex_count) VALUES (3,1003,'20210101','STOCK INICIAL',10);
INSERT INTO Kardex (kardex_id,product_id,kardex_date,kardex_description,kardex_count) VALUES (4,1004,'20210101','STOCK INICIAL',10);
INSERT INTO Kardex (kardex_id,product_id,kardex_date,kardex_description,kardex_count) VALUES (5,1005,'20210101','STOCK INICIAL',10);
INSERT INTO Kardex (kardex_id,product_id,kardex_date,kardex_description,kardex_count) VALUES (6,1006,'20210101','STOCK INICIAL',10);
INSERT INTO Kardex (kardex_id,product_id,kardex_date,kardex_description,kardex_count) VALUES (7,1007,'20210101','STOCK INICIAL',10);
INSERT INTO Kardex (kardex_id,product_id,kardex_date,kardex_description,kardex_count) VALUES (8,1008,'20210101','STOCK INICIAL',10);
INSERT INTO Kardex (kardex_id,product_id,kardex_date,kardex_description,kardex_count) VALUES (9,1009,'20210101','STOCK INICIAL',10);
INSERT INTO Kardex (kardex_id,product_id,kardex_date,kardex_description,kardex_count) VALUES (10,1010,'20210101','STOCK INICIAL',10);
INSERT INTO Kardex (kardex_id,product_id,kardex_date,kardex_description,kardex_count) VALUES (11,1011,'20210101','STOCK INICIAL',10);
INSERT INTO Kardex (kardex_id,product_id,kardex_date,kardex_description,kardex_count) VALUES (12,1012,'20210101','STOCK INICIAL',10);
INSERT INTO Kardex (kardex_id,product_id,kardex_date,kardex_description,kardex_count) VALUES (13,1013,'20210101','STOCK INICIAL',10);
INSERT INTO Kardex (kardex_id,product_id,kardex_date,kardex_description,kardex_count) VALUES (14,2001,'20210101','STOCK INICIAL',10);
INSERT INTO Kardex (kardex_id,product_id,kardex_date,kardex_description,kardex_count) VALUES (15,2002,'20210101','STOCK INICIAL',10);
INSERT INTO Kardex (kardex_id,product_id,kardex_date,kardex_description,kardex_count) VALUES (16,2003,'20210101','STOCK INICIAL',10);
INSERT INTO Kardex (kardex_id,product_id,kardex_date,kardex_description,kardex_count) VALUES (17,2004,'20210101','STOCK INICIAL',10);
INSERT INTO Kardex (kardex_id,product_id,kardex_date,kardex_description,kardex_count) VALUES (18,2005,'20210101','STOCK INICIAL',10);
INSERT INTO Kardex (kardex_id,product_id,kardex_date,kardex_description,kardex_count) VALUES (19,2006,'20210101','STOCK INICIAL',10);
INSERT INTO Kardex (kardex_id,product_id,kardex_date,kardex_description,kardex_count) VALUES (20,2007,'20210101','STOCK INICIAL',10);
INSERT INTO Kardex (kardex_id,product_id,kardex_date,kardex_description,kardex_count) VALUES (21,2008,'20210101','STOCK INICIAL',10);
INSERT INTO Kardex (kardex_id,product_id,kardex_date,kardex_description,kardex_count) VALUES (22,2009,'20210101','STOCK INICIAL',10);
INSERT INTO Kardex (kardex_id,product_id,kardex_date,kardex_description,kardex_count) VALUES (23,2010,'20210101','STOCK INICIAL',10);
INSERT INTO Kardex (kardex_id,product_id,kardex_date,kardex_description,kardex_count) VALUES (24,2011,'20210101','STOCK INICIAL',10);
INSERT INTO Kardex (kardex_id,product_id,kardex_date,kardex_description,kardex_count) VALUES (25,2012,'20210101','STOCK INICIAL',10);
INSERT INTO Kardex (kardex_id,product_id,kardex_date,kardex_description,kardex_count) VALUES (26,2013,'20210101','STOCK INICIAL',10);
INSERT INTO Kardex (kardex_id,product_id,kardex_date,kardex_description,kardex_count) VALUES (27,3001,'20210101','STOCK INICIAL',10);
INSERT INTO Kardex (kardex_id,product_id,kardex_date,kardex_description,kardex_count) VALUES (28,3002,'20210101','STOCK INICIAL',10);
INSERT INTO Kardex (kardex_id,product_id,kardex_date,kardex_description,kardex_count) VALUES (29,3003,'20210101','STOCK INICIAL',10);
INSERT INTO Kardex (kardex_id,product_id,kardex_date,kardex_description,kardex_count) VALUES (30,3004,'20210101','STOCK INICIAL',10);
INSERT INTO Kardex (kardex_id,product_id,kardex_date,kardex_description,kardex_count) VALUES (31,3005,'20210101','STOCK INICIAL',10);
INSERT INTO Kardex (kardex_id,product_id,kardex_date,kardex_description,kardex_count) VALUES (32,3006,'20210101','STOCK INICIAL',10);
INSERT INTO Kardex (kardex_id,product_id,kardex_date,kardex_description,kardex_count) VALUES (33,3007,'20210101','STOCK INICIAL',10);
INSERT INTO Kardex (kardex_id,product_id,kardex_date,kardex_description,kardex_count) VALUES (34,3008,'20210101','STOCK INICIAL',10);
INSERT INTO Kardex (kardex_id,product_id,kardex_date,kardex_description,kardex_count) VALUES (35,3009,'20210101','STOCK INICIAL',10);
INSERT INTO Kardex (kardex_id,product_id,kardex_date,kardex_description,kardex_count) VALUES (36,3010,'20210101','STOCK INICIAL',10);
INSERT INTO Kardex (kardex_id,product_id,kardex_date,kardex_description,kardex_count) VALUES (37,3011,'20210101','STOCK INICIAL',10);
INSERT INTO Kardex (kardex_id,product_id,kardex_date,kardex_description,kardex_count) VALUES (38,3012,'20210101','STOCK INICIAL',10);
INSERT INTO Kardex (kardex_id,product_id,kardex_date,kardex_description,kardex_count) VALUES (39,3013,'20210101','STOCK INICIAL',10);