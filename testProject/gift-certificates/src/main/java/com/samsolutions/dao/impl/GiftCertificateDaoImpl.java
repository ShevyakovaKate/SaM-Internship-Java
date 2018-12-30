package com.samsolutions.dao.impl;

import com.samsolutions.dao.GiftCertificateDao;
import com.samsolutions.entity.GiftCertificateEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class GiftCertificateDaoImpl extends GenericDaoImpl<GiftCertificateEntity> implements GiftCertificateDao {


    @Override
    public List<GiftCertificateEntity> findByParameters(String textPart, String tagName) {
        CriteriaQuery<GiftCertificateEntity> select = getCertificateCriteriaQuery(textPart, tagName);
        TypedQuery<GiftCertificateEntity> query = getEntityManager().createQuery(select);
        return query.getResultList();
    }

    private CriteriaQuery<GiftCertificateEntity> getCertificateCriteriaQuery(String textPart, String tagName) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<GiftCertificateEntity> criteriaQuery = criteriaBuilder.createQuery(GiftCertificateEntity.class);
        Root<GiftCertificateEntity> certificateRoot = criteriaQuery.from(GiftCertificateEntity.class);

        Predicate resultPredicate = criteriaBuilder.conjunction();

        if (!StringUtils.isBlank(textPart)) {
            Predicate textPartPredicate = predicateByTextPart(textPart, criteriaBuilder, certificateRoot);
            resultPredicate = criteriaBuilder.and(resultPredicate, textPartPredicate);
        }

        if (!StringUtils.isBlank(tagName)) {
            Predicate tagNamePredicate = predicateBytagName(tagName, criteriaBuilder, certificateRoot);
            resultPredicate = criteriaBuilder.and(resultPredicate, tagNamePredicate);
        }

        return criteriaQuery.select(certificateRoot).where(resultPredicate);
    }

    private Predicate predicateByTextPart(String textPart, CriteriaBuilder criteriaBuilder,
                                          Root<GiftCertificateEntity> certificateRoot) {
        Expression<String> pathName = certificateRoot.get("name");
        Expression<String> pathDescription = certificateRoot.get("description");
        Predicate namePredicate = criteriaBuilder.like(criteriaBuilder.upper(pathName), "%" + textPart.toUpperCase() + "%");
        Predicate descriptionPredicate = criteriaBuilder.like(criteriaBuilder.upper(pathDescription), "%" + textPart.toUpperCase() + "%");
        return criteriaBuilder.or(namePredicate, descriptionPredicate);
    }

    private Predicate predicateBytagName(String tagName, CriteriaBuilder criteriaBuilder,
                                           Root<GiftCertificateEntity> certificateRoot) {
        Expression<String> pathName = certificateRoot.get("name");
        Expression<String> searchExpression = certificateRoot.join("tags").get("name");
        return criteriaBuilder.like(criteriaBuilder.upper(searchExpression), "%" + tagName.toUpperCase() + "%");
    }

}
