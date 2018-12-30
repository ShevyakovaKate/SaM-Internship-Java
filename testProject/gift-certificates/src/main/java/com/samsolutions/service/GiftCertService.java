package com.samsolutions.service;

import com.samsolutions.dto.GiftCertificateDTO;

import java.util.List;

public interface GiftCertService {
    /**
     * Method is find the all certificates and convert them into DTO.
     * @return list of {@link GiftCertificateDTO}
     */
    List<GiftCertificateDTO> getAll();

    /**
     * Method is find the certificates by one of text or tag name or both.
     * @param textPart part of certificate name or description.
     * @param tagName name of tag.
     * @return list of {@link GiftCertificateDTO}
     */
    List<GiftCertificateDTO> getByParameters(String textPart, String tagName);

    /**
     * Method is find the certificate by id and convert it into DTO.
     * @param id identifier of certificate
     * @return {@link GiftCertificateDTO}
     */
    GiftCertificateDTO getById(Long id);

    /**
     * Method is fill all fields of new certificate dto,
     * add them to data base, return new added certificate.
     * @param dto certificate to be added.
     * @return {@link GiftCertificateDTO}
     */
    GiftCertificateDTO add(GiftCertificateDTO dto);

    /**
     * Method update the certificate with field from new dto,
     * and return the updated dto.
     * @param dto contains new fields.
     * @param id identifier of certificate will be updated.
     * @return updated {@link GiftCertificateDTO}
     */
    GiftCertificateDTO update(GiftCertificateDTO dto, Long id);

    /**
     * Method is delete the certificate.
     * @param dto certificate will be deleted.
     */
    void delete(GiftCertificateDTO dto);

    /**
     * Method is delete the certificate by id number.
     * @param id  certificate will be deleted.
     */
    void deleteById(Long id);
}
