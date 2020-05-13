package com.kagankuscu.webchat.model.domain;

import com.kagankuscu.webchat.common.BaseDTO;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * UserDTO
 */
@Entity
@Table(name="UserTable")
public class UserDTO extends BaseDTO {
    @NotNull
    @NotEmpty
    @Size(min = 3, max = 11)
    private String username;

    @NotNull
    @NotEmpty
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}