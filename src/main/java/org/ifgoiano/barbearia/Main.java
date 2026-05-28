package org.ifgoiano.barbearia;

import org.ifgoiano.barbearia.dao.ClienteDAO;
import org.ifgoiano.barbearia.model.Cliente;
import org.ifgoiano.barbearia.service.ClienteService;
import org.ifgoiano.barbearia.view.main.MainView;

public class Main {

    public static void main(String[] args) {

        // Carrega os componentes do JavaFX
       MainView.main(args);
    }
}