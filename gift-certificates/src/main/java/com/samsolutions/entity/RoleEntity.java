package com.samsolutions.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "role")
public class RoleEntity implements Serializable {

    private static final long serialVersionUID = -2624763960384047421L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(
            mappedBy = "role",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<UserEntity> users = new ArrayList<>();

    public void addUser(UserEntity user) {
        users.add(user);
        user.setRole(this);
    }

    public void removeUser(UserEntity user) {
        users.remove(user);
        user.setRole(null);
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

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }
}
