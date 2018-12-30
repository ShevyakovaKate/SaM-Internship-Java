package com.samsolutions.service.impl;

import com.samsolutions.converter.RoleConverter;
import com.samsolutions.dao.RoleDao;
import com.samsolutions.dto.RoleDTO;
import com.samsolutions.entity.RoleEntity;
import com.samsolutions.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private RoleDao roleDao;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<RoleDTO> getAll() {
        List<RoleEntity> all = roleDao.findAll();
        return RoleConverter.toDtoList(all);
    }

    @Override
    public RoleDTO getById(Long id) {
        RoleEntity roleEntity = roleDao.find(id);
        return RoleConverter.toDto(roleEntity);
    }

    @Override
    public RoleDTO getByName(String name) {
        RoleEntity roleEntity = roleDao.findByName(name);
        return RoleConverter.toDto(roleEntity);
    }

    @Override
    public RoleDTO add(RoleDTO dto) {
        RoleEntity roleEntity = RoleConverter.toEntity(dto);
        RoleEntity savedEntity = roleDao.save(roleEntity);
        return RoleConverter.toDto(savedEntity);
    }

    @Override
    public RoleDTO update(RoleDTO dto, Long id) {
        RoleEntity roleEntityFromDB = roleDao.find(id);
        roleEntityFromDB.setName(dto.getName());
        RoleEntity updatedEntity = roleDao.update(roleEntityFromDB);
        return RoleConverter.toDto(updatedEntity);
    }

    @Override
    public void delete(RoleDTO dto) {
        RoleEntity roleEntity = RoleConverter.toEntity(dto);
        roleDao.delete(roleEntity);
    }

    @Override
    public void deleteById(Long id) {
        roleDao.deleteById(id);
    }
}
