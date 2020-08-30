package com.qianfeng.auction.eums;

public enum AuctionEnum {
	
	AUCTION_NAME_IS_NULL("auction_name_is_null","拍卖品名错误"),
	AUCTION_START_PRICE_IS_FAIL("auction_start_price_is_fail","价格错误"),
	AUCTION_LOW_PRICE_IS_FAIL("auction_low_price_is_fail","底价错误"),
	AUCTION_START_TIME_IS_FAIL("auction_start_time_is_fail","起拍日期错误"),
	AUCTION_END_TIME_IS_FAIL("auction_end_time_is_fail","结束日期错误"),
	AUCTION_DESC_IS_FAIL("auction_desc_is_fail","描述错误"),
	AUCTION_ADD_FAIL("auction_add_fail","添加拍卖品失敗"),
	AUCTION_UPDATE_FAIL("auction_update_fail","修改拍卖品失敗"),
	AUCTION_DELETE_FAIL("auction_delete_fail","刪除拍卖品失敗"),
	AUCTION_ADD_SUCCESS("auction_add_success","添加拍卖品成功"),
	AUCTION_UPDATE_SUCCESS("auction_update_success","修改拍卖品成功"),
	AUCTION_DELETE_SUCCESS("auction_delete_success","删除拍卖品成功"),
	AUCTION_SUCCESS("auction_success","竞拍成功"),
	AUCTION_FAIL("auction_fail","竞拍失败");
	
	private String value;
	private String desc;
	
	private AuctionEnum(String value, String desc) {
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
