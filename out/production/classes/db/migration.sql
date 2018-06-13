CREATE DATABASE IF NOT EXISTS estoque;

USE estoque;

CREATE TABLE IF NOT EXISTS perfis(
        id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
        descricao VARCHAR(200),
        linha VARCHAR(20),
    );

CREATE TABLE IF NOT EXISTS acessorios(
        id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
        descricao VARCHAR(200),
        linha VARCHAR(20),
        funcao TEXT
    );
    


