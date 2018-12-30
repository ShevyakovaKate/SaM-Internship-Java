package com.samsolutions.converter;

import com.samsolutions.dto.RoleDTO;
import com.samsolutions.entity.RoleEntity;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

public class RoleConverter {

    public static RoleDTO toDto(RoleEntity entity) {
        RoleDTO dto = new RoleDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }

    public static List<RoleDTO> toDtoList(List<RoleEntity> entityList) {
        List<RoleDTO> resultList = null;
        if (CollectionUtils.isNotEmpty(entityList)) {
            resultList = entityList.stream().map(RoleConverter::toDto).collect(Collectors.toList());
        }
        return resultList;
    }

    public static RoleEntity toEntity(RoleDTO dto) {
        RoleEntity entity = new RoleEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        return entity;
    }

    public static List<RoleEntity> toEnityList(List<RoleDTO> dtoList) {
        List<RoleEntity> resultList = null;
        if (CollectionUtils.isNotEmpty(dtoList)) {
            resultList = dtoList.stream().map(RoleConverter::toEntity).collect(Collectors.toList());
        }
        return resultList;
    }
}
