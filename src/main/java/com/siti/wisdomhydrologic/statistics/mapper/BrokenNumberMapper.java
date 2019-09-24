package com.siti.wisdomhydrologic.statistics.mapper;

import com.siti.wisdomhydrologic.statistics.entity.BrokenType;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by dell on 2019/9/23.
 */
public interface BrokenNumberMapper {

    @Select("<script>select count(*) as number,d.broken_according_id,d.broken_according from (" +
            "select c.broken_according,c.broken_according_id,a.station_id from report_station_broken a " +
            "right join config_river_station b on a.station_id = b.station_id   " +
            "right join config_abnormal_dictionary c on c.broken_according_id = a.broken_according_id  " +
            "where 1=1 " +
            "<if test = \'stationName != null \'> and a.station_name like '%${stationName}%' </if> " +
            "<if test = \'yearMonth != null \'> and DATE_FORMAT(a.create_time,'%Y-%m') = #{yearMonth}</if> ) d" +
            " where d.station_id is not null" +
            " GROUP BY d.broken_according_id  </script>")
    List<BrokenType> getList(@Param("stationName") String stationName,@Param("yearMonth") String yearMonth);

}
