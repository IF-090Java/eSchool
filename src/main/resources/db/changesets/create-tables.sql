--liquibase formatted sql

--changeset User1:1

CREATE TABLE IF NOT EXISTS `subject` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(255) NOT NULL,
  `last_name` VARCHAR(255) NOT NULL,
  `patronymic` VARCHAR(255) NOT NULL,
  `date_of_birth` DATE NOT NULL,
  `login` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `avatar` MEDIUMTEXT NULL DEFAULT NULL,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  `phone` VARCHAR(25) NULL DEFAULT NULL,
  `role` ENUM('ROLE_ADMIN','ROLE_TEACHER','ROLE_USER'),
  `sex` ENUM('male', 'female'),
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


CREATE TABLE IF NOT EXISTS `teacher` (
  `id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_teacher_user`
    FOREIGN KEY (`id`)
    REFERENCES `user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;



CREATE TABLE IF NOT EXISTS `clazz` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `academic_year` INT(11) NOT NULL,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  `is_active` BIT(1) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;



CREATE TABLE IF NOT EXISTS `class_teacher_subject_link` (
  `teacher_id` INT(11) NOT NULL,
  `subject_id` INT(11) NOT NULL,
  `clazz_id` INT(11) NOT NULL,
  `is_active` BIT(1) NOT NULL,
  PRIMARY KEY (`teacher_id`, `subject_id`, `clazz_id`),
  INDEX `FK2cxobx1gt9ypn7xqvnnu5kelo` (`subject_id` ASC),
  INDEX `FKs14ndc8vkc4tq78by75egflka` (`clazz_id` ASC),
  CONSTRAINT `fk_class_subject_teacher1`
    FOREIGN KEY (`subject_id`)
    REFERENCES `subject` (`id`),
  CONSTRAINT `fk_class_subject_teacher2`
    FOREIGN KEY (`teacher_id`)
    REFERENCES `teacher` (`id`),
  CONSTRAINT `fk_class_subject_teacher3`
    FOREIGN KEY (`clazz_id`)
    REFERENCES `clazz` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `file` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `file` MEDIUMTEXT NULL DEFAULT NULL,
  `file_type` VARCHAR(255) NULL DEFAULT NULL,
   PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `lesson` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `clazz_id` INT(11) NOT NULL,
  `subject_id` INT(11) NOT NULL,
  `date` DATE NOT NULL,
  `hometask` VARCHAR(255) NULL DEFAULT NULL,
  `lesson_number` TINYINT(2) NOT NULL,
  `mark_type` ENUM('Labaratorna','Practic','Control','Module') CHARACTER SET utf8 COLLATE utf8_unicode_ci,
  `homework_file_id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKlqfrfxjgij4gjebdvvaeoyr14` (`clazz_id` ASC),
  CONSTRAINT `fk_lesson_class`
    FOREIGN KEY (`clazz_id`)
    REFERENCES `clazz` (`id`),
  CONSTRAINT `fk_lesson_subject`
    FOREIGN KEY (`subject_id`)
    REFERENCES `subject` (`id`),
  CONSTRAINT `fk_homework_file`
    FOREIGN KEY (`homework_file_id`)
    REFERENCES `file` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `student` (
  `id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_student_user`
    FOREIGN KEY (`id`)
    REFERENCES `user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


CREATE TABLE IF NOT EXISTS `mark` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `mark` TINYINT(2),
  `note` VARCHAR(255),
  `lesson_id` INT(11) NOT NULL,
  `student_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKqw2aqrsqut82rwjb469mpyari` (`lesson_id` ASC),
  INDEX `FKcwocngy0rfmqdhqwm3qlrfamx` (`student_id` ASC),
  CONSTRAINT `fk_mark_student`
    FOREIGN KEY (`student_id`)
    REFERENCES `student` (`id`),
  CONSTRAINT `fk_mark_lesson`
    FOREIGN KEY (`lesson_id`)
    REFERENCES `lesson` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
create unique index unique_index on mark(lesson_id,student_id);


CREATE TABLE IF NOT EXISTS `students_classes` (
  `student_id` INT(11) NOT NULL,
  `class_id` INT(11) NOT NULL,
  PRIMARY KEY (`student_id`, `class_id`),
  INDEX `FK3p1litksmc8exv2br5l5w0l9h` (`class_id` ASC),
  CONSTRAINT `fk_student_class1`
    FOREIGN KEY (`class_id`)
    REFERENCES `clazz` (`id`),
  CONSTRAINT `fk_student_class2`
    FOREIGN KEY (`student_id`)
    REFERENCES `student` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;