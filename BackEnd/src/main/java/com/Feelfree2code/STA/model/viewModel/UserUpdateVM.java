package com.Feelfree2code.STA.model.viewModel;

import com.Feelfree2code.STA.common.UpdateVM;

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
    @Size(min = 6, max = 25)
    @Email
    public String email;
    public boolean isAdmin;
    public boolean isDeleted;
}