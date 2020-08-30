package com.qianfeng.auction.eums;

public enum AuctionOrderEnum {
	
	ADD_ORDER_SUCCESS("ADD_ORDER_SUCCESS", "订单添加成功"), ADD_ORDER_FAIL(
			"ADD_ORDER_FAIL", "订单条加失败");

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
