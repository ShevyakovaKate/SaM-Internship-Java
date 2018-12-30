package com.samsolutions.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "\"ORDER\"")
public class OrderEntity implements Serializable {

    private static final long serialVersionUID = 4223092787993392757L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gc_id")
    private GiftCertificateEntity giftCertificate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pay_status_id")
    private OrderPayStatusEntity payStatus;

    @Column(name = "datetime")
    private LocalDateTime dateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GiftCertificateEntity getGiftCertificate() {
        return giftCertificate;
    }

    public void setGiftCertificate(GiftCertificateEntity giftCertificate) {
        this.giftCertificate = giftCertificate;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public OrderPayStatusEntity getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(OrderPayStatusEntity payStatus) {
        this.payStatus = payStatus;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
