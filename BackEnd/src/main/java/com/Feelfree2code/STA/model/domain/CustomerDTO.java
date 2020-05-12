package com.Feelfree2code.STA.model.domain;

import com.Feelfree2code.STA.common.BaseDTO;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * CustomerDTO
 */
@Entity
@Table(name = "Customer")
public class CustomerDTO extends BaseDTO {
    @NotEmpty
    @NotNull
    @Size(min=4, max=15)
    private String firstName;

    @NotEmpty
    @NotNull
    @Size(min=4, max=15)
    private String lastName;

    @NotEmpty
    @NotNull
    private String phoneNumber;

    @Email
    @NotNull
    @NotEmpty
    private String email;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}