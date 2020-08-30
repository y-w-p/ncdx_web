package com.qianfeng.auction.eums;

public enum RoleEnum {
	
	ROLE_ADMIN("admin","管理员"),
	ROLE_USER("user","用户");
	
	private String value;
	private String desc;
	private RoleEnum(String value, String desc) {
		this.value = value;
		this.desc = desc;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
}
