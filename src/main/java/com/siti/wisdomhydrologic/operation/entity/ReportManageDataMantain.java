package com.siti.wisdomhydrologic.operation.entity;

import java.util.Date;

public class ReportManageDataMantain {

  private Integer reportId;
  private String stationCode;
  private String alterDate;
  private String stationName;
  private Integer alterSensorTypeId;
  private String alterSensorTypeName;
  private String errorDataReason;
  private Integer errorDataType;
  private String errorTimeSpace;
  private String errorValue;
  private String confirValue;
  private String errorUnit;
  private Integer errorDataReRun;
  private String missDataType;
  private String missTimeSpace;
  private Integer missDataReRun;
  private Date createTime;
  private Integer createBy;
  private Integer manageOrgId;
  private String manageOrgName;

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

  public String getAlterDate() {
    return alterDate;
  }

  public void setAlterDate(String alterDate) {
    this.alterDate = alterDate;
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

  public Integer getCreateBy() {
    return createBy;
  }

  public void setCreateBy(Integer createBy) {
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
    return "ReportManageDataMantain{" + "reportId=" + reportId + ", stationCode='" + stationCode + '\'' + ", alterDate='" + alterDate + '\'' + ", stationName='" + stationName + '\'' + ", alterSensorTypeId=" + alterSensorTypeId + ", alterSensorTypeName='" + alterSensorTypeName + '\'' + ", errorDataReason='" + errorDataReason + '\'' + ", errorDataType=" + errorDataType + ", errorTimeSpace='" + errorTimeSpace + '\'' + ", errorValue='" + errorValue + '\'' + ", confirValue='" + confirValue + '\'' + ", errorUnit='" + errorUnit + '\'' + ", errorDataReRun=" + errorDataReRun + ", missDataType='" + missDataType + '\'' + ", missTimeSpace='" + missTimeSpace + '\'' + ", missDataReRun=" + missDataReRun + ", createTime=" + createTime + ", createBy=" + createBy + ", manageOrgId=" + manageOrgId + ", manageOrgName='" + manageOrgName + '\'' + '}';
  }
}
