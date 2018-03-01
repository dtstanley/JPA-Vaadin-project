-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema fullfillmint3
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `fullfillmint3` ;

-- -----------------------------------------------------
-- Schema fullfillmint3
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `fullfillmint3` DEFAULT CHARACTER SET utf8 ;
USE `fullfillmint3` ;

-- -----------------------------------------------------
-- Table `fullfillmint3`.`category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fullfillmint3`.`category` ;

CREATE TABLE IF NOT EXISTS `fullfillmint3`.`category` (
  `idcategory` INT(11) NOT NULL AUTO_INCREMENT,
  `category_travel` VARCHAR(45) NULL DEFAULT NULL,
  `category_health` VARCHAR(45) NULL DEFAULT NULL,
  `category_lifeMilestone` VARCHAR(45) NULL DEFAULT NULL,
  `category_educNsk` VARCHAR(45) NULL DEFAULT NULL,
  `category_adventure` VARCHAR(60) NULL DEFAULT NULL,
  `category_outdoors` VARCHAR(45) NULL DEFAULT NULL,
  `category_entertainment` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idcategory`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `fullfillmint3`.`goal_history`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fullfillmint3`.`goal_history` ;

CREATE TABLE IF NOT EXISTS `fullfillmint3`.`goal_history` (
  `idgoal` INT(11) NOT NULL AUTO_INCREMENT,
  `goal_title` VARCHAR(45) NULL DEFAULT NULL,
  `goal_targetDate` DATE NULL DEFAULT NULL,
  `goal_startDatel` DATE NULL DEFAULT NULL,
  `goal_description` MULTILINESTRING NULL DEFAULT NULL,
  `goal_accPartner` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`idgoal`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `fullfillmint3`.`activity`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fullfillmint3`.`activity` ;

CREATE TABLE IF NOT EXISTS `fullfillmint3`.`activity` (
  `idactivity` INT(11) NOT NULL AUTO_INCREMENT,
  `activity_date` DATE NULL DEFAULT NULL,
  `activity_location` VARCHAR(45) NULL DEFAULT NULL,
  `activity_theme` VARCHAR(45) NULL DEFAULT NULL,
  `goal_history_idgoal` INT(11) NOT NULL,
  `step*` VARCHAR(45) NULL DEFAULT NULL,
  `category_idcategory` INT(11) NOT NULL,
  PRIMARY KEY (`idactivity`, `goal_history_idgoal`, `category_idcategory`),
  CONSTRAINT `fk_activity_category1`
    FOREIGN KEY (`category_idcategory`)
    REFERENCES `fullfillmint3`.`category` (`idcategory`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_activity_goal_history1`
    FOREIGN KEY (`goal_history_idgoal`)
    REFERENCES `fullfillmint3`.`goal_history` (`idgoal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE INDEX `fk_activity_goal_history1_idx` ON `fullfillmint3`.`activity` (`goal_history_idgoal` ASC);

CREATE INDEX `fk_activity_category1_idx` ON `fullfillmint3`.`activity` (`category_idcategory` ASC);


-- -----------------------------------------------------
-- Table `fullfillmint3`.`activity_level`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fullfillmint3`.`activity_level` ;

CREATE TABLE IF NOT EXISTS `fullfillmint3`.`activity_level` (
  `idactivity_level` INT(11) NOT NULL AUTO_INCREMENT,
  `beginner` VARCHAR(45) NULL DEFAULT NULL,
  `intermediate` VARCHAR(45) NULL DEFAULT NULL,
  `advanced` VARCHAR(45) NULL DEFAULT NULL,
  `pro` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idactivity_level`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `fullfillmint3`.`activity_level_has_activity`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fullfillmint3`.`activity_level_has_activity` ;

CREATE TABLE IF NOT EXISTS `fullfillmint3`.`activity_level_has_activity` (
  `activity_level_idactivity_level` INT(11) NOT NULL,
  `activity_idactivity` INT(11) NOT NULL,
  PRIMARY KEY (`activity_level_idactivity_level`, `activity_idactivity`),
  CONSTRAINT `fk_activity_level_has_activity_activity1`
    FOREIGN KEY (`activity_idactivity`)
    REFERENCES `fullfillmint3`.`activity` (`idactivity`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_activity_level_has_activity_activity_level1`
    FOREIGN KEY (`activity_level_idactivity_level`)
    REFERENCES `fullfillmint3`.`activity_level` (`idactivity_level`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE INDEX `fk_activity_level_has_activity_activity1_idx` ON `fullfillmint3`.`activity_level_has_activity` (`activity_idactivity` ASC);

CREATE INDEX `fk_activity_level_has_activity_activity_level1_idx` ON `fullfillmint3`.`activity_level_has_activity` (`activity_level_idactivity_level` ASC);


-- -----------------------------------------------------
-- Table `fullfillmint3`.`competed_goal`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fullfillmint3`.`competed_goal` ;

CREATE TABLE IF NOT EXISTS `fullfillmint3`.`competed_goal` (
  `idcompeted_goal` INT(11) NOT NULL AUTO_INCREMENT,
  `goal_title` VARCHAR(45) NULL DEFAULT NULL,
  `goal_history_idgoal` INT(11) NOT NULL,
  PRIMARY KEY (`idcompeted_goal`),
  CONSTRAINT `fk_competed_goal_goal_history1`
    FOREIGN KEY (`goal_history_idgoal`)
    REFERENCES `fullfillmint3`.`goal_history` (`idgoal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE INDEX `fk_competed_goal_goal_history1_idx` ON `fullfillmint3`.`competed_goal` (`goal_history_idgoal` ASC);


-- -----------------------------------------------------
-- Table `fullfillmint3`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fullfillmint3`.`user` ;

CREATE TABLE IF NOT EXISTS `fullfillmint3`.`user` (
  `iduser` INT(11) NOT NULL AUTO_INCREMENT,
  `user_first_name` VARCHAR(45) NULL DEFAULT NULL,
  `user_last_name` VARCHAR(45) NULL DEFAULT NULL,
  `user_email` VARCHAR(75) NULL DEFAULT NULL,
  PRIMARY KEY (`iduser`))
ENGINE = InnoDB
AUTO_INCREMENT = 4866
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `fullfillmint3`.`user_has_category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fullfillmint3`.`user_has_category` ;

CREATE TABLE IF NOT EXISTS `fullfillmint3`.`user_has_category` (
  `user_iduser` INT(11) NOT NULL,
  `category_idcategory` INT(11) NOT NULL,
  PRIMARY KEY (`user_iduser`, `category_idcategory`),
  CONSTRAINT `fk_user_has_category_category1`
    FOREIGN KEY (`category_idcategory`)
    REFERENCES `fullfillmint3`.`category` (`idcategory`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_category_user1`
    FOREIGN KEY (`user_iduser`)
    REFERENCES `fullfillmint3`.`user` (`iduser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE INDEX `fk_user_has_category_category1_idx` ON `fullfillmint3`.`user_has_category` (`category_idcategory` ASC);

CREATE INDEX `fk_user_has_category_user1_idx` ON `fullfillmint3`.`user_has_category` (`user_iduser` ASC);


-- -----------------------------------------------------
-- Table `fullfillmint3`.`user_has_goal_history`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fullfillmint3`.`user_has_goal_history` ;

CREATE TABLE IF NOT EXISTS `fullfillmint3`.`user_has_goal_history` (
  `user_iduser` INT(11) NOT NULL,
  `goal_history_idgoal` INT(11) NOT NULL,
  PRIMARY KEY (`user_iduser`, `goal_history_idgoal`),
  CONSTRAINT `fk_user_has_goal_history_goal_history1`
    FOREIGN KEY (`goal_history_idgoal`)
    REFERENCES `fullfillmint3`.`goal_history` (`idgoal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_goal_history_user`
    FOREIGN KEY (`user_iduser`)
    REFERENCES `fullfillmint3`.`user` (`iduser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE INDEX `fk_user_has_goal_history_goal_history1_idx` ON `fullfillmint3`.`user_has_goal_history` (`goal_history_idgoal` ASC);

CREATE INDEX `fk_user_has_goal_history_user_idx` ON `fullfillmint3`.`user_has_goal_history` (`user_iduser` ASC);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
