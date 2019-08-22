package com.siti.wisdomhydrologic.operation.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.util.Date;

public class ReportManageApplicationBroken {


    @Excel(name = "reportId", height = 11, width = 20)
    private int reportId;
    @Excel(name = "stationId", height = 11, width = 20)
    private int stationId;
    @Excel(name = "stationName", height = 11, width = 20)
    private String stationName;
    @Excel(name = "brokenName", height = 11, width = 20)
    private String brokenName;
    @Excel(name = "brokenAccording", height = 11, width = 20)
    private String brokenAccording;
    @Excel(name = "brokenAccordingId", height = 11, width = 20)
    private String brokenAccordingId;
    @Excel(name = "brokenResponseTime", height = 11, width = 20)
    private Date brokenResponseTime;
    @Excel(name = "brokenResolveTime", height = 11, width = 20)
    private Date brokenResolveTime;
    @Excel(name = "createTime", height = 11, width = 20)
    private Date createTime;
    @Excel(name = "resolveMethod", height = 11, width = 20)
    private String resolveMethod;
    @Excel(name = "resolveUserId", height = 11, width = 20)
    private int resolveUserId;
    @Excel(name = "remark", height = 11, width = 20)
    private String remark;
    @Excel(name = "requestDesignatingStatus", height = 11, width = 20)
    private int requestDesignatingStatus;
    @Excel(name = "requestDesignatingTime", height = 11, width = 20)
    private String requestDesignatingTime;



    public int getRequestDesignatingStatus() {
        return requestDesignatingStatus;
    }

    public void setRequestDesignatingStatus(int requestDesignatingStatus) {
        this.requestDesignatingStatus = requestDesignatingStatus;
    }

    public String getRequestDesignatingTime() {
        return requestDesignatingTime;
    }

    public void setRequestDesignatingTime(String requestDesignatingTime) {
        this.requestDesignatingTime = requestDesignatingTime;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getBrokenName() {
        return brokenName;
    }

    public void setBrokenName(String brokenName) {
        this.brokenName = brokenName;
    }


    public Date getBrokenResponseTime() {
        return brokenResponseTime;
    }

    public void setBrokenResponseTime(Date brokenResponseTime) {
        this.brokenResponseTime = brokenResponseTime;
    }


    public Date getBrokenResolveTime() {
        return brokenResolveTime;
    }

    public void setBrokenResolveTime(Date brokenResolveTime) {
        this.brokenResolveTime = brokenResolveTime;
    }

    public int getResolveUserId() {
        return resolveUserId;
    }

    public void setResolveUserId(int resolveUserId) {
        this.resolveUserId = resolveUserId;
    }

    public String getResolveMethod() {

        return resolveMethod;
    }

    public void setResolveMethod(String resolveMethod) {
        this.resolveMethod = resolveMethod;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
