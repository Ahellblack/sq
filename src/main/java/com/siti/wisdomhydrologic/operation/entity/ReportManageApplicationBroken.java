package com.siti.wisdomhydrologic.operation.entity;

import java.util.Date;

public class ReportManageApplicationBroken {

  private long reportId;
  private long manageOrgId;
  private long manageOrgName;
  private long brokenType;
  private String brokenName;
  private String brokenSituation;
  private Date brokenResponseTime;
  private Date brokenResolveTime;
  private Date createTime;
  private String resovleMethod;
  private long resovleUserId;
  private String remark;


  public long getReportId() {
    return reportId;
  }

  public void setReportId(long reportId) {
    this.reportId = reportId;
  }


  public long getManageOrgId() {
    return manageOrgId;
  }

  public void setManageOrgId(long manageOrgId) {
    this.manageOrgId = manageOrgId;
  }


  public long getManageOrgName() {
    return manageOrgName;
  }

  public void setManageOrgName(long manageOrgName) {
    this.manageOrgName = manageOrgName;
  }


  public long getBrokenType() {
    return brokenType;
  }

  public void setBrokenType(long brokenType) {
    this.brokenType = brokenType;
  }


  public String getBrokenName() {
    return brokenName;
  }

  public void setBrokenName(String brokenName) {
    this.brokenName = brokenName;
  }


  public String getBrokenSituation() {
    return brokenSituation;
  }

  public void setBrokenSituation(String brokenSituation) {
    this.brokenSituation = brokenSituation;
  }


  public Date getBrokenResponseTime() {
    return brokenResponseTime;
  }

  public void setBrokenResponseTime(Date brokenResponseTime) {
    this.brokenResponseTime = brokenResponseTime;
  }


  public Date getBrokenResolveTime() {
    return brokenResolveTime;
  }

  public void setBrokenResolveTime(Date brokenResolveTime) {
    this.brokenResolveTime = brokenResolveTime;
  }


  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }


  public String getResovleMethod() {
    return resovleMethod;
  }

  public void setResovleMethod(String resovleMethod) {
    this.resovleMethod = resovleMethod;
  }


  public long getResovleUserId() {
    return resovleUserId;
  }

  public void setResovleUserId(long resovleUserId) {
    this.resovleUserId = resovleUserId;
  }


  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

}
