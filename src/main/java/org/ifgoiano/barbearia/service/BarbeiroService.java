package org.ifgoiano.barbearia.service;

import org.ifgoiano.barbearia.dao.BarbeiroDAO;
import org.ifgoiano.barbearia.model.Barbeiro;

public class BarbeiroService {
    private final BarbeiroDAO barbeiroDAO;

    public BarbeiroService(BarbeiroDAO barbeiroDAO) {
        this.barbeiroDAO = barbeiroDAO;
    }

    public void createBarbeiro(Barbeiro barbeiro) {
        this.barbeiroDAO.create(barbeiro);
    }

    public void readBarbeiro(int idBarbeiro) {
        this.barbeiroDAO.readById(idBarbeiro);
    }

    public void updateBarbeiro(Barbeiro barbeiro) {
        this.barbeiroDAO.updateById(barbeiro);
    }

    public void deleteBarbeiro(Barbeiro barbeiro) {
        this.barbeiroDAO.deleteById(barbeiro);
    }
}
