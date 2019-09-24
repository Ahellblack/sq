package com.siti.wisdomhydrologic.statistics.entity;

/**
 * Created by dell on 2019/9/23.
 */
public class BrokenType {

    private Integer number;
    private String brokenAccording;
    private String brokenAccordingId;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getBrokenAccording() {
        return brokenAccording;
    }

    public void setBrokenAccording(String brokenAccording) {
        this.brokenAccording = brokenAccording;
    }

    public String getBrokenAccordingId() {
        return brokenAccordingId;
    }

    public void setBrokenAccordingId(String brokenAccordingId) {
        this.brokenAccordingId = brokenAccordingId;
    }

    /*select count(*) as number,d.broken_according,d.broken_according_id,d.station_id from (
            select c.broken_according,c.broken_according_id,a.station_id from report_station_broken a right join config_river_station b on a.station_id = b.station_id
            right join config_abnormal_dictionary c on c.broken_according_id = a.broken_according_id  ) d
    where d.station_id is not null
    GROUP BY d.broken_according_id*/
}
