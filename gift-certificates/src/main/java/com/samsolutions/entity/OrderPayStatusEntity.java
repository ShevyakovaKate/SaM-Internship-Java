package com.samsolutions.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PAY_STATUS")
public class OrderPayStatusEntity implements Serializable {

    private static final long serialVersionUID = 6860647991843559315L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status_name")
    private String name;

    @OneToMany(
            mappedBy = "payStatus",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<OrderEntity> orders = new ArrayList<>();

    public void addOrder(OrderEntity order) {
        orders.add(order);
        order.setPayStatus(this);
    }

    public void removeOrder(OrderEntity order) {
        orders.remove(order);
        order.setPayStatus(null);
    }

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

    public List<OrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderEntity> orders) {
        this.orders = orders;
    }
}
