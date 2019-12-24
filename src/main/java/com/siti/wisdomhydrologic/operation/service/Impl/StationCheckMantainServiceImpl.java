package com.siti.wisdomhydrologic.operation.service.Impl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.siti.wisdomhydrologic.configmaintain.mapper.ConfigRiverStationMapper;
import com.siti.wisdomhydrologic.operation.entity.ReportStationCheckMantain;
import com.siti.wisdomhydrologic.operation.mapper.StationCheckMantainMapper;
import com.siti.wisdomhydrologic.operation.service.StationCheckMantainService;
import com.siti.wisdomhydrologic.user.entity.User;
import com.siti.wisdomhydrologic.user.mapper.UserMapper;
import com.siti.wisdomhydrologic.user.service.UserInfoService;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zyw on 2019/7/31.
 */
@Service
public class StationCheckMantainServiceImpl implements StationCheckMantainService {

    @Resource
    private ConfigRiverStationMapper configRiverStationMapper;
    @Resource
    private StationCheckMantainMapper stationCheckMantainMapper;
    @Resource
    private UserInfoService userInfoService;
    @Resource
    private UserMapper userMapper;

    /**
     * 模版单sheet导出示例
     *
     * @return
     */
    public Workbook exportSheetByTemplate(Integer reportId) {
        // 查询数据,此处省略
        ReportStationCheckMantain entity = stationCheckMantainMapper.getByReportId(reportId);

        // 获取导出excel指定模版
        TemplateExportParams params = new TemplateExportParams( "sqexcelmodel/model5.xls");
        params.setSheetName("表五");

        if(entity.getSolarEnergyVoltageCheck() ==1){
            entity.setSolarEnergyVoltageCheckRightName("☑ 正常"+entity.getSolarEnergyVoltageValue()+"V");
            entity.setSolarEnergyVoltageCheckWrongName("□ 不正常");
        }else{
            entity.setSolarEnergyVoltageCheckRightName("□正常");
            entity.setSolarEnergyVoltageCheckWrongName("☑ 不正常"+entity.getSolarEnergyVoltageValue()+"V");
        }
        if(entity.getStorageBatteryVoltageCheck() ==1){
            entity.setStorageBatteryVoltageCheckRightName("☑ 正常"+entity.getStorageBatteryValue()+"V");
            entity.setStorageBatteryVoltageCheckWrongName("□ 不正常");
        }else{
            entity.setStorageBatteryVoltageCheckRightName("□ 正常");
            entity.setStorageBatteryVoltageCheckWrongName("☑ 不正常"+entity.getStorageBatteryValue()+"V");
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", entity);
        Workbook workbook = ExcelExportUtil.exportExcel(params, map);
        // 导出excel
        return workbook;
    }


    /**
     * 模版多sheet导出,暂未完成
     *
     * @return
     */
    public Workbook exportAllRecord(@Param("mantainDate") String mantainDate, @Param("stationId") String stationId) {
//        User user = (User) userInfoService.get();
//        List<Integer> orgList = userMapper.getOrgIdList(user.getId());
//
//        List<ReportStationCheckMantain> list = new ArrayList<>();
//        if(orgList != null){
//            list = stationCheckMantainMapper.getListByDateAndStationId(mantainDate,stationId,orgList);
//        }
//
//        TemplateExportParams params = new TemplateExportParams( "sqexcelmodel/model5.xls",
//                true);
//
//        List<String> sheetNameList = new ArrayList<>();
        List<Map<String, Object>> sheetsList = new ArrayList<>() ;
//        for (ReportStationCheckMantain entity: list) {
//            // 设置sheet名称
//            sheetNameList.add(entity.getMantainDate().substring(0,10));
//
//            ExportParams exportParams = new ExportParams() ;
//            exportParams.setSheetName(entity.getMantainDate().substring(0,10));
//
//            Map<String, Object> map = new HashMap<String, Object>();
//            map.put("title", exportParams);
//            map.put("entity", params.getStyle());
//            map.put("data", entity);
//            sheetsList.add(map);
//        }

        Workbook workbook = ExcelExportUtil.exportExcel(sheetsList, ExcelType.XSSF);
        // 导出excel
        return workbook;
    }

    public PageInfo<ReportStationCheckMantain> getList(String maintainDate, String stationId, int page, int pageSize){
        User user = (User) userInfoService.get();
        List<Integer> orgList = userMapper.getOrgIdList(user.getId());

        List<ReportStationCheckMantain> list = new ArrayList<>();
        if(orgList != null){
            PageHelper.startPage(page, pageSize);
            list = stationCheckMantainMapper.getListByDateAndStationId(maintainDate,stationId,orgList);
        }
        return new PageInfo<>(list);
    }

}
