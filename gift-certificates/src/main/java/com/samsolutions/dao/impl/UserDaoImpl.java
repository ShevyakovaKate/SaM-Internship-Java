package com.samsolutions.dao.impl;

import com.samsolutions.dao.UserDao;
import com.samsolutions.entity.GiftCertificateEntity;
import com.samsolutions.entity.UserEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class UserDaoImpl extends GenericDaoImpl<UserEntity> implements UserDao {

    @Override
    public UserEntity findByName(String login) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<UserEntity> criteria = criteriaBuilder.createQuery( UserEntity.class );
        Root<UserEntity> userEntityRoot = criteria.from( UserEntity.class );
        criteria.select( userEntityRoot );
        criteria.where( criteriaBuilder.equal( userEntityRoot.get("login"), login ) );
        UserEntity singleResult = new UserEntity();
        try {
            singleResult = getEntityManager().createQuery(criteria).getSingleResult();
        } catch (NoResultException ex) {
            //TODO logger
            return null;
        }
        return singleResult;
    }
}
