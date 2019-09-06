package com.siti.wisdomhydrologic.operation.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.util.Date;

public class ReportInspectionMaintenance {

  @Excel(name = "reportId", height = 11, width = 20)
  private int reportId;

  @Excel(name = "stationCode", height = 11, width = 20)
  private String stationCode;

  @Excel(name = "stationName", height = 11, width = 20)
  private String stationName;

  @Excel(name = "rtuType", height = 11, width = 20)
  private String rtuType;

  @Excel(name = "monitoringProgram", height = 11, width = 20)
  private String monitoringProgram;

  @Excel(name = "dataCollectionClock", height = 11, width = 20)
  private int dataCollectionClock;

  @Excel(name = "dataCollectionParamCheck", height = 11, width = 20)
  private int dataCollectionParamCheck;

  @Excel(name = "dataCollectionNormal", height = 11, width = 20)
  private int dataCollectionNormal;

  @Excel(name = "dataCommunicationParam", height = 11, width = 20)
  private int dataCommunicationParam;

  @Excel(name = "dataCommunicationChannel", height = 11, width = 20)
  private int dataCommunicationChannel;

  @Excel(name = "powerSolarCheck", height = 11, width = 20)
  private int powerSolarCheck;

  @Excel(name = "powerSolarVolt", height = 11, width = 20)
  private double powerSolarVolt;

  @Excel(name = "powerStorageVolt", height = 11, width = 20)
  private double powerStorageVolt;

  @Excel(name = "powerStorageCheck", height = 11, width = 20)
  private int powerStorageCheck;

  @Excel(name = "rainInstrumentCheck", height = 11, width = 20)
  private int rainInstrumentCheck;

  @Excel(name = "rainInstrumentCleanCheck", height = 11, width = 20)
  private int rainInstrumentCleanCheck;

  @Excel(name = "rainEnviromentCheck", height = 11, width = 20)
  private int rainEnviromentCheck;

  @Excel(name = "waterLevelInstrumentCheck", height = 11, width = 20)
  private int waterLevelInstrumentCheck;

  @Excel(name = "waterLevelShaftCheck", height = 11, width = 20)
  private int waterLevelShaftCheck;

  @Excel(name = "waterLevelStaffCheck", height = 11, width = 20)
  private int waterLevelStaffCheck;

  @Excel(name = "waterLevelStaffIsAdjust", height = 11, width = 20)
  private int waterLevelStaffIsAdjust;

  @Excel(name = "waterLevelZeroHeight", height = 11, width = 20)
  private double waterLevelZeroHeight;

  @Excel(name = "waterLevelStaffValue", height = 11, width = 20)
  private double waterLevelStaffValue;

  @Excel(name = "waterLevelTime", height = 11, width = 20)
  private Date waterLevelTime;

  @Excel(name = "waterLevelIndicatorValue", height = 11, width = 20)
  private double waterLevelIndicatorValue;

  @Excel(name = "waterLevelValue", height = 11, width = 20)
  private double waterLevelValue;

  @Excel(name = "windInstrumentCheck", height = 11, width = 20)
  private int windInstrumentCheck;

  @Excel(name = "windLightningProtect", height = 11, width = 20)
  private int windLightningProtect;

  @Excel(name = "flowIsCompletion", height = 11, width = 20)
  private int flowIsCompletion;

  @Excel(name = "flowPitch", height = 11, width = 20)
  private double flowPitch;

  @Excel(name = "flowRoll", height = 11, width = 20)
  private double flowRoll;

  @Excel(name = "flowEcho", height = 11, width = 20)
  private int flowEcho;

  @Excel(name = "flowTestTime", height = 11, width = 20)
  private int flowTestTime;

  @Excel(name = "pipeline", height = 11, width = 20)
  private int pipeline;

  @Excel(name = "stationEnviromentInstrument", height = 11, width = 20)
  private int stationEnviromentInstrument;

  @Excel(name = "stationIsClean", height = 11, width = 20)
  private int stationIsClean;

  @Excel(name = "remark", height = 11, width = 20)
  private String remark;

  @Excel(name = "maintainer", height = 11, width = 20)
  private String maintainer;

  @Excel(name = "yearMonthTime", height = 11, width = 20)
  private String yearMonthTime;


  public String getYearMonthTime() {
    return yearMonthTime;
  }

  public void setYearMonthTime(String yearMonthTime) {
    this.yearMonthTime = yearMonthTime;
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


  public String getRtuType() {
    return rtuType;
  }

  public void setRtuType(String rtuType) {
    this.rtuType = rtuType;
  }


  public String getMonitoringProgram() {
    return monitoringProgram;
  }

  public void setMonitoringProgram(String monitoringProgram) {
    this.monitoringProgram = monitoringProgram;
  }


  public int getDataCollectionClock() {
    return dataCollectionClock;
  }

  public void setDataCollectionClock(int dataCollectionClock) {
    this.dataCollectionClock = dataCollectionClock;
  }


  public int getDataCollectionParamCheck() {
    return dataCollectionParamCheck;
  }

  public void setDataCollectionParamCheck(int dataCollectionParamCheck) {
    this.dataCollectionParamCheck = dataCollectionParamCheck;
  }


  public int getDataCollectionNormal() {
    return dataCollectionNormal;
  }

  public void setDataCollectionNormal(int dataCollectionNormal) {
    this.dataCollectionNormal = dataCollectionNormal;
  }


  public int getDataCommunicationParam() {
    return dataCommunicationParam;
  }

  public void setDataCommunicationParam(int dataCommunicationParam) {
    this.dataCommunicationParam = dataCommunicationParam;
  }


  public int getDataCommunicationChannel() {
    return dataCommunicationChannel;
  }

  public void setDataCommunicationChannel(int dataCommunicationChannel) {
    this.dataCommunicationChannel = dataCommunicationChannel;
  }


  public int getPowerSolarCheck() {
    return powerSolarCheck;
  }

  public void setPowerSolarCheck(int powerSolarCheck) {
    this.powerSolarCheck = powerSolarCheck;
  }


  public double getPowerSolarVolt() {
    return powerSolarVolt;
  }

  public void setPowerSolarVolt(double powerSolarVolt) {
    this.powerSolarVolt = powerSolarVolt;
  }


  public double getPowerStorageVolt() {
    return powerStorageVolt;
  }

  public void setPowerStorageVolt(double powerStorageVolt) {
    this.powerStorageVolt = powerStorageVolt;
  }


  public int getPowerStorageCheck() {
    return powerStorageCheck;
  }

  public void setPowerStorageCheck(int powerStorageCheck) {
    this.powerStorageCheck = powerStorageCheck;
  }


  public int getRainInstrumentCheck() {
    return rainInstrumentCheck;
  }

  public void setRainInstrumentCheck(int rainInstrumentCheck) {
    this.rainInstrumentCheck = rainInstrumentCheck;
  }


  public int getRainInstrumentCleanCheck() {
    return rainInstrumentCleanCheck;
  }

  public void setRainInstrumentCleanCheck(int rainInstrumentCleanCheck) {
    this.rainInstrumentCleanCheck = rainInstrumentCleanCheck;
  }


  public int getRainEnviromentCheck() {
    return rainEnviromentCheck;
  }

  public void setRainEnviromentCheck(int rainEnviromentCheck) {
    this.rainEnviromentCheck = rainEnviromentCheck;
  }


  public int getWaterLevelInstrumentCheck() {
    return waterLevelInstrumentCheck;
  }

  public void setWaterLevelInstrumentCheck(int waterLevelInstrumentCheck) {
    this.waterLevelInstrumentCheck = waterLevelInstrumentCheck;
  }


  public int getWaterLevelShaftCheck() {
    return waterLevelShaftCheck;
  }

  public void setWaterLevelShaftCheck(int waterLevelShaftCheck) {
    this.waterLevelShaftCheck = waterLevelShaftCheck;
  }


  public int getWaterLevelStaffCheck() {
    return waterLevelStaffCheck;
  }

  public void setWaterLevelStaffCheck(int waterLevelStaffCheck) {
    this.waterLevelStaffCheck = waterLevelStaffCheck;
  }


  public int getWaterLevelStaffIsAdjust() {
    return waterLevelStaffIsAdjust;
  }

  public void setWaterLevelStaffIsAdjust(int waterLevelStaffIsAdjust) {
    this.waterLevelStaffIsAdjust = waterLevelStaffIsAdjust;
  }


  public double getWaterLevelZeroHeight() {
    return waterLevelZeroHeight;
  }

  public void setWaterLevelZeroHeight(double waterLevelZeroHeight) {
    this.waterLevelZeroHeight = waterLevelZeroHeight;
  }


  public double getWaterLevelStaffValue() {
    return waterLevelStaffValue;
  }

  public void setWaterLevelStaffValue(double waterLevelStaffValue) {
    this.waterLevelStaffValue = waterLevelStaffValue;
  }

  public Date getWaterLevelTime() {
    return waterLevelTime;
  }

  public void setWaterLevelTime(Date waterLevelTime) {
    this.waterLevelTime = waterLevelTime;
  }

  public double getWaterLevelIndicatorValue() {
    return waterLevelIndicatorValue;
  }

  public void setWaterLevelIndicatorValue(double waterLevelIndicatorValue) {
    this.waterLevelIndicatorValue = waterLevelIndicatorValue;
  }


  public double getWaterLevelValue() {
    return waterLevelValue;
  }

  public void setWaterLevelValue(double waterLevelValue) {
    this.waterLevelValue = waterLevelValue;
  }


  public int getWindInstrumentCheck() {
    return windInstrumentCheck;
  }

  public void setWindInstrumentCheck(int windInstrumentCheck) {
    this.windInstrumentCheck = windInstrumentCheck;
  }


  public int getWindLightningProtect() {
    return windLightningProtect;
  }

  public void setWindLightningProtect(int windLightningProtect) {
    this.windLightningProtect = windLightningProtect;
  }


  public int getFlowIsCompletion() {
    return flowIsCompletion;
  }

  public void setFlowIsCompletion(int flowIsCompletion) {
    this.flowIsCompletion = flowIsCompletion;
  }


  public double getFlowPitch() {
    return flowPitch;
  }

  public void setFlowPitch(double flowPitch) {
    this.flowPitch = flowPitch;
  }


  public double getFlowRoll() {
    return flowRoll;
  }

  public void setFlowRoll(double flowRoll) {
    this.flowRoll = flowRoll;
  }


  public int getFlowEcho() {
    return flowEcho;
  }

  public void setFlowEcho(int flowEcho) {
    this.flowEcho = flowEcho;
  }


  public int getFlowTestTime() {
    return flowTestTime;
  }

  public void setFlowTestTime(int flowTestTime) {
    this.flowTestTime = flowTestTime;
  }


  public int getPipeline() {
    return pipeline;
  }

  public void setPipeline(int pipeline) {
    this.pipeline = pipeline;
  }


  public int getStationEnviromentInstrument() {
    return stationEnviromentInstrument;
  }

  public void setStationEnviromentInstrument(int stationEnviromentInstrument) {
    this.stationEnviromentInstrument = stationEnviromentInstrument;
  }


  public int getStationIsClean() {
    return stationIsClean;
  }

  public void setStationIsClean(int stationIsClean) {
    this.stationIsClean = stationIsClean;
  }


  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }


  public String getMaintainer() {
    return maintainer;
  }

  public void setMaintainer(String maintainer) {
    this.maintainer = maintainer;
  }

  @Override
  public String toString() {
    return "ReportInspectionMaintenance{" + "reportId=" + reportId + ", stationCode='" + stationCode + '\'' + ", stationName='" + stationName + '\'' + ", rtuType='" + rtuType + '\'' + ", monitoringProgram='" + monitoringProgram + '\'' + ", dataCollectionClock=" + dataCollectionClock + ", dataCollectionParamCheck=" + dataCollectionParamCheck + ", dataCollectionNormal=" + dataCollectionNormal + ", dataCommunicationParam=" + dataCommunicationParam + ", dataCommunicationChannel=" + dataCommunicationChannel + ", powerSolarCheck=" + powerSolarCheck + ", powerSolarVolt=" + powerSolarVolt + ", powerStorageVolt=" + powerStorageVolt + ", powerStorageCheck=" + powerStorageCheck + ", rainInstrumentCheck=" + rainInstrumentCheck + ", rainInstrumentCleanCheck=" + rainInstrumentCleanCheck + ", rainEnviromentCheck=" + rainEnviromentCheck + ", waterLevelInstrumentCheck=" + waterLevelInstrumentCheck + ", waterLevelShaftCheck=" + waterLevelShaftCheck + ", waterLevelStaffCheck=" + waterLevelStaffCheck + ", waterLevelStaffIsAdjust=" + waterLevelStaffIsAdjust + ", waterLevelZeroHeight=" + waterLevelZeroHeight + ", waterLevelStaffValue=" + waterLevelStaffValue + ", waterLevelTime=" + waterLevelTime + ", waterLevelIndicatorValue=" + waterLevelIndicatorValue + ", waterLevelValue=" + waterLevelValue + ", windInstrumentCheck=" + windInstrumentCheck + ", windLightningProtect=" + windLightningProtect + ", flowIsCompletion=" + flowIsCompletion + ", flowPitch=" + flowPitch + ", flowRoll=" + flowRoll + ", flowEcho=" + flowEcho + ", flowTestTime=" + flowTestTime + ", pipeline=" + pipeline + ", stationEnviromentInstrument=" + stationEnviromentInstrument + ", stationIsClean=" + stationIsClean + ", remark='" + remark + '\'' + ", maintainer='" + maintainer + '\'' + ", yearMonthTime='" + yearMonthTime + '\'' + '}';
  }
}
