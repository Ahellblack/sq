package com.siti.wisdomhydrologic.operation.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;

public class ReportManageDataMantain {

    @Override
    public String toString() {
        return "ReportManageDataMantain{" + "reportId=" + reportId + ", stationCode=" + stationCode + ", alterDate='" + alterDate + '\'' + ", stationName='" + stationName + '\'' + ", alterSensorTypeId=" + alterSensorTypeId + ", alterSensorTypeName='" + alterSensorTypeName + '\'' + ", errorDataReason='" + errorDataReason + '\'' + ", errorDataType=" + errorDataType + ", errorTimeSpace='" + errorTimeSpace + '\'' + ", errorValue='" + errorValue + '\'' + ", confirValue='" + confirValue + '\'' + ", errorUnit='" + errorUnit + '\'' + ", errorDataReRun=" + errorDataReRun + ", missDataType=" + missDataType + ", missTimeSpace='" + missTimeSpace + '\'' + ", missDataReRun=" + missDataReRun + ", createTime='" + createTime + '\'' + ", createBy='" + createBy + '\'' + ", manageOrgId=" + manageOrgId + ", manageOrgName='" + manageOrgName + '\'' + ", brokenAccordingId='" + brokenAccordingId + '\'' + ", errorLastestAppearTime='" + errorLastestAppearTime + '\'' + '}';
    }

    @ApiModelProperty("value=自增主键id")
    private Integer reportId;
    @ApiModelProperty("value=测站编号")
    private int stationCode;
    @ApiModelProperty("value=数据修正日期，xxxx-xx")
    private String alterDate;
    @ApiModelProperty("value=测站名称")
    private String stationName;
    @ApiModelProperty("value=修改项目字典ID(传感器类型ID)")
    private Integer alterSensorTypeId;
    @ApiModelProperty("value=修改项目字典名称(传感器类型名称)")
    private String alterSensorTypeName;
    @ApiModelProperty("value=错误原因 ")
    private String errorDataReason;
    @ApiModelProperty("value=数据类型（是否有固定类型:1 实时 2 五分钟 3 小时 4 一天")
    private Integer errorDataType;
    @ApiModelProperty("value=错误发生时段")
    private String errorTimeSpace;
    @ApiModelProperty("value=错误值")
    private String errorValue;
    @ApiModelProperty("value=改正值")
    private String confirValue;
    @ApiModelProperty("value=错误值单位")
    private String errorUnit;
    @ApiModelProperty("value=是否进行错误数据反算0 否 1 是,")
    private Integer errorDataReRun;
    @ApiModelProperty("value=缺数数据类型（是否有固定类型，实时、五分钟、小时）")
    private Integer missDataType;
    @ApiModelProperty("value=缺数数据时段2019-03-20 10:00~2019-03-20 11:00")
    private String missTimeSpace;
    @ApiModelProperty("value=是否进行缺数数据反算0 否 1 是")
    private Integer missDataReRun;
    @ApiModelProperty("value=上报时间")
    private String createTime;
    @ApiModelProperty("value=数据修改人")
    private String createBy;
    @ApiModelProperty("value=分中心（管理组织）ID")
    private Integer manageOrgId;
    @ApiModelProperty("value=分中心（管理组织）名称")
    private String manageOrgName;
    @ApiModelProperty("value=数据错误判断依据")
    private String brokenAccordingId;

    @ApiModelProperty("value=数据错误最后发生时间")
    private String errorLastestAppearTime;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getErrorLastestAppearTime() {
        return errorLastestAppearTime;
    }

    public void setErrorLastestAppearTime(String errorLastestAppearTime) {
        this.errorLastestAppearTime = errorLastestAppearTime;
    }

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

    public Integer getMissDataType() {
        return missDataType;
    }

    public void setMissDataType(Integer missDataType) {
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


}
