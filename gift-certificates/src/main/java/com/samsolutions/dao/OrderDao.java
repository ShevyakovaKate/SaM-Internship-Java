package com.samsolutions.dao;

import com.samsolutions.entity.OrderEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderDao extends GenericDao<OrderEntity> {
    List<OrderEntity> findByUserId(Long id);
    List<OrderEntity> findByParameter(String status, LocalDateTime period, String sort);
}
