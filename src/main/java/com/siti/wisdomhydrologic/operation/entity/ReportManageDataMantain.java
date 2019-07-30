package com.siti.wisdomhydrologic.operation.entity;

import java.util.Date;

public class ReportManageDataMantain {

  private long reportId;
  private String stationCode;
  private String alterDate;
  private String stationName;
  private long alterSensorTypeId;
  private String alterSensorTypeName;
  private String errorDataReason;
  private long errorDataType;
  private String errorTimeSpace;
  private String errorValue;
  private String confirValue;
  private String errorUnit;
  private long errorDataReRun;
  private String missDataType;
  private String missTimeSpace;
  private long missDataReRun;
  private Date createTime;
  private long createBy;
  private long manageOrgId;
  private String manageOrgName;


  public long getReportId() {
    return reportId;
  }

  public void setReportId(long reportId) {
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


  public long getAlterSensorTypeId() {
    return alterSensorTypeId;
  }

  public void setAlterSensorTypeId(long alterSensorTypeId) {
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


  public long getErrorDataType() {
    return errorDataType;
  }

  public void setErrorDataType(long errorDataType) {
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


  public long getErrorDataReRun() {
    return errorDataReRun;
  }

  public void setErrorDataReRun(long errorDataReRun) {
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


  public long getMissDataReRun() {
    return missDataReRun;
  }

  public void setMissDataReRun(long missDataReRun) {
    this.missDataReRun = missDataReRun;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public long getCreateBy() {
    return createBy;
  }

  public void setCreateBy(long createBy) {
    this.createBy = createBy;
  }


  public long getManageOrgId() {
    return manageOrgId;
  }

  public void setManageOrgId(long manageOrgId) {
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
