package org.ifgoiano.barbearia.dao.interfaceClass;


import org.ifgoiano.barbearia.model.Cliente;

import java.util.List;

public interface EntidadeDAO<T> {

    boolean create(T object);

    T readById(Integer id);
<<<<<<< Updated upstream
    boolean deleteById(T object);
    void updateById(T object );
=======
>>>>>>> Stashed changes

    boolean updateById(T object);

    boolean deleteById(T object);

    List<T> readAll();
}