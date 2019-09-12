package com.siti.wisdomhydrologic.mainpage.vo;

/**
 * Created by dell on 2019/8/21.
 */
public class StationMalFunction {
//北站
    private Integer NstationNumber;//北测站数
    private Integer NmalNumber;//北派单数
    private Integer NonResolveNumber;//北在处理中数
    private Integer NendResolveNumber;//北处理完数
    private Integer NnormalStationNumber;//北正常测站数
    private Integer NabnormalStationNumber;//北异常测站数
    private Integer NdownStationNumber;//北离线测站数
//南站
    private Integer SstationNumber;
    private Integer SmalNumber;
    private Integer SonResolveNumber;
    private Integer SendResolveNumber;
    private Integer SnormalStationNumber;
    private Integer SabnormalStationNumber;
    private Integer SdownStationNumber;


    private Integer requestDesignatingStatus;
    private String regionName;


    public Integer getRequestDesignatingStatus() {
        return requestDesignatingStatus;
    }

    public void setRequestDesignatingStatus(Integer requestDesignatingStatus) {
        this.requestDesignatingStatus = requestDesignatingStatus;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public Integer getNstationNumber() {
        return NstationNumber;
    }

    public void setNstationNumber(Integer nstationNumber) {
        NstationNumber = nstationNumber;
    }

    public Integer getNmalNumber() {
        return NmalNumber;
    }

    public void setNmalNumber(Integer nmalNumber) {
        NmalNumber = nmalNumber;
    }

    public Integer getNonResolveNumber() {
        return NonResolveNumber;
    }

    public void setNonResolveNumber(Integer nonResolveNumber) {
        NonResolveNumber = nonResolveNumber;
    }

    public Integer getNendResolveNumber() {
        return NendResolveNumber;
    }

    public void setNendResolveNumber(Integer nendResolveNumber) {
        NendResolveNumber = nendResolveNumber;
    }

    public Integer getSstationNumber() {
        return SstationNumber;
    }

    public void setSstationNumber(Integer sstationNumber) {
        SstationNumber = sstationNumber;
    }

    public Integer getSmalNumber() {
        return SmalNumber;
    }

    public void setSmalNumber(Integer smalNumber) {
        SmalNumber = smalNumber;
    }

    public Integer getSonResolveNumber() {
        return SonResolveNumber;
    }

    public void setSonResolveNumber(Integer sonResolveNumber) {
        SonResolveNumber = sonResolveNumber;
    }

    public Integer getSendResolveNumber() {
        return SendResolveNumber;
    }

    public void setSendResolveNumber(Integer sendResolveNumber) {
        SendResolveNumber = sendResolveNumber;
    }

    public Integer getNnormalStationNumber() {
        return NnormalStationNumber;
    }

    public void setNnormalStationNumber(Integer nnormalStationNumber) {
        NnormalStationNumber = nnormalStationNumber;
    }

    public Integer getNabnormalStationNumber() {
        return NabnormalStationNumber;
    }

    public void setNabnormalStationNumber(Integer nabnormalStationNumber) {
        NabnormalStationNumber = nabnormalStationNumber;
    }

    public Integer getNdownStationNumber() {
        return NdownStationNumber;
    }

    public void setNdownStationNumber(Integer ndownStationNumber) {
        NdownStationNumber = ndownStationNumber;
    }

    public Integer getSnormalStationNumber() {
        return SnormalStationNumber;
    }

    public void setSnormalStationNumber(Integer snormalStationNumber) {
        SnormalStationNumber = snormalStationNumber;
    }

    public Integer getSabnormalStationNumber() {
        return SabnormalStationNumber;
    }

    public void setSabnormalStationNumber(Integer sabnormalStationNumber) {
        SabnormalStationNumber = sabnormalStationNumber;
    }

    public Integer getSdownStationNumber() {
        return SdownStationNumber;
    }

    public void setSdownStationNumber(Integer sdownStationNumber) {
        SdownStationNumber = sdownStationNumber;
    }

    @Override
    public String toString() {
        return "StationMalFunction{" + "NstationNumber=" + NstationNumber + ", NmalNumber=" + NmalNumber + ", NonResolveNumber=" + NonResolveNumber + ", NendResolveNumber=" + NendResolveNumber + ", SstationNumber=" + SstationNumber + ", SmalNumber=" + SmalNumber + ", SonResolveNumber=" + SonResolveNumber + ", SendResolveNumber=" + SendResolveNumber + ", requestDesignatingStatus=" + requestDesignatingStatus + ", regionName='" + regionName + '\'' + '}';
    }

    public StationMalFunction(Integer nstationNumber, Integer nmalNumber, Integer nonResolveNumber, Integer nendResolveNumber, Integer nnormalStationNumber, Integer nabnormalStationNumber, Integer ndownStationNumber, Integer sstationNumber, Integer smalNumber, Integer sonResolveNumber, Integer sendResolveNumber, Integer snormalStationNumber, Integer sabnormalStationNumber, Integer sdownStationNumber) {
        NstationNumber = nstationNumber;
        NmalNumber = nmalNumber;
        NonResolveNumber = nonResolveNumber;
        NendResolveNumber = nendResolveNumber;
        NnormalStationNumber = nnormalStationNumber;
        NabnormalStationNumber = nabnormalStationNumber;
        NdownStationNumber = ndownStationNumber;
        SstationNumber = sstationNumber;
        SmalNumber = smalNumber;
        SonResolveNumber = sonResolveNumber;
        SendResolveNumber = sendResolveNumber;
        SnormalStationNumber = snormalStationNumber;
        SabnormalStationNumber = sabnormalStationNumber;
        SdownStationNumber = sdownStationNumber;
    }


    public StationMalFunction() {
    }
}
