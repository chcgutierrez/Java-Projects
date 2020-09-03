-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema Transportadora
-- -----------------------------------------------------
-- Sistema de transporte para Web

-- -----------------------------------------------------
-- Schema Transportadora
--
-- Sistema de transporte para Web
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Transportadora` DEFAULT CHARACTER SET utf8 ;
USE `Transportadora` ;

-- -----------------------------------------------------
-- Table `Transportadora`.`MaestraTipoDocumento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Transportadora`.`MaestraTipoDocumento` (
  `idMaestraTipoDocumento` INT NOT NULL AUTO_INCREMENT,
  `cod_tipodoc` VARCHAR(5) NOT NULL,
  `Descripcion` VARCHAR(150) NOT NULL,
  `fec_con` DATETIME NULL,
  `login` VARCHAR(20) NULL,
  `obs` VARCHAR(200) NULL,
  PRIMARY KEY (`idMaestraTipoDocumento`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Transportadora`.`MaestraSexo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Transportadora`.`MaestraSexo` (
  `idMaestraSexo` INT NOT NULL AUTO_INCREMENT,
  `cod_sexo` VARCHAR(5) NOT NULL,
  `Descripcion` VARCHAR(100) NOT NULL,
  `fec_con` DATETIME NULL,
  `login` VARCHAR(20) NULL,
  `obs` VARCHAR(200) NULL,
  PRIMARY KEY (`idMaestraSexo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Transportadora`.`MaestraPersona`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Transportadora`.`MaestraPersona` (
  `idMaestraPersona` INT NOT NULL AUTO_INCREMENT,
  `num_documento` VARCHAR(20) NOT NULL,
  `pri_nombre` VARCHAR(50) NOT NULL,
  `seg_nombre` VARCHAR(50) NOT NULL,
  `pri_apellido` VARCHAR(50) NOT NULL,
  `seg_apellido` VARCHAR(50) NOT NULL,
  `direccion` VARCHAR(100) NULL,
  `telefono` VARCHAR(20) NOT NULL,
  `email` VARCHAR(200) NULL,
  `fec_nace` DATE NOT NULL,
  `edad` INT NOT NULL,
  `fec_con` DATETIME NULL,
  `login` VARCHAR(20) NULL,
  `obs` VARCHAR(200) NULL,
  `idTipoDoc` INT NOT NULL,
  `idSexo` INT NOT NULL,
  PRIMARY KEY (`idMaestraPersona`),
  INDEX `fk_MaestraPersona_MaestraTipoDocumento1_idx` (`idTipoDoc` ASC),
  INDEX `fk_MaestraPersona_MaestraSexo1_idx` (`idSexo` ASC),
  CONSTRAINT `fk_MaestraPersona_MaestraTipoDocumento1`
    FOREIGN KEY (`idTipoDoc`)
    REFERENCES `Transportadora`.`MaestraTipoDocumento` (`idMaestraTipoDocumento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_MaestraPersona_MaestraSexo1`
    FOREIGN KEY (`idSexo`)
    REFERENCES `Transportadora`.`MaestraSexo` (`idMaestraSexo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Transportadora`.`MaestraEstados`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Transportadora`.`MaestraEstados` (
  `idMaestraEstados` INT NOT NULL AUTO_INCREMENT,
  `Descripcion` VARCHAR(100) NOT NULL,
  `fec_con` DATETIME NULL,
  `login` VARCHAR(20) NULL,
  `obs` VARCHAR(200) NULL,
  PRIMARY KEY (`idMaestraEstados`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Transportadora`.`Cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Transportadora`.`Cliente` (
  `cod_cliente` VARCHAR(20) NOT NULL,
  `login_cli` VARCHAR(45) NOT NULL,
  `password_cli` VARCHAR(45) NOT NULL,
  `idPersona` INT NOT NULL,
  `est_cliente` INT NOT NULL,
  PRIMARY KEY (`idPersona`, `cod_cliente`),
  INDEX `fk_Cliente_MaestraEstados1_idx` (`est_cliente` ASC),
  CONSTRAINT `fk_Cliente_MaestraPersona1`
    FOREIGN KEY (`idPersona`)
    REFERENCES `Transportadora`.`MaestraPersona` (`idMaestraPersona`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Cliente_MaestraEstados1`
    FOREIGN KEY (`est_cliente`)
    REFERENCES `Transportadora`.`MaestraEstados` (`idMaestraEstados`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Transportadora`.`EncabezadoReserva`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Transportadora`.`EncabezadoReserva` (
  `idEncabezadoReserva` INT NOT NULL AUTO_INCREMENT,
  `fec_con` DATETIME NULL,
  `login` VARCHAR(20) NULL,
  `obs` VARCHAR(200) NULL,
  `idPersona` INT NOT NULL,
  `idCliente` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`idEncabezadoReserva`),
  INDEX `fk_EncabezadoReserva_Cliente1_idx` (`idPersona` ASC, `idCliente` ASC),
  CONSTRAINT `fk_EncabezadoReserva_Cliente1`
    FOREIGN KEY (`idPersona` , `idCliente`)
    REFERENCES `Transportadora`.`Cliente` (`idPersona` , `cod_cliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Transportadora`.`MaestraPais`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Transportadora`.`MaestraPais` (
  `idMaestraPais` INT NOT NULL AUTO_INCREMENT,
  `Descripcion` VARCHAR(120) NOT NULL,
  `fec_con` DATETIME NULL,
  `login` VARCHAR(20) NULL,
  `obs` VARCHAR(200) NULL,
  PRIMARY KEY (`idMaestraPais`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Transportadora`.`MaestraDpto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Transportadora`.`MaestraDpto` (
  `idMaestraDpto` INT NOT NULL AUTO_INCREMENT,
  `Descripcion` VARCHAR(150) NOT NULL,
  `fec_con` DATETIME NULL,
  `login` VARCHAR(20) NULL,
  `obs` VARCHAR(200) NULL,
  `idPais` INT NOT NULL,
  PRIMARY KEY (`idMaestraDpto`),
  INDEX `fk_MaestraDpto_MaestraPais1_idx` (`idPais` ASC),
  CONSTRAINT `fk_MaestraDpto_MaestraPais1`
    FOREIGN KEY (`idPais`)
    REFERENCES `Transportadora`.`MaestraPais` (`idMaestraPais`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Transportadora`.`MaestraCiudad`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Transportadora`.`MaestraCiudad` (
  `idMaestraCiudad` INT NOT NULL AUTO_INCREMENT,
  `Descripcion` VARCHAR(150) NOT NULL,
  `fec_con` DATETIME NULL,
  `login` VARCHAR(20) NULL,
  `obs` VARCHAR(200) NULL,
  `idDpto` INT NOT NULL,
  PRIMARY KEY (`idMaestraCiudad`),
  INDEX `fk_MaestraCiudad_MaestraDpto1_idx` (`idDpto` ASC),
  CONSTRAINT `fk_MaestraCiudad_MaestraDpto1`
    FOREIGN KEY (`idDpto`)
    REFERENCES `Transportadora`.`MaestraDpto` (`idMaestraDpto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Transportadora`.`DetalleReserva`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Transportadora`.`DetalleReserva` (
  `idDetReserva` INT NOT NULL AUTO_INCREMENT,
  `asiento` VARCHAR(2) NOT NULL,
  `fec_reserva` DATETIME NOT NULL,
  `fec_viaje` DATE NOT NULL,
  `hora_viaje` VARCHAR(3) NOT NULL,
  `min_viaje` VARCHAR(3) NOT NULL,
  `pago_total` DECIMAL(18,2) NOT NULL,
  `retorno` INT NOT NULL,
  `fec_retorno` DATE NULL,
  `hora_retorno` VARCHAR(3) NULL,
  `min_retorno` VARCHAR(3) NULL,
  `total_pasajeros` INT NOT NULL,
  `fec_con` DATETIME NULL,
  `login` VARCHAR(20) NULL,
  `obs` VARCHAR(200) NULL,
  `idEncabezadoReserva` INT NOT NULL,
  `estado_rsva` INT NOT NULL,
  `idCiudadOR` INT NOT NULL,
  `idCiudadDE` INT NOT NULL,
  PRIMARY KEY (`idDetReserva`),
  INDEX `fk_DetalleReserva_EncabezadoReserva1_idx` (`idEncabezadoReserva` ASC),
  INDEX `fk_DetalleReserva_MaestraEstados1_idx` (`estado_rsva` ASC),
  INDEX `fk_DetalleReserva_MaestraCiudad1_idx` (`idCiudadOR` ASC),
  INDEX `fk_DetalleReserva_MaestraCiudad2_idx` (`idCiudadDE` ASC),
  CONSTRAINT `fk_DetalleReserva_EncabezadoReserva1`
    FOREIGN KEY (`idEncabezadoReserva`)
    REFERENCES `Transportadora`.`EncabezadoReserva` (`idEncabezadoReserva`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_DetalleReserva_MaestraEstados1`
    FOREIGN KEY (`estado_rsva`)
    REFERENCES `Transportadora`.`MaestraEstados` (`idMaestraEstados`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_DetalleReserva_MaestraCiudad1`
    FOREIGN KEY (`idCiudadOR`)
    REFERENCES `Transportadora`.`MaestraCiudad` (`idMaestraCiudad`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_DetalleReserva_MaestraCiudad2`
    FOREIGN KEY (`idCiudadDE`)
    REFERENCES `Transportadora`.`MaestraCiudad` (`idMaestraCiudad`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Transportadora`.`Empleado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Transportadora`.`Empleado` (
  `salario` DECIMAL(18,2) NOT NULL,
  `login_emp` VARCHAR(45) NOT NULL,
  `password_emp` VARCHAR(45) NOT NULL,
  `idPersona` INT NOT NULL,
  `estado_emp` INT NOT NULL,
  PRIMARY KEY (`idPersona`),
  INDEX `fk_Empleado_MaestraEstados1_idx` (`estado_emp` ASC),
  CONSTRAINT `fk_Empleado_MaestraPersona`
    FOREIGN KEY (`idPersona`)
    REFERENCES `Transportadora`.`MaestraPersona` (`idMaestraPersona`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Empleado_MaestraEstados1`
    FOREIGN KEY (`estado_emp`)
    REFERENCES `Transportadora`.`MaestraEstados` (`idMaestraEstados`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
