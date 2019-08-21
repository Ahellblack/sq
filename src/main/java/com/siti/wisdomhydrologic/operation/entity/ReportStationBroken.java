package com.siti.wisdomhydrologic.operation.entity;

import java.util.Date;

public class ReportStationBroken {

  private int reportId;
  private String stationCode;
  private String stationName;
  private String brokenHappenTime;
  private String brokenSituation;
  private String brokenResolveCreateTime;
  private String resolveMethod;
  private String resolveUserIds;
  private String remark;
  private int createBy;
  private int manageOrgId;
  private String manageOrgName;
  private String brokenResolveTime;
  private String brokenResponseTime;
  private String createTime;

  public int getReportId() {
    return reportId;
  }

  public void setReportId(int reportId) {
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

  public String getBrokenHappenTime() {
    return brokenHappenTime;
  }

  public void setBrokenHappenTime(String brokenHappenTime) {
    this.brokenHappenTime = brokenHappenTime;
  }

  public String getBrokenSituation() {
    return brokenSituation;
  }

  public void setBrokenSituation(String brokenSituation) {
    this.brokenSituation = brokenSituation;
  }

  public String getBrokenResolveCreateTime() {
    return brokenResolveCreateTime;
  }

  public void setBrokenResolveCreateTime(String brokenResolveCreateTime) {
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

  public int getCreateBy() {
    return createBy;
  }

  public void setCreateBy(int createBy) {
    this.createBy = createBy;
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

  public String getBrokenResolveTime() {
    return brokenResolveTime;
  }

  public void setBrokenResolveTime(String brokenResolveTime) {
    this.brokenResolveTime = brokenResolveTime;
  }

  public String getBrokenResponseTime() {
    return brokenResponseTime;
  }

  public void setBrokenResponseTime(String brokenResponseTime) {
    this.brokenResponseTime = brokenResponseTime;
  }

  public String getCreateTime() {
    return createTime;
  }

  public void setCreateTime(String createTime) {
    this.createTime = createTime;
  }

  @Override
  public String toString() {
    return "ReportStationBroken{" + "reportId=" + reportId + ", stationCode='" + stationCode + '\'' + ", stationName='" + stationName + '\'' + ", brokenHappenTime='" + brokenHappenTime + '\'' + ", brokenSituation='" + brokenSituation + '\'' + ", brokenResolveCreateTime='" + brokenResolveCreateTime + '\'' + ", resolveMethod='" + resolveMethod + '\'' + ", resolveUserIds='" + resolveUserIds + '\'' + ", remark='" + remark + '\'' + ", createBy=" + createBy + ", manageOrgId=" + manageOrgId + ", manageOrgName='" + manageOrgName + '\'' + ", brokenResolveTime='" + brokenResolveTime + '\'' + ", brokenResponseTime='" + brokenResponseTime + '\'' + ", createTime='" + createTime + '\'' + '}';
  }
}
