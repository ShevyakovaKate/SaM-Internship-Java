package com.samsolutions.converter;

import com.samsolutions.dto.GiftCertificateDTO;
import com.samsolutions.dto.GiftTagDTO;
import com.samsolutions.entity.GiftCertificateEntity;
import com.samsolutions.entity.GiftTagEntity;
import org.apache.commons.collections4.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

public class GiftCertificateConverter {


    public static GiftCertificateDTO toDto(GiftCertificateEntity entity) {
        GiftCertificateDTO dto = new GiftCertificateDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setPrice(entity.getPrice());
        dto.setDateOfCreation(entity.getDateOfCreation());
        dto.setDateOfModification(entity.getDateOfModification());
        dto.setDuration(entity.getDuration());
        Set<GiftTagDTO> giftTagDTOS = GiftTagConverter.toDtoSet(entity.getTags());
        dto.setTags(giftTagDTOS);
        return dto;
    }

    public static List<GiftCertificateDTO> toDtoList(List<GiftCertificateEntity> entityList) {
        List<GiftCertificateDTO> resultList = Collections.emptyList();
        if (CollectionUtils.isNotEmpty(entityList)) {
            resultList = entityList.stream().map(GiftCertificateConverter::toDto).collect(Collectors.toList());
        }
        return resultList;
    }

    public static GiftCertificateEntity toEntity(GiftCertificateDTO dto) {
        GiftCertificateEntity entity = new GiftCertificateEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setDateOfCreation(dto.getDateOfCreation());
        entity.setDateOfModification(dto.getDateOfModification());
        entity.setDuration(dto.getDuration());
        Set<GiftTagEntity> giftTagEntities = GiftTagConverter.toEntitySet(dto.getTags());
        entity.setTags(giftTagEntities);
        return entity;
    }

    public static List<GiftCertificateEntity> toEntityList(List<GiftCertificateDTO> dtoList) {
        List<GiftCertificateEntity> resultList = null;
        if (CollectionUtils.isNotEmpty(dtoList)) {
            resultList = dtoList.stream().map(GiftCertificateConverter::toEntity).collect(Collectors.toList());
        }
        return resultList;
    }


}
