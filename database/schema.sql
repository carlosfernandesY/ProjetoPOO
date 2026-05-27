-- 1. Criação do Banco de Dados
CREATE DATABASE IF NOT EXISTS barbearia;
USE barbearia;

-- 2. Criação da Tabela Cliente
CREATE TABLE IF NOT EXISTS Cliente (
    idCliente INT AUTO_INCREMENT,
    nome VARCHAR(150) NOT NULL,
    email VARCHAR(100),
    telefone VARCHAR(20),
    CONSTRAINT PK_Cliente PRIMARY KEY (idCliente)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 3. Criação da Tabela Barbeiro
CREATE TABLE IF NOT EXISTS Barbeiro (
    idBarbeiro INT AUTO_INCREMENT,
    nome VARCHAR(150) NOT NULL,
    CONSTRAINT PK_Barbeiro PRIMARY KEY (idBarbeiro)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 4. Criação da Tabela Atendimento
CREATE TABLE IF NOT EXISTS Atendimento (
    idAtendimento INT AUTO_INCREMENT,
    idClienteFK INT NOT NULL,
    idBarbeiroFK INT NOT NULL,
    data DATE NOT NULL,
    valorTotal DECIMAL(10,2) NOT NULL,
    CONSTRAINT PK_Atendimento PRIMARY KEY (idAtendimento),
    CONSTRAINT FK_Atendimento_Cliente FOREIGN KEY (idClienteFK)
    REFERENCES Cliente(idCliente) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FK_Atendimento_Barbeiro FOREIGN KEY (idBarbeiroFK)
    REFERENCES Barbeiro(idBarbeiro) ON DELETE CASCADE ON UPDATE CASCADE
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;