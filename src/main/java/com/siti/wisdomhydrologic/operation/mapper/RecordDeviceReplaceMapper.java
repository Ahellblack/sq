package com.siti.wisdomhydrologic.operation.mapper;

import com.siti.wisdomhydrologic.operation.entity.RecordDeviceReplace;
import com.siti.wisdomhydrologic.operation.vo.RecordDeviceReplaceVo;
import com.siti.wisdomhydrologic.statistics.vo.DeviceStatistics;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by dell on 2019/8/23.
 */
public interface RecordDeviceReplaceMapper  extends Mapper<RecordDeviceReplace>{

    @Select("<script>select * from record_device_replace " +
            "<if test=\"createDate!=null and  createDate!=''\"> where DATE_FORMAT(create_time,'%Y-%m') = #{createDate} </if>" +
            "<if test=\"stationId!=null  and stationId!=''\"> and station_code = #{stationId}  </if>" +
            "order by create_time desc" +
            "</script>")
    List<RecordDeviceReplaceVo> getAll(@Param("stationId") String stationId, @Param("createDate")String createDate);

    @Select("<script>select * from record_device_replace " +
            "where to_days(sensor_data_upload_time) = to_days(now())  " +
            "<if test=\"stationId!=null  and stationId!=''\"> and station_code = #{stationId}  </if>" +
            "order by create_time desc" +
            "</script>")
    List<RecordDeviceReplaceVo> getToday(@Param("stationId") String stationId);


    @Delete("delete from record_device_replace where report_id = #{reportId}")
    int deleteData(@Param("reportId") Integer reportId);

    @Update("update record_device_replace set replace_reason = #{RecordDeviceReplace.replaceReason}," +
            "create_by  = #{RecordDeviceReplace.createBy} where report_id = #{RecordDeviceReplace.reportId}")
    int updateData( @Param("RecordDeviceReplace")RecordDeviceReplace entity);

    @Select("select * from record_device_replace rdr  right join config_sensor_database csd  " +
            "on rdr.origin_device_code = csd.property_code " +
            "where origin_org_name is not null\n")
     List<RecordDeviceReplaceVo> getDeviceReplace();

    /**
     * 当月备品替换次数
     * */
    @Select("select origin_device_name,new_device_name,count(*) as displaytime " +
            "from record_device_replace rdr  right join config_sensor_database csd  " +
            "on rdr.origin_device_code = csd.property_code " +
            "where origin_org_name is not null " +
            "and DATE_FORMAT( rdr.create_time, '%Y%m' ) = DATE_FORMAT( CURDATE() , '%Y%m' )" +
            "group by new_device_name " +
            "ORDER BY displaytime desc ")
    List<DeviceStatistics> getNewStatistics();
    @Select("select origin_device_name,new_device_name,count(*) as displaytime " +
            "from record_device_replace rdr  right join config_sensor_database csd  " +
            "on rdr.origin_device_code = csd.property_code " +
            "where origin_org_name is not null " +
            "and DATE_FORMAT( rdr.create_time, '%Y%m' ) = DATE_FORMAT( CURDATE() , '%Y%m' )" +
            "group by origin_device_name " +
            "ORDER BY displaytime desc ")
    List<DeviceStatistics> getOrgStatistics();
}
