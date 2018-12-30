package com.samsolutions.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

public class OrderDTO implements Serializable {
    private static final long serialVersionUID = -8451099914335044181L;
    private Long id;
    private GiftCertificateDTO giftCertificate;
    private UserDTO user;
    private OrderPayStatusDTO payStatusDTO;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GiftCertificateDTO getGiftCertificate() {
        return giftCertificate;
    }

    public void setGiftCertificate(GiftCertificateDTO giftCertificate) {
        this.giftCertificate = giftCertificate;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public OrderPayStatusDTO getPayStatus() {
        return payStatusDTO;
    }

    public void setPayStatus(OrderPayStatusDTO payStatusDTO) {
        this.payStatusDTO = payStatusDTO;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
