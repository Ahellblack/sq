package com.siti.wisdomhydrologic.fundconfig.entity;


public class ConfigSensorType {

  private long sensorTypeId;
  private String sensorTypeName;
  private String sensorDataUnit;
  private long createBy;
  private java.sql.Timestamp createTime;
  private long updateBy;
  private java.sql.Timestamp updateTime;


  public long getSensorTypeId() {
    return sensorTypeId;
  }

  public void setSensorTypeId(long sensorTypeId) {
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


  public long getCreateBy() {
    return createBy;
  }

  public void setCreateBy(long createBy) {
    this.createBy = createBy;
  }


  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }


  public long getUpdateBy() {
    return updateBy;
  }

  public void setUpdateBy(long updateBy) {
    this.updateBy = updateBy;
  }


  public java.sql.Timestamp getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(java.sql.Timestamp updateTime) {
    this.updateTime = updateTime;
  }

}
