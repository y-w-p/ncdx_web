package com.qianfeng.auction.entity;

import java.io.Serializable;
import java.sql.Timestamp;

// ʲô�����ļ������ʸ��ʵ���ࣿ
// 1��Ҫ����ORM�淶 
// (����3��һֱ  �������ļ���һ��)
// JAVA�ļ��������������ݿ��е��ֶ���Ҫ����һ��
// ���ݿ��е����ͺ�JAVA�е�����Ҫ����ת��
// 2:Ҫ����JAVABEAN�淶
// �����������JAVABEAN �淶Ҫ�������¼���
// (1)˽�е�����
// (2)Ϊ˽�е������ṩ GETER STEER
// (3)�ṩ�޲ι��캯��
// (4)ʵ�����л��ӿ� (���԰��ļ��洢�ڴ���)
public class User implements Serializable {
	private int id;
	private String username;
	private String password;
	private Timestamp createtime;
	private Timestamp updateTime;
	private String email;
	private int state;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
	

}
