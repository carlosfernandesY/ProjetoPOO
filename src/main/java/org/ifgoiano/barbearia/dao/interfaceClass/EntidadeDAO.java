package org.ifgoiano.barbearia.dao.interfaceClass;


public interface EntidadeDAO<T> {
    boolean create(T object);
    T readById(Integer id);
    boolean deleteById(T object);
    void updateById(T object );


}