package org.ifgoiano.barbearia.view.atendimento;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import org.ifgoiano.barbearia.model.Atendimento;
import org.ifgoiano.barbearia.model.Barbeiro;
import org.ifgoiano.barbearia.model.Cliente;
import org.ifgoiano.barbearia.service.AtendimentoService;
import org.ifgoiano.barbearia.service.BarbeiroService;
import org.ifgoiano.barbearia.service.ClienteService;
import org.ifgoiano.barbearia.view.components.AlertComponent;
import java.sql.Date;
import org.kordamp.ikonli.javafx.FontIcon;

public class AtendimentoView extends VBox {

    private final TableView<Atendimento> tabela = new TableView<>();
    private final ComboBox<Cliente> clienteBox = new ComboBox<>();
    private final ComboBox<Barbeiro> barbeiroBox = new ComboBox<>();
    private final TextField valorField = new TextField();
    private final DatePicker dataPicker = new DatePicker();
    private final Button salvarButton = new Button("Salvar Atendimento");
    
    private final ClienteService clienteService;
    private final BarbeiroService barbeiroService;
    private final AtendimentoService atendimentoService;
    
    private Atendimento atendimentoEmEdicao = null;

    public AtendimentoView(ClienteService clienteService, BarbeiroService barbeiroService, AtendimentoService atendimentoService) {
        this.clienteService = clienteService;
        this.barbeiroService = barbeiroService;
        this.atendimentoService = atendimentoService;
        
        this.setSpacing(20);
        this.setPadding(new Insets(36, 40, 36, 40));
        this.getStyleClass().add("content-area");

        // CABEÇALHO DA SEÇÃO
        VBox headerBox = new VBox(4);
        Label tituloSecao = new Label("Histórico de Atendimentos");
        tituloSecao.getStyleClass().add("page-title");
        Label subtitulo = new Label("REGISTRO DE AGENDAMENTOS — ALTERAR E EXCLUIR");
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

        Label formTitle = new Label("Dados do Atendimento");
        formTitle.getStyleClass().add("form-title");

        clienteBox.setPromptText("Selecione o Cliente");
        clienteBox.setMaxWidth(Double.MAX_VALUE);
        clienteBox.setItems(FXCollections.observableArrayList(clienteService.readAllCliente()));

        barbeiroBox.setPromptText("Selecione o Barbeiro");
        barbeiroBox.setMaxWidth(Double.MAX_VALUE);
        barbeiroBox.setItems(FXCollections.observableArrayList(barbeiroService.readAllBarbeiro()));

        valorField.setPromptText("Valor Total (R$)");
        dataPicker.setPromptText("Data do Atendimento");
        dataPicker.setMaxWidth(Double.MAX_VALUE);

        salvarButton.getStyleClass().add("button-primary");
        salvarButton.setMaxWidth(Double.MAX_VALUE);

        painelFormulario.getChildren().addAll(formTitle, clienteBox, barbeiroBox, valorField, dataPicker, salvarButton);

        
        TableColumn<Atendimento, Integer> idColuna = new TableColumn<>("ID");
        idColuna.setCellValueFactory(new PropertyValueFactory<>("idAtendimento"));
        idColuna.setPrefWidth(50);
        idColuna.setStyle("-fx-alignment: CENTER;");

        TableColumn<Atendimento, Cliente> clienteColuna = new TableColumn<>("Cliente");
        clienteColuna.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        clienteColuna.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Cliente item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getNome());
            }
        });

        TableColumn<Atendimento, Barbeiro> barbeiroColuna = new TableColumn<>("Barbeiro");
        barbeiroColuna.setCellValueFactory(new PropertyValueFactory<>("barbeiro"));
        barbeiroColuna.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Barbeiro item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getNome());
            }
        });

        TableColumn<Atendimento, Date> dataColuna = new TableColumn<>("Data");
        dataColuna.setCellValueFactory(new PropertyValueFactory<>("data"));

        TableColumn<Atendimento, Double> valorColuna = new TableColumn<>("Valor");
        valorColuna.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));
        valorColuna.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : String.format("R$ %.2f", item));
            }
        });

       
        TableColumn<Atendimento, Void> colEditar = new TableColumn<>("Editar");
        colEditar.setPrefWidth(65);
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
                    atendimentoEmEdicao = getTableView().getItems().get(getIndex());

                    clienteBox.setValue(atendimentoEmEdicao.getCliente());
                    barbeiroBox.setValue(atendimentoEmEdicao.getBarbeiro());
                    valorField.setText(String.valueOf(atendimentoEmEdicao.getValorTotal()));

                    if (atendimentoEmEdicao.getData() != null) {
                        dataPicker.setValue(atendimentoEmEdicao.getData().toLocalDate());
                    }

                    salvarButton.setText("Atualizar Registro");
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : btn);
            }
        });

       
        TableColumn<Atendimento, Void> colExcluir = new TableColumn<>("Excluir");
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
                    Atendimento atendimento = getTableView().getItems().get(getIndex());

                    Alert confirmacao = new Alert(
                            Alert.AlertType.CONFIRMATION,
                            "Deseja remover este atendimento do cliente " + atendimento.getCliente().getNome() + "?",
                            ButtonType.YES,
                            ButtonType.NO
                    );
                    confirmacao.setHeaderText(null);
                    confirmacao.setTitle("Confirmar Exclusão");

                    confirmacao.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.YES) {
                            try {
                               
                                atendimentoService.deleteAtendimento(atendimento);
                                atualizarTabela();
                                limparFormulario();
                                AlertComponent.sucesso("Atendimento removido com sucesso.");
                            } catch (IllegalArgumentException | IllegalStateException e) {
                                AlertComponent.aviso(e.getMessage());
                            } catch (Exception e) {
                                AlertComponent.erro("Erro inesperado ao excluir atendimento.");
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

        
        salvarButton.setOnAction(e -> {
            try {
                
                double valor = 0.0;
                if (!valorField.getText().trim().isEmpty()) {
                    valor = Double.parseDouble(valorField.getText().replace(",", "."));
                }
                
                Date dataSql = (dataPicker.getValue() != null) ? Date.valueOf(dataPicker.getValue()) : null;

                if (atendimentoEmEdicao == null) {
                    Atendimento atendimento = new Atendimento();
                    atendimento.setCliente(clienteBox.getValue());
                    atendimento.setBarbeiro(barbeiroBox.getValue());
                    atendimento.setValorTotal(valor);
                    atendimento.setData(dataSql);

                    
                    atendimentoService.createAtendimento(atendimento);
                    AlertComponent.sucesso("Atendimento salvo com sucesso.");
                } else {
                    atendimentoEmEdicao.setCliente(clienteBox.getValue());
                    atendimentoEmEdicao.setBarbeiro(barbeiroBox.getValue());
                    atendimentoEmEdicao.setValorTotal(valor);
                    atendimentoEmEdicao.setData(dataSql);

                    atendimentoService.updateAtendimento(atendimentoEmEdicao);
                    AlertComponent.sucesso("Atendimento atualizado com sucesso.");
                }

                atualizarTabela();
                limparFormulario();

            } catch (NumberFormatException ex) {
                
                AlertComponent.aviso("Valor inválido. Utilize apenas números e vírgula.");
            } catch (IllegalArgumentException | IllegalStateException ex) {
                
                AlertComponent.aviso(ex.getMessage());
            } catch (Exception ex) {
                ex.printStackTrace();
                AlertComponent.erro("Erro ao salvar atendimento.");
            }
        });

        tabela.getColumns().addAll(idColuna, clienteColuna, barbeiroColuna, dataColuna, valorColuna, colEditar, colExcluir);
        tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tabela.setPlaceholder(new Label("Nenhum atendimento registrado."));

        atualizarTabela();

        layoutPrincipal.getChildren().addAll(tabela, painelFormulario);
        this.getChildren().addAll(headerBox, divider, layoutPrincipal);
    }

    private void atualizarTabela() {
        tabela.setItems(FXCollections.observableArrayList(atendimentoService.readAllAtendimento()));
    }

    private void limparFormulario() {
        valorField.clear();
        clienteBox.setValue(null);
        barbeiroBox.setValue(null);
        dataPicker.setValue(null);
        atendimentoEmEdicao = null;
        salvarButton.setText("Salvar Atendimento");

        clienteBox.setItems(FXCollections.observableArrayList(clienteService.readAllCliente()));
        barbeiroBox.setItems(FXCollections.observableArrayList(barbeiroService.readAllBarbeiro()));
    }
}