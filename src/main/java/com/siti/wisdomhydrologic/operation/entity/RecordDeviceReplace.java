package com.siti.wisdomhydrologic.operation.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class RecordDeviceReplace {

    @ApiModelProperty(value = "自增id")
    private int reportId;
    @ApiModelProperty(value = "测站id")
    private String stationCode;
    @ApiModelProperty(value = "测站名")
    private String stationName;
    @ApiModelProperty(value = "测站组织id")
    private int manageOrgId;
    @ApiModelProperty(value = "测站组织名")
    private String manageOrgName;
    @ApiModelProperty(value = "更换日期")
    private String replaceDate;
    @ApiModelProperty(value = "原设备型号")
    private String originDeviceTypeCode;
    @ApiModelProperty(value = "原设备编号" )
    private String originDeviceCode;
    @ApiModelProperty(value = "原资产归属单位ID")
    private int originOrgId;
    @ApiModelProperty(value = "原资产归属单位名称")
    private String originOrgName;
    @ApiModelProperty(value = "现设备型号")
    private String newDeviceTypeCode;
    @ApiModelProperty(value = "现设备编号")
    private String newDeviceCode;
    @ApiModelProperty(value = "现设备资产归属单位ID")
    private int newOrgId;
    @ApiModelProperty(value = "现设备自查归属单位名称")
    private String newOrgName;
    @ApiModelProperty(value = "更换原因")
    private String replaceReason;
    @ApiModelProperty(value = "填报人")
    private String createBy;
    @ApiModelProperty(value = "填报时间")
    private String createTime;
    @ApiModelProperty(value = "修改人")
    private int updateBy;
    @ApiModelProperty(value = "现设备名称")
    private String newDeviceName;
    @ApiModelProperty(value = "原设备名称")
    private String originDeviceName;
    public RecordDeviceReplace() {
        super();
    }
    private RecordDeviceReplace(Builder builder) {
        setReportId(builder.reportId);
        setStationCode(builder.stationCode);
        setStationName(builder.stationName);
        setManageOrgId(builder.manageOrgId);
        setManageOrgName(builder.manageOrgName);
        setReplaceDate(builder.replaceDate);
        setOriginDeviceTypeCode(builder.originDeviceTypeCode);
        setOriginDeviceCode(builder.originDeviceCode);
        setOriginOrgId(builder.originOrgId);
        setOriginOrgName(builder.originOrgName);
        setNewDeviceTypeCode(builder.newDeviceTypeCode);
        setNewDeviceCode(builder.newDeviceCode);
        setNewOrgId(builder.newOrgId);
        setNewOrgName(builder.newOrgName);
        setReplaceReason(builder.replaceReason);
        setCreateBy(builder.createBy);
        setCreateTime(builder.createTime);
        setUpdateBy(builder.updateBy);
        setNewDeviceName(builder.newDeviceName);
        setOriginDeviceName(builder.originDeviceName);
    }


    public String getNewDeviceName() {
        return newDeviceName;
    }

    public void setNewDeviceName(String newDeviceName) {
        this.newDeviceName = newDeviceName;
    }

    public String getOriginDeviceName() {
        return originDeviceName;
    }

    public void setOriginDeviceName(String originDeviceName) {
        this.originDeviceName = originDeviceName;
    }

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }


    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }


    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }


    public int getManageOrgId() {
        return manageOrgId;
    }

    public void setManageOrgId(int manageOrgId) {
        this.manageOrgId = manageOrgId;
    }


    public String getManageOrgName() {
        return manageOrgName;
    }

    public void setManageOrgName(String manageOrgName) {
        this.manageOrgName = manageOrgName;
    }


    public String getReplaceDate() {
        return replaceDate;
    }

    public void setReplaceDate(String replaceDate) {
        this.replaceDate = replaceDate;
    }


    public String getOriginDeviceTypeCode() {
        return originDeviceTypeCode;
    }

    public void setOriginDeviceTypeCode(String originDeviceTypeCode) {
        this.originDeviceTypeCode = originDeviceTypeCode;
    }


    public String getOriginDeviceCode() {
        return originDeviceCode;
    }

    public void setOriginDeviceCode(String originDeviceCode) {
        this.originDeviceCode = originDeviceCode;
    }


    public int getOriginOrgId() {
        return originOrgId;
    }

    public void setOriginOrgId(int originOrgId) {
        this.originOrgId = originOrgId;
    }


    public String getOriginOrgName() {
        return originOrgName;
    }

    public void setOriginOrgName(String originOrgName) {
        this.originOrgName = originOrgName;
    }


    public String getNewDeviceTypeCode() {
        return newDeviceTypeCode;
    }

    public void setNewDeviceTypeCode(String newDeviceTypeCode) {
        this.newDeviceTypeCode = newDeviceTypeCode;
    }


    public String getNewDeviceCode() {
        return newDeviceCode;
    }

    public void setNewDeviceCode(String newDeviceCode) {
        this.newDeviceCode = newDeviceCode;
    }


    public int getNewOrgId() {
        return newOrgId;
    }

    public void setNewOrgId(int newOrgId) {
        this.newOrgId = newOrgId;
    }


    public String getNewOrgName() {
        return newOrgName;
    }

    public void setNewOrgName(String newOrgName) {
        this.newOrgName = newOrgName;
    }


    public String getReplaceReason() {
        return replaceReason;
    }

    public void setReplaceReason(String replaceReason) {
        this.replaceReason = replaceReason;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }


    public int getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(int updateBy) {
        this.updateBy = updateBy;
    }


    @Override
    public String toString() {
        return "RecordDeviceReplace{" + "reportId=" + reportId + ", stationCode='" + stationCode + '\'' + ", stationName='" + stationName + '\'' + ", manageOrgId=" + manageOrgId + ", manageOrgName='" + manageOrgName + '\'' + ", replaceDate='" + replaceDate + '\'' + ", originDeviceTypeCode='" + originDeviceTypeCode + '\'' + ", originDeviceCode='" + originDeviceCode + '\'' + ", originOrgId=" + originOrgId + ", originOrgName='" + originOrgName + '\'' + ", newDeviceTypeCode='" + newDeviceTypeCode + '\'' + ", newDeviceCode='" + newDeviceCode + '\'' + ", newOrgId=" + newOrgId + ", newOrgName='" + newOrgName + '\'' + ", replaceReason='" + replaceReason + '\'' + ", createBy='" + createBy + '\'' + ", createTime='" + createTime + '\'' + ", updateBy=" + updateBy + ", newDeviceName='" + newDeviceName + '\'' + ", originDeviceName='" + originDeviceName + '\'' + '}';
    }


    public static final class Builder {
        private int reportId;
        private String stationCode;
        private String stationName;
        private int manageOrgId;
        private String manageOrgName;
        private String replaceDate;
        private String originDeviceTypeCode;
        private String originDeviceCode;
        private int originOrgId;
        private String originOrgName;
        private String newDeviceTypeCode;
        private String newDeviceCode;
        private int newOrgId;
        private String newOrgName;
        private String replaceReason;
        private String createBy;
        private String createTime;
        private int updateBy;
        private String newDeviceName;
        private String originDeviceName;

        public Builder() {
        }

        public Builder reportId(int val) {
            reportId = val;
            return this;
        }

        public Builder stationCode(String val) {
            stationCode = val;
            return this;
        }

        public Builder stationName(String val) {
            stationName = val;
            return this;
        }

        public Builder manageOrgId(int val) {
            manageOrgId = val;
            return this;
        }

        public Builder manageOrgName(String val) {
            manageOrgName = val;
            return this;
        }

        public Builder replaceDate(String val) {
            replaceDate = val;
            return this;
        }

        public Builder originDeviceTypeCode(String val) {
            originDeviceTypeCode = val;
            return this;
        }

        public Builder originDeviceCode(String val) {
            originDeviceCode = val;
            return this;
        }

        public Builder originOrgId(int val) {
            originOrgId = val;
            return this;
        }

        public Builder originOrgName(String val) {
            originOrgName = val;
            return this;
        }

        public Builder newDeviceTypeCode(String val) {
            newDeviceTypeCode = val;
            return this;
        }

        public Builder newDeviceCode(String val) {
            newDeviceCode = val;
            return this;
        }

        public Builder newOrgId(int val) {
            newOrgId = val;
            return this;
        }

        public Builder newOrgName(String val) {
            newOrgName = val;
            return this;
        }

        public Builder replaceReason(String val) {
            replaceReason = val;
            return this;
        }

        public Builder createBy(String val) {
            createBy = val;
            return this;
        }

        public Builder createTime(String val) {
            createTime = val;
            return this;
        }

        public Builder updateBy(int val) {
            updateBy = val;
            return this;
        }

        public Builder newDeviceName(String val) {
            newDeviceName = val;
            return this;
        }

        public Builder originDeviceName(String val) {
            originDeviceName = val;
            return this;
        }

        public RecordDeviceReplace build() {
            return new RecordDeviceReplace(this);
        }
    }
}
