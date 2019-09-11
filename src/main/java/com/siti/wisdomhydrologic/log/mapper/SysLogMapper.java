package com.siti.wisdomhydrologic.log.mapper;

import com.siti.wisdomhydrologic.log.entity.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

/**
 * Created by DC on 2019/8/28.
 *
 * @data ${DATA}-10:40
 */
public interface SysLogMapper extends Mapper<SysLog>{
    @Insert("<script>insert into sys_log(username,action,previous_val," +
            "fresh_val,operate_des)values(sysLog.username,sysLog.action,sysLog.previousVal" +
            ",sysLog.freshVal,sysLog.operateDes);</script>")
    int insertUserOprLog(@Param("SysLog") SysLog sysLog);
}
