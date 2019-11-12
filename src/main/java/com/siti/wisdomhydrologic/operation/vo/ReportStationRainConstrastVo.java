package com.siti.wisdomhydrologic.operation.vo;

/**
 * Created by zyw on 2019/8/2.
 */
public class ReportStationRainConstrastVo {

    private int reportId;
    private String stationCode;
    private String stationName;
    private int manageOrgId;
    private String manageOrgName;
    private String dataYearMonth;
    private String total;
    private String remark;
    private String createTime;
    private String createBy;
    private String updateTime;
    private int updateBy;

 /*   *//**
     * 字段列表
     * *//*
    private List<Double> autoList;
    private List<Double> baseList;
    private List<Double> diffList;*/
    /**
     * auto字段
     * base字段
     * diff字段
     * */
    private String day1Auto;
    private String day1Base;
    private String day1Diff;
    private String day2Auto;
    private String day2Base;
    private String day2Diff;
    private String day3Auto;
    private String day3Base;
    private String day3Diff;
    private String day4Auto;
    private String day4Base;
    private String day4Diff;
    private String day5Auto;
    private String day5Base;
    private String day5Diff;
    private String day6Auto;
    private String day6Base;
    private String day6Diff;
    private String day7Auto;
    private String day7Base;
    private String day7Diff;
    private String day8Auto;
    private String day8Base;
    private String day8Diff;
    private String day9Auto;
    private String day9Base;
    private String day9Diff;
    private String day10Auto;
    private String day10Base;
    private String day10Diff;
    private String day11Auto;
    private String day11Base;
    private String day11Diff;
    private String day12Auto;
    private String day12Base;
    private String day12Diff;
    private String day13Auto;
    private String day13Base;
    private String day13Diff;
    private String day14Auto;
    private String day14Base;
    private String day14Diff;
    private String day15Auto;
    private String day15Base;
    private String day15Diff;
    private String day16Auto;
    private String day16Base;
    private String day16Diff;
    private String day17Auto;
    private String day17Base;
    private String day17Diff;
    private String day18Auto;
    private String day18Base;
    private String day18Diff;
    private String day19Auto;
    private String day19Base;
    private String day19Diff;
    private String day20Auto;
    private String day20Base;
    private String day20Diff;
    private String day21Auto;
    private String day21Base;
    private String day21Diff;
    private String day22Auto;
    private String day22Base;
    private String day22Diff;
    private String day23Auto;
    private String day23Base;
    private String day23Diff;
    private String day24Auto;
    private String day24Base;
    private String day24Diff;
    private String day25Auto;
    private String day25Base;
    private String day25Diff;
    private String day26Auto;
    private String day26Base;
    private String day26Diff;
    private String day27Auto;
    private String day27Base;
    private String day27Diff;
    private String day28Auto;
    private String day28Base;
    private String day28Diff;
    private String day29Auto;
    private String day29Base;
    private String day29Diff;
    private String day30Auto;
    private String day30Base;
    private String day30Diff;
    private String day31Auto;
    private String day31Base;
    private String day31Diff;
    //total
    private String autoTotal;
    private String baseTotal;
    private String diffTotal;

    @Override
    public String toString() {
        return "ReportStationRainConstrastVo{" + "reportId=" + reportId + ", stationCode='" + stationCode + '\'' + ", stationName='" + stationName + '\'' + ", manageOrgId=" + manageOrgId + ", manageOrgName='" + manageOrgName + '\'' + ", dataYearMonth='" + dataYearMonth + '\'' + ", total='" + total + '\'' + ", remark='" + remark + '\'' + ", createTime='" + createTime + '\'' + ", createBy='" + createBy + '\'' + ", updateTime='" + updateTime + '\'' + ", updateBy=" + updateBy + ", day1Auto='" + day1Auto + '\'' + ", day1Base='" + day1Base + '\'' + ", day1Diff='" + day1Diff + '\'' + ", day2Auto='" + day2Auto + '\'' + ", day2Base='" + day2Base + '\'' + ", day2Diff='" + day2Diff + '\'' + ", day3Auto='" + day3Auto + '\'' + ", day3Base='" + day3Base + '\'' + ", day3Diff='" + day3Diff + '\'' + ", day4Auto='" + day4Auto + '\'' + ", day4Base='" + day4Base + '\'' + ", day4Diff='" + day4Diff + '\'' + ", day5Auto='" + day5Auto + '\'' + ", day5Base='" + day5Base + '\'' + ", day5Diff='" + day5Diff + '\'' + ", day6Auto='" + day6Auto + '\'' + ", day6Base='" + day6Base + '\'' + ", day6Diff='" + day6Diff + '\'' + ", day7Auto='" + day7Auto + '\'' + ", day7Base='" + day7Base + '\'' + ", day7Diff='" + day7Diff + '\'' + ", day8Auto='" + day8Auto + '\'' + ", day8Base='" + day8Base + '\'' + ", day8Diff='" + day8Diff + '\'' + ", day9Auto='" + day9Auto + '\'' + ", day9Base='" + day9Base + '\'' + ", day9Diff='" + day9Diff + '\'' + ", day10Auto='" + day10Auto + '\'' + ", day10Base='" + day10Base + '\'' + ", day10Diff='" + day10Diff + '\'' + ", day11Auto='" + day11Auto + '\'' + ", day11Base='" + day11Base + '\'' + ", day11Diff='" + day11Diff + '\'' + ", day12Auto='" + day12Auto + '\'' + ", day12Base='" + day12Base + '\'' + ", day12Diff='" + day12Diff + '\'' + ", day13Auto='" + day13Auto + '\'' + ", day13Base='" + day13Base + '\'' + ", day13Diff='" + day13Diff + '\'' + ", day14Auto='" + day14Auto + '\'' + ", day14Base='" + day14Base + '\'' + ", day14Diff='" + day14Diff + '\'' + ", day15Auto='" + day15Auto + '\'' + ", day15Base='" + day15Base + '\'' + ", day15Diff='" + day15Diff + '\'' + ", day16Auto='" + day16Auto + '\'' + ", day16Base='" + day16Base + '\'' + ", day16Diff='" + day16Diff + '\'' + ", day17Auto='" + day17Auto + '\'' + ", day17Base='" + day17Base + '\'' + ", day17Diff='" + day17Diff + '\'' + ", day18Auto='" + day18Auto + '\'' + ", day18Base='" + day18Base + '\'' + ", day18Diff='" + day18Diff + '\'' + ", day19Auto='" + day19Auto + '\'' + ", day19Base='" + day19Base + '\'' + ", day19Diff='" + day19Diff + '\'' + ", day20Auto='" + day20Auto + '\'' + ", day20Base='" + day20Base + '\'' + ", day20Diff='" + day20Diff + '\'' + ", day21Auto='" + day21Auto + '\'' + ", day21Base='" + day21Base + '\'' + ", day21Diff='" + day21Diff + '\'' + ", day22Auto='" + day22Auto + '\'' + ", day22Base='" + day22Base + '\'' + ", day22Diff='" + day22Diff + '\'' + ", day23Auto='" + day23Auto + '\'' + ", day23Base='" + day23Base + '\'' + ", day23Diff='" + day23Diff + '\'' + ", day24Auto='" + day24Auto + '\'' + ", day24Base='" + day24Base + '\'' + ", day24Diff='" + day24Diff + '\'' + ", day25Auto='" + day25Auto + '\'' + ", day25Base='" + day25Base + '\'' + ", day25Diff='" + day25Diff + '\'' + ", day26Auto='" + day26Auto + '\'' + ", day26Base='" + day26Base + '\'' + ", day26Diff='" + day26Diff + '\'' + ", day27Auto='" + day27Auto + '\'' + ", day27Base='" + day27Base + '\'' + ", day27Diff='" + day27Diff + '\'' + ", day28Auto='" + day28Auto + '\'' + ", day28Base='" + day28Base + '\'' + ", day28Diff='" + day28Diff + '\'' + ", day29Auto='" + day29Auto + '\'' + ", day29Base='" + day29Base + '\'' + ", day29Diff='" + day29Diff + '\'' + ", day30Auto='" + day30Auto + '\'' + ", day30Base='" + day30Base + '\'' + ", day30Diff='" + day30Diff + '\'' + ", day31Auto='" + day31Auto + '\'' + ", day31Base='" + day31Base + '\'' + ", day31Diff='" + day31Diff + '\'' + ", autoTotal='" + autoTotal + '\'' + ", baseTotal='" + baseTotal + '\'' + ", diffTotal='" + diffTotal + '\'' + '}';
    }

    public String getAutoTotal() {
        return autoTotal;
    }

    public void setAutoTotal(String autoTotal) {
        this.autoTotal = autoTotal;
    }

    public String getBaseTotal() {
        return baseTotal;
    }

    public void setBaseTotal(String baseTotal) {
        this.baseTotal = baseTotal;
    }

    public String getDiffTotal() {
        return diffTotal;
    }

    public void setDiffTotal(String diffTotal) {
        this.diffTotal = diffTotal;
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

    public String getDataYearMonth() {
        return dataYearMonth;
    }

    public void setDataYearMonth(String dataYearMonth) {
        this.dataYearMonth = dataYearMonth;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public int getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(int updateBy) {
        this.updateBy = updateBy;
    }
/*

    public List<Double> getAutoList() {
        return autoList;
    }

    public void setAutoList(List<Double> autoList) {
        this.autoList = autoList;
    }

    public List<Double> getBaseList() {
        return baseList;
    }

    public void setBaseList(List<Double> baseList) {
        this.baseList = baseList;
    }

    public List<Double> getDiffList() {
        return diffList;
    }

    public void setDiffList(List<Double> diffList) {
        this.diffList = diffList;
    }
*/

    public String getDay1Auto() {
        return day1Auto;
    }

    public void setDay1Auto(String day1Auto) {
        this.day1Auto = day1Auto;
    }

    public String getDay1Base() {
        return day1Base;
    }

    public void setDay1Base(String day1Base) {
        this.day1Base = day1Base;
    }

    public String getDay1Diff() {
        return day1Diff;
    }

    public void setDay1Diff(String day1Diff) {
        this.day1Diff = day1Diff;
    }

    public String getDay2Auto() {
        return day2Auto;
    }

    public void setDay2Auto(String day2Auto) {
        this.day2Auto = day2Auto;
    }

    public String getDay2Base() {
        return day2Base;
    }

    public void setDay2Base(String day2Base) {
        this.day2Base = day2Base;
    }

    public String getDay2Diff() {
        return day2Diff;
    }

    public void setDay2Diff(String day2Diff) {
        this.day2Diff = day2Diff;
    }

    public String getDay3Auto() {
        return day3Auto;
    }

    public void setDay3Auto(String day3Auto) {
        this.day3Auto = day3Auto;
    }

    public String getDay3Base() {
        return day3Base;
    }

    public void setDay3Base(String day3Base) {
        this.day3Base = day3Base;
    }

    public String getDay3Diff() {
        return day3Diff;
    }

    public void setDay3Diff(String day3Diff) {
        this.day3Diff = day3Diff;
    }

    public String getDay4Auto() {
        return day4Auto;
    }

    public void setDay4Auto(String day4Auto) {
        this.day4Auto = day4Auto;
    }

    public String getDay4Base() {
        return day4Base;
    }

    public void setDay4Base(String day4Base) {
        this.day4Base = day4Base;
    }

    public String getDay4Diff() {
        return day4Diff;
    }

    public void setDay4Diff(String day4Diff) {
        this.day4Diff = day4Diff;
    }

    public String getDay5Auto() {
        return day5Auto;
    }

    public void setDay5Auto(String day5Auto) {
        this.day5Auto = day5Auto;
    }

    public String getDay5Base() {
        return day5Base;
    }

    public void setDay5Base(String day5Base) {
        this.day5Base = day5Base;
    }

    public String getDay5Diff() {
        return day5Diff;
    }

    public void setDay5Diff(String day5Diff) {
        this.day5Diff = day5Diff;
    }

    public String getDay6Auto() {
        return day6Auto;
    }

    public void setDay6Auto(String day6Auto) {
        this.day6Auto = day6Auto;
    }

    public String getDay6Base() {
        return day6Base;
    }

    public void setDay6Base(String day6Base) {
        this.day6Base = day6Base;
    }

    public String getDay6Diff() {
        return day6Diff;
    }

    public void setDay6Diff(String day6Diff) {
        this.day6Diff = day6Diff;
    }

    public String getDay7Auto() {
        return day7Auto;
    }

    public void setDay7Auto(String day7Auto) {
        this.day7Auto = day7Auto;
    }

    public String getDay7Base() {
        return day7Base;
    }

    public void setDay7Base(String day7Base) {
        this.day7Base = day7Base;
    }

    public String getDay7Diff() {
        return day7Diff;
    }

    public void setDay7Diff(String day7Diff) {
        this.day7Diff = day7Diff;
    }

    public String getDay8Auto() {
        return day8Auto;
    }

    public void setDay8Auto(String day8Auto) {
        this.day8Auto = day8Auto;
    }

    public String getDay8Base() {
        return day8Base;
    }

    public void setDay8Base(String day8Base) {
        this.day8Base = day8Base;
    }

    public String getDay8Diff() {
        return day8Diff;
    }

    public void setDay8Diff(String day8Diff) {
        this.day8Diff = day8Diff;
    }

    public String getDay9Auto() {
        return day9Auto;
    }

    public void setDay9Auto(String day9Auto) {
        this.day9Auto = day9Auto;
    }

    public String getDay9Base() {
        return day9Base;
    }

    public void setDay9Base(String day9Base) {
        this.day9Base = day9Base;
    }

    public String getDay9Diff() {
        return day9Diff;
    }

    public void setDay9Diff(String day9Diff) {
        this.day9Diff = day9Diff;
    }

    public String getDay10Auto() {
        return day10Auto;
    }

    public void setDay10Auto(String day10Auto) {
        this.day10Auto = day10Auto;
    }

    public String getDay10Base() {
        return day10Base;
    }

    public void setDay10Base(String day10Base) {
        this.day10Base = day10Base;
    }

    public String getDay10Diff() {
        return day10Diff;
    }

    public void setDay10Diff(String day10Diff) {
        this.day10Diff = day10Diff;
    }

    public String getDay11Auto() {
        return day11Auto;
    }

    public void setDay11Auto(String day11Auto) {
        this.day11Auto = day11Auto;
    }

    public String getDay11Base() {
        return day11Base;
    }

    public void setDay11Base(String day11Base) {
        this.day11Base = day11Base;
    }

    public String getDay11Diff() {
        return day11Diff;
    }

    public void setDay11Diff(String day11Diff) {
        this.day11Diff = day11Diff;
    }

    public String getDay12Auto() {
        return day12Auto;
    }

    public void setDay12Auto(String day12Auto) {
        this.day12Auto = day12Auto;
    }

    public String getDay12Base() {
        return day12Base;
    }

    public void setDay12Base(String day12Base) {
        this.day12Base = day12Base;
    }

    public String getDay12Diff() {
        return day12Diff;
    }

    public void setDay12Diff(String day12Diff) {
        this.day12Diff = day12Diff;
    }

    public String getDay13Auto() {
        return day13Auto;
    }

    public void setDay13Auto(String day13Auto) {
        this.day13Auto = day13Auto;
    }

    public String getDay13Base() {
        return day13Base;
    }

    public void setDay13Base(String day13Base) {
        this.day13Base = day13Base;
    }

    public String getDay13Diff() {
        return day13Diff;
    }

    public void setDay13Diff(String day13Diff) {
        this.day13Diff = day13Diff;
    }

    public String getDay14Auto() {
        return day14Auto;
    }

    public void setDay14Auto(String day14Auto) {
        this.day14Auto = day14Auto;
    }

    public String getDay14Base() {
        return day14Base;
    }

    public void setDay14Base(String day14Base) {
        this.day14Base = day14Base;
    }

    public String getDay14Diff() {
        return day14Diff;
    }

    public void setDay14Diff(String day14Diff) {
        this.day14Diff = day14Diff;
    }

    public String getDay15Auto() {
        return day15Auto;
    }

    public void setDay15Auto(String day15Auto) {
        this.day15Auto = day15Auto;
    }

    public String getDay15Base() {
        return day15Base;
    }

    public void setDay15Base(String day15Base) {
        this.day15Base = day15Base;
    }

    public String getDay15Diff() {
        return day15Diff;
    }

    public void setDay15Diff(String day15Diff) {
        this.day15Diff = day15Diff;
    }

    public String getDay16Auto() {
        return day16Auto;
    }

    public void setDay16Auto(String day16Auto) {
        this.day16Auto = day16Auto;
    }

    public String getDay16Base() {
        return day16Base;
    }

    public void setDay16Base(String day16Base) {
        this.day16Base = day16Base;
    }

    public String getDay16Diff() {
        return day16Diff;
    }

    public void setDay16Diff(String day16Diff) {
        this.day16Diff = day16Diff;
    }

    public String getDay17Auto() {
        return day17Auto;
    }

    public void setDay17Auto(String day17Auto) {
        this.day17Auto = day17Auto;
    }

    public String getDay17Base() {
        return day17Base;
    }

    public void setDay17Base(String day17Base) {
        this.day17Base = day17Base;
    }

    public String getDay17Diff() {
        return day17Diff;
    }

    public void setDay17Diff(String day17Diff) {
        this.day17Diff = day17Diff;
    }

    public String getDay18Auto() {
        return day18Auto;
    }

    public void setDay18Auto(String day18Auto) {
        this.day18Auto = day18Auto;
    }

    public String getDay18Base() {
        return day18Base;
    }

    public void setDay18Base(String day18Base) {
        this.day18Base = day18Base;
    }

    public String getDay18Diff() {
        return day18Diff;
    }

    public void setDay18Diff(String day18Diff) {
        this.day18Diff = day18Diff;
    }

    public String getDay19Auto() {
        return day19Auto;
    }

    public void setDay19Auto(String day19Auto) {
        this.day19Auto = day19Auto;
    }

    public String getDay19Base() {
        return day19Base;
    }

    public void setDay19Base(String day19Base) {
        this.day19Base = day19Base;
    }

    public String getDay19Diff() {
        return day19Diff;
    }

    public void setDay19Diff(String day19Diff) {
        this.day19Diff = day19Diff;
    }

    public String getDay20Auto() {
        return day20Auto;
    }

    public void setDay20Auto(String day20Auto) {
        this.day20Auto = day20Auto;
    }

    public String getDay20Base() {
        return day20Base;
    }

    public void setDay20Base(String day20Base) {
        this.day20Base = day20Base;
    }

    public String getDay20Diff() {
        return day20Diff;
    }

    public void setDay20Diff(String day20Diff) {
        this.day20Diff = day20Diff;
    }

    public String getDay21Auto() {
        return day21Auto;
    }

    public void setDay21Auto(String day21Auto) {
        this.day21Auto = day21Auto;
    }

    public String getDay21Base() {
        return day21Base;
    }

    public void setDay21Base(String day21Base) {
        this.day21Base = day21Base;
    }

    public String getDay21Diff() {
        return day21Diff;
    }

    public void setDay21Diff(String day21Diff) {
        this.day21Diff = day21Diff;
    }

    public String getDay22Auto() {
        return day22Auto;
    }

    public void setDay22Auto(String day22Auto) {
        this.day22Auto = day22Auto;
    }

    public String getDay22Base() {
        return day22Base;
    }

    public void setDay22Base(String day22Base) {
        this.day22Base = day22Base;
    }

    public String getDay22Diff() {
        return day22Diff;
    }

    public void setDay22Diff(String day22Diff) {
        this.day22Diff = day22Diff;
    }

    public String getDay23Auto() {
        return day23Auto;
    }

    public void setDay23Auto(String day23Auto) {
        this.day23Auto = day23Auto;
    }

    public String getDay23Base() {
        return day23Base;
    }

    public void setDay23Base(String day23Base) {
        this.day23Base = day23Base;
    }

    public String getDay23Diff() {
        return day23Diff;
    }

    public void setDay23Diff(String day23Diff) {
        this.day23Diff = day23Diff;
    }

    public String getDay24Auto() {
        return day24Auto;
    }

    public void setDay24Auto(String day24Auto) {
        this.day24Auto = day24Auto;
    }

    public String getDay24Base() {
        return day24Base;
    }

    public void setDay24Base(String day24Base) {
        this.day24Base = day24Base;
    }

    public String getDay24Diff() {
        return day24Diff;
    }

    public void setDay24Diff(String day24Diff) {
        this.day24Diff = day24Diff;
    }

    public String getDay25Auto() {
        return day25Auto;
    }

    public void setDay25Auto(String day25Auto) {
        this.day25Auto = day25Auto;
    }

    public String getDay25Base() {
        return day25Base;
    }

    public void setDay25Base(String day25Base) {
        this.day25Base = day25Base;
    }

    public String getDay25Diff() {
        return day25Diff;
    }

    public void setDay25Diff(String day25Diff) {
        this.day25Diff = day25Diff;
    }

    public String getDay26Auto() {
        return day26Auto;
    }

    public void setDay26Auto(String day26Auto) {
        this.day26Auto = day26Auto;
    }

    public String getDay26Base() {
        return day26Base;
    }

    public void setDay26Base(String day26Base) {
        this.day26Base = day26Base;
    }

    public String getDay26Diff() {
        return day26Diff;
    }

    public void setDay26Diff(String day26Diff) {
        this.day26Diff = day26Diff;
    }

    public String getDay27Auto() {
        return day27Auto;
    }

    public void setDay27Auto(String day27Auto) {
        this.day27Auto = day27Auto;
    }

    public String getDay27Base() {
        return day27Base;
    }

    public void setDay27Base(String day27Base) {
        this.day27Base = day27Base;
    }

    public String getDay27Diff() {
        return day27Diff;
    }

    public void setDay27Diff(String day27Diff) {
        this.day27Diff = day27Diff;
    }

    public String getDay28Auto() {
        return day28Auto;
    }

    public void setDay28Auto(String day28Auto) {
        this.day28Auto = day28Auto;
    }

    public String getDay28Base() {
        return day28Base;
    }

    public void setDay28Base(String day28Base) {
        this.day28Base = day28Base;
    }

    public String getDay28Diff() {
        return day28Diff;
    }

    public void setDay28Diff(String day28Diff) {
        this.day28Diff = day28Diff;
    }

    public String getDay29Auto() {
        return day29Auto;
    }

    public void setDay29Auto(String day29Auto) {
        this.day29Auto = day29Auto;
    }

    public String getDay29Base() {
        return day29Base;
    }

    public void setDay29Base(String day29Base) {
        this.day29Base = day29Base;
    }

    public String getDay29Diff() {
        return day29Diff;
    }

    public void setDay29Diff(String day29Diff) {
        this.day29Diff = day29Diff;
    }

    public String getDay30Auto() {
        return day30Auto;
    }

    public void setDay30Auto(String day30Auto) {
        this.day30Auto = day30Auto;
    }

    public String getDay30Base() {
        return day30Base;
    }

    public void setDay30Base(String day30Base) {
        this.day30Base = day30Base;
    }

    public String getDay30Diff() {
        return day30Diff;
    }

    public void setDay30Diff(String day30Diff) {
        this.day30Diff = day30Diff;
    }

    public String getDay31Auto() {
        return day31Auto;
    }

    public void setDay31Auto(String day31Auto) {
        this.day31Auto = day31Auto;
    }

    public String getDay31Base() {
        return day31Base;
    }

    public void setDay31Base(String day31Base) {
        this.day31Base = day31Base;
    }

    public String getDay31Diff() {
        return day31Diff;
    }

    public void setDay31Diff(String day31Diff) {
        this.day31Diff = day31Diff;
    }

}
