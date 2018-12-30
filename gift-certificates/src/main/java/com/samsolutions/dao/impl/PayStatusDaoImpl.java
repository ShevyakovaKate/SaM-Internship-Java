package com.samsolutions.dao.impl;

import com.samsolutions.dao.PayStatusDao;
import com.samsolutions.entity.OrderPayStatusEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class PayStatusDaoImpl extends GenericDaoImpl<OrderPayStatusEntity> implements PayStatusDao {

    @Override
    public OrderPayStatusEntity findByName(String name) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<OrderPayStatusEntity> criteria = criteriaBuilder.createQuery( OrderPayStatusEntity.class );
        Root<OrderPayStatusEntity> OrderPayStatusEntityRoot = criteria.from( OrderPayStatusEntity.class );
        criteria.select( OrderPayStatusEntityRoot );
        criteria.where( criteriaBuilder.equal( OrderPayStatusEntityRoot.get("name"), name ) );
        return getEntityManager().createQuery( criteria ).getSingleResult();
    }
}
