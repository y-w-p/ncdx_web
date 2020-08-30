package com.qianfeng.auction.eums;

public enum PermissionEnum {
	
	
	PERMISSION_ADD_AUCTION("addauction","添加拍賣品"),
	PERMISSION_UPDATE_AUCTION("updateauction","修改拍賣品"), 
	PERMISSION_DELETE_AUCTION("deleteauction","刪除拍賣品"), 
	PERMISSION_PAY_AUCTION("payauction","竟拍");
	

	private String value;
	private String desc;

	private PermissionEnum(String value, String desc) {
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
