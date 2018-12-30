package com.samsolutions.dao;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

public interface GenericDao<T extends Serializable> {
    T find(Long id);

    List<T> findAll();

    T save(T entity );

    T update(T entity );

    void delete(T entity );

    void deleteById(Long id);

    EntityManager getEntityManager();
}
