package com.Feelfree2code.STA.model.viewModel;

import com.Feelfree2code.STA.common.UpdateVM;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * ProjectPartUpdateVM
 */
public class ProjectPartUpdateVM extends UpdateVM {
    @NotNull
    public int partId;

    @NotNull
    public int projectId;

    @NotNull
    @Min(1)
    public int amount;
}