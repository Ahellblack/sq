package com.siti.wisdomhydrologic.operation.entity;

import java.util.Date;

/**
 * Created by dell on 2019/7/26.
 */
public class ReportHyetometerTest {

      private Integer reportId;
      private String stationCode;
      private String stationName;
      private Integer manageOrgId;
      private Integer manageOrgName;
      private String libraryDate;
      private String deviceTypeCode;
      private String deviceCode;
      private String startTime;
      private String endTime;
      private String timeDuration;
      private double waterPoll;
      private double waterPollStrength;
      private double waterDisplay;
      private double errorValue;
      private String createBy;
      private Date createTime;

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
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

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ReportHyetometerTest{" + "reportId=" + reportId + ", stationCode='" + stationCode + '\'' + ", stationName='" + stationName + '\'' + ", manageOrgId=" + manageOrgId + ", manageOrgName=" + manageOrgName + ", libraryDate='" + libraryDate + '\'' + ", deviceTypeCode='" + deviceTypeCode + '\'' + ", deviceCode='" + deviceCode + '\'' + ", startTime='" + startTime + '\'' + ", endTime='" + endTime + '\'' + ", timeDuration='" + timeDuration + '\'' + ", waterPoll=" + waterPoll + ", waterPollStrength=" + waterPollStrength + ", waterDisplay=" + waterDisplay + ", errorValue=" + errorValue + ", createBy='" + createBy + '\'' + ", createTime=" + createTime + '}';
    }
}
