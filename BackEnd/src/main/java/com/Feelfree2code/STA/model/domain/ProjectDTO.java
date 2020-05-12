package com.Feelfree2code.STA.model.domain;

import com.Feelfree2code.STA.common.BaseDTO;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * ProjectDTO
 */
@Entity
@Table(name="Project")
public class ProjectDTO extends BaseDTO {

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date startTime;

    @Temporal(TemporalType.DATE)
    @Future
    private Date endTime;

    @NotEmpty
    @NotNull
    @Size(min = 1, max = 10)
    private String title;

    @NotEmpty
    @NotNull
    @Size(min = 10, max = 200)
    private String address;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}