package com.siti.wisdomhydrologic.operation.entity;

public class ReportManageMantain {

  private long reportId;
  private long manageOrgId;
  private String manageOrgName;
  private String mantainMonth;
  private String mantainHour;
  private long tempHuimidityException;
  private long serverTimeException;
  private long databaseServerException;
  private long communicateServerException;
  private long applicationServerException;
  private long webServerException;
  private long changtongRateException;
  private long voltageException;
  private long voltageProcessLineException;
  private long dayRainReportException;
  private long rainBarException;
  private long daySeaLevelReportException;
  private long seaLeveProcessLineException;
  private long otherReportException;
  private String remark;
  private long createBy;
  private java.sql.Timestamp createTime;


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


  public long getTempHuimidityException() {
    return tempHuimidityException;
  }

  public void setTempHuimidityException(long tempHuimidityException) {
    this.tempHuimidityException = tempHuimidityException;
  }


  public long getServerTimeException() {
    return serverTimeException;
  }

  public void setServerTimeException(long serverTimeException) {
    this.serverTimeException = serverTimeException;
  }


  public long getDatabaseServerException() {
    return databaseServerException;
  }

  public void setDatabaseServerException(long databaseServerException) {
    this.databaseServerException = databaseServerException;
  }


  public long getCommunicateServerException() {
    return communicateServerException;
  }

  public void setCommunicateServerException(long communicateServerException) {
    this.communicateServerException = communicateServerException;
  }


  public long getApplicationServerException() {
    return applicationServerException;
  }

  public void setApplicationServerException(long applicationServerException) {
    this.applicationServerException = applicationServerException;
  }


  public long getWebServerException() {
    return webServerException;
  }

  public void setWebServerException(long webServerException) {
    this.webServerException = webServerException;
  }


  public long getChangtongRateException() {
    return changtongRateException;
  }

  public void setChangtongRateException(long changtongRateException) {
    this.changtongRateException = changtongRateException;
  }


  public long getVoltageException() {
    return voltageException;
  }

  public void setVoltageException(long voltageException) {
    this.voltageException = voltageException;
  }


  public long getVoltageProcessLineException() {
    return voltageProcessLineException;
  }

  public void setVoltageProcessLineException(long voltageProcessLineException) {
    this.voltageProcessLineException = voltageProcessLineException;
  }


  public long getDayRainReportException() {
    return dayRainReportException;
  }

  public void setDayRainReportException(long dayRainReportException) {
    this.dayRainReportException = dayRainReportException;
  }


  public long getRainBarException() {
    return rainBarException;
  }

  public void setRainBarException(long rainBarException) {
    this.rainBarException = rainBarException;
  }


  public long getDaySeaLevelReportException() {
    return daySeaLevelReportException;
  }

  public void setDaySeaLevelReportException(long daySeaLevelReportException) {
    this.daySeaLevelReportException = daySeaLevelReportException;
  }


  public long getSeaLeveProcessLineException() {
    return seaLeveProcessLineException;
  }

  public void setSeaLeveProcessLineException(long seaLeveProcessLineException) {
    this.seaLeveProcessLineException = seaLeveProcessLineException;
  }


  public long getOtherReportException() {
    return otherReportException;
  }

  public void setOtherReportException(long otherReportException) {
    this.otherReportException = otherReportException;
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


  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }

  @Override
  public String toString() {
    return "ReportManageMantain{" + "reportId=" + reportId + ", manageOrgId=" + manageOrgId + ", manageOrgName='" + manageOrgName + '\'' + ", mantainMonth='" + mantainMonth + '\'' + ", mantainHour='" + mantainHour + '\'' + ", tempHuimidityException=" + tempHuimidityException + ", serverTimeException=" + serverTimeException + ", databaseServerException=" + databaseServerException + ", communicateServerException=" + communicateServerException + ", applicationServerException=" + applicationServerException + ", webServerException=" + webServerException + ", changtongRateException=" + changtongRateException + ", voltageException=" + voltageException + ", voltageProcessLineException=" + voltageProcessLineException + ", dayRainReportException=" + dayRainReportException + ", rainBarException=" + rainBarException + ", daySeaLevelReportException=" + daySeaLevelReportException + ", seaLeveProcessLineException=" + seaLeveProcessLineException + ", otherReportException=" + otherReportException + ", remark='" + remark + '\'' + ", createBy=" + createBy + ", createTime=" + createTime + '}';
  }
}
