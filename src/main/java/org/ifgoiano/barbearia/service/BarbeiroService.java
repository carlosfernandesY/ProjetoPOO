package org.ifgoiano.barbearia.service;

import java.util.List;

import org.ifgoiano.barbearia.dao.BarbeiroDAO;
import org.ifgoiano.barbearia.model.Barbeiro;

public class BarbeiroService {
    private final BarbeiroDAO barbeiroDAO;

    public BarbeiroService(BarbeiroDAO barbeiroDAO) {
        this.barbeiroDAO = barbeiroDAO;
    }

    public void createBarbeiro(Barbeiro barbeiro) {
        if (barbeiro == null) {
            throw new IllegalArgumentException("O barbeiro não pode ser nulo.");
        }
        if (barbeiro.getNome() == null || barbeiro.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do barbeiro é obrigatório.");
        }
        this.barbeiroDAO.create(barbeiro);
    }

    public Barbeiro readBarbeiro(int idBarbeiro) {
        if (idBarbeiro <= 0) {
            throw new IllegalArgumentException("ID do barbeiro inválido. Deve ser maior que zero.");
        }
        
        return this.barbeiroDAO.readById(idBarbeiro);
    }

    public void updateBarbeiro(Barbeiro barbeiro) {
        if (barbeiro == null || barbeiro.getIdBarbeiro() <= 0) {
            throw new IllegalArgumentException("Barbeiro inválido para atualização.");
        }

        if (barbeiro.getNome() == null || barbeiro.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do barbeiro não pode ser apagado na atualização.");
        }

        this.barbeiroDAO.updateById(barbeiro);
    }

    public void deleteBarbeiro(Barbeiro barbeiro) {
        if (barbeiro == null || barbeiro.getIdBarbeiro() <= 0) {
            throw new IllegalArgumentException("Barbeiro inválido para exclusão.");
        }
        this.barbeiroDAO.deleteById(barbeiro);
    }

    public List<Barbeiro> readAllBarbeiro() {
        return this.barbeiroDAO.readAll();
    }
}
