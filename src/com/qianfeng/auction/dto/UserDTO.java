package com.qianfeng.auction.dto;

import java.sql.Timestamp;

public class UserDTO {
	private int id;
	private String username;
	private String password;
	private Timestamp createtime;
	private Timestamp updateTime;
	private String email;
	private int state;
	private int auctionprice;

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

	public int getAuctionprice() {
		return auctionprice;
	}

	public void setAuctionprice(int auctionprice) {
		this.auctionprice = auctionprice;
	}
	
	

}
