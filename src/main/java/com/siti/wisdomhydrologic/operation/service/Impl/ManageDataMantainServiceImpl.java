package com.siti.wisdomhydrologic.operation.service.Impl;

import com.siti.wisdomhydrologic.operation.entity.ReportManageDataMantain;
import com.siti.wisdomhydrologic.operation.mapper.ManageDataMantainMapper;
import com.siti.wisdomhydrologic.operation.service.ManageDataMantainService;
import com.siti.wisdomhydrologic.operation.vo.ReportManageDataMantainVo;
import com.siti.wisdomhydrologic.util.DateOrTimeTrans;
import com.siti.wisdomhydrologic.util.DateTransform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by dell on 2019/7/30.
 */
@Service
public class ManageDataMantainServiceImpl implements ManageDataMantainService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private ManageDataMantainMapper reportManageDataMantainMapper;

    public Map<String, Object> getByCreateDate(String createDate) {

        List<ReportManageDataMantain> list = reportManageDataMantainMapper.getByCreateDate(createDate);
        List<String> createList = new ArrayList<>();
        list.forEach(data -> {
            String str = data.getCreateBy().substring(1);
            str = str.substring(0, str.length() - 1);
            str = str.replace("\"","");
            String[] split = str.split(",");
            for (String s : split) {
                createList.add(s);
            }
        });
        /*String s = null;
        if (createDate != null) {
            s = DateOrTimeTrans.Date2TimeString2(createDate);
        }*/
        Map<String, Object> map = new HashMap<>();
        map.put("data", list);
        map.put("createBy", createList);
        return map;
    }

    public int delete(Integer reportId) {
        return reportManageDataMantainMapper.deleteByReportId(reportId);
    }

    public int update(ReportManageDataMantain reportManageDataMantain) {
        //数据发生修改时,altertime数据更新为当前时间
        Date date = new Date();
        reportManageDataMantain.setAlterDate(DateOrTimeTrans.Date2TimeString2(date));
        logger.info("修改后的ReportManageDataMantain：{}", reportManageDataMantain);
        return reportManageDataMantainMapper.update(reportManageDataMantain);
    }

    public int insert(ReportManageDataMantain reportManageDataMantain) {
        DateTransform.String2Date(reportManageDataMantain.getAlterDate(), "YYYY-MM-dd HH:mm:ss");
        logger.info("要添加的ReportManageDataMantain：{}", reportManageDataMantain);
        return reportManageDataMantainMapper.insert(reportManageDataMantain);
    }
}
