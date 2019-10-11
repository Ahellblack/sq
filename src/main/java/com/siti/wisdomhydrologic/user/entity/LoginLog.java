package com.siti.wisdomhydrologic.user.entity;

import java.sql.Timestamp;

//
//@Entity
//@Table(name="loginlog")
public class LoginLog {

	public LoginLog(){
		
	}
	
//	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO)
//	@Column(name="id",nullable=false,unique=true)
	private Long id;
	
//	@Column(name="userid")
	private Long userid;
	
//	@Column(name="username")
	private String username;
	
//	@Column(name="ipaddress")
	private String ipaddress;
	
//	@Column(name="browsertype")
	private String browsertype;
	
//	@Column(name="logintime")
	private Timestamp logintime;
	
//	@Column(name="terminaltype")
	private Long terminaltype;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getBrowsertype() {
		return browsertype;
	}

	public void setBrowsertype(String browsertype) {
		this.browsertype = browsertype;
	}

	public Timestamp getLogintime() {
		return logintime;
	}

	public void setLogintime(Timestamp logintime) {
		this.logintime = logintime;
	}

	public String getIpaddress() {
		return ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

	public Long getTerminaltype() {
		return terminaltype;
	}

	public void setTerminaltype(Long terminaltype) {
		this.terminaltype = terminaltype;
	}
	
}
