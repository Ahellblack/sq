package com.siti.wisdomhydrologic.maintainconfig.entity;

/**
 * Created by dell on 2019/9/27.
 */
public class ConfigAbnormalError {

    private String errorId;
    private String errorName;
    private String belongWhichTable;
    private Integer table1Relate;

    private String brokenAccordingId;
    private String brokenAccording;

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

    public String getErrorId() {
        return errorId;
    }

    public void setErrorId(String errorId) {
        this.errorId = errorId;
    }

    public String getErrorName() {
        return errorName;
    }

    public void setErrorName(String errorName) {
        this.errorName = errorName;
    }

    public String getBelongWhichTable() {
        return belongWhichTable;
    }

    public void setBelongWhichTable(String belongWhichTable) {
        this.belongWhichTable = belongWhichTable;
    }

    public Integer getTable1Relate() {
        return table1Relate;
    }

    public void setTable1Relate(Integer table1Relate) {
        this.table1Relate = table1Relate;
    }


}
