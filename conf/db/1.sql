-- # Transactions schema
--
-- # --- !Ups
--
-- CREATE TABLE Transactions (
--   id int NOT NULL AUTO_INCREMENT,
--   merchant varchar(100) NOT NULL,
--   amount double NOT NULL,
--   source varchar(100) NOT NULL,
--   memo varchar(100) NOT NULL, PRIMARY KEY (id)
-- );
--
-- -- CREATE TABLE User (
-- --     id bigint(20) NOT NULL AUTO_INCREMENT,
-- --     email varchar(255) NOT NULL,
-- --     password varchar(255) NOT NULL,
-- --     fullname varchar(255) NOT NULL,
-- --     isAdmin boolean NOT NULL,
-- --     PRIMARY KEY (id)
-- -- );
--
-- # --- !Downs
--
-- DROP TABLE Transactions;