package com.qianfeng.auction.eums;

public enum UserLoginEnum {

	// 枚举的格式   是全部要大写 多个单词 用下划线隔开
	// ctrl + shift + y + x  (大小写转换)
	// 一个功能如果 有涉及到业务场景 那么这个时候 我们就要考虑到封装一个枚举文件 用来记录这些业务场景，而不是
	// 让开发开发人员去死记硬背 这些 单词
	USER_NAME_IS_NUll("user_name_is_null","用户名不能为空"),
	USER_PASSWORD_IS_NULL("user_password_is_null","密码不能为空"),
	USER_NAME_OR_PASSWORD_IS_FAIL("user_name_or_password_is_fail","用户名和密码不能为空"),
	USER_VALIDATE_CODE_IS_FAIL("user_validate_code_is_fail","验证码错误"),
	USER_LOGIN_SUCCESS("user_login_success","登录成功");
	
	private String value;
	private String desc;
	
	// 添加构造函数的 快捷键 是 alt + shift + s
	private UserLoginEnum(String value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	// 添加构geter seter的 快捷键 是 alt + shift + s
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
