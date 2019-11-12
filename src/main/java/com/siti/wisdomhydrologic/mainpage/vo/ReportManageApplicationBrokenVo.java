package com.siti.wisdomhydrologic.mainpage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by zyw on 2019/9/3.
 */

@ApiModel(value="首页派单轮播图",description="首页派单轮播图")
public class ReportManageApplicationBrokenVo {

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

    @ApiModelProperty(value = "故障开始解决时间（自动）", name = "reportId")
    private String brokenOnResolveTime;

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


    @ApiModelProperty(value = "防汛要求解决时间（手动）", name = "reportId")
    private String brokenAskToResolveTime;

    @ApiModelProperty(value = "防汛要求抢修情况上报时间，与前一列一致，且可改（手动）", name = "reportId")
    private String brokenrRequestReportTime;

    @ApiModelProperty(value = "站址", name = "reportId")
    private String stationAddress;


    public String getBrokenOnResolveTime() {
        return brokenOnResolveTime;
    }

    public void setBrokenOnResolveTime(String brokenOnResolveTime) {
        this.brokenOnResolveTime = brokenOnResolveTime;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getResolveMethod() {
        return resolveMethod;
    }

    public void setResolveMethod(String resolveMethod) {
        this.resolveMethod = resolveMethod;
    }

    public String getResolveUserId() {
        return resolveUserId;
    }

    public void setResolveUserId(String resolveUserId) {
        this.resolveUserId = resolveUserId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getBrokenAskToResolveTime() {
        return brokenAskToResolveTime;
    }

    public void setBrokenAskToResolveTime(String brokenAskToResolveTime) {
        this.brokenAskToResolveTime = brokenAskToResolveTime;
    }

    public String getBrokenrRequestReportTime() {
        return brokenrRequestReportTime;
    }

    public void setBrokenrRequestReportTime(String brokenrRequestReportTime) {
        this.brokenrRequestReportTime = brokenrRequestReportTime;
    }

    public String getStationAddress() {
        return stationAddress;
    }

    public void setStationAddress(String stationAddress) {
        this.stationAddress = stationAddress;
    }
}
