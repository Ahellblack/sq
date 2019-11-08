package com.siti.wisdomhydrologic.realmessageprocess.mapper;

import com.siti.wisdomhydrologic.mainpage.vo.AbnormalDetailVo;
import com.siti.wisdomhydrologic.operation.entity.ReportManageApplicationBroken;
import com.siti.wisdomhydrologic.operation.vo.ReportManageDataMantainVo;
import com.siti.wisdomhydrologic.realmessageprocess.entity.*;
import com.siti.wisdomhydrologic.realmessageprocess.vo.RealVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by DC on 2019/7/19.
 *
 * @data ${DATA}-18:03
 */
public interface AbnormalDetailMapper extends Mapper<AbnormalDetailEntity> {


    @Select("<script>select * from abnormal_detail_current"+
            "<if test=\"date!=null\" >where  is_recover_status = '0' </if></script>")//last_date &gt;= #{date} and
    List<AbnormalDetailEntity> getCurrentAbnormal(@Param("date") String date);

    @Select("<script>select * from abnormal_detail_current ad " +
            " left join config_abnormal_dictionary cad on ad.data_error = cad.broken_according_id" +
            "<if test=\"time!=null\">where last_date &gt;= #{time}</if> " +
            "<if test=\"stationCode!=null\">  and  SUBSTR(sensor_code,1,5) = #{stationCode}</if></script>")
    List<AbnormalDetailVo> getStationLatestData(@Param("time") String time, @Param("stationCode") Integer stationCode);


    @Select("select * from abnormal_detail " +
            "where SUBSTR(sensor_code,1,5) = #{stationId} " +
            "and date BETWEEN #{latest30minute} and #{date} " +
            "and data_error = #{accordingId}")
    List<AbnormalDetailEntity> get30MinuteDate(@Param("stationId")String stationId,@Param("accordingId")String accordingId,@Param("date")String date,@Param("latest30minute") String latest30minute);

/*
    @Update("<script>update abnormal_detail set table4_display_status = 1 " +
            "where id in " +
            "(<foreach collection=\"idList\" item=\"item\" separator=\",\">#{item}" +
            "</foreach>) </script>")
    int updateTable4Status(@Param("idList") List<Integer> idList);

    @Update("<script>update abnormal_detail set table2_display_status = 1 " +
            "where id in " +
            "(<foreach collection=\"idList\" item=\"item\" separator=\",\">#{item}" +
            "</foreach>) </script>")
    int updateTable2Status(@Param("idList") List<Integer> idList);
*/

}
