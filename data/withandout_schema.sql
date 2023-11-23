-- -----------------------------------------------------
-- Schema wao_db
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `wao_db` ;

-- -----------------------------------------------------
-- Schema wao_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `wao_db` DEFAULT CHARACTER SET utf8 ;
-- -----------------------------------------------------
-- Schema ssafy_withandout
-- -----------------------------------------------------
USE `wao_db` ;

-- -----------------------------------------------------
-- Table `wao_db`.`Users`
-- -----------------------------------------------------
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


-- -----------------------------------------------------
-- Table `wao_db`.`Parties`
-- -----------------------------------------------------
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
  `fk-users-parties-no_user` INT NULL,
  PRIMARY KEY (`no_party`),
  UNIQUE INDEX `no_party_UNIQUE` (`no_party` ASC) VISIBLE,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE,
  INDEX `fk-users-parties-no_user_idx` (`fk-users-parties-no_user` ASC) VISIBLE,
  CONSTRAINT `fk-users-parties-no_user`
    FOREIGN KEY (`fk-users-parties-no_user`)
    REFERENCES `wao_db`.`Users` (`no_user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `wao_db`.`Events`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `wao_db`.`Events` ;

CREATE TABLE IF NOT EXISTS `wao_db`.`Events` (
  `no_event` INT NOT NULL AUTO_INCREMENT,
  `start_time` DATETIME NOT NULL,
  `end_time` DATETIME NOT NULL,
  `content` TINYTEXT NULL,
  `fk-parties-events-no_party` INT NOT NULL,
  PRIMARY KEY (`no_event`),
  UNIQUE INDEX `no_schedule_UNIQUE` (`no_event` ASC) VISIBLE,
  INDEX `fk-parties-events-no_party_idx` (`fk-parties-events-no_party` ASC) VISIBLE,
  CONSTRAINT `fk-parties-events-no_party`
    FOREIGN KEY (`fk-parties-events-no_party`)
    REFERENCES `wao_db`.`Parties` (`no_party`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `wao_db`.`Users_Parties`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `wao_db`.`Users_Parties` ;

CREATE TABLE IF NOT EXISTS `wao_db`.`Users_Parties` (
  `fk-users_parties-no_party` INT NOT NULL,
  `fk-users_parties-no_user` INT NOT NULL,
  `is_accepted` TINYINT NOT NULL DEFAULT 0,
  `content` TINYTEXT NULL,
  `invited_date` DATETIME NOT NULL,
  `accepted_date` DATETIME NULL,
  PRIMARY KEY (`fk-users_parties-no_party`, `fk-users_parties-no_user`),
  CONSTRAINT `fk-users_parties-no_party2`
    FOREIGN KEY (`fk-users_parties-no_party`)
    REFERENCES `wao_db`.`Parties` (`no_party`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk-users_parties-no_user2`
    FOREIGN KEY (`fk-users_parties-no_user`)
    REFERENCES `wao_db`.`Users` (`no_user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `wao_db`.`Users_Events`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `wao_db`.`Users_Events` ;

CREATE TABLE IF NOT EXISTS `wao_db`.`Users_Events` (
  `fk-users_events-no_user` INT NOT NULL,
  `fk-users_events-no_event` INT NOT NULL,
  PRIMARY KEY (`fk-users_events-no_user`, `fk-users_events-no_event`),
  INDEX `fk-users_events-no_event_idx` (`fk-users_events-no_event` ASC) VISIBLE,
  CONSTRAINT `fk-users_events-no_user`
    FOREIGN KEY (`fk-users_events-no_user`)
    REFERENCES `wao_db`.`Users` (`no_user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk-users_events-no_event`
    FOREIGN KEY (`fk-users_events-no_event`)
    REFERENCES `wao_db`.`Events` (`no_event`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `wao_db`.`Articles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `wao_db`.`Articles` ;

CREATE TABLE IF NOT EXISTS `wao_db`.`Articles` (
  `no_article` INT NOT NULL AUTO_INCREMENT,
  `fk-users-articles-no_user` INT NOT NULL,
  `fk-parties-articles-no_party` INT NOT NULL,
  `content` TINYTEXT NULL,
  `reg_date` DATETIME NOT NULL,
  `img_path` VARCHAR(300) NULL,
  `img_name` VARCHAR(200) NULL,
  PRIMARY KEY (`no_article`),
  UNIQUE INDEX `no_article_UNIQUE` (`no_article` ASC) VISIBLE,
  INDEX `fk-users-articles-no_user_idx` (`fk-users-articles-no_user` ASC) VISIBLE,
  INDEX `fk-parties-articles-no_party_idx` (`fk-parties-articles-no_party` ASC) VISIBLE,
  CONSTRAINT `fk-users-articles-no_user`
    FOREIGN KEY (`fk-users-articles-no_user`)
    REFERENCES `wao_db`.`Users` (`no_user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk-parties-articles-no_party`
    FOREIGN KEY (`fk-parties-articles-no_party`)
    REFERENCES `wao_db`.`Parties` (`no_party`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `wao_db`.`RUNNING_LOG`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `wao_db`.`RUNNING_LOG` ;

CREATE TABLE IF NOT EXISTS `wao_db`.`RUNNING_LOG` (
  `no_log` INT NOT NULL AUTO_INCREMENT,
  `fk-users-log-no_user` INT NOT NULL,
  `stt_time` DATETIME NOT NULL,
  `end_time` DATETIME NOT NULL,
  -- 미터 단위.
  `distance` INT NOT NULL, 
  PRIMARY KEY (`no_log`)
  )
ENGINE = InnoDB;



SET @WORKPATH = '/src/assets/upload/';
SET @GALLERYPATH = '/src/assets/img/gallery/';
SET @PW = 'aaaaaaaa';

SELECT * FROM `users`;
SELECT * FROM `parties`;
SELECT * FROM `events`;
SELECT * FROM `users_parties`;
SELECT * FROM `articles`;
SELECT * FROM `users_events`;

commit ;

USE `wao_db` ;

INSERT INTO `wao_db`.`users`
(`user_id`, `password`, `nickname`, `region`, `gender`, `age`, `content`, `is_authorized`, `img_path`, `img_name`)
VALUES
('ssafy1', @PW, '김영섭', '강남구', '남성', 28, '왈왈 크르릉 왈왈', 0, CONCAT(@WORKPATH, 'defaultUser.png'), 'defaultUser.png'),
('ssafy2', @PW, '김예림', '강남구', '여성', 27, '총무 겸 큰손 겸 왕자님 겸 스프링 GOD', 1, CONCAT(@WORKPATH, 'defaultUser.png'), 'defaultUser.png'),
('ssafy3', @PW, '이승헌', '강남구', '남성', 27, '2017년 제주 해녀 선정 올해의 과메기남', 1, CONCAT(@WORKPATH, 'seungheon.png'), 'seungheon.png'),
('ssafy4', @PW, '조현수', '강남구', '남성', 29, '역삼 음식물 수거 트럭 탈취범', 1, CONCAT(@WORKPATH, 'hyunsoo.png'), 'hyunsoo.png'),
('ssafy5', @PW, '김병현', '강남구', '남성', 29, '역삼동 팬티도둑', 1, CONCAT(@WORKPATH, 'byeonghyeon.png'), 'byeonghyeon.png'),
('ssafy6', @PW, '김종인', '강남구', '남성', 29, '역삼동 발가락', 1, CONCAT(@WORKPATH, 'jongin.png'), 'jongin.png'),
('ssafy7', @PW, '석지명', '강남구', '남성', 29, 'IM 이하 연락 금지', 1, CONCAT(@WORKPATH, 'jimyeong.png'), 'jimyeong.png'),
('ssafy8', @PW, '유승호', '강남구', '남성', 25, '왕십리 곱창 훌라후프남', 1, CONCAT(@WORKPATH, 'defaultUser.png'), 'defaultUser.png'),
('ssafy9', @PW, '김남준', '강남구', '남성', 28, '신림역 나체 털보산적', 1, CONCAT(@WORKPATH, 'namjoon.png'), 'namjoon.png'),
('ssafy10', @PW, '김태운', '강남구', '남성', 25, '동작구 거부기', 1, CONCAT(@WORKPATH, 'defaultUser.png'), 'defaultUser.png'),
('ssafy11', @PW, '조용환', '강남구', '남성', 29, '아빠 안잔다', 1, CONCAT(@WORKPATH, 'yonghwan.png'), 'yonghwan.png'),
('ssafy12', @PW, '황인승', '강남구', '남성', 28, '역삼동 2019년생 김지환군의 개나리공원 미끄럼틀 경쟁자', 1, CONCAT(@WORKPATH, 'inseung.png'), 'inseung.png'), 
('ssafy13', @PW, '김선영', '강남구', '여성', 29, '송파구 쇠사슬', 1, CONCAT(@WORKPATH, 'defaultUser.png'), 'defaultUser.png'), 
('ssafy14', @PW, '김유경', '강남구', '여성', 25, '쿠치키 뱌쿠야', 1, CONCAT(@WORKPATH, 'defaultUser.png'), 'defaultUser.png'), 
('ssafy15', @PW, '김지은', '강남구', '여성', 26, '절뚝이는 기현이 옆에서 슬릭백 추는 사람', 1, CONCAT(@WORKPATH, 'defaultUser.png'), 'defaultUser.png'), 
('ssafy16', @PW, '노세희', '강남구', '여성', 25, '올리비아 핫세', 1, CONCAT(@WORKPATH, 'defaultUser.png'), 'defaultUser.png'), 
('ssafy17', @PW, '류기현', '강남구', '남성', 25, '목동 카이저 소제', 1, CONCAT(@WORKPATH, 'defaultUser.png'), 'defaultUser.png'), 
('ssafy18', @PW, '문성현', '강남구', '여성', 28, '난토쟝', 1, CONCAT(@WORKPATH, 'defaultUser.png'), 'defaultUser.png'), 
('ssafy19', @PW, '배유열', '강남구', '남성', 28, '알빠노', 1, CONCAT(@WORKPATH, 'defaultUser.png'), 'defaultUser.png'), 
('ssafy20', @PW, '정유경', '강남구', '여성', 26, '김병현 김병현', 1, CONCAT(@WORKPATH, 'defaultUser.png'), 'defaultUser.png'),
('ssafy21', @PW, '정현아', '강남구', '여성', 26, '침묵의 007빵', 1, CONCAT(@WORKPATH, 'defaultUser.png'), 'defaultUser.png'),
('ssafy22', @PW, '유현정', '강남구', '여성', 26, '토블론 밀크(노란색)', 1, CONCAT(@WORKPATH, 'defaultUser.png'), 'defaultUser.png');


INSERT INTO `wao_db`.`parties` (`name`, `sports`, `region`, `content`, `img_path`, `img_name`, `size_limit`, `fk-users-parties-no_user`)
VALUES
('그린빌 러너즈', '러닝', '강남구', '금요일마다 크대대 회식합니다 2차 참여 필수', CONCAT(@WORKPATH, 'defaultParty.png'), 'defaultParty.png', 8, 4),
('역삼동 식핑거즈', '러닝', '강남구', '헤일리의 아픈 손가락들', CONCAT(@WORKPATH, 'defaultParty.png'), 'defaultParty.png', 8, 8),
('오저뭐먹?', '러닝', '강남구', '메뉴 잘 정하는 사람 환영', CONCAT(@WORKPATH, 'defaultParty.png'), 'defaultParty.png', 10, 4),
('역삼동 세븐프린세스', '러닝', '강남구', '리더 김선영을 주축으로 한 철저한 상명복종', CONCAT(@WORKPATH, 'defaultParty.png'), 'defaultParty.png', 7, 13);

INSERT INTO `wao_db`.`users_parties`
(`fk-users_parties-no_party`, `fk-users_parties-no_user`, `is_accepted`, `content`, `invited_date`, `accepted_date`)
VALUES
(1, 1 , 1, '', '2023-04-26 09:00:00.007', '2019-05-26 09:00:00.007'),
(1, 3 , 0, '역삼 사는 길고양이 거둬줄 형님들 구합니다.', '2023-04-29 09:00:00.007', '2019-05-28 09:00:00.007'),
(1, 4 , 1, '', '2023-04-26 09:00:00.007', '2019-05-26 09:00:00.007'),
(1, 5 , 1, '', '2023-07-28 09:00:00.007', '2019-08-22 09:00:00.007'),
(1, 6 , 1, '', '2023-04-27 09:00:00.007', '2019-05-27 09:00:00.007'),
(1, 7 , 1, '', '2023-04-27 09:00:00.007', '2019-05-27 09:00:00.007'),
(2, 3 , 1, '', '2023-04-30 09:00:00.007', '2019-05-29 09:00:00.007'),
(2, 7 , 1, '', '2023-07-26 09:00:00.007', '2019-08-20 09:00:00.007'),
(2, 8 , 1, '', '2023-07-27 09:00:00.007', '2019-08-21 09:00:00.007'),
(2, 9 , 1, '', '2023-07-28 09:00:00.007', '2019-08-22 09:00:00.007'),
(2, 4 , 1, '', '2023-07-28 09:00:00.007', '2019-08-22 09:00:00.007'),
(2, 5 , 1, '', '2023-07-28 09:00:00.007', '2019-08-22 09:00:00.007'),
(2, 6 , 1, '', '2023-07-28 09:00:00.007', '2019-08-22 09:00:00.007'),
(3, 4 , 1, '', '2023-07-28 09:00:00.007', '2019-08-22 09:00:00.007'),
(3, 1 , 1, '', '2023-07-28 09:00:00.007', '2019-08-22 09:00:00.007'),
(3, 10 , 1, '', '2023-07-28 09:00:00.007', '2019-08-22 09:00:00.007'),
(3, 11 , 1, '', '2023-07-28 09:00:00.007', '2019-08-22 09:00:00.007'),
(3, 12 , 1, '', '2023-07-28 09:00:00.007', '2019-08-22 09:00:00.007'),
(3, 2 , 0, '물에빠진고기,해산물,기름진거,단거빼고잘먹어요', '2023-07-28 09:00:00.007', '2019-08-22 09:00:00.007'),
(4, 13 , 1, '', '2023-07-28 09:00:00.007', '2019-08-22 09:00:00.007'),
(4, 14 , 1, '', '2023-07-28 09:00:00.007', '2019-08-22 09:00:00.007'),
(4, 15 , 1, '', '2023-07-28 09:00:00.007', '2019-08-22 09:00:00.007'),
(4, 20 , 1, '', '2023-07-28 09:00:00.007', '2019-08-22 09:00:00.007'),
(4, 21 , 1, '', '2023-07-28 09:00:00.007', '2019-08-22 09:00:00.007');


INSERT INTO `wao_db`.`Events` (`start_time`, `end_time`, `content`, `fk-parties-events-no_party`)
VALUES
('2023-11-25 07:00:00.000', '2023-11-25 08:00:00.000', '주말 아침 러닝', 2),
('2023-11-26 16:00:00.000', '2023-11-26 20:00:00.000', '하프 마라톤 연습', 1),
('2023-11-24 22:00:00.000', '2023-11-25 01:00:00.000', '강남역 알부자', 1),
('2023-11-27 09:00:00.000', '2023-11-27 11:00:00.000', '온라인수업 기념 산책', 1),
('2023-11-24 18:00:00.000', '2023-11-24 23:00:00.000', '역삼역 하몽하몽', 2),
('2023-11-25 07:00:00.000', '2023-11-25 11:00:00.000', '탄천 따라 모닝 러닝', 2),
('2023-11-27 12:00:00.000', '2023-11-27 17:00:00.000', '조용환의 맛따라멋따라', 3),
('2023-11-24 13:00:00.000', '2023-11-24 17:00:00.000', '관악산 백슉먹짜했짜나', 3);

INSERT INTO `Users_Events` (`fk-users_events-no_user`, `fk-users_events-no_event`)
VALUES
(4, 1),
(4, 3),
(4, 5),
(5, 1),
(5, 4),
(5, 5),
(6, 1);

INSERT INTO `wao_db`.`Articles`
(`fk-users-articles-no_user`, `fk-parties-articles-no_party`,
`content`, `reg_date`, `img_path`, `img_name`)
VALUES
(4, 1, '', '2023-11-10 01:00:00.000', CONCAT(@GALLERYPATH, 'greenvil_1.png'), 'greenvil_1'),
(4, 1, '', '2023-11-11 01:00:00.000', CONCAT(@GALLERYPATH, 'greenvil_2.png'), 'greenvil_2'),
(4, 1, '', '2023-11-12 01:00:00.000', CONCAT(@GALLERYPATH, 'greenvil_3.png'), 'greenvil_3'),
(4, 1, '', '2023-11-13 01:00:00.000', CONCAT(@GALLERYPATH, 'greenvil_4.png'), 'greenvil_4'),
(4, 1, '', '2023-11-14 01:00:00.000', CONCAT(@GALLERYPATH, 'greenvil_5.png'), 'greenvil_5'),
(4, 1, '', '2023-11-15 01:00:00.000', CONCAT(@GALLERYPATH, 'greenvil_6.png'), 'greenvil_6'),
(4, 1, '', '2023-11-16 01:00:00.000', CONCAT(@GALLERYPATH, 'greenvil_7.png'), 'greenvil_7'),
(4, 1, '', '2023-11-17 01:00:00.000', CONCAT(@GALLERYPATH, 'greenvil_8.png'), 'greenvil_8'),
(4, 1, '', '2023-11-18 01:00:00.000', CONCAT(@GALLERYPATH, 'greenvil_9.png'), 'greenvil_9'),
(11, 3, '', '2023-11-10 01:00:00.000', CONCAT(@GALLERYPATH, 'prin7_1.png'), 'prin7_1'),
(11, 3, '', '2023-11-11 01:00:00.000', CONCAT(@GALLERYPATH, 'prin7_2.png'), 'prin7_2'),
(11, 3, '', '2023-11-12 01:00:00.000', CONCAT(@GALLERYPATH, 'prin7_3.png'), 'prin7_3'),
(11, 3, '', '2023-11-13 01:00:00.000', CONCAT(@GALLERYPATH, 'prin7_4.png'), 'prin7_4'),
(11, 3, '', '2023-11-14 01:00:00.000', CONCAT(@GALLERYPATH, 'prin7_5.png'), 'prin7_5'),
(8, 2, '', '2023-11-10 01:00:00.000', CONCAT(@GALLERYPATH, 'sick_1.png'), 'sick_1'),
(8, 2, '', '2023-11-11 01:00:00.000', CONCAT(@GALLERYPATH, 'sick_2.png'), 'sick_2'),
(8, 2, '', '2023-11-12 01:00:00.000', CONCAT(@GALLERYPATH, 'sick_3.png'), 'sick_3'),
(8, 2, '', '2023-11-15 01:00:00.000', CONCAT(@GALLERYPATH, 'greenvil_6.png'), 'greenvil_6'),
(8, 2, '', '2023-11-16 01:00:00.000', CONCAT(@GALLERYPATH, 'greenvil_7.png'), 'greenvil_7'),
(8, 2, '', '2023-11-17 01:00:00.000', CONCAT(@GALLERYPATH, 'greenvil_8.png'), 'greenvil_8'),
(8, 2, '', '2023-11-18 01:00:00.000', CONCAT(@GALLERYPATH, 'greenvil_9.png'), 'greenvil_9'),
(20, 4, '', '2023-11-10 01:00:00.000', CONCAT(@GALLERYPATH, 'prin7_1.png'), 'prin7_1'),
(20, 4, '', '2023-11-11 01:00:00.000', CONCAT(@GALLERYPATH, 'prin7_2.png'), 'prin7_2'),
(20, 4, '', '2023-11-12 01:00:00.000', CONCAT(@GALLERYPATH, 'prin7_3.png'), 'prin7_3'),
(20, 4, '', '2023-11-13 01:00:00.000', CONCAT(@GALLERYPATH, 'prin7_4.png'), 'prin7_4'),
(20, 4, '', '2023-11-14 01:00:00.000', CONCAT(@GALLERYPATH, 'prin7_5.png'), 'prin7_5');

-- CREATE TABLE IF NOT EXISTS `wao_db`.`Articles` (
--   `no_article` INT NOT NULL AUTO_INCREMENT,
--   `fk-users-articles-no_user` INT NOT NULL,
--   `fk-parties-articles-no_party` INT NOT NULL,
--   `content` TINYTEXT NULL,
--   `reg_date` DATETIME NOT NULL,
--   `img_path` VARCHAR(300) NULL,
--   `img_name` VARCHAR(200) NULL

INSERT INTO `wao_db`.`RUNNING_LOG`
(`fk-users-log-no_user`, `stt_time`, `end_time`, `distance`)
VALUES
(FLOOR( 1 + RAND() *21), '2023-11-17 19:00:00.000', '2023-11-17 19:30:00.000', 1000 + FLOOR( 1 + (RAND() *3000))),
(FLOOR( 1 + RAND() *21), '2023-11-17 19:00:00.000', '2023-11-17 19:30:00.000', 1000 + FLOOR( 1 + (RAND() *3000))),
(FLOOR( 1 + RAND() *21), '2023-11-17 19:00:00.000', '2023-11-17 19:30:00.000', 1000 + FLOOR( 1 + (RAND() *3000))),
(FLOOR( 1 + RAND() *21), '2023-11-17 19:00:00.000', '2023-11-17 19:30:00.000', 1000 + FLOOR( 1 + (RAND() *3000))),
(FLOOR( 1 + RAND() *21), '2023-11-17 19:00:00.000', '2023-11-17 19:30:00.000', 1000 + FLOOR( 1 + (RAND() *3000))),
(FLOOR( 1 + RAND() *21), '2023-11-17 19:00:00.000', '2023-11-17 19:30:00.000', 1000 + FLOOR( 1 + (RAND() *3000))),
(FLOOR( 1 + RAND() *21), '2023-11-17 19:00:00.000', '2023-11-17 19:30:00.000', 1000 + FLOOR( 1 + (RAND() *3000))),
(FLOOR( 1 + RAND() *21), '2023-11-17 19:00:00.000', '2023-11-17 19:30:00.000', 1000 + FLOOR( 1 + (RAND() *3000))),
(FLOOR( 1 + RAND() *21), '2023-11-17 19:00:00.000', '2023-11-17 19:30:00.000', 1000 + FLOOR( 1 + (RAND() *3000))),
(FLOOR( 1 + RAND() *21), '2023-11-17 19:00:00.000', '2023-11-17 19:30:00.000', 1000 + FLOOR( 1 + (RAND() *3000))),
(FLOOR( 1 + RAND() *21), '2023-11-17 19:00:00.000', '2023-11-17 19:30:00.000', 1000 + FLOOR( 1 + (RAND() *3000))),
(FLOOR( 1 + RAND() *21), '2023-11-17 19:00:00.000', '2023-11-17 19:30:00.000', 1000 + FLOOR( 1 + (RAND() *3000))),
(FLOOR( 1 + RAND() *21), '2023-11-17 19:00:00.000', '2023-11-17 19:30:00.000', 1000 + FLOOR( 1 + (RAND() *3000))),
(FLOOR( 1 + RAND() *21), '2023-11-17 19:00:00.000', '2023-11-17 19:30:00.000', 1000 + FLOOR( 1 + (RAND() *3000))),
(FLOOR( 1 + RAND() *21), '2023-11-17 19:00:00.000', '2023-11-17 19:30:00.000', 1000 + FLOOR( 1 + (RAND() *3000))),
(FLOOR( 1 + RAND() *21), '2023-11-17 19:00:00.000', '2023-11-17 19:30:00.000', 1000 + FLOOR( 1 + (RAND() *3000))),
(FLOOR( 1 + RAND() *21), '2023-11-17 19:00:00.000', '2023-11-17 19:30:00.000', 1000 + FLOOR( 1 + (RAND() *3000))),
(FLOOR( 1 + RAND() *21), '2023-11-17 19:00:00.000', '2023-11-17 19:30:00.000', 1000 + FLOOR( 1 + (RAND() *3000))),
(FLOOR( 1 + RAND() *21), '2023-11-17 19:00:00.000', '2023-11-17 19:30:00.000', 1000 + FLOOR( 1 + (RAND() *3000))),
(FLOOR( 1 + RAND() *21), '2023-11-17 19:00:00.000', '2023-11-17 19:30:00.000', 1000 + FLOOR( 1 + (RAND() *3000))),
(FLOOR( 1 + RAND() *21), '2023-11-17 19:00:00.000', '2023-11-17 19:30:00.000', 1000 + FLOOR( 1 + (RAND() *3000))),
(FLOOR( 1 + RAND() *21), '2023-11-17 19:00:00.000', '2023-11-17 19:30:00.000', 1000 + FLOOR( 1 + (RAND() *3000))),
(FLOOR( 1 + RAND() *21), '2023-11-17 19:00:00.000', '2023-11-17 19:30:00.000', 1000 + FLOOR( 1 + (RAND() *3000))),
(FLOOR( 1 + RAND() *21), '2023-11-17 19:00:00.000', '2023-11-17 19:30:00.000', 1000 + FLOOR( 1 + (RAND() *3000))),
(FLOOR( 1 + RAND() *21), '2023-11-17 19:00:00.000', '2023-11-17 19:30:00.000', 1000 + FLOOR( 1 + (RAND() *3000)));


SELECT * FROM 
`wao_db`.`RUNNING_LOG`;

commit ;


-- 파티 멤버 전체 조회 
    

SELECT `no_user`, `nickname`, `img_path`, 
CASE WHEN SUM(`distance`) IS NULL THEN 0
ELSE SUM(`distance`) END AS distance, COUNT(*) as running_cnt
FROM 
(SELECT u.`no_user`, `nickname`, `img_path`
FROM
(SELECT `fk-users_parties-no_user` as `no_user`, `fk-users_parties-no_party` as `no_party`, `is_accepted`
FROM `wao_db`.`USERS_Parties` up LEFT JOIN `wao_db`.`parties` p
ON up.`fk-users_parties-no_party` = p.`no_party`
WHERE p.`no_party` = 1 AND `is_accepted` = 1) members LEFT JOIN `wao_db`.`Users` u
ON members.`no_user` = u.`no_user`) ptmember
LEFT JOIN `wao_db`.`RUNNING_LOG` r_log
ON ptmember.`no_user` = r_log.`fk-users-log-no_user`
WHERE r_log.`stt_time` >= DATE_SUB(NOW(), INTERVAL 1 WEEK) OR `no_log` IS NULL
GROUP BY ptmember.`no_user`
ORDER BY distance DESC;
-- , SUM(distance), COUNT(*)



SELECT u.`no_user`, SUM(r_log.`distance`) AS distance, COUNT(*) AS running_cnt
FROM `wao_db`.`USERS` u
LEFT JOIN
`wao_db`.`RUNNING_LOG` r_log ON u.`no_user` = r_log.`fk-users-log-no_user`
WHERE u.`no_user` = 4 AND r_log.`stt_time` >= DATE_SUB(NOW(), INTERVAL 7 DAY)
GROUP BY u.`no_user`;


