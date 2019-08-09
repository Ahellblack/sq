package com.siti.wisdomhydrologic.operation.entity;

import java.util.Date;

public class ReportManageMantain {

  private int reportId;
  private int manageOrgId;
  private String manageOrgName;
  private String mantainMonth;
  private String mantainHour;
  private int tempHuimidityException;
  private int serverTimeException;
  private int databaseServerException;
  private int communicateServerException;
  private int applicationServerException;
  private int webServerException;
  private int changtongRateException;
  private int voltageException;
  private int voltageProcessLineException;
  private int dayRainReportException;
  private int rainBarException;
  private int daySeaLevelReportException;
  private int seaLeveProcessLineException;
  private int otherReportException;
  private String remark;
  private int createBy;
  private Date createTime;


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


  public String getManageOrgName() {
    return manageOrgName;
  }

  public void setManageOrgName(String manageOrgName) {
    this.manageOrgName = manageOrgName;
  }


  public String getMantainMonth() {
    return mantainMonth;
  }

  public void setMantainMonth(String mantainMonth) {
    this.mantainMonth = mantainMonth;
  }


  public String getMantainHour() {
    return mantainHour;
  }

  public void setMantainHour(String mantainHour) {
    this.mantainHour = mantainHour;
  }


  public int getTempHuimidityException() {
    return tempHuimidityException;
  }

  public void setTempHuimidityException(int tempHuimidityException) {
    this.tempHuimidityException = tempHuimidityException;
  }


  public int getServerTimeException() {
    return serverTimeException;
  }

  public void setServerTimeException(int serverTimeException) {
    this.serverTimeException = serverTimeException;
  }


  public int getDatabaseServerException() {
    return databaseServerException;
  }

  public void setDatabaseServerException(int databaseServerException) {
    this.databaseServerException = databaseServerException;
  }


  public int getCommunicateServerException() {
    return communicateServerException;
  }

  public void setCommunicateServerException(int communicateServerException) {
    this.communicateServerException = communicateServerException;
  }


  public int getApplicationServerException() {
    return applicationServerException;
  }

  public void setApplicationServerException(int applicationServerException) {
    this.applicationServerException = applicationServerException;
  }


  public int getWebServerException() {
    return webServerException;
  }

  public void setWebServerException(int webServerException) {
    this.webServerException = webServerException;
  }


  public int getChangtongRateException() {
    return changtongRateException;
  }

  public void setChangtongRateException(int changtongRateException) {
    this.changtongRateException = changtongRateException;
  }


  public int getVoltageException() {
    return voltageException;
  }

  public void setVoltageException(int voltageException) {
    this.voltageException = voltageException;
  }


  public int getVoltageProcessLineException() {
    return voltageProcessLineException;
  }

  public void setVoltageProcessLineException(int voltageProcessLineException) {
    this.voltageProcessLineException = voltageProcessLineException;
  }


  public int getDayRainReportException() {
    return dayRainReportException;
  }

  public void setDayRainReportException(int dayRainReportException) {
    this.dayRainReportException = dayRainReportException;
  }


  public int getRainBarException() {
    return rainBarException;
  }

  public void setRainBarException(int rainBarException) {
    this.rainBarException = rainBarException;
  }


  public int getDaySeaLevelReportException() {
    return daySeaLevelReportException;
  }

  public void setDaySeaLevelReportException(int daySeaLevelReportException) {
    this.daySeaLevelReportException = daySeaLevelReportException;
  }


  public int getSeaLeveProcessLineException() {
    return seaLeveProcessLineException;
  }

  public void setSeaLeveProcessLineException(int seaLeveProcessLineException) {
    this.seaLeveProcessLineException = seaLeveProcessLineException;
  }


  public int getOtherReportException() {
    return otherReportException;
  }

  public void setOtherReportException(int otherReportException) {
    this.otherReportException = otherReportException;
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


  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public Date getCreateTime() {
    return createTime;
  }

  @Override
  public String toString() {
    return "ReportManageMantain{" + "reportId=" + reportId + ", manageOrgId=" + manageOrgId + ", manageOrgName='" + manageOrgName + '\'' + ", mantainMonth='" + mantainMonth + '\'' + ", mantainHour='" + mantainHour + '\'' + ", tempHuimidityException=" + tempHuimidityException + ", serverTimeException=" + serverTimeException + ", databaseServerException=" + databaseServerException + ", communicateServerException=" + communicateServerException + ", applicationServerException=" + applicationServerException + ", webServerException=" + webServerException + ", changtongRateException=" + changtongRateException + ", voltageException=" + voltageException + ", voltageProcessLineException=" + voltageProcessLineException + ", dayRainReportException=" + dayRainReportException + ", rainBarException=" + rainBarException + ", daySeaLevelReportException=" + daySeaLevelReportException + ", seaLeveProcessLineException=" + seaLeveProcessLineException + ", otherReportException=" + otherReportException + ", remark='" + remark + '\'' + ", createBy=" + createBy + ", createTime=" + createTime + '}';
  }
}
