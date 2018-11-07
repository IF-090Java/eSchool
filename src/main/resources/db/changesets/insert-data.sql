--liquibase formatted sql

--changeset PavloDarchyn:inserting_data

INSERT INTO `subject`(name, description)
VALUES('Історія України', 'Гуманітарний навчальний предмет. Починає вивчатись із 5-го класу'),
('Інформатика', default),
('Англійська мова', 'Гуманітарний навчальний предмет'),
('Українська мова', 'Гуманітарний навчальний предмет'),
('Українська література', 'Гуманітарний навчальний предмет'),
('Фізика', 'Природничий навчальний предмет. Починає вивчатись із 7-го класу'),
('Географія', 'Природничий навчальний предмет. Починає вивчатись із 6-го класу'),
('Біологія', 'Природничий навчальний предмет. Починає вивчатись із 6-го класу'),
('Математика', default),
('Хімія', 'Природничий навчальний предмет. Починає вивчатись із 7-го класу');

INSERT INTO `user`(first_name, last_name, patronymic, date_of_birth, login, password, avatar, description, email, phone, role, sex)
VALUES('Михайло', 'Грушевський', 'Сергійович', '1964-05-03', 'mGrush03', '$2a$04$TKKBh6qCAp6F7ZOikROKUeViZx30AmIezWxTEIzkpCMYXgyl.GDxy', default, default, default, default, 'ROLE_TEACHER', 'male'),
('Григорій', 'Бублик', 'Опанасович', '1971-10-23', 'gBublik23', '$2a$04$TKKBh6qCAp6F7ZOikROKUeViZx30AmIezWxTEIzkpCMYXgyl.GDxy', default, 'Вчитель іноземних мов', default, '068542189', 'ROLE_TEACHER', 'male'),
('Агатангел', 'Кримський', 'Юхимович', '1955-11-06', 'aCrim06', '$2a$04$TKKBh6qCAp6F7ZOikROKUeViZx30AmIezWxTEIzkpCMYXgyl.GDxy', default, 'Вчитель вищої категорії', default, '023548961', 'ROLE_TEACHER', 'male'),
('Білл', 'Гейтс', 'Вільямович', '1955-10-28', 'bGates28', '$2a$04$TKKBh6qCAp6F7ZOikROKUeViZx30AmIezWxTEIzkpCMYXgyl.GDxy', default, 'Класний керівник. Найбагатша людина світу', default, '085411235', 'ROLE_TEACHER', 'male'),
('Альберт', 'Ейнштейн', 'Германович', '1981-03-14', 'aEinst14', '$2a$04$TKKBh6qCAp6F7ZOikROKUeViZx30AmIezWxTEIzkpCMYXgyl.GDxy', default, 'Вчитель вищої категорії. Нобелівський лауреат', default, default, 'ROLE_TEACHER', 'male'),
('Христина', 'Хойновська-Ліскевич', 'Батьківна', '1990-06-03', 'kLiske10', '$2a$04$TKKBh6qCAp6F7ZOikROKUeViZx30AmIezWxTEIzkpCMYXgyl.GDxy', default, default, default, default, 'ROLE_TEACHER', 'female'),
('Марія', 'Склодовська-Кюрі', 'Владиславівна', '1967-07-04', 'mCurie04', '$2a$04$TKKBh6qCAp6F7ZOikROKUeViZx30AmIezWxTEIzkpCMYXgyl.GDxy', default, 'Вчитель вищої категорії', default, default, 'ROLE_TEACHER', 'female'),
('Світлана', 'Майборода', 'Василівна', '1981-06-02', 'sMaybo02', '$2a$04$TKKBh6qCAp6F7ZOikROKUeViZx30AmIezWxTEIzkpCMYXgyl.GDxy', default, default, default, default, 'ROLE_TEACHER', 'female'),

('Василь', 'Крупа', 'Іванович', '1995-01-01', 'vKrupa01', '$2a$04$TKKBh6qCAp6F7ZOikROKUeViZx30AmIezWxTEIzkpCMYXgyl.GDxy', default, 'Староста класу', default, default, 'ROLE_USER', 'male'),
('Анастасія', 'Пишненька', 'Захарівна', '1996-11-03', 'aPisnen03', '$2a$04$TKKBh6qCAp6F7ZOikROKUeViZx30AmIezWxTEIzkpCMYXgyl.GDxy', default, default, default, default, 'ROLE_USER', 'female'),
('Тарас', 'Квас', 'Зеновійович', '1995-04-12', 'tKvas12', '$2a$04$TKKBh6qCAp6F7ZOikROKUeViZx30AmIezWxTEIzkpCMYXgyl.GDxy', default, default, default, default, 'ROLE_USER', 'male'),
('Маргарита', 'Висока', 'Степанівна', '1997-05-11', 'mVisoka11', '$2a$04$TKKBh6qCAp6F7ZOikROKUeViZx30AmIezWxTEIzkpCMYXgyl.GDxy', default, default, default, default, 'ROLE_USER', 'female'),
('Ігор', 'Підгорний', 'Васильвич', '1997-08-21', 'iPidgor21', '$2a$04$TKKBh6qCAp6F7ZOikROKUeViZx30AmIezWxTEIzkpCMYXgyl.GDxy', default, 'Староста класу', default, default, 'ROLE_USER', 'male'),
('Віта', 'Квітовська', 'Миколаївна', '1998-09-01', 'vKvit01', '$2a$04$TKKBh6qCAp6F7ZOikROKUeViZx30AmIezWxTEIzkpCMYXgyl.GDxy', default, default, default, default, 'ROLE_USER', 'female'),
('Степан', 'Когут', 'Григорійович', '1998-02-14', 'sKohyt14', '$2a$04$TKKBh6qCAp6F7ZOikROKUeViZx30AmIezWxTEIzkpCMYXgyl.GDxy', default, default, default, default, 'ROLE_USER', 'male'),
('Зеновій', 'Запухляк', 'Віталійович', '1999-12-31', 'zZapukh31', '$2a$04$TKKBh6qCAp6F7ZOikROKUeViZx30AmIezWxTEIzkpCMYXgyl.GDxy', default, default, default, default, 'ROLE_USER', 'male'),
('Тетяна', 'Куца', 'Орестівна', '1999-12-01', 'tKutsa01', '$2a$04$TKKBh6qCAp6F7ZOikROKUeViZx30AmIezWxTEIzkpCMYXgyl.GDxy', default, 'Староста класу', default, default, 'ROLE_USER', 'female'),
('Ірина', 'Грушецька', 'Вікторівна', '1999-06-06', 'iGrush06', '$2a$04$TKKBh6qCAp6F7ZOikROKUeViZx30AmIezWxTEIzkpCMYXgyl.GDxy', default, default, default, default, 'ROLE_USER', 'female'),
('Катерина', 'Полянська', 'Валеріївна', '1999-08-16', 'kPolyan16', '$2a$04$TKKBh6qCAp6F7ZOikROKUeViZx30AmIezWxTEIzkpCMYXgyl.GDxy', default, 'Староста класу', default, default, 'ROLE_USER', 'female'),
('Марія', 'Василик', 'Володимирівна', '2000-10-14', 'mVasylyk14', '$2a$04$TKKBh6qCAp6F7ZOikROKUeViZx30AmIezWxTEIzkpCMYXgyl.GDxy', default, default, default, default, 'ROLE_USER', 'female'),
('Михайло', 'Кобилянський', 'Андрійович', '2001-01-12', 'mKobyl12', '$2a$04$TKKBh6qCAp6F7ZOikROKUeViZx30AmIezWxTEIzkpCMYXgyl.GDxy', default, 'Староста класу', default, default, 'ROLE_USER', 'male'),
('Олег', 'Ляховський', 'Богданович', '2002-03-10', 'oLyakh10', '$2a$04$TKKBh6qCAp6F7ZOikROKUeViZx30AmIezWxTEIzkpCMYXgyl.GDxy', default, default, default, default, 'ROLE_USER', 'male'),
('Іван', 'Черв''яковський', 'Орестович', '2001-05-08', 'iCherv08', '$2a$04$TKKBh6qCAp6F7ZOikROKUeViZx30AmIezWxTEIzkpCMYXgyl.GDxy', default, default, default, default, 'ROLE_USER', 'male'),
('Іван', 'Черв''яковський', 'Орестович', '2001-05-08', 'admin', '$2a$04$I/u5oartNzAEf8987ELl6u5jo.NVGHEKQCgZbizPgYkmuJGXv/IWm', default, default, default, default, 'ROLE_ADMIN', 'male');
INSERT INTO `clazz`(name, academic_year, description, is_active) VALUES('8-А', 2017, default, false),
('7-А', 2018, default, true),
('7-Б', 2018, default, true),
('9', 2018, default, true),
('5-А', 2016, default, false);

INSERT INTO `teacher`(id) VALUES
((SELECT id FROM `user` WHERE `login` LIKE 'mGrush03')),
((SELECT id FROM `user` WHERE `login` LIKE 'gBublik23')),
((SELECT id FROM `user` WHERE `login` LIKE 'aCrim06')),
((SELECT id FROM `user` WHERE `login` LIKE 'bGates28')),
((SELECT id FROM `user` WHERE `login` LIKE 'aEinst14')),
((SELECT id FROM `user` WHERE `login` LIKE 'kLiske10')),
((SELECT id FROM `user` WHERE `login` LIKE 'mCurie04')),
((SELECT id FROM `user` WHERE `login` LIKE 'sMaybo02'));

INSERT INTO `student`(id) VALUES
((SELECT id FROM `user` WHERE `login` LIKE 'vKrupa01')),
((SELECT id FROM `user` WHERE `login` LIKE 'aPisnen03')),
((SELECT id FROM `user` WHERE `login` LIKE 'tKvas12')),
((SELECT id FROM `user` WHERE `login` LIKE 'mVisoka11')),
((SELECT id FROM `user` WHERE `login` LIKE 'iPidgor21')),
((SELECT id FROM `user` WHERE `login` LIKE 'vKvit01')),
((SELECT id FROM `user` WHERE `login` LIKE 'sKohyt14')),
((SELECT id FROM `user` WHERE `login` LIKE 'zZapukh31')),
((SELECT id FROM `user` WHERE `login` LIKE 'tKutsa01')),
((SELECT id FROM `user` WHERE `login` LIKE 'iGrush06')),
((SELECT id FROM `user` WHERE `login` LIKE 'kPolyan16')),
((SELECT id FROM `user` WHERE `login` LIKE 'mVasylyk14')),
((SELECT id FROM `user` WHERE `login` LIKE 'mKobyl12')),
((SELECT id FROM `user` WHERE `login` LIKE 'oLyakh10')),
((SELECT id FROM `user` WHERE `login` LIKE 'iCherv08'));

INSERT INTO class_teacher_subject_link(teacher_id, subject_id, clazz_id, is_active) VALUES
((SELECT id FROM `user` WHERE `login` LIKE 'mGrush03'), (SELECT id FROM `subject` WHERE name LIKE 'Історія України') , (SELECT `id` FROM `clazz` WHERE `name` LIKE '8-А'), true),
((SELECT id FROM `user` WHERE `login` LIKE 'mGrush03'), (SELECT id FROM `subject` WHERE name LIKE 'Історія України'), (SELECT `id` FROM `clazz` WHERE `name` LIKE '5-А'), true),
((SELECT id FROM `user` WHERE `login` LIKE 'bGates28'), (SELECT id FROM `subject` WHERE name LIKE 'Історія України'), (SELECT `id` FROM `clazz` WHERE `name` LIKE '7-Б'), true),
((SELECT id FROM `user` WHERE `login` LIKE 'gBublik23'), (SELECT id FROM `subject` WHERE name LIKE 'Англійська мова'), (SELECT `id` FROM `clazz` WHERE `name` LIKE '8-А'), true),
((SELECT id FROM `user` WHERE `login` LIKE 'gBublik23'), (SELECT id FROM `subject` WHERE name LIKE 'Англійська мова'), (SELECT `id` FROM `clazz` WHERE `name` LIKE '7-А'), true),
((SELECT id FROM `user` WHERE `login` LIKE 'aCrim06'), (SELECT id FROM `subject` WHERE name LIKE 'Українська мова'), (SELECT `id` FROM `clazz` WHERE `name` LIKE '8-А'), true),
((SELECT id FROM `user` WHERE `login` LIKE 'aCrim06'), (SELECT id FROM `subject` WHERE name LIKE 'Українська література'), (SELECT `id` FROM `clazz` WHERE `name` LIKE '8-А'), true),
((SELECT id FROM `user` WHERE `login` LIKE 'aCrim06'), (SELECT id FROM `subject` WHERE name LIKE 'Українська література'), (SELECT `id` FROM `clazz` WHERE `name` LIKE '7-А'), true),
((SELECT id FROM `user` WHERE `login` LIKE 'aCrim06'), (SELECT id FROM `subject` WHERE name LIKE 'Українська мова'), (SELECT `id` FROM `clazz` WHERE `name` LIKE '7-Б'), true),
((SELECT id FROM `user` WHERE `login` LIKE 'aCrim06'), (SELECT id FROM `subject` WHERE name LIKE 'Українська література'), (SELECT `id` FROM `clazz` WHERE `name` LIKE '5-А'), true),
((SELECT id FROM `user` WHERE `login` LIKE 'aEinst14'), (SELECT id FROM `subject` WHERE name LIKE 'Українська література'), (SELECT `id` FROM `clazz` WHERE `name` LIKE '7-Б'), true),
((SELECT id FROM `user` WHERE `login` LIKE 'bGates28'), (SELECT id FROM `subject` WHERE name LIKE 'Інформатика'), (SELECT `id` FROM `clazz` WHERE `name` LIKE '8-А'), true),
((SELECT id FROM `user` WHERE `login` LIKE 'bGates28'), (SELECT id FROM `subject` WHERE name LIKE 'Інформатика'), (SELECT `id` FROM `clazz` WHERE `name` LIKE '5-А'), true),
((SELECT id FROM `user` WHERE `login` LIKE 'aEinst14'), (SELECT id FROM `subject` WHERE name LIKE 'Фізика'), (SELECT `id` FROM `clazz` WHERE `name` LIKE '9'), true),
((SELECT id FROM `user` WHERE `login` LIKE 'kLiske10'), (SELECT id FROM `subject` WHERE name LIKE 'Фізика'), (SELECT `id` FROM `clazz` WHERE `name` LIKE '7-А'), true),
((SELECT id FROM `user` WHERE `login` LIKE 'kLiske10'), (SELECT id FROM `subject` WHERE name LIKE 'Географія'), (SELECT `id` FROM `clazz` WHERE `name` LIKE '9'), true),
((SELECT id FROM `user` WHERE `login` LIKE 'mCurie04'), (SELECT id FROM `subject` WHERE name LIKE 'Біологія'), (SELECT `id` FROM `clazz` WHERE `name` LIKE '7-Б'), true),
((SELECT id FROM `user` WHERE `login` LIKE 'mCurie04'), (SELECT id FROM `subject` WHERE name LIKE 'Біологія'), (SELECT `id` FROM `clazz` WHERE `name` LIKE '9'), true),
((SELECT id FROM `user` WHERE `login` LIKE 'mCurie04'), (SELECT id FROM `subject` WHERE name LIKE 'Хімія'), (SELECT `id` FROM `clazz` WHERE `name` LIKE '7-Б'), true),
((SELECT id FROM `user` WHERE `login` LIKE 'mCurie04'), (SELECT id FROM `subject` WHERE name LIKE 'Хімія'), (SELECT `id` FROM `clazz` WHERE `name` LIKE '9'), true),
((SELECT id FROM `user` WHERE `login` LIKE 'mCurie04'), (SELECT id FROM `subject` WHERE name LIKE 'Хімія'), (SELECT `id` FROM `clazz` WHERE `name` LIKE '5-А'), true),
((SELECT id FROM `user` WHERE `login` LIKE 'sMaybo02'), (SELECT id FROM `subject` WHERE name LIKE 'Математика'), (SELECT `id` FROM `clazz` WHERE `name` LIKE '7-А'), true),
((SELECT id FROM `user` WHERE `login` LIKE 'sMaybo02'), (SELECT id FROM `subject` WHERE name LIKE 'Математика'), (SELECT `id` FROM `clazz` WHERE `name` LIKE '9'), true),
((SELECT id FROM `user` WHERE `login` LIKE 'sMaybo02'), (SELECT id FROM `subject` WHERE name LIKE 'Математика'), (SELECT `id` FROM `clazz` WHERE `name` LIKE '5-А'), true);

INSERT INTO students_classes(student_id, class_id) VALUES
((SELECT id FROM `user` WHERE `login` LIKE 'vKrupa01'), (SELECT id FROM `clazz` WHERE name LIKE '5-А')),
((SELECT id FROM `user` WHERE `login` LIKE 'aPisnen03'), (SELECT id FROM `clazz` WHERE name LIKE '5-А')),
((SELECT id FROM `user` WHERE `login` LIKE 'tKvas12'), (SELECT id FROM `clazz` WHERE name LIKE '5-А')),
((SELECT id FROM `user` WHERE `login` LIKE 'mVisoka11'), (SELECT id FROM `clazz` WHERE name LIKE '9')),
((SELECT id FROM `user` WHERE `login` LIKE 'iPidgor21'), (SELECT id FROM `clazz` WHERE name LIKE '9')),
((SELECT id FROM `user` WHERE `login` LIKE 'vKvit01'), (SELECT id FROM `clazz` WHERE name LIKE '9')),
((SELECT id FROM `user` WHERE `login` LIKE 'sKohyt14'), (SELECT id FROM `clazz` WHERE name LIKE '8-А')),
((SELECT id FROM `user` WHERE `login` LIKE 'zZapukh31'), (SELECT id FROM `clazz` WHERE name LIKE '8-А')),
((SELECT id FROM `user` WHERE `login` LIKE 'tKutsa01'), (SELECT id FROM `clazz` WHERE name LIKE '8-А')),
((SELECT id FROM `user` WHERE `login` LIKE 'iGrush06'), (SELECT id FROM `clazz` WHERE name LIKE '7-А')),
((SELECT id FROM `user` WHERE `login` LIKE 'kPolyan16'), (SELECT id FROM `clazz` WHERE name LIKE '7-А')),
((SELECT id FROM `user` WHERE `login` LIKE 'mVasylyk14'), (SELECT id FROM `clazz` WHERE name LIKE '7-А')),
((SELECT id FROM `user` WHERE `login` LIKE 'mKobyl12'), (SELECT id FROM `clazz` WHERE name LIKE '7-Б')),
((SELECT id FROM `user` WHERE `login` LIKE 'oLyakh10'), (SELECT id FROM `clazz` WHERE name LIKE '7-Б')),
((SELECT id FROM `user` WHERE `login` LIKE 'iCherv08'), (SELECT id FROM `clazz` WHERE name LIKE '7-Б'));
