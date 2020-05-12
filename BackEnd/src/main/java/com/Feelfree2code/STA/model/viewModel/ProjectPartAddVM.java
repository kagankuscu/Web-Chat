package com.Feelfree2code.STA.model.viewModel;

import com.Feelfree2code.STA.common.AddVM;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * ProjectPartAddVM
 */
public class ProjectPartAddVM extends AddVM {

    @NotNull
    public int partId;

    @NotNull
    public int projectId;

    @NotNull
    @Min(1)
    public int amount;
}