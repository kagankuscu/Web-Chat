package com.Feelfree2code.STA.model.domain;

import com.Feelfree2code.STA.common.BaseDTO;
import com.Feelfree2code.STA.common.enums.PartTypeEnum;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * PartDTO
 */
@Entity
@Table(name = "Part")
public class PartDTO extends BaseDTO {

    @Column(length = 32, columnDefinition = "varchar(32) default 'NotDefined'")
    @Enumerated(value = EnumType.STRING)
    private PartTypeEnum partType = PartTypeEnum.NotDefined;

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    @Size(min = 5, max = 15)
    private String specs;

    @NotNull
    @Min(1)
    private Integer amount;

    public PartTypeEnum getPartType() {
        return partType;
    }

    public void setPartType(PartTypeEnum partType) {
        this.partType = partType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecs() {
        return specs;
    }

    public void setSpecs(String specs) {
        this.specs = specs;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

}