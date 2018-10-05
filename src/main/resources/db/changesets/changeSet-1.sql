--liquibase formatted sql
--preconditions onFail:HALT onError:CONTINUE

--changeset Popovych:department_employee_tables
CREATE TABLE department (department_id INT PRIMARY KEY, department_name VARCHAR (255));
CREATE TABLE employee (employee_id INT PRIMARY KEY , employee_name VARCHAR (255));
CREATE TABLE employee_department (idemployee INT NOT NULL, iddepartment INT NOT NULL );
CREATE TABLE id_gen (gen_name VARCHAR (255) NOT NULL, gen_val INT, PRIMARY KEY (gen_name));
ALTER TABLE employee_department ADD CONSTRAINT fk_iddepartment FOREIGN KEY (iddepartment) REFERENCES department(department_id);
ALTER TABLE employee_department ADD CONSTRAINT fk_idemployee FOREIGN KEY (idemployee) REFERENCES employee(employee_id);