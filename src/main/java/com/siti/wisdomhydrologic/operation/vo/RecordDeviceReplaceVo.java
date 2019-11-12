package com.siti.wisdomhydrologic.operation.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by zyw on 2019/9/11.
 */
public class RecordDeviceReplaceVo {

    @ApiModelProperty(value = "自增id")
    private int reportId;
    @ApiModelProperty(value = "测站id")
    private String stationCode;
    @ApiModelProperty(value = "测站名")
    private String stationName;
    @ApiModelProperty(value = "测站组织id")
    private int manageOrgId;
    @ApiModelProperty(value = "测站组织名")
    private String manageOrgName;
    @ApiModelProperty(value = "更换日期")
    private String replaceDate;
    @ApiModelProperty(value = "原设备型号")
    private String originDeviceTypeCode;
    @ApiModelProperty(value = "原设备编号" )
    private String originDeviceCode;
    @ApiModelProperty(value = "原资产归属单位ID")
    private int originOrgId;
    @ApiModelProperty(value = "原资产归属单位名称")
    private String originOrgName;
    @ApiModelProperty(value = "现设备型号")
    private String newDeviceTypeCode;
    @ApiModelProperty(value = "现设备编号")
    private String newDeviceCode;
    @ApiModelProperty(value = "现设备资产归属单位ID")
    private int newOrgId;
    @ApiModelProperty(value = "现设备自查归属单位名称")
    private String newOrgName;
    @ApiModelProperty(value = "更换原因")
    private String replaceReason;
    @ApiModelProperty(value = "填报人")
    private String createBy;
    @ApiModelProperty(value = "填报时间")
    private String createTime;
    @ApiModelProperty(value = "修改人")
    private int updateBy;
    @ApiModelProperty(value = "现设备名称")
    private String newDeviceName;
    @ApiModelProperty(value = "原设备名称")
    private String originDeviceName;

    @ApiModelProperty(value = "原设备资产id")
    private String originDatabaseId;

    @ApiModelProperty(value = "原设备替换状态")
    private String originDatabaseStatus;
    @ApiModelProperty(value = "新设备资产id")
    private String newDatabaseId;


    public String getReplaceDate() {
        return replaceDate;
    }

    public void setReplaceDate(String replaceDate) {
        this.replaceDate = replaceDate;
    }

    public String getReplaceReason() {
        return replaceReason;
    }

    public void setReplaceReason(String replaceReason) {
        this.replaceReason = replaceReason;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(int updateBy) {
        this.updateBy = updateBy;
    }

    public String getNewDatabaseId() {
        return newDatabaseId;
    }

    public void setNewDatabaseId(String newDatabaseId) {
        this.newDatabaseId = newDatabaseId;
    }

    public String getOriginDatabaseId() {

        return originDatabaseId;
    }

    public void setOriginDatabaseId(String originDatabaseId) {
        this.originDatabaseId = originDatabaseId;
    }

    public String getOriginDatabaseStatus() {
        return originDatabaseStatus;
    }

    public void setOriginDatabaseStatus(String originDatabaseStatus) {
        this.originDatabaseStatus = originDatabaseStatus;
    }

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
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

    public int getManageOrgId() {
        return manageOrgId;
    }

    public void setManageOrgId(int manageOrgId) {
        this.manageOrgId = manageOrgId;
    }

    public String getManageOrgName() {
        return manageOrgName;
    }

    public void setManageOrgName(String manageOrgName) {
        this.manageOrgName = manageOrgName;
    }

    public String getOriginDeviceTypeCode() {
        return originDeviceTypeCode;
    }

    public void setOriginDeviceTypeCode(String originDeviceTypeCode) {
        this.originDeviceTypeCode = originDeviceTypeCode;
    }

    public String getOriginDeviceCode() {
        return originDeviceCode;
    }

    public void setOriginDeviceCode(String originDeviceCode) {
        this.originDeviceCode = originDeviceCode;
    }

    public int getOriginOrgId() {
        return originOrgId;
    }

    public void setOriginOrgId(int originOrgId) {
        this.originOrgId = originOrgId;
    }

    public String getOriginOrgName() {
        return originOrgName;
    }

    public void setOriginOrgName(String originOrgName) {
        this.originOrgName = originOrgName;
    }

    public String getNewDeviceTypeCode() {
        return newDeviceTypeCode;
    }

    public void setNewDeviceTypeCode(String newDeviceTypeCode) {
        this.newDeviceTypeCode = newDeviceTypeCode;
    }

    public String getNewDeviceCode() {
        return newDeviceCode;
    }

    public void setNewDeviceCode(String newDeviceCode) {
        this.newDeviceCode = newDeviceCode;
    }

    public int getNewOrgId() {
        return newOrgId;
    }

    public void setNewOrgId(int newOrgId) {
        this.newOrgId = newOrgId;
    }

    public String getNewOrgName() {
        return newOrgName;
    }

    public void setNewOrgName(String newOrgName) {
        this.newOrgName = newOrgName;
    }

    public String getNewDeviceName() {
        return newDeviceName;
    }

    public void setNewDeviceName(String newDeviceName) {
        this.newDeviceName = newDeviceName;
    }

    public String getOriginDeviceName() {
        return originDeviceName;
    }

    public void setOriginDeviceName(String originDeviceName) {
        this.originDeviceName = originDeviceName;
    }

    @Override
    public String toString() {
        return "RecordDeviceReplaceVo{" + "reportId=" + reportId + ", stationCode='" + stationCode + '\'' + ", stationName='" + stationName + '\'' + ", manageOrgId=" + manageOrgId + ", manageOrgName='" + manageOrgName + '\'' + ", replaceDate='" + replaceDate + '\'' + ", originDeviceTypeCode='" + originDeviceTypeCode + '\'' + ", originDeviceCode='" + originDeviceCode + '\'' + ", originOrgId=" + originOrgId + ", originOrgName='" + originOrgName + '\'' + ", newDeviceTypeCode='" + newDeviceTypeCode + '\'' + ", newDeviceCode='" + newDeviceCode + '\'' + ", newOrgId=" + newOrgId + ", newOrgName='" + newOrgName + '\'' + ", replaceReason='" + replaceReason + '\'' + ", createBy='" + createBy + '\'' + ", createTime='" + createTime + '\'' + ", updateBy=" + updateBy + ", newDeviceName='" + newDeviceName + '\'' + ", originDeviceName='" + originDeviceName + '\'' + ", originDatabaseId='" + originDatabaseId + '\'' + ", originDatabaseStatus='" + originDatabaseStatus + '\'' + ", newDatabaseId='" + newDatabaseId + '\'' + '}';
    }
}
