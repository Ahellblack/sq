package com.siti.wisdomhydrologic.operation.service.Impl;

import com.siti.wisdomhydrologic.configmaintain.entity.ConfigAbnormalError;
import com.siti.wisdomhydrologic.configmaintain.entity.ConfigRiverStation;
import com.siti.wisdomhydrologic.configmaintain.mapper.ConfigAbnormalDictionaryMapper;
import com.siti.wisdomhydrologic.configmaintain.mapper.ConfigAbnormalErrorMapper;
import com.siti.wisdomhydrologic.configmaintain.mapper.ConfigRiverStationMapper;
import com.siti.wisdomhydrologic.operation.entity.ReportManageMantain;
import com.siti.wisdomhydrologic.operation.entity.ReportStationBroken;
import com.siti.wisdomhydrologic.operation.mapper.ManageApplicationBrokenMapper;
import com.siti.wisdomhydrologic.operation.mapper.ManageMantainMapper;
import com.siti.wisdomhydrologic.operation.mapper.ReportStationBrokenMapper;
import com.siti.wisdomhydrologic.operation.service.ManageMantainService;
import com.siti.wisdomhydrologic.operation.vo.ManageMantainVo;
import com.siti.wisdomhydrologic.util.DateTransform;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by zyw on 2019/7/31.
 */
@Service
public class ManageMantainServiceImpl implements ManageMantainService {
    @Resource
    private ManageMantainMapper reportManageMantainMapper;
    @Resource
    private ManageApplicationBrokenMapper reportManageApplicationBrokenMapper;
    @Resource
    private ConfigAbnormalDictionaryMapper configAbnormalDictionaryMapper;
    @Resource
    private ReportStationBrokenMapper reportStationBrokenMapper;
    @Resource
    private ConfigRiverStationMapper configRiverStationMapper;

    @Resource
    private ConfigAbnormalErrorMapper configAbnormalErrorMapper;


    //现默认浦东新区
    private final int SYSORG = 1002;

    /**
     * 根据sys-org和日期获取数据表
     * 若表中暂无当日月份数据时，自动添加并生成
     */
    public List<ReportManageMantain> getAll(String date) {
        //查询数据前进行数据获取
        //insertOrUpdate(date);
        return reportManageMantainMapper.getDataByMonth(date, SYSORG);
    }

    /**
     * 每个月获取等于上个月的天数条数据
     *
     * @param yearMonth 需要添加或判断的月份 yyyy-MM
     */
    public void insertOrUpdate(String yearMonth) {
        //reportManageMantainMapper.selectMonthData();
        Calendar cal = Calendar.getInstance();
        Date date = DateTransform.String2Date(yearMonth, "yyyy-MM");
        Calendar calHour = Calendar.getInstance();
        cal.setTime(date);
        //cal.add(Calendar.MONTH, -1);
        List<ConfigRiverStation> all = configRiverStationMapper.getBySysOrg();
        String LastMonthDate = DateTransform.Date2String(cal.getTime(), "yyyy-MM");

        //依据区域号修改
        //建立异常名字典表的map
        Map<String, String> map = new HashMap<>();
        List<ConfigAbnormalError> list = configAbnormalErrorMapper.getErrorNameList();
        list.forEach((ConfigAbnormalError data) -> map.put(data.getErrorName(), data.getTable1Relate()));
        if (reportManageMantainMapper.getDataByMonth(LastMonthDate, SYSORG).size() == 0) {

            //添加上月天数条数据，默认所有check字段都为1，遍历测站的中心站id赋值
            for (int i = 0; i < cal.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
                //给entity赋值个月的年月日份 xxxx-xx-xx格式
                cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH) + i);
                ReportManageMantain entity1 = new ReportManageMantain(1, "", DateTransform.Date2String(cal.getTime(), "yyyy-MM-dd"), "09:00", 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, "", "", DateTransform.Date2String(cal.getTime(), "yyyy-MM-dd HH:mm:ss"));
                entity1.setManageOrgId(SYSORG);
                reportManageMantainMapper.insert(entity1);
                ReportManageMantain entity2 = new ReportManageMantain(1, "", DateTransform.Date2String(cal.getTime(), "yyyy-MM-dd"), "16:00", 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, "", "", DateTransform.Date2String(cal.getTime(), "yyyy-MM-dd HH:mm:ss"));
                entity2.setManageOrgId(SYSORG);
                reportManageMantainMapper.insert(entity2);
            }
        }
        List<ReportManageMantain> updateList = reportManageMantainMapper.getDataByMonth(LastMonthDate, SYSORG);

        updateList.forEach(mantain -> {
            reportManageApplicationBrokenMapper.getLastMonthList(LastMonthDate, SYSORG).forEach(table4 -> {

                String yearMonthDay = table4.getCreateTime().substring(0, 10);
                String hourMinuteSecond = table4.getCreateTime().substring(11, 13);
                int hour = Integer.parseInt(hourMinuteSecond);
                //当表四的时间与表一数据的 年月日相匹配时,判断是否属于当天还是昨天
                if (yearMonthDay.equals(mantain.getMantainMonth())) {
                    if (hour >= 9 && hour <= 16) {
                        if ("16:00".equals(mantain.getMantainHour())) {
                            //hour属于9-16的时候设置同天的16:00数据
                            setTable4Status(map, table4, mantain);
                        }
                    } else if (hour < 9 && hour >= 0) {
                        if ("09:00".equals(mantain.getMantainHour())) {
                            //hour小于9的时候设置同天的9:00数据
                            setTable4Status(map, table4, mantain);
                        }
                    } else {
                        //设置第二天的数据状态
                        calHour.setTime(DateTransform.String2Date(yearMonthDay, "yyyy-MM-dd"));
                        calHour.add(Calendar.DAY_OF_MONTH, 1);
                        yearMonthDay = DateTransform.Date2String(calHour.getTime(), "yyyy-MM-dd");
                        ReportManageMantain oneData = reportManageMantainMapper.getOneData(yearMonthDay, "09:00", SYSORG);
                        setTable4Status(map, table4, oneData);
                    }
                }
            });
            reportStationBrokenMapper.getLastMonthAll(DateTransform.Date2String(cal.getTime(), "yyyy-MM")).forEach(table3 -> {
                String yearMonthDay = table3.getBrokenHappenTime().substring(0, 10);
                String hourMinuteSecond = table3.getCreateTime().substring(11, 13);
                int hour = Integer.parseInt(hourMinuteSecond);

                if (yearMonthDay.equals(mantain.getMantainMonth())) {
                    if (hour >= 9 && hour <= 16) {
                        if ("16:00".equals(mantain.getMantainHour())) {
                            setTable3Status(map, table3, mantain);
                        }
                    } else if (hour < 9 && hour >= 0) {
                        if ("09:00".equals(mantain.getMantainHour())) {
                            setTable3Status(map, table3, mantain);
                        }
                    } else {
                        calHour.setTime(DateTransform.String2Date(yearMonthDay, "yyyy-MM-dd"));
                        calHour.add(Calendar.DAY_OF_MONTH, 1);
                        yearMonthDay = DateTransform.Date2String(calHour.getTime(), "yyyy-MM-dd");
                        ReportManageMantain oneData = reportManageMantainMapper.getOneData(yearMonthDay, "09:00", SYSORG);
                        setTable3Status(map, table3, oneData);
                    }
                }
            });
        });
        updateList.forEach(t -> {
            //t.setWorkStation(-1);
            reportManageMantainMapper.update(t);
        });

    }

    @Override
    public int update(ReportManageMantain reportManageMantain) {
        System.out.println("待修改的表一数据：" + reportManageMantain);
        try {
            return reportManageMantainMapper.update(reportManageMantain);
        } catch (Exception e) {
            return 0;
        }
    }

    public int delete(Integer reportId) {
        return reportManageMantainMapper.deleteById(reportId);
    }


    public static void setTable4Status(Map<String, String> map, ManageMantainVo vo, ReportManageMantain entity) {
        if (map.containsKey(vo.getBrokenName())) {
            switch (map.get(vo.getBrokenName())) {
                case "9":
                    entity.setVoltageException(-1);
                    break;
                case "10":
                    entity.setVoltageProcessLineException(-1);
                    break;
                case "11":
                    entity.setDayRainReportException(-1);
                    break;
                case "12":
                    entity.setRainBarException(-1);
                    break;
                case "13":
                    entity.setDaySeaLevelReportException(-1);
                    break;
                case "14":
                    entity.setSeaLeveProcessLineException(-1);
                    break;
                case "15":
                    entity.setOtherReportException(-1);
                    break;
            }
        }
    }

    public static void setTable3Status(Map<String, String> map, ReportStationBroken vo, ReportManageMantain entity) {
        if (map.containsKey(vo.getApplicationEquipName())) {
            switch (map.get(vo.getApplicationEquipName())) {
                case "1":
                    entity.setTempHuimidityException(-1);
                    break;
                case "2":
                    entity.setServerTimeException(-1);
                    break;
                case "3":
                    entity.setDatabaseServerException(-1);
                    break;
                case "4":
                    entity.setCommunicateServerException(-1);
                    break;
                case "5":
                    entity.setApplicationServerException(-1);
                    break;
                case "6":
                    entity.setWebServerException(-1);
                    break;
                case "7":
                    entity.setWorkStation(-1);
                    break;
            }
        }
    }
}
