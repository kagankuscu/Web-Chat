package com.Feelfree2code.STA.common;

import java.util.List;

public class APIBadRequestModel {
    private List<String> requiredFields;

    public List<String> getRequiredFields() {
        return requiredFields;
    }

    public void setRequiredFields(List<String> requiredFields) {
        this.requiredFields = requiredFields;
    }
}
