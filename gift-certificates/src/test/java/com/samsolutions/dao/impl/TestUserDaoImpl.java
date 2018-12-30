package com.samsolutions.dao.impl;

import com.samsolutions.dao.RoleDao;
import com.samsolutions.dao.TestDatabaseConfig;
import com.samsolutions.dao.UserDao;
import com.samsolutions.entity.RoleEntity;
import com.samsolutions.entity.UserEntity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@WebAppConfiguration
@ContextConfiguration(loader = AnnotationConfigWebContextLoader.class, classes = {TestDatabaseConfig.class})
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
public class TestUserDaoImpl {

    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;

    private UserEntity testUser;
    private UserEntity savedUser;



    @Before
    public void setupData() {
        testUser = new UserEntity();
        testUser.setLogin("Test user");
        testUser.setEmail("User email");
        testUser.setPassword("Test password");
        RoleEntity testRole = new RoleEntity();
        testRole.setName("Test role");
        roleDao.save(testRole);
        testUser.setRole(testRole);
        savedUser = userDao.save(testUser);
    }

    @Test
    @Rollback(true)
    public void testSave() {

        Assert.assertEquals(testUser.getLogin(), savedUser.getLogin());
        Assert.assertEquals(testUser.getPassword(), savedUser.getPassword());
        String savedUserRoleName = savedUser.getRole().getName();
        String roleName = testUser.getRole().getName();
        Assert.assertEquals(savedUserRoleName, roleName);
    }
    
    @Test
    @Rollback(true)
    @Transactional
    public void testUpdate() {
        testUser.setLogin("new test name");
        UserEntity updated = userDao.update(testUser);
        Assert.assertEquals(testUser.getLogin(), updated.getLogin());
    }

    @Test
    @Rollback(true)
    @Transactional
    public void testDelete() {
        userDao.deleteById(testUser.getId());
        Assert.assertNull(userDao.find(savedUser.getId()));
    }    
    
}
