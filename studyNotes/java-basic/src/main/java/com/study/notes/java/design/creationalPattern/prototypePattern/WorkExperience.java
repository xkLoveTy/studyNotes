package com.study.notes.java.design.creationalPattern.prototypePattern;

import java.io.Serializable;

/**
 * Created by xiangke on 2016/5/27.
 */
public class WorkExperience implements Serializable{

    private String workDate;
    private String company;

    public String getWorkDate() {
        return workDate;
    }

    public void setWorkDate(String workDate) {
        this.workDate = workDate;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
