-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema tpfinalpoo
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema tpfinalpoo
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `tpfinalpoo` ;
USE `tpfinalpoo` ;

-- -----------------------------------------------------
-- Table `tpfinalpoo`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tpfinalpoo`.`usuario` (
                                                      `nome` VARCHAR(45) NOT NULL,
                                                      `descricao` VARCHAR(45) NOT NULL,
                                                      `dataNascimento` DATE NOT NULL,
                                                      `formacao` VARCHAR(45) NOT NULL,
                                                      `username` VARCHAR(45) NOT NULL,
                                                      `tipoUsuario` TINYINT(1) NULL,
                                                      PRIMARY KEY (`username`),
                                                      UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tpfinalpoo`.`disciplina`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tpfinalpoo`.`disciplina` (
                                                         `codDisciplina` VARCHAR(45) NOT NULL,
                                                         `nome` VARCHAR(45) NOT NULL,
                                                         `descricao` VARCHAR(45) NOT NULL,
                                                         PRIMARY KEY (`codDisciplina`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tpfinalpoo`.`topico`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tpfinalpoo`.`topico` (
                                                     `idTopico` INT NOT NULL AUTO_INCREMENT,
                                                     `descricao` VARCHAR(45) NOT NULL,
                                                     PRIMARY KEY (`idTopico`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tpfinalpoo`.`aula`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tpfinalpoo`.`aula` (
                                                   `codAula` VARCHAR(45) NOT NULL,
                                                   `valorHora` DECIMAL(4,2) NOT NULL,
                                                   `descricao` VARCHAR(45) NOT NULL,
                                                   `codDisciplina` VARCHAR(45) NOT NULL,
                                                   `usernameProfessor` VARCHAR(45) NOT NULL,
                                                   PRIMARY KEY (`codAula`, `codDisciplina`, `usernameProfessor`),
                                                   INDEX `fk_aula_disciplina1_idx` (`codDisciplina` ASC) VISIBLE,
                                                   INDEX `fk_aula_professor_idx` (`usernameProfessor` ASC) VISIBLE,
                                                   CONSTRAINT `fk_aula_disciplina1`
                                                       FOREIGN KEY (`codDisciplina`)
                                                           REFERENCES `tpfinalpoo`.`disciplina` (`codDisciplina`)
                                                           ON DELETE NO ACTION
                                                           ON UPDATE NO ACTION,
                                                   CONSTRAINT `fk_aula_professor`
                                                       FOREIGN KEY (`usernameProfessor`)
                                                           REFERENCES `tpfinalpoo`.`usuario` (`username`)
                                                           ON DELETE NO ACTION
                                                           ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tpfinalpoo`.`contratoAula`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tpfinalpoo`.`contratoAula` (
                                                           `usernameAluno` VARCHAR(45) NOT NULL,
                                                           `codAula` VARCHAR(45) NOT NULL,
                                                           `codContrato` VARCHAR(45) NULL,
                                                           `dataFinal` DATE NULL,
                                                           `dataInicio` DATE NULL,
                                                           PRIMARY KEY (`usernameAluno`, `codAula`),
                                                           INDEX `fk_aluno_has_aula_aula1_idx` (`codAula` ASC) VISIBLE,
                                                           UNIQUE INDEX `cod_contrato_UNIQUE` (`codContrato` ASC) VISIBLE,
                                                           CONSTRAINT `fk_aluno_has_aula_aula1`
                                                               FOREIGN KEY (`codAula`)
                                                                   REFERENCES `tpfinalpoo`.`aula` (`codAula`)
                                                                   ON DELETE NO ACTION
                                                                   ON UPDATE NO ACTION,
                                                           CONSTRAINT `fk_contratoAula_usernameAluno`
                                                               FOREIGN KEY (`usernameAluno`)
                                                                   REFERENCES `tpfinalpoo`.`usuario` (`username`)
                                                                   ON DELETE NO ACTION
                                                                   ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tpfinalpoo`.`topicoAula`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tpfinalpoo`.`topicoAula` (
                                                         `idTopico` INT NOT NULL,
                                                         `codAula` VARCHAR(45) NOT NULL,
                                                         PRIMARY KEY (`idTopico`, `codAula`),
                                                         INDEX `fk_topico_has_aula_aula1_idx` (`codAula` ASC) VISIBLE,
                                                         INDEX `fk_topico_has_aula_topico1_idx` (`idTopico` ASC) VISIBLE,
                                                         CONSTRAINT `fk_topico_has_aula_topico1`
                                                             FOREIGN KEY (`idTopico`)
                                                                 REFERENCES `tpfinalpoo`.`topico` (`idTopico`)
                                                                 ON DELETE NO ACTION
                                                                 ON UPDATE NO ACTION,
                                                         CONSTRAINT `fk_topico_has_aula_aula1`
                                                             FOREIGN KEY (`codAula`)
                                                                 REFERENCES `tpfinalpoo`.`aula` (`codAula`)
                                                                 ON DELETE NO ACTION
                                                                 ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tpfinalpoo`.`avaliacaoUsuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tpfinalpoo`.`avaliacaoUsuario` (
                                                               `usernameAvaliador` VARCHAR(45) NOT NULL,
                                                               `usernameProfessor` VARCHAR(45) NOT NULL,
                                                               `comentario` VARCHAR(45) NULL,
                                                               `valor` DECIMAL(2,2) NULL,
                                                               PRIMARY KEY (`usernameAvaliador`, `usernameProfessor`),
                                                               INDEX `fk_usernameProfessor_idx` (`usernameProfessor` ASC) VISIBLE,
                                                               CONSTRAINT `fk_avaliacao_usernameAvaliador`
                                                                   FOREIGN KEY (`usernameAvaliador`)
                                                                       REFERENCES `tpfinalpoo`.`usuario` (`username`)
                                                                       ON DELETE NO ACTION
                                                                       ON UPDATE NO ACTION,
                                                               CONSTRAINT `fk_avaliacao_usernameAvaliado`
                                                                   FOREIGN KEY (`usernameProfessor`)
                                                                       REFERENCES `tpfinalpoo`.`usuario` (`username`)
                                                                       ON DELETE NO ACTION
                                                                       ON UPDATE NO ACTION)
    ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
