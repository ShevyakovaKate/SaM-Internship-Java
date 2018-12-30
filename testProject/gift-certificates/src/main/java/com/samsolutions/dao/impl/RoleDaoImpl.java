package com.samsolutions.dao.impl;

import com.samsolutions.dao.RoleDao;
import com.samsolutions.entity.RoleEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class RoleDaoImpl extends GenericDaoImpl<RoleEntity> implements RoleDao {

    @Override
    public RoleEntity findByName(String name) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<RoleEntity> criteria = criteriaBuilder.createQuery( RoleEntity.class );
        Root<RoleEntity> roleEntityRoot = criteria.from( RoleEntity.class );
        criteria.select( roleEntityRoot );
        criteria.where( criteriaBuilder.equal( roleEntityRoot.get("name"), name ) );
        return getEntityManager().createQuery( criteria ).getSingleResult();
    }
}
