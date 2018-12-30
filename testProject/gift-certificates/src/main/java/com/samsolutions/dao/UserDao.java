package com.samsolutions.dao;

import com.samsolutions.entity.UserEntity;

public interface UserDao extends GenericDao<UserEntity> {
    UserEntity findByName(String name);
}
