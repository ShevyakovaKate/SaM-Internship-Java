package com.samsolutions.service.impl;

import com.samsolutions.converter.RoleConverter;
import com.samsolutions.converter.UserConverter;
import com.samsolutions.dao.UserDao;
import com.samsolutions.dto.RoleDTO;
import com.samsolutions.dto.SignUpInformationDTO;
import com.samsolutions.dto.UserDTO;
import com.samsolutions.entity.RoleEntity;
import com.samsolutions.entity.UserEntity;
import com.samsolutions.error.exception.LoginExistsException;
import com.samsolutions.service.RoleService;
import com.samsolutions.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final String DEFAULT_USER_ROLE = "USER";

    private UserDao userDao;
    private RoleService roleService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserDao userDao, RoleService roleService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDao = userDao;
        this.roleService = roleService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public List<UserDTO> getAll() {
        List<UserEntity> all = userDao.findAll();
        return UserConverter.toDtoList(all);
    }

    @Override
    public UserDTO getById(Long id) {
        UserEntity userEntity = userDao.find(id);
        return UserConverter.toDto(userEntity);
    }

    @Override
    public UserDTO getByName(String name) {
        UserEntity userEntity = userDao.findByName(name);

        return UserConverter.toDto(userEntity);
    }

    @Override
    public UserDTO add(SignUpInformationDTO informationDTO) throws LoginExistsException {
        //TODO:переести в DTO
        if (loginExist(informationDTO.getLogin())) {
            throw new LoginExistsException();
        }
        informationDTO.setPassword(bCryptPasswordEncoder.encode(informationDTO.getPassword()));
        UserEntity userEntity = UserConverter.toEntity(informationDTO);
        RoleDTO roleDTO = roleService.getByName(DEFAULT_USER_ROLE);
        userEntity.setRole(RoleConverter.toEntity(roleDTO));
        UserEntity savedEntity = userDao.save(userEntity);
        return UserConverter.toDto(savedEntity);
    }

    @Override
    public UserDTO update(UserDTO dto, Long id) {
        UserEntity entity = UserConverter.toEntity(dto);
        UserEntity userEntityFromDB = userDao.find(id);
        userEntityFromDB.setLogin(entity.getLogin());
        userEntityFromDB.setEmail(entity.getEmail());
        String hashedPassword = bCryptPasswordEncoder.encode(entity.getPassword());
        userEntityFromDB.setPassword(hashedPassword);
        RoleDTO defaultUserRoleDTO = roleService.getByName(DEFAULT_USER_ROLE);
        RoleEntity defaultUserRoleEntity = RoleConverter.toEntity(defaultUserRoleDTO);
        userEntityFromDB.setRole(defaultUserRoleEntity);
        UserEntity updatedEntity = userDao.update(userEntityFromDB);
        return UserConverter.toDto(updatedEntity);
    }

    @Override
    public void delete(UserDTO dto) {
        UserEntity entity = UserConverter.toEntity(dto);
        userDao.delete(entity);
    }

    @Override
    public void deleteById(Long id) {
        userDao.deleteById(id);
    }

    private boolean loginExist(String login) {
        UserDTO userDTO = getByName(login);
        if (userDTO != null) {
            return true;
        }
        return false;
    }
}
