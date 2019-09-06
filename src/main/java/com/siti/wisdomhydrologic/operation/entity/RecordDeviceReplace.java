package com.siti.wisdomhydrologic.operation.entity;


import cn.afterturn.easypoi.excel.annotation.Excel;

public class RecordDeviceReplace {

    @Excel(name = "reportId", height = 11, width = 20)
    private int reportId;
    @Excel(name = "stationCode", height = 11, width = 20)
    private String stationCode;
    @Excel(name = "stationName", height = 11, width = 20)
    private String stationName;
    @Excel(name = "manageOrgId", height = 11, width = 20)
    private int manageOrgId;
    @Excel(name = "manageOrgName", height = 11, width = 20)
    private String manageOrgName;
    @Excel(name = "replaceDate", height = 11, width = 20)
    private String replaceDate;
    @Excel(name = "originDeviceTypeCode", height = 11, width = 20)
    private String originDeviceTypeCode;
    @Excel(name = "originDeviceCode", height = 11, width = 20)
    private String originDeviceCode;
    @Excel(name = "originOrgId", height = 11, width = 20)
    private int originOrgId;
    @Excel(name = "originOrgName", height = 11, width = 20)
    private String originOrgName;
    @Excel(name = "newDeviceTypeCode", height = 11, width = 20)
    private String newDeviceTypeCode;
    @Excel(name = "newDeviceCode", height = 11, width = 20)
    private String newDeviceCode;
    @Excel(name = "newOrgId", height = 11, width = 20)
    private int newOrgId;
    @Excel(name = "newOrgName", height = 11, width = 20)
    private String newOrgName;
    @Excel(name = "replaceReason", height = 11, width = 20)
    private String replaceReason;
    @Excel(name = "createBy", height = 11, width = 20)
    private String createBy;
    @Excel(name = "createTime", height = 11, width = 20)
    private String createTime;
    @Excel(name = "updateBy", height = 11, width = 20)
    private int updateBy;
    @Excel(name = "newDeviceName", height = 11, width = 20)
    private String newDeviceName;
    @Excel(name = "originDeviceName", height = 11, width = 20)
    private String originDeviceName;


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


    public String getReplaceDate() {
        return replaceDate;
    }

    public void setReplaceDate(String replaceDate) {
        this.replaceDate = replaceDate;
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

}
