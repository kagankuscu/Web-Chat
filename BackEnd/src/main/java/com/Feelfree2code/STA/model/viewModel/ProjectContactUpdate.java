package com.Feelfree2code.STA.model.viewModel;

import com.Feelfree2code.STA.common.UpdateVM;

import javax.validation.constraints.NotNull;

/**
 * ProjectContactUpdate
 */
public class ProjectContactUpdate extends UpdateVM {

    @NotNull
    public int projectId;

    @NotNull
    public int customerId;

    @NotNull
    public int priortyIndex;
}