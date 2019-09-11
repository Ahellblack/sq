package com.siti.wisdomhydrologic.maintainconfig.entity;

import java.util.Date;

public class ConfigSensorDatabase {

  private int propertyCode;
  private String sensorCode;
  private int sensorTypeId;
  private String sensorTypeName;
  private String sensorDataUnit;
  private int sensorUseStatus;
  private int createBy;
  private Date createTime;
  private int updateBy;
  private Date updateTime;
  private int manageOrgId;
  private String manageOrgName;
  private String sensorModelType;
  private String subordinateCompany;

  public String getSubordinateCompany() {
    return subordinateCompany;
  }

  public void setSubordinateCompany(String subordinateCompany) {
    this.subordinateCompany = subordinateCompany;
  }

  public int getPropertyCode() {
    return propertyCode;
  }

  public void setPropertyCode(int propertyCode) {
    this.propertyCode = propertyCode;
  }


  public String getSensorCode() {
    return sensorCode;
  }

  public void setSensorCode(String sensorCode) {
    this.sensorCode = sensorCode;
  }


  public int getSensorTypeId() {
    return sensorTypeId;
  }

  public void setSensorTypeId(int sensorTypeId) {
    this.sensorTypeId = sensorTypeId;
  }


  public String getSensorTypeName() {
    return sensorTypeName;
  }

  public void setSensorTypeName(String sensorTypeName) {
    this.sensorTypeName = sensorTypeName;
  }


  public String getSensorDataUnit() {
    return sensorDataUnit;
  }

  public void setSensorDataUnit(String sensorDataUnit) {
    this.sensorDataUnit = sensorDataUnit;
  }


  public int getSensorUseStatus() {
    return sensorUseStatus;
  }

  public void setSensorUseStatus(int sensorUseStatus) {
    this.sensorUseStatus = sensorUseStatus;
  }


  public int getCreateBy() {
    return createBy;
  }

  public void setCreateBy(int createBy) {
    this.createBy = createBy;
  }


  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }


  public int getUpdateBy() {
    return updateBy;
  }

  public void setUpdateBy(int updateBy) {
    this.updateBy = updateBy;
  }


  public Date getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Date updateTime) {
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

}
