package com.qianfeng.auction.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class AuctionRecord implements Serializable {
	
	private int id;
	private int auctionprice;
	private Timestamp auctiontime;
	private User user;
	private Auction auction;
	private Timestamp createtime;
	private Timestamp updatetime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAuctionprice() {
		return auctionprice;
	}

	public void setAuctionprice(int auctionprice) {
		this.auctionprice = auctionprice;
	}

	public Timestamp getAuctiontime() {
		return auctiontime;
	}

	public void setAuctiontime(Timestamp auctiontime) {
		this.auctiontime = auctiontime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Auction getAuction() {
		return auction;
	}

	public void setAuction(Auction auction) {
		this.auction = auction;
	}

	public Timestamp getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public Timestamp getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Timestamp updatetime) {
		this.updatetime = updatetime;
	}

}
