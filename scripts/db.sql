-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema tpfinalpoo
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema tpfinalpoo
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `tpfinalpoo`;
USE `tpfinalpoo` ;

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
-- Table `tpfinalpoo`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tpfinalpoo`.`usuario` (
                                                      `nome` VARCHAR(45) NOT NULL,
                                                      `descricao` VARCHAR(45) NOT NULL,
                                                      `dataNascimento` DATE NOT NULL,
                                                      `formacao` VARCHAR(45) NOT NULL,
                                                      `username` VARCHAR(45) NOT NULL,
                                                      `tipoUsuario` TINYINT(1) NULL DEFAULT NULL,
                                                      PRIMARY KEY (`username`),
                                                      UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tpfinalpoo`.`aula`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tpfinalpoo`.`aula` (
                                                   `codAula` VARCHAR(45) NOT NULL,
                                                   `descricao` VARCHAR(45) NOT NULL,
                                                   `codDisciplina` VARCHAR(45) NOT NULL,
                                                   `usernameProfessor` VARCHAR(45) NOT NULL,
                                                   `valorHora` DECIMAL(6,2) NULL DEFAULT NULL,
                                                   PRIMARY KEY (`codAula`, `codDisciplina`, `usernameProfessor`),
                                                   INDEX `fk_aula_disciplina1_idx` (`codDisciplina` ASC) VISIBLE,
                                                   INDEX `fk_aula_professor_idx` (`usernameProfessor` ASC) VISIBLE,
                                                   CONSTRAINT `fk_aula_disciplina1`
                                                       FOREIGN KEY (`codDisciplina`)
                                                           REFERENCES `tpfinalpoo`.`disciplina` (`codDisciplina`),
                                                   CONSTRAINT `fk_aula_professor`
                                                       FOREIGN KEY (`usernameProfessor`)
                                                           REFERENCES `tpfinalpoo`.`usuario` (`username`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tpfinalpoo`.`avaliacaousuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tpfinalpoo`.`avaliacaousuario` (
                                                               `usernameAvaliador` VARCHAR(45) NOT NULL,
                                                               `usernameProfessor` VARCHAR(45) NOT NULL,
                                                               `comentario` VARCHAR(45) NULL DEFAULT NULL,
                                                               `valor` DECIMAL(3,2) NULL DEFAULT NULL,
                                                               PRIMARY KEY (`usernameAvaliador`, `usernameProfessor`),
                                                               INDEX `fk_usernameProfessor_idx` (`usernameProfessor` ASC) VISIBLE,
                                                               CONSTRAINT `fk_avaliacao_usernameAvaliado`
                                                                   FOREIGN KEY (`usernameProfessor`)
                                                                       REFERENCES `tpfinalpoo`.`usuario` (`username`),
                                                               CONSTRAINT `fk_avaliacao_usernameAvaliador`
                                                                   FOREIGN KEY (`usernameAvaliador`)
                                                                       REFERENCES `tpfinalpoo`.`usuario` (`username`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tpfinalpoo`.`contratoaula`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tpfinalpoo`.`contratoaula` (
                                                           `usernameAluno` VARCHAR(45) NOT NULL,
                                                           `codAula` VARCHAR(45) NOT NULL,
                                                           `codContrato` VARCHAR(45) NULL DEFAULT NULL,
                                                           `dataFinal` DATE NULL DEFAULT NULL,
                                                           `dataInicio` DATE NULL DEFAULT NULL,
                                                           `statusContrato` INT NULL DEFAULT NULL,
                                                           PRIMARY KEY (`usernameAluno`, `codAula`),
                                                           UNIQUE INDEX `cod_contrato_UNIQUE` (`codContrato` ASC) VISIBLE,
                                                           INDEX `fk_aluno_has_aula_aula1_idx` (`codAula` ASC) VISIBLE,
                                                           CONSTRAINT `fk_aluno_has_aula_aula1`
                                                               FOREIGN KEY (`codAula`)
                                                                   REFERENCES `tpfinalpoo`.`aula` (`codAula`),
                                                           CONSTRAINT `fk_contratoAula_usernameAluno`
                                                               FOREIGN KEY (`usernameAluno`)
                                                                   REFERENCES `tpfinalpoo`.`usuario` (`username`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tpfinalpoo`.`topico`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tpfinalpoo`.`topico` (
                                                     `idTopico` INT NOT NULL AUTO_INCREMENT,
                                                     `descricao` VARCHAR(45) NOT NULL,
                                                     PRIMARY KEY (`idTopico`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 6;


-- -----------------------------------------------------
-- Table `tpfinalpoo`.`topicoaula`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tpfinalpoo`.`topicoaula` (
                                                         `idTopico` INT NOT NULL,
                                                         `codAula` VARCHAR(45) NOT NULL,
                                                         PRIMARY KEY (`idTopico`, `codAula`),
                                                         INDEX `fk_topico_has_aula_aula1_idx` (`codAula` ASC) VISIBLE,
                                                         INDEX `fk_topico_has_aula_topico1_idx` (`idTopico` ASC) VISIBLE,
                                                         CONSTRAINT `fk_topico_has_aula_aula1`
                                                             FOREIGN KEY (`codAula`)
                                                                 REFERENCES `tpfinalpoo`.`aula` (`codAula`),
                                                         CONSTRAINT `fk_topico_has_aula_topico1`
                                                             FOREIGN KEY (`idTopico`)
                                                                 REFERENCES `tpfinalpoo`.`topico` (`idTopico`))
    ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

use tpfinalpoo;

insert into disciplina(`codDisciplina`,`nome`,`descricao`) values ("MAF","Cálculo 1","Descrição Cálculo 1");
insert into disciplina(`codDisciplina`,`nome`,`descricao`) values ("CFC","POO","Descrição POO");
insert into disciplina(`codDisciplina`,`nome`,`descricao`) values ("ADF","Economia","Descrição Economia");

insert into usuario(`nome`,`descricao`,`dataNascimento`,`formacao`,`username`,`tipoUsuario`)
 values ("Dener Vieira","Descrição Dener","2000-10-07","Ciência da Computação","dener",1);
 insert into usuario(`nome`,`descricao`,`dataNascimento`,`formacao`,`username`,`tipoUsuario`)
 values ("Germano Barcelos","Descrição Germano","2001-03-26","Ciência da Computação","germano",0);
 insert into usuario(`nome`,`descricao`,`dataNascimento`,`formacao`,`username`,`tipoUsuario`)
 values ("Fabricio ","Descrição Fabricio","1978-05-03","Ciência da Computação","fabricio",1);
 insert into usuario(`nome`,`descricao`,`dataNascimento`,`formacao`,`username`,`tipoUsuario`)
 values ("Fábio Trindade","Descrição Fábio","2001-09-05","Ciência da Computação","fabio",0);

insert into topico(`idTopico`,`descricao`) values (1,"Integral");
insert into topico(`idTopico`,`descricao`) values (2,"Interfaces");
insert into topico(`idTopico`,`descricao`) values (3,"Exceptions");

insert into avaliacaousuario(`usernameAvaliador`,`usernameProfessor`,`comentario`,`valor`)
 values ("germano","dener","Excelente!",5.0);

insert into aula(`codAula`,`descricao`,`codDisciplina`,`usernameProfessor`,`valorHora`)
 values ("codAula1","Descriçaõ aula Calculo","MAF","dener",15.0);
 insert into aula(`codAula`,`descricao`,`codDisciplina`,`usernameProfessor`,`valorHora`)
 values ("codAula2","Descrição aula POO","CFC","fabricio",25.0);
 
 insert into topicoaula(`idTopico`,`codAula`) values (1,"codAula1");
 insert into topicoaula(`idTopico`,`codAula`) values (2,"codAula2");
 insert into topicoaula(`idTopico`,`codAula`) values (3,"codAula2");
  
 insert into contratoaula(`usernameAluno`,`codAula`,`codContrato`,`dataFinal`,`dataInicio`,`statusContrato`)
 values ("germano","codAula1","codContrato1","2021-05-27","2021-05-20",1);
 insert into contratoaula(`usernameAluno`,`codAula`,`codContrato`,`dataFinal`,`dataInicio`,`statusContrato`)
 values ("fabio","codAula2","codContrato2","2021-06-20","2021-06-10",0);
