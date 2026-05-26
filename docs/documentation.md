# Documentação Técnica — Sistema de Barbearia

## Visão Geral

O projeto consiste em um sistema de gerenciamento de barbearia desenvolvido em Java utilizando Programação Orientada a Objetos (POO), padrão DAO (Data Access Object), JDBC e banco de dados MySQL.

O sistema possui foco em operações básicas de persistência e manipulação de entidades relacionadas ao domínio da barbearia.

---

# Estrutura Atual do Projeto

```text
Projeto-POO/
│
├── src/
│   └── main/
│       ├── java/
│       │
│       └── br/edu/ifgoiano/barbearia/
│           │
│           ├── app/          # Inicialização e execução principal do sistema
│           │
│           ├── connection/   # Gerenciamento de conexão com o banco de dados
│           │
│           ├── model/        # Entidades e objetos do domínio do sistema
│           │
│           ├── dao/          # Operações de acesso e manipulação dos dados 
│           │
│           └── service/      # Regras de negócio e intermediação entre DAO e aplicação
│
├── pom.xml
│
└── README.md
```

---

# Tecnologias Utilizadas

| Tecnologia | Finalidade |
|---|---|
| Java | Desenvolvimento da aplicação |
| JavaFX | Interface gráfica |
| MySQL | Persistência de dados |
| JDBC | Comunicação com banco de dados |
| Maven | Gerenciamento de dependências |

---

# Funcionalidades Implementadas

- Cadastro de clientes
- Consulta de clientes por ID
- Atualização de clientes
- Remoção de clientes

- Cadastro de barbeiros
- Consulta de barbeiros por ID
- Atualização de barbeiros
- Remoção de barbeiros

- Cadastro de atendimentos
- Consulta de atendimentos por ID
- Atualização de atendimentos
- Exclusão de atendimentos

---

# Entidades do Sistema

## Cliente

Representa os clientes cadastrados na barbearia.

### Atributos 

| Campo | Tipo |
|---|---|
| idCliente | Integer |
| nome | String |
| telefone | String |
| email | String |




## Barbeiro

Representa os barbeiros cadastrados.

### Atributos 

| Campo | Tipo |
|---|---|
| idBarbeiro | Integer |
| nome | String |


## Atendimento

Representa um atendimento realizado na barbearia.

### Atributos 

| Campo | Tipo |
|---|---|
| idAtendimento | Integer |
| cliente | Cliente |
| barbeiro | Barbeiro |
| data | Date |
| valorTotal | Double |


# Camada DAO

O projeto utiliza o padrão DAO para encapsular operações de persistência no banco de dados.

---

## Interface Genérica `EntidadeDAO<T>`

Define operações CRUD básicas reutilizáveis.

### Métodos disponíveis

```java
create(T)
readById(Integer)
updateById(T)
deleteById(T)
```

### Responsabilidades

- Inserção de registros
- Consulta por ID
- Atualização de registros
- Remoção de registros

---

## ClienteDAO

Responsável pela persistência da entidade Cliente.

### Métodos

```java
create(Cliente)
readById(Integer)
updateById(Cliente)
deleteById(Cliente)
```

---

## BarbeiroDAO

Responsável pela persistência da entidade Barbeiro.

### Métodos

```java
create(Barbeiro)
readById(Integer)
updateById(Barbeiro)
deleteById(Barbeiro)
```

---

## AtendimentoDAO

Responsável pela persistência da entidade Atendimento.

### Métodos

```java
create(Atendimento)
readById(Integer)
updateById(Atendimento)
deleteById(Atendimento)
```

---

# Camada de Serviço

## ClienteService

Existe atualmente uma camada de serviço para clientes.

### Métodos identificados

```java
salvar(Cliente)
read(int)
```

### Responsabilidades 

- Intermediar comunicação entre aplicação e DAO
- Centralizar operações relacionadas ao cliente

---

# Conexão com Banco de Dados

## ConnectionFactory

Classe responsável pela criação de conexões JDBC.

### Método disponível

```java
getConnection()
```

### Responsabilidades

- Abrir conexões com o banco MySQL
- Centralizar configuração de conexão

---

# Classe Principal

## Main

Classe responsável pela inicialização do sistema.



# Relacionamentos do Sistema

## Atendimento → Cliente

Cada atendimento possui um cliente associado.


## Atendimento → Barbeiro

Cada atendimento possui um barbeiro associado.


## DAOs → EntidadeDAO<T>

Os DAOs implementam a interface genérica de persistência.


