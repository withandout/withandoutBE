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


SET @WORKPATH = 'src/assets/upload/';
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
('ssafy1', @PW, '김영섭', '관악구', 'M', 28, '왈왈 크르릉 왈왈', 1, CONCAT(@WORKPATH, 'defaultUser.png'), 'defaultUser.png'),
('ssafy2', @PW, '김예림', '강남구', 'W', 27, '총무 겸 큰손 겸 왕자님 겸 스프링 GOD', 1, CONCAT(@WORKPATH, 'defaultUser.png'), 'defaultUser.png'),
('ssafy3', @PW, '이승헌', '강남구', 'M', 27, '2017년 제주 해녀 선정 올해의 과메기남', 0, CONCAT(@WORKPATH, 'seungheon.png'), 'seungheon.png'),
('ssafy4', @PW, '조현수', '강남구', 'M', 29, '역삼동 음식물 수거 트럭 탈취범', 0, CONCAT(@WORKPATH, 'hyunsoo.png'), 'hyunsoo.png'),
('ssafy5', @PW, '김병현', '강남구', 'M', 29, '역삼동 팬티도둑', 0, CONCAT(@WORKPATH, 'byeonghyeon.png'), 'byeonghyeon.png'),
('ssafy6', @PW, '김종인', '강남구', 'M', 29, '역삼동 발가락', 0, CONCAT(@WORKPATH, 'jongin.png'), 'jongin.png'),
('ssafy7', @PW,  '석지명', '강남구', 'M', 29, 'IM 이하 연락 금지', 0, CONCAT(@WORKPATH, 'jimyeong.png'), 'jimyeong.png'),
('ssafy8', @PW,  '유승호', '강남구', 'M', 25, '왕십리 곱창 훌라후프남', 0, CONCAT(@WORKPATH, 'defaultUser.png'), 'defaultUser.png'),
('ssafy9', @PW, '김남준', '강남구', 'M', 28, '신림역 나체 털보산적', 0, CONCAT(@WORKPATH, 'namjoon.png'), 'namjoon.png'),
('ssafy10', @PW, '김태운', '동작구', 'M', 25, '동작구 거부기', 0, CONCAT(@WORKPATH, 'defaultUser.png'), 'defaultUser.png');


INSERT INTO `wao_db`.`parties` (`name`, `sports`, `region`, `content`, `img_path`, `img_name`, `size_limit`, `fk-users-parties-no_user`)
VALUES
('그린빌 러너즈', 'running', '강남구', '금요일마다 크대대 회식합니다 2차 참여 필수!', '', '', 6, 4),
('역삼동 식핑거즈', 'running', '강남구', '헤일리의 아픈 손가락들', '', '', 6, 8),
('오늘저녁뭐먹음?', 'running', '강남구', '저녁메뉴 잘 정하는 사함 환영', '', '', 6, 4);


INSERT INTO `wao_db`.`users_parties`
(`fk-users_parties-no_party`, `fk-users_parties-no_user`, `is_accepted`, `content`, `invited_date`, `accepted_date`)
VALUES

(1, 4 , 1, '', '2023-04-26 09:00:00.007', '2019-05-26 09:00:00.007'),
(1, 5 , 1, '', '2023-07-28 09:00:00.007', '2019-08-22 09:00:00.007'),
(1, 6 , 1, '', '2023-04-27 09:00:00.007', '2019-05-27 09:00:00.007'),
(1, 3 , 0, '역삼 사는 길고양이 거둬줄 형님들 구합니다.', '2023-04-29 09:00:00.007', '2019-05-28 09:00:00.007'),
(2, 3 , 1, '', '2023-04-30 09:00:00.007', '2019-05-29 09:00:00.007'),
(2, 7 , 1, '', '2023-07-26 09:00:00.007', '2019-08-20 09:00:00.007'),
(2, 8 , 1, '', '2023-07-27 09:00:00.007', '2019-08-21 09:00:00.007'),
(2, 9 , 0, '', '2023-07-28 09:00:00.007', '2019-08-22 09:00:00.007'),
(3, 4 , 1, '', '2023-07-28 09:00:00.007', '2019-08-22 09:00:00.007'),
(3, 5 , 1, '', '2023-07-28 09:00:00.007', '2019-08-22 09:00:00.007'),
(3, 6 , 1, '', '2023-07-28 09:00:00.007', '2019-08-22 09:00:00.007'),
(3, 1 , 1, '', '2023-07-28 09:00:00.007', '2019-08-22 09:00:00.007'),
(3, 2 , 0, '물에빠진고기,해산물,기름진거,단거빼고잘먹어요', '2023-07-28 09:00:00.007', '2019-08-22 09:00:00.007');

INSERT INTO `wao_db`.`Events` (`start_time`, `end_time`, `content`, `fk-parties-events-no_party`)
VALUES
('2023-11-17 09:00:00.007', '2023-12-31 09:00:00.007', '스케쥴1', 1),
('2023-11-19 09:00:00.007', '2023-12-31 09:00:00.007', '스케쥴2', 1),
('2023-11-21 09:00:00.007', '2023-01-05 09:00:00.007', '스케쥴3', 1),
('2023-11-23 09:00:00.007', '2023-01-05 09:00:00.007', '스케쥴4', 1),
('2023-12-27 09:00:00.007', '2022-12-21 09:00:00.007', '스케쥴5', 1),
('2023-12-29 09:00:00.007', '2024-12-31 09:00:00.007', '스케쥴6', 1);



INSERT INTO `Users_Events` (`fk-users_events-no_user`, `fk-users_events-no_event`)
VALUES
(1, 1),
(1, 2),
(10, 2),
(2, 2), 
(3, 2), 
(4, 2),
(2, 3),  
(1, 4),
(1, 5),
(1, 6);


commit ;

SELECT `user_id`, `nickname`, `region`, `gender`, `age`, `content`, `is_authorized`, `img_path`, `img_name`
        FROM
        (SELECT `fk-users_parties-no_user` as `no_user`, `fk-users_parties-no_party` as `no_party`
        FROM `wao_db`.`USERS_Parties` up LEFT JOIN `wao_db`.`parties` p
        ON up.`fk-users_parties-no_party` = p.`no_party`
        WHERE p.`no_party` = 1) members LEFT JOIN `wao_db`.`Users` u
        ON members.`no_user` = u.`no_user`
        WHERE `is_authorized` = 1;
        
        
SELECT `fk-users_parties-no_user` as `no_user`, `fk-users_parties-no_party` as `no_party`, `is_accepted`
        FROM `wao_db`.`USERS_Parties` up LEFT JOIN `wao_db`.`parties` p
        ON up.`fk-users_parties-no_party` = p.`no_party`
        WHERE p.`no_party` = 1;
        
UPDATE `wao_db`.`USERS_Parties`
SET `is_accepted` = 1
WHERE `fk-users_parties-no_user` = 7 AND `fk-users_parties-no_party` = 1;

-- 승인
-- UPDATE `wao_db`.`USERS_Parties`
-- SET `is_accepted` = 1
-- WHERE `fk-users_parties-no_user` = 7 AND `fk-users_parties-no_party` = 1;

-- 거절
-- DELETE FROM `wao_db`.`USERS_Parties`
-- WHERE `fk-users_parties-no_user` = 7 AND `fk-users_parties-no_party` = 1;

SELECT `no_party` `name`, `sports`, `region`, `content`, `img_path`, `img_name`, `size_limit`, `fk-users-parties-no_user`
        FROM `wao_db`.`Parties`;
        
SELECT *
FROM `wao_db`.`USERS_Parties` up LEFT JOIN `wao_db`.`USERS` u
ON up.`fk-users_parties-no_user` = u.`no_user`;



SELECT p.`no_party`, p.`name`, p.`sports`, p.`region`, p.`content`, p.`img_path`, p.`img_name`, p.`size_limit`, p.`fk-users-parties-no_user`
FROM 
(SELECT *
FROM `wao_db`.`Users_Parties`
WHERE `fk-users_parties-no_user` = 4 AND is_accepted = 1) up LEFT JOIN `wao_db`.`Parties` p
ON up.`fk-users_parties-no_party` = p.`no_party`;

-- SELECT *
-- FROM (
-- SELECT * FROM 
-- (SELECT * FROM `users` u WHERE u.nickname = '조현수') u
-- LEFT JOIN `wao_db`.`Users_Parties` up
-- ON up.`fk-users_parties-no_user` = u.`no_user`
-- WHERE up.`is_accepted` = 1) up LEFT JOIN `wao_db`.`Parties` p
-- ON up.`fk-users_parties-no_party` = p.`no_party`;

SELECT u.`no_user`, `fk-users_parties-no_user` FROM 
(SELECT * FROM `users` u WHERE u.nickname = '조현수') u
LEFT JOIN `wao_db`.`Users_Parties` up
ON up.`fk-users_parties-no_user` = u.`no_user`
WHERE up.`is_accepted` = 1;


SELECT * FROM
(SELECT *
FROM `wao_db`.`Users_Parties` 
WHERE `fk-users_parties-no_user` = 4 AND is_accepted = 1) up LEFT JOIN `wao_db`.`Users` u
ON up.`fk-users_parties-no_user` = u.`no_user`;


-- 내 일정 조회하기 서브쿼리
SELECT us.`fk-users_events-no_event` as `no_event`, us.`fk-users_events-no_user` as `no_party`, u.`no_user`
FROM `wao_db`.`Users_Events` us LEFT JOIN  `wao_db`.`Users` u 
ON u.`no_user` = us.`fk-users_events-no_user`
WHERE u.`no_user` = 1;

-- 내 일정 조회하기 (해당 기간에 겹치는 시간이 있는지?)
SELECT *
FROM
(SELECT us.`fk-users_events-no_event` as `no_event`, us.`fk-users_events-no_user` as `no_party`, u.`no_user`
FROM `wao_db`.`Users_Events` us LEFT JOIN  `wao_db`.`Users` u 
ON u.`no_user` = us.`fk-users_events-no_user`
WHERE u.`no_user` = 1) myevent LEFT JOIN `wao_db`.`Events` evnt 
ON myevent.`no_event` = evnt.`no_event`;

-- 내 일정 조회하기 (해당 기간에 겹치는 시간이 있는지?) TO가 먼저, FROM이 다음.
SELECT *
FROM
(SELECT us.`fk-users_events-no_event` as `no_event`, us.`fk-users_events-no_user` as `no_party`, u.`no_user`
FROM `wao_db`.`Users_Events` us LEFT JOIN  `wao_db`.`Users` u 
ON u.`no_user` = us.`fk-users_events-no_user`
WHERE u.`no_user` = 1) myevent LEFT JOIN `wao_db`.`Events` evnt 
ON myevent.`no_event` = evnt.`no_event`
WHERE start_time < '2023-01-10' AND end_time > '2023-01-01';

SELECT *
FROM `wao_db`.`Users_Events` usev LEFT JOIN `wao_db`.`Events` evnt
ON evnt.`no_event` = usev.`fk-users_events-no_event`;

SELECT * FROM `wao_db`.`Users_Events`;

DELETE FROM `wao_db`.`Users_Events`
        WHERE `fk-users_events-no_user` = 1 AND `fk-users_events-no_event` = 1;

-- SELECT `no_event`, `start_time`, `end_time`, `content`, `fk-parties-events-no_party`, 
-- CASE WHEN `fk-users_events-no_user` IS NULL THEN 0 ELSE 1 END AS `is_applied`

-- CASE WHEN `fk-users_events-no_user` IS NULL THEN 0 ELSE 1 END AS `is_applied`;

-- 현재 참석자 수 조회 (완성)
SELECT `no_event`, `start_time`, `end_time`, `content`, `fk-parties-events-no_party`, COUNT(*) as `no_participant`,
CASE WHEN FIND_IN_SET('1', GROUP_CONCAT(ue.`fk-users_events-no_user`)) > 0 THEN '1' ELSE '0' END AS my_attendance_status
FROM (
SELECT * FROM `wao_db`.`Events` e
	WHERE `fk-parties-events-no_party` = 1
    AND e.`start_time` > NOW() AND e.`start_time` <= date_add(NOW(), INTERVAL 7 DAY)) pe LEFT JOIN `wao_db`.`Users_Events` ue
    ON pe.`no_event` = ue.`fk-users_events-no_event`
    GROUP BY `no_event`;
    
-- 현재 로그인한 사람이 파티장으로 있는 조회.

SELECT *
FROM 
(SELECT `no_party`
FROM `wao_db`.`Parties` p 
LEFT JOIN `wao_db`.`Users` u 
ON u.`no_user` = p.`fk-users-parties-no_user`
WHERE u.`no_user` = 4) sq LEFT JOIN `wao_db`.`USERS_Parties` up
ON sq.`no_party` = up.`fk-users_parties-no_party`
WHERE up.`is_accepted` = 0;


SELECT *
FROM
(SELECT *
FROM 
(SELECT `no_party`, `name`
FROM `wao_db`.`Parties` p 
LEFT JOIN `wao_db`.`Users` u 
ON u.`no_user` = p.`fk-users-parties-no_user`
WHERE u.`no_user` = 4) sq LEFT JOIN `wao_db`.`USERS_Parties` up
ON sq.`no_party` = up.`fk-users_parties-no_party`
WHERE up.`is_accepted` = 0) sq2 LEFT JOIN `wao_db`.`Users` u2
ON u2.`no_user` = sq2.`fk-users_parties-no_user`;


-- `no_user`, `nickname`, `content`, `age`, `gender`, `img_path`, `name` as partyName, `no_party`

SELECT *
FROM `wao_db`.`Parties` p 
LEFT JOIN `wao_db`.`Users` u 
ON u.`no_user` = p.`fk-users-parties-no_user`
WHERE u.`no_user` = 4;
