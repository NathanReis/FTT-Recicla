-- ####################
-- Criação do banco
-- ####################
DROP DATABASE IF EXISTS ReciclaDB;

CREATE DATABASE ReciclaDB;

USE ReciclaDB;
-- ####################
-- Criação do usuário
-- ####################
-- DROP USER IF EXISTS 'recicla'@'localhost';

-- CREATE USER 'recicla'@'localhost' IDENTIFIED BY 'jhdf8923b';
-- GRANT ALL PRIVILEGES ON ReciclaDB . * TO 'recicla'@'localhost';
-- FLUSH PRIVILEGES;
-- ####################
-- Criação das tabelas
-- ####################
CREATE TABLE Usuarios
(
  UsuarioId INT UNSIGNED AUTO_INCREMENT,
  Nome VARCHAR(50) NOT NULL,
  Usuario VARCHAR(15) NOT NULL,
  Senha VARBINARY(255) NOT NULL,
  TipoUsuario CHAR(1) DEFAULT 'A',
  PRIMARY KEY (UsuarioId)
);

CREATE TABLE Configuracoes
(
  ConfiguracaoId INT UNSIGNED,
  Som CHAR(1) DEFAULT 'L',
  Musica CHAR(1) DEFAULT 'L',
  PRIMARY KEY (ConfiguracaoId),
  FOREIGN KEY (ConfiguracaoId) REFERENCES Usuarios (UsuarioId)
);

CREATE TABLE Jogos
(
  JogoId TINYINT UNSIGNED AUTO_INCREMENT,
  Descricao VARCHAR(50) NOT NULL,
  PRIMARY KEY (JogoId)
);

CREATE TABLE Recordes
(
  RecordeId INT UNSIGNED AUTO_INCREMENT,
  JogoId TINYINT UNSIGNED NOT NULL,
  UsuarioId INT UNSIGNED NOT NULL,
  QtdPartidas SMALLINT UNSIGNED DEFAULT 0,
  QtdVitorias SMALLINT UNSIGNED DEFAULT 0,
  MelhorTempo TIME NOT NULL,
  Pontos SMALLINT UNSIGNED NOT NULL,
  PRIMARY KEY (RecordeId),
  FOREIGN KEY (JogoId) REFERENCES Jogos (JogoId),
  FOREIGN KEY (UsuarioId) REFERENCES Usuarios (UsuarioId)
);

CREATE TABLE ItensLoja
(
  ItemLojaId TINYINT UNSIGNED AUTO_INCREMENT,
  Nome VARCHAR(25) NOT NULL,
  Descricao VARCHAR(255) NOT NULL,
  Preco DECIMAL NOT NULL,
  PRIMARY KEY (ItemLojaId)
);

CREATE TABLE ItensLojaXUsuarios
(
  ItemLojaId TINYINT UNSIGNED,
  UsuarioId INT UNSIGNED,
  Quantidade TINYINT UNSIGNED NOT NULL,
  PRIMARY KEY (ItemLojaId, UsuarioId),
  FOREIGN KEY (ItemLojaId) REFERENCES ItensLoja (ItemLojaId),
  FOREIGN KEY (UsuarioId) REFERENCES Usuarios (UsuarioId)
);

CREATE TABLE PerguntasQuiz
(
  PerguntaQuizId SMALLINT UNSIGNED AUTO_INCREMENT,
  Pergunta VARCHAR(255) NOT NULL,
  RespostaCorreta VARCHAR(255) NOT NULL,
  Alternativa1 VARCHAR(255) NOT NULL,
  Alternativa2 VARCHAR(255) NOT NULL,
  JogoId TINYINT UNSIGNED DEFAULT 1,
  PRIMARY KEY (PerguntaQuizId),
  FOREIGN KEY (JogoId) REFERENCES Jogos (JogoId)
);

CREATE TABLE Salas
(
  SalaId INT UNSIGNED AUTO_INCREMENT,
  Descricao VARCHAR(50) NOT NULL,
  ChaveAcesso CHAR(5) NOT NULL,
  HorarioInicio TIME NOT NULL,
  PRIMARY KEY (SalaId)
);

CREATE TABLE SalasXUsuarios
(
  SalaId INT UNSIGNED,
  UsuarioId INT UNSIGNED,
  PRIMARY KEY (SalaId, UsuarioId),
  FOREIGN KEY (SalaId) REFERENCES Salas (SalaId),
  FOREIGN KEY (UsuarioId) REFERENCES Usuarios (UsuarioId)
);

CREATE TABLE Rodadas
(
  RodadaId INT UNSIGNED AUTO_INCREMENT,
  SalaId INT UNSIGNED NOT NULL,
  JogoId TINYINT UNSIGNED NOT NULL,
  PRIMARY KEY (RodadaId),
  FOREIGN KEY (SalaId) REFERENCES Salas (SalaId),
  FOREIGN KEY (JogoId) REFERENCES Jogos (JogoId)
);

CREATE TABLE ItensRodada
(
  ItemRodadaId INT UNSIGNED AUTO_INCREMENT,
  RodadaId INT UNSIGNED NOT NULL,
  ItemJogoId SMALLINT UNSIGNED,
  PRIMARY KEY (ItemRodadaId),
  FOREIGN KEY (RodadaId) REFERENCES Rodadas (RodadaId)
);
-- ####################
-- Criação das views
-- ####################

DELIMITER $
-- ####################
-- Criação das triggers
-- ####################
CREATE TRIGGER tgrAfterInsertUsuarios
AFTER INSERT ON Usuarios
FOR EACH ROW
BEGIN
	INSERT INTO Configuracoes (ConfiguracaoId) VALUES (NEW.UsuarioId);
END$
-- ####################
-- Criação dos eventos
-- ####################

DELIMITER ;
-- ####################
-- Criação de dados base
-- ####################
INSERT INTO Usuarios (Nome, Usuario, Senha, TipoUsuario)
VALUES ('Professor', 'professor', AES_ENCRYPT('manager', 'ld83mf0'), 'P');