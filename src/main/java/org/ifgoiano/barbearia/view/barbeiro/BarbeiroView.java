package org.ifgoiano.barbearia.view.barbeiro;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import org.ifgoiano.barbearia.dao.BarbeiroDAO;
import org.ifgoiano.barbearia.service.BarbeiroService;
import org.ifgoiano.barbearia.model.Barbeiro;
import org.ifgoiano.barbearia.view.components.AlertComponent;
import org.kordamp.ikonli.javafx.FontIcon;

public class BarbeiroView extends VBox {

    private final BarbeiroService barbeiroService = new BarbeiroService(new BarbeiroDAO());
    private final TableView<Barbeiro> tabela = new TableView<>();

    private final TextField nomeField = new TextField();
    private final Button salvarButton = new Button("Salvar Barbeiro");

    private Barbeiro barbeiroEmEdicao = null;

    public BarbeiroView() {
        this.setSpacing(20);
        this.setPadding(new Insets(36, 40, 36, 40));
        this.getStyleClass().add("content-area");

        VBox headerBox = new VBox(4);
        Label tituloSecao = new Label("Gerenciamento de Barbeiros");
        tituloSecao.getStyleClass().add("page-title");
        Label subtitulo = new Label("EQUIPE DE PROFISSIONAIS — ALTERAR E EXCLUIR");
        subtitulo.getStyleClass().add("page-subtitle");
        headerBox.getChildren().addAll(tituloSecao, subtitulo);

        Region divider = new Region();
        divider.setPrefHeight(1);
        divider.getStyleClass().add("gold-divider");

        HBox layoutPrincipal = new HBox(30);
        HBox.setHgrow(tabela, Priority.ALWAYS);

        VBox painelFormulario = new VBox(15);
        painelFormulario.getStyleClass().add("form-panel");
        painelFormulario.setPrefWidth(300);
        painelFormulario.setMinWidth(300);

        Label formTitle = new Label("Novo Barbeiro");
        formTitle.getStyleClass().add("form-title");

        nomeField.setPromptText("Nome do Barbeiro");

        salvarButton.getStyleClass().add("button-primary");
        salvarButton.setMaxWidth(Double.MAX_VALUE);

        painelFormulario.getChildren().addAll(formTitle, nomeField, salvarButton);

        TableColumn<Barbeiro, Integer> idColuna = new TableColumn<>("ID");
        idColuna.setCellValueFactory(new PropertyValueFactory<>("idBarbeiroFK"));
        idColuna.setPrefWidth(80);
        idColuna.setStyle("-fx-alignment: CENTER;");

        TableColumn<Barbeiro, String> nomeColuna = new TableColumn<>("Nome do Profissional");
        nomeColuna.setCellValueFactory(new PropertyValueFactory<>("nome"));

        TableColumn<Barbeiro, Void> colEditar = new TableColumn<>("Editar");
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
                    Barbeiro barbeiro = getTableView().getItems().get(getIndex());
                    barbeiroEmEdicao = barbeiro;
                    nomeField.setText(barbeiro.getNome());
                    salvarButton.setText("Atualizar Profissional");
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : btn);
            }
        });

        TableColumn<Barbeiro, Void> colExcluir = new TableColumn<>("Excluir");
        colExcluir.setPrefWidth(70);
        colExcluir.setStyle("-fx-alignment: CENTER;");
        colExcluir.setCellFactory(param -> new TableCell<>() {

            private final Button btn = new Button();

            {
                btn.getStyleClass().add("btn-acao-tabela");

                FontIcon icone = new FontIcon("fas-trash-alt");
                icone.getStyleClass().add("icon-excluir");

                btn.setGraphic(icone);
                btn.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);

                btn.setOnAction(event -> {
                    Barbeiro barbeiro = getTableView().getItems().get(getIndex());

                    Alert confirmacao = new Alert(
                            Alert.AlertType.CONFIRMATION,
                            "Deseja remover o profissional " + barbeiro.getNome() + "?",
                            ButtonType.YES,
                            ButtonType.NO
                    );
                    confirmacao.setHeaderText(null);

                    confirmacao.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.YES) {
                            try {
                                barbeiroService.deleteBarbeiro(barbeiro);
                                atualizarTabela();
                                limparFormulario();
                                AlertComponent.sucesso("Profissional removido do sistema.");
                            } catch (IllegalArgumentException | IllegalStateException e) {
                                AlertComponent.aviso(e.getMessage());
                            } catch (Exception e) {
                                AlertComponent.erro("Erro inesperado ao excluir profissional.");
                            }
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

        tabela.getColumns().addAll(idColuna, nomeColuna, colEditar, colExcluir);
        tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tabela.setPlaceholder(new Label("Nenhum barbeiro cadastrado."));

        // SALVAR / ATUALIZAR
        salvarButton.setOnAction(e -> {
            try {
                String nome = nomeField.getText().trim();

                if (barbeiroEmEdicao == null) {
                    Barbeiro b = new Barbeiro();
                    b.setNome(nome);
                    barbeiroService.createBarbeiro(b);
                    AlertComponent.sucesso("Barbeiro cadastrado com sucesso.");
                } else {
                    barbeiroEmEdicao.setNome(nome);
                    barbeiroService.updateBarbeiro(barbeiroEmEdicao);
                    AlertComponent.sucesso("Cadastro do barbeiro atualizado.");
                }

                atualizarTabela();
                limparFormulario();

            } catch (IllegalArgumentException ex) {
                AlertComponent.aviso(ex.getMessage());
            } catch (Exception ex) {
                AlertComponent.erro("Erro ao processar o formulário do barbeiro.");
            }
        });

        atualizarTabela();

        layoutPrincipal.getChildren().addAll(tabela, painelFormulario);
        this.getChildren().addAll(headerBox, divider, layoutPrincipal);
    }

    private void atualizarTabela() {
        tabela.setItems(FXCollections.observableArrayList(barbeiroService.readAllBarbeiro()));
    }

    private void limparFormulario() {
        nomeField.clear();
        barbeiroEmEdicao = null;
        salvarButton.setText("Salvar Barbeiro");
    }
}