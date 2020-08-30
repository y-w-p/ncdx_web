package com.qianfeng.auction.eums;

public enum PermissionEnum {
	
	
	PERMISSION_ADD_AUCTION("addauction","������uƷ"),
	PERMISSION_UPDATE_AUCTION("updateauction","�޸����uƷ"), 
	PERMISSION_DELETE_AUCTION("deleteauction","�h�����uƷ"), 
	PERMISSION_PAY_AUCTION("payauction","����");
	

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
