package com.siti.wisdomhydrologic.statistics.mapper;

import com.siti.wisdomhydrologic.statistics.entity.DeviceChange;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by dell on 2019/9/23.
 */
public interface DeviceMapper {

    @Select("<script>select count(*) as number,new_device_name from record_device_replace " +
            "where station_name like '%${stationName}%' GROUP BY new_device_name</script>")
    List<DeviceChange> getList(@Param("stationName") String stationName);
}
