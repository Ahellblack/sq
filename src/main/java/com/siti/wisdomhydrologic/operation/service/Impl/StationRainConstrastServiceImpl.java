package com.siti.wisdomhydrologic.operation.service.Impl;

import com.siti.wisdomhydrologic.operation.entity.ReportStationRainConstrast;
import com.siti.wisdomhydrologic.operation.mapper.StationRainConstrastMapper;
import com.siti.wisdomhydrologic.operation.service.StationRainConstrastService;
import com.siti.wisdomhydrologic.operation.vo.ReportStationRainConstrastVo;
import com.siti.wisdomhydrologic.util.DateOrTimeTrans;
import com.siti.wisdomhydrologic.util.DateTransform;
import com.siti.wisdomhydrologic.util.DatesUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by dell on 2019/8/1.
 */
@Service
public class StationRainConstrastServiceImpl implements StationRainConstrastService {
    @Resource
    private StationRainConstrastMapper stationRainConstrastMapper;

    @Override
    public List<ReportStationRainConstrastVo> getAutoByMonth(Date date) {
        return stationRainConstrastMapper.getAutoByMonth(date);
    }

    @Override
    public List<ReportStationRainConstrastVo> getBaseByMonth(Date date) {
        return stationRainConstrastMapper.getBaseByMonth(date);
    }

    @Override
    public List<ReportStationRainConstrastVo> getDiffByMonth(Date date) {
        return stationRainConstrastMapper.getDiffByMonth(date);
    }

    @Override
    public List<ReportStationRainConstrast> getAll(Date date) {
        return null;
    }

    @Override
    public List<ReportStationRainConstrastVo> getByMonth(String date) {
        if (date == null) {
            //获取当前日期的上个月时间
            date = DateOrTimeTrans.Date2TimeString3(new Date());
        }
        System.out.println(date);
        List<ReportStationRainConstrastVo> list = stationRainConstrastMapper.getByMonth(date);
        List<Double> AutoList = new ArrayList<>();
        List<Double> baseList = new ArrayList<>();
        List<Double> diffList = new ArrayList<>();
        for (ReportStationRainConstrastVo constrast : list) {
            AutoList.add(constrast.getDay1Auto());
            AutoList.add(constrast.getDay2Auto());
            AutoList.add(constrast.getDay3Auto());
            AutoList.add(constrast.getDay4Auto());
            AutoList.add(constrast.getDay5Auto());
            AutoList.add(constrast.getDay6Auto());
            AutoList.add(constrast.getDay7Auto());
            AutoList.add(constrast.getDay8Auto());
            AutoList.add(constrast.getDay9Auto());
            AutoList.add(constrast.getDay10Auto());
            AutoList.add(constrast.getDay11Auto());
            AutoList.add(constrast.getDay12Auto());
            AutoList.add(constrast.getDay13Auto());
            AutoList.add(constrast.getDay14Auto());
            AutoList.add(constrast.getDay15Auto());
            AutoList.add(constrast.getDay16Auto());
            AutoList.add(constrast.getDay17Auto());
            AutoList.add(constrast.getDay18Auto());
            AutoList.add(constrast.getDay19Auto());
            AutoList.add(constrast.getDay20Auto());
            AutoList.add(constrast.getDay21Auto());
            AutoList.add(constrast.getDay22Auto());
            AutoList.add(constrast.getDay23Auto());
            AutoList.add(constrast.getDay24Auto());
            AutoList.add(constrast.getDay25Auto());
            AutoList.add(constrast.getDay26Auto());
            AutoList.add(constrast.getDay27Auto());
            AutoList.add(constrast.getDay28Auto());
            AutoList.add(constrast.getDay29Auto());
            AutoList.add(constrast.getDay30Auto());
            AutoList.add(constrast.getDay31Auto());
            constrast.setAutoList(AutoList);
            baseList.add(constrast.getDay1Base());
            baseList.add(constrast.getDay2Base());
            baseList.add(constrast.getDay3Base());
            baseList.add(constrast.getDay4Base());
            baseList.add(constrast.getDay5Base());
            baseList.add(constrast.getDay6Base());
            baseList.add(constrast.getDay7Base());
            baseList.add(constrast.getDay8Base());
            baseList.add(constrast.getDay9Base());
            baseList.add(constrast.getDay10Base());
            baseList.add(constrast.getDay11Base());
            baseList.add(constrast.getDay12Base());
            baseList.add(constrast.getDay13Base());
            baseList.add(constrast.getDay14Base());
            baseList.add(constrast.getDay15Base());
            baseList.add(constrast.getDay16Base());
            baseList.add(constrast.getDay17Base());
            baseList.add(constrast.getDay18Base());
            baseList.add(constrast.getDay19Base());
            baseList.add(constrast.getDay20Base());
            baseList.add(constrast.getDay21Base());
            baseList.add(constrast.getDay22Base());
            baseList.add(constrast.getDay23Base());
            baseList.add(constrast.getDay24Base());
            baseList.add(constrast.getDay25Base());
            baseList.add(constrast.getDay26Base());
            baseList.add(constrast.getDay27Base());
            baseList.add(constrast.getDay28Base());
            baseList.add(constrast.getDay29Base());
            baseList.add(constrast.getDay30Base());
            baseList.add(constrast.getDay31Base());
            constrast.setBaseList(baseList);
            diffList.add(constrast.getDay1Diff());
            diffList.add(constrast.getDay2Diff());
            diffList.add(constrast.getDay3Diff());
            diffList.add(constrast.getDay4Diff());
            diffList.add(constrast.getDay5Diff());
            diffList.add(constrast.getDay6Diff());
            diffList.add(constrast.getDay7Diff());
            diffList.add(constrast.getDay8Diff());
            diffList.add(constrast.getDay9Diff());
            diffList.add(constrast.getDay10Diff());
            diffList.add(constrast.getDay11Diff());
            diffList.add(constrast.getDay12Diff());
            diffList.add(constrast.getDay13Diff());
            diffList.add(constrast.getDay14Diff());
            diffList.add(constrast.getDay15Diff());
            diffList.add(constrast.getDay16Diff());
            diffList.add(constrast.getDay17Diff());
            diffList.add(constrast.getDay18Diff());
            diffList.add(constrast.getDay19Diff());
            diffList.add(constrast.getDay20Diff());
            diffList.add(constrast.getDay21Diff());
            diffList.add(constrast.getDay22Diff());
            diffList.add(constrast.getDay23Diff());
            diffList.add(constrast.getDay24Diff());
            diffList.add(constrast.getDay25Diff());
            diffList.add(constrast.getDay26Diff());
            diffList.add(constrast.getDay27Diff());
            diffList.add(constrast.getDay28Diff());
            diffList.add(constrast.getDay29Diff());
            diffList.add(constrast.getDay30Diff());
            diffList.add(constrast.getDay31Diff());
            constrast.setDiffList(diffList);
        }

        return list;
    }

    @Override
    public int update(ReportStationRainConstrastVo vo) {
        ReportStationRainConstrast reportStationRainConstrast = new ReportStationRainConstrast();
        /**
         *为DAY1至DAY31进行数据修改
         * */
        reportStationRainConstrast.setDay1(vo.getDay1Auto() + "," + vo.getDay1Base() + "," + vo.getDay1Diff());
        reportStationRainConstrast.setDay2(vo.getDay2Auto() + "," + vo.getDay2Base() + "," + vo.getDay2Diff());
        reportStationRainConstrast.setDay3(vo.getDay3Auto() + "," + vo.getDay3Base() + "," + vo.getDay3Diff());
        reportStationRainConstrast.setDay4(vo.getDay4Auto() + "," + vo.getDay4Base() + "," + vo.getDay4Diff());
        reportStationRainConstrast.setDay5(vo.getDay5Auto() + "," + vo.getDay5Base() + "," + vo.getDay5Diff());
        reportStationRainConstrast.setDay6(vo.getDay6Auto() + "," + vo.getDay6Base() + "," + vo.getDay6Diff());
        reportStationRainConstrast.setDay7(vo.getDay7Auto() + "," + vo.getDay7Base() + "," + vo.getDay7Diff());
        reportStationRainConstrast.setDay8(vo.getDay8Auto() + "," + vo.getDay8Base() + "," + vo.getDay8Diff());
        reportStationRainConstrast.setDay9(vo.getDay9Auto() + "," + vo.getDay9Base() + "," + vo.getDay9Diff());
        reportStationRainConstrast.setDay10(vo.getDay10Auto() + "," + vo.getDay10Base() + "," + vo.getDay10Diff());
        reportStationRainConstrast.setDay11(vo.getDay11Auto() + "," + vo.getDay11Base() + "," + vo.getDay11Diff());
        reportStationRainConstrast.setDay12(vo.getDay12Auto() + "," + vo.getDay12Base() + "," + vo.getDay12Diff());
        reportStationRainConstrast.setDay13(vo.getDay13Auto() + "," + vo.getDay13Base() + "," + vo.getDay13Diff());
        reportStationRainConstrast.setDay14(vo.getDay14Auto() + "," + vo.getDay14Base() + "," + vo.getDay14Diff());
        reportStationRainConstrast.setDay15(vo.getDay15Auto() + "," + vo.getDay15Base() + "," + vo.getDay15Diff());
        reportStationRainConstrast.setDay16(vo.getDay16Auto() + "," + vo.getDay16Base() + "," + vo.getDay16Diff());
        reportStationRainConstrast.setDay17(vo.getDay17Auto() + "," + vo.getDay17Base() + "," + vo.getDay17Diff());
        reportStationRainConstrast.setDay18(vo.getDay18Auto() + "," + vo.getDay18Base() + "," + vo.getDay18Diff());
        reportStationRainConstrast.setDay19(vo.getDay19Auto() + "," + vo.getDay19Base() + "," + vo.getDay19Diff());
        reportStationRainConstrast.setDay20(vo.getDay20Auto() + "," + vo.getDay20Base() + "," + vo.getDay20Diff());
        reportStationRainConstrast.setDay21(vo.getDay21Auto() + "," + vo.getDay21Base() + "," + vo.getDay21Diff());
        reportStationRainConstrast.setDay22(vo.getDay22Auto() + "," + vo.getDay22Base() + "," + vo.getDay22Diff());
        reportStationRainConstrast.setDay23(vo.getDay23Auto() + "," + vo.getDay23Base() + "," + vo.getDay23Diff());
        reportStationRainConstrast.setDay24(vo.getDay24Auto() + "," + vo.getDay24Base() + "," + vo.getDay24Diff());
        reportStationRainConstrast.setDay25(vo.getDay25Auto() + "," + vo.getDay25Base() + "," + vo.getDay25Diff());
        reportStationRainConstrast.setDay26(vo.getDay26Auto() + "," + vo.getDay26Base() + "," + vo.getDay26Diff());
        reportStationRainConstrast.setDay27(vo.getDay27Auto() + "," + vo.getDay27Base() + "," + vo.getDay27Diff());
        reportStationRainConstrast.setDay28(vo.getDay28Auto() + "," + vo.getDay28Base() + "," + vo.getDay28Diff());
        reportStationRainConstrast.setDay29(vo.getDay29Auto() + "," + vo.getDay29Base() + "," + vo.getDay29Diff());
        reportStationRainConstrast.setDay30(vo.getDay30Auto() + "," + vo.getDay30Base() + "," + vo.getDay30Diff());
        reportStationRainConstrast.setDay31(vo.getDay31Auto() + "," + vo.getDay31Base() + "," + vo.getDay31Diff());

        /**
         * 为修改值赋值
         * */
        reportStationRainConstrast.setCreateBy(vo.getCreateBy());
        reportStationRainConstrast.setCreateTime(vo.getCreateTime());
        reportStationRainConstrast.setDataYearMonth(vo.getDataYearMonth());
        reportStationRainConstrast.setManageOrgId(vo.getManageOrgId());
        reportStationRainConstrast.setManageOrgName(vo.getManageOrgName());
        reportStationRainConstrast.setStationCode(vo.getStationCode());
        reportStationRainConstrast.setStationName(vo.getStationName());

        /**
         * 设置total的值
         * */
        reportStationRainConstrast.setTotal((vo.getDay1Auto() + vo.getDay2Auto() + vo.getDay3Auto() + vo.getDay4Auto() + vo.getDay5Auto() + vo.getDay6Auto() + vo.getDay7Auto() + vo.getDay8Auto() + vo.getDay9Auto() + vo.getDay10Auto() + vo.getDay11Auto() + vo.getDay12Auto() + vo.getDay13Auto() + vo.getDay14Auto() + vo.getDay15Auto() + vo.getDay16Auto() + vo.getDay17Auto() + vo.getDay18Auto() + vo.getDay19Auto() + vo.getDay20Auto() + vo.getDay21Auto() + vo.getDay22Auto() + vo.getDay23Auto() + vo.getDay24Auto() + vo.getDay25Auto() + vo.getDay26Auto() + vo.getDay27Auto() + vo.getDay28Auto() + vo.getDay29Auto() + vo.getDay30Auto() + vo.getDay31Auto()) + "," + (vo.getDay1Base() + vo.getDay2Base() + vo.getDay3Base() + vo.getDay4Base() + vo.getDay5Base() + vo.getDay6Base() + vo.getDay7Base() + vo.getDay8Base() + vo.getDay9Base() + vo.getDay10Base() + vo.getDay11Base() + vo.getDay12Base() + vo.getDay13Base() + vo.getDay14Base() + vo.getDay15Base() + vo.getDay16Base() + vo.getDay17Base() + vo.getDay18Base() + vo.getDay19Base() + vo.getDay20Base() + vo.getDay21Base() + vo.getDay22Base() + vo.getDay23Base() + vo.getDay24Base() + vo.getDay25Base() + vo.getDay26Base() + vo.getDay27Base() + vo.getDay28Base() + vo.getDay29Base() + vo.getDay30Base() + vo.getDay31Base()) + "," + (vo.getDay1Diff() + vo.getDay2Diff() + vo.getDay3Diff() + vo.getDay4Diff() + vo.getDay5Diff() + vo.getDay6Diff() + vo.getDay7Diff() + vo.getDay8Diff() + vo.getDay9Diff() + vo.getDay10Diff() + vo.getDay11Diff() + vo.getDay12Diff() + vo.getDay13Diff() + vo.getDay14Diff() + vo.getDay15Diff() + vo.getDay16Diff() + vo.getDay17Diff() + vo.getDay18Diff() + vo.getDay19Diff() + vo.getDay20Diff() + vo.getDay21Diff() + vo.getDay22Diff() + vo.getDay23Diff() + vo.getDay24Diff() + vo.getDay25Diff() + vo.getDay26Diff() + vo.getDay27Diff() + vo.getDay28Diff() + vo.getDay29Diff() + vo.getDay30Diff() + vo.getDay31Diff()));


        return stationRainConstrastMapper.updateByPrimaryKey(reportStationRainConstrast);
    }
}
