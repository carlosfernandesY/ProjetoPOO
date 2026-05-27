package org.ifgoiano.barbearia.view.components;

import javafx.scene.control.Alert;

public class AlertComponent {

    public static void sucesso(String mensagem) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Sucesso");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);

        alert.showAndWait();
    }

    public static void erro(String mensagem) {

        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setTitle("Erro");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);

        alert.showAndWait();
    }

    public static void aviso(String mensagem) {

        Alert alert = new Alert(Alert.AlertType.WARNING);

        alert.setTitle("Aviso");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);

        alert.showAndWait();
    }
}