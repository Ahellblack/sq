package com.siti.wisdomhydrologic.operation.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;

/**
 * Created by dell on 2019/9/26.
 */
public class RainVo {

    @Excel(name = "reportId", height = 11, width = 10)
    private int reportId;

    @Excel(name = "mantainDate", height = 11, width = 10)
    private String mantainDate;

    @Excel(name = "stationCode", height = 11, width = 10)
    private int stationCode;

    @Excel(name = "stationName", height = 11, width = 10)
    private String stationName;

    @Excel(name = "stationManangeOrgId", height = 11, width = 10)
    private int stationManangeOrgId;

    @Excel(name = "stationManageOrgName", height = 11, width = 10)
    private String stationManageOrgName;

    @Excel(name = "rtuType", height = 11, width = 10)
    private String rtuType;

    @Excel(name = "checkRainSensor", height = 11, width = 10)
    private int checkRainSensor;

    @Excel(name = "checkWaterLevelSensor", height = 11, width = 10)
    private int checkWaterLevelSensor;

    @Excel(name = "checkSpeedDirectionSensor", height = 11, width = 10)
    private int checkSpeedDirectionSensor;

    @Excel(name = "checkFlowSensor", height = 11, width = 10)
    private int checkFlowSensor;

    @Excel(name = "checkOtherSensor", height = 11, width = 10)
    private String checkOtherSensor;

    @Excel(name = "dataCollectionCorrect", height = 11, width = 10)
    private int dataCollectionCorrect;

    @Excel(name = "dataCollectionParameterCheck", height = 11, width = 10)
    private int dataCollectionParameterCheck;

    @Excel(name = "dataCollectionNormal", height = 11, width = 10)
    private int dataCollectionNormal;

    @Excel(name = "dataCommunicateParameterCheck", height = 11, width = 10)
    private int dataCommunicateParameterCheck;

    @Excel(name = "dataCommunicateChannel", height = 11, width = 10)
    private int dataCommunicateChannel;

    @Excel(name = "solarEnergyVoltageCheck", height = 11, width = 10)
    private int solarEnergyVoltageCheck;

    @Excel(name = "solarEnergyVoltageValue", height = 11, width = 10)
    private double solarEnergyVoltageValue;

    @Excel(name = "storageBatteryVoltageCheck", height = 11, width = 10)
    private int storageBatteryVoltageCheck;

    @Excel(name = "storageBatteryValue", height = 11, width = 10)
    private double storageBatteryValue;

    @Excel(name = "rainfallSensorNormal", height = 11, width = 10)
    private int rainfallSensorNormal;

    @Excel(name = "rainfallSensorCleanCheck", height = 11, width = 10)
    private int rainfallSensorCleanCheck;

    @Excel(name = "rainfallSensorRangeCheck", height = 11, width = 10)
    private int rainfallSensorRangeCheck;

    @Excel(name = "waterLevelCheckNormal", height = 11, width = 10)
    private int waterLevelCheckNormal;

    @Excel(name = "waterLevelCheckShaft", height = 11, width = 10)
    private int waterLevelCheckShaft;

    @Excel(name = "waterLevelCleanCheck", height = 11, width = 10)
    private int waterLevelCleanCheck;

    @Excel(name = "waterLevelAdjust", height = 11, width = 10)
    private int waterLevelAdjust;

    @Excel(name = "speedDirectionCheckNormal", height = 11, width = 10)
    private int speedDirectionCheckNormal;

    @Excel(name = "speedDirectionCheckLightingProtection", height = 11, width = 10)
    private int speedDirectionCheckLightingProtection;

    @Excel(name = "flowmeterDataCheckNormal", height = 11, width = 10)
    private int flowmeterDataCheckNormal;

    @Excel(name = "flowmeterPitchGesture", height = 11, width = 10)
    private double flowmeterPitchGesture;

    @Excel(name = "flowmeterRollingGesture", height = 11, width = 10)
    private double flowmeterRollingGesture;

    @Excel(name = "flowmeterSignalStrength", height = 11, width = 10)
    private int flowmeterSignalStrength;

    @Excel(name = "flowmeterRecordTimeWarp", height = 11, width = 10)
    private int flowmeterRecordTimeWarp;

    @Excel(name = "linePipeCheckNormal", height = 11, width = 10)
    private int linePipeCheckNormal;

    @Excel(name = "stationEnviroment", height = 11, width = 10)
    private int stationEnviroment;

    @Excel(name = "stationCleanCheck", height = 11, width = 10)
    private int stationCleanCheck;

    @Excel(name = "remark", height = 11, width = 10)
    private String remark;

    @Excel(name = "createBy", height = 11, width = 10)
    private String createBy;

    @Excel(name = "createTime", height = 11, width = 10)
    private String createTime;

    private String solarEnergyVoltageCheckRightName;
    private String storageBatteryVoltageCheckRightName;
    private String solarEnergyVoltageCheckWrongName;
    private String storageBatteryVoltageCheckWrongName;

    private String waterLevelZeroHeight;
    private String waterLevelStaffValue;
    private String waterLevelTime;
    private String waterLevelIndicatorValue;
    private String waterLevelValue;

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

    public int getStationCode() {
        return stationCode;
    }

    public void setStationCode(int stationCode) {
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

    public int getWaterLevelCheckShaft() {
        return waterLevelCheckShaft;
    }

    public void setWaterLevelCheckShaft(int waterLevelCheckShaft) {
        this.waterLevelCheckShaft = waterLevelCheckShaft;
    }

    public int getWaterLevelCleanCheck() {
        return waterLevelCleanCheck;
    }

    public void setWaterLevelCleanCheck(int waterLevelCleanCheck) {
        this.waterLevelCleanCheck = waterLevelCleanCheck;
    }

    public int getWaterLevelAdjust() {
        return waterLevelAdjust;
    }

    public void setWaterLevelAdjust(int waterLevelAdjust) {
        this.waterLevelAdjust = waterLevelAdjust;
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

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getSolarEnergyVoltageCheckRightName() {
        return solarEnergyVoltageCheckRightName;
    }

    public void setSolarEnergyVoltageCheckRightName(String solarEnergyVoltageCheckRightName) {
        this.solarEnergyVoltageCheckRightName = solarEnergyVoltageCheckRightName;
    }

    public String getStorageBatteryVoltageCheckRightName() {
        return storageBatteryVoltageCheckRightName;
    }

    public void setStorageBatteryVoltageCheckRightName(String storageBatteryVoltageCheckRightName) {
        this.storageBatteryVoltageCheckRightName = storageBatteryVoltageCheckRightName;
    }

    public String getSolarEnergyVoltageCheckWrongName() {
        return solarEnergyVoltageCheckWrongName;
    }

    public void setSolarEnergyVoltageCheckWrongName(String solarEnergyVoltageCheckWrongName) {
        this.solarEnergyVoltageCheckWrongName = solarEnergyVoltageCheckWrongName;
    }

    public String getStorageBatteryVoltageCheckWrongName() {
        return storageBatteryVoltageCheckWrongName;
    }

    public void setStorageBatteryVoltageCheckWrongName(String storageBatteryVoltageCheckWrongName) {
        this.storageBatteryVoltageCheckWrongName = storageBatteryVoltageCheckWrongName;
    }

    public String getWaterLevelZeroHeight() {
        return waterLevelZeroHeight;
    }

    public void setWaterLevelZeroHeight(String waterLevelZeroHeight) {
        this.waterLevelZeroHeight = waterLevelZeroHeight;
    }

    public String getWaterLevelStaffValue() {
        return waterLevelStaffValue;
    }

    public void setWaterLevelStaffValue(String waterLevelStaffValue) {
        this.waterLevelStaffValue = waterLevelStaffValue;
    }

    public String getWaterLevelTime() {
        return waterLevelTime;
    }

    public void setWaterLevelTime(String waterLevelTime) {
        this.waterLevelTime = waterLevelTime;
    }

    public String getWaterLevelIndicatorValue() {
        return waterLevelIndicatorValue;
    }

    public void setWaterLevelIndicatorValue(String waterLevelIndicatorValue) {
        this.waterLevelIndicatorValue = waterLevelIndicatorValue;
    }

    public String getWaterLevelValue() {
        return waterLevelValue;
    }

    public void setWaterLevelValue(String waterLevelValue) {
        this.waterLevelValue = waterLevelValue;
    }

    @Override
    public String toString() {
        return "RainVo{" + "reportId=" + reportId + ", mantainDate='" + mantainDate + '\'' + ", stationCode=" + stationCode + ", stationName='" + stationName + '\'' + ", stationManangeOrgId=" + stationManangeOrgId + ", stationManageOrgName='" + stationManageOrgName + '\'' + ", rtuType='" + rtuType + '\'' + ", checkRainSensor=" + checkRainSensor + ", checkWaterLevelSensor=" + checkWaterLevelSensor + ", checkSpeedDirectionSensor=" + checkSpeedDirectionSensor + ", checkFlowSensor=" + checkFlowSensor + ", checkOtherSensor='" + checkOtherSensor + '\'' + ", dataCollectionCorrect=" + dataCollectionCorrect + ", dataCollectionParameterCheck=" + dataCollectionParameterCheck + ", dataCollectionNormal=" + dataCollectionNormal + ", dataCommunicateParameterCheck=" + dataCommunicateParameterCheck + ", dataCommunicateChannel=" + dataCommunicateChannel + ", solarEnergyVoltageCheck=" + solarEnergyVoltageCheck + ", solarEnergyVoltageValue=" + solarEnergyVoltageValue + ", storageBatteryVoltageCheck=" + storageBatteryVoltageCheck + ", storageBatteryValue=" + storageBatteryValue + ", rainfallSensorNormal=" + rainfallSensorNormal + ", rainfallSensorCleanCheck=" + rainfallSensorCleanCheck + ", rainfallSensorRangeCheck=" + rainfallSensorRangeCheck + ", waterLevelCheckNormal=" + waterLevelCheckNormal + ", waterLevelCheckShaft=" + waterLevelCheckShaft + ", waterLevelCleanCheck=" + waterLevelCleanCheck + ", waterLevelAdjust=" + waterLevelAdjust + ", speedDirectionCheckNormal=" + speedDirectionCheckNormal + ", speedDirectionCheckLightingProtection=" + speedDirectionCheckLightingProtection + ", flowmeterDataCheckNormal=" + flowmeterDataCheckNormal + ", flowmeterPitchGesture=" + flowmeterPitchGesture + ", flowmeterRollingGesture=" + flowmeterRollingGesture + ", flowmeterSignalStrength=" + flowmeterSignalStrength + ", flowmeterRecordTimeWarp=" + flowmeterRecordTimeWarp + ", linePipeCheckNormal=" + linePipeCheckNormal + ", stationEnviroment=" + stationEnviroment + ", stationCleanCheck=" + stationCleanCheck + ", remark='" + remark + '\'' + ", createBy='" + createBy + '\'' + ", createTime='" + createTime + '\'' + ", solarEnergyVoltageCheckRightName='" + solarEnergyVoltageCheckRightName + '\'' + ", storageBatteryVoltageCheckRightName='" + storageBatteryVoltageCheckRightName + '\'' + ", solarEnergyVoltageCheckWrongName='" + solarEnergyVoltageCheckWrongName + '\'' + ", storageBatteryVoltageCheckWrongName='" + storageBatteryVoltageCheckWrongName + '\'' + ", waterLevelZeroHeight='" + waterLevelZeroHeight + '\'' + ", waterLevelStaffValue='" + waterLevelStaffValue + '\'' + ", waterLevelTime='" + waterLevelTime + '\'' + ", waterLevelIndicatorValue='" + waterLevelIndicatorValue + '\'' + ", waterLevelValue='" + waterLevelValue + '\'' + '}';
    }
}
