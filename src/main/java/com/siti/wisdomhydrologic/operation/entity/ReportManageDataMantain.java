package com.siti.wisdomhydrologic.operation.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.util.Date;

public class ReportManageDataMantain {

    @Excel(name = "reportId", height = 11, width = 10)
    private Integer reportId;
    @Excel(name = "stationCode", height = 11, width = 10)
    private int stationCode;
    @Excel(name = "alterDate", height = 11, width = 20)
    private String alterDate;
    @Excel(name = "stationName", height = 11, width = 20)
    private String stationName;
    @Excel(name = "alterSensorTypeId", height = 11, width = 10)
    private Integer alterSensorTypeId;
    @Excel(name = "alterSensorTypeName", height = 11, width = 20)
    private String alterSensorTypeName;
    @Excel(name = "errorDataReason", height = 11, width = 20)
    private String errorDataReason;
    @Excel(name = "errorDataType", height = 11, width = 10)
    private Integer errorDataType;
    @Excel(name = "errorTimeSpace", height = 11, width = 10)
    private String errorTimeSpace;
    @Excel(name = "errorValue", height = 11, width = 10)
    private String errorValue;
    @Excel(name = "confirValue", height = 11, width = 10)
    private String confirValue;
    @Excel(name = "errorUnit", height = 11, width = 5)
    private String errorUnit;
    @Excel(name = "errorDataReRun", height = 11, width = 10)
    private Integer errorDataReRun;
    @Excel(name = "missDataType", height = 11, width = 10)
    private String missDataType;
    @Excel(name = "missTimeSpace", height = 11, width = 10)
    private String missTimeSpace;
    @Excel(name = "missDataReRun", height = 11, width = 10)
    private Integer missDataReRun;
    @Excel(name = "createTime", height = 11, width = 20)
    private Date createTime;
    @Excel(name = "createBy", height = 11, width = 20)
    private String createBy;
    @Excel(name = "manageOrgId", height = 11, width = 20)
    private Integer manageOrgId;
    @Excel(name = "manageOrgName", height = 11, width = 20)
    private String manageOrgName;
    @Excel(name = "brokenAccordingId", height = 11, width = 20)
    private String brokenAccordingId;

    public String getBrokenAccordingId() {
        return brokenAccordingId;
    }

    public void setBrokenAccordingId(String brokenAccordingId) {
        this.brokenAccordingId = brokenAccordingId;
    }

    public String getAlterDate() {
        return alterDate;
    }

    public void setAlterDate(String alterDate) {
        this.alterDate = alterDate;
    }

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public int getStationCode() {
        return stationCode;
    }

    public void setStationCode(int stationCode) {
        this.stationCode = stationCode;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public Integer getAlterSensorTypeId() {
        return alterSensorTypeId;
    }

    public void setAlterSensorTypeId(Integer alterSensorTypeId) {
        this.alterSensorTypeId = alterSensorTypeId;
    }

    public String getAlterSensorTypeName() {
        return alterSensorTypeName;
    }

    public void setAlterSensorTypeName(String alterSensorTypeName) {
        this.alterSensorTypeName = alterSensorTypeName;
    }

    public String getErrorDataReason() {
        return errorDataReason;
    }

    public void setErrorDataReason(String errorDataReason) {
        this.errorDataReason = errorDataReason;
    }

    public Integer getErrorDataType() {
        return errorDataType;
    }

    public void setErrorDataType(Integer errorDataType) {
        this.errorDataType = errorDataType;
    }

    public String getErrorTimeSpace() {
        return errorTimeSpace;
    }

    public void setErrorTimeSpace(String errorTimeSpace) {
        this.errorTimeSpace = errorTimeSpace;
    }

    public String getErrorValue() {
        return errorValue;
    }

    public void setErrorValue(String errorValue) {
        this.errorValue = errorValue;
    }

    public String getConfirValue() {
        return confirValue;
    }

    public void setConfirValue(String confirValue) {
        this.confirValue = confirValue;
    }

    public String getErrorUnit() {
        return errorUnit;
    }

    public void setErrorUnit(String errorUnit) {
        this.errorUnit = errorUnit;
    }

    public Integer getErrorDataReRun() {
        return errorDataReRun;
    }

    public void setErrorDataReRun(Integer errorDataReRun) {
        this.errorDataReRun = errorDataReRun;
    }

    public String getMissDataType() {
        return missDataType;
    }

    public void setMissDataType(String missDataType) {
        this.missDataType = missDataType;
    }

    public String getMissTimeSpace() {
        return missTimeSpace;
    }

    public void setMissTimeSpace(String missTimeSpace) {
        this.missTimeSpace = missTimeSpace;
    }

    public Integer getMissDataReRun() {
        return missDataReRun;
    }

    public void setMissDataReRun(Integer missDataReRun) {
        this.missDataReRun = missDataReRun;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Integer getManageOrgId() {
        return manageOrgId;
    }

    public void setManageOrgId(Integer manageOrgId) {
        this.manageOrgId = manageOrgId;
    }

    public String getManageOrgName() {
        return manageOrgName;
    }

    public void setManageOrgName(String manageOrgName) {
        this.manageOrgName = manageOrgName;
    }

    @Override
    public String toString() {
        return "ReportManageDataMantainVo{" + "reportId=" + reportId + ", stationCode='" + stationCode + '\'' + ", alterDate='" + alterDate + '\'' + ", stationName='" + stationName + '\'' + ", alterSensorTypeId=" + alterSensorTypeId + ", alterSensorTypeName='" + alterSensorTypeName + '\'' + ", errorDataReason='" + errorDataReason + '\'' + ", errorDataType=" + errorDataType + ", errorTimeSpace='" + errorTimeSpace + '\'' + ", errorValue='" + errorValue + '\'' + ", confirValue='" + confirValue + '\'' + ", errorUnit='" + errorUnit + '\'' + ", errorDataReRun=" + errorDataReRun + ", missDataType='" + missDataType + '\'' + ", missTimeSpace='" + missTimeSpace + '\'' + ", missDataReRun=" + missDataReRun + ", createTime=" + createTime + ", createBy=" + createBy + ", manageOrgId=" + manageOrgId + ", manageOrgName='" + manageOrgName + '\'' + '}';
    }
}
