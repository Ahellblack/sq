package com.siti.wisdomhydrologic.operation.service.Impl;


import com.siti.wisdomhydrologic.operation.entity.ReportHyetometerTest;
import com.siti.wisdomhydrologic.operation.mapper.ReportHyetometerMapper;
import com.siti.wisdomhydrologic.operation.service.ReportHyetometerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dell on 2019/7/26.
 */
@Service
public class ReportHyetometerServiceImpl implements ReportHyetometerService {

    @Resource
    private ReportHyetometerMapper reportHyetometerMapper;

    public List<ReportHyetometerTest> getAll() {
        return reportHyetometerMapper.getAll();
    }

    public int delByReportId(Integer reportId) {
        return reportHyetometerMapper.delByReportId(reportId);
    }

    public int insert(ReportHyetometerTest reportHyetometer) {
        return reportHyetometerMapper.insert(reportHyetometer);
    }

    public int update(ReportHyetometerTest reportHyetometer) {
        return reportHyetometerMapper.update(reportHyetometer);
    }

    public int delByReportIdList(List<Integer> reportIdList) {
        int sum = 0;
        for (Integer id:reportIdList){
            int i = delByReportId(id);
            sum=sum+i;
        }
        return sum;
    }
}
