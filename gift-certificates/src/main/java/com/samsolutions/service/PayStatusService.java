package com.samsolutions.service;

import com.samsolutions.dto.OrderPayStatusDTO;

import java.util.List;

public interface PayStatusService {
    /**
     * Method is find the all pay statuses and convert them into DTO.
     * @return list of {@link OrderPayStatusDTO}.
     */
    List<OrderPayStatusDTO> getAll();

    /**
     * Method is find the pay status by id and convert it into DTO.
     * @param id identifier of role.
     * @return {@link OrderPayStatusDTO}.
     */
    OrderPayStatusDTO getById(Long id);



    /**
     * Method is find the pay status by name and convert it into DTO.
     * @param name - pay status name.
     * @return {@link OrderPayStatusDTO}
     */
    OrderPayStatusDTO getByName(String name);

    /**
     * Method is add new pay status dto to data base and return it with id.
     * @param dto new pay status.
     * @return {@link OrderPayStatusDTO}
     */
    OrderPayStatusDTO add(OrderPayStatusDTO dto);

    /**
     * Method update the pay status with field from new dto,
     * and return the updated dto.
     * @param dto pay status with new fields.
     * @param id identifier of pay status will be updated.
     * @return updated {@link OrderPayStatusDTO}.
     */
    OrderPayStatusDTO update(OrderPayStatusDTO dto, Long id);

    /**
     * Method is delete the pay status.
     * @param dto pay status will be deleted.
     */
    void delete(OrderPayStatusDTO dto);

    /**
     * Method is delete the pay status by id.
     * @param id identifier of pay status will be deleted.
     */
    void deleteById(Long id);
}
