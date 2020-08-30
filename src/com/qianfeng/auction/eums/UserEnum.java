package com.qianfeng.auction.eums;

public enum UserEnum {

	USER_UPDATE_SUCCESS("user_update_success", "修改成功"), USER_UPDATE_PERMISSION_FAIL(
			"user_update_success", "最少选择一个权限"), USER_UPDATE_ROLE_FAIL(
			"user_update_success", "最少选择一个角色");

	private String value;
	private String desc;

	private UserEnum(String value, String desc) {
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
