package com.samsolutions.entity;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "gift_tag")
public class GiftTagEntity implements Serializable {

    private static final long serialVersionUID = 3696950402550199005L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "gt_name")
    private String name;

    @ManyToMany(cascade = {
            CascadeType.REFRESH,
    }, mappedBy = "tags", fetch = FetchType.LAZY)
    @Cascade(value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    private Set<GiftCertificateEntity> giftCertificates = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<GiftCertificateEntity> getGiftCertificates() {
        return giftCertificates;
    }

    public void setGiftCertificates(Set<GiftCertificateEntity> giftCertificates) {
        this.giftCertificates = giftCertificates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GiftTagEntity giftTag = (GiftTagEntity) o;

        if (id != null ? !id.equals(giftTag.id) : giftTag.id != null) return false;
        if (name != null ? !name.equals(giftTag.name) : giftTag.name != null) return false;
        return giftCertificates != null ? giftCertificates.equals(giftTag.giftCertificates) : giftTag.giftCertificates == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (giftCertificates != null ? giftCertificates.hashCode() : 0);
        return result;
    }
}
