package com.samsolutions.service.impl;

import com.samsolutions.converter.GiftTagConverter;
import com.samsolutions.dao.GiftTagDao;
import com.samsolutions.dto.GiftTagDTO;
import com.samsolutions.entity.GiftTagEntity;
import com.samsolutions.service.GiftTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class GiftTagServiceImpl implements GiftTagService {

    private final GiftTagDao giftTagDao;

    @Autowired
    public GiftTagServiceImpl(GiftTagDao giftTagDao) {
        this.giftTagDao = giftTagDao;
    }

    @Override
    public List<GiftTagDTO> getAll() {
        List<GiftTagEntity> giftTagEntities = giftTagDao.findAll();
        Set<GiftTagEntity> giftTagEntitySet = new HashSet<>(giftTagEntities);
        Set<GiftTagDTO> giftTagDTOSet = GiftTagConverter.toDtoSet(giftTagEntitySet);
        return new ArrayList<>(giftTagDTOSet);
    }

    @Override
    public Set<GiftTagDTO> getAllById(Set<Long> tagsIdSet) {
        Set<GiftTagEntity> giftTagEntitySet =
                tagsIdSet.stream().map(giftTagDao::find).collect(Collectors.toSet());
        return GiftTagConverter.toDtoSet(giftTagEntitySet);
    }

    @Override
    public GiftTagDTO getById(Long id) {
        GiftTagEntity entity =  giftTagDao.find(id);
        return GiftTagConverter.toDto(entity);
    }

    @Override
    public GiftTagDTO add(GiftTagDTO dto) {
        GiftTagEntity entity = giftTagDao.save(GiftTagConverter.toEntity(dto));
        return GiftTagConverter.toDto(entity);
    }

    @Override
    public GiftTagDTO update(GiftTagDTO newDto, Long id) {
        GiftTagEntity newEntity = GiftTagConverter.toEntity(newDto);
        GiftTagEntity entity = giftTagDao.find(id);
        entity.setName(newEntity.getName());
        GiftTagEntity updetedEntity = giftTagDao.update(entity);
        return GiftTagConverter.toDto(updetedEntity);
    }

    @Override
    public void delete(GiftTagDTO dto) {
        GiftTagEntity entity = GiftTagConverter.toEntity(dto);
        giftTagDao.delete(entity);
    }

    @Override
    public void deleteById(Long id) {
        giftTagDao.deleteById(id);
    }
}
