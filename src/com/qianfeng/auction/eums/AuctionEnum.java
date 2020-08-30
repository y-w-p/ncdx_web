package com.qianfeng.auction.eums;

public enum AuctionEnum {
	
	AUCTION_NAME_IS_NULL("auction_name_is_null","����Ʒ������"),
	AUCTION_START_PRICE_IS_FAIL("auction_start_price_is_fail","�۸����"),
	AUCTION_LOW_PRICE_IS_FAIL("auction_low_price_is_fail","�׼۴���"),
	AUCTION_START_TIME_IS_FAIL("auction_start_time_is_fail","�������ڴ���"),
	AUCTION_END_TIME_IS_FAIL("auction_end_time_is_fail","�������ڴ���"),
	AUCTION_DESC_IS_FAIL("auction_desc_is_fail","��������"),
	AUCTION_ADD_FAIL("auction_add_fail","�������Ʒʧ��"),
	AUCTION_UPDATE_FAIL("auction_update_fail","�޸�����Ʒʧ��"),
	AUCTION_DELETE_FAIL("auction_delete_fail","�h������Ʒʧ��"),
	AUCTION_ADD_SUCCESS("auction_add_success","�������Ʒ�ɹ�"),
	AUCTION_UPDATE_SUCCESS("auction_update_success","�޸�����Ʒ�ɹ�"),
	AUCTION_DELETE_SUCCESS("auction_delete_success","ɾ������Ʒ�ɹ�"),
	AUCTION_SUCCESS("auction_success","���ĳɹ�"),
	AUCTION_FAIL("auction_fail","����ʧ��");
	
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
