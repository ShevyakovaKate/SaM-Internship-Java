package com.samsolutions.service;

import com.samsolutions.dto.GiftTagDTO;

import java.util.List;
import java.util.Set;

public interface GiftTagService {

    /**
     * Method is find the all tag and convert them into DTO.
     * @return list of all {@link GiftTagDTO}
     */
    List<GiftTagDTO> getAll();

    /**
     * Method is find the all tag with id's from stagsIdSet and convert them into DTO.
     * @param tagsIdSet set of tags identifiers.
     * @return set of {@link GiftTagDTO}.
     */
    Set<GiftTagDTO> getAllById(Set<Long> tagsIdSet);

    /**
     * Method is find the tag by id and convert it into DTO.
     * @param id of tag.
     * @return  {@link GiftTagDTO}.
     */
    GiftTagDTO getById(Long id);

    /**
     * Method is add tag dto to data base, and return the added DTO.
     * @param dto tag will be added
     * @return added  {@link GiftTagDTO}
     */
    GiftTagDTO add(GiftTagDTO dto);

    /**
     * Method update the tag with field from new dto,
     * and return the updated dto.
     * @param dto contains new fields.
     * @param id identifier of tag will be updated.
     * @return updated  {@link GiftTagDTO}.
     */
    GiftTagDTO update(GiftTagDTO dto, Long id);

    /**
     * Method is delete the tag.
     * @param dto tag will be deleted.
     */
    void delete(GiftTagDTO dto);

    /**
     * Method is delete the tag by id number.
     * @param id identifier of tag will be deleted.
     */
    void deleteById(Long id);
}
