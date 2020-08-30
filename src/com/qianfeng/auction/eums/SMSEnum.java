package com.qianfeng.auction.eums;

public enum SMSEnum {
	SMS_ADD_SUCCESS("sms_add_success"), SMS_ADD_FAIL("sms_add_fail"), SMS_DELETE_SUCCESS(
			"sms_delete_success"), SMS_DELETE_FAIL("sms_delete_fail");
	private String value;

	private SMSEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
