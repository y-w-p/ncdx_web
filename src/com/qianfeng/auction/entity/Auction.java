package com.qianfeng.auction.entity;

import java.io.Serializable;

public class Auction implements Serializable{
	
	private int id;
	private String auctionname;
	private int auctionstartprice;
	private int auctionlowprice;
	private java.sql.Timestamp auctionstarttime;
	private java.sql.Timestamp auctionendtime;
	private String auctiondesc;
	private java.sql.Timestamp auctioncreatetime;
	private java.sql.Timestamp auctionupdatetime;
	private String auctionimage;
	private int state;
	
	

	// alt + shift + s ÷ÿ–¥TOSTRING
	@Override
	public String toString() {
		return "Auction [id=" + id + ", auctionname=" + auctionname
				+ ", auctionstartprice=" + auctionstartprice
				+ ", auctionlowprice=" + auctionlowprice + ", auctionstarttime="
				+ auctionstarttime + ", auctionendtime=" + auctionendtime
				+ ", auctiondesc=" + auctiondesc + ", auctioncreatetime="
				+ auctioncreatetime + ", auctionupdatetime="
				+ auctionupdatetime + ", auctionimage=" + auctionimage + "]";
	}

	// alt shift + s
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAuctionname() {
		return auctionname;
	}

	public void setAuctionname(String auctionname) {
		this.auctionname = auctionname;
	}

	public int getAuctionstartprice() {
		return auctionstartprice;
	}

	public void setAuctionstartprice(int auctionstartprice) {
		this.auctionstartprice = auctionstartprice;
	}

	public int getAuctionlowprice() {
		return auctionlowprice;
	}

	public void setAuctionlowprice(int auctionlowprice) {
		this.auctionlowprice = auctionlowprice;
	}

	public java.sql.Timestamp getauctionstarttime() {
		return auctionstarttime;
	}

	public void setauctionstarttime(java.sql.Timestamp auctionstarttime) {
		this.auctionstarttime = auctionstarttime;
	}

	public java.sql.Timestamp getAuctionendtime() {
		return auctionendtime;
	}

	public void setAuctionendtime(java.sql.Timestamp auctionendtime) {
		this.auctionendtime = auctionendtime;
	}

	public String getAuctiondesc() {
		return auctiondesc;
	}

	public void setAuctiondesc(String auctiondesc) {
		this.auctiondesc = auctiondesc;
	}

	public java.sql.Timestamp getAuctioncreatetime() {
		return auctioncreatetime;
	}

	public void setAuctioncreatetime(java.sql.Timestamp auctioncreatetime) {
		this.auctioncreatetime = auctioncreatetime;
	}

	public java.sql.Timestamp getAuctionupdatetime() {
		return auctionupdatetime;
	}

	public void setAuctionupdatetime(java.sql.Timestamp auctionupdatetime) {
		this.auctionupdatetime = auctionupdatetime;
	}

	public String getAuctionimage() {
		return auctionimage;
	}

	public void setAuctionimage(String auctionimage) {
		this.auctionimage = auctionimage;
	}

	public java.sql.Timestamp getAuctionstarttime() {
		return auctionstarttime;
	}

	public void setAuctionstarttime(java.sql.Timestamp auctionstarttime) {
		this.auctionstarttime = auctionstarttime;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

}
