package com.siti.wisdomhydrologic.operation.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;

public class ReportManageMantain {

    @Excel(name = "reportId", height = 11, width = 10)
    private int reportId;

    @Excel(name = "manageOrgId", height = 11, width = 10)
    private int manageOrgId;

    @Excel(name = "manageOrgName", height = 11, width = 10)
    private String manageOrgName;

    @Excel(name = "mantainMonth", height = 11, width = 10)
    private String mantainMonth;

    @Excel(name = "mantainHour", height = 11, width = 10)
    private String mantainHour;

    @Excel(name = "tempHuimidityException", height = 11, width = 10)
    private int tempHuimidityException;

    @Excel(name = "serverTimeException", height = 11, width = 10)
    private int serverTimeException;

    @Excel(name = "databaseServerException", height = 11, width = 10)
    private int databaseServerException;

    @Excel(name = "communicateServerException", height = 11, width = 10)
    private int communicateServerException;

    @Excel(name = "applicationServerException", height = 11, width = 10)
    private int applicationServerException;

    @Excel(name = "webServerException", height = 11, width = 10)
    private int webServerException;

    private int workStation;

    @Excel(name = "changtongRateException", height = 11, width = 10)
    private int changtongRateException;

    @Excel(name = "voltageException", height = 11, width = 10)
    private int voltageException;

    @Excel(name = "voltageProcessLineException", height = 11, width = 10)
    private int voltageProcessLineException;

    @Excel(name = "dayRainReportException", height = 11, width = 10)
    private int dayRainReportException;

    @Excel(name = "rainBarException", height = 11, width = 10)
    private int rainBarException;

    @Excel(name = "daySeaLevelReportException", height = 11, width = 10)
    private int daySeaLevelReportException;

    @Excel(name = "seaLeveProcessLineException", height = 11, width = 10)
    private int seaLeveProcessLineException;

    @Excel(name = "otherReportException", height = 11, width = 10)
    private int otherReportException;

    @Excel(name = "remark", height = 11, width = 10)
    private String remark;

    @Excel(name = "createBy", height = 11, width = 10)
    private String createBy;

    @Excel(name = "createTime", height = 11, width = 10)
    private String createTime;


    public int getWorkStation() {
        return workStation;
    }

    public void setWorkStation(int workStation) {
        this.workStation = workStation;
    }

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
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


    public String getMantainMonth() {
        return mantainMonth;
    }

    public void setMantainMonth(String mantainMonth) {
        this.mantainMonth = mantainMonth;
    }


    public String getMantainHour() {
        return mantainHour;
    }

    public void setMantainHour(String mantainHour) {
        this.mantainHour = mantainHour;
    }


    public int getTempHuimidityException() {
        return tempHuimidityException;
    }

    public void setTempHuimidityException(int tempHuimidityException) {
        this.tempHuimidityException = tempHuimidityException;
    }


    public int getServerTimeException() {
        return serverTimeException;
    }

    public void setServerTimeException(int serverTimeException) {
        this.serverTimeException = serverTimeException;
    }


    public int getDatabaseServerException() {
        return databaseServerException;
    }

    public void setDatabaseServerException(int databaseServerException) {
        this.databaseServerException = databaseServerException;
    }


    public int getCommunicateServerException() {
        return communicateServerException;
    }

    public void setCommunicateServerException(int communicateServerException) {
        this.communicateServerException = communicateServerException;
    }


    public int getApplicationServerException() {
        return applicationServerException;
    }

    public void setApplicationServerException(int applicationServerException) {
        this.applicationServerException = applicationServerException;
    }


    public int getWebServerException() {
        return webServerException;
    }

    public void setWebServerException(int webServerException) {
        this.webServerException = webServerException;
    }


    public int getChangtongRateException() {
        return changtongRateException;
    }

    public void setChangtongRateException(int changtongRateException) {
        this.changtongRateException = changtongRateException;
    }


    public int getVoltageException() {
        return voltageException;
    }

    public void setVoltageException(int voltageException) {
        this.voltageException = voltageException;
    }


    public int getVoltageProcessLineException() {
        return voltageProcessLineException;
    }

    public void setVoltageProcessLineException(int voltageProcessLineException) {
        this.voltageProcessLineException = voltageProcessLineException;
    }


    public int getDayRainReportException() {
        return dayRainReportException;
    }

    public void setDayRainReportException(int dayRainReportException) {
        this.dayRainReportException = dayRainReportException;
    }


    public int getRainBarException() {
        return rainBarException;
    }

    public void setRainBarException(int rainBarException) {
        this.rainBarException = rainBarException;
    }


    public int getDaySeaLevelReportException() {
        return daySeaLevelReportException;
    }

    public void setDaySeaLevelReportException(int daySeaLevelReportException) {
        this.daySeaLevelReportException = daySeaLevelReportException;
    }


    public int getSeaLeveProcessLineException() {
        return seaLeveProcessLineException;
    }

    public void setSeaLeveProcessLineException(int seaLeveProcessLineException) {
        this.seaLeveProcessLineException = seaLeveProcessLineException;
    }


    public int getOtherReportException() {
        return otherReportException;
    }

    public void setOtherReportException(int otherReportException) {
        this.otherReportException = otherReportException;
    }


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    @Override
    public String toString() {
        return "ReportManageMantain{" + "reportId=" + reportId + ", manageOrgId=" + manageOrgId + ", manageOrgName='" + manageOrgName + '\'' + ", mantainMonth='" + mantainMonth + '\'' + ", mantainHour='" + mantainHour + '\'' + ", tempHuimidityException=" + tempHuimidityException + ", serverTimeException=" + serverTimeException + ", databaseServerException=" + databaseServerException + ", communicateServerException=" + communicateServerException + ", applicationServerException=" + applicationServerException + ", webServerException=" + webServerException + ", changtongRateException=" + changtongRateException + ", voltageException=" + voltageException + ", voltageProcessLineException=" + voltageProcessLineException + ", dayRainReportException=" + dayRainReportException + ", rainBarException=" + rainBarException + ", daySeaLevelReportException=" + daySeaLevelReportException + ", seaLeveProcessLineException=" + seaLeveProcessLineException + ", otherReportException=" + otherReportException + ", remark='" + remark + '\'' + ", createBy=" + createBy + ", createTime=" + createTime + '}';
    }

    public ReportManageMantain(int manageOrgId, String manageOrgName, String mantainMonth, String mantainHour, int tempHuimidityException, int serverTimeException, int databaseServerException, int communicateServerException, int applicationServerException, int webServerException, int workStation, int changtongRateException, int voltageException, int voltageProcessLineException, int dayRainReportException, int rainBarException, int daySeaLevelReportException, int seaLeveProcessLineException, int otherReportException, String remark, String createBy, String createTime) {
        this.manageOrgId = manageOrgId;
        this.manageOrgName = manageOrgName;
        this.mantainMonth = mantainMonth;
        this.mantainHour = mantainHour;
        this.tempHuimidityException = tempHuimidityException;
        this.serverTimeException = serverTimeException;
        this.databaseServerException = databaseServerException;
        this.communicateServerException = communicateServerException;
        this.applicationServerException = applicationServerException;
        this.webServerException = webServerException;
        this.workStation = workStation;
        this.changtongRateException = changtongRateException;
        this.voltageException = voltageException;
        this.voltageProcessLineException = voltageProcessLineException;
        this.dayRainReportException = dayRainReportException;
        this.rainBarException = rainBarException;
        this.daySeaLevelReportException = daySeaLevelReportException;
        this.seaLeveProcessLineException = seaLeveProcessLineException;
        this.otherReportException = otherReportException;
        this.remark = remark;
        this.createBy = createBy;
        this.createTime = createTime;
    }

    public ReportManageMantain() {
    }
}
