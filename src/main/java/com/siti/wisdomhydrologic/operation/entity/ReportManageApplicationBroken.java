package com.siti.wisdomhydrologic.operation.entity;

import java.util.Date;

public class ReportManageApplicationBroken {

  private int reportId;
  private int manageOrgId;
  private int manageOrgName;
  private int brokenType;
  private String brokenName;
  private String brokenSituation;
  private Date brokenResponseTime;
  private Date brokenResolveTime;
  private Date createTime;
  private String resovleMethod;
  private int resovleUserId;
  private String remark;


  public int getReportId() {
    return reportId;
  }

  public void setReportId(int reportId) {
    this.reportId = reportId;
  }


  public int getManageOrgId() {
    return manageOrgId;
  }

  public void setManageOrgId(int manageOrgId) {
    this.manageOrgId = manageOrgId;
  }


  public int getManageOrgName() {
    return manageOrgName;
  }

  public void setManageOrgName(int manageOrgName) {
    this.manageOrgName = manageOrgName;
  }


  public int getBrokenType() {
    return brokenType;
  }

  public void setBrokenType(int brokenType) {
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


  public int getResovleUserId() {
    return resovleUserId;
  }

  public void setResovleUserId(int resovleUserId) {
    this.resovleUserId = resovleUserId;
  }


  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

}
