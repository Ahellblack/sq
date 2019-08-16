package com.siti.wisdomhydrologic.operation.entity;

import java.util.Date;

public class ReportManageApplicationBroken {

    private int reportId;
    private int stationId;
    private String stationName;
    private String brokenName;
    private String brokenAccording;
    private String brokenAccordingId;
    private Date brokenResponseTime;
    private Date brokenResolveTime;
    private Date createTime;
    private String resolveMethod;
    private int resolveUserId;
    private String remark;

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
