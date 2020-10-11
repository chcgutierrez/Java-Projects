-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema GESTORBACK
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema GESTORBACK
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `GESTORBACK` DEFAULT CHARACTER SET utf8 ;
USE `GESTORBACK` ;

-- -----------------------------------------------------
-- Table `GESTORBACK`.`tipo_usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GESTORBACK`.`tipo_usuario` ;

CREATE TABLE IF NOT EXISTS `GESTORBACK`.`tipo_usuario` (
  `idTipoUsuario` INT NOT NULL AUTO_INCREMENT,
  `descr_tipo_usu` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`idTipoUsuario`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GESTORBACK`.`usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GESTORBACK`.`usuario` ;

CREATE TABLE IF NOT EXISTS `GESTORBACK`.`usuario` (
  `idUsuario` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(200) NOT NULL,
  `email` VARCHAR(200) NOT NULL,
  `password` VARCHAR(50) NOT NULL,
  `idTipoUsuario` INT NOT NULL,
  PRIMARY KEY (`idUsuario`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  INDEX `fk_usuario_tipo_usuario1_idx` (`idTipoUsuario` ASC),
  CONSTRAINT `fk_usuario_tipo_usuario1`
    FOREIGN KEY (`idTipoUsuario`)
    REFERENCES `GESTORBACK`.`tipo_usuario` (`idTipoUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GESTORBACK`.`estado`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GESTORBACK`.`estado` ;

CREATE TABLE IF NOT EXISTS `GESTORBACK`.`estado` (
  `idEstado` INT NOT NULL AUTO_INCREMENT,
  `desc_estado` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`idEstado`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GESTORBACK`.`categoria`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GESTORBACK`.`categoria` ;

CREATE TABLE IF NOT EXISTS `GESTORBACK`.`categoria` (
  `idCategoria` INT NOT NULL AUTO_INCREMENT,
  `desc_categoria` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`idCategoria`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GESTORBACK`.`prioridad`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GESTORBACK`.`prioridad` ;

CREATE TABLE IF NOT EXISTS `GESTORBACK`.`prioridad` (
  `idPrioridad` INT NOT NULL AUTO_INCREMENT,
  `desc_prioridad` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`idPrioridad`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GESTORBACK`.`proyecto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GESTORBACK`.`proyecto` ;

CREATE TABLE IF NOT EXISTS `GESTORBACK`.`proyecto` (
  `idProyecto` INT NOT NULL AUTO_INCREMENT,
  `desc_proyecto` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`idProyecto`),
  UNIQUE INDEX `desc_proyecto_UNIQUE` (`desc_proyecto` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GESTORBACK`.`tarea`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GESTORBACK`.`tarea` ;

CREATE TABLE IF NOT EXISTS `GESTORBACK`.`tarea` (
  `idTarea` INT NOT NULL AUTO_INCREMENT,
  `titulo` VARCHAR(200) NOT NULL,
  `detalle` VARCHAR(200) NOT NULL,
  `fecha_abre` DATETIME NULL,
  `fecha_estima_cierre` DATETIME NULL,
  `fecha_edicion` DATETIME NULL,
  `idCategoria` INT NOT NULL,
  `idPrioridad` INT NOT NULL,
  `idEstado` INT NOT NULL,
  `idUsuarioCrea` INT NOT NULL,
  `idUsuarioResuelve` INT NOT NULL,
  `idProyecto` INT NOT NULL,
  PRIMARY KEY (`idTarea`),
  INDEX `fk_tarea_categoria_idx` (`idCategoria` ASC),
  INDEX `fk_tarea_prioridad1_idx` (`idPrioridad` ASC),
  INDEX `fk_tarea_estado1_idx` (`idEstado` ASC),
  INDEX `fk_tarea_usuario1_idx` (`idUsuarioCrea` ASC),
  INDEX `fk_tarea_usuario2_idx` (`idUsuarioResuelve` ASC),
  INDEX `fk_tarea_proyecto1_idx` (`idProyecto` ASC),
  CONSTRAINT `fk_tarea_categoria`
    FOREIGN KEY (`idCategoria`)
    REFERENCES `GESTORBACK`.`categoria` (`idCategoria`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tarea_prioridad1`
    FOREIGN KEY (`idPrioridad`)
    REFERENCES `GESTORBACK`.`prioridad` (`idPrioridad`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tarea_estado1`
    FOREIGN KEY (`idEstado`)
    REFERENCES `GESTORBACK`.`estado` (`idEstado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tarea_usuario1`
    FOREIGN KEY (`idUsuarioCrea`)
    REFERENCES `GESTORBACK`.`usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tarea_usuario2`
    FOREIGN KEY (`idUsuarioResuelve`)
    REFERENCES `GESTORBACK`.`usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tarea_proyecto1`
    FOREIGN KEY (`idProyecto`)
    REFERENCES `GESTORBACK`.`proyecto` (`idProyecto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GESTORBACK`.`tarea_historial`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GESTORBACK`.`tarea_historial` ;

CREATE TABLE IF NOT EXISTS `GESTORBACK`.`tarea_historial` (
  `idTareaHistorial` INT NOT NULL AUTO_INCREMENT,
  `fecha_entrada` DATETIME NOT NULL,
  `nota_entrada` VARCHAR(300) NOT NULL,
  `idEstado` INT NOT NULL,
  `idTarea` INT NOT NULL,
  `idUsuario` INT NOT NULL,
  PRIMARY KEY (`idTareaHistorial`),
  INDEX `fk_tarea_historial_estado1_idx` (`idEstado` ASC),
  INDEX `fk_tarea_historial_tarea1_idx` (`idTarea` ASC),
  INDEX `fk_tarea_historial_usuario1_idx` (`idUsuario` ASC),
  CONSTRAINT `fk_tarea_historial_estado1`
    FOREIGN KEY (`idEstado`)
    REFERENCES `GESTORBACK`.`estado` (`idEstado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tarea_historial_tarea1`
    FOREIGN KEY (`idTarea`)
    REFERENCES `GESTORBACK`.`tarea` (`idTarea`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tarea_historial_usuario1`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `GESTORBACK`.`usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GESTORBACK`.`documento`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GESTORBACK`.`documento` ;

CREATE TABLE IF NOT EXISTS `GESTORBACK`.`documento` (
  `idDocumento` INT NOT NULL AUTO_INCREMENT,
  `url_documento` VARCHAR(300) NOT NULL,
  `idTareaHistorial` INT NOT NULL,
  PRIMARY KEY (`idDocumento`),
  INDEX `fk_documento_tarea_historial1_idx` (`idTareaHistorial` ASC),
  CONSTRAINT `fk_documento_tarea_historial1`
    FOREIGN KEY (`idTareaHistorial`)
    REFERENCES `GESTORBACK`.`tarea_historial` (`idTareaHistorial`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
