package com.siti.wisdomhydrologic.log.entity;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "sys_log")
public class SysLog {
    @Id
    public Long id;
    public String username;
    public String action;
    public String previousVal;
    public String freshVal;
    public String operateDes;

    private SysLog(builder builder) {
        this.id = builder.id;
        this.username = builder.username;
        this.action = builder.action;
        this.previousVal = builder.previousVal;
        this.freshVal = builder.freshVal;
        this.operateDes = builder.operateDes;
    }

   public static class builder {
        private Long id;
        private String username;
        private String action;
        private String previousVal;
        private String freshVal;
        private String operateDes;

        public SysLog build() {
            return new SysLog(this);
        }

        public builder setId(Long id) {
            this.id = id;
            return this;
        }

        public builder setUsername(String username) {
            this.username = username;
            return this;
        }

        public builder setAction(String action) {
            this.action = action;
            return this;

        }

        public builder setPreviousVal(String previousVal) {
            this.previousVal = previousVal;
            return this;
        }



        public builder setFreshVal(String freshVal) {
            this.freshVal = freshVal;
            return this;
        }

        public builder setOperateDes(String operateDes) {
            this.operateDes = operateDes;
            return this;
        }
    }

}
