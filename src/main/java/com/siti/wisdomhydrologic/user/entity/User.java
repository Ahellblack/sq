package com.siti.wisdomhydrologic.user.entity;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

/**
 * Created by DC on 2019/8/21.
 *
 * @data ${DATA}-11:33
 */
@Table(name="sys_user")
public class User {
    @Id
    private int id;

    private String userName;

    private String realName;

    private String password;

    private String sex;

    private String image;

    private String phoneNum;

    private int status;

    private String updateBy;

    private String updateTime;

    private String remark;

    private String lastTime;

    private String userRole;
    @Transient
    List<Role> roles;
    @Transient
    List<Permission> menuList ;   //  获取角色的目录权限
    @Transient
    List<Org> orgList;

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Permission> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Permission> menuList) {
        this.menuList = menuList;
    }

    public List<Org> getOrgList() {
        return orgList;
    }

    public void setOrgList(List<Org> orgList) {
        this.orgList = orgList;
    }

    public User(User user) {
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public User(int id, String userName, String realName, String password, String sex, String image, String phoneNum, int status, String updateBy, String updateTime, String remark, String lastTime) {
        this.id = id;
        this.userName = userName;
        this.realName = realName;
        this.password = password;
        this.sex = sex;
        this.image = image;
        this.phoneNum = phoneNum;
        this.status = status;
        this.updateBy = updateBy;
        this.updateTime = updateTime;
        this.remark = remark;
        this.lastTime = lastTime;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public User() {
    }
}