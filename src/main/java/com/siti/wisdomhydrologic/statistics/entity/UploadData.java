package com.siti.wisdomhydrologic.statistics.entity;


/**
 * Created by dell on 2019/11/7.
 */
public class UploadData {
     private Integer dataType;
     private String yearMonth ;
     private Integer value;
     private String updateTime;

    @Override
    public String toString() {
        return "UploadData{" + "dataType=" + dataType + ", yearMonth='" + yearMonth + '\'' + ", value=" + value + ", updateTime='" + updateTime + '\'' + '}';
    }

    public UploadData() {
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public String getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
