package com.siti.wisdomhydrologic.document.vo;

/**
 * Created by dell on 2019/10/16.
 */
public class RainGroupVo {

    Double sum;
    String Month;
    String StationName;

    public String getStationName() {
        return StationName;
    }

    public void setStationName(String stationName) {
        StationName = stationName;
    }

    @Override
    public String toString() {
        return "RainGroupVo{" + "sum=" + sum + ", Month='" + Month + '\'' + '}';
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public String getMonth() {
        return Month;
    }

    public void setMonth(String month) {
        Month = month;
    }

    public RainGroupVo(Double sum, String stationName) {
        this.sum = sum;
        StationName = stationName;
    }

    public RainGroupVo() {
    }
}
