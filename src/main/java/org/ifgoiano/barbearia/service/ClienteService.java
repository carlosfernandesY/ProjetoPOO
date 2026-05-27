package org.ifgoiano.barbearia.service;

import org.ifgoiano.barbearia.dao.ClienteDAO;
import org.ifgoiano.barbearia.model.Cliente;

public class ClienteService {
    private final ClienteDAO clienteDAO;

    public ClienteService(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }
    public void createCliente (Cliente cliente){
        this.clienteDAO.create(cliente);
    }
    public Cliente read(int  idCliente ){
        return this.clienteDAO.readById(idCliente);
    }
    public void update(Cliente cliente){
        this.clienteDAO.updateById(cliente);
    }
    public void delete(Cliente cliente){
        this.clienteDAO.deleteById(cliente);
    }

}
