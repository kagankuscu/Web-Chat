package com.Feelfree2code.STA.model.viewModel;

import com.Feelfree2code.STA.common.UpdateVM;
import com.Feelfree2code.STA.common.enums.PartTypeEnum;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * PartUpdateVM
 */
public class PartUpdateVM extends UpdateVM {

    @Column(length = 32, columnDefinition = "varchar(32) default 'NotDefined'")
    @Enumerated(value = EnumType.STRING)
    public PartTypeEnum partType;

    @NotNull
    @NotEmpty
    public String name;

    @NotNull
    @NotEmpty
    @Size(min = 5, max = 15)
    public String specs;

    @NotNull
    @Min(1)
    public int amount;
}