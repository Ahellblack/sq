package com.siti.wisdomhydrologic.mainpage.vo;

import java.util.Arrays;

/**
 * Created by dell on 2019/8/23.
 */
public class HaiKangVo {

    private String page;
    private String[] data;

    private String liveAddress;
    private String deviceSerial;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }

    public String getLiveAddress() {
        return liveAddress;
    }

    public void setLiveAddress(String liveAddress) {
        this.liveAddress = liveAddress;
    }

    public String getDeviceSerial() {
        return deviceSerial;
    }

    public void setDeviceSerial(String deviceSerial) {
        this.deviceSerial = deviceSerial;
    }

    @Override
    public String toString() {
        return "HaiKangVo{" + "page='" + page + '\'' + ", data=" + Arrays.toString(data) + ", liveAddress='" + liveAddress + '\'' + ", deviceSerial='" + deviceSerial + '\'' + '}';
    }
}
