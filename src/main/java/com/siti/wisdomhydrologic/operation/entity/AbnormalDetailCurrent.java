package com.siti.wisdomhydrologic.operation.entity;

/**
 * Created by zyw on 2019/10/24.
 */
public class AbnormalDetailCurrent {

    private int id;
    private int sensorCode;
    private String date;
    private String dataError;
    private double errorValue;
    private String lastDate;
    private double lastErrorValue;
    private int isRecoverStatus;
    private int occurTimes;
    private String recoverTime;
    private String updateTime;
    private String description;
    private int table2DisplayStatus;
    private int table4DisplayStatus;

    private String brokenAccordingId;
    private String brokenAccording;
    private String valueTypeName;
    private String errorId;
    private String errorName;
    private Integer errorDataId;
    private String table1Relate;


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

    public String getValueTypeName() {
        return valueTypeName;
    }

    public void setValueTypeName(String valueTypeName) {
        this.valueTypeName = valueTypeName;
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

    public Integer getErrorDataId() {
        return errorDataId;
    }

    public void setErrorDataId(Integer errorDataId) {
        this.errorDataId = errorDataId;
    }

    public String getTable1Relate() {
        return table1Relate;
    }

    public void setTable1Relate(String table1Relate) {
        this.table1Relate = table1Relate;
    }

    public int getTable2DisplayStatus() {
        return table2DisplayStatus;
    }

    public void setTable2DisplayStatus(int table2DisplayStatus) {
        this.table2DisplayStatus = table2DisplayStatus;
    }

    public int getTable4DisplayStatus() {
        return table4DisplayStatus;
    }

    public void setTable4DisplayStatus(int table4DisplayStatus) {
        this.table4DisplayStatus = table4DisplayStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSensorCode() {
        return sensorCode;
    }

    public void setSensorCode(int sensorCode) {
        this.sensorCode = sensorCode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDataError() {
        return dataError;
    }

    public void setDataError(String dataError) {
        this.dataError = dataError;
    }

    public double getErrorValue() {
        return errorValue;
    }

    public void setErrorValue(double errorValue) {
        this.errorValue = errorValue;
    }

    public String getLastDate() {
        return lastDate;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }

    public double getLastErrorValue() {
        return lastErrorValue;
    }

    public void setLastErrorValue(double lastErrorValue) {
        this.lastErrorValue = lastErrorValue;
    }

    public int getIsRecoverStatus() {
        return isRecoverStatus;
    }

    public void setIsRecoverStatus(int isRecoverStatus) {
        this.isRecoverStatus = isRecoverStatus;
    }

    public int getOccurTimes() {
        return occurTimes;
    }

    public void setOccurTimes(int occurTimes) {
        this.occurTimes = occurTimes;
    }

    public String getRecoverTime() {
        return recoverTime;
    }

    public void setRecoverTime(String recoverTime) {
        this.recoverTime = recoverTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

