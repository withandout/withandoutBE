DROP SCHEMA IF EXISTS `wao_db` ;

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `wao_db` DEFAULT CHARACTER SET utf8 ;
-- -----------------------------------------------------
-- Schema ssafy_withandout
-- -----------------------------------------------------
USE `wao_db` ;

-- -----------------------------------------------------
-- Table `mydb`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `wao_db`.`user` ;

CREATE TABLE IF NOT EXISTS `wao_db`.`user` (
  `no_user` INT NOT NULL AUTO_INCREMENT,
  `user_id` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `nickname` VARCHAR(45) NOT NULL,
  `region` VARCHAR(45) NOT NULL,
  `gender` VARCHAR(45) NOT NULL,
  `age` INT NOT NULL,
  `content` TINYTEXT NULL,
  `authorized` TINYINT NOT NULL DEFAULT 0,
  `img_path` VARCHAR(300) NULL,
  `img_name` VARCHAR(200) NULL,
  PRIMARY KEY (`no_user`),
  UNIQUE INDEX `no_user_UNIQUE` (`no_user` ASC) VISIBLE,
  UNIQUE INDEX `userid_UNIQUE` (`user_id` ASC) VISIBLE,
  UNIQUE INDEX `nickname_UNIQUE` (`nickname` ASC) VISIBLE)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `mydb`.`party`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `wao_db`.`party` ;

CREATE TABLE IF NOT EXISTS `wao_db`.`party` (
  `no_party` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `sports` VARCHAR(45) NOT NULL DEFAULT 'running',
  `region` VARCHAR(45) NOT NULL,
  `content` TINYTEXT NULL,
  `img_path` VARCHAR(300) NULL,
  `img_name` VARCHAR(200) NULL,
  `size` INT NOT NULL,
  `fk_user_no_user` INT NOT NULL,
  PRIMARY KEY (`no_party`),
  UNIQUE INDEX `no_party_UNIQUE` (`no_party` ASC) VISIBLE,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE,
  INDEX `no_user_idx` (`fk_user_no_user` ASC) VISIBLE,
  CONSTRAINT `no_user`
    FOREIGN KEY (`fk_user_no_user`)
    REFERENCES `wao_db`.`user` (`no_user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`schedule`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `wao_db`.`schedule` ;

CREATE TABLE IF NOT EXISTS `wao_db`.`schedule` (
  `no_schedule` INT NOT NULL AUTO_INCREMENT,
  `start_time` DATETIME NULL,
  `end_time` DATETIME NULL,
  `fk_party_no_party` INT NULL,
  PRIMARY KEY (`no_schedule`),
  UNIQUE INDEX `no_schedule_UNIQUE` (`no_schedule` ASC) VISIBLE,
  INDEX `fk_party_no_party_idx` (`fk_party_no_party` ASC) VISIBLE,
  CONSTRAINT `fk_party_no_party`
    FOREIGN KEY (`fk_party_no_party`)
    REFERENCES `wao_db`.`party` (`no_party`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

DROP TABLE IF EXISTS `wao_db`.`user_party` ;

CREATE TABLE IF NOT EXISTS `wao_db`.`user_party` (
  `fk_invited_no_party` INT NOT NULL,
  `fk_invited_no_user` INT NOT NULL,
  `accepted` TINYINT NOT NULL DEFAULT 0,
  `invited_date` DATETIME NULL,
  `accepted_date` DATETIME NULL,
  PRIMARY KEY (`fk_invited_no_party`, `fk_invited_no_user`),
  INDEX `fk_invited_no_user_idx` (`fk_invited_no_user` ASC) VISIBLE,
  CONSTRAINT `fk_invited_no_party`
    FOREIGN KEY (`fk_invited_no_party`)
    REFERENCES `wao_db`.`party` (`no_party`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_invited_no_user`
    FOREIGN KEY (`fk_invited_no_user`)
    REFERENCES `wao_db`.`user` (`no_user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SELECT * FROM `wao_db`.`event`;

commit ;