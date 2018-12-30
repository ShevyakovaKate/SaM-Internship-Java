package com.samsolutions.dao.impl;

import com.samsolutions.dao.OrderDao;
import com.samsolutions.entity.OrderEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class OrderDaoImpl extends GenericDaoImpl<OrderEntity> implements OrderDao {

    private static final String SORT_DATETIME_DESC = "desc";


    @Override
    public List<OrderEntity> findByUserId(Long id) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<OrderEntity> criteria = criteriaBuilder.createQuery( OrderEntity.class );
        Root<OrderEntity> orderEntityRoot = criteria.from( OrderEntity.class );
        criteria.select(orderEntityRoot);
        criteria.where(criteriaBuilder.equal(orderEntityRoot.get("user"), id));

        List<OrderEntity> resultList = getEntityManager().createQuery(criteria).getResultList();
        if (resultList.isEmpty()) {
            return null;
        }
        return resultList;
    }

    @Override
    public List<OrderEntity> findByParameter(String status, LocalDateTime period, String sort) {
        CriteriaQuery<OrderEntity> select = getOrderCriteriaQuery(status, period, sort);
        TypedQuery<OrderEntity> query = getEntityManager().createQuery(select);
        return query.getResultList();
    }

    private CriteriaQuery<OrderEntity> getOrderCriteriaQuery(String status, LocalDateTime period, String sort) { //TODO period int
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<OrderEntity> criteriaQuery = criteriaBuilder.createQuery(OrderEntity.class);
        Root<OrderEntity> orderRoot = criteriaQuery.from(OrderEntity.class);

        Predicate resultPredicate = criteriaBuilder.conjunction();
        if (!StringUtils.isBlank(status)) {
            Predicate statusPredicate = predicateByStatusPart(status, criteriaBuilder, orderRoot);
            resultPredicate = criteriaBuilder.and(resultPredicate, statusPredicate);
        }

        if (period != null) {
            Predicate periodPredicate = predicateByPeriod(period, criteriaBuilder, orderRoot);
            resultPredicate = criteriaBuilder.and(resultPredicate, periodPredicate);
        }

        if(!StringUtils.isBlank(sort)) {
            Order order = orderBySortType(sort, criteriaBuilder, orderRoot);
            return criteriaQuery.select(orderRoot).where(resultPredicate).orderBy(order);
        }
        return criteriaQuery.select(orderRoot).where(resultPredicate);
    }

    private Predicate predicateByStatusPart(String status, CriteriaBuilder criteriaBuilder,
                                          Root<OrderEntity> orderRoot) {
        return criteriaBuilder.equal(orderRoot.join("payStatus").get("name"), status);
    }

    private Predicate predicateByPeriod(LocalDateTime period, CriteriaBuilder criteriaBuilder,
                                        Root<OrderEntity> orderRoot) {

        return criteriaBuilder.greaterThanOrEqualTo(orderRoot.get("dateTime"), period);
    }

    private Order orderBySortType(String sortBy, CriteriaBuilder criteriaBuilder,
                                  Root<OrderEntity> certificateRoot) {
        if (!SORT_DATETIME_DESC.equals(sortBy)) {
            return criteriaBuilder.desc(certificateRoot.get("dateTime"));
        }

        return criteriaBuilder.asc(certificateRoot.get("dateTime"));
    }
}
