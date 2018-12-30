package com.samsolutions.service;

import com.samsolutions.dto.RoleDTO;

import java.util.List;

public interface RoleService {
    /**
     * Method is find the all roles and convert them into DTO.
     * @return list of {@link RoleDTO}.
     */
    List<RoleDTO> getAll();

    /**
     * Method is find the role by id and convert it into DTO.
     * @param id identifier of role.
     * @return {@link RoleDTO}.
     */
    RoleDTO getById(Long id);

    /**
     * Method is find the role by name and convert it into DTO.
     * @param name role name.
     * @return {@link RoleDTO}
     */
    RoleDTO getByName(String name);

    /**
     * Method is add new role dto to data base and return it with id.
     * @param dto new role.
     * @return {@link RoleDTO}
     */
    RoleDTO add(RoleDTO dto);

    /**
     * Method update the role with field from new dto,
     * and return the updated dto.
     * @param dto role with new fields.
     * @param id identifier of role will be updated.
     * @return updated {@link RoleDTO}.
     */
    RoleDTO update(RoleDTO dto, Long id);

    /**
     * Method is delete the role.
     * @param dto role will be deleted.
     */
    void delete(RoleDTO dto);

    /**
     * Method is delete the role by id.
     * @param id identifier of role will be deleted.
     */
    void deleteById(Long id);
}
