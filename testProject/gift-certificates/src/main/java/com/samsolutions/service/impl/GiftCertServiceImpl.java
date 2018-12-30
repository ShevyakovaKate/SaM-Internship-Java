package com.samsolutions.service.impl;

import com.samsolutions.converter.GiftCertificateConverter;
import com.samsolutions.converter.GiftTagConverter;
import com.samsolutions.dao.GiftCertificateDao;
import com.samsolutions.dao.GiftTagDao;
import com.samsolutions.dto.GiftCertificateDTO;
import com.samsolutions.dto.GiftTagDTO;
import com.samsolutions.entity.GiftCertificateEntity;
import com.samsolutions.entity.GiftTagEntity;
import com.samsolutions.service.GiftCertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class GiftCertServiceImpl implements GiftCertService {

    private final GiftCertificateDao giftCertificateDao;
    private final GiftTagDao giftTagDao;

    @Autowired
    public GiftCertServiceImpl(GiftCertificateDao giftCertificateDao, GiftTagDao giftTagDao) {
        this.giftCertificateDao = giftCertificateDao;
        this.giftTagDao = giftTagDao;
    }

    @Override
    public List<GiftCertificateDTO> getByParameters(String textPart, String tagName) {
        List<GiftCertificateEntity> entityList = giftCertificateDao.findByParameters(textPart, tagName);
        return GiftCertificateConverter.toDtoList(entityList);
    }

    @Override
    public List<GiftCertificateDTO> getAll() {
        List<GiftCertificateEntity> entityList = giftCertificateDao.findAll();
        return GiftCertificateConverter.toDtoList(entityList);
    }

    @Override
    public GiftCertificateDTO getById(Long id) {
        GiftCertificateEntity entity = giftCertificateDao.find(id);
        GiftCertificateDTO dto = GiftCertificateConverter.toDto(entity);
        return dto;
    }

    @Override
    public GiftCertificateDTO add(GiftCertificateDTO dto) {
        Set<GiftTagDTO> tags = dto.getTags();
        List<Long> tagsIds = tags.stream().map(GiftTagDTO::getId).collect(Collectors.toList());
//        Set<GiftTagEntity> tagsEntitiesSet  = dao.findAllByIds(tagsIds);

        Set<GiftTagEntity> tagsEntitiesSet = tags.stream().map(tag -> { //rewrite later
            Long tagId = tag.getId();
            GiftTagEntity entity;
            if (tagId == null) {
                entity = giftTagDao.save(GiftTagConverter.toEntity(tag));
            } else {
                entity = giftTagDao.find(tagId);
                if (entity == null) {
                    tag.setId(null);
                    entity = giftTagDao.save(GiftTagConverter.toEntity(tag));
                }
            }
            return entity;
        }).collect(Collectors.toSet());

        GiftCertificateEntity giftCertificateEntity = GiftCertificateConverter.toEntity(dto);
        giftCertificateEntity.setDateOfCreation(LocalDate.now());
        giftCertificateEntity.setDateOfModification(LocalDate.now());

        giftCertificateEntity.setTags(tagsEntitiesSet);
        GiftCertificateEntity entity = giftCertificateDao.save(giftCertificateEntity);

        return GiftCertificateConverter.toDto(entity);
    }

    @Override
    public GiftCertificateDTO update(GiftCertificateDTO newDto, Long id) {
        Set<GiftTagDTO> newDtoTags = newDto.getTags();

        List<Long> tagsIds = newDtoTags.stream().map(GiftTagDTO::getId).collect(Collectors.toList());
//        Set<GiftTagEntity> tagsEntitiesSet  = dao.findAllByIds(tagsIds);

        Set<GiftTagEntity> tagsEntitiesSet = newDtoTags.stream().map(tag -> { //rewrite later
            Long tagId = tag.getId();
            GiftTagEntity entity;
            if (tagId == null) {
                entity = giftTagDao.save(GiftTagConverter.toEntity(tag));
            } else {
                entity = giftTagDao.find(tagId);
                if (entity == null) {
                    tag.setId(null);
                    entity = giftTagDao.save(GiftTagConverter.toEntity(tag));
                }
            }
            return entity;
        }).collect(Collectors.toSet());

        GiftCertificateEntity newEntity = GiftCertificateConverter.toEntity(newDto);
        GiftCertificateEntity entity = giftCertificateDao.find(id);
        entity.setName(newEntity.getName());
        entity.setPrice(newEntity.getPrice());
        entity.setDescription(newEntity.getDescription());
        entity.setDateOfModification(LocalDate.now());
        entity.setDuration(newEntity.getDuration());

        entity.setTags(tagsEntitiesSet);
        GiftCertificateEntity resultEntity = giftCertificateDao.update(entity);
        return GiftCertificateConverter.toDto(resultEntity);
    }

    @Override
    public void delete(GiftCertificateDTO dto) {
        GiftCertificateEntity entity = GiftCertificateConverter.toEntity(dto);
        giftCertificateDao.delete(entity);
    }

    @Override
    public void deleteById(Long id) {
        giftCertificateDao.deleteById(id);
    }
}
