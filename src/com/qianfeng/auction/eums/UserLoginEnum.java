package com.qianfeng.auction.eums;

public enum UserLoginEnum {

	// ö�ٵĸ�ʽ   ��ȫ��Ҫ��д ������� ���»��߸���
	// ctrl + shift + y + x  (��Сдת��)
	// һ��������� ���漰��ҵ�񳡾� ��ô���ʱ�� ���Ǿ�Ҫ���ǵ���װһ��ö���ļ� ������¼��Щҵ�񳡾���������
	// �ÿ���������Աȥ����Ӳ�� ��Щ ����
	USER_NAME_IS_NUll("user_name_is_null","�û�������Ϊ��"),
	USER_PASSWORD_IS_NULL("user_password_is_null","���벻��Ϊ��"),
	USER_NAME_OR_PASSWORD_IS_FAIL("user_name_or_password_is_fail","�û��������벻��Ϊ��"),
	USER_VALIDATE_CODE_IS_FAIL("user_validate_code_is_fail","��֤�����"),
	USER_LOGIN_SUCCESS("user_login_success","��¼�ɹ�");
	
	private String value;
	private String desc;
	
	// ��ӹ��캯���� ��ݼ� �� alt + shift + s
	private UserLoginEnum(String value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	// ��ӹ�geter seter�� ��ݼ� �� alt + shift + s
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
