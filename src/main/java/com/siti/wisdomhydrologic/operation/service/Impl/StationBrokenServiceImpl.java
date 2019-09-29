package com.siti.wisdomhydrologic.operation.service.Impl;

import com.siti.wisdomhydrologic.operation.entity.ReportStationBroken;
import com.siti.wisdomhydrologic.operation.mapper.ReportStationBrokenMapper;
import com.siti.wisdomhydrologic.operation.service.StationBrokenService;
import com.siti.wisdomhydrologic.user.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dell on 2019/7/31.
 */
@Service
public class StationBrokenServiceImpl implements StationBrokenService {

    @Resource
    private ReportStationBrokenMapper reportStationBrokenMapper;
    @Resource
    private UserMapper userMapper;
    @Override
    public List<ReportStationBroken> getAll(String createDate,String applicationEquipName) {
        return reportStationBrokenMapper.getAll(createDate, applicationEquipName);
    }

    @Override
    public int delete(Integer reportId) {
        return reportStationBrokenMapper.deleteById(reportId);
    }

    @Override
    public int update(ReportStationBroken reportStationBroken) {
        reportStationBroken.setCreateTime(reportStationBroken.getBrokenHappenTime()+":00:00");
        try {
            return reportStationBrokenMapper.updateData(reportStationBroken);

        }catch (Exception e){
            return 0;
        }
    }

    @Override
    public int insert(ReportStationBroken reportStationBroken) {
        reportStationBroken.setCreateTime(reportStationBroken.getBrokenHappenTime()+":00:00");
        try{
            return reportStationBrokenMapper.insertData(reportStationBroken);
        }catch (Exception e){
            return 0;
        }
    }
}
