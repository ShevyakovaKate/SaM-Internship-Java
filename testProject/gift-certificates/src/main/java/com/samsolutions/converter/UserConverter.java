package com.samsolutions.converter;

import com.samsolutions.dto.RoleDTO;
import com.samsolutions.dto.SignUpInformationDTO;
import com.samsolutions.dto.UserDTO;
import com.samsolutions.entity.RoleEntity;
import com.samsolutions.entity.UserEntity;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;


//TODO исправить конрертеры с проверками на нал правильными
public class UserConverter {

    public UserConverter() {
    }

    public static UserDTO toDto(UserEntity entity) {
        UserDTO dto = null; //TODO check null
        if(entity != null) {
            dto = new UserDTO();
            dto.setId(entity.getId());
            dto.setLogin(entity.getLogin());
            dto.setEmail(entity.getEmail());
            dto.setPassword(entity.getPassword());
            RoleDTO roleDTO = RoleConverter.toDto(entity.getRole());
            dto.setRole(roleDTO);
        }
        return dto;
    }

    public static List<UserDTO> toDtoList(List<UserEntity> entityList) {
        List<UserDTO> resultList = null;
        if (CollectionUtils.isNotEmpty(entityList)) {
            resultList = entityList.stream().map(UserConverter::toDto).collect(Collectors.toList());
        }
        return resultList;
    }

    public static UserEntity toEntity(UserDTO dto) {
        UserEntity entity = new UserEntity();
        entity.setId(dto.getId());
        entity.setLogin(dto.getLogin());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        RoleEntity roleEntity = RoleConverter.toEntity(dto.getRole());
        entity.setRole(roleEntity);
        return entity;
    }

    public static UserEntity toEntity(SignUpInformationDTO dto) {
        UserEntity entity = new UserEntity();
        entity.setLogin(dto.getLogin());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
//        RoleEntity roleEntity = RoleConverter.toEntity(dto.getRole());
//        entity.setRole(roleEntity);
        return entity;
    }

    public static List<UserEntity> toEntityList(List<UserDTO> dtoList) {
        List<UserEntity> resultList = null;
        if (CollectionUtils.isNotEmpty(dtoList)) {
            resultList = dtoList.stream().map(UserConverter::toEntity).collect(Collectors.toList());
        }
        return resultList;
    }

}
