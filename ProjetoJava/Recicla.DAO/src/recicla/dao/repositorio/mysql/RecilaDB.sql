-- ####################
-- Criação do banco
-- ####################
CREATE DATABASE `ReciclaDB`;

USE `ReciclaDB`;
-- ####################
-- Criação do usuário
-- ####################
-- DROP USER IF EXISTS 'recicla'@'localhost';

-- CREATE USER 'recicla'@'localhost' IDENTIFIED BY 'jhdf8923b';
-- GRANT ALL PRIVILEGES ON `ReciclaDB` . * TO 'recicla'@'localhost';
-- FLUSH PRIVILEGES;
-- ####################
-- Criação das tabelas
-- ####################
CREATE TABLE `Salas`
(
  `SalaId` INT UNSIGNED AUTO_INCREMENT,
  `Descricao` VARCHAR(50) NOT NULL,
  `ChaveAcesso` CHAR(5) NOT NULL,
  PRIMARY KEY (`SalaId`)
);

CREATE TABLE `Usuarios`
(
  `UsuarioId` INT UNSIGNED AUTO_INCREMENT,
  `Nome` VARCHAR(50) NOT NULL,
  `Usuario` VARCHAR(15) NOT NULL,
  `Senha` VARBINARY(255) NOT NULL,
  `TipoUsuario` CHAR(1) DEFAULT 'A',
  `SalaId` INT UNSIGNED,
  `Dinheiro` DECIMAL DEFAULT 0,
  PRIMARY KEY (`UsuarioId`),
  FOREIGN KEY (`SalaId`) REFERENCES `Salas` (`SalaId`)
);

CREATE TABLE `Configuracoes`
(
  `ConfiguracaoId` INT UNSIGNED,
  `Som` CHAR(1) DEFAULT 'L',
  `Musica` CHAR(1) DEFAULT 'L',
  PRIMARY KEY (`ConfiguracaoId`),
  FOREIGN KEY (`ConfiguracaoId`) REFERENCES `Usuarios` (`UsuarioId`)
);

CREATE TABLE `Jogos`
(
  `JogoId` TINYINT UNSIGNED AUTO_INCREMENT,
  `Descricao` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`JogoId`)
);

CREATE TABLE `Recordes`
(
  `RecordeId` INT UNSIGNED AUTO_INCREMENT,
  `JogoId` TINYINT UNSIGNED NOT NULL,
  `UsuarioId` INT UNSIGNED NOT NULL,
  `QtdPartidas` SMALLINT UNSIGNED DEFAULT 0,
  `QtdVitorias` SMALLINT UNSIGNED DEFAULT 0,
  `MelhorTempo` TIME NOT NULL,
  `Pontos` SMALLINT UNSIGNED NOT NULL,
  PRIMARY KEY (`RecordeId`),
  FOREIGN KEY (`JogoId`) REFERENCES `Jogos` (`JogoId`),
  FOREIGN KEY (`UsuarioId`) REFERENCES `Usuarios` (`UsuarioId`)
);

CREATE TABLE `ItensLoja`
(
  `ItemLojaId` TINYINT UNSIGNED AUTO_INCREMENT,
  `Nome` VARCHAR(25) NOT NULL,
  `Descricao` VARCHAR(255) NOT NULL,
  `Preco` DECIMAL(6, 2) NOT NULL,
  PRIMARY KEY (`ItemLojaId`)
);

CREATE TABLE `ItensLojaXUsuarios`
(
  `ItemLojaId` TINYINT UNSIGNED,
  `UsuarioId` INT UNSIGNED,
  `Quantidade` TINYINT UNSIGNED NOT NULL,
  PRIMARY KEY (`ItemLojaId`, `UsuarioId`),
  FOREIGN KEY (`ItemLojaId`) REFERENCES `ItensLoja` (`ItemLojaId`),
  FOREIGN KEY (`UsuarioId`) REFERENCES `Usuarios` (`UsuarioId`)
);

CREATE TABLE `PerguntasQuiz`
(
  `PerguntaQuizId` SMALLINT UNSIGNED AUTO_INCREMENT,
  `Pergunta` VARCHAR(255) NOT NULL,
  `RespostaCorreta` VARCHAR(255) NOT NULL,
  `Alternativa1` VARCHAR(255) NOT NULL,
  `Alternativa2` VARCHAR(255) NOT NULL,
  `JogoId` TINYINT UNSIGNED DEFAULT 1,
  PRIMARY KEY (`PerguntaQuizId`),
  FOREIGN KEY (`JogoId`) REFERENCES `Jogos` (`JogoId`)
);

CREATE TABLE `Rodadas`
(
  `RodadaId` INT UNSIGNED AUTO_INCREMENT,
  `SalaId` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`RodadaId`),
  FOREIGN KEY (`SalaId`) REFERENCES `Salas` (`SalaId`)
);

CREATE TABLE `RodadasXUsuarios`
(
	`RodadaId` INT UNSIGNED,
	`UsuarioId` INT UNSIGNED,
  PRIMARY KEY (`RodadaId`, `UsuarioId`),
  FOREIGN KEY (`RodadaId`) REFERENCES `Rodadas` (`RodadaId`),
  FOREIGN KEY (`UsuarioId`) REFERENCES `Usuarios` (`UsuarioId`)
);

CREATE TABLE `JogosRodada`
(
  `JogoRodadaId` INT UNSIGNED AUTO_INCREMENT,
  `RodadaId` INT UNSIGNED NOT NULL,
  `JogoId` TINYINT UNSIGNED NOT NULL,
  PRIMARY KEY (`JogoRodadaId`),
  FOREIGN KEY (`RodadaId`) REFERENCES `Rodadas` (`RodadaId`),
  FOREIGN KEY (`JogoId`) REFERENCES `Jogos` (`JogoId`)
);

CREATE TABLE `JogosRodadaXPerguntasQuiz`
(
  `JogoRodadaId` INT UNSIGNED,
  `PerguntaQuizId` SMALLINT UNSIGNED,
  PRIMARY KEY (`JogoRodadaId`, `PerguntaQuizId`),
  FOREIGN KEY (`JogoRodadaId`) REFERENCES `JogosRodada` (`JogoRodadaId`),
  FOREIGN KEY (`PerguntaQuizId`) REFERENCES `PerguntasQuiz` (`PerguntaQuizId`)
);
-- ####################
-- Criação das views
-- ####################

DELIMITER $
-- ####################
-- Criação das triggers
-- ####################
CREATE TRIGGER `tgrAfterInsertUsuarios`
AFTER INSERT ON `Usuarios`
FOR EACH ROW
BEGIN
	-- Qualquer usuário terá configuração
	INSERT INTO `Configuracoes` 
		(`ConfiguracaoId`, `Som`, `Musica`) 
	VALUES 
		(NEW.`UsuarioId`, 'L', 'L');
	
  IF NEW.`TipoUsuario` = 'A' THEN
		-- Somente alunos terão itens da loja
		INSERT INTO `ItensLojaXUsuarios`
			(`ItemLojaId`, `UsuarioId`, `Quantidade`)
		VALUES
			(1, NEW.`UsuarioId`, 0),
			(2, NEW.`UsuarioId`, 0),
			(3, NEW.`UsuarioId`, 0);
		
    -- Somente alunos terão recordes
		INSERT INTO `Recordes`
			(`JogoId`, `UsuarioId`, `QtdPartidas`, `QtdVitorias`, `MelhorTempo`, `Pontos`)
		VALUES
			(1, NEW.`UsuarioId`, 0, 0, '23:59:59', 0),
      (2, NEW.`UsuarioId`, 0, 0, '23:59:59', 0),
      (3, NEW.`UsuarioId`, 0, 0, '23:59:59', 0);
	END IF;
END$
-- ####################
-- Criação dos eventos
-- ####################

DELIMITER ;
-- ####################
-- Criação de dados base
-- ####################
INSERT INTO `Jogos` 
	(`Descricao`)
VALUES
	('Acerto o alvo'),
	('Jogo da memória'),
  ('Quiz');

INSERT INTO `ItensLoja` 
	(`Nome`, `Descricao`, `Preco`)
VALUES 
	('Tempo', 'Ganhe mais 30s', 50),
  ('Resposta', 'Descubra a resposta', 200),
  ('Pontos', 'Dobra oo pontos de uma partida', 300);

INSERT INTO `Usuarios` 
	(`Nome`, `Usuario`, `Senha`, `TipoUsuario`)
VALUES 
	('Professor', 'professor', AES_ENCRYPT('manager', 'ld83mf0'), 'P');
