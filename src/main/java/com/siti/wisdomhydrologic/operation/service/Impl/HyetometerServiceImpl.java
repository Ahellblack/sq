package com.siti.wisdomhydrologic.operation.service.Impl;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.siti.wisdomhydrologic.configmaintain.entity.ConfigRiverStation;
import com.siti.wisdomhydrologic.configmaintain.mapper.ConfigRiverStationMapper;
import com.siti.wisdomhydrologic.operation.entity.ReportHyetometerTest;
import com.siti.wisdomhydrologic.operation.mapper.HyetometerMapper;
import com.siti.wisdomhydrologic.user.entity.Org;
import com.siti.wisdomhydrologic.user.entity.User;
import com.siti.wisdomhydrologic.user.mapper.UserMapper;
import com.siti.wisdomhydrologic.user.service.UserInfoService;
import com.siti.wisdomhydrologic.util.DateTransform;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.net.URL;
import java.util.*;

/**
 * Created by zyw on 2019/7/26.
 */
@Service
public class HyetometerServiceImpl  {

    @Resource
    ConfigRiverStationMapper configRiverStationMapper;
    @Resource
    private HyetometerMapper reportHyetometerMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserInfoService userInfoService;

    public List<ReportHyetometerTest> getAll(HttpSession session,String createTime, String stationId) {
        User user = (User) userInfoService.get();
        List<Org> orgList = userMapper.findOrg(user.getId());
        if (orgList.size()>0){
            return reportHyetometerMapper.getAll(createTime, stationId,orgList.get(0).getId());
        }else {
            return null;
        }
    }

    public int delByReportId(Integer reportId) {
        return reportHyetometerMapper.delByReportId(reportId);
    }

    public int insert(ReportHyetometerTest reportHyetometer) {

        List<ConfigRiverStation> allByStationName = configRiverStationMapper.getAllByStationName(reportHyetometer.getStationName());
        reportHyetometer.setStationCode(allByStationName.get(0).getStationId());
        if (reportHyetometer.getCreateTime() == null) {
            reportHyetometer.setCreateTime(DateTransform.Date2String(new Date(), "yyyy-MM-dd HH:mm:ss"));
        }
        reportHyetometer.setLibraryDate(reportHyetometer.getCreateTime());
        try {
            reportHyetometerMapper.insert(reportHyetometer);
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }

    public int update(ReportHyetometerTest reportHyetometer) {
        List<ConfigRiverStation> allByStationName = configRiverStationMapper.getAllByStationName(reportHyetometer.getStationName());
        reportHyetometer.setStationCode(allByStationName.get(0).getStationId());
        try {
            reportHyetometerMapper.update(reportHyetometer);
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }

    public int delByReportIdList(List<Integer> reportIdList) {
        int sum = 0;
        for (Integer id : reportIdList) {
            int i = delByReportId(id);
            sum = sum + i;
        }
        return sum;
    }



    /**
     * 模版单sheet导出示例
     *
     * @return
     */
    public Workbook exportSheetByTemplate(HttpSession session, String createTime, String stationId) {

        User user = (User) userInfoService.get();
        List<Org> orgList = userMapper.findOrg(user.getId());        // 查询数据,此处省略

        List<ReportHyetometerTest> list = reportHyetometerMapper.getAll(createTime, stationId, orgList.get(0).getId());

        for (int i = 0; i < list.size(); i++) {
            ReportHyetometerTest data = list.get(i);
            data.setReportId(i + 1);
            if (data.getStartTime() != null)
                data.setStartTime(data.getStartTime().substring(11, 19) );
            if (data.getEndTime() != null)
                data.setEndTime(data.getEndTime().substring(11, 19) );

//            data.getTimeDuration();
//            data.getStartTime();
//            data.getEndTime();
//            if (data.getTimeDuration() != null)
//                data.setTimeDuration(data.getTimeDuration().substring(11, 13) + "时" + data.getTimeDuration().substring(14, 16) + "分" + data.getTimeDuration().substring(17, 19) + "秒");
//            if (data.getStartTime() != null)
//                data.setStartTime(data.getStartTime().substring(11, 13) + "时" + data.getStartTime().substring(14, 16) + "分" + data.getStartTime().substring(17, 19) + "秒");
//            if (data.getEndTime() != null)
//                data.setEndTime(data.getEndTime().substring(11, 13) + "时" + data.getEndTime().substring(14, 16) + "分" + data.getEndTime().substring(17, 19) + "秒");
//            if (data.getCreateTime() != null)
//                data.setCreateTime(data.getCreateTime().substring(0, 4) + "年" + data.getCreateTime().substring(5, 7) + "月" + data.getCreateTime().substring(8, 10) + "日");
        }
        int count1 = 0;
        // 设置导出配置

        //获取动态路径
        File f = new File(this.getClass().getResource("/").getPath());
        /*String programpath = f.getPath().split("target")[0] ;
        System.out.println(programpath);
        String filepath = "resources/sqexcelmodel/model4.xls";
*/
        URL url = this.getClass().getClassLoader().getResource("");
        String logFilePath = url.getPath();
        // 获取导出excel指定模版
        TemplateExportParams params = new TemplateExportParams( "sqexcelmodel/model6.xls");
        // 标题开始行
        // params.setHeadingStartRow(0);
        // 标题行数
        // params.setHeadingRows(2);
        // 设置sheetName,若不设置该参数,则使用得原本得sheet名称
        params.setSheetName("表六");
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
    public Workbook exportSheetByTemplate(HttpSession session, String createTime, String stationId,  List<Integer> reportIdList) {

        User user = (User) userInfoService.get();
        List<Org> orgList = userMapper.findOrg(user.getId());        // 查询数据,此处省略

        List<ReportHyetometerTest> list = reportHyetometerMapper.getAll(createTime, stationId, orgList.get(0).getId());
        /**
         * 选择导出reportList替换全部list
         * */
        if (reportIdList.size() > 0) {
            List<ReportHyetometerTest> reportlist = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                if (reportIdList.contains(list.get(i).getReportId())) {
                    reportlist.add(list.get(i));
                }
            }
            list = reportlist;
        }
        for (int i = 0; i < list.size(); i++) {
            ReportHyetometerTest data = list.get(i);
            data.setReportId(i + 1);
            if (data.getStartTime() != null)
                data.setStartTime(data.getStartTime().substring(11, 19) );
            if (data.getEndTime() != null)
                data.setEndTime(data.getEndTime().substring(11, 19) );
//            data.getTimeDuration();
//            data.getStartTime();
//            data.getEndTime();
//            if (data.getTimeDuration() != null)
//                data.setTimeDuration(data.getTimeDuration().substring(11, 13) + "时" + data.getTimeDuration().substring(14, 16) + "分" + data.getTimeDuration().substring(17, 19) + "秒");
//            if (data.getStartTime() != null)
//                data.setStartTime(data.getStartTime().substring(11, 13) + "时" + data.getStartTime().substring(14, 16) + "分" + data.getStartTime().substring(17, 19) + "秒");
//            if (data.getEndTime() != null)
//                data.setEndTime(data.getEndTime().substring(11, 13) + "时" + data.getEndTime().substring(14, 16) + "分" + data.getEndTime().substring(17, 19) + "秒");
//            if (data.getCreateTime() != null)
//                data.setCreateTime(data.getCreateTime().substring(0, 4) + "年" + data.getCreateTime().substring(5, 7) + "月" + data.getCreateTime().substring(8, 10) + "日");

            // if (data.getCreateTime()!= null)data.setCreateTime(data.getCreateTime().substring(8,10)+"日"+data.getCreateTime().substring(11,13)+"时");
        }
        int count1 = 0;
        // 设置导出配置


        //获取动态路径
        File f = new File(this.getClass().getResource("/").getPath());
        /*String programpath = f.getPath().split("target")[0] ;
        System.out.println(programpath);
        String filepath = "resources/sqexcelmodel/model4.xls";
*/
        URL url = this.getClass().getClassLoader().getResource("");
        String logFilePath = url.getPath();
        // 获取导出excel指定模版
        TemplateExportParams params = new TemplateExportParams( "sqexcelmodel/model6.xls");
        // 标题开始行
        // params.setHeadingStartRow(0);
        // 标题行数
        // params.setHeadingRows(2);
        // 设置sheetName,若不设置该参数,则使用得原本得sheet名称
        params.setSheetName("表六");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", list);
        map.put("date", createTime);
        Workbook workbook = ExcelExportUtil.exportExcel(params, map);
        // 导出excel
        return workbook;
    }
}
