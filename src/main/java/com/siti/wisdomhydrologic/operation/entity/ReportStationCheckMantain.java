package com.siti.wisdomhydrologic.operation.entity;

public class ReportStationCheckMantain {

  private int reportId;
  private String mantainDate;
  private String stationCode;
  private String stationName;
  private int stationManangeOrgId;
  private String stationManageOrgName;
  private String rtuType;
  private int checkRainSensor;
  private int checkWaterLevelSensor;
  private int checkSpeedDirectionSensor;
  private int checkFlowSensor;
  private String checkOtherSensor;
  private int dataCollectionCorrect;
  private int dataCollectionParameterCheck;
  private int dataCollectionNormal;
  private int dataCommunicateParameterCheck;
  private int dataCommunicateChannel;
  private int solarEnergyVoltageCheck;
  private double solarEnergyVoltageValue;
  private int storageBatteryVoltageCheck;
  private double storageBatteryValue;
  private int rainfallSensorNormal;
  private int rainfallSensorCleanCheck;
  private int rainfallSensorRangeCheck;
  private int waterLevelCheckNormal;
  private int waterLeverCheckShaft;
  private int waterLeverCleanCheck;
  private int waterLeverAdjust;
  private int speedDirectionCheckNormal;
  private int speedDirectionCheckLightingProtection;
  private int flowmeterDataCheckNormal;
  private double flowmeterPitchGesture;
  private double flowmeterRollingGesture;
  private int flowmeterSignalStrength;
  private int flowmeterRecordTimeWarp;
  private int linePipeCheckNormal;
  private int stationEnviroment;
  private int stationCleanCheck;
  private String remark;
  private int createBy;
  private java.sql.Timestamp createTime;


  public int getReportId() {
    return reportId;
  }

  public void setReportId(int reportId) {
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


  public int getStationManangeOrgId() {
    return stationManangeOrgId;
  }

  public void setStationManangeOrgId(int stationManangeOrgId) {
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


  public int getCheckRainSensor() {
    return checkRainSensor;
  }

  public void setCheckRainSensor(int checkRainSensor) {
    this.checkRainSensor = checkRainSensor;
  }


  public int getCheckWaterLevelSensor() {
    return checkWaterLevelSensor;
  }

  public void setCheckWaterLevelSensor(int checkWaterLevelSensor) {
    this.checkWaterLevelSensor = checkWaterLevelSensor;
  }


  public int getCheckSpeedDirectionSensor() {
    return checkSpeedDirectionSensor;
  }

  public void setCheckSpeedDirectionSensor(int checkSpeedDirectionSensor) {
    this.checkSpeedDirectionSensor = checkSpeedDirectionSensor;
  }


  public int getCheckFlowSensor() {
    return checkFlowSensor;
  }

  public void setCheckFlowSensor(int checkFlowSensor) {
    this.checkFlowSensor = checkFlowSensor;
  }


  public String getCheckOtherSensor() {
    return checkOtherSensor;
  }

  public void setCheckOtherSensor(String checkOtherSensor) {
    this.checkOtherSensor = checkOtherSensor;
  }


  public int getDataCollectionCorrect() {
    return dataCollectionCorrect;
  }

  public void setDataCollectionCorrect(int dataCollectionCorrect) {
    this.dataCollectionCorrect = dataCollectionCorrect;
  }


  public int getDataCollectionParameterCheck() {
    return dataCollectionParameterCheck;
  }

  public void setDataCollectionParameterCheck(int dataCollectionParameterCheck) {
    this.dataCollectionParameterCheck = dataCollectionParameterCheck;
  }


  public int getDataCollectionNormal() {
    return dataCollectionNormal;
  }

  public void setDataCollectionNormal(int dataCollectionNormal) {
    this.dataCollectionNormal = dataCollectionNormal;
  }


  public int getDataCommunicateParameterCheck() {
    return dataCommunicateParameterCheck;
  }

  public void setDataCommunicateParameterCheck(int dataCommunicateParameterCheck) {
    this.dataCommunicateParameterCheck = dataCommunicateParameterCheck;
  }


  public int getDataCommunicateChannel() {
    return dataCommunicateChannel;
  }

  public void setDataCommunicateChannel(int dataCommunicateChannel) {
    this.dataCommunicateChannel = dataCommunicateChannel;
  }


  public int getSolarEnergyVoltageCheck() {
    return solarEnergyVoltageCheck;
  }

  public void setSolarEnergyVoltageCheck(int solarEnergyVoltageCheck) {
    this.solarEnergyVoltageCheck = solarEnergyVoltageCheck;
  }


  public double getSolarEnergyVoltageValue() {
    return solarEnergyVoltageValue;
  }

  public void setSolarEnergyVoltageValue(double solarEnergyVoltageValue) {
    this.solarEnergyVoltageValue = solarEnergyVoltageValue;
  }


  public int getStorageBatteryVoltageCheck() {
    return storageBatteryVoltageCheck;
  }

  public void setStorageBatteryVoltageCheck(int storageBatteryVoltageCheck) {
    this.storageBatteryVoltageCheck = storageBatteryVoltageCheck;
  }


  public double getStorageBatteryValue() {
    return storageBatteryValue;
  }

  public void setStorageBatteryValue(double storageBatteryValue) {
    this.storageBatteryValue = storageBatteryValue;
  }


  public int getRainfallSensorNormal() {
    return rainfallSensorNormal;
  }

  public void setRainfallSensorNormal(int rainfallSensorNormal) {
    this.rainfallSensorNormal = rainfallSensorNormal;
  }


  public int getRainfallSensorCleanCheck() {
    return rainfallSensorCleanCheck;
  }

  public void setRainfallSensorCleanCheck(int rainfallSensorCleanCheck) {
    this.rainfallSensorCleanCheck = rainfallSensorCleanCheck;
  }


  public int getRainfallSensorRangeCheck() {
    return rainfallSensorRangeCheck;
  }

  public void setRainfallSensorRangeCheck(int rainfallSensorRangeCheck) {
    this.rainfallSensorRangeCheck = rainfallSensorRangeCheck;
  }


  public int getWaterLevelCheckNormal() {
    return waterLevelCheckNormal;
  }

  public void setWaterLevelCheckNormal(int waterLevelCheckNormal) {
    this.waterLevelCheckNormal = waterLevelCheckNormal;
  }


  public int getWaterLeverCheckShaft() {
    return waterLeverCheckShaft;
  }

  public void setWaterLeverCheckShaft(int waterLeverCheckShaft) {
    this.waterLeverCheckShaft = waterLeverCheckShaft;
  }


  public int getWaterLeverCleanCheck() {
    return waterLeverCleanCheck;
  }

  public void setWaterLeverCleanCheck(int waterLeverCleanCheck) {
    this.waterLeverCleanCheck = waterLeverCleanCheck;
  }


  public int getWaterLeverAdjust() {
    return waterLeverAdjust;
  }

  public void setWaterLeverAdjust(int waterLeverAdjust) {
    this.waterLeverAdjust = waterLeverAdjust;
  }


  public int getSpeedDirectionCheckNormal() {
    return speedDirectionCheckNormal;
  }

  public void setSpeedDirectionCheckNormal(int speedDirectionCheckNormal) {
    this.speedDirectionCheckNormal = speedDirectionCheckNormal;
  }


  public int getSpeedDirectionCheckLightingProtection() {
    return speedDirectionCheckLightingProtection;
  }

  public void setSpeedDirectionCheckLightingProtection(int speedDirectionCheckLightingProtection) {
    this.speedDirectionCheckLightingProtection = speedDirectionCheckLightingProtection;
  }


  public int getFlowmeterDataCheckNormal() {
    return flowmeterDataCheckNormal;
  }

  public void setFlowmeterDataCheckNormal(int flowmeterDataCheckNormal) {
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


  public int getFlowmeterSignalStrength() {
    return flowmeterSignalStrength;
  }

  public void setFlowmeterSignalStrength(int flowmeterSignalStrength) {
    this.flowmeterSignalStrength = flowmeterSignalStrength;
  }


  public int getFlowmeterRecordTimeWarp() {
    return flowmeterRecordTimeWarp;
  }

  public void setFlowmeterRecordTimeWarp(int flowmeterRecordTimeWarp) {
    this.flowmeterRecordTimeWarp = flowmeterRecordTimeWarp;
  }


  public int getLinePipeCheckNormal() {
    return linePipeCheckNormal;
  }

  public void setLinePipeCheckNormal(int linePipeCheckNormal) {
    this.linePipeCheckNormal = linePipeCheckNormal;
  }


  public int getStationEnviroment() {
    return stationEnviroment;
  }

  public void setStationEnviroment(int stationEnviroment) {
    this.stationEnviroment = stationEnviroment;
  }


  public int getStationCleanCheck() {
    return stationCleanCheck;
  }

  public void setStationCleanCheck(int stationCleanCheck) {
    this.stationCleanCheck = stationCleanCheck;
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


  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }

}
