package com.siti.wisdomhydrologic.user.entity;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by DC on 2019/8/21.
 *
 * @data ${DATA}-11:37
 */
@Table(name="sys_role_permission")
public class RolePermission {

    @Id
    private int role_id;

    @Id
    private String permission_id;

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getPermission_id() {
        return permission_id;
    }

    public void setPermission_id(String permission_id) {
        this.permission_id = permission_id;
    }
}
