package com.qianfeng.auction.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import com.qianfeng.auction.entity.Role;

public class UserVO implements Serializable {

	private int id;
	private String username;
	private String password;
	private Timestamp createtime;
	private Timestamp updateTime;
	private String rolename;
	private String roleid;

	// JAVA����Ҫ��д�޲ι��캯��
	// public User() {
	// }

	// geter seter ��ݼ� �� alt + shift + s

	// geter seter ����ǧ��Ҫ��д���� ��Ϊ�����ڵ������� ��˽�е��������� �� ��ȡ �ͷ�װ�޹�
	// geter seter һ���ǳ������ļ��� ������ -- �������淶
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Timestamp getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

}
