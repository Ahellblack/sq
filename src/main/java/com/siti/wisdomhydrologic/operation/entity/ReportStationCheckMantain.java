package com.siti.wisdomhydrologic.operation.entity;

public class ReportStationCheckMantain {

  private long reportId;
  private String mantainDate;
  private String stationCode;
  private String stationName;
  private long stationManangeOrgId;
  private String stationManageOrgName;
  private String rtuType;
  private long checkRainSensor;
  private long checkWaterLevelSensor;
  private long checkSpeedDirectionSensor;
  private long checkFlowSensor;
  private String checkOtherSensor;
  private long dataCollectionCorrect;
  private long dataCollectionParameterCheck;
  private long dataCollectionNormal;
  private long dataCommunicateParameterCheck;
  private long dataCommunicateChannel;
  private long solarEnergyVoltageCheck;
  private double solarEnergyVoltageValue;
  private long storageBatteryVoltageCheck;
  private double storageBatteryValue;
  private long rainfallSensorNormal;
  private long rainfallSensorCleanCheck;
  private long rainfallSensorRangeCheck;
  private long waterLevelCheckNormal;
  private long waterLeverCheckShaft;
  private long waterLeverCleanCheck;
  private long waterLeverAdjust;
  private long speedDirectionCheckNormal;
  private long speedDirectionCheckLightingProtection;
  private long flowmeterDataCheckNormal;
  private double flowmeterPitchGesture;
  private double flowmeterRollingGesture;
  private long flowmeterSignalStrength;
  private long flowmeterRecordTimeWarp;
  private long linePipeCheckNormal;
  private long stationEnviroment;
  private long stationCleanCheck;
  private String remark;
  private long createBy;
  private java.sql.Timestamp createTime;


  public long getReportId() {
    return reportId;
  }

  public void setReportId(long reportId) {
    this.reportId = reportId;
  }


  public String getMantainDate() {
    return mantainDate;
  }

  public void setMantainDate(String mantainDate) {
    this.mantainDate = mantainDate;
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


  public long getStationManangeOrgId() {
    return stationManangeOrgId;
  }

  public void setStationManangeOrgId(long stationManangeOrgId) {
    this.stationManangeOrgId = stationManangeOrgId;
  }


  public String getStationManageOrgName() {
    return stationManageOrgName;
  }

  public void setStationManageOrgName(String stationManageOrgName) {
    this.stationManageOrgName = stationManageOrgName;
  }


  public String getRtuType() {
    return rtuType;
  }

  public void setRtuType(String rtuType) {
    this.rtuType = rtuType;
  }


  public long getCheckRainSensor() {
    return checkRainSensor;
  }

  public void setCheckRainSensor(long checkRainSensor) {
    this.checkRainSensor = checkRainSensor;
  }


  public long getCheckWaterLevelSensor() {
    return checkWaterLevelSensor;
  }

  public void setCheckWaterLevelSensor(long checkWaterLevelSensor) {
    this.checkWaterLevelSensor = checkWaterLevelSensor;
  }


  public long getCheckSpeedDirectionSensor() {
    return checkSpeedDirectionSensor;
  }

  public void setCheckSpeedDirectionSensor(long checkSpeedDirectionSensor) {
    this.checkSpeedDirectionSensor = checkSpeedDirectionSensor;
  }


  public long getCheckFlowSensor() {
    return checkFlowSensor;
  }

  public void setCheckFlowSensor(long checkFlowSensor) {
    this.checkFlowSensor = checkFlowSensor;
  }


  public String getCheckOtherSensor() {
    return checkOtherSensor;
  }

  public void setCheckOtherSensor(String checkOtherSensor) {
    this.checkOtherSensor = checkOtherSensor;
  }


  public long getDataCollectionCorrect() {
    return dataCollectionCorrect;
  }

  public void setDataCollectionCorrect(long dataCollectionCorrect) {
    this.dataCollectionCorrect = dataCollectionCorrect;
  }


  public long getDataCollectionParameterCheck() {
    return dataCollectionParameterCheck;
  }

  public void setDataCollectionParameterCheck(long dataCollectionParameterCheck) {
    this.dataCollectionParameterCheck = dataCollectionParameterCheck;
  }


  public long getDataCollectionNormal() {
    return dataCollectionNormal;
  }

  public void setDataCollectionNormal(long dataCollectionNormal) {
    this.dataCollectionNormal = dataCollectionNormal;
  }


  public long getDataCommunicateParameterCheck() {
    return dataCommunicateParameterCheck;
  }

  public void setDataCommunicateParameterCheck(long dataCommunicateParameterCheck) {
    this.dataCommunicateParameterCheck = dataCommunicateParameterCheck;
  }


  public long getDataCommunicateChannel() {
    return dataCommunicateChannel;
  }

  public void setDataCommunicateChannel(long dataCommunicateChannel) {
    this.dataCommunicateChannel = dataCommunicateChannel;
  }


  public long getSolarEnergyVoltageCheck() {
    return solarEnergyVoltageCheck;
  }

  public void setSolarEnergyVoltageCheck(long solarEnergyVoltageCheck) {
    this.solarEnergyVoltageCheck = solarEnergyVoltageCheck;
  }


  public double getSolarEnergyVoltageValue() {
    return solarEnergyVoltageValue;
  }

  public void setSolarEnergyVoltageValue(double solarEnergyVoltageValue) {
    this.solarEnergyVoltageValue = solarEnergyVoltageValue;
  }


  public long getStorageBatteryVoltageCheck() {
    return storageBatteryVoltageCheck;
  }

  public void setStorageBatteryVoltageCheck(long storageBatteryVoltageCheck) {
    this.storageBatteryVoltageCheck = storageBatteryVoltageCheck;
  }


  public double getStorageBatteryValue() {
    return storageBatteryValue;
  }

  public void setStorageBatteryValue(double storageBatteryValue) {
    this.storageBatteryValue = storageBatteryValue;
  }


  public long getRainfallSensorNormal() {
    return rainfallSensorNormal;
  }

  public void setRainfallSensorNormal(long rainfallSensorNormal) {
    this.rainfallSensorNormal = rainfallSensorNormal;
  }


  public long getRainfallSensorCleanCheck() {
    return rainfallSensorCleanCheck;
  }

  public void setRainfallSensorCleanCheck(long rainfallSensorCleanCheck) {
    this.rainfallSensorCleanCheck = rainfallSensorCleanCheck;
  }


  public long getRainfallSensorRangeCheck() {
    return rainfallSensorRangeCheck;
  }

  public void setRainfallSensorRangeCheck(long rainfallSensorRangeCheck) {
    this.rainfallSensorRangeCheck = rainfallSensorRangeCheck;
  }


  public long getWaterLevelCheckNormal() {
    return waterLevelCheckNormal;
  }

  public void setWaterLevelCheckNormal(long waterLevelCheckNormal) {
    this.waterLevelCheckNormal = waterLevelCheckNormal;
  }


  public long getWaterLeverCheckShaft() {
    return waterLeverCheckShaft;
  }

  public void setWaterLeverCheckShaft(long waterLeverCheckShaft) {
    this.waterLeverCheckShaft = waterLeverCheckShaft;
  }


  public long getWaterLeverCleanCheck() {
    return waterLeverCleanCheck;
  }

  public void setWaterLeverCleanCheck(long waterLeverCleanCheck) {
    this.waterLeverCleanCheck = waterLeverCleanCheck;
  }


  public long getWaterLeverAdjust() {
    return waterLeverAdjust;
  }

  public void setWaterLeverAdjust(long waterLeverAdjust) {
    this.waterLeverAdjust = waterLeverAdjust;
  }


  public long getSpeedDirectionCheckNormal() {
    return speedDirectionCheckNormal;
  }

  public void setSpeedDirectionCheckNormal(long speedDirectionCheckNormal) {
    this.speedDirectionCheckNormal = speedDirectionCheckNormal;
  }


  public long getSpeedDirectionCheckLightingProtection() {
    return speedDirectionCheckLightingProtection;
  }

  public void setSpeedDirectionCheckLightingProtection(long speedDirectionCheckLightingProtection) {
    this.speedDirectionCheckLightingProtection = speedDirectionCheckLightingProtection;
  }


  public long getFlowmeterDataCheckNormal() {
    return flowmeterDataCheckNormal;
  }

  public void setFlowmeterDataCheckNormal(long flowmeterDataCheckNormal) {
    this.flowmeterDataCheckNormal = flowmeterDataCheckNormal;
  }


  public double getFlowmeterPitchGesture() {
    return flowmeterPitchGesture;
  }

  public void setFlowmeterPitchGesture(double flowmeterPitchGesture) {
    this.flowmeterPitchGesture = flowmeterPitchGesture;
  }


  public double getFlowmeterRollingGesture() {
    return flowmeterRollingGesture;
  }

  public void setFlowmeterRollingGesture(double flowmeterRollingGesture) {
    this.flowmeterRollingGesture = flowmeterRollingGesture;
  }


  public long getFlowmeterSignalStrength() {
    return flowmeterSignalStrength;
  }

  public void setFlowmeterSignalStrength(long flowmeterSignalStrength) {
    this.flowmeterSignalStrength = flowmeterSignalStrength;
  }


  public long getFlowmeterRecordTimeWarp() {
    return flowmeterRecordTimeWarp;
  }

  public void setFlowmeterRecordTimeWarp(long flowmeterRecordTimeWarp) {
    this.flowmeterRecordTimeWarp = flowmeterRecordTimeWarp;
  }


  public long getLinePipeCheckNormal() {
    return linePipeCheckNormal;
  }

  public void setLinePipeCheckNormal(long linePipeCheckNormal) {
    this.linePipeCheckNormal = linePipeCheckNormal;
  }


  public long getStationEnviroment() {
    return stationEnviroment;
  }

  public void setStationEnviroment(long stationEnviroment) {
    this.stationEnviroment = stationEnviroment;
  }


  public long getStationCleanCheck() {
    return stationCleanCheck;
  }

  public void setStationCleanCheck(long stationCleanCheck) {
    this.stationCleanCheck = stationCleanCheck;
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

}
