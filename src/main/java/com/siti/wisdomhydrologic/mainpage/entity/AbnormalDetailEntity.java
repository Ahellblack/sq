package com.siti.wisdomhydrologic.mainpage.entity;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by DC on 2019/7/19.
 *
 * @data ${DATA}-18:04
 */
@Table(name = "abnormal_detail")
public class AbnormalDetailEntity {
    @Id
    int id;
    String date;
    int sensorCode;
    String errorPeriod;
    double errorValue;
    String dataError;
    String equipmentError;
    Date createTime;
    String description;

    private AbnormalDetailEntity(builer builer){
        this. date=builer.date;
        this. sensorCode=builer.sensorCode;
        this. errorPeriod=builer.errorPeriod;
        this. errorValue=builer.errorValue;
        this. dataError=builer.dataError;
        this. equipmentError=builer.equipmentError;
        this. createTime=builer.createTime;
        this. description=builer.description;
        //java 构建器
    }

    public static class builer{
        String date;
        int sensorCode;
        double errorValue;
        String dataError;
        String equipmentError;
        Date createTime;
       String errorPeriod;
        String description;
        public AbnormalDetailEntity build(){
            return new AbnormalDetailEntity(this);
        }


        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public builer date(String date) {
            this.date = date;
            return this;
        }

        public builer errorPeriod(String errorPeriod) {
            this.errorPeriod = errorPeriod;
            return this;
        }
        public builer sensorCode(int sensorCode) {
            this.sensorCode = sensorCode;
            return this;
        }

        public builer dateError(String dataError) {
            this.dataError = dataError;
            return this;
        }

        public builer errorValue(double errorValue) {
            this.errorValue = errorValue;
            return this;
        }

        public builer equipmentError(String equipmentError) {
            this.equipmentError = equipmentError;
            return this;
        }

        public builer createTime(Date createTime) {
            this.createTime = createTime;
            return this;
        }
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

    public String getDataError() {
        return dataError;
    }

    public void setDataError(String dataError) {
        this.dataError = dataError;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AbnormalDetailEntity() {
    }
}
