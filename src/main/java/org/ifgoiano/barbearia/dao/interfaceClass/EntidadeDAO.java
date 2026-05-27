package org.ifgoiano.barbearia.dao.interfaceClass;


import org.ifgoiano.barbearia.model.Cliente;

import java.util.List;

public interface EntidadeDAO<T> {

    boolean create(T object);

    T readById(Integer id);

    boolean updateById(T object);

    boolean deleteById(T object);

    List<T> readAll();
}