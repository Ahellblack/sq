package com.siti.wisdomhydrologic.realmessageprocess.entity;

import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;

import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by DC on 2019/7/19.
 *
 * @data ${DATA}-18:04
 */
@Table(name = "abnormal_detail")
public class AbnormalDetailEntity {
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
    double errorValue;
    String errorPeriod;
    String equipmentError;
    Date createTime;

    private AbnormalDetailEntity(builer builer){
        this. date=builer.date;
        this. sensorCode=builer.sensorCode;
        this. fiveBelow=builer.fiveBelow;
        this. fiveAbove=builer.fiveAbove;
        this. hourBelow=builer.hourBelow;
        this. hourAbove=builer.hourAbove;
        this. dayBelow=builer.dayBelow;
        this. dayAbove=builer.dayAbove;
        this. moreNear=builer.moreNear;
        this. lessNear=builer.lessNear;
        this. floatingUp= builer.floatingUp;
        this. floatingDown=builer.floatingDown;
        this. keepTime=builer.keepTime;
        this. continueInterrupt=builer.continueInterrupt;
        this. errorValue=builer.errorValue;
        this. errorPeriod=builer.errorPeriod;
        this. equipmentError=builer.equipmentError;
        this. createTime=builer.createTime;
        //java 构建器
    }

    public static class builer{
        String date;
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
        double errorValue;
        String errorPeriod;
        String equipmentError;
        Date createTime;

        public AbnormalDetailEntity build(){
            return new AbnormalDetailEntity(this);
        }

        public builer date(String date) {
            this.date = date;
            return this;
        }

        public builer sensorCode(int sensorCode) {
            this.sensorCode = sensorCode;
            return this;
        }

        public builer fiveBelow(int fiveBelow) {
            this.fiveBelow = fiveBelow;
            return this;
        }

        public builer fiveAbove(int fiveAbove) {
            this.fiveAbove = fiveAbove;
            return this;
        }

        public builer hourBelow(int hourBelow) {
            this.hourBelow = hourBelow;
            return this;
        }

        public builer hourAbove(int hourAbove) {
            this.hourAbove = hourAbove;
            return this;
        }

        public builer dayBelow(int dayBelow) {
            this.dayBelow = dayBelow;
            return this;
        }

        public builer dayAbove(int dayAbove) {
            this.dayAbove = dayAbove;
            return this;
        }

        public builer moreNear(int moreNear) {
            this.moreNear = moreNear;
            return this;
        }

        public builer lessNear(int lessNear) {
            this.lessNear = lessNear;
            return this;
        }

        public builer floatingUp(int floatingUp) {
            this.floatingUp = floatingUp;
            return this;
        }

        public builer floatingDown(int floatingDown) {
            this.floatingDown = floatingDown;
            return this;
        }

        public builer keepTime(int keepTime) {
            this.keepTime = keepTime;
            return this;
        }

        public builer continueInterrupt(int continueInterrupt) {
            this.continueInterrupt = continueInterrupt;
            return this;
        }

        public builer errorValue(double errorValue) {
            this.errorValue = errorValue;
            return this;
        }

        public builer errorPeriod(String errorPeriod) {
            this.errorPeriod = errorPeriod;
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

    public String getErrorPeriod() {
        return errorPeriod;
    }

    public void setErrorPeriod(String errorPeriod) {
        this.errorPeriod = errorPeriod;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
