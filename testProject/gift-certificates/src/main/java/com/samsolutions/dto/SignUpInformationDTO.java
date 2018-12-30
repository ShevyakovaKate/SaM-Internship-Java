package com.samsolutions.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class SignUpInformationDTO implements Serializable {
    //TODO:встаить id

    @NotNull
    @Size(min = 2, max = 80, message = "message.error.user.login")
    private String login;

    /**
     *  A-Z characters allowed
     *  a-z characters allowed
     *  0-9 numbers allowed
     *  both the local part and the domain name can contain one or more dots
     *  two dots not can appear right next to each other.
     *  the first and last characters in the local part and in the domain name must not be
     *  domain name must include at least one dot
     *  the part of the domain name after the last dot can only consist of letters
     * the top-level domain must consist of two to six letters only
     */
    @NotNull
    @Pattern(regexp = "\\w*?@\\w*?\\.\\w{1,6}" )
    @Size(min = 4, max = 256, message = "message.error.user.email")
    private String email;

    /**
     *
     * digit must occur at least once
     * lower case letter must occur at least once
     * an upper case letter must occur at least once
     * no whitespace allowed in the entire string
     * anything, at least eight places though
     */
    @NotNull
    @Size(min = 8, max = 20)
    @Pattern(regexp = "(^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,20}$)", message = "message.error.user.password")
    private String password;

    @NotNull
    @Size(min = 8, max = 20)
    private String matchingPassword;

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

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }
}
