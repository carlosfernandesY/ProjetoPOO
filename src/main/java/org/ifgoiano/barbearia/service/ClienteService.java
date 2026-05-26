package org.ifgoiano.barbearia.service;

import org.ifgoiano.barbearia.dao.ClienteDAO;

public class ClienteService {
    private ClienteDAO clienteDAO;

    public ClienteService(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

}
