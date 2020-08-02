--liquibase formatted sql

--changeset david:1
CREATE SEQUENCE IF NOT EXISTS hibernate_sequence;

CREATE TABLE IF NOT EXISTS spent (
    id BIGINT NOT NULL CONSTRAINT spent_pkey PRIMARY KEY,
    person varchar(255) NOT NULL,
    description VARCHAR(255),
    datetime TIMESTAMP,
    value DOUBLE PRECISION
);

--rollback DROP TABLE product;
--rollback DROP SEQUENCE hibernate_sequence;

--changeset outrousuario:2
CREATE TABLE IF NOT EXISTS tags (
    tag VARCHAR(255),
    spent_id BIGINT CONSTRAINT spent_tags_fkey REFERENCES spent
);
--rollback ALTER TABLE tags DROP CONSTRAINT spent_tags_fkey;
--rollback DROP TABLE tags;