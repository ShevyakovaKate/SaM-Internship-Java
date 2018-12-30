package com.samsolutions.service.impl;

import com.samsolutions.converter.OrderPayStatusConverter;
import com.samsolutions.dao.PayStatusDao;
import com.samsolutions.dto.OrderPayStatusDTO;
import com.samsolutions.entity.OrderPayStatusEntity;
import com.samsolutions.service.PayStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PayStatusServiceImpl implements PayStatusService {

    @Autowired
    private PayStatusDao payStatusDao;

    @Override
    public List<OrderPayStatusDTO> getAll() {
        return null;
    }

    @Override
    public OrderPayStatusDTO getById(Long id) {
        return null;
    }

    @Override
    public OrderPayStatusDTO getByName(String name) {
        OrderPayStatusEntity payStatusEntity = payStatusDao.findByName(name);
        return OrderPayStatusConverter.toDto(payStatusEntity);
    }

    @Override
    public OrderPayStatusDTO add(OrderPayStatusDTO dto) {
        return null;
    }

    @Override
    public OrderPayStatusDTO update(OrderPayStatusDTO dto, Long id) {
        return null;
    }

    @Override
    public void delete(OrderPayStatusDTO dto) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
