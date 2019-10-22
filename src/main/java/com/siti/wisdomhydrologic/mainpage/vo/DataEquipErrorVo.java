package com.siti.wisdomhydrologic.mainpage.vo;


/**
 * Created by dell on 2019/8/23.
 */
public class DataEquipErrorVo {
    private int dataErrorNumber ;
    private int equipErrorNumber;

    private int dataAnalystNumber;
    private int equipAnalystNumber;
    private int modelNumber;
    private int typicalValueNumber;


    public int getEquipErrorNumber() {
        return equipErrorNumber;
    }

    public void setEquipErrorNumber(int equipErrorNumber) {
        this.equipErrorNumber = equipErrorNumber;
    }

    public int getDataAnalystNumber() {
        return dataAnalystNumber;
    }

    public void setDataAnalystNumber(int dataAnalystNumber) {
        this.dataAnalystNumber = dataAnalystNumber;
    }

    public int getEquipAnalystNumber() {
        return equipAnalystNumber;
    }

    public void setEquipAnalystNumber(int equipAnalystNumber) {
        this.equipAnalystNumber = equipAnalystNumber;
    }

    public int getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(int modelNumber) {
        this.modelNumber = modelNumber;
    }

    public int getTypicalValueNumber() {
        return typicalValueNumber;
    }

    public void setTypicalValueNumber(int typicalValueNumber) {
        this.typicalValueNumber = typicalValueNumber;
    }

    public int getDataErrorNumber() {
        return dataErrorNumber;
    }

    public void setDataErrorNumber(int dataErrorNumber) {
        this.dataErrorNumber = dataErrorNumber;
    }

    public DataEquipErrorVo(int dataErrorNumber, int equipErrorNumber, int dataAnalystNumber, int equipAnalystNumber, int modelNumber, int typicalValueNumber) {
        this.dataErrorNumber = dataErrorNumber;
        this.equipErrorNumber = equipErrorNumber;
        this.dataAnalystNumber = dataAnalystNumber;
        this.equipAnalystNumber = equipAnalystNumber;
        this.modelNumber = modelNumber;
        this.typicalValueNumber = typicalValueNumber;
    }

    public DataEquipErrorVo() {
    }
}
