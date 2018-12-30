package com.samsolutions.dao;

import com.samsolutions.entity.RoleEntity;

public interface RoleDao extends GenericDao<RoleEntity> {
    RoleEntity findByName(String name);
}
