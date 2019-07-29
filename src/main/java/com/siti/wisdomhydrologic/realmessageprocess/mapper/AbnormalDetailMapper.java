package com.siti.wisdomhydrologic.realmessageprocess.mapper;

import com.siti.wisdomhydrologic.realmessageprocess.entity.AbnormalDetailEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by DC on 2019/7/19.
 * @data ${DATA}-18:03
 */
public interface AbnormalDetailMapper extends Mapper<AbnormalDetailEntity> {

    @Select("<script>replace into abnormal_detail(date,sensor_code,below,above,floating,overtime,continue_interrupt)\n" +
            "values <foreach collection=\"list\" index=\"index\" item=\"item\" separator=\",\">" +
            "(#{item.date},#{item.sensorCode},#{item.below})</foreach></script>\n")
    void insertAndUpdate(@Param("list") List<AbnormalDetailEntity> list);

}
