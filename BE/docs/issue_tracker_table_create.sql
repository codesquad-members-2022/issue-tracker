-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema issue_tracker
-- -----------------------------------------------------
drop SCHEMA IF EXISTS `issue_tracker` ;

-- -----------------------------------------------------
-- Schema issue_tracker
-- -----------------------------------------------------
create SCHEMA IF NOT EXISTS `issue_tracker` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `issue_tracker` ;

-- -----------------------------------------------------
-- Table `issue_tracker`.`member`
-- -----------------------------------------------------
create TABLE IF NOT EXISTS `issue_tracker`.`member` (
  `member_id` BIGINT NOT NULL AUTO_INCREMENT,
  `user_id` VARCHAR(20) NULL DEFAULT NULL,
  `name` VARCHAR(20) NULL DEFAULT NULL,
  `type` VARCHAR(10) NULL DEFAULT NULL,  
  `img_url` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`member_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `issue_tracker`.`milestone`
-- -----------------------------------------------------
create TABLE IF NOT EXISTS `issue_tracker`.`milestone` (
  `milestone_id` BIGINT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(50) NULL DEFAULT NULL,  
  `description` VARCHAR(255) NULL DEFAULT NULL,
  `completion_date` DATE NULL DEFAULT NULL,
  `member_id` BIGINT NOT NULL,
  PRIMARY KEY (`milestone_id`),
  INDEX `fk_milestone_member_id` (`member_id` ASC) VISIBLE,
  CONSTRAINT `fk_milestone_member`
    FOREIGN KEY (`member_id`)
    REFERENCES `issue_tracker`.`member` (`member_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `issue_tracker`.`issue`
-- -----------------------------------------------------
create TABLE IF NOT EXISTS `issue_tracker`.`issue` (
  `issue_id` BIGINT NOT NULL AUTO_INCREMENT,  
  `title` VARCHAR(50) NOT NULL,
  `content` VARCHAR(255) NOT NULL,
  `is_opened` BIT(1) NOT NULL,
  `member_id` BIGINT NOT NULL,
  `milestone_id` BIGINT NULL DEFAULT NULL,
  `created_at` DATETIME(6) NULL DEFAULT NULL,
  `modified_at` DATETIME(6) NULL DEFAULT NULL,
  PRIMARY KEY (`issue_id`),
  INDEX `fk_issue_member_id` (`member_id` ASC) VISIBLE,
  INDEX `fk_issue_milestone_id` (`milestone_id` ASC) VISIBLE,
  CONSTRAINT `fk_issue_member`
    FOREIGN KEY (`member_id`)
    REFERENCES `issue_tracker`.`member` (`member_id`),
  CONSTRAINT `fk_issue_milestone`
    FOREIGN KEY (`milestone_id`)
    REFERENCES `issue_tracker`.`milestone` (`milestone_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `issue_tracker`.`comment`
-- -----------------------------------------------------
create TABLE IF NOT EXISTS `issue_tracker`.`comment` (
  `comment_id` BIGINT NOT NULL AUTO_INCREMENT,
  `content` VARCHAR(255) NULL DEFAULT NULL,
  `issue_id` BIGINT NULL DEFAULT NULL,
  `member_id` BIGINT NULL DEFAULT NULL,
  `created_at` DATETIME(6) NULL DEFAULT NULL,
  `modified_at` DATETIME(6) NULL DEFAULT NULL,
  PRIMARY KEY (`comment_id`),
  INDEX `fk_comment_issue_id` (`issue_id` ASC) VISIBLE,
  INDEX `fk_comment_member_id` (`member_id` ASC) VISIBLE,
  CONSTRAINT `fk_comment_member`
    FOREIGN KEY (`member_id`)
    REFERENCES `issue_tracker`.`member` (`member_id`),
  CONSTRAINT `fk_comment_issue`
    FOREIGN KEY (`issue_id`)
    REFERENCES `issue_tracker`.`issue` (`issue_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `issue_tracker`.`emogi`
-- -----------------------------------------------------
create TABLE IF NOT EXISTS `issue_tracker`.`emogi` (
  `emogi_id` BIGINT NOT NULL AUTO_INCREMENT,
  `unicode_value` VARCHAR(50) NULL DEFAULT NULL,
  `comment_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`emogi_id`),
  INDEX `fk_emogi_comment_id` (`comment_id` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `issue_tracker`.`comment_emogi`
-- -----------------------------------------------------
create TABLE IF NOT EXISTS `issue_tracker`.`comment_emogi` (
  `comment_emogi_id` BIGINT NOT NULL AUTO_INCREMENT,
  `comment_id` BIGINT NULL DEFAULT NULL,
  `emogi_id` BIGINT NULL DEFAULT NULL,
  `member_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`comment_emogi_id`),
  INDEX `fk_comment_emogi_comment_id` (`comment_id` ASC) VISIBLE,
  INDEX `fk_comment_emogi_emogi_id` (`emogi_id` ASC) VISIBLE,
  INDEX `fk_comment_emogi_member_id` (`member_id` ASC) VISIBLE,
  CONSTRAINT `fk_comment_emogi_comment`
    FOREIGN KEY (`comment_id`)
    REFERENCES `issue_tracker`.`comment` (`comment_id`),
  CONSTRAINT `fk_comment_emogi_emogi`
    FOREIGN KEY (`emogi_id`)
    REFERENCES `issue_tracker`.`emogi` (`emogi_id`),
  CONSTRAINT `fk_comment_emogi_member`
    FOREIGN KEY (`member_id`)
    REFERENCES `issue_tracker`.`member` (`member_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `issue_tracker`.`label`
-- -----------------------------------------------------
create TABLE IF NOT EXISTS `issue_tracker`.`label` (
  `label_id` BIGINT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(50) NULL DEFAULT NULL,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  `background_color` VARCHAR(20) NULL DEFAULT NULL,  
  `is_dark_mode` BIT(1) NOT NULL,
  `member_id` BIGINT NOT NULL,
  PRIMARY KEY (`label_id`),
  INDEX `fk_label_member_id` (`member_id` ASC) VISIBLE,
  CONSTRAINT `fk_label_member`
    FOREIGN KEY (`member_id`)
    REFERENCES `issue_tracker`.`member` (`member_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `issue_tracker`.`issue_label`
-- -----------------------------------------------------
create TABLE IF NOT EXISTS `issue_tracker`.`issue_label` (
  `issue_label_id` BIGINT NOT NULL AUTO_INCREMENT,
  `issue_id` BIGINT NULL DEFAULT NULL,
  `label_id` BIGINT NULL DEFAULT NULL,
--  `member_id` BIGINT NULL DEFAULT NULL, -- 없어져도 될 것
  PRIMARY KEY (`issue_label_id`),
  INDEX `fk_issue_label_issue_id` (`issue_id` ASC) VISIBLE,
  INDEX `fk_issue_label_label_id` (`label_id` ASC) VISIBLE,
--  INDEX `fk_issue_label_member_id` (`member_id` ASC) VISIBLE,
  CONSTRAINT `fk_issue_label_issue`
    FOREIGN KEY (`issue_id`)
    REFERENCES `issue_tracker`.`issue` (`issue_id`),
  CONSTRAINT `fk_issue_label_label`
    FOREIGN KEY (`label_id`)
    REFERENCES `issue_tracker`.`label` (`label_id`))
--  CONSTRAINT `fk_issue_label_member`
--    FOREIGN KEY (`member_id`)
--    REFERENCES `issue_tracker`.`member` (`member_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

ALTER TABLE issue_label ADD UNIQUE (issue_id, label_id);

-- -----------------------------------------------------
-- Table `issue_tracker`.`issue_member`
-- -----------------------------------------------------
create TABLE IF NOT EXISTS `issue_tracker`.`issue_member` (
  `issue_member_id` BIGINT NOT NULL AUTO_INCREMENT,
  `member_id` BIGINT NULL DEFAULT NULL,
  `issue_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`issue_member_id`),
  INDEX `fk_issue_member_member_id` (`member_id` ASC) VISIBLE,
  INDEX `fk_issue_member_issue_id` (`issue_id` ASC) VISIBLE,
  CONSTRAINT `fk_issue_member_member`
    FOREIGN KEY (`member_id`)
    REFERENCES `issue_tracker`.`member` (`member_id`),
  CONSTRAINT `fk_issue_member_issue`
    FOREIGN KEY (`issue_id`)
    REFERENCES `issue_tracker`.`issue` (`issue_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- TODO : 해당 제약 사항, 컬럼 순서 무엇이 좋을 지 확인하기
ALTER TABLE issue_member ADD UNIQUE (member_id, issue_id);