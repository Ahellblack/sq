package com.siti.wisdomhydrologic.operation.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;

import javax.persistence.Id;
import java.util.Date;

public class ReportManageDataMantainVo {

    private Integer reportId;
    private int stationCode;
    private String alterDate;
    private String stationName;
    private Integer alterSensorTypeId;
    private String alterSensorTypeName;
    private String errorDataReason;
    private Integer errorDataType;
    private String errorTimeSpace;
    private String errorValue;
    private String confirValue;
    private String errorUnit;
    private Integer errorDataReRun;
    private String missDataType;
    private String missTimeSpace;
    private Integer missDataReRun;
    private String createTime;
    private String createBy;
    private Integer manageOrgId;
    private String manageOrgName;
    private String brokenAccordingId;


    /**
     * 异常数据表字段
     */
    @Id
    String date;
    @Id
    int sensorCode;
    int fiveBelow;
    int fiveAbove;
    int hourBelow;
    int hourAbove;
    int dayBelow;
    int dayAbove;
    int moreNear;
    int lessNear;
    int floatingUp;
    int floatingDown;
    int keepTime;
    int continueInterrupt;
    int updateTime;


    private int sectionCode;
    private String sectionName;
    private String sensorName;
    private String sectionDataUnit;
    private String sectionStatus;
    private String dataError;
    private String equipmentError;

    public int getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(int updateTime) {
        this.updateTime = updateTime;
    }

    public String getBrokenAccordingId() {
        return brokenAccordingId;
    }

    public String getDataError() {
        return dataError;
    }

    public void setDataError(String dataError) {
        this.dataError = dataError;
    }

    public String getEquipmentError() {
        return equipmentError;
    }

    public void setEquipmentError(String equipmentError) {
        this.equipmentError = equipmentError;
    }

    public void setBrokenAccordingId(String brokenAccordingId) {
        this.brokenAccordingId = brokenAccordingId;
    }

    public int getSectionCode() {
        return sectionCode;
    }

    public void setSectionCode(int sectionCode) {
        this.sectionCode = sectionCode;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public String getSectionDataUnit() {
        return sectionDataUnit;
    }

    public void setSectionDataUnit(String sectionDataUnit) {
        this.sectionDataUnit = sectionDataUnit;
    }

    public String getSectionStatus() {
        return sectionStatus;
    }

    public void setSectionStatus(String sectionStatus) {
        this.sectionStatus = sectionStatus;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getSensorCode() {
        return sensorCode;
    }

    public void setSensorCode(int sensorCode) {
        this.sensorCode = sensorCode;
    }

    public int getFiveBelow() {
        return fiveBelow;
    }

    public void setFiveBelow(int fiveBelow) {
        this.fiveBelow = fiveBelow;
    }

    public int getFiveAbove() {
        return fiveAbove;
    }

    public void setFiveAbove(int fiveAbove) {
        this.fiveAbove = fiveAbove;
    }

    public int getHourBelow() {
        return hourBelow;
    }

    public void setHourBelow(int hourBelow) {
        this.hourBelow = hourBelow;
    }

    public int getHourAbove() {
        return hourAbove;
    }

    public void setHourAbove(int hourAbove) {
        this.hourAbove = hourAbove;
    }

    public int getDayBelow() {
        return dayBelow;
    }

    public void setDayBelow(int dayBelow) {
        this.dayBelow = dayBelow;
    }

    public int getDayAbove() {
        return dayAbove;
    }

    public void setDayAbove(int dayAbove) {
        this.dayAbove = dayAbove;
    }

    public int getMoreNear() {
        return moreNear;
    }

    public void setMoreNear(int moreNear) {
        this.moreNear = moreNear;
    }

    public int getLessNear() {
        return lessNear;
    }

    public void setLessNear(int lessNear) {
        this.lessNear = lessNear;
    }

    public int getFloatingUp() {
        return floatingUp;
    }

    public void setFloatingUp(int floatingUp) {
        this.floatingUp = floatingUp;
    }

    public int getFloatingDown() {
        return floatingDown;
    }

    public void setFloatingDown(int floatingDown) {
        this.floatingDown = floatingDown;
    }

    public int getKeepTime() {
        return keepTime;
    }

    public void setKeepTime(int keepTime) {
        this.keepTime = keepTime;
    }

    public int getContinueInterrupt() {
        return continueInterrupt;
    }

    public void setContinueInterrupt(int continueInterrupt) {
        this.continueInterrupt = continueInterrupt;
    }

    public String getAlterDate() {
        return alterDate;
    }

    public void setAlterDate(String alterDate) {
        this.alterDate = alterDate;
    }

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
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

    public Integer getAlterSensorTypeId() {
        return alterSensorTypeId;
    }

    public void setAlterSensorTypeId(Integer alterSensorTypeId) {
        this.alterSensorTypeId = alterSensorTypeId;
    }

    public String getAlterSensorTypeName() {
        return alterSensorTypeName;
    }

    public void setAlterSensorTypeName(String alterSensorTypeName) {
        this.alterSensorTypeName = alterSensorTypeName;
    }

    public String getErrorDataReason() {
        return errorDataReason;
    }

    public void setErrorDataReason(String errorDataReason) {
        this.errorDataReason = errorDataReason;
    }

    public Integer getErrorDataType() {
        return errorDataType;
    }

    public void setErrorDataType(Integer errorDataType) {
        this.errorDataType = errorDataType;
    }

    public String getErrorTimeSpace() {
        return errorTimeSpace;
    }

    public void setErrorTimeSpace(String errorTimeSpace) {
        this.errorTimeSpace = errorTimeSpace;
    }

    public String getErrorValue() {
        return errorValue;
    }

    public void setErrorValue(String errorValue) {
        this.errorValue = errorValue;
    }

    public String getConfirValue() {
        return confirValue;
    }

    public void setConfirValue(String confirValue) {
        this.confirValue = confirValue;
    }

    public String getErrorUnit() {
        return errorUnit;
    }

    public void setErrorUnit(String errorUnit) {
        this.errorUnit = errorUnit;
    }

    public Integer getErrorDataReRun() {
        return errorDataReRun;
    }

    public void setErrorDataReRun(Integer errorDataReRun) {
        this.errorDataReRun = errorDataReRun;
    }

    public String getMissDataType() {
        return missDataType;
    }

    public void setMissDataType(String missDataType) {
        this.missDataType = missDataType;
    }

    public String getMissTimeSpace() {
        return missTimeSpace;
    }

    public void setMissTimeSpace(String missTimeSpace) {
        this.missTimeSpace = missTimeSpace;
    }

    public Integer getMissDataReRun() {
        return missDataReRun;
    }

    public void setMissDataReRun(Integer missDataReRun) {
        this.missDataReRun = missDataReRun;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Integer getManageOrgId() {
        return manageOrgId;
    }

    public void setManageOrgId(Integer manageOrgId) {
        this.manageOrgId = manageOrgId;
    }

    public String getManageOrgName() {
        return manageOrgName;
    }

    public void setManageOrgName(String manageOrgName) {
        this.manageOrgName = manageOrgName;
    }

    @Override
    public String toString() {
        return "ReportManageDataMantainVo{" + "reportId=" + reportId + ", stationCode=" + stationCode + ", alterDate='" + alterDate + '\'' + ", stationName='" + stationName + '\'' + ", alterSensorTypeId=" + alterSensorTypeId + ", alterSensorTypeName='" + alterSensorTypeName + '\'' + ", errorDataReason='" + errorDataReason + '\'' + ", errorDataType=" + errorDataType + ", errorTimeSpace='" + errorTimeSpace + '\'' + ", errorValue='" + errorValue + '\'' + ", confirValue='" + confirValue + '\'' + ", errorUnit='" + errorUnit + '\'' + ", errorDataReRun=" + errorDataReRun + ", missDataType='" + missDataType + '\'' + ", missTimeSpace='" + missTimeSpace + '\'' + ", missDataReRun=" + missDataReRun + ", createTime='" + createTime + '\'' + ", createBy='" + createBy + '\'' + ", manageOrgId=" + manageOrgId + ", manageOrgName='" + manageOrgName + '\'' + ", brokenAccordingId=" + brokenAccordingId + ", date='" + date + '\'' + ", sensorCode=" + sensorCode + ", fiveBelow=" + fiveBelow + ", fiveAbove=" + fiveAbove + ", hourBelow=" + hourBelow + ", hourAbove=" + hourAbove + ", dayBelow=" + dayBelow + ", dayAbove=" + dayAbove + ", moreNear=" + moreNear + ", lessNear=" + lessNear + ", floatingUp=" + floatingUp + ", floatingDown=" + floatingDown + ", keepTime=" + keepTime + ", continueInterrupt=" + continueInterrupt + ", updateTime=" + updateTime + ", sectionCode=" + sectionCode + ", sectionName='" + sectionName + '\'' + ", sensorName='" + sensorName + '\'' + ", sectionDataUnit='" + sectionDataUnit + '\'' + ", sectionStatus='" + sectionStatus + '\'' + '}';
    }
}
