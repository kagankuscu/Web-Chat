package com.Feelfree2code.STA.model.domain;

import com.Feelfree2code.STA.common.BaseDTO;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * ProjectPartDTO
 */
@Entity
@Table(name = "ProjectPart")
public class ProjectPartDTO extends BaseDTO {

    @NotNull
    @Min(1)
    private Integer amount;

    // F_KEY

    @NotNull
    @ManyToOne
    @JoinColumn(name="fk_project")
    private ProjectDTO projectId;

    @NotNull
    @ManyToOne
    @JoinColumn(name="fk_part")
    private PartDTO partId;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public ProjectDTO getProjectId() {
        return projectId;
    }

    public void setProjectId(ProjectDTO projectId) {
        this.projectId = projectId;
    }

    public PartDTO getPartId() {
        return partId;
    }

    public void setPartId(PartDTO partId) {
        this.partId = partId;
    }

    
}