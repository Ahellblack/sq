package com.siti.wisdomhydrologic.fundconfig.entity;


public class ConfigAbnormalDictionary {

  private String brokenAccordingId;
  private String brokenAccording;
  private String errorName;
  private long errorDataId;


  public String getBrokenAccordingId() {
    return brokenAccordingId;
  }

  public void setBrokenAccordingId(String brokenAccordingId) {
    this.brokenAccordingId = brokenAccordingId;
  }


  public String getBrokenAccording() {
    return brokenAccording;
  }

  public void setBrokenAccording(String brokenAccording) {
    this.brokenAccording = brokenAccording;
  }


  public String getErrorName() {
    return errorName;
  }

  public void setErrorName(String errorName) {
    this.errorName = errorName;
  }


  public long getErrorDataId() {
    return errorDataId;
  }

  public void setErrorDataId(long errorDataId) {
    this.errorDataId = errorDataId;
  }

}
