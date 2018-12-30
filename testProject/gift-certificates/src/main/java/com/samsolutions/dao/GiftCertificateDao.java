package com.samsolutions.dao;

import com.samsolutions.entity.GiftCertificateEntity;

import java.util.List;

public interface GiftCertificateDao extends GenericDao<GiftCertificateEntity> {

    List<GiftCertificateEntity> findByParameters(String textPart, String tagName);
}
