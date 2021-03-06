package com.siti.wisdomhydrologic.upload.entity;

public class FileUploadInformation {

  private Integer fileId;
  private String originFileName;
  private String fileUrl;
  private Integer uploadUser;
  private String uploadTime;
  private String uploadIpAddress;


  public Integer getFileId() {
    return fileId;
  }

  public void setFileId(Integer fileId) {
    this.fileId = fileId;
  }


  public String getOriginFileName() {
    return originFileName;
  }

  public void setOriginFileName(String originFileName) {
    this.originFileName = originFileName;
  }

  public String getFileUrl() {
    return fileUrl;
  }

  public void setFileUrl(String fileUrl) {
    this.fileUrl = fileUrl;
  }

  public Integer getUploadUser() {
    return uploadUser;
  }

  public void setUploadUser(Integer uploadUser) {
    this.uploadUser = uploadUser;
  }

  public String getUploadTime() {
    return uploadTime;
  }

  public void setUploadTime(String uploadTime) {
    this.uploadTime = uploadTime;
  }

  public String getUploadIpAddress() {
    return uploadIpAddress;
  }

  public void setUploadIpAddress(String uploadIpAddress) {
    this.uploadIpAddress = uploadIpAddress;
  }

}
