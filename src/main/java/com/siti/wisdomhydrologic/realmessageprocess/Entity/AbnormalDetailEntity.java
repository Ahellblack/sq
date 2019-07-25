package com.siti.wisdomhydrologic.realmessageprocess.Entity;

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
    String date;
    @Id
    double sensorCode;
    Integer fiveBelow;
    Integer fiveAbove;
    Integer hourBelow;
    Integer hourAbove;
    Integer dayBelow;
    Integer dayAbove;
    Integer moreNear;
    Integer lessNear;
    Integer floatingUp;
    Integer floatingDown;
    Integer keepTime;
    Integer continueInterrupt;
    Date createTime;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getSensorCode() {
        return sensorCode;
    }

    public void setSensorCode(double sensorCode) {
        this.sensorCode = sensorCode;
    }

    public Integer getFiveBelow() {
        return fiveBelow;
    }

    public void setFiveBelow(Integer fiveBelow) {
        this.fiveBelow = fiveBelow;
    }

    public Integer getFiveAbove() {
        return fiveAbove;
    }

    public void setFiveAbove(Integer fiveAbove) {
        this.fiveAbove = fiveAbove;
    }

    public Integer getHourBelow() {
        return hourBelow;
    }

    public void setHourBelow(Integer hourBelow) {
        this.hourBelow = hourBelow;
    }

    public Integer getHourAbove() {
        return hourAbove;
    }

    public void setHourAbove(Integer hourAbove) {
        this.hourAbove = hourAbove;
    }

    public Integer getDayBelow() {
        return dayBelow;
    }

    public void setDayBelow(Integer dayBelow) {
        this.dayBelow = dayBelow;
    }

    public Integer getDayAbove() {
        return dayAbove;
    }

    public void setDayAbove(Integer dayAbove) {
        this.dayAbove = dayAbove;
    }

    public Integer getMoreNear() {
        return moreNear;
    }

    public void setMoreNear(Integer moreNear) {
        this.moreNear = moreNear;
    }

    public Integer getLessNear() {
        return lessNear;
    }

    public void setLessNear(Integer lessNear) {
        this.lessNear = lessNear;
    }

    public Integer getFloatingUp() {
        return floatingUp;
    }

    public void setFloatingUp(Integer floatingUp) {
        this.floatingUp = floatingUp;
    }

    public Integer getFloatingDown() {
        return floatingDown;
    }

    public void setFloatingDown(Integer floatingDown) {
        this.floatingDown = floatingDown;
    }

    public Integer getKeepTime() {
        return keepTime;
    }

    public void setKeepTime(Integer keepTime) {
        this.keepTime = keepTime;
    }

    public Integer getContinueInterrupt() {
        return continueInterrupt;
    }

    public void setContinueInterrupt(Integer continueInterrupt) {
        this.continueInterrupt = continueInterrupt;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
