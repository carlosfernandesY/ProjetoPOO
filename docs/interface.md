# Estrutura da Interface do Sistema

A interface gráfica do sistema foi desenvolvida utilizando JavaFX, seguindo uma arquitetura modular baseada em telas independentes para cada entidade do sistema.

O layout atual possui:

- Navegação lateral
- Área dinâmica de conteúdo
- Tabelas interativas
- Formulários laterais
- Componentes reutilizáveis
- Ações de edição e exclusão diretamente nas tabelas

---

# Estrutura Atual da Camada View

```text
src/
└── main/
    └── java/
        └── org/
            └── ifgoiano/
                └── barbearia/
                    │
                    ├── app/
                    │
                    ├── connection/
                    │
                    ├── dao/
                    │
                    ├── model/
                    │
                    ├── service/
                    │
                    └── view/
                        │
                        ├── main/
                        │   └── MainView.java
                        │
                        ├── cliente/
                        │   └── ClienteView.java
                        │
                        ├── barbeiro/
                        │   └── BarbeiroView.java
                        │
                        ├── atendimento/
                        │   └── AtendimentoView.java
                        │
                        └── components/
                            ├── AlertComponent.java
                            ├── ClienteController.java
                            ├── BarbeiroController.java
                            └── AtendimentoController.java
```

---

# Estrutura Visual do Sistema

A aplicação atualmente utiliza uma estrutura dividida em:

```text
+-------------------------------------------------------------+
| SIDEBAR                     | ÁREA PRINCIPAL                |
|-----------------------------|-------------------------------|
|                             |                               |
|  SISTEMA BARBEARIA          |  Conteúdo dinâmico da tela    |
|                             |                               |
|  [ Clientes ]               |                               |
|  [ Barbeiros ]              |                               |
|  [ Atendimentos ]           |                               |
|                             |                               |
+-------------------------------------------------------------+
```

---

# Tela Principal

Responsável pela navegação entre os módulos do sistema.

## Componentes

- Sidebar lateral
- Botões de navegação
- Área de conteúdo central
- Troca dinâmica de telas

---

## Estrutura textual

```text
+-------------------------------------------------------------+
| SISTEMA BARBEARIA                                           |
+-------------------------------------------------------------+
|                                                             |
| [ Clientes ]                                                |
| [ Barbeiros ]                                               |
| [ Atendimentos ]                                            |
|                                                             |
+-------------------------------------------------------------+
```

---

# Tela de Clientes

Responsável pelo gerenciamento completo de clientes.

---

## Funcionalidades atuais

- Cadastro de clientes
- Atualização de clientes
- Exclusão de clientes
- Listagem em tabela
- Atualização dinâmica da interface

---

## Estrutura visual

```text
+--------------------------------------------------------------------------------+
| CLIENTES                                                                       |
| Cadastro e gerenciamento de clientes                                           |
+--------------------------------------------------------------------------------+
|                                                                                |
|  TABELA DE CLIENTES                 | DADOS DO CLIENTE                         |
|-------------------------------------|------------------------------------------|
| ID | Nome | Telefone | Email        | Nome:      [____________________]        |
|-------------------------------------|                                          |
| 1  | João | 99999-9999 | ...        | Telefone: [____________________]         |
| 2  | Ana  | 98888-8888 | ...        |                                          |
|                                     | Email:     [____________________]        |
| [✏] [🗑]                            |                                          |
|                                     | [ Salvar Cliente ]                       |
|                                     |                                          |
+--------------------------------------------------------------------------------+
```

---

# Tela de Barbeiros

Responsável pelo gerenciamento de profissionais da barbearia.

---

## Funcionalidades atuais

- Cadastro de barbeiros
- Atualização de barbeiros
- Exclusão de barbeiros
- Listagem em tabela

---

## Estrutura visual

```text
+--------------------------------------------------------------------------------+
| BARBEIROS                                                                      |
| Cadastro e gerenciamento de profissionais                                      |
+--------------------------------------------------------------------------------+
|                                                                                |
|  TABELA DE BARBEIROS              | DADOS DO BARBEIRO                          |
|-----------------------------------|--------------------------------------------|
| ID | Nome                         | Nome: [__________________________]         |
|-----------------------------------|                                            |
| 1  | Carlos                       |                                            |
| 2  | Pedro                        | [ Salvar Profissional ]                    |
|                                   |                                            |
| [✏] [🗑]                          |                                            |
|                                   |                                            |
+--------------------------------------------------------------------------------+
```

---

# Tela de Atendimentos

Responsável pelo gerenciamento de atendimentos realizados.

---

## Funcionalidades atuais

- Registro de atendimento
- Atualização de atendimento
- Exclusão de atendimento
- Seleção de cliente
- Seleção de barbeiro
- Seleção de data
- Controle de valor total
- Listagem completa em tabela

---

## Estrutura visual

```text
+------------------------------------------------------------------------------------------------+
| HISTÓRICO DE ATENDIMENTOS                                                                      |
| Registro de agendamentos — alterar e excluir                                                   |
+------------------------------------------------------------------------------------------------+
|                                                                                                |
| TABELA DE ATENDIMENTOS          | DADOS DO ATENDIMENTO                                         |
|---------------------------------|--------------------------------------------------------------|
| ID | Cliente | Barbeiro | Valor | Cliente:   [ ComboBox Cliente ]                              |
|---------------------------------|                                                              |
| 1  | João    | Carlos   | 45.00 | Barbeiro:  [ ComboBox Barbeiro ]                             |
|                                 |                                                              |
| [✏] [🗑]                        | Valor:     [____________________]                            |
|                                 |                                                              |
|                                 | Data:      [ DatePicker ]                                    |
|                                 |                                                              |
|                                 | [ Salvar Atendimento ]                                       |
|                                 |                                                              |
+------------------------------------------------------------------------------------------------+
```

---

# Componentes Reutilizáveis

A pasta `components/` contém elementos reutilizados em múltiplas telas.

---

## Componentes atuais

### AlertComponent

Responsável por:

- Alertas de sucesso
- Alertas de erro
- Alertas de aviso
- Confirmações do sistema

# Recursos visuais implementados

O sistema atualmente utiliza:

- CSS customizado
- Ícones vetoriais via JavaFX CSS
- Botões de ação estilizados
- Layout responsivo com VBox/HBox
- Formulários laterais
- TableView estilizada
- Sidebar de navegação
- Separadores visuais
- Componentes reutilizáveis

---

# Fluxo de Funcionamento

```text
Usuário
   ↓
Interface JavaFX
   ↓
Controllers/View
   ↓
DAO
   ↓
Banco de Dados MySQL
```

