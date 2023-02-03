-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema bdconcesionario
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema bdconcesionario
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bdconcesionario` DEFAULT CHARACTER SET utf8 ;
USE `bdconcesionario` ;

-- -----------------------------------------------------
-- Table `bdconcesionario`.`concesionario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdconcesionario`.`concesionario` (
  `idconcesionario` INT(9) NOT NULL,
  `pais` VARCHAR(45) NULL,
  `direccion` VARCHAR(65) NULL,
  PRIMARY KEY (`idconcesionario`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bdconcesionario`.`cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdconcesionario`.`cliente` (
  `idcliente` INT(9) NOT NULL,
  `dni` VARCHAR(45) NULL,
  `nombre` VARCHAR(45) NULL,
  `apellidos` VARCHAR(45) NULL,
  `telefono` INT(9) NULL,
  `concesionario_idconcesionario` INT(9) NOT NULL,
  PRIMARY KEY (`idcliente`),
  INDEX `fk_cliente_concesionario1_idx` (`concesionario_idconcesionario` ASC) VISIBLE,
  CONSTRAINT `fk_cliente_concesionario1`
    FOREIGN KEY (`concesionario_idconcesionario`)
    REFERENCES `bdconcesionario`.`concesionario` (`idconcesionario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bdconcesionario`.`coche`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdconcesionario`.`coche` (
  `matricula` VARCHAR(7) NOT NULL,
  `marca` VARCHAR(45) NULL,
  `fechaConstruccion` DATE NULL,
  `pais` VARCHAR(45) NULL,
  `precio` INT(9) NULL,
  `concesionario_idconcesionario` INT(9) NOT NULL,
  PRIMARY KEY (`matricula`),
  INDEX `fk_coche_concesionario_idx` (`concesionario_idconcesionario` ASC) VISIBLE,
  CONSTRAINT `fk_coche_concesionario`
    FOREIGN KEY (`concesionario_idconcesionario`)
    REFERENCES `bdconcesionario`.`concesionario` (`idconcesionario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bdconcesionario`.`distribuidor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdconcesionario`.`distribuidor` (
  `iddistribuidor` INT(9) NOT NULL,
  `nombre` VARCHAR(45) NULL,
  `pais` VARCHAR(45) NULL,
  PRIMARY KEY (`iddistribuidor`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bdconcesionario`.`distribuidor_has_concesionario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdconcesionario`.`distribuidor_has_concesionario` (
  `distribuidor_iddistribuidor` INT(9) NOT NULL,
  `concesionario_idconcesionario` INT(9) NOT NULL,
  `precioDistribucion` INT(11) NULL,
  `fechaDistribucion` DATE NULL,
  PRIMARY KEY (`distribuidor_iddistribuidor`, `concesionario_idconcesionario`),
  INDEX `fk_distribuidor_has_concesionario_concesionario1_idx` (`concesionario_idconcesionario` ASC) VISIBLE,
  INDEX `fk_distribuidor_has_concesionario_distribuidor1_idx` (`distribuidor_iddistribuidor` ASC) VISIBLE,
  CONSTRAINT `fk_distribuidor_has_concesionario_distribuidor1`
    FOREIGN KEY (`distribuidor_iddistribuidor`)
    REFERENCES `bdconcesionario`.`distribuidor` (`iddistribuidor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_distribuidor_has_concesionario_concesionario1`
    FOREIGN KEY (`concesionario_idconcesionario`)
    REFERENCES `bdconcesionario`.`concesionario` (`idconcesionario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
