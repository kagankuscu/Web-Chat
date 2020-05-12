package com.kagankuscu.webchat.model.viewModel;

import com.kagankuscu.webchat.common.UpdateVM;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * UserUpdateVM
 */
public class UserUpdateVM extends UpdateVM {
    @NotEmpty
    @Size(min = 4, max = 15)
    public String userName;

    @NotEmpty
    public String password;
}