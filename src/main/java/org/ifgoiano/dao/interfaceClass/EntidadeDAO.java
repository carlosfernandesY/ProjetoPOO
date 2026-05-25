package org.ifgoiano.dao.interfaceClass;


public interface EntidadeDAO<T> {
    void create(T object);
    T readById(Integer id);
    boolean deleteById(T object);
    void updateById(T object );


}