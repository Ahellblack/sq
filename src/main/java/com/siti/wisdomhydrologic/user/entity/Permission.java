package com.siti.wisdomhydrologic.user.entity;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

/**
 * Created by DC on 2019/8/21.
 *
 * @data ${DATA}-11:34
 */
@Table(name="sys_permission")
public class Permission {

    @Id
    private int id;

    private String description;

    private int sort;

    private String type;

    private String path;

    private String uri;

    @Transient
    private List<Permission> child;

    public List<Permission> getChild() {
        return child;
    }

    public void setChild(List<Permission> child) {
        this.child = child;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}

/*


    CREATE TABLE `sys_permission` (
        `id` int(11) NOT NULL COMMENT '编号',
        `description` varchar(100) DEFAULT NULL COMMENT '操作描述',
        `sort` tinyint(2) DEFAULT NULL COMMENT '排序',
        `type` varchar(20) DEFAULT NULL COMMENT '菜单类型',
        `path` varchar(500) DEFAULT NULL COMMENT '完全路径',
        `uri` varchar(500) DEFAULT NULL COMMENT 'URI',
        PRIMARY KEY (`id`),
        KEY `log_id` (`id`)
        ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='操作日志';

*/
