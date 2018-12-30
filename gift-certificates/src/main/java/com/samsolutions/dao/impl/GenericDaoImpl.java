package com.samsolutions.dao.impl;

import com.samsolutions.dao.GenericDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@Repository
public abstract class GenericDaoImpl<T extends Serializable> implements GenericDao<T> {
    private Class<T> clazz;

    @PersistenceContext
    private EntityManager em;

    public GenericDaoImpl() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.clazz = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
    }

    public void setClazz(Class<T> clazz){
        this.clazz = clazz;
    }

    @Override
    public T find(Long id) {
        return em.find(clazz, id);
    }

    @Override
    public List<T> findAll() {
        String hql = "Select a From " + clazz.getName() + " a";
        TypedQuery<T> query = em.createQuery(hql, clazz);
        return query.getResultList();
    }

    @Override
    public T save(T entity) {
        em.persist(entity);
        return entity;
    }

    @Override
    public T update(T entity) {
        return (T) em.merge(entity);
    }

    @Override
    public void delete(T entity) {
        em.remove(entity);
    }

    @Override
    public void deleteById(Long id) {
        T entity = find(id);
        em.remove(entity);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }


}
