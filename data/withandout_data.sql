USE `wao_db` ;

INSERT INTO `wao_db`.`user`
(`user_id`, `password`, `nickname`, `region`, `gender`, `age`, `content`, `authorized`, `img_path`, `img_name`)
VALUES
('ssafy1', 'ssafy1', 'youngkimi', '관악구', 'M', 28, '왈왈 크르릉 왈왈', 0, null, null),
('ssafy2', 'ssafy2', 'lainlnya', '강북구', 'W', 27, '총무 겸 큰손 겸 왕자님 겸 스프링 GOD', 0, null, null),
('ssafy3', 'ssafy3', 'ectmdgjs', '강남구', 'M', 27, 'SSAFY 10기 가능성의 남자', 0, null, null),
('ssafy4', 'ssafy4', 'hyunsoo31', '강남구', 'M', 29, '역삼동 음식물 수거 트럭 탈취범', 0, null, null),
('ssafy5', 'ssafy5', 'qudgus2411', '강남구', 'M', 29, '역삼동 팬티도둑', 0, null, null),
('ssafy6', 'ssafy6', 'kirua951115', '강남구', 'M', 29, '역삼동 발가락', 0, null, null),
('ssafy7', 'ssafy7', 'jayseok423', '강남구', 'M', 29, 'IM 이하 연락 금지', 0, null, null),
('ssafy8', 'ssafy8', 'babyho99', '강남구', 'M', 25, '엄지', 0, null, null),
('ssafy9', 'ssafy9', 'wrasf175', '강남구', 'M', 28, '어?', 0, null, null),
('ssafy10', 'ssafy10', 'twnkm7089', '동작구', 'M', 25, '동작구 거부기', 0, null, null);



SELECT * from `wao_db`.`user`;
SELECT * from `wao_db`.`party`;

SELECT * from `wao_db`.`user_party`;

INSERT INTO `wao_db`.`user_party`
(`fk_invited_no_party`, `fk_invited_no_user`, `accepted`, `invited_date`, `accepted_date`)
VALUES
(7, 4 , 1, '2023-04-26 09:00:00.007', '2019-05-26 09:00:00.007'),
(7, 5 , 1, '2023-04-27 09:00:00.007', '2019-05-27 09:00:00.007'),
(7, 6 , 1, '2023-04-29 09:00:00.007', '2019-05-28 09:00:00.007'),
(7, 7 , 0, '2023-04-30 09:00:00.007', '2019-05-29 09:00:00.007'),
(8, 7 , 1, '2023-07-26 09:00:00.007', '2019-08-20 09:00:00.007'),
(8, 8 , 1, '2023-07-27 09:00:00.007', '2019-08-21 09:00:00.007'),
(8, 9 , 0, '2023-07-28 09:00:00.007', '2019-08-22 09:00:00.007');

SELECT * FROM `wao_db`.`user` u
WHERE u.user_id = 'ssafy9' AND u.password = 'ssafy9';

INSERT INTO `wao_db`.`party` (`name`, `sports`, `region`, `content`, `img_path`, `img_name`, `size`, `fk_user_no_user`)
VALUES
('그린빌 러너즈', 'running', '강남구', '금요일마다 크대대 회식합니다 2차 참여 필수!', '', '', 6, 4),
('역삼동 식핑거즈', 'running', '강남구', '헤일리의 아픈 손가락들', '', '', 6, 8);

SELECT * FROM `wao_db`.`party`;

-- -----------------------------------------------------
-- Table `mydb`.`event`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `wao_db`.`event` ;

CREATE TABLE IF NOT EXISTS `wao_db`.`event` (
  `no_event` INT NOT NULL AUTO_INCREMENT,
  `start_time` DATETIME NULL,
  `end_time` DATETIME NULL,
  `fk_event_no_party` INT NULL,
  PRIMARY KEY (`no_event`),
  UNIQUE INDEX `no_event_UNIQUE` (`no_event` ASC) VISIBLE,
  INDEX `fk_event_no_party` (`fk_event_no_party` ASC) VISIBLE,
  CONSTRAINT `fk_event_no_party`
    FOREIGN KEY (`fk_event_no_party`)
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