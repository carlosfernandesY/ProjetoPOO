package org.ifgoiano.barbearia.view.cliente;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import org.ifgoiano.barbearia.dao.ClienteDAO;
import org.ifgoiano.barbearia.model.Cliente;
import org.ifgoiano.barbearia.view.components.AlertComponent;
import org.kordamp.ikonli.javafx.FontIcon;

public class ClienteView extends VBox {

    private final ClienteDAO clienteDAO = new ClienteDAO();
    private final TableView<Cliente> tabela = new TableView<>();

    private final TextField nomeField = new TextField();
    private final TextField telefoneField = new TextField();
    private final TextField emailField = new TextField();
    private final Button salvarButton = new Button("Salvar Cliente");

    private Cliente clienteEmEdicao = null;

    public ClienteView() {
        this.setSpacing(20);
        this.setPadding(new Insets(36, 40, 36, 40));
        this.getStyleClass().add("content-area");

        // CABEÇALHO DA SEÇÃO
        VBox headerBox = new VBox(4);
        Label tituloSecao = new Label("Gerenciamento de Clientes");
        tituloSecao.getStyleClass().add("page-title");
        Label subtitulo = new Label("CADASTRO, ALTERAÇÃO E EXCLUSÃO DE CLIENTES");
        subtitulo.getStyleClass().add("page-subtitle");
        headerBox.getChildren().addAll(tituloSecao, subtitulo);

        Region divider = new Region();
        divider.setPrefHeight(1);
        divider.getStyleClass().add("gold-divider");

        HBox layoutPrincipal = new HBox(30);
        HBox.setHgrow(tabela, Priority.ALWAYS);

        // FORMULÁRIO LATERAL DIREITO
        VBox painelFormulario = new VBox(15);
        painelFormulario.getStyleClass().add("form-panel");
        painelFormulario.setPrefWidth(300);
        painelFormulario.setMinWidth(300);

        Label formTitle = new Label("Dados do Cliente");
        formTitle.getStyleClass().add("form-title");

        nomeField.setPromptText("Nome do Cliente");
        telefoneField.setPromptText("(00) 00000-0000");
        emailField.setPromptText("E-mail");

        // APLICAÇÃO DE MÁSCARA COMPLETA NO TELEFONE
        telefoneField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) return;

            String apenasDigitos = newValue.replaceAll("\\D", "");

            if (apenasDigitos.length() > 11) {
                apenasDigitos = apenasDigitos.substring(0, 11);
            }

            StringBuilder formatado = new StringBuilder();
            int tam = apenasDigitos.length();

            if (tam > 0) {
                formatado.append("(").append(apenasDigitos.substring(0, Math.min(tam, 2)));
            }
            if (tam > 2) {
                formatado.append(") ").append(apenasDigitos.substring(2, Math.min(tam, 7)));
            }
            if (tam > 7) {
                formatado.append("-").append(apenasDigitos.substring(7, tam));
            }

            javafx.application.Platform.runLater(() -> {
                telefoneField.setText(formatado.toString());
                telefoneField.positionCaret(formatado.length());
            });
        });

        salvarButton.getStyleClass().add("button-primary");
        salvarButton.setMaxWidth(Double.MAX_VALUE);

        painelFormulario.getChildren().addAll(formTitle, nomeField, telefoneField, emailField, salvarButton);

        // TABELA ESQUERDA
        TableColumn<Cliente, Integer> idColuna = new TableColumn<>("ID");
        idColuna.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
        idColuna.setPrefWidth(60);

        TableColumn<Cliente, String> nomeColuna = new TableColumn<>("Nome");
        nomeColuna.setCellValueFactory(new PropertyValueFactory<>("nome"));

        TableColumn<Cliente, String> telefoneColuna = new TableColumn<>("Telefone");
        telefoneColuna.setCellValueFactory(new PropertyValueFactory<>("telefone"));

        TableColumn<Cliente, String> emailColuna = new TableColumn<>("E-mail");
        emailColuna.setCellValueFactory(new PropertyValueFactory<>("email"));

        // COLUNA EDITAR
        TableColumn<Cliente, Void> colEditar = new TableColumn<>("Editar");
        colEditar.setPrefWidth(70);
        colEditar.setStyle("-fx-alignment: CENTER;");
        colEditar.setCellFactory(param -> new TableCell<>() {

            private final Button btn = new Button();

            {
                btn.getStyleClass().add("btn-acao-tabela");

                FontIcon icone = new FontIcon("fas-edit");
                icone.getStyleClass().add("icon-editar");

                btn.setGraphic(icone);
                btn.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);

                btn.setOnAction(event -> {
                    clienteEmEdicao = getTableView().getItems().get(getIndex());

                    nomeField.setText(clienteEmEdicao.getNome());
                    telefoneField.setText(clienteEmEdicao.getTelefone());
                    emailField.setText(clienteEmEdicao.getEmail());

                    salvarButton.setText("Atualizar Cliente");
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : btn);
            }
        });

        // COLUNA EXCLUIR
        TableColumn<Cliente, Void> colExcluir = new TableColumn<>("Excluir");
        colExcluir.setPrefWidth(70);
        colExcluir.setStyle("-fx-alignment: CENTER;");
        colExcluir.setCellFactory(param -> new TableCell<>() {

            private final Button btn = new Button();

            {
                btn.getStyleClass().add("btn-acao-tabela");

                FontIcon icone = new FontIcon("fas-trash");
                icone.getStyleClass().add("icon-excluir");

                btn.setGraphic(icone);
                btn.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);

                btn.setOnAction(event -> {
                    Cliente cliente = getTableView().getItems().get(getIndex());

                    Alert confirmacao = new Alert(
                            Alert.AlertType.CONFIRMATION,
                            "Deseja realmente excluir o cliente " + cliente.getNome() + "?",
                            ButtonType.YES,
                            ButtonType.NO
                    );
                    confirmacao.setHeaderText(null);
                    confirmacao.setTitle("Confirmar Exclusão");

                    confirmacao.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.YES) {
                            clienteDAO.deleteById(cliente);
                            atualizarTabela();
                            limparFormulario();
                            AlertComponent.sucesso("Cliente excluído com sucesso.");
                        }
                    });
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : btn);
            }
        });

        tabela.getColumns().addAll(idColuna, nomeColuna, telefoneColuna, emailColuna, colEditar, colExcluir);
        tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tabela.setPlaceholder(new Label("Nenhum cliente cadastrado."));

        // LÓGICA UNIFICADA DE SALVAR OU ALTERAR
        salvarButton.setOnAction(e -> {
            String nome = nomeField.getText().trim();
            String telefone = telefoneField.getText().trim();
            String email = emailField.getText().trim();

            if (nome.isEmpty() || telefone.isEmpty() || email.isEmpty()) {
                AlertComponent.aviso("Todos os campos (Nome, Telefone e E-mail) são obrigatórios.");
                return;
            }

            if (clienteEmEdicao == null) {
                Cliente novoCliente = new Cliente();
                novoCliente.setNome(nome);
                novoCliente.setTelefone(telefone);
                novoCliente.setEmail(email);
                clienteDAO.create(novoCliente);
                AlertComponent.sucesso("Cliente cadastrado com sucesso.");
            } else {
                clienteEmEdicao.setNome(nome);
                clienteEmEdicao.setTelefone(telefone);
                clienteEmEdicao.setEmail(email);
                clienteDAO.updateById(clienteEmEdicao);
                AlertComponent.sucesso("Cliente atualizado com sucesso.");
            }

            atualizarTabela();
            limparFormulario();
        });

        atualizarTabela();

        layoutPrincipal.getChildren().addAll(tabela, painelFormulario);
        this.getChildren().addAll(headerBox, divider, layoutPrincipal);
    }

    private void atualizarTabela() {
        tabela.setItems(FXCollections.observableArrayList(clienteDAO.readAll()));
    }

    private void limparFormulario() {
        nomeField.clear();
        telefoneField.clear();
        emailField.clear();
        clienteEmEdicao = null;
        salvarButton.setText("Salvar Cliente");
    }
}