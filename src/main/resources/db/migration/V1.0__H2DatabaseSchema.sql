-- Table: publicperson

-- DROP TABLE IF EXISTS public.person;

-- CREATE TABLE IF NOT EXISTS person
-- (
--     id BIGINT AUTO_INCREMENT PRIMARY KEY,
--     email VARCHAR(255) NOT NULL,
--     name VARCHAR(255) NOT NULL,
--     registration_number VARCHAR(255) NOT NULL,
--     type CHAR(1) NOT NULL
-- );

-- Table: public.delivery

-- DROP TABLE IF EXISTS public.delivery;

-- CREATE TABLE IF NOT EXISTS delivery (
--   id BIGINT AUTO_INCREMENT PRIMARY KEY,
--   commission REAL,
--   distance INTEGER,
--   end_time TIMESTAMP,
--   price REAL,
--   start_time TIMESTAMP,
--   customer_id BIGINT,
--   delivery_man_id BIGINT
-- );
-- ALTER TABLE delivery ADD CONSTRAINT FK_CUSTOMER_ID FOREIGN KEY (customer_id) REFERENCES person(id);
-- ALTER TABLE delivery ADD CONSTRAINT FK_DELIVERY_MAN_ID FOREIGN KEY (delivery_man_id) REFERENCES person(id);