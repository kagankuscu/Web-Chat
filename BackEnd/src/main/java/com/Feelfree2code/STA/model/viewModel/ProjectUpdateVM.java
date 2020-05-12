package com.Feelfree2code.STA.model.viewModel;

import com.Feelfree2code.STA.common.UpdateVM;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * ProjectUpdateVM
 */
public class ProjectUpdateVM extends UpdateVM {

    @NotNull
    @Temporal(TemporalType.DATE)
    public Date startDate;

    @Future
    @Temporal(TemporalType.DATE)
    public Date endDate;

    @NotEmpty
    @NotNull
    @Size(min = 1, max = 10)
    public String title;

    @NotEmpty
    @NotNull
    @Size(min = 10, max = 200)
    public String address;
}