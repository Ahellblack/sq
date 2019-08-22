package com.siti.wisdomhydrologic.user.entity;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by DC on 2019/8/21.
 *
 * @data ${DATA}-11:33
 */
@Table(name="sys_role")
public class Role {
    @Id
    private int id;

    private String name;

    private String  path;

    private String type;

    private String descr;

    private String updateBy;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }
}

    /*CREATE TABLE `sys_role` (
        `id` int(100) NOT NULL AUTO_INCREMENT,
        `name` varchar(32) NOT NULL COMMENT '角色名称',
        `path` varchar(255) DEFAULT NULL COMMENT '角色编码',
        `type` enum('system','infocheck','maintain','visitor') DEFAULT NULL COMMENT '类型：系统管理员；infocheck信息检查人员；maintain运维角色 visitor普通访客',
        `descr` varchar(128) DEFAULT NULL COMMENT '描述',
        `update_by` int(11) DEFAULT NULL COMMENT '操作用户ID',
        `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
        PRIMARY KEY (`id`),
        UNIQUE KEY `name` (`name`) USING BTREE,
        UNIQUE KEY `code` (`path`) USING BTREE
        ) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='角色';

*/