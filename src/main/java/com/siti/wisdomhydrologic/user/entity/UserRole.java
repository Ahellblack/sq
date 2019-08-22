package com.siti.wisdomhydrologic.user.entity;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by DC on 2019/8/21.
 *
 * @data ${DATA}-11:36
 */
@Table(name="sys_user_role")
public class UserRole {

    @Id
    private int uid;

    @Id
    private int rid;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }
}
