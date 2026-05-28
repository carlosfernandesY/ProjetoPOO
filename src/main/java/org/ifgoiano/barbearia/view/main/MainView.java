package org.ifgoiano.barbearia.view.main;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import org.ifgoiano.barbearia.dao.AtendimentoDAO;
import org.ifgoiano.barbearia.dao.BarbeiroDAO;
import org.ifgoiano.barbearia.dao.ClienteDAO;
import org.ifgoiano.barbearia.service.AtendimentoService;
import org.ifgoiano.barbearia.service.BarbeiroService;
import org.ifgoiano.barbearia.service.ClienteService;
import org.ifgoiano.barbearia.view.cliente.ClienteView;
import org.ifgoiano.barbearia.view.barbeiro.BarbeiroView;
import org.ifgoiano.barbearia.view.atendimento.AtendimentoView;

import java.util.Objects;

public class MainView extends Application {

    private BorderPane mainLayout;
    private Button btnAtivo = null;

    @Override
    public void start(Stage stage) {
        mainLayout = new BorderPane();

        // =========================================================
        // SIDEBAR — Menu lateral aprimorado
        // =========================================================
        VBox sidebar = new VBox();
        sidebar.getStyleClass().add("sidebar");
        sidebar.setPrefWidth(230);

        VBox brandBox = new VBox(4);
        brandBox.setPadding(new Insets(30, 20, 20, 24));

        Label brandLabel = new Label("BARBEARIA");
        brandLabel.getStyleClass().add("sidebar-title");
        brandLabel.setStyle("-fx-text-fill: #000000; -fx-font-size: 14px; -fx-font-weight: bold; "
                + "-fx-letter-spacing: 3px; -fx-font-family: 'Segoe UI', sans-serif;");

        brandBox.getChildren().addAll(brandLabel);

        // Linha divisória
        Region goldLine = new Region();

        goldLine.setPrefHeight(1);
        goldLine.setMaxWidth(Double.MAX_VALUE);

        goldLine.setStyle(
                "-fx-background-color: rgba(0,0,0,0.25);"
        );

        VBox.setMargin(goldLine, new Insets(0, 20, 16, 24));

        // Rótulo de seção
        Label navLabel = new Label("NAVEGAÇÃO");
        navLabel.setStyle("-fx-text-fill: #2a3550; -fx-font-size: 9px; -fx-font-family: 'Segoe UI', sans-serif;"
                + "-fx-letter-spacing: 2px; -fx-padding: 0 0 6px 24px;");

        // Botões de navegação
        Button btnAtendimentos = criarBotaoMenu("📅", "Atendimentos");
        Button btnClientes     = criarBotaoMenu("👥", "Clientes");
        Button btnBarbeiros    = criarBotaoMenu("✂", "Barbeiros");

        // Espaçador para empurrar o rodapé para baixo
        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        // Rodapé da sidebar
        VBox footerBox = new VBox(4);
        footerBox.setPadding(new Insets(0, 20, 20, 24));

        Region footerLine = new Region();
        footerLine.setPrefHeight(1);
        footerLine.setStyle("-fx-background-color: #1a2438;");
        VBox.setMargin(footerLine, new Insets(0, 0, 12, 0));

        footerBox.getChildren().addAll(footerLine);

        sidebar.getChildren().addAll(
                brandBox, goldLine, navLabel,
                btnAtendimentos, btnClientes, btnBarbeiros,
                spacer, footerBox
        );

        mainLayout.setLeft(sidebar);

        // =========================================================
        // TROCA DINÂMICA DE TELA COM DESTAQUE ATIVO
        // =========================================================
        btnAtendimentos.setOnAction(e -> {
            setActive(btnAtendimentos);
            mainLayout.setCenter(new AtendimentoView(new ClienteService(new ClienteDAO()),new BarbeiroService(new BarbeiroDAO()),new AtendimentoService(new AtendimentoDAO())));
        });

        btnClientes.setOnAction(e -> {
            setActive(btnClientes);
            mainLayout.setCenter(new ClienteView());
        });

        btnBarbeiros.setOnAction(e -> {
            setActive(btnBarbeiros);
            mainLayout.setCenter(new BarbeiroView());
        });

        // Tela padrão: Atendimentos
        setActive(btnAtendimentos);
        mainLayout.setCenter(new AtendimentoView(new ClienteService(new ClienteDAO()),new BarbeiroService(new BarbeiroDAO()),new AtendimentoService(new AtendimentoDAO())));

        // =========================================================
        // SCENE E CSS GLOBAL
        // =========================================================
        Scene scene = new Scene(mainLayout, 1280, 720);

        String cssPath = Objects.requireNonNull(Thread.currentThread()
                        .getContextClassLoader()
                        .getResource("style.css"))
                .toExternalForm();
        scene.getStylesheets().add(cssPath);

        stage.setScene(scene);
        stage.setTitle("Barbearia — Painel de Controle");
        stage.setMaximized(true);
        stage.show();
    }

    /**
     * Cria um botão de navegação lateral com ícone e texto.
     */
    private Button criarBotaoMenu(String icone, String texto) {
        Button btn = new Button(icone + "  " + texto);
        btn.getStyleClass().add("sidebar-button");
        btn.setMaxWidth(Double.MAX_VALUE);
        return btn;
    }

    /**
     * Define o botão ativo (destaca visualmente o item de menu selecionado).
     */
    private void setActive(Button botao) {
        // Remove estilo ativo do botão anterior
        if (btnAtivo != null) {
            btnAtivo.setStyle("");
        }
        // Aplica o estilo de ativo
        botao.setStyle(
                "-fx-background-color: rgba(200,151,42,0.14);"
                        + "-fx-text-fill: #2a6ec8;"
                        + "-fx-border-color: transparent transparent transparent #2a6ec8;"
                        + "-fx-border-width: 0 0 0 3px;"
        );
        btnAtivo = botao;
    }

    public static void main(String[] args) {
        launch(args);
    }
}