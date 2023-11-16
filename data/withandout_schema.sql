DROP SCHEMA IF EXISTS `wao_db` ;

-- CREATE DB withandout
CREATE SCHEMA IF NOT EXISTS `wao_db` DEFAULT CHARACTER SET utf8 ;

USE `wao_db` ;

-- CREATE schema USERS
DROP TABLE IF EXISTS `wao_db`.`Users` ;

CREATE TABLE IF NOT EXISTS `wao_db`.`Users` (
  `no_user` INT NOT NULL AUTO_INCREMENT,
  `user_id` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `nickname` VARCHAR(45) NOT NULL,
  `region` VARCHAR(45) NOT NULL,
  `gender` VARCHAR(45) NOT NULL,
  `age` INT NOT NULL,
  `content` TINYTEXT NULL,
  `is_authorized` TINYINT NOT NULL DEFAULT 0,
  `img_path` VARCHAR(300) NULL,
  `img_name` VARCHAR(200) NULL,
  PRIMARY KEY (`no_user`),
  UNIQUE INDEX `no_user_UNIQUE` (`no_user` ASC) VISIBLE,
  UNIQUE INDEX `userid_UNIQUE` (`user_id` ASC) VISIBLE,
  UNIQUE INDEX `nickname_UNIQUE` (`nickname` ASC) VISIBLE)
ENGINE = InnoDB;

-- CREATE schema PARTIES
DROP TABLE IF EXISTS `wao_db`.`Parties` ;

CREATE TABLE IF NOT EXISTS `wao_db`.`Parties` (
  `no_party` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `sports` VARCHAR(45) NOT NULL DEFAULT 'running',
  `region` VARCHAR(45) NOT NULL,
  `content` TINYTEXT NULL,
  `img_path` VARCHAR(300) NULL,
  `img_name` VARCHAR(200) NULL,
  `size_limit` INT NOT NULL,
  `fk-users-parties-no_user` INT NOT NULL,
  PRIMARY KEY (`no_party`),
  UNIQUE INDEX `no_party_UNIQUE` (`no_party` ASC) VISIBLE,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE,
  INDEX `no_user_idx` (`fk-users-parties-no_user` ASC) VISIBLE,
  CONSTRAINT `no_user`
    FOREIGN KEY (`fk-users-parties-no_user`)
    REFERENCES `wao_db`.`users` (`no_user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- CREATE schema EVENTS 
DROP TABLE IF EXISTS `wao_db`.`events` ;

CREATE TABLE IF NOT EXISTS `wao_db`.`events` (
  `no_event` INT NOT NULL AUTO_INCREMENT,
  `start_time` DATETIME NULL,
  `end_time` DATETIME NULL,
  `fk-parties-events-no_parties` INT NULL,
  PRIMARY KEY (`no_event`),
  UNIQUE INDEX `no_event_UNIQUE` (`no_event` ASC) VISIBLE,
  INDEX `fk-parties-events-no_parties_idx` (`fk-parties-events-no_parties` ASC) VISIBLE,
  CONSTRAINT `fk-parties-events-no_parties`
    FOREIGN KEY (`fk-parties-events-no_parties`)
    REFERENCES `wao_db`.`parties` (`no_party`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

DROP TABLE IF EXISTS `wao_db`.`users_and_parties` ;

CREATE TABLE IF NOT EXISTS `wao_db`.`users_and_parties` (
  `fk-parties-uap-no_party` INT NOT NULL,
  `fk_users-uap-no_user` INT NOT NULL,
  `is_accepted` TINYINT NOT NULL DEFAULT 0,
  `invited_date` DATETIME NULL,
  `accepted_date` DATETIME NULL,
  PRIMARY KEY (`fk-parties-uap-no_party`, `fk_users-uap-no_user`),
  INDEX `fk_users-uap-no_user_idx` (`fk_users-uap-no_user` ASC) VISIBLE,
  CONSTRAINT `fk-parties-uap-no_party`
    FOREIGN KEY (`fk-parties-uap-no_party`)
    REFERENCES `wao_db`.`parties` (`no_party`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_users-uap-no_user`
    FOREIGN KEY (`fk_users-uap-no_user`)
    REFERENCES `wao_db`.`users` (`no_user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SELECT * FROM `wao_db`.`users`;

commit ;