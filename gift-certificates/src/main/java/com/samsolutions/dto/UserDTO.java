package com.samsolutions.dto;


import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 7243232123241692111L;

    private Long id;

    @NotNull
    @Size(min = 2, max = 80, message = "message.error.user.login")
    private String login;

    @NotNull
    @Size(min = 4, max = 256, message = "message.error.user.email")
    private String email;

    @NotNull
    @Size( message = "message.error.user.password")
    private String password;

    private RoleDTO role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleDTO getRole() {
        return role;
    }

    public void setRole(RoleDTO role) {
        this.role = role;
    }
}
