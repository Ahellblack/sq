package com.siti.wisdomhydrologic.operation.entity;

import java.util.Date;

public class ReportStationBroken {

  private long reportId;
  private String stationCode;
  private String stationName;
  private Date brokenHappenTime;
  private String brokenSituation;
  private Date brokenResolveCreateTime;
  private String resolveMethod;
  private String resolveUserIds;
  private String remark;
  private long createBy;
  private long manageOrgId;
  private String manageOrgName;
  private Date brokenResolveTime;
  private Date brokenResponseTime;
  private Date createTime;


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


  public String getStationName() {
    return stationName;
  }

  public void setStationName(String stationName) {
    this.stationName = stationName;
  }


  public Date getBrokenHappenTime() {
    return brokenHappenTime;
  }

  public void setBrokenHappenTime(Date brokenHappenTime) {
    this.brokenHappenTime = brokenHappenTime;
  }


  public String getBrokenSituation() {
    return brokenSituation;
  }

  public void setBrokenSituation(String brokenSituation) {
    this.brokenSituation = brokenSituation;
  }


  public Date getBrokenResolveCreateTime() {
    return brokenResolveCreateTime;
  }

  public void setBrokenResolveCreateTime(Date brokenResolveCreateTime) {
    this.brokenResolveCreateTime = brokenResolveCreateTime;
  }


  public String getResolveMethod() {
    return resolveMethod;
  }

  public void setResolveMethod(String resolveMethod) {
    this.resolveMethod = resolveMethod;
  }


  public String getResolveUserIds() {
    return resolveUserIds;
  }

  public void setResolveUserIds(String resolveUserIds) {
    this.resolveUserIds = resolveUserIds;
  }


  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
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


  public Date getBrokenResolveTime() {
    return brokenResolveTime;
  }

  public void setBrokenResolveTime(Date brokenResolveTime) {
    this.brokenResolveTime = brokenResolveTime;
  }


  public Date getBrokenResponseTime() {
    return brokenResponseTime;
  }

  public void setBrokenResponseTime(Date brokenResponseTime) {
    this.brokenResponseTime = brokenResponseTime;
  }


  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

}
