package org.ifgoiano.barbearia.service;

import java.util.List;

import org.ifgoiano.barbearia.dao.ClienteDAO;
import org.ifgoiano.barbearia.model.Cliente;


public class ClienteService {
    private final ClienteDAO clienteDAO;

    public ClienteService(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    public boolean createCliente(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("O cliente não pode ser nulo.");
        }
        if (cliente.getNome() == null || cliente.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do cliente é obrigatório para o cadastro.");
        }
        if (cliente.getTelefone() == null || cliente.getTelefone().length() < 8) {
            throw new IllegalArgumentException("Informe um número de telefone válido.");
        }
        return this.clienteDAO.create(cliente);
    }

    public Cliente read(int idCliente) {
        if (idCliente <= 0) {
            throw new IllegalArgumentException("ID de cliente inválido. O ID deve ser maior que zero.");
        }
        return this.clienteDAO.readById(idCliente);
    }

    public void update(Cliente cliente) {
        if (cliente == null || cliente.getIdCliente() <= 0) {
            throw new IllegalArgumentException("Cliente inválido para atualização. Verifique os dados.");
        }
        
        if (cliente.getNome() == null || cliente.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome não pode ser apagado na atualização.");
        }

        this.clienteDAO.updateById(cliente);
    }

    public boolean delete(Cliente cliente) {
        if (cliente == null || cliente.getIdCliente() <= 0) {
            throw new IllegalArgumentException("Cliente inválido para exclusão.");
        }
        return this.clienteDAO.deleteById(cliente);
    }

    public List<Cliente> readAllCliente() {
        return this.clienteDAO.readAll();
    }
}
