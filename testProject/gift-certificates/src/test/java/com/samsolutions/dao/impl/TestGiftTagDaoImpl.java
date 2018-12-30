package com.samsolutions.dao.impl;

import com.samsolutions.dao.GiftTagDao;
import com.samsolutions.dao.TestDatabaseConfig;
import com.samsolutions.entity.GiftTagEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@WebAppConfiguration
@ContextConfiguration(loader = AnnotationConfigWebContextLoader.class, classes = {TestDatabaseConfig.class})
@Transactional()
@RunWith(SpringJUnit4ClassRunner.class)
public class TestGiftTagDaoImpl {

    @Autowired
    private GiftTagDao giftTagDao;

    private GiftTagEntity testGiftTag;

    @BeforeTransaction
    public void setupData() {
        testGiftTag = new GiftTagEntity();
        testGiftTag.setName("Test tag");
    }

    @Test
    @Rollback(true)
    public void testSave() {
        GiftTagEntity saved = giftTagDao.save(testGiftTag);
        Assert.assertEquals(testGiftTag.getName(), saved.getName());
    }


    @Test
    @Rollback(true)
    public void testUpdate() {
        GiftTagEntity saved = giftTagDao.save(testGiftTag);
        testGiftTag.setName("new test name");
        GiftTagEntity updated = giftTagDao.update(testGiftTag);
        Assert.assertEquals(testGiftTag.getName(), updated.getName());
    }

    @Test
    @Rollback(true)
    public void testDelete() {
        GiftTagEntity saved = giftTagDao.save(testGiftTag);
        giftTagDao.deleteById(saved.getId());
        Assert.assertNull(giftTagDao.find(saved.getId()));
    }
}
