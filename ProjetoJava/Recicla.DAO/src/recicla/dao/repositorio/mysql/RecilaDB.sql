-- ####################
-- Criação do banco
-- ####################
DROP DATABASE `ReciclaDB`;
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
  `Dinheiro` DECIMAL DEFAULT 0,
  PRIMARY KEY (`UsuarioId`)
);

CREATE TABLE `Jogos`
(
  `JogoId` TINYINT UNSIGNED AUTO_INCREMENT,
  `Descricao` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`JogoId`)
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
  `StatusRodada` TINYINT DEFAULT 0,
  PRIMARY KEY (`RodadaId`),
  FOREIGN KEY (`SalaId`) REFERENCES `Salas` (`SalaId`)
);

CREATE TABLE `RodadasXUsuarios`
(
	`UsuarioId` INT UNSIGNED,
  `RodadaId` INT UNSIGNED,
	`Pontos` INT UNSIGNED,
  PRIMARY KEY (`UsuarioId`),
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
  IF NEW.`TipoUsuario` = 'A' THEN
		-- Somente alunos terão itens da loja
		INSERT INTO `ItensLojaXUsuarios`
			(`ItemLojaId`, `UsuarioId`, `Quantidade`)
		VALUES
			(1, NEW.`UsuarioId`, 0),
			(2, NEW.`UsuarioId`, 0),
			(3, NEW.`UsuarioId`, 0);
      
		-- Somente alunos serão associados a uma rodada
    INSERT INTO `RodadasXUsuarios`
			(`UsuarioId`, `RodadaId`, `Pontos`)
		VALUES
			(NEW.`UsuarioId`, NULL, 0);
	END IF;
END$

CREATE TRIGGER `tgrAfterUpdadeItensLojaXUsuarios`
AFTER UPDATE ON `ItensLojaXUsuarios`
FOR EACH ROW
BEGIN
	-- Novo valor maior que antigo representa compra de item
	IF NEW.`Quantidade` > OLD.`Quantidade` THEN
		SET @preco = (SELECT `Preco` FROM `ItensLoja` WHERE `ItemLojaId` = NEW.`ItemLojaId`);
    SET @difQuantidade = NEW.`Quantidade` - OLD.`Quantidade`;
    
    -- Desconta valor gasto com o item (preço * quantidade comprada)
		UPDATE `Usuarios`
    SET `Dinheiro` = (`Dinheiro` - (@preco * @difQuantidade))
    WHERE `UsuarioId` = NEW.`UsuarioId`;
  END IF;
END$

CREATE TRIGGER `tgrBeforeDeleteSala`
BEFORE DELETE ON `Salas`
FOR EACH ROW
BEGIN
	-- Retira relação de rodada e pontos do usuário
	UPDATE `RodadasXUsuarios`
  SET `RodadaId` = NULL,
	 		`Pontos` = 0
  WHERE `RodadaId` IN (SELECT `RodadaId` FROM `Rodadas` WHERE `SalaId` = OLD.`SalaId`);
	
  -- Apaga os jogos das rodadas
  DELETE FROM `JogosRodada` 
  WHERE `RodadaId` IN (SELECT `RodadaId` 
											 FROM `Rodadas` 
                       WHERE `SalaId` = OLD.`SalaId`);
  
  -- Apaga as rodadas
  DELETE FROM `Rodadas` WHERE `SalaId` = OLD.`SalaId`;
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
	('Apague a luz'),
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
