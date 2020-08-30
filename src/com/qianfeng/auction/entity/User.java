package com.qianfeng.auction.entity;

import java.io.Serializable;
import java.sql.Timestamp;

// 什么样的文件才有资格叫实体类？
// 1：要符合ORM规范 
// (保持3个一直  表名和文件名一致)
// JAVA文件的属性名和数据库中的字段名要保持一致
// 数据库中的类型和JAVA中的类型要可以转换
// 2:要符合JAVABEAN规范
// 符合最基础的JAVABEAN 规范要满足以下几点
// (1)私有的属性
// (2)为私有的属性提供 GETER STEER
// (3)提供无参构造函数
// (4)实现序列化接口 (可以把文件存储内存中)
public class User implements Serializable {
	private int id;
	private String username;
	private String password;
	private Timestamp createtime;
	private Timestamp updateTime;
	private String email;
	private int state;

	// JAVA不需要手写无参构造函数
	// public User() {
	// }

	// geter seter 快捷键 是 alt + shift + s

	// geter seter 里面千万不要再写代码 因为它存在的意义是 给私有的属性设置 和 获取 和封装无关
	// geter seter 一定是出现在文件的 最下面 -- 阿里编码规范
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
