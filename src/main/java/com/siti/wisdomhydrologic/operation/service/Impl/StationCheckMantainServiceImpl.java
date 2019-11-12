package com.siti.wisdomhydrologic.operation.service.Impl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.siti.wisdomhydrologic.configmaintain.entity.ConfigRiverStation;
import com.siti.wisdomhydrologic.configmaintain.mapper.ConfigRiverStationMapper;
import com.siti.wisdomhydrologic.operation.entity.ReportStationCheckMantain;
import com.siti.wisdomhydrologic.operation.mapper.StationCheckMantainMapper;
import com.siti.wisdomhydrologic.operation.service.StationCheckMantainService;
import com.siti.wisdomhydrologic.operation.vo.RainVo;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.net.URL;
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


    public int insert(ReportStationCheckMantain reportStationCheckMantain) {
        ConfigRiverStation allByStationName = configRiverStationMapper.getAllByCode(reportStationCheckMantain.getStationCode());

        if (allByStationName != null) {
            reportStationCheckMantain.setStationName(allByStationName.getStationName());
        }
        try {
            return stationCheckMantainMapper.insert(reportStationCheckMantain);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int delete(Integer reportId) {
        return stationCheckMantainMapper.deleteById(reportId);
    }

    public int update(ReportStationCheckMantain reportStationCheckMantain) {
        ConfigRiverStation allByStationName = configRiverStationMapper.getAllByCode(reportStationCheckMantain.getStationCode());

        if (allByStationName != null) {
            reportStationCheckMantain.setStationName(allByStationName.getStationName());
        }
        try {
            return stationCheckMantainMapper.update(reportStationCheckMantain);
        } catch (Exception e) {
            return 0;
        }
    }


    /**
     * 模版单sheet导出示例
     *
     * @return
     */
    public Workbook exportSheetByTemplate(@Param("mantainDate") String mantainDate, @Param("stationId") Integer stationId) {
        // 查询数据,此处省略
        RainVo entity = stationCheckMantainMapper.getByStationIdVo(mantainDate, stationId);
        /*for (int i = 0; i < list.size(); i++) {
            ReportInspectionMaintenance data = list.get(i);
            data.setReportId(i+1);
         }*/
        int count1 = 0;
        // 设置导出配置
        // 获取导出excel指定模版
        URL url = this.getClass().getClassLoader().getResource("");
        String logFilePath = url.getPath();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String rootPath = request.getSession().getServletContext().getRealPath("/").replace("\\", "/");
        TemplateExportParams params = new TemplateExportParams(logFilePath + "sqexcelmodel/model5.xls");
        File f = new File(this.getClass().getResource("/").getPath());
        // 标题开始行
        // params.setHeadingStartRow(0);
        // 标题行数
        // params.setHeadingRows(2);
        // 设置sheetName,若不设置该参数,则使用得原本得sheet名称
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
        map.put("date", mantainDate);
        Workbook workbook = ExcelExportUtil.exportExcel(params, map);
        // 导出excel
        return workbook;
    }

}
