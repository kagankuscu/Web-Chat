package com.Feelfree2code.STA.common;

public class APIResultVM {
    private boolean isSucceed;
    private Integer id;

    public boolean isSucceed() {
        return isSucceed;
    }

    public void setSucceed(boolean succeed) {
        isSucceed = succeed;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
