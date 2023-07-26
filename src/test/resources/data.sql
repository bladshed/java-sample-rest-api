-- Add Persons

INSERT INTO person(username, password, email, name, registration_number, type)
VALUES
('francis', '$2a$12$ZhGS.zcWt1gnZ9xRNp7inOvo5hIT0ngN7N.pN939cShxKvaQYHnnu', 'francis@yahoo.com', 'Francis', '123FMS', 'D')
,('paolo', '$2a$12$ZhGS.zcWt1gnZ9xRNp7inOvo5hIT0ngN7N.pN939cShxKvaQYHnnu','paolo@yahoo.com', 'Paolo', '123JPS', 'D')
,('carlos', '$2a$12$ZhGS.zcWt1gnZ9xRNp7inOvo5hIT0ngN7N.pN939cShxKvaQYHnnu','carlos@yahoo.com', 'Carlos', '123CJS', 'D')
,('lebron', '$2a$12$ZhGS.zcWt1gnZ9xRNp7inOvo5hIT0ngN7N.pN939cShxKvaQYHnnu','lebron@yahoo.com', 'LeBron', '623LBJ', 'D')
,('kobe', '$2a$12$ZhGS.zcWt1gnZ9xRNp7inOvo5hIT0ngN7N.pN939cShxKvaQYHnnu','kobe@yahoo.com', 'Kobe', '824KBB', 'D')
,('ramon', '$2a$12$ZhGS.zcWt1gnZ9xRNp7inOvo5hIT0ngN7N.pN939cShxKvaQYHnnu','ramon@yahoo.com', 'Ramon', '616rts', 'C')
,('agnes', '$2a$12$ZhGS.zcWt1gnZ9xRNp7inOvo5hIT0ngN7N.pN939cShxKvaQYHnnu','agnes@yahoo.com', 'Agnes', '108ABS', 'C')
,('kenzo', '$2a$12$ZhGS.zcWt1gnZ9xRNp7inOvo5hIT0ngN7N.pN939cShxKvaQYHnnu','kenzo@yahoo.com', 'Kenzo', '126KBS', 'C')
,('mateo', '$2a$12$ZhGS.zcWt1gnZ9xRNp7inOvo5hIT0ngN7N.pN939cShxKvaQYHnnu','mateo@yahoo.com', 'Mateo', '777MGS', 'C')
,('abraham', '$2a$12$ZhGS.zcWt1gnZ9xRNp7inOvo5hIT0ngN7N.pN939cShxKvaQYHnnu','abraham@yahoo.com', 'Abraham', '101ABR', 'C')
,('test', '$2a$12$ZhGS.zcWt1gnZ9xRNp7inOvo5hIT0ngN7N.pN939cShxKvaQYHnnu','test@yahoo.com', 'Test', '123TST', 'C');

-- Add Deliveries

INSERT INTO delivery(commission, distance, end_time, price, start_time, customer_id, delivery_man_id)
VALUES (24.5, 50, '2023-06-23 02:18:10.938553', 50, '2023-06-23 02:17:50.938553', 7, 4)
,(27.5, 25, '2023-06-23 02:28:10.938553', 30, '2023-06-23 02:27:50.938553', 8, 5)
,(30.2, 40, '2023-06-23 07:39:10.938553', 37, '2023-06-23 7:17:50.938553', 6, 2)
,(14.3, 17, '2023-06-23 08:51:10.938553', 43, '2023-06-23 8:27:50.938553', 8, 5)
,(22.1, 35, '2023-06-23 09:31:10.938553', 27, '2023-06-23 9:07:50.938553', 10, 4)
,(7.1, 9, '2023-06-23 09:43:10.938553', 12, '2023-06-23 9:27:50.938553', 9, 1)
,(17.21, 21, '2023-06-23 10:33:10.938553', 61, '2023-06-23 10:05:50.938553', 10, 2)
,(25.37, 44, '2023-06-24 07:26:10.938553', 52, '2023-06-24 07:02:50.938553', 9, 3)
,(44.17, 14, '2023-06-24 08:26:10.938553', 81, '2023-06-24 08:02:50.938553', 6, 4)
,(34.77, 23, '2023-06-25 10:32:10.938553', 81, '2023-06-25 10:15:50.938553', 7, 3)
,(19.57, 19, '2023-06-25 11:57:10.938553', 35, '2023-06-25 11:19:50.938553', 9, 4)
,(22.27, 29, '2023-06-26 11:57:10.938553', 44, '2023-06-26 11:19:50.938553', 8, 2)
,(27.37, 43, '2023-06-26 12:34:10.938553', 37, '2023-06-26 12:09:50.938553', 6, 1);

INSERT INTO delivery(distance, price, start_time, customer_id, delivery_man_id)
VALUES (27, 32, '2023-06-24 18:17:50.938553', 7, 4);

-- SECURITY ROLES

INSERT INTO security_role (id, role_name, description) VALUES (1, 'ROLE_ADMIN', 'Administrator');
INSERT INTO security_role (id, role_name, description) VALUES (2, 'ROLE_USER', 'User');

-- PERSON ROLES

INSERT INTO person_role(person_id, role_id) VALUES
 (1, 1),
 (2, 2),
 (3, 2),
 (4, 1),
 (4, 2),
 (5, 1),
 (6, 2),
 (7, 1),
 (8, 2),
 (9, 1),
 (10, 1),
 (11, 2);