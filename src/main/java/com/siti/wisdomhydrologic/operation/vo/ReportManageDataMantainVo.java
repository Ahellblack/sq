package com.siti.wisdomhydrologic.operation.vo;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Id;

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
    private String errorDataTypeName;
    private String errorDataReRunName;
    private String missDataReRunName;
    private String description;

    @ApiModelProperty("value=数据错误最后发生时间")
    private String errorLastestAppearTime;

    /**
     * 异常数据表字段
     */
    Integer id;
    @Id
    String date;
    @Id
    int sensorCode;
    private int continueInterrupt;
    private int updateTime;
    private String dataError;
    private String equipmentError;

    private int sectionCode;
    private String sectionName;
    private String sensorName;
    private String sectionDataUnit;
    private String sectionStatus;

    private ReportManageDataMantainVo(Builder builder) {
        setReportId(builder.reportId);
        setStationCode(builder.stationCode);
        setAlterDate(builder.alterDate);
        setStationName(builder.stationName);
        setAlterSensorTypeId(builder.alterSensorTypeId);
        setAlterSensorTypeName(builder.alterSensorTypeName);
        setErrorDataReason(builder.errorDataReason);
        setErrorDataType(builder.errorDataType);
        setErrorTimeSpace(builder.errorTimeSpace);
        setErrorValue(builder.errorValue);
        setConfirValue(builder.confirValue);
        setErrorUnit(builder.errorUnit);
        setErrorDataReRun(builder.errorDataReRun);
        setMissDataType(builder.missDataType);
        setMissTimeSpace(builder.missTimeSpace);
        setMissDataReRun(builder.missDataReRun);
        setCreateTime(builder.createTime);
        setCreateBy(builder.createBy);
        setManageOrgId(builder.manageOrgId);
        setManageOrgName(builder.manageOrgName);
        setBrokenAccordingId(builder.brokenAccordingId);
        setErrorDataTypeName(builder.errorDataTypeName);
        setErrorDataReRunName(builder.errorDataReRunName);
        setMissDataReRunName(builder.missDataReRunName);
        setDescription(builder.description);
        setErrorLastestAppearTime(builder.errorLastestAppearTime);
        setId(builder.id);
        setDate(builder.date);
        setSensorCode(builder.sensorCode);
        setContinueInterrupt(builder.continueInterrupt);
        setUpdateTime(builder.updateTime);
        setDataError(builder.dataError);
        setEquipmentError(builder.equipmentError);
        setSectionCode(builder.sectionCode);
        setSectionName(builder.sectionName);
        setSensorName(builder.sensorName);
        setSectionDataUnit(builder.sectionDataUnit);
        setSectionStatus(builder.sectionStatus);
    }

    @Override
    public String toString() {
        return "ReportManageDataMantainVo{" + "reportId=" + reportId + ", stationCode=" + stationCode + ", alterDate='" + alterDate + '\'' + ", stationName='" + stationName + '\'' + ", alterSensorTypeId=" + alterSensorTypeId + ", alterSensorTypeName='" + alterSensorTypeName + '\'' + ", errorDataReason='" + errorDataReason + '\'' + ", errorDataType=" + errorDataType + ", errorTimeSpace='" + errorTimeSpace + '\'' + ", errorValue='" + errorValue + '\'' + ", confirValue='" + confirValue + '\'' + ", errorUnit='" + errorUnit + '\'' + ", errorDataReRun=" + errorDataReRun + ", missDataType='" + missDataType + '\'' + ", missTimeSpace='" + missTimeSpace + '\'' + ", missDataReRun=" + missDataReRun + ", createTime='" + createTime + '\'' + ", createBy='" + createBy + '\'' + ", manageOrgId=" + manageOrgId + ", manageOrgName='" + manageOrgName + '\'' + ", brokenAccordingId='" + brokenAccordingId + '\'' + ", errorDataTypeName='" + errorDataTypeName + '\'' + ", errorDataReRunName='" + errorDataReRunName + '\'' + ", missDataReRunName='" + missDataReRunName + '\'' + ", description='" + description + '\'' + ", errorLastestAppearTime='" + errorLastestAppearTime + '\'' + ", id=" + id + ", date='" + date + '\'' + ", sensorCode=" + sensorCode + ", continueInterrupt=" + continueInterrupt + ", updateTime=" + updateTime + ", dataError='" + dataError + '\'' + ", equipmentError='" + equipmentError + '\'' + ", sectionCode=" + sectionCode + ", sectionName='" + sectionName + '\'' + ", sensorName='" + sensorName + '\'' + ", sectionDataUnit='" + sectionDataUnit + '\'' + ", sectionStatus='" + sectionStatus + '\'' + '}';
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getErrorLastestAppearTime() {
        return errorLastestAppearTime;
    }

    public void setErrorLastestAppearTime(String errorLastestAppearTime) {
        this.errorLastestAppearTime = errorLastestAppearTime;
    }

    public String getErrorDataTypeName() {
        return errorDataTypeName;
    }

    public void setErrorDataTypeName(String errorDataTypeName) {
        this.errorDataTypeName = errorDataTypeName;
    }

    public String getErrorDataReRunName() {
        return errorDataReRunName;
    }

    public void setErrorDataReRunName(String errorDataReRunName) {
        this.errorDataReRunName = errorDataReRunName;
    }

    public String getMissDataReRunName() {
        return missDataReRunName;
    }

    public void setMissDataReRunName(String missDataReRunName) {
        this.missDataReRunName = missDataReRunName;
    }

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


    public static final class Builder {
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
        private String errorDataTypeName;
        private String errorDataReRunName;
        private String missDataReRunName;
        private String description;
        private String errorLastestAppearTime;
        private Integer id;
        private String date;
        private int sensorCode;
        private int continueInterrupt;
        private int updateTime;
        private String dataError;
        private String equipmentError;
        private int sectionCode;
        private String sectionName;
        private String sensorName;
        private String sectionDataUnit;
        private String sectionStatus;

        public Builder() {
        }

        public Builder reportId(Integer val) {
            reportId = val;
            return this;
        }

        public Builder stationCode(int val) {
            stationCode = val;
            return this;
        }

        public Builder alterDate(String val) {
            alterDate = val;
            return this;
        }

        public Builder stationName(String val) {
            stationName = val;
            return this;
        }

        public Builder alterSensorTypeId(Integer val) {
            alterSensorTypeId = val;
            return this;
        }

        public Builder alterSensorTypeName(String val) {
            alterSensorTypeName = val;
            return this;
        }

        public Builder errorDataReason(String val) {
            errorDataReason = val;
            return this;
        }

        public Builder errorDataType(Integer val) {
            errorDataType = val;
            return this;
        }

        public Builder errorTimeSpace(String val) {
            errorTimeSpace = val;
            return this;
        }

        public Builder errorValue(String val) {
            errorValue = val;
            return this;
        }

        public Builder confirValue(String val) {
            confirValue = val;
            return this;
        }

        public Builder errorUnit(String val) {
            errorUnit = val;
            return this;
        }

        public Builder errorDataReRun(Integer val) {
            errorDataReRun = val;
            return this;
        }

        public Builder missDataType(String val) {
            missDataType = val;
            return this;
        }

        public Builder missTimeSpace(String val) {
            missTimeSpace = val;
            return this;
        }

        public Builder missDataReRun(Integer val) {
            missDataReRun = val;
            return this;
        }

        public Builder createTime(String val) {
            createTime = val;
            return this;
        }

        public Builder createBy(String val) {
            createBy = val;
            return this;
        }

        public Builder manageOrgId(Integer val) {
            manageOrgId = val;
            return this;
        }

        public Builder manageOrgName(String val) {
            manageOrgName = val;
            return this;
        }

        public Builder brokenAccordingId(String val) {
            brokenAccordingId = val;
            return this;
        }

        public Builder errorDataTypeName(String val) {
            errorDataTypeName = val;
            return this;
        }

        public Builder errorDataReRunName(String val) {
            errorDataReRunName = val;
            return this;
        }

        public Builder missDataReRunName(String val) {
            missDataReRunName = val;
            return this;
        }

        public Builder description(String val) {
            description = val;
            return this;
        }

        public Builder errorLastestAppearTime(String val) {
            errorLastestAppearTime = val;
            return this;
        }

        public Builder id(Integer val) {
            id = val;
            return this;
        }

        public Builder date(String val) {
            date = val;
            return this;
        }

        public Builder sensorCode(int val) {
            sensorCode = val;
            return this;
        }

        public Builder continueInterrupt(int val) {
            continueInterrupt = val;
            return this;
        }

        public Builder updateTime(int val) {
            updateTime = val;
            return this;
        }

        public Builder dataError(String val) {
            dataError = val;
            return this;
        }

        public Builder equipmentError(String val) {
            equipmentError = val;
            return this;
        }

        public Builder sectionCode(int val) {
            sectionCode = val;
            return this;
        }

        public Builder sectionName(String val) {
            sectionName = val;
            return this;
        }

        public Builder sensorName(String val) {
            sensorName = val;
            return this;
        }

        public Builder sectionDataUnit(String val) {
            sectionDataUnit = val;
            return this;
        }

        public Builder sectionStatus(String val) {
            sectionStatus = val;
            return this;
        }

        public ReportManageDataMantainVo build() {
            return new ReportManageDataMantainVo(this);
        }
    }
}
