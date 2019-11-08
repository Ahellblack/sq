package com.siti.wisdomhydrologic.upload.entity;

import java.util.Date;

public class SysManualLog {

  private long id;
  private String message;
  private long finishStatus;
  private Date createTime;
  private String createBy;
  private Date updateTime;
  private int status;

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }


  public long getFinishStatus() {
    return finishStatus;
  }

  public void setFinishStatus(long finishStatus) {
    this.finishStatus = finishStatus;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public String getCreateBy() {
    return createBy;
  }

  public void setCreateBy(String createBy) {
    this.createBy = createBy;
  }

  public Date getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }
}
