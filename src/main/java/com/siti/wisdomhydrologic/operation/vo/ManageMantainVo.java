package com.siti.wisdomhydrologic.operation.vo;

/**
 * Created by dell on 2019/8/28.
 */
public class ManageMantainVo {

    private int sysOrg;
    private String brokenName;
    private String createTime;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getSysOrg() {
        return sysOrg;
    }

    public void setSysOrg(int sysOrg) {
        this.sysOrg = sysOrg;
    }

    public String getBrokenName() {
        return brokenName;
    }

    public void setBrokenName(String brokenName) {
        this.brokenName = brokenName;
    }
}
