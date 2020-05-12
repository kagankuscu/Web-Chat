package com.Feelfree2code.STA.model.viewModel;

import com.Feelfree2code.STA.common.AddVM;

import javax.validation.constraints.NotNull;

/**
 * ProjectContactAddVM
 */
public class ProjectContactAddVM extends AddVM {

    @NotNull
    public int projectId;

    @NotNull
    public int customerId;

    @NotNull
    public int priortyIndex;
}