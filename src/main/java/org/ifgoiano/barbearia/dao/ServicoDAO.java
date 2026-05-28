package org.ifgoiano.barbearia.dao;

import org.ifgoiano.barbearia.dao.interfaceClass.EntidadeDAO;

import java.sql.Connection;
import java.util.Collection;
import java.util.List;

public class ServicoDAO implements EntidadeDAO<ServicoDAO> {

    Connection connection;
    public ServicoDAO(Connection connection) {
        this.connection = connection;

    }
    @Override
    public boolean create(ServicoDAO object) {




        return false;
    }

    @Override
    public ServicoDAO readById(Integer id) {
        return null;
    }

    @Override
    public boolean updateById(ServicoDAO object) {
        return false;
    }

    @Override
    public boolean deleteById(ServicoDAO object) {
        return false;
    }

    @Override
    public List<ServicoDAO> readAll() {
        return List.of();
    }
}
