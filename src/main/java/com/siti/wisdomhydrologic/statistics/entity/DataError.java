package com.siti.wisdomhydrologic.statistics.entity;

/**
 * Created by zyw on 2019/9/26.
 */
public class DataError {

    private Integer number;
    private String brokenAccording;
    private String brokenAccordingId;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getBrokenAccording() {
        return brokenAccording;
    }

    public void setBrokenAccording(String brokenAccording) {
        this.brokenAccording = brokenAccording;
    }

    public String getBrokenAccordingId() {
        return brokenAccordingId;
    }

    public void setBrokenAccordingId(String brokenAccordingId) {
        this.brokenAccordingId = brokenAccordingId;
    }
}
