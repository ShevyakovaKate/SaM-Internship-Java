package com.samsolutions.dao;

import com.samsolutions.entity.OrderPayStatusEntity;

public interface PayStatusDao extends GenericDao<OrderPayStatusEntity> {
    OrderPayStatusEntity findByName(String name);
}
