package com.qianfeng.auction.eums;

public enum AuctionOrderEnum {
	
	ADD_ORDER_SUCCESS("ADD_ORDER_SUCCESS", "������ӳɹ�"), ADD_ORDER_FAIL(
			"ADD_ORDER_FAIL", "��������ʧ��");

	private String value;
	private String desc;

	private AuctionOrderEnum(String value, String desc) {
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
