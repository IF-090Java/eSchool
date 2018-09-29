--liquibase formatted sql
--preconditions onFail:HALT onError:CONTINUE

--changeset Popovych:02
INSERT INTO users (id, name) VALUES (3, 'Kevin');