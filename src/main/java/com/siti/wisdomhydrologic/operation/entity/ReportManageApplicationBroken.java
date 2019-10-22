package com.siti.wisdomhydrologic.operation.entity;

import io.swagger.annotations.ApiModelProperty;


public class ReportManageApplicationBroken {
    @Override
    public String toString() {
        return "ReportManageApplicationBroken{" + "reportId=" + reportId + ", stationId=" + stationId + ", stationName='" + stationName + '\'' + ", brokenName='" + brokenName + '\'' + ", brokenAccording='" + brokenAccording + '\'' + ", brokenAccordingId='" + brokenAccordingId + '\'' + ", brokenResponseTime='" + brokenResponseTime + '\'' + ", brokenResolveTime='" + brokenResolveTime + '\'' + ", createTime='" + createTime + '\'' + ", requestDesignatingTime='" + requestDesignatingTime + '\'' + ", resolveMethod='" + resolveMethod + '\'' + ", resolveUserId='" + resolveUserId + '\'' + ", remark='" + remark + '\'' + ", requestDesignatingStatus=" + requestDesignatingStatus + ", brokenAskToResolveTime='" + brokenAskToResolveTime + '\'' + ", brokenRequestReportTime='" + brokenRequestReportTime + '\'' + ", brokenOnResolveTime='" + brokenOnResolveTime + '\'' + ", errorLastestAppearTime='" + errorLastestAppearTime + '\'' + '}';
    }

    @ApiModelProperty(value = "用户名", name = "reportId")
    private int reportId;

    @ApiModelProperty(value = "测站id", name = "reportId")
    private int stationId;

    @ApiModelProperty(value = "测站名", name = "reportId")
    private String stationName;

    @ApiModelProperty(value = "异常名", name = "reportId")
    private String brokenName;

    @ApiModelProperty(value = "异常依据", name = "reportId")
    private String brokenAccording;

    @ApiModelProperty(value = "异常依据id", name = "reportId")
    private String brokenAccordingId;

    @ApiModelProperty(value = "故障应处理时间(自动)", name = "reportId")
    private String brokenResponseTime;

    @ApiModelProperty(value = "故障解决时间（自动）", name = "reportId")
    private String brokenResolveTime;

    @ApiModelProperty(value = "故障生成时间（自动）", name = "reportId")
    private String createTime;

    @ApiModelProperty(value = "派单时间（自动）", name = "reportId")
    private String requestDesignatingTime;

    @ApiModelProperty(value = "处理方式", name = "reportId")
    private String resolveMethod;

    @ApiModelProperty(value = "故障处理人（是否存在多人）", name = "reportId")
    private String resolveUserId;

    @ApiModelProperty(value = "备注", name = "reportId")
    private String remark;

    @ApiModelProperty(value = " 派单状态 1:发现  2：已派单  3：维护中  4：已处理", name = "reportId")
    private int requestDesignatingStatus;

    @ApiModelProperty(value = "防汛要求解决时间（手动）")
    private String brokenAskToResolveTime;

    @ApiModelProperty(value = "防汛要求抢修情况上报时间，与前一列一致，且可改（手动）")
    private String brokenRequestReportTime;

    @ApiModelProperty(value = "故障开始处理时间（自动）")
    private String brokenOnResolveTime;

    @ApiModelProperty(value = "故障最后出现时间")
    private String errorLastestAppearTime;

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrokenRequestReportTime() {
        return brokenRequestReportTime;
    }

    public void setBrokenRequestReportTime(String brokenRequestReportTime) {
        this.brokenRequestReportTime = brokenRequestReportTime;
    }

    public String getErrorLastestAppearTime() {
        return errorLastestAppearTime;
    }

    public void setErrorLastestAppearTime(String errorLastestAppearTime) {
        this.errorLastestAppearTime = errorLastestAppearTime;
    }

    public String getBrokenOnResolveTime() {

        return brokenOnResolveTime;
    }

    public void setBrokenOnResolveTime(String brokenOnResolveTime) {
        this.brokenOnResolveTime = brokenOnResolveTime;
    }

    public String getBrokenAskToResolveTime() {
        return brokenAskToResolveTime;
    }

    public void setBrokenAskToResolveTime(String brokenAskToResolveTime) {
        this.brokenAskToResolveTime = brokenAskToResolveTime;
    }

    public String getBrokenrRequestReportTime() {
        return brokenRequestReportTime;
    }

    public void setBrokenrRequestReportTime(String brokenRequestReportTime) {
        this.brokenRequestReportTime = brokenRequestReportTime;
    }

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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
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


    public String getBrokenResponseTime() {
        return brokenResponseTime;
    }

    public void setBrokenResponseTime(String brokenResponseTime) {
        this.brokenResponseTime = brokenResponseTime;
    }


    public String getBrokenResolveTime() {
        return brokenResolveTime;
    }

    public void setBrokenResolveTime(String brokenResolveTime) {
        this.brokenResolveTime = brokenResolveTime;
    }

    public String getResolveUserId() {
        return resolveUserId;
    }

    public void setResolveUserId(String resolveUserId) {
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
