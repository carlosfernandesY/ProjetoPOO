package org.ifgoiano.barbearia.dao;


import org.ifgoiano.barbearia.dao.interfaceClass.EntidadeDAO;
import org.ifgoiano.barbearia.model.Barbeiro;

public class BarbeiroDAO implements EntidadeDAO<Barbeiro> {

    @Override
    public void create(Barbeiro object) {

    }

    @Override
    public Barbeiro readById(Integer id) {
        return null;
    }

    @Override
    public boolean deleteById(Barbeiro barbeiro) {
        return false;
    }

    @Override
    public void updateById(Barbeiro object) {


    }
}
