package com.Feelfree2code.STA.model.viewModel;

import com.Feelfree2code.STA.common.AddVM;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * PartBuyHistoryAddVM
 */
public class PartBuyHistoryAddVM extends AddVM {

    @NotNull
    public int partId;

    @NotNull
    @Temporal(TemporalType.DATE)
    public Date dateTime;

    @NotNull
    @Min(1)
    public int amount;

    @NotNull
    @Min(1)
    public double price;
}