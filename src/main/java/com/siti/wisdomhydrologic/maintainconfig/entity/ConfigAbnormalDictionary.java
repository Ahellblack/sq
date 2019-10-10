package com.siti.wisdomhydrologic.maintainconfig.entity;


public class ConfigAbnormalDictionary {

    private String brokenAccordingId;
    private String brokenAccording;
    private String errorName;
    private int errorDataId;
    private int table1Relate;

    @Override
    public String toString() {
        return "ConfigAbnormalDictionary{" + "brokenAccordingId='" + brokenAccordingId + '\'' + ", brokenAccording='" + brokenAccording + '\'' + ", errorName='" + errorName + '\'' + ", errorDataId=" + errorDataId + ", table1Relate=" + table1Relate + '}';
    }

    public int getTable1_relate() {
        return table1Relate;
    }

    public void setTable1_relate(int table1Relate) {
        this.table1Relate = table1Relate;
    }

    public String getBrokenAccordingId() {
        return brokenAccordingId;
    }

    public void setBrokenAccordingId(String brokenAccordingId) {
        this.brokenAccordingId = brokenAccordingId;
    }

    public String getBrokenAccording() {
        return brokenAccording;
    }

    public void setBrokenAccording(String brokenAccording) {
        this.brokenAccording = brokenAccording;
    }

    public String getErrorName() {
        return errorName;
    }

    public void setErrorName(String errorName) {
        this.errorName = errorName;
    }


    public int getErrorDataId() {
        return errorDataId;
    }

    public void setErrorDataId(int errorDataId) {
        this.errorDataId = errorDataId;
    }
}
