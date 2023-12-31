-- Drop Tables
DROP TABLE IF EXISTS delivery;
DROP TABLE IF EXISTS person_role;
DROP TABLE IF EXISTS security_role;
DROP TABLE IF EXISTS person;

-- Table: publicperson

CREATE TABLE IF NOT EXISTS person
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username varchar(255) NOT NULL,
    password varchar(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    registration_number VARCHAR(255) NOT NULL,
    type CHAR(1) NOT NULL
);

-- Table: public.delivery

CREATE TABLE IF NOT EXISTS delivery (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  commission REAL,
  distance INTEGER,
  end_time TIMESTAMP,
  price REAL,
  start_time TIMESTAMP,
  customer_id BIGINT,
  delivery_man_id BIGINT
);
ALTER TABLE delivery ADD CONSTRAINT FK_CUSTOMER_ID FOREIGN KEY (customer_id) REFERENCES person(id);
ALTER TABLE delivery ADD CONSTRAINT FK_DELIVERY_MAN_ID FOREIGN KEY (delivery_man_id) REFERENCES person(id);

-- Table: public.security_role

CREATE TABLE security_role (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  description varchar(100) DEFAULT NULL,
  role_name varchar(100) DEFAULT NULL
);

-- Table: public.person_role

CREATE TABLE person_role (
  person_id BIGINT NOT NULL,
  role_id BIGINT NOT NULL,
  CONSTRAINT FK_SECURITY_PERSON_ID FOREIGN KEY (person_id) REFERENCES person (id),
  CONSTRAINT FK_SECURITY_ROLE_ID FOREIGN KEY (role_id) REFERENCES security_role (id)
);