-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema library
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema library
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `library` DEFAULT CHARACTER SET utf8 ;
USE `library` ;

-- -----------------------------------------------------
-- Table `library`.`authors`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library`.`authors` (
  `id_author` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'Поле необходимое для установки номера (идентификатора) автора.',
  `surname` VARCHAR(45) NOT NULL COMMENT 'Поле хранит фамилию автора.',
  `name` VARCHAR(45) NOT NULL COMMENT 'Поле хранит имя автора.',
  `middle_name` VARCHAR(45) NULL DEFAULT NULL COMMENT 'Поле хранит отчество автора.',
  `country` VARCHAR(45) NOT NULL COMMENT 'Поле хранит страну рождения автора.',
  PRIMARY KEY (`id_author`),
  INDEX `country` (`country` ASC)  COMMENT 'Индекс, состоящий из страны рождения автора. Данный индекс необходим, так как возможен запрос на чтение по данному полю.',
  INDEX `surname_name_middlename` (`surname` ASC, `name` ASC, `middle_name` ASC)  COMMENT 'Индекс, состоящий из фамилии, имени и отчества автора. Так как по данным полям наиболее часто выполняется запрос на чтение.')
ENGINE = InnoDB
AUTO_INCREMENT = 25
DEFAULT CHARACTER SET = utf8
COMMENT = 'Данная таблица предназначена для хранения информации об авторах книг в библиотеке. Первичным ключом является номер автора. ';


-- -----------------------------------------------------
-- Table `library`.`books`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library`.`books` (
  `id_book` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'Поле хранит номер (идентификатор) книги и является первичным ключом.',
  `isbn` VARCHAR(13) CHARACTER SET 'utf8' COLLATE 'utf8_danish_ci' NOT NULL COMMENT 'Поле хранит международный стандартный книжный номер.',
  `tittle` VARCHAR(45) CHARACTER SET 'utf8' NOT NULL COMMENT 'Поле хранит название книги.',
  `date_edition` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_danish_ci' NOT NULL COMMENT 'Поле хранит дату издания книги.',
  `place_edition` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_danish_ci' NOT NULL COMMENT 'Поле хранит место издания книги.',
  `publisher` VARCHAR(45) CHARACTER SET 'utf8' NOT NULL COMMENT 'Поле хранит имя издательства, выпустившего книгу.',
  `number_copies` INT(11) NOT NULL COMMENT 'Поле хранит количество экземпляров книг, находящихся в библиотеке.',
  `image_book` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_danish_ci' NOT NULL,
  PRIMARY KEY (`id_book`),
  INDEX `tittle_date` (`tittle` ASC, `date_edition` ASC)  COMMENT 'Индекс служит для запроса на чтения по названию книги и даты издания.',
  INDEX `publisher_place` (`publisher` ASC, `place_edition` ASC)  COMMENT 'Индекс для запроса на чтение по таким полям, как издательство и место издания.')
ENGINE = InnoDB
AUTO_INCREMENT = 30
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_danish_ci
COMMENT = 'Данная таблица служит для хранения информации о книгах. Все поля должны быть не нулевые.';


-- -----------------------------------------------------
-- Table `library`.`book_author`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library`.`book_author` (
  `id_book` INT(11) NOT NULL COMMENT 'Поле хранит номер (идентификатор) книги. Также является внешним ключом, предназначено для хранения значения первичного ключа таблицы \"books\" с целью организации связи между этими таблицами.',
  `id_author` INT(11) NOT NULL COMMENT 'Поле хранит номер (идентификатор) автора. Также является внешним ключом, предназначено для хранения значения первичного ключа таблицы \"authors\" с целью организации связи между этими таблицами.',
  PRIMARY KEY (`id_book`, `id_author`),
  INDEX `id_author_idx` (`id_author` ASC),
  CONSTRAINT `id_author`
    FOREIGN KEY (`id_author`)
    REFERENCES `library`.`authors` (`id_author`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `id_book_author`
    FOREIGN KEY (`id_book`)
    REFERENCES `library`.`books` (`id_book`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'Данная таблица хранит информацию об определенных авторах определенных книг. Состоит из составного первичного ключа.';


-- -----------------------------------------------------
-- Table `library`.`genres`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library`.`genres` (
  `id_genre` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'Поле хранит номер (идентификатор) жанра и является первичным ключом.',
  `name_genre` VARCHAR(43) NOT NULL COMMENT 'Поле хранит название жанра.',
  PRIMARY KEY (`id_genre`),
  INDEX `name_genre` (`name_genre` ASC)  COMMENT 'Индекс, состоящий из названия жанра. Необходим для запроса на чтение по полю \"название жанра\".')
ENGINE = InnoDB
AUTO_INCREMENT = 21
DEFAULT CHARACTER SET = utf8
COMMENT = 'Данная таблица хранит информацию о жанрах. Все поля являются не нулевыми.';


-- -----------------------------------------------------
-- Table `library`.`book_genre`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library`.`book_genre` (
  `id_book` INT(11) NOT NULL COMMENT 'Поле хранит номер (идентификатор) книги. Также является внешним ключом, предназначено для хранения значения первичного ключа таблицы \"books\" с целью организации связи между этими таблицами.',
  `id_genre` INT(11) NOT NULL COMMENT 'Поле хранит номер (идентификатор) жанра. Также является внешним ключом, предназначено для хранения значения первичного ключа таблицы \"genres\" с целью организации связи между этими таблицами.',
  PRIMARY KEY (`id_book`, `id_genre`),
  INDEX `id_genre_idx` (`id_genre` ASC),
  INDEX `id_genre_book_idx` (`id_genre` ASC),
  CONSTRAINT `id_book_genre`
    FOREIGN KEY (`id_book`)
    REFERENCES `library`.`books` (`id_book`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `id_genre`
    FOREIGN KEY (`id_genre`)
    REFERENCES `library`.`genres` (`id_genre`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'Данная таблица хранит информацию об определенных жанрах определеных книг. Содержит составной первичный ключ.';


-- -----------------------------------------------------
-- Table `library`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library`.`users` (
  `id_user` INT(11) NOT NULL AUTO_INCREMENT,
  `number_ticket` INT(11) NULL DEFAULT NULL COMMENT 'Поле хранит номер билета читателя, является первичным ключом и уникальным, так как каждый читатель имеет свой уникальный номер билета.',
  `id_role` INT(11) NOT NULL COMMENT 'Поле хранит идентификатор роли пользователя. Является внешним ключом, предназначено для хранения значения первичного ключа таблицы \"roles\" с целью организации связи между этими таблицами.',
  `surname` VARCHAR(45) NOT NULL COMMENT 'Поле хранит фамилию читателя.',
  `name` VARCHAR(45) NOT NULL COMMENT 'Поле хранит имя читателя.',
  `middle_name` VARCHAR(45) NOT NULL COMMENT 'Поле хранит отчество читателя.',
  `age` INT(11) NULL DEFAULT NULL COMMENT 'Поле хранит дату возраст читателя.',
  `phone_number` VARCHAR(20) NULL DEFAULT NULL COMMENT 'Поле хранит номер телефона читателя.',
  `mail` VARCHAR(45) NULL DEFAULT NULL COMMENT 'Поле хранит почтовый адрес читателя.',
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `image` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE INDEX `number_ticket_UNIQUE` (`number_ticket` ASC)  COMMENT 'Индекс, состоящий из номера билета читателя и являющийся уникальным, так как у каждого читателя уникальный номер билета. Необходим для запроса на чтения по данному полю.',
  INDEX `surname_name_middlename` (`surname` ASC, `name` ASC, `middle_name` ASC)  COMMENT 'Индекс является составным и содержит в себе такие поля, как фамилия, имя и отчество читателя. Необъодим для запроса на чтения по данным полям.',
  INDEX `role_fk_idx` (`id_role` ASC)  COMMENT 'Индекс состоит из идентификатора роли пользователя.')
ENGINE = InnoDB
AUTO_INCREMENT = 14
DEFAULT CHARACTER SET = utf8
COMMENT = 'Данная таблица хранит информацию о читателях. Все поля должны быть не нулевыми.'
KEY_BLOCK_SIZE = 8;


-- -----------------------------------------------------
-- Table `library`.`borrow_book`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library`.`borrow_book` (
  `id_record` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'Поле хранит номер (идентификатор) записи выдачи книг. Является первичным ключом.',
  `id_user` INT(11) NOT NULL COMMENT 'Поле хранит номер билета читателя. Является внешним ключом, предназначено для хранения значения первичного ключа таблицы \"readers\" с целью организации связи между этими таблицами.',
  `id_book` INT(11) NOT NULL COMMENT 'Поле хранит номер (идентификатор) книги. Является внешним ключом, предназначено для хранения значения первичного ключа таблицы \"books\" с целью организации связи между этими таблицами.',
  `date_borrow` DATE NOT NULL COMMENT 'Поле хранит дату выдачи книги читателю.',
  `date_return` DATE NOT NULL COMMENT 'Поле хранит дату сдачи книги читателем в библиотеку.',
  `method_borrow` VARCHAR(45) NOT NULL COMMENT 'Поле хранит способ взятия книги читателем (абонемент или в читальный зал).',
  PRIMARY KEY (`id_record`),
  INDEX `date_borrow` (`date_borrow` ASC)  COMMENT 'Индекс состоит из поля \"дата выдачи книг\". Необходим для запроса на чтение по данному полю.',
  INDEX `date_return` (`date_return` ASC)  COMMENT 'Индекс состоит из поля \"дата сдачи книг\". Необходим для запроса на чтение по данному полю.',
  INDEX `method_borrow` (`method_borrow` ASC)  COMMENT 'Индекс состоит из поля \"способ выдачи книг\". Необходим для запроса на чтение по данному полю.',
  INDEX `id_book_idx` (`id_book` ASC),
  INDEX `id_user_idx` (`id_user` ASC),
  CONSTRAINT `id_book`
    FOREIGN KEY (`id_book`)
    REFERENCES `library`.`books` (`id_book`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `id_user`
    FOREIGN KEY (`id_user`)
    REFERENCES `library`.`users` (`id_user`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 40
DEFAULT CHARACTER SET = utf8
COMMENT = 'Данная таблица хранит информацию о выдаче книг читателям. Все поля являются не нулевыми.';


-- -----------------------------------------------------
-- Table `library`.`orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library`.`orders` (
  `id_order` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'Поле хранит номер (идентификатор) заказа. Является первичным ключом',
  `id_user` INT(11) NOT NULL COMMENT 'Поле хранит номер билета читателя. Является внешним ключом, предназначено для хранения значения первичного ключа таблицы \"readers\" с целью организации связи между этими таблицами.',
  `id_book` INT(11) NOT NULL COMMENT 'Поле хранит номер (идентификатор) книги. Является внешним ключом, предназначено для хранения значения первичного ключа таблицы \"books\" с целью организации связи между этими таблицами.',
  `id_author` INT(11) NOT NULL COMMENT 'Поле хранит номер (идентификатор) автора. Является внешним ключом, предназначено для хранения значения первичного ключа таблицы \"authors\" с целью организации связи между этими таблицами.',
  PRIMARY KEY (`id_order`),
  INDEX `id_book_order_idx` (`id_book` ASC),
  INDEX `id_user_order_idx` (`id_user` ASC),
  CONSTRAINT `id_book_order`
    FOREIGN KEY (`id_book`)
    REFERENCES `library`.`books` (`id_book`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `id_user_order`
    FOREIGN KEY (`id_user`)
    REFERENCES `library`.`users` (`id_user`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 87
DEFAULT CHARACTER SET = utf8
COMMENT = 'Данная таблица хранит данные о заказе книг клиентом. Все поля являются не нулевыми.';


-- -----------------------------------------------------
-- Table `library`.`roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library`.`roles` (
  `id_role` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'Поле хранит номер (идентификатор) роли. Является первичным ключом.',
  `name_role` VARCHAR(45) NOT NULL COMMENT 'Поле хранит название роли (администратор или пользователь).',
  PRIMARY KEY (`id_role`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8
COMMENT = 'Данная таблица служит для хранения информации о роля, исполняющихся в приложении (администратор или пользователь).Все поля должны быть не нулевые';


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
