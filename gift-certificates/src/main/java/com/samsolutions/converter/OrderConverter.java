package com.samsolutions.converter;

import com.samsolutions.dto.GiftCertificateDTO;
import com.samsolutions.dto.OrderDTO;
import com.samsolutions.dto.OrderPayStatusDTO;
import com.samsolutions.dto.UserDTO;
import com.samsolutions.entity.GiftCertificateEntity;
import com.samsolutions.entity.OrderEntity;
import com.samsolutions.entity.OrderPayStatusEntity;
import com.samsolutions.entity.UserEntity;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

public class OrderConverter {

    public static OrderDTO toDto(OrderEntity entity) {
        OrderDTO dto = null;//TODO ту чертовщину убрать нужно
        if (entity != null) {
            dto = new OrderDTO();
            dto.setId(entity.getId());
            GiftCertificateDTO giftCertificateDTO = GiftCertificateConverter.toDto(entity.getGiftCertificate());
            dto.setGiftCertificate(giftCertificateDTO);
            UserDTO userDTO = UserConverter.toDto(entity.getUser());
            dto.setUser(userDTO);
            OrderPayStatusDTO orderPayStatusDTO = OrderPayStatusConverter.toDto(entity.getPayStatus());
            dto.setPayStatus(orderPayStatusDTO);
            dto.setDateTime(entity.getDateTime());
        }
        return dto;
    }

    public static List<OrderDTO> toDtoList(List<OrderEntity> entityList) {
        List<OrderDTO> resultList = null;
        if (CollectionUtils.isNotEmpty(entityList)) {
            resultList = entityList.stream().map(OrderConverter::toDto).collect(Collectors.toList());
        }
        return resultList;
    }

    public static OrderEntity toEntity(OrderDTO dto) {
        OrderEntity entity = new OrderEntity();
        entity.setId(dto.getId());
        GiftCertificateEntity giftCertificateEntity = GiftCertificateConverter.toEntity(dto.getGiftCertificate());
        entity.setGiftCertificate(giftCertificateEntity);
        UserEntity userEntity = UserConverter.toEntity(dto.getUser());
        entity.setUser(userEntity);
        OrderPayStatusEntity orderPayStatusEntity = OrderPayStatusConverter.toEntity(dto.getPayStatus());
        entity.setPayStatus(orderPayStatusEntity);
        entity.setDateTime(dto.getDateTime());
        return entity;
    }

    public static List<OrderEntity> toEntitySet(List<OrderDTO> dtoSet) {
        List<OrderEntity> resultList = null;
        if (CollectionUtils.isNotEmpty(dtoSet)) {
            resultList = dtoSet.stream().map(OrderConverter::toEntity).collect(Collectors.toList());
        }
        return resultList;
    }


}
