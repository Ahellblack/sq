package com.siti.wisdomhydrologic.fundconfig.entity;


public class ConfigRiver {

  private long riverId;
  private String riverName;
  private String orgIds;
  private String orgNames;
  private String riverGaodePolygeom;
  private double riverCenterGaodeLongitude;
  private double riverCenterGaodeLatitude;
  private java.sql.Timestamp createTime;
  private long createBy;
  private java.sql.Timestamp updateTime;
  private long updateBy;


  public long getRiverId() {
    return riverId;
  }

  public void setRiverId(long riverId) {
    this.riverId = riverId;
  }


  public String getRiverName() {
    return riverName;
  }

  public void setRiverName(String riverName) {
    this.riverName = riverName;
  }


  public String getOrgIds() {
    return orgIds;
  }

  public void setOrgIds(String orgIds) {
    this.orgIds = orgIds;
  }


  public String getOrgNames() {
    return orgNames;
  }

  public void setOrgNames(String orgNames) {
    this.orgNames = orgNames;
  }


  public String getRiverGaodePolygeom() {
    return riverGaodePolygeom;
  }

  public void setRiverGaodePolygeom(String riverGaodePolygeom) {
    this.riverGaodePolygeom = riverGaodePolygeom;
  }


  public double getRiverCenterGaodeLongitude() {
    return riverCenterGaodeLongitude;
  }

  public void setRiverCenterGaodeLongitude(double riverCenterGaodeLongitude) {
    this.riverCenterGaodeLongitude = riverCenterGaodeLongitude;
  }


  public double getRiverCenterGaodeLatitude() {
    return riverCenterGaodeLatitude;
  }

  public void setRiverCenterGaodeLatitude(double riverCenterGaodeLatitude) {
    this.riverCenterGaodeLatitude = riverCenterGaodeLatitude;
  }


  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }


  public long getCreateBy() {
    return createBy;
  }

  public void setCreateBy(long createBy) {
    this.createBy = createBy;
  }


  public java.sql.Timestamp getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(java.sql.Timestamp updateTime) {
    this.updateTime = updateTime;
  }


  public long getUpdateBy() {
    return updateBy;
  }

  public void setUpdateBy(long updateBy) {
    this.updateBy = updateBy;
  }

}
