package com.Feelfree2code.STA.model.domain;

import com.Feelfree2code.STA.common.BaseDTO;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * ProjectContactDTO
 */
@Entity
@Table(name = "ProjectContact")
public class ProjectContactDTO extends BaseDTO {

    @NotNull
    private Integer priortyIndex;

    // F_KEYS
    @NotNull
    @ManyToOne
    @JoinColumn(name = "fk_project")
    private ProjectDTO projectId;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "fk_customer")
    private CustomerDTO customerId;

    public Integer getPriortyIndex() {
        return priortyIndex;
    }

    public void setPriortyIndex(Integer priortyIndex) {
        this.priortyIndex = priortyIndex;
    }

    public ProjectDTO getProjectId() {
        return projectId;
    }

    public void setProjectId(ProjectDTO projectId) {
        this.projectId = projectId;
    }

    public CustomerDTO getCustomerId() {
        return customerId;
    }

    public void setCustomerId(CustomerDTO customerId) {
        this.customerId = customerId;
    }




}