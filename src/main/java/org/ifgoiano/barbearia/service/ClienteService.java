package org.ifgoiano.barbearia.service;

import org.ifgoiano.barbearia.dao.ClienteDAO;
import org.ifgoiano.barbearia.model.Cliente;

public class ClienteService {
    private ClienteDAO clienteDAO;

    public ClienteService(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    public void salvar (Cliente cliente){
        clienteDAO.create(cliente);
    }
    public void read(int  id ){}

}
