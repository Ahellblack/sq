package com.siti.wisdomhydrologic.operation.service.Impl;

import com.siti.wisdomhydrologic.operation.entity.ReportManageDataMantain;
import com.siti.wisdomhydrologic.operation.mapper.ManageDataMantainMapper;
import com.siti.wisdomhydrologic.operation.service.ManageDataMantainService;
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

    public List<ReportManageDataMantain> getByCreateDate(String createDate) {
        //默认查询本月
        if(createDate == null){
            createDate = DateOrTimeTrans.Date2TimeString3(new Date());
        }
        System.out.println(createDate);
        List<ReportManageDataMantain> list = reportManageDataMantainMapper.getByCreateDate(createDate);
        /**
         * 获取的字符串掐头去尾[]符号,并删去"重新返回以","分隔的字符串
         * */
        list.forEach(data -> {
            List<String> createList = new ArrayList<>();
            if (data.getCreateBy()!=null) {
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
        return list;
    }

    public int delete(Integer reportId) {
        return reportManageDataMantainMapper.deleteByReportId(reportId);
    }

    public int update(ReportManageDataMantain reportManageDataMantain) {
        //数据发生修改时,altertime数据更新为当前时间
        Date date = new Date();
        //格式为YYYY-MM-dd
        reportManageDataMantain.setAlterDate(DateOrTimeTrans.Date2TimeString(date));
       /* reportManageDataMantain.setCreateBy("["+reportManageDataMantain.getCreateBy()+"]");
*/
        logger.info("修改后的ReportManageDataMantain：{}", reportManageDataMantain);
        return reportManageDataMantainMapper.update(reportManageDataMantain);
    }

    public int insert(ReportManageDataMantain reportManageDataMantain) {
        DateTransform.String2Date(reportManageDataMantain.getAlterDate(), "YYYY-MM-dd");
        logger.info("要添加的ReportManageDataMantain：{}", reportManageDataMantain);
        return reportManageDataMantainMapper.insert(reportManageDataMantain);
    }
}
