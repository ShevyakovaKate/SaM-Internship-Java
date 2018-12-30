package com.samsolutions.converter;

import com.samsolutions.dto.OrderPayStatusDTO;
import com.samsolutions.entity.OrderPayStatusEntity;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

public class OrderPayStatusConverter {

    public static OrderPayStatusDTO toDto(OrderPayStatusEntity entity) {
        OrderPayStatusDTO dto = new OrderPayStatusDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }

    public static List<OrderPayStatusDTO> toDtoList(List<OrderPayStatusEntity> entityList) {
        List<OrderPayStatusDTO> resultList = null;
        if (CollectionUtils.isNotEmpty(entityList)) {
            resultList = entityList.stream().map(OrderPayStatusConverter::toDto).collect(Collectors.toList());
        }
        return resultList;
    }

    public static OrderPayStatusEntity toEntity(OrderPayStatusDTO dto) {
        OrderPayStatusEntity entity = new OrderPayStatusEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        return entity;
    }

    public static List<OrderPayStatusEntity> toEnityList(List<OrderPayStatusDTO> dtoList) {
        List<OrderPayStatusEntity> resultList = null;
        if (CollectionUtils.isNotEmpty(dtoList)) {
            resultList = dtoList.stream().map(OrderPayStatusConverter::toEntity).collect(Collectors.toList());
        }
        return resultList;
    }
}
