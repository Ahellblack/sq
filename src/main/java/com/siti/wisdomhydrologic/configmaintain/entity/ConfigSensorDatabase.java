package com.siti.wisdomhydrologic.configmaintain.entity;


public class ConfigSensorDatabase {

  private String propertyCode;
  private String sensorCode;
  private String sensorTypeId;
  private String sensorTypeName;
  private String sensorUseStatus;
  private String sensorLocation;
  private int createBy;
  private String createByName;
  private java.sql.Timestamp createTime;
  private int updateBy;
  private String updateByName;
  private java.sql.Timestamp updateTime;
  private int manageOrgId;
  private String manageOrgName;
  private String sensorModelType;
  private String subordinateCompany;
  private String remarks;

  @Override
  public String toString() {
    return "ConfigSensorDatabase{" + "propertyCode='" + propertyCode + '\'' + ", sensorCode='" + sensorCode + '\'' + ", sensorTypeId='" + sensorTypeId + '\'' + ", sensorTypeName='" + sensorTypeName + '\'' + ", sensorUseStatus='" + sensorUseStatus + '\'' + ", sensorLocation='" + sensorLocation + '\'' + ", createBy=" + createBy + ", createByName='" + createByName + '\'' + ", createTime=" + createTime + ", updateBy=" + updateBy + ", updateByName='" + updateByName + '\'' + ", updateTime=" + updateTime + ", manageOrgId=" + manageOrgId + ", manageOrgName='" + manageOrgName + '\'' + ", sensorModelType='" + sensorModelType + '\'' + ", subordinateCompany='" + subordinateCompany + '\'' + ", remarks='" + remarks + '\'' + '}';
  }

  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  public String getCreateByName() {
    return createByName;
  }

  public void setCreateByName(String createByName) {
    this.createByName = createByName;
  }

  public String getUpdateByName() {
    return updateByName;
  }

  public void setUpdateByName(String updateByName) {
    this.updateByName = updateByName;
  }

  public String getPropertyCode() {
    return propertyCode;
  }

  public void setPropertyCode(String propertyCode) {
    this.propertyCode = propertyCode;
  }


  public String getSensorCode() {
    return sensorCode;
  }

  public void setSensorCode(String sensorCode) {
    this.sensorCode = sensorCode;
  }


  public String getSensorTypeId() {
    return sensorTypeId;
  }

  public void setSensorTypeId(String sensorTypeId) {
    this.sensorTypeId = sensorTypeId;
  }


  public String getSensorTypeName() {
    return sensorTypeName;
  }

  public void setSensorTypeName(String sensorTypeName) {
    this.sensorTypeName = sensorTypeName;
  }


  public String getSensorUseStatus() {
    return sensorUseStatus;
  }

  public void setSensorUseStatus(String sensorUseStatus) {
    this.sensorUseStatus = sensorUseStatus;
  }


  public String getSensorLocation() {
    return sensorLocation;
  }

  public void setSensorLocation(String sensorLocation) {
    this.sensorLocation = sensorLocation;
  }


  public int getCreateBy() {
    return createBy;
  }

  public void setCreateBy(int createBy) {
    this.createBy = createBy;
  }


  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }


  public int getUpdateBy() {
    return updateBy;
  }

  public void setUpdateBy(int updateBy) {
    this.updateBy = updateBy;
  }


  public java.sql.Timestamp getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(java.sql.Timestamp updateTime) {
    this.updateTime = updateTime;
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


  public String getSensorModelType() {
    return sensorModelType;
  }

  public void setSensorModelType(String sensorModelType) {
    this.sensorModelType = sensorModelType;
  }


  public String getSubordinateCompany() {
    return subordinateCompany;
  }

  public void setSubordinateCompany(String subordinateCompany) {
    this.subordinateCompany = subordinateCompany;
  }

}
