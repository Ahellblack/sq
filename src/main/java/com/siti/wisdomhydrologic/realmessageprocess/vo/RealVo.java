package com.siti.wisdomhydrologic.realmessageprocess.vo;

import java.util.Date;

/**
 * Created by DC on 2019/7/24.
 *
 * @data ${DATA}-15:07
 */
public class RealVo {

    private int sendId;

    private Date time;

    private Double factv;

    private Integer ifch;

    private Integer cycle;

    private Integer state;

    private Integer ts;

    public int getSendId() {
        return sendId;
    }

    public void setSendId(int sendId) {
        this.sendId = sendId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Double getFactv() {
        return factv;
    }

    public void setFactv(Double factv) {
        this.factv = factv;
    }

    public Integer getIfch() {
        return ifch;
    }

    public void setIfch(Integer ifch) {
        this.ifch = ifch;
    }

    public Integer getCycle() {
        return cycle;
    }

    public void setCycle(Integer cycle) {
        this.cycle = cycle;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getTs() {
        return ts;
    }

    public void setTs(Integer ts) {
        this.ts = ts;
    }
}
