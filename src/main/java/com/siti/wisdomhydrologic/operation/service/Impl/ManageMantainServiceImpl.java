package com.siti.wisdomhydrologic.operation.service.Impl;

import com.siti.wisdomhydrologic.maintainconfig.entity.ConfigAbnormalDictionary;
import com.siti.wisdomhydrologic.maintainconfig.entity.ConfigRiverStation;
import com.siti.wisdomhydrologic.maintainconfig.mapper.ConfigAbnormalDictionaryMapper;
import com.siti.wisdomhydrologic.maintainconfig.mapper.ConfigRiverStationMapper;
import com.siti.wisdomhydrologic.operation.entity.ReportManageMantain;
import com.siti.wisdomhydrologic.operation.mapper.ManageApplicationBrokenMapper;
import com.siti.wisdomhydrologic.operation.mapper.ManageMantainMapper;
import com.siti.wisdomhydrologic.operation.mapper.ReportStationBrokenMapper;
import com.siti.wisdomhydrologic.operation.service.ManageMantainService;
import com.siti.wisdomhydrologic.util.DateTransform;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by dell on 2019/7/31.
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

    /**
     * 根据sys-org和日期获取数据表
     * 若表中暂无当日月份数据时，自动添加并生成
     * */
    public List<ReportManageMantain> getAll(String date) {
        //查询数据前进行数据获取
        //insertOrUpdate(date);
        int sysOrg=1002;
        return reportManageMantainMapper.getDataByMonth(date,sysOrg);
    }

    /**
     * 每个月获取等于上个月的天数条数据
     * @param yearMonth 需要添加或判断的月份 yyyy-MM
     */
    public void insertOrUpdate(String yearMonth) {
        //reportManageMantainMapper.selectMonthData();
        Calendar cal = Calendar.getInstance();
        Date date = DateTransform.String2Date(yearMonth,"yyyy-MM");
        cal.setTime(date);
        //cal.add(Calendar.MONTH, -1);
        List<ConfigRiverStation> all = configRiverStationMapper.getBySysOrg();
        List<ConfigAbnormalDictionary> list = configAbnormalDictionaryMapper.getTableList();
        Map<String, Integer> map = new HashMap<>();
        String LastMonthDate = DateTransform.Date2String(cal.getTime(), "yyyy-MM");
        //依据区域号修改
        int sysOrg = 1002;
        list.forEach((ConfigAbnormalDictionary data) -> map.put(data.getBrokenAccordingId(), data.getTable1_relate()));
        all.forEach(data -> {
            if (reportManageMantainMapper.getDataByMonth(LastMonthDate, sysOrg).size() == 0) {
                //添加上月天数条数据，默认所有check字段都为1，遍历测站的中心站id赋值
                for (int i = 0; i < cal.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
                    //给entity赋值个月的年月日份 xxxx-xx-xx格式
                    cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH) + i);
                    ReportManageMantain entity1 = new ReportManageMantain(1, "", DateTransform.Date2String(cal.getTime(), "yyyy-MM-dd"), "09:00", 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, "", "", DateTransform.Date2String(cal.getTime(), "yyyy-MM-dd HH:mm:ss"));
                    entity1.setManageOrgId(data.getSysOrg());
                    reportManageMantainMapper.insert(entity1);
                    ReportManageMantain entity2 = new ReportManageMantain(1, "", DateTransform.Date2String(cal.getTime(), "yyyy-MM-dd"), "16:00", 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, "", "", DateTransform.Date2String(cal.getTime(), "yyyy-MM-dd HH:mm:ss"));
                    entity2.setManageOrgId(data.getSysOrg());
                    reportManageMantainMapper.insert(entity2);
                }
            }
            List<ReportManageMantain> updateList = reportManageMantainMapper.getDataByMonth(LastMonthDate, sysOrg);

            updateList.forEach(mantain -> {
                reportManageApplicationBrokenMapper.getLastMonthList(LastMonthDate, sysOrg).forEach(table4 -> {
                    if (table4.getCreateTime().substring(0, 10).equals(mantain.getMantainMonth())) {
                        if (map.containsKey(table4.getBrokenAccordingId())) {
                            switch (map.get(table4.getBrokenAccordingId())) {
                                case 9:
                                    mantain.setVoltageException(-1);
                                    break;
                                case 10:
                                    mantain.setVoltageProcessLineException(-1);
                                    break;
                                case 11:
                                    mantain.setDayRainReportException(-1);
                                    break;
                                case 12:
                                    mantain.setRainBarException(-1);
                                    break;
                                case 13:
                                    mantain.setDaySeaLevelReportException(-1);
                                    break;
                                case 14:
                                    mantain.setSeaLeveProcessLineException(-1);
                                    break;
                                case 15:
                                    mantain.setOtherReportException(-1);
                                    break;
                            }
                        }
                    }
                });
                reportStationBrokenMapper.getLastMonthAll(DateTransform.Date2String(cal.getTime(), "yyyy-MM")).forEach(table3 -> {
                    if (table3.getBrokenHappenTime().substring(0, 10).equals(mantain.getMantainMonth())) {
                        if (map.containsKey(table3.getApplicationEquipTypeId())) {
                            switch (map.get(table3.getApplicationEquipTypeId())) {
                                case 1:
                                    mantain.setTempHuimidityException(-1);
                                    break;
                                case 2:
                                    mantain.setServerTimeException(-1);
                                    break;
                                case 3:
                                    mantain.setDatabaseServerException(-1);
                                    break;
                                case 4:
                                    mantain.setCommunicateServerException(-1);
                                    break;
                                case 5:
                                    mantain.setApplicationServerException(-1);
                                    break;
                                case 6:
                                    mantain.setWebServerException(-1);
                                    break;
                                case 7:
                                    mantain.setWorkStation(-1);
                                    break;
                            }
                        }
                    }
                });
            });
            updateList.forEach(t -> {
                //t.setWorkStation(-1);
                reportManageMantainMapper.update(t);
            });
        });
    }

    @Override
    public int update(ReportManageMantain reportManageMantain) {
        System.out.println("待修改的表一数据："+reportManageMantain);
        return reportManageMantainMapper.update(reportManageMantain);
    }

    public int delete(Integer reportId) {
        return reportManageMantainMapper.deleteById(reportId);
    }


}
