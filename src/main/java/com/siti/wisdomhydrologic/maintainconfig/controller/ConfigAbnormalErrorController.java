package com.siti.wisdomhydrologic.maintainconfig.controller;

import com.alibaba.fastjson.JSONObject;
import com.siti.wisdomhydrologic.log.entity.SysLog;
import com.siti.wisdomhydrologic.log.mapper.SysLogMapper;
import com.siti.wisdomhydrologic.maintainconfig.entity.ConfigAbnormalError;
import com.siti.wisdomhydrologic.maintainconfig.mapper.ConfigAbnormalErrorMapper;
import com.siti.wisdomhydrologic.user.entity.User;
import com.siti.wisdomhydrologic.user.service.RedisBiz;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/abnormalError")
public class ConfigAbnormalErrorController {

    @Resource
    private ConfigAbnormalErrorMapper configAbnormalErrorMapper;
    @Resource
    private RedisBiz redisBiz;
    @Resource
    private SysLogMapper sysLogMapper;

    // 根据表序号查找异常字典
    @RequestMapping("/findAllByTypeID")
    public JSONObject findAllByBelongTable(@RequestParam(value = "tableIndex") Integer tableIndex) {
        JSONObject jsonObject = new JSONObject();
        try {
            List<ConfigAbnormalError> list = configAbnormalErrorMapper.getAllByTableIndex(tableIndex);
            if (null != list) {
                jsonObject.put("abnormalErrorList", list);
                jsonObject.put("status", 1);
                jsonObject.put("message", "查询成功！");
                jsonObject.put("tableIndex", tableIndex);
                return jsonObject;
            } else {
                jsonObject.put("status", 0);
                jsonObject.put("message", "查询结束,无匹配记录！");
                jsonObject.put("tableIndex", tableIndex);
                return jsonObject;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        jsonObject.put("status", -1);
        jsonObject.put("message", "异常错误！");
        jsonObject.put("tableIndex", tableIndex);
        return jsonObject;
    }

    @RequestMapping("/insert")
    public JSONObject insert(@RequestBody ConfigAbnormalError configAbnormalError, HttpSession session) {
        JSONObject jsonObject = new JSONObject();
        try {
            if (null == configAbnormalError.getErrorName() || "".equals(configAbnormalError.getErrorName())) {
                jsonObject.put("status", 0);
                jsonObject.put("message", "名称缺失！");
                return jsonObject;
            } else if (null == configAbnormalError.getBelongWhichTable()) {
                jsonObject.put("status", 0);
                jsonObject.put("message", "关联表序号缺失！");
                return jsonObject;
            } else {
                // 用户自定义添加的选项以"D"开头
                configAbnormalError.setErrorId("D" + System.currentTimeMillis());
                if (0 != configAbnormalErrorMapper.insert(configAbnormalError)) {
                    jsonObject.put("status", 1);
                    jsonObject.put("message", "添加成功！");
                    User user = (User) redisBiz.get(session.getId());
                    sysLogMapper.insertUserOprLog( new SysLog.builder()
                            .setUsername(user.getUserName())
                            .setOperateDes("异常字典表"+jsonObject)
                            .setFreshVal(configAbnormalError+"")
                            .setAction("添加")
                            .setPreviousVal("")
                            .build());


                    return jsonObject;
                } else {
                    jsonObject.put("status", 2);
                    jsonObject.put("message", "其它原因，添加失败！");
                    return jsonObject;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        jsonObject.put("status", -1);
        jsonObject.put("message", "异常错误！");
        return jsonObject;
    }

    @RequestMapping("/update")
    public JSONObject update(@RequestBody ConfigAbnormalError configAbnormalError,HttpSession session) {
        JSONObject jsonObject = new JSONObject();
        try {
            if (null == configAbnormalError.getErrorId() || "".equals(configAbnormalError.getErrorId())) {
                jsonObject.put("status", 0);
                jsonObject.put("message", "ID缺失！");
                return jsonObject;
            } else if (null == configAbnormalError.getErrorName() || "".equals(configAbnormalError.getErrorName())) {
                jsonObject.put("status", 0);
                jsonObject.put("message", "名称缺失！");
                return jsonObject;
            } else if (null == configAbnormalError.getBelongWhichTable()) {
                jsonObject.put("status", 0);
                jsonObject.put("message", "关联表序号缺失！");
                return jsonObject;
            }  else if(0 == configAbnormalErrorMapper.getCountByErrorId(configAbnormalError.getErrorId())) {
                jsonObject.put("status", 0);
                jsonObject.put("message", "ID不存在，修改失败！");
                return jsonObject;
            }else {
                if (0 != configAbnormalErrorMapper.update(configAbnormalError)) {
                    jsonObject.put("status", 1);
                    jsonObject.put("message", "修改成功！");

                    User user = (User) redisBiz.get(session.getId());
                    sysLogMapper.insertUserOprLog( new SysLog.builder()
                            .setUsername(user.getUserName())
                            .setOperateDes("异常字典表"+jsonObject)
                            .setFreshVal(configAbnormalError+"")
                            .setAction("添加")
                            .setPreviousVal("")
                            .build());

                    return jsonObject;
                } else {
                    jsonObject.put("status", 2);
                    jsonObject.put("message", "其它原因，修改失败！");
                    return jsonObject;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        jsonObject.put("status", -1);
        jsonObject.put("message", "异常错误！");
        return jsonObject;
    }

    @RequestMapping("/delete")
    public JSONObject delete(@RequestParam(value = "errorId") String errorId,HttpSession session) {
        JSONObject jsonObject = new JSONObject();
        try {
            if (null == errorId ) {
                jsonObject.put("status", 0);
                jsonObject.put("message", "ID缺失，删除失败！");
                return jsonObject;
            } else if (!errorId.startsWith("D")) {
                jsonObject.put("status", 0);
                jsonObject.put("message", "非自定义添加的类型，删除失败！");
                return jsonObject;
            } else if(0 == configAbnormalErrorMapper.getCountByErrorId(errorId)) {
                jsonObject.put("status", 0);
                jsonObject.put("message", "ID不存在，删除失败！");
                return jsonObject;
            }else {
                if (0 != configAbnormalErrorMapper.delete(errorId)) {
                    jsonObject.put("status", 1);
                    jsonObject.put("message", "删除成功！");
                    User user = (User) redisBiz.get(session.getId());
                    sysLogMapper.insertUserOprLog( new SysLog.builder()
                            .setUsername(user.getUserName())
                            .setOperateDes("异常字典表"+jsonObject)
                            .setFreshVal(errorId+"")
                            .setAction("添加")
                            .setPreviousVal("")
                            .build());
                    return jsonObject;
                } else {
                    jsonObject.put("status", 2);
                    jsonObject.put("message", "其它原因，删除失败！");
                    return jsonObject;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        jsonObject.put("status", -1);
        jsonObject.put("message", "异常错误！");
        return jsonObject;
    }
}
