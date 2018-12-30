package com.samsolutions.dao.impl;

import com.samsolutions.dao.GiftCertificateDao;
import com.samsolutions.dao.TestDatabaseConfig;
import com.samsolutions.entity.GiftCertificateEntity;
import com.samsolutions.entity.GiftTagEntity;
import org.apache.commons.collections4.CollectionUtils;
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

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebAppConfiguration
@ContextConfiguration(loader = AnnotationConfigWebContextLoader.class, classes = {TestDatabaseConfig.class})
@Transactional()
@RunWith(SpringJUnit4ClassRunner.class)
public class TestGiftCertificateDaoImpl {

    @Autowired
    private GiftCertificateDao giftCertificateDao;

    private GiftCertificateEntity testGiftCertificate;

    @BeforeTransaction
    public void setupData() {
        testGiftCertificate = new GiftCertificateEntity();
        testGiftCertificate.setName("Certificate One");
        testGiftCertificate.setPrice(100.00);
        testGiftCertificate.setDescription("Description of certificate one");
        testGiftCertificate.setDuration(60);
        testGiftCertificate.setDateOfCreation(LocalDate.now());
        testGiftCertificate.setDateOfModification(LocalDate.now());

        Set<GiftTagEntity> tags = new HashSet<>();
        GiftTagEntity giftTagOne = new GiftTagEntity();
        giftTagOne.setName("Tag 1");
        GiftTagEntity giftTagTwo = new GiftTagEntity();
        giftTagTwo.setName("Tag 2");
        tags.add(giftTagOne);
        tags.add(giftTagTwo);

        testGiftCertificate.setTags(tags);
    }

    @Test
    @Rollback
    public void testSave() {
        GiftCertificateEntity saved = giftCertificateDao.save(testGiftCertificate);
        Assert.assertEquals(testGiftCertificate.getName(), saved.getName());
        Assert.assertEquals(testGiftCertificate.getPrice(), saved.getPrice());
        Assert.assertEquals(testGiftCertificate.getDescription(), saved.getDescription());
        Assert.assertEquals(testGiftCertificate.getDuration(), saved.getDuration());
        Assert.assertEquals(testGiftCertificate.getDateOfCreation(), saved.getDateOfCreation());
        Assert.assertEquals(testGiftCertificate.getDateOfModification(), saved.getDateOfModification());
    }

    @Test
    @Rollback
    public void testGiftCertificateTags() {
        GiftCertificateEntity saved = giftCertificateDao.save(testGiftCertificate);
        Set<GiftTagEntity> savedGiftTags = saved.getTags();
        Set<String> savedGiftTagsNames = new HashSet<>();
        for (GiftTagEntity tag : savedGiftTags) {
            savedGiftTagsNames.add(tag.getName());
        }
        List<String> expectedValues = Arrays.asList("Tag 1", "Tag 2");
        Set<String> expectedNames = new HashSet<>(expectedValues);
        Assert.assertTrue(CollectionUtils.isEqualCollection(savedGiftTagsNames, expectedNames));
    }


    @Test
    @Rollback
    public void testUpdate() {
        GiftCertificateEntity saved = giftCertificateDao.save(testGiftCertificate);
        LocalDate dateOfModification = LocalDate.now();

        testGiftCertificate.setDateOfModification(dateOfModification);
        GiftCertificateEntity updated = giftCertificateDao.update(testGiftCertificate);
        List<GiftCertificateEntity> all = giftCertificateDao.findAll();
        Assert.assertEquals(testGiftCertificate.getName(), updated.getName());
        Assert.assertEquals(testGiftCertificate.getPrice(), updated.getPrice());
        Assert.assertEquals(testGiftCertificate.getDescription(), updated.getDescription());
        Assert.assertEquals(testGiftCertificate.getDuration(), updated.getDuration());
        Assert.assertEquals(testGiftCertificate.getDateOfCreation(), updated.getDateOfCreation());
        Assert.assertEquals(testGiftCertificate.getDateOfModification(), updated.getDateOfModification());
        giftCertificateDao.delete(testGiftCertificate);
    }

    @Test
    @Rollback
    public void testDelete() {
        GiftCertificateEntity saved = giftCertificateDao.save(testGiftCertificate);
        giftCertificateDao.deleteById(saved.getId());
        Assert.assertNull(giftCertificateDao.find(saved.getId()));
    }


}
