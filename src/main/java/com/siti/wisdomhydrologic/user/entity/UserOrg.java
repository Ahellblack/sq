package com.siti.wisdomhydrologic.user.entity;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by DC on 2019/8/21.
 *
 * @data ${DATA}-11:36
 */
@Table(name="sys_user_org")
public class UserOrg {
    @Id
    private int uid;
    @Id
    private int orgId;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }
}
