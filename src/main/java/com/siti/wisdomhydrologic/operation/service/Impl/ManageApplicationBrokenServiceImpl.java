package com.siti.wisdomhydrologic.operation.service.Impl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.siti.wisdomhydrologic.operation.entity.ReportManageApplicationBroken;
import com.siti.wisdomhydrologic.operation.mapper.ManageApplicationBrokenMapper;
import com.siti.wisdomhydrologic.operation.service.ManageApplicationBrokenService;
import com.siti.wisdomhydrologic.user.mapper.UserMapper;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zyw on 2019/7/31.
 */
@Service
public class ManageApplicationBrokenServiceImpl implements ManageApplicationBrokenService {

    @Resource
    private ManageApplicationBrokenMapper manageApplicationBrokenMapper;

    @Resource
    private ManageApplicationBrokenServiceImpl stationBrokenService;
    @Resource
    private UserMapper userMapper;
    @Override
    public List<ReportManageApplicationBroken> getAll(String createDate, String applicationEquipName) {
        return manageApplicationBrokenMapper.getAll(createDate, applicationEquipName);
    }

    @Override
    public int delete(Integer reportId) {
        return manageApplicationBrokenMapper.deleteById(reportId);
    }

    @Override
    public int update(ReportManageApplicationBroken reportManageApplicationBroken) {
        reportManageApplicationBroken.setCreateTime(reportManageApplicationBroken.getBrokenHappenTime()+":00:00");
        try {
            return manageApplicationBrokenMapper.updateData(reportManageApplicationBroken);

        }catch (Exception e){
            return 0;
        }
    }

    @Override
    public int insert(ReportManageApplicationBroken reportManageApplicationBroken) {
        reportManageApplicationBroken.setCreateTime(reportManageApplicationBroken.getBrokenHappenTime()+":00:00");
        try{
            return manageApplicationBrokenMapper.insertData(reportManageApplicationBroken);
        }catch (Exception e){
            return 0;
        }
    }

    /**
     * 模版单sheet导出示例
     *
     * @return
     */
    public Workbook exportSheetByTemplate(String createTime, String applicationEquipName, @RequestParam List<Integer> reportIdList) {
        // 查询数据,此处省略
        List<ReportManageApplicationBroken> list = stationBrokenService.getAll(createTime, applicationEquipName);

        /**
         * 选择导出reportList替换全部list
         * */
        if (reportIdList.size() > 0) {
            List<ReportManageApplicationBroken> reportlist = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                if (reportIdList.contains(list.get(i).getReportId())) {
                    reportlist.add(list.get(i));
                }
            }
            list = reportlist;
        }
        for (int i = 0; i < list.size(); i++) {
            ReportManageApplicationBroken data = list.get(i);
            data.setReportId(i + 1);
            if (data.getCreateTime() != null && data.getCreateTime().length() > 13)
                data.setCreateTime(data.getCreateTime().substring(8, 10) + "日" + data.getCreateTime().substring(11, 13) + "时");
            if (data.getBrokenResponseTime() != null && data.getBrokenResponseTime().length() > 13)
                data.setBrokenResponseTime(data.getBrokenResponseTime().substring(8, 10) + "日" + data.getBrokenResponseTime().substring(11, 13) + "时");
            if (data.getBrokenHappenTime() != null && data.getBrokenHappenTime().length() > 13)
                data.setBrokenHappenTime(data.getBrokenHappenTime().substring(8, 10) + "日" + data.getBrokenHappenTime().substring(11, 13) + "时");
            if (data.getBrokenResolveCreateTime() != null && data.getBrokenResolveCreateTime().length() > 13)
                data.setBrokenResolveCreateTime(data.getBrokenResolveCreateTime().substring(8, 10) + "日" + data.getBrokenResolveCreateTime().substring(11, 13) + "时");
            if (data.getBrokenResolveTime() != null && data.getBrokenResolveTime().length() > 13)
                data.setBrokenResolveTime(data.getBrokenResolveTime().substring(8, 10) + "日" + data.getBrokenResolveTime().substring(11, 13) + "时");
        }
        int count1 = 0;
        // 设置导出配置
        // 获取导出excel指定模版
        URL url = this.getClass().getClassLoader().getResource("");
        String logFilePath = url.getPath();
        TemplateExportParams params = new TemplateExportParams( "sqexcelmodel/model3.xls");
        File f = new File(this.getClass().getResource("/").getPath());
        // 标题开始行
        // params.setHeadingStartRow(0);
        // 标题行数
        // params.setHeadingRows(2);
        // 设置sheetName,若不设置该参数,则使用得原本得sheet名称
        params.setSheetName("表三");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", list);
        map.put("date", createTime);
        Workbook workbook = ExcelExportUtil.exportExcel(params, map);
        // 导出excel
        return workbook;
    }




    /**
     * 模版单sheet导出示例
     *
     * @return
     */
    public Workbook exportSheetByTemplate(String createTime, String applicationEquipName) {
        // 查询数据,此处省略
        List<ReportManageApplicationBroken> list = stationBrokenService.getAll(createTime, applicationEquipName);


        for (int i = 0; i < list.size(); i++) {
            ReportManageApplicationBroken data = list.get(i);
            data.setReportId(i + 1);
            if (data.getCreateTime() != null && data.getCreateTime().length() > 13)
                data.setCreateTime(data.getCreateTime().substring(8, 10) + "日" + data.getCreateTime().substring(11, 13) + "时");
            if (data.getBrokenResponseTime() != null && data.getBrokenResponseTime().length() > 13)
                data.setBrokenResponseTime(data.getBrokenResponseTime().substring(8, 10) + "日" + data.getBrokenResponseTime().substring(11, 13) + "时");
            if (data.getBrokenHappenTime() != null && data.getBrokenHappenTime().length() > 13)
                data.setBrokenHappenTime(data.getBrokenHappenTime().substring(8, 10) + "日" + data.getBrokenHappenTime().substring(11, 13) + "时");
            if (data.getBrokenResolveCreateTime() != null && data.getBrokenResolveCreateTime().length() > 13)
                data.setBrokenResolveCreateTime(data.getBrokenResolveCreateTime().substring(8, 10) + "日" + data.getBrokenResolveCreateTime().substring(11, 13) + "时");
            if (data.getBrokenResolveTime() != null && data.getBrokenResolveTime().length() > 13)
                data.setBrokenResolveTime(data.getBrokenResolveTime().substring(8, 10) + "日" + data.getBrokenResolveTime().substring(11, 13) + "时");
        }
        int count1 = 0;
        // 设置导出配置
        // 获取导出excel指定模版
        URL url = this.getClass().getClassLoader().getResource("");
        String logFilePath = url.getPath();
        TemplateExportParams params = new TemplateExportParams( "sqexcelmodel/model3.xls");
        File f = new File(this.getClass().getResource("/").getPath());
        // 标题开始行
        // params.setHeadingStartRow(0);
        // 标题行数
        // params.setHeadingRows(2);
        // 设置sheetName,若不设置该参数,则使用得原本得sheet名称
        params.setSheetName("表三");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", list);
        map.put("date", createTime);
        Workbook workbook = ExcelExportUtil.exportExcel(params, map);
        // 导出excel
        return workbook;
    }




}
