package com.samsolutions.service;

import com.samsolutions.dto.SignUpInformationDTO;
import com.samsolutions.dto.UserDTO;
import com.samsolutions.error.exception.LoginExistsException;

import java.util.List;

public interface UserService {

    /**
     * Method is find the all users and convert them into DTO.
     * @return list of {@link UserDTO}.
     */
    List<UserDTO> getAll();

    /**
     * Method is find the user by id and convert it into DTO.
     * @param id identifier of user.
     * @return {@link UserDTO}
     */
    UserDTO getById(Long id);

    /**
     * Method is find the user by name and convert it into DTO.
     * @param name
     * @return {@link UserDTO}
     */
    UserDTO getByName(String name);

    /**
     * Method is add new user to data base and return it with id.
     * @param dto user will be added.
     * @return added LoginExistsException.
     * @throws LoginExistsException
     */
    UserDTO add(SignUpInformationDTO dto) throws LoginExistsException;

     /**
     * Method update the user with field from new dto,
     * and return the updated dto.
     * @param dto user with new fields.
     * @param id identifier of dto will be updated.
     * @return updated {@link UserDTO}.
     */
    UserDTO update(UserDTO dto, Long id);

    /**
     * Method is delete the role.
     * @param dto user will be deleted.
     */
    void delete(UserDTO dto);

    /**
     * Method is delete the role by id.
     * @param id identifier of user will bw deleted.
     */
    void deleteById(Long id);
}
