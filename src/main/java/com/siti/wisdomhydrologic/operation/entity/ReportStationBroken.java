package com.siti.wisdomhydrologic.operation.entity;


import cn.afterturn.easypoi.excel.annotation.Excel;


/**
 * 表三
 * */
public class ReportStationBroken {

  @Excel(name = "reportId", height = 11, width = 10)
  private int reportId;

  @Excel(name = "stationCode", height = 11, width = 10)
  private String stationCode;

  @Excel(name = "stationName", height = 11, width = 10)
  private String stationName;

  @Excel(name = "brokenHappenTime", height = 11, width = 10)
  private String brokenHappenTime;

  @Excel(name = "brokenSituation", height = 11, width = 10)
  private String brokenSituation;

  @Excel(name = "brokenResolveCreateTime", height = 11, width = 10)
  private String brokenResolveCreateTime;

  @Excel(name = "resolveMethod", height = 11, width = 10)
  private String resolveMethod;

  @Excel(name = "resolveUserIds", height = 11, width = 10)
  private String resolveUserIds;

  @Excel(name = "remark", height = 11, width = 10)
  private String remark;

  @Excel(name = "createBy", height = 11, width = 10)
  private String createBy;

  @Excel(name = "manageOrgId", height = 11, width = 10)
  private int manageOrgId;

  @Excel(name = "manageOrgName", height = 11, width = 10)
  private String manageOrgName;

  @Excel(name = "brokenResolveTime", height = 11, width = 10)
  private String brokenResolveTime;

  @Excel(name = "brokenResponseTime", height = 11, width = 10)
  private String brokenResponseTime;

  @Excel(name = "createTime", height = 11, width = 10)
  private String createTime;

  private String applicationEquipName;

  private String applicationEquipTypeId;

  public String getApplicationEquipTypeId() {
    return applicationEquipTypeId;
  }

  public void setApplicationEquipTypeId(String applicationEquipTypeId) {
    this.applicationEquipTypeId = applicationEquipTypeId;
  }

  public String getApplicationEquipName() {
    return applicationEquipName;
  }

  public void setApplicationEquipName(String applicationEquipName) {
    this.applicationEquipName = applicationEquipName;
  }

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

  public String getCreateBy() {
    return createBy;
  }

  public void setCreateBy(String createBy) {
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
