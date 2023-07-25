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
 (10, 1);