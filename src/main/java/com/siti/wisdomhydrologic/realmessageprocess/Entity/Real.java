package com.siti.wisdomhydrologic.realmessageprocess.Entity;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by DC on 2019/7/24.
 *
 * @data ${DATA}-15:11
 */
@Table(name="real")
public class Real {
    @Id
    private int sensorode;
    @Id
    private Date time;

    private Double realVal;

    private Integer modified;

    private Integer cycle;

    private Integer state;

    private Integer ts;

    public int getSensorode() {
        return sensorode;
    }

    public void setSensorode(int sensorode) {
        this.sensorode = sensorode;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Double getRealVal() {
        return realVal;
    }

    public void setRealVal(Double realVal) {
        this.realVal = realVal;
    }

    public Integer getModified() {
        return modified;
    }

    public void setModified(Integer modified) {
        this.modified = modified;
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

    /*  CREATE TABLE `real` (
            `sensor_code` int(10) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
            `real_val` decimal(11,3) DEFAULT NULL,
  `modified` enum('0','1') COLLATE utf8_bin DEFAULT '0' COMMENT '0未处理 1已经处理',
            `cycle` int(11) DEFAULT NULL,
  `state` tinyint(4) DEFAULT NULL,
  `ts` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

*/
}
