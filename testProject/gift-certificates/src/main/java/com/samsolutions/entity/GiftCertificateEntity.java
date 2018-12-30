package com.samsolutions.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "gift_certificate")
public class GiftCertificateEntity implements Serializable {

    private static final long serialVersionUID = -6994834619650649487L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "gc_name")
    private String name;

    @Column(name = "gc_description")
    private String description;

    @Column(name = "gc_price")
    private Double price;

    @Column(name = "gc_date_of_creation")
    private LocalDate dateOfCreation;

    @Column(name = "gc_date_of_modification")
    private LocalDate dateOfModification;

    @Column(name = "gc_duration")
    private Integer duration;

    @OneToMany(
            mappedBy = "giftCertificate",
            cascade = CascadeType.REFRESH,
            orphanRemoval = true, //when removing an item from the collection, the item in the database will be deleted
            fetch = FetchType.EAGER
    )
    private List<OrderEntity> orders = new ArrayList<>();

    @ManyToMany(cascade = {
            CascadeType.REFRESH
    }, fetch = FetchType.EAGER)
    @JoinTable(name = "gift_certificate_has_tag",
            joinColumns = @JoinColumn(name = "gc_id"),
            inverseJoinColumns = @JoinColumn(name = "gt_id")
    )
    private Set<GiftTagEntity> tags = new HashSet<>();

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDate getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(LocalDate dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public LocalDate getDateOfModification() {
        return dateOfModification;
    }

    public void setDateOfModification(LocalDate dateOfModification) {
        this.dateOfModification = dateOfModification;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Set<GiftTagEntity> getTags() {
        return new HashSet<>(tags);
    }

    public void setTags(Set<GiftTagEntity> tags) {
        this.tags = tags;
    }

    public List<OrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderEntity> orders) {
        this.orders = orders;
    }

    public void addTag(GiftTagEntity tag) {
        tags.add(tag);
        tag.getGiftCertificates().add(this);
    }

    public void removeTag(GiftTagEntity tag) {
        tags.remove(tag);
        tag.getGiftCertificates().remove(this);
    }

    public void addOrder(OrderEntity order) {
        orders.add(order);
        order.setGiftCertificate(this);
    }

    public void removeComment(OrderEntity order) {
        orders.remove(order);
        order.setGiftCertificate(null);
    }


}
