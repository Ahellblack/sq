package com.siti.wisdomhydrologic.operation.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.siti.wisdomhydrologic.maintainconfig.entity.ConfigAbnormalDictionary;
import com.siti.wisdomhydrologic.maintainconfig.entity.ConfigSensorSectionModule;
import com.siti.wisdomhydrologic.maintainconfig.mapper.ConfigAbnormalDictionaryMapper;
import com.siti.wisdomhydrologic.maintainconfig.mapper.ConfigSensorSectionModuleMapper;
import com.siti.wisdomhydrologic.operation.entity.ReportManageApplicationBroken;
import com.siti.wisdomhydrologic.operation.mapper.ManageApplicationBrokenMapper;
import com.siti.wisdomhydrologic.operation.service.ManageApplicationBrokenService;
import com.siti.wisdomhydrologic.operation.vo.ReportManageDataMantainVo;
import com.siti.wisdomhydrologic.realmessageprocess.mapper.AbnormalDetailMapper;
import com.siti.wisdomhydrologic.util.DateOrTimeTrans;
import com.siti.wisdomhydrologic.util.DateTransform;
import com.siti.wisdomhydrologic.util.StationIdUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Created by dell on 2019/7/31.
 */
@Service
public class ManageApplicationBrokenServiceImpl implements ManageApplicationBrokenService {
    @Resource
    private ManageApplicationBrokenMapper reportManageApplicationBrokenMapper;
    @Resource
    private ConfigSensorSectionModuleMapper configSensorSectionModuleMapper;
    @Resource
    private ConfigAbnormalDictionaryMapper configAbnormalDictionaryMapper;
    @Resource
    private AbnormalDetailMapper abnormalDetailMapper;

    public PageInfo<ReportManageApplicationBroken> getAll(int page, int pageSize, String createDate) {
        //默认查询本月
        if (createDate == null) {
            createDate = DateOrTimeTrans.Date2TimeString3(new Date());
        }
        System.out.println(createDate);
        PageHelper.startPage(page, pageSize);
        List<ReportManageApplicationBroken> all = reportManageApplicationBrokenMapper.getAll(createDate);
        return new PageInfo<>(all);
    }

    @Override
    public int insert(ReportManageApplicationBroken reportManageApplicationBroken) {
        return reportManageApplicationBrokenMapper.insert(reportManageApplicationBroken);
    }

    @Override
    public int update(ReportManageApplicationBroken reportManageApplicationBroken) {
        return reportManageApplicationBrokenMapper.updateByPrimaryKey(reportManageApplicationBroken);
    }

    @Override
    public int delete(Integer reportId) {
        return reportManageApplicationBrokenMapper.deleteById(reportId);
    }


    /**
     * 根据日期获取异常
     */
    @Override
    public int insertDataMantain(String date) {
        List<ConfigAbnormalDictionary> list = configAbnormalDictionaryMapper.getList();
        //根据日期获取异常信息
        List<ReportManageDataMantainVo> all = abnormalDetailMapper.getALL(date);
        List<ConfigSensorSectionModule> moduleList = configSensorSectionModuleMapper.getStation();
        List<ReportManageApplicationBroken> brokenList = new ArrayList();
        //获取异常配置参数
        if (all.size() > 0) {
            all.forEach(data -> {
                Calendar calendar = Calendar.getInstance();
                ReportManageApplicationBroken applicationBroken = new ReportManageApplicationBroken();
                //异常包含设备异常或数据异常的一种
                if (data.getEquipmentError() != null) {
                    applicationBroken.setBrokenAccordingId(data.getEquipmentError());
                } else if (data.getDataError() != null) {
                    applicationBroken.setBrokenAccordingId(data.getDataError());
                }
                if(applicationBroken.getBrokenAccordingId()!=null) {
                    //结合module表添加测站参数
                    moduleList.forEach(module -> {
                        if (module.getSectionCode() == data.getSectionCode()) {
                            applicationBroken.setStationId(module.getStationCode());
                            applicationBroken.setStationName(module.getStationName());
                        }
                    });
                    List<String> basicStationList = StationIdUtils.getBasicStationList();
                    //如果包含基本站名,判断为基本站
                    if (basicStationList.contains(data.getStationName())) {
                        calendar.setTime(DateTransform.String2Date(data.getCreateTime(), "YYYY-MM-dd HH:mm:ss"));
                        //基本站往后1小时内
                        calendar.add(calendar.HOUR, 1);
                        applicationBroken.setBrokenResponseTime(calendar.getTime());
                    } else {
                        calendar.setTime(DateTransform.String2Date(data.getCreateTime(), "YYYY-MM-dd HH:mm:ss"));
                        //一般站往后3小时内
                        calendar.add(calendar.HOUR, 3);
                        applicationBroken.setBrokenResponseTime(calendar.getTime());
                    }
                    list.forEach(e -> {
                        //根据字典赋值故障判断依据和故障名称
                        if (e.getBrokenAccordingId().equals(applicationBroken.getBrokenAccordingId())) {
                            applicationBroken.setBrokenAccording(e.getBrokenAccording());
                            applicationBroken.setBrokenName(e.getErrorName());
                        }
                    });
                    applicationBroken.setCreateTime(DateTransform.String2Date(data.getCreateTime(), "YYYY-MM-dd HH:mm:ss"));
                    brokenList.add(applicationBroken);
                }
            });

        int size = 1000;
        int allsize = brokenList.size();
        int cycle = allsize % size == 0 ? allsize / size : (allsize / size + 1);
        IntStream.range(0, cycle).forEach(e -> {
            if (allsize > 0) {
                reportManageApplicationBrokenMapper.insertDataMantain(brokenList.subList(e * size, (e + 1) * size > allsize ? allsize : size * (e + 1)));
            }
        });
        return allsize;
        }else{
            return 0;
        }
    }
}

