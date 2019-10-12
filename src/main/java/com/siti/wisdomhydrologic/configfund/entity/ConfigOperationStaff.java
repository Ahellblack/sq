package com.siti.wisdomhydrologic.configfund.entity;


public class ConfigOperationStaff {

  private long operationStaffId;
  private String operationStaffName;
  private String operationStaffNumber;
  private long regionId;
  private String regionName;
  private long onGuard;


  public long getOperationStaffId() {
    return operationStaffId;
  }

  public void setOperationStaffId(long operationStaffId) {
    this.operationStaffId = operationStaffId;
  }


  public String getOperationStaffName() {
    return operationStaffName;
  }

  public void setOperationStaffName(String operationStaffName) {
    this.operationStaffName = operationStaffName;
  }


  public String getOperationStaffNumber() {
    return operationStaffNumber;
  }

  public void setOperationStaffNumber(String operationStaffNumber) {
    this.operationStaffNumber = operationStaffNumber;
  }


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


  public long getOnGuard() {
    return onGuard;
  }

  public void setOnGuard(long onGuard) {
    this.onGuard = onGuard;
  }

}
