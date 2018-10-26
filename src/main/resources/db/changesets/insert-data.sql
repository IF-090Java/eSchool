--liquibase formatted sql

--changeset User1:2

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
VALUES('Михайло', 'Грушевський', 'Сергійович', '1964-05-03', 'mGrush03', 'password', default, default, default, default, 'ROLE_TEACHER', 'male'),
('Григорій', 'Бублик', 'Опанасович', '1971-10-23', 'gBublik23', 'password', default, 'Вчитель іноземних мов', default, '068542189', 'ROLE_TEACHER', 'male'),
('Агатангел', 'Кримський', 'Юхимович', '1955-11-06', 'aCrim06', 'password', default, 'Вчитель вищої категорії', default, '023548961', 'ROLE_TEACHER', 'male'),
('Білл', 'Гейтс', 'Вільямович', '1955-10-28', 'bGates28', 'password', default, 'Класний керівник. Найбагатша людина світу', default, '085411235', 'ROLE_TEACHER', 'male'),
('Альберт', 'Ейнштейн', 'Германович', '1981-03-14', 'aEinst14', 'password', default, 'Вчитель вищої категорії. Нобелівський лауреат', default, default, 'ROLE_TEACHER', 'male'),
('Христина', 'Хойновська-Ліскевич', 'Батьківна', '1990-06-03', 'kLiske10', 'password', default, default, default, default, 'ROLE_TEACHER', 'female'),
('Марія', 'Склодовська-Кюрі', 'Владиславівна', '1967-07-04', 'mCurie04', 'password', default, 'Вчитель вищої категорії', default, default, 'ROLE_TEACHER', 'female'),
('Світлана', 'Майборода', 'Василівна', '1981-06-02', 'sMaybo02', 'password', default, default, default, default, 'ROLE_TEACHER', 'female'),

('Василь', 'Крупа', 'Іванович', '1995-01-01', 'vKrupa01', 'password', default, 'Староста класу', default, default, 'ROLE_USER', 'male'),
('Анастасія', 'Пишненька', 'Захарівна', '1996-11-03', 'aPisnen03', 'password', default, default, default, default, 'ROLE_USER', 'female'),
('Тарас', 'Квас', 'Зеновійович', '1995-04-12', 'tKvas12', 'password', default, default, default, default, 'ROLE_USER', 'male'),
('Маргарита', 'Висока', 'Степанівна', '1997-05-11', 'mVisoka11', 'password', default, default, default, default, 'ROLE_USER', 'female'),
('Ігор', 'Підгорний', 'Васильвич', '1997-08-21', 'iPidgor21', 'password', default, 'Староста класу', default, default, 'ROLE_USER', 'male'),
('Віта', 'Квітовська', 'Миколаївна', '1998-09-01', 'vKvit01', 'password', default, default, default, default, 'ROLE_USER', 'female'),
('Степан', 'Когут', 'Григорійович', '1998-02-14', 'sKohyt14', 'password', default, default, default, default, 'ROLE_USER', 'male'),
('Зеновій', 'Запухляк', 'Віталійович', '1999-12-31', 'zZapukh31', 'password', default, default, default, default, 'ROLE_USER', 'male'),
('Тетяна', 'Куца', 'Орестівна', '1999-12-01', 'tKutsa01', 'password', default, 'Староста класу', default, default, 'ROLE_USER', 'female'),
('Ірина', 'Грушецька', 'Вікторівна', '1999-06-06', 'iGrush06', 'password', default, default, default, default, 'ROLE_USER', 'female'),
('Катерина', 'Полянська', 'Валеріївна', '1999-08-16', 'kPolyan16', 'password', default, 'Староста класу', default, default, 'ROLE_USER', 'female'),
('Марія', 'Василик', 'Володимирівна', '2000-10-14', 'mVasylyk14', 'password', default, default, default, default, 'ROLE_USER', 'female'),
('Михайло', 'Кобилянський', 'Андрійович', '2001-01-12', 'mKobyl12', 'password', default, 'Староста класу', default, default, 'ROLE_USER', 'male'),
('Олег', 'Ляховський', 'Богданович', '2002-03-10', 'oLyakh10', 'password', default, default, default, default, 'ROLE_USER', 'male'),
('Іван', 'Черв''яковський', 'Орестович', '2001-05-08', 'iCherv08', 'password', default, default, default, default, 'ROLE_USER', 'male');

INSERT INTO clazz(name, academic_year, description, is_active) VALUES('8-A', 2017, default, false),
('7-A', 2018, default, true),
('7-Б', 2018, default, true),
('9', 2018, default, true),
('5-A', 2016, default, false);

INSERT INTO `teacher`(id) VALUES(1), (2), (3), (4), (5), (6), (7), (8);

INSERT INTO `student`(id) VALUES(9), (10), (11), (12), (13), (14), (15), (16), (17), (18), (19), (20), (21), (22), (23);

INSERT INTO class_teacher_subject_link(teacher_id, subject_id, clazz_id, is_active) VALUES(1, 1, 1, true),
(1, 1, 2, true),
(1, 1, 5, true),
(2, 3, 1, true),
(2, 3, 2, true),
(3, 4, 1, true),
(3, 5, 1, true),
(3, 5, 2, true),
(3, 4, 3, true),
(3, 5, 5, true),
(4, 2, 1, true),
(4, 2, 3, true),
(4, 2, 5, true),
(5, 6, 3, true),
(5, 6, 4, true),
(6, 7, 2, true),
(6, 7, 4, true),
(7, 8, 3, true),
(7, 10, 3, true),
(7, 10, 4, true),
(7, 8, 4, true),
(7, 10, 5, true),
(8, 9, 2, true),
(8, 9, 4, true),
(8, 9, 5, true);

INSERT INTO students_classes(student_id, class_id) VALUES(9, 5),
(10, 5),
(11, 5),
(12, 4),
(13, 4),
(14, 4),
(15, 1),
(16, 1),
(17, 1),
(18, 2),
(19, 2),
(20, 2),
(21, 3),
(22, 3),
(23, 3);
