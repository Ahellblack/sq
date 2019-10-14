package com.siti.wisdomhydrologic.operation.vo;

import java.util.List;

/**
 * Created by dell on 2019/9/30.
 */
public class ReportListVo {

    List<Integer> reportList;

    public List<Integer> getReportList() {
        return reportList;
    }

    public void setReportList(List<Integer> reportList) {
        this.reportList = reportList;
    }

    @Override
    public String toString() {
        return "ReportListVo{" + "reportList=" + reportList + '}';
    }
}
