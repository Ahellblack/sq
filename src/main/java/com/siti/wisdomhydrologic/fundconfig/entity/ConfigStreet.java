package com.siti.wisdomhydrologic.fundconfig.entity;


public class ConfigStreet {

  private long streetId;
  private String streetName;
  private String streetPolygeom;
  private double streetCenterGaodeLongitude;
  private double streetCenterGaodeLatitude;
  private long createBy;
  private java.sql.Timestamp createTime;
  private long updateBy;
  private java.sql.Timestamp updateTime;
  private long sysOrgId;
  private String sysOrgName;


  public long getStreetId() {
    return streetId;
  }

  public void setStreetId(long streetId) {
    this.streetId = streetId;
  }


  public String getStreetName() {
    return streetName;
  }

  public void setStreetName(String streetName) {
    this.streetName = streetName;
  }


  public String getStreetPolygeom() {
    return streetPolygeom;
  }

  public void setStreetPolygeom(String streetPolygeom) {
    this.streetPolygeom = streetPolygeom;
  }


  public double getStreetCenterGaodeLongitude() {
    return streetCenterGaodeLongitude;
  }

  public void setStreetCenterGaodeLongitude(double streetCenterGaodeLongitude) {
    this.streetCenterGaodeLongitude = streetCenterGaodeLongitude;
  }


  public double getStreetCenterGaodeLatitude() {
    return streetCenterGaodeLatitude;
  }

  public void setStreetCenterGaodeLatitude(double streetCenterGaodeLatitude) {
    this.streetCenterGaodeLatitude = streetCenterGaodeLatitude;
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


  public long getSysOrgId() {
    return sysOrgId;
  }

  public void setSysOrgId(long sysOrgId) {
    this.sysOrgId = sysOrgId;
  }


  public String getSysOrgName() {
    return sysOrgName;
  }

  public void setSysOrgName(String sysOrgName) {
    this.sysOrgName = sysOrgName;
  }

}
