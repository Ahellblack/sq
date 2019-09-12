package com.siti.wisdomhydrologic.operation.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;

/**
 * Created by dell on 2019/7/26.
 */
public class ReportHyetometerTest {

    @Excel(name = "reportId", height = 11, width = 20)
    private Integer reportId;
    @Excel(name = "stationCode", height = 11, width = 20)
    private int stationCode;
    @Excel(name = "stationName", height = 11, width = 20)
    private String stationName;
    @Excel(name = "manageOrgId", height = 11, width = 20)
    private Integer manageOrgId;
    @Excel(name = "manageOrgName", height = 11, width = 20)
    private Integer manageOrgName;
    @Excel(name = "libraryDate", height = 11, width = 20)
    private String libraryDate;
    @Excel(name = "deviceTypeCode", height = 11, width = 20)
    private String deviceTypeCode;
    @Excel(name = "deviceCode", height = 11, width = 20)
    private String deviceCode;
    @Excel(name = "startTime", height = 11, width = 20)
    private String startTime;
    @Excel(name = "endTime", height = 11, width = 20)
    private String endTime;
    @Excel(name = "timeDuration", height = 11, width = 20)
    private String timeDuration;
    @Excel(name = "waterPoll", height = 11, width = 20)
    private double waterPoll;
    @Excel(name = "waterPollStrength", height = 11, width = 20)
    private double waterPollStrength;
    @Excel(name = "waterDisplay", height = 11, width = 20)
    private double waterDisplay;
    @Excel(name = "errorValue", height = 11, width = 20)
    private double errorValue;
    @Excel(name = "createBy", height = 11, width = 20)
    private String createBy;
    @Excel(name = "createTime", height = 11, width = 20)
    private String createTime;


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

    public Integer getManageOrgId() {
        return manageOrgId;
    }

    public void setManageOrgId(Integer manageOrgId) {
        this.manageOrgId = manageOrgId;
    }

    public Integer getManageOrgName() {
        return manageOrgName;
    }

    public void setManageOrgName(Integer manageOrgName) {
        this.manageOrgName = manageOrgName;
    }

    public String getLibraryDate() {
        return libraryDate;
    }

    public void setLibraryDate(String libraryDate) {
        this.libraryDate = libraryDate;
    }

    public String getDeviceTypeCode() {
        return deviceTypeCode;
    }

    public void setDeviceTypeCode(String deviceTypeCode) {
        this.deviceTypeCode = deviceTypeCode;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getTimeDuration() {
        return timeDuration;
    }

    public void setTimeDuration(String timeDuration) {
        this.timeDuration = timeDuration;
    }

    public double getWaterPoll() {
        return waterPoll;
    }

    public void setWaterPoll(double waterPoll) {
        this.waterPoll = waterPoll;
    }

    public double getWaterPollStrength() {
        return waterPollStrength;
    }

    public void setWaterPollStrength(double waterPollStrength) {
        this.waterPollStrength = waterPollStrength;
    }

    public double getWaterDisplay() {
        return waterDisplay;
    }

    public void setWaterDisplay(double waterDisplay) {
        this.waterDisplay = waterDisplay;
    }

    public double getErrorValue() {
        return errorValue;
    }

    public void setErrorValue(double errorValue) {
        this.errorValue = errorValue;
    }


    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Override
    public String toString() {
        return "ReportHyetometerTest{" + "reportId=" + reportId + ", stationCode='" + stationCode + '\'' + ", stationName='" + stationName + '\'' + ", manageOrgId=" + manageOrgId + ", manageOrgName=" + manageOrgName + ", libraryDate='" + libraryDate + '\'' + ", deviceTypeCode='" + deviceTypeCode + '\'' + ", deviceCode='" + deviceCode + '\'' + ", startTime='" + startTime + '\'' + ", endTime='" + endTime + '\'' + ", timeDuration='" + timeDuration + '\'' + ", waterPoll=" + waterPoll + ", waterPollStrength=" + waterPollStrength + ", waterDisplay=" + waterDisplay + ", errorValue=" + errorValue + ", createBy='" + createBy + '\'' + ", createTime=" + createTime + '}';
    }
}
