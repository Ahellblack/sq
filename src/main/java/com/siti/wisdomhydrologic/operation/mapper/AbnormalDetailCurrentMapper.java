package com.siti.wisdomhydrologic.operation.mapper;

import com.siti.wisdomhydrologic.operation.entity.AbnormalDetailCurrent;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * Created by dell on 2019/10/24.
 */
public interface AbnormalDetailCurrentMapper {

    @Select("select * from abnormal_detail_current adc left join config_abnormal_dictionary cad " +
            "on adc.data_error = cad.broken_according_id " +
            "where table2_display_status = 0")
    List<AbnormalDetailCurrent> get2Lastest();

    @Select("select * from abnormal_detail_current adc left join config_abnormal_dictionary cad " +
            "on adc.data_error = cad.broken_according_id " +
            "where table4_display_status = 0")
    List<AbnormalDetailCurrent> get4Lastest();


    @Update("<script>update abnormal_detail_current set table2_display_status = 1 " +
            "where id in " +
            "(<foreach collection=\"idList\" item=\"item\" separator=\",\">#{item}" +
            "</foreach>) </script>")
    Integer update2StatusList(@Param("idList") List<Integer> idList);

    @Update("<script>update abnormal_detail_current set table4_display_status = 1 " +
            "where id in " +
            "(<foreach collection=\"idList\" item=\"item\" separator=\",\">#{item}" +
            "</foreach>) </script>")
    Integer update4StatusList(@Param("idList") List<Integer> idList);

    @Select("update abnormal_detail_current set table4_display_status = 1 where id = #{id}")
    Integer update4Status(@Param("id") int id);


    @Select("update abnormal_detail_current set table2_display_status = 1 where id = #{id}")
    Integer update2Status(@Param("id") int id);

    @Select("SELECT * FROM `abnormal_detail_current` adc  " +
            "left join config_sensor_section_module cssm " +
            "on adc.sensor_code = cssm.section_code " +
            "where cssm.station_code = #{stationId} and is_recover_status = 0 ")
    int getUnCoverData(@Param("stationId") Integer stationId);
}
