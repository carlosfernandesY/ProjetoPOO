package org.ifgoiano.barbearia;

import org.ifgoiano.barbearia.dao.ClienteDAO;
import org.ifgoiano.barbearia.model.Cliente;
import org.ifgoiano.barbearia.service.ClienteService;
import org.ifgoiano.barbearia.view.main.MainView;

public class Main {

    public static void main(String[] args) {
        Cliente cliente = new ClienteService(new ClienteDAO()).read(4);
        ClienteService clienteService = new ClienteService(new ClienteDAO());
        clienteService.delete(cliente);
        // Carrega os componentes do JavaFX
      //  MainView.main(args);
    }
}