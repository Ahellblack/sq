package com.siti.wisdomhydrologic.user.entity;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by DC on 2019/8/21.
 *
 * @data ${DATA}-11:34
 */
@Table(name="sys_org")
public class Org {
    @Id
    private int id;

    private String shortName;

    private String name;

    private Integer pid;

    private String path;

    private String polygeom;

    private String centerGaodeLongitude;

    private String centerGaodeLatitude;

    private int gisLevel;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getPid() {
        return pid;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPolygeom() {
        return polygeom;
    }

    public void setPolygeom(String polygeom) {
        this.polygeom = polygeom;
    }

    public String getCenterGaodeLongitude() {
        return centerGaodeLongitude;
    }

    public void setCenterGaodeLongitude(String centerGaodeLongitude) {
        this.centerGaodeLongitude = centerGaodeLongitude;
    }

    public String getCenterGaodeLatitude() {
        return centerGaodeLatitude;
    }

    public void setCenterGaodeLatitude(String centerGaodeLatitude) {
        this.centerGaodeLatitude = centerGaodeLatitude;
    }

    public int getGisLevel() {
        return gisLevel;
    }

    public void setGisLevel(int gisLevel) {
        this.gisLevel = gisLevel;
    }
}
/*

    CREATE TABLE `sys_org` (
        `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
        `short_name` varchar(16) COLLATE utf8_bin DEFAULT NULL COMMENT '组织简称',
        `name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '企业名称',
        `sort` int(11) DEFAULT NULL COMMENT '排序号1 国；2省；3市；4区；5镇、街道；6组；99企业',
        `pid` int(11) DEFAULT NULL COMMENT '直接上级组织',
        `path` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '所有上级组织英文半角逗号分开',
        `polygeom` geometry DEFAULT NULL COMMENT '组织地图边界',
        `center_gaode_longitude` decimal(20,5) DEFAULT NULL COMMENT '组织地图位置经度',
        `center_gaode_latitude` decimal(20,5) DEFAULT NULL COMMENT '组织地图位置纬度',
        `gis_level` int(1) DEFAULT NULL COMMENT '组织地图缩放级别',
        `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
        `update_by` int(11) DEFAULT NULL COMMENT '修改人',
        `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
        PRIMARY KEY (`id`)
        ) ENGINE=InnoDB AUTO_INCREMENT=1110 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

*/
