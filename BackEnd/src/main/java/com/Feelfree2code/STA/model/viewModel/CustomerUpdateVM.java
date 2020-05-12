package com.Feelfree2code.STA.model.viewModel;

import com.Feelfree2code.STA.common.UpdateVM;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * CustomerUpdateVM
 */
public class CustomerUpdateVM extends UpdateVM {

    @NotNull
    @NotEmpty
    @Size(min=4, max=15)
    public String firstName;

    @NotNull
    @NotEmpty
    @Size(min=4, max=15)
    public String lastName;

    @NotNull
    @NotEmpty
    public String phoneNumber;

    @Email
    @NotNull
    @NotEmpty
    public String email;
}