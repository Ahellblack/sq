package com.siti.wisdomhydrologic.mainpage.vo;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by DC on 2019/7/19.
 *
 * @data ${DATA}-18:04
 */
@Table(name = "abnormal_detail")
public class AbnormalDetailVo {
    @Id
    int id;
    String date;
    int sensorCode;
    String errorPeriod;
    double errorValue;
    String dataError;
    String equipmentError;
    Date createTime;

    String errorName;
    String brokenAccording;
    String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrokenAccording() {
        return brokenAccording;
    }

    public void setBrokenAccording(String brokenAccording) {
        this.brokenAccording = brokenAccording;
    }

    public String getDataError() {
        return dataError;
    }

    public void setDataError(String dataError) {
        this.dataError = dataError;
    }

    public String getErrorName() {
        return errorName;
    }

    public void setErrorName(String errorName) {
        this.errorName = errorName;
    }

    public String getDateError() {
        return dataError;
    }

    public void setDateError(String dataError) {
        this.dataError = dataError;
    }

    public String getEquipmentError() {
        return equipmentError;
    }

    public void setEquipmentError(String equipmentError) {
        this.equipmentError = equipmentError;
    }

    public double getErrorValue() {
        return errorValue;
    }

    public void setErrorValue(double errorValue) {
        this.errorValue = errorValue;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getErrorPeriod() {
        return errorPeriod;
    }

    public void setErrorPeriod(String errorPeriod) {
        this.errorPeriod = errorPeriod;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AbnormalDetailVo() {
    }
}
