package com.siti.wisdomhydrologic.realmessageprocess.entity;

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
    Date createTime;

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
