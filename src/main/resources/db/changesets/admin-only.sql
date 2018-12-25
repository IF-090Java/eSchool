--liquibase formatted sql

--changeset PDarchyn:only admin user

INSERT INTO `user`(first_name, last_name, patronymic, date_of_birth, login, password, avatar, description, email, phone, role, sex)
VALUES('Admin', 'Admin', 'Admin', '2001-05-08', '${admin.username}', '${admin.password}', default, default, default, default, 'ROLE_ADMIN', 'male');