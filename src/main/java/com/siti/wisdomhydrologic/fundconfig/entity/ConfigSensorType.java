package com.siti.wisdomhydrologic.fundconfig.entity;


public class ConfigSensorType {

  private String sensorTypeId;
  private String sensorTypeName;
  private String sensorDataUnit;
  private String otherName;
  private int createBy;
  private java.sql.Timestamp createTime;
  private int updateBy;
  private java.sql.Timestamp updateTime;


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


  public String getSensorDataUnit() {
    return sensorDataUnit;
  }

  public void setSensorDataUnit(String sensorDataUnit) {
    this.sensorDataUnit = sensorDataUnit;
  }


  public String getOtherName() {
    return otherName;
  }

  public void setOtherName(String otherName) {
    this.otherName = otherName;
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

}
