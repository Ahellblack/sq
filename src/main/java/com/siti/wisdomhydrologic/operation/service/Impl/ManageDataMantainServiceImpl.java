package com.siti.wisdomhydrologic.operation.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.siti.wisdomhydrologic.datepull.mapper.DayDataMapper;
import com.siti.wisdomhydrologic.maintainconfig.entity.ConfigAbnormalDictionary;
import com.siti.wisdomhydrologic.maintainconfig.entity.ConfigSensorSectionModule;
import com.siti.wisdomhydrologic.maintainconfig.mapper.ConfigAbnormalDictionaryMapper;
import com.siti.wisdomhydrologic.maintainconfig.mapper.ConfigSensorSectionModuleMapper;
import com.siti.wisdomhydrologic.operation.entity.ReportManageApplicationBroken;
import com.siti.wisdomhydrologic.operation.entity.ReportManageDataMantain;
import com.siti.wisdomhydrologic.operation.mapper.ManageDataMantainMapper;
import com.siti.wisdomhydrologic.operation.service.ManageDataMantainService;
import com.siti.wisdomhydrologic.operation.utils.AbnormalParamUtils;
import com.siti.wisdomhydrologic.operation.vo.ReportManageDataMantainVo;
import com.siti.wisdomhydrologic.realmessageprocess.mapper.AbnormalDetailMapper;
import com.siti.wisdomhydrologic.util.DateOrTimeTrans;
import com.siti.wisdomhydrologic.util.DateTransform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.IntStream;

/**
 * Created by dell on 2019/7/30.
 */
@Service
public class ManageDataMantainServiceImpl implements ManageDataMantainService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private ManageDataMantainMapper reportManageDataMantainMapper;
    @Resource
    private AbnormalDetailMapper abnormalDetailMapper;
    @Resource
    private ConfigSensorSectionModuleMapper configSensorSectionModuleMapper;
    @Resource
    private ConfigAbnormalDictionaryMapper configAbnormalDictionaryMapper;


    public PageInfo<ReportManageDataMantain> getByCreateDate(int page, int pageSize, String createDate) {
        //默认查询本月
        if (createDate == null) {
            createDate = DateOrTimeTrans.Date2TimeString3(new Date());
        }
        System.out.println(createDate);
        PageHelper.startPage(page, pageSize);
        List<ReportManageDataMantain> list = reportManageDataMantainMapper.getByCreateDate(createDate);
        /**
         * 获取的字符串掐头去尾[]符号,并删去"重新返回以","分隔的字符串
         * */
        list.forEach(data -> {
            List<String> createList = new ArrayList<>();
            if (data.getCreateBy() != null) {
                String str = data.getCreateBy().substring(1);
                str = str.substring(0, str.length() - 1);
                str = str.replace("\"", "");
                String[] split = str.split(",");
                for (String s : split) {
                    createList.add(s);
                }
            }
            StringBuffer resultBuffer = new StringBuffer();
            for (int i = 0; i < createList.size(); i++) {
                String result = createList.get(i);
                if (i == 0) {
                    resultBuffer.append(result);
                } else {
                    resultBuffer.append("," + result);
                }
            }
            String createName = resultBuffer.toString();
            data.setCreateBy(createName);
        });
        return new PageInfo<ReportManageDataMantain>(list);
    }

    @Override
    public int delete(Integer reportId) {
        return reportManageDataMantainMapper.deleteByReportId(reportId);
    }

    @Override
    public int update(ReportManageDataMantain reportManageDataMantain) {
        //数据发生修改时,altertime数据更新为当前时间
        Date date = new Date();
        //格式为YYYY-MM-dd
        reportManageDataMantain.setAlterDate(DateOrTimeTrans.Date2TimeString(date));
        logger.info("修改后的ReportManageDataMantain：{}", reportManageDataMantain);
        return reportManageDataMantainMapper.update(reportManageDataMantain);
    }

    @Override
    public int insertAbnormalData(String date) {
        List<ConfigAbnormalDictionary> abnormallist = configAbnormalDictionaryMapper.getList();
        List<ReportManageDataMantainVo> all = abnormalDetailMapper.getALL(date);
        List<ConfigSensorSectionModule> moduleList = configSensorSectionModuleMapper.getStation();
        List<ReportManageDataMantainVo> abnormalall = new ArrayList<>();
        if (all.size() > 0) {
            //获取异常配置参数
            all.forEach(abnormalData -> {
                if (abnormalData.getDataError() != null) {
                    abnormalData.setBrokenAccordingId(abnormalData.getDataError());
                    //根据字典获取异常名
                    abnormallist.forEach(param -> {
                        if (param.getBrokenAccordingId().equals(abnormalData.getBrokenAccordingId())) {
                            abnormalData.setErrorDataReason(param.getErrorName());
                            abnormalData.setBrokenAccordingId(param.getBrokenAccordingId());
                            //赋值测站号和修改日期
                            abnormalData.setStationCode(abnormalData.getSensorCode() / 100);
                            abnormalData.setCreateTime(abnormalData.getDate());
                            //修改日期添加时精确到某日
                            abnormalData.setAlterDate(abnormalData.getCreateTime().substring(0, 10));
                            abnormalData.setErrorTimeSpace(abnormalData.getCreateTime());
                        }
                    });
                    //结合module表添加测站参数
                    moduleList.forEach(module -> {
                        if (module.getSectionCode() == abnormalData.getSectionCode()) {
                            abnormalData.setAlterSensorTypeName(module.getSectionName());
                            abnormalData.setAlterSensorTypeId(module.getSectionCode() % 100);
                            abnormalData.setStationName(module.getStationName());
                        }
                    });
                    abnormalall.add(abnormalData);
                }
            });
            int size = 1000;
            int allsize = abnormalall.size();
            int cycle = allsize % size == 0 ? allsize / size : (allsize / size + 1);
            IntStream.range(0, cycle).forEach(e -> {
                reportManageDataMantainMapper.insertAbnormal(abnormalall.subList(e * size, (e + 1) * size > allsize ? allsize : size * (e + 1)));
            });
            return allsize;
        } else {
            return 0;
        }
    }
    //return reportManageDataMantainMapper.insertAbnormal(all);
    public int insert(ReportManageDataMantain reportManageDataMantain) {
        DateTransform.String2Date(reportManageDataMantain.getAlterDate(), "YYYY-MM-dd");
        logger.info("要添加的ReportManageDataMantain：{}", reportManageDataMantain);
        return reportManageDataMantainMapper.insert(reportManageDataMantain);
    }
}
