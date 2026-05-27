## Criação do Banco de Dados

```sql
CREATE DATABASE IF NOT EXISTS barbearia;
USE barbearia;

-- Cria o banco de dados apenas se ele não existir
-- Define o banco como ativo para execução das próximas operações


-- 2. Tabela Cliente
CREATE TABLE IF NOT EXISTS Cliente (
idCliente INT AUTO_INCREMENT,
nome VARCHAR(150) NOT NULL,
email VARCHAR(100),
telefone VARCHAR(20),
CONSTRAINT PK_Cliente PRIMARY KEY (idCliente)
);

-- Armazena os clientes do sistema
-- idCliente: identificador único (chave primária)
-- nome: obrigatório
-- email e telefone: opcionais
-- AUTO_INCREMENT: gera IDs automaticamente


-- 3. Tabela Barbeiro
CREATE TABLE IF NOT EXISTS Barbeiro (
idBarbeiro INT AUTO_INCREMENT,
nome VARCHAR(150) NOT NULL,
CONSTRAINT PK_Barbeiro PRIMARY KEY (idBarbeiro)
);

-- Armazena os barbeiros do sistema
-- idBarbeiro: identificador único (chave primária)
-- nome: obrigatório
-- AUTO_INCREMENT: geração automática de IDs


-- 4. Tabela Atendimento
CREATE TABLE IF NOT EXISTS Atendimento (
idAtendimento INT AUTO_INCREMENT,
idClienteFK INT NOT NULL,
idBarbeiroFK INT NOT NULL,
data DATE NOT NULL,
valorTotal DECIMAL(10,2) NOT NULL,
CONSTRAINT PK_Atendimento PRIMARY KEY (idAtendimento),

    CONSTRAINT FK_Atendimento_Cliente FOREIGN KEY (idClienteFK)
        REFERENCES Cliente(idCliente)
        ON DELETE CASCADE
        ON UPDATE CASCADE,

    CONSTRAINT FK_Atendimento_Barbeiro FOREIGN KEY (idBarbeiroFK)
        REFERENCES Barbeiro(idBarbeiro)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

-- Registra os atendimentos realizados
-- idAtendimento: identificador único do atendimento
-- idClienteFK: referência ao cliente atendido
-- idBarbeiroFK: referência ao barbeiro responsável
-- data: data do atendimento
-- valorTotal: valor cobrado pelo serviço

-- Regras de integridade:
-- FOREIGN KEY: garante relacionamento entre tabelas
-- ON DELETE CASCADE: remove atendimentos se cliente ou barbeiro for excluído
-- ON UPDATE CASCADE: atualiza automaticamente mudanças de IDs

-- Configurações gerais:
-- ENGINE = InnoDB: suporta transações e chaves estrangeiras
-- utf8mb4: suporta acentuação e caracteres especiais