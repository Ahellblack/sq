package com.siti.wisdomhydrologic.fundconfig.entity;


public class ConfigRegion {

  private long regionId;
  private String regionName;
  private long manageOrgId;
  private String manageOrgName;
  private long regionTypeId;
  private String regionTypeName;
  private String regionPolygeom;
  private double regionCenterGaodeLongitude;
  private double regionCenterGaodeLatitude;
  private long mantainOrgId;
  private String mantainOrgName;
  private long createBy;
  private java.sql.Timestamp createTime;
  private long updateBy;
  private java.sql.Timestamp updateTime;


  public long getRegionId() {
    return regionId;
  }

  public void setRegionId(long regionId) {
    this.regionId = regionId;
  }


  public String getRegionName() {
    return regionName;
  }

  public void setRegionName(String regionName) {
    this.regionName = regionName;
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


  public long getRegionTypeId() {
    return regionTypeId;
  }

  public void setRegionTypeId(long regionTypeId) {
    this.regionTypeId = regionTypeId;
  }


  public String getRegionTypeName() {
    return regionTypeName;
  }

  public void setRegionTypeName(String regionTypeName) {
    this.regionTypeName = regionTypeName;
  }


  public String getRegionPolygeom() {
    return regionPolygeom;
  }

  public void setRegionPolygeom(String regionPolygeom) {
    this.regionPolygeom = regionPolygeom;
  }


  public double getRegionCenterGaodeLongitude() {
    return regionCenterGaodeLongitude;
  }

  public void setRegionCenterGaodeLongitude(double regionCenterGaodeLongitude) {
    this.regionCenterGaodeLongitude = regionCenterGaodeLongitude;
  }


  public double getRegionCenterGaodeLatitude() {
    return regionCenterGaodeLatitude;
  }

  public void setRegionCenterGaodeLatitude(double regionCenterGaodeLatitude) {
    this.regionCenterGaodeLatitude = regionCenterGaodeLatitude;
  }


  public long getMantainOrgId() {
    return mantainOrgId;
  }

  public void setMantainOrgId(long mantainOrgId) {
    this.mantainOrgId = mantainOrgId;
  }


  public String getMantainOrgName() {
    return mantainOrgName;
  }

  public void setMantainOrgName(String mantainOrgName) {
    this.mantainOrgName = mantainOrgName;
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
