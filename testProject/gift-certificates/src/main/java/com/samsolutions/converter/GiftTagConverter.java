package com.samsolutions.converter;

import com.samsolutions.dto.GiftTagDTO;
import com.samsolutions.entity.GiftTagEntity;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Set;
import java.util.stream.Collectors;

public class GiftTagConverter {

    public static GiftTagDTO toDto(GiftTagEntity entity) {
        GiftTagDTO dto = new GiftTagDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }

    public static Set<GiftTagDTO> toDtoSet(Set<GiftTagEntity> entitySet) {
        Set<GiftTagDTO> resultSet = null;
        if (CollectionUtils.isNotEmpty(entitySet)) {
            resultSet = entitySet.stream().map(GiftTagConverter::toDto).collect(Collectors.toSet());
        }
        return resultSet;
    }

    public static GiftTagEntity toEntity(GiftTagDTO dto) {
        GiftTagEntity entity = new GiftTagEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        return entity;
    }

    public static Set<GiftTagEntity> toEntitySet(Set<GiftTagDTO> dtoSet) {
        Set<GiftTagEntity> resultSet = null;
        if (CollectionUtils.isNotEmpty(dtoSet)) {
            resultSet = dtoSet.stream().map(GiftTagConverter::toEntity).collect(Collectors.toSet());
        }
        return resultSet;
    }









}
